package com.example.helloworld.auth.service.impl;

import com.example.helloworld.User.entity.User;
import com.example.helloworld.auth.dto.request.LoginRequest;
import com.example.helloworld.auth.dto.request.RefreshTokenRequest;
import com.example.helloworld.auth.dto.response.LoginResponse;
import com.example.helloworld.auth.dto.response.RefreshTokenResponse;
import com.example.helloworld.auth.entity.RefreshToken;
import com.example.helloworld.auth.entity.Role;
import com.example.helloworld.auth.jwt.JwtService;
import com.example.helloworld.auth.repository.RefreshTokenRepository;
import com.example.helloworld.auth.security.CustomUserDetails;
import com.example.helloworld.auth.service.AuthService;
import com.example.helloworld.auth.service.CustomUserDetailsService;
import com.example.helloworld.config.AppProperties;
import com.example.helloworld.exception.BadRequestException;
import com.example.helloworld.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomUserDetailsService userDetailsService;
    private final AppProperties appProperties;

    @Override
    @Transactional
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        String accessToken = jwtService.generateToken(userDetails);
        String refreshTokenStr = createRefreshToken(user);

        List<String> roleNames = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        return LoginResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roles(roleNames)
                .accessToken(accessToken)
                .refreshToken(refreshTokenStr)
                .tokenType("Bearer")
                .build();
    }

    @Override
    @Transactional
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new NotFoundException("Refresh Token tidak ditemukan"));

        if (refreshToken.getRevoked()) {
            throw new BadRequestException("Refresh Token sudah tidak berlaku (revoked)");
        }

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new BadRequestException("Refresh Token sudah kadaluarsa, silakan login kembali");
        }

        User user = refreshToken.getUser();
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(user.getEmail());

        String newAccessToken = jwtService.generateToken(userDetails);

        return RefreshTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken.getToken())
                .tokenType("Bearer")
                .build();
    }

    @Override
    @Transactional
    public void logout(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new NotFoundException("Refresh Token tidak ditemukan"));

        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }

    private String createRefreshToken(User user) {
        refreshTokenRepository.deleteByUser(user);

        String token = UUID.randomUUID().toString();
        Instant expiryDate = Instant.now().plusMillis(appProperties.getJwt().getRefreshExpiration());

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(token)
                .expiryDate(expiryDate)
                .revoked(false)
                .build();

        refreshTokenRepository.save(refreshToken);
        return token;
    }
}