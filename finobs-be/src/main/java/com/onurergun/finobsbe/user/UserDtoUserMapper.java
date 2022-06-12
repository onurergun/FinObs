package com.onurergun.finobsbe.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoUserMapper {
    @Mapping(target = "registerDate", ignore = true)
    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}
