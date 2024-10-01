package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.User;
import com.pablodev9.novainvest.model.dto.UserDto;
import com.pablodev9.novainvest.model.dto.UserPortfolioDto;
import com.pablodev9.novainvest.model.dto.UserResponseDto;
import com.pablodev9.novainvest.repository.UserRepository;
import com.pablodev9.novainvest.service.mapper.UserMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;

    public UserResponseDto registerUser(final UserDto userDto) {
        final User user = userMapper.requestDtoToEntity(userDto);
        user.setBalance(BigDecimal.ZERO);
        final User savedUser = userRepository.save(user);
        return userMapper.entityToResponseDto(savedUser);
    }

    public UserPortfolioDto getUserPortfolio(final Long userId) {
        final User user = findUserById(userId);
        return userMapper.toResponseDto(user);
    }

    @SneakyThrows
    public User findUserById(final Long userId) {
        final Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new Exception();
    }
}
