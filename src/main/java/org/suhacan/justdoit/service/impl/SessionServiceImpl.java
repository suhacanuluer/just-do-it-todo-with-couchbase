package org.suhacan.justdoit.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.suhacan.justdoit.dto.model.UserDto;
import org.suhacan.justdoit.dto.request.LoginRequest;
import org.suhacan.justdoit.dto.response.LoginResponse;
import org.suhacan.justdoit.security.TokenManager;
import org.suhacan.justdoit.service.SessionService;
import org.suhacan.justdoit.service.UserService;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

    @Override
    public LoginResponse login(LoginRequest request) {
        UserDto user = userService.findUserByUsername(request.getUsername());

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return LoginResponse.builder()
                .token(tokenManager.generateJwtToken(authentication))
                .userId(user.getId())
                .build();
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) new SecurityContextLogoutHandler().logout(request, response, auth);
    }
}
