package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.UserDto;
import com.pablodev9.novainvest.model.dto.UserPortfolioDto;
import com.pablodev9.novainvest.model.dto.UserResponseDto;
import com.pablodev9.novainvest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    private ResponseEntity<UserResponseDto> registerUser(@RequestBody final UserDto userDto) {
        return ResponseEntity.ok(userService.registerUser(userDto));
    }

    @GetMapping("/me/{userId}")
    private ResponseEntity<UserPortfolioDto> getUserPortfolio(@PathVariable final Long userId) {
        return ResponseEntity.ok(userService.getUserPortfolio(userId));
    }

    @PutMapping("/me")
    private ResponseEntity<UserResponseDto> updateUser(@RequestBody final UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

}
