package org.suhacan.justdoit.decorator;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.suhacan.justdoit.dto.model.UserDto;
import org.suhacan.justdoit.entity.User;
import org.suhacan.justdoit.mapper.UserMapper;

public abstract class UserMapperDecorator implements UserMapper {

    @Setter(onMethod = @__({@Autowired}))
    private UserMapper userMapper;

    @Setter(onMethod = @__({@Autowired}))
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User toEntity(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
