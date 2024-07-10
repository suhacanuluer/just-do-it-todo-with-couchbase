package org.suhacan.justdoit.service.impl;

import com.couchbase.client.core.error.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.suhacan.justdoit.dto.model.UserDto;
import org.suhacan.justdoit.entity.User;
import org.suhacan.justdoit.exception.UsernameAlreadyExistException;
import org.suhacan.justdoit.mapper.UserMapper;
import org.suhacan.justdoit.repository.UserRepository;
import org.suhacan.justdoit.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto addUser(UserDto userDto) {
        checkUsernameAlreadyExist(userDto);

        User user = userMapper.toEntity(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto getUser(String userId) {
        User user = getUserByIdOrThrow(userId);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        checkUsernameAlreadyExist(userDto);

        User user = getUserByIdOrThrow(userId);
        user.setPassword(userDto.getPassword());
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public String deleteUser(String userId) {
        User user = getUserByIdOrThrow(userId);
        userRepository.delete(user);
        return user.getId();
    }

    @Override
    public UserDto findUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User service", "User not found with username: " + username));

        return userMapper.toDto(user);
    }

    private void checkUsernameAlreadyExist(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername()).equals(true))
            throw new UsernameAlreadyExistException("username already exist: " + userDto.getUsername());
    }

    private User getUserByIdOrThrow(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
    }
}
