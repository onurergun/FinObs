package com.onurergun.finobs.infrastructure.user;

import com.onurergun.finobs.application.user.UserDto;
import com.onurergun.finobs.controller.user.CreateUserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    
    UserDto createUserRequestToUserDto(CreateUserRequest createUserRequest);
}
