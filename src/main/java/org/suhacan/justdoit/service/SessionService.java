package org.suhacan.justdoit.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.suhacan.justdoit.dto.request.LoginRequest;
import org.suhacan.justdoit.dto.response.LoginResponse;

@Service
public interface SessionService {
    LoginResponse login(LoginRequest request);
    void logout(HttpServletRequest request, HttpServletResponse response);
}
