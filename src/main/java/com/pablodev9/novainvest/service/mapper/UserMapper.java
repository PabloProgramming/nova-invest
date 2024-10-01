package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.User;
import com.pablodev9.novainvest.model.dto.UserDto;
import com.pablodev9.novainvest.model.dto.UserPortfolioDto;
import com.pablodev9.novainvest.model.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    @Autowired
    PortfolioMapper portfolioMapper;

    public User requestDtoToEntity(final UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setBalance(userDto.getBalance());
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
                .balance(user.getBalance())
                .role(user.getRole())
                .createdAt(user.getCreatedAt().toString())
                .build();
    }

    public UserPortfolioDto toResponseDto(final User user) {
        return UserPortfolioDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .balance(user.getBalance())
                .updatedAt(user.getUpdatedAt())
                .portfolioSummaryDtos(portfolioMapper.toSummaryDtos(user.getPortfolios()))
                .build();
    }

}
