package org.suhacan.justdoit.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.suhacan.justdoit.dto.model.UserDto;
import org.suhacan.justdoit.dto.request.user.AddUserRequest;
import org.suhacan.justdoit.dto.request.user.UpdateUserRequest;
import org.suhacan.justdoit.dto.response.user.UserResponse;
import org.suhacan.justdoit.entity.User;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper extends EntityMapper<UserDto, User>{
    UserDto addUserRequestToUserDto(AddUserRequest request);
    UserResponse userDtotoUserResponse(UserDto userDto);
    UserDto updateUserRequestToUserDto(UpdateUserRequest request);
}
