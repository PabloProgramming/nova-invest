package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.User;
import com.pablodev9.novainvest.model.dto.UserDto;
import com.pablodev9.novainvest.model.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User requestDtoToEntity(final UserDto userDto) {
        User user = new User();
        user.setId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRole(userDto.getRole());
        return user;
    }

    public UserResponseDto entityToResponseDto(final User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .createdAt(user.getCreatedAt().toString())
                .build();
    }

}
