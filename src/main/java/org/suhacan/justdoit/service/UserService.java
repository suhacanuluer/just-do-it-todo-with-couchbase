package org.suhacan.justdoit.service;

import org.springframework.stereotype.Service;
import org.suhacan.justdoit.dto.model.UserDto;

@Service
public interface UserService {
    UserDto addUser(UserDto userDto);
    UserDto getUser(String userId);
    UserDto updateUser(String userId, UserDto userDto);
    String deleteUser(String userId);
    UserDto findUserByUsername(String username);
}
