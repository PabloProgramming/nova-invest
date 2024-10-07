package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.User;
import com.pablodev9.novainvest.model.dto.UserDto;
import com.pablodev9.novainvest.model.dto.UserResponseDto;
import com.pablodev9.novainvest.repository.UserRepository;
import com.pablodev9.novainvest.service.mapper.UserMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    public UserResponseDto registerUser(final UserDto userDto) {
        final User user = userMapper.requestDtoToEntity(userDto);
        final User savedUser = userRepository.save(user);
        accountService.createAccountForUser(savedUser);
        return userMapper.entityToResponseDto(savedUser);
    }

    public UserResponseDto updateUser(final UserDto userDto) {
        User user = findUserById(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        final User updatedUser = userRepository.save(user);
        return userMapper.entityToResponseDto(updatedUser);
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
