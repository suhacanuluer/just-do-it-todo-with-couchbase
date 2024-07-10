package org.suhacan.justdoit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.suhacan.justdoit.dto.model.UserDto;
import org.suhacan.justdoit.dto.request.user.AddUserRequest;
import org.suhacan.justdoit.dto.request.user.UpdateUserRequest;
import org.suhacan.justdoit.dto.response.user.UserResponse;
import org.suhacan.justdoit.mapper.UserMapper;
import org.suhacan.justdoit.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid AddUserRequest request) {
        UserDto userDto = userMapper.addUserRequestToUserDto(request);
        userDto = userService.addUser(userDto);
        return ResponseEntity.ok(userDto.getId());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUser(userId);
        return ResponseEntity.ok(userMapper.userDtotoUserResponse(userDto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("userId") String userId, @RequestBody @Valid UpdateUserRequest request) {
        UserDto userDto = userMapper.updateUserRequestToUserDto(request);
        userDto = userService.updateUser(userId, userDto);
        return ResponseEntity.ok(userMapper.userDtotoUserResponse(userDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
        String deletedUserId = userService.deleteUser(userId);
        return ResponseEntity.ok(deletedUserId);
    }
}
