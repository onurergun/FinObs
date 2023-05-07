package com.onurergun.finobsbe.infrastructure.user;

import com.onurergun.finobsbe.application.user.UserDto;
import com.onurergun.finobsbe.controller.user.CreateUserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    
    UserDto createUserRequestToUserDto(CreateUserRequest createUserRequest);
}
