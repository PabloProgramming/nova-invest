package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.UserDto;
import com.pablodev9.novainvest.model.dto.UserResponseDto;
import com.pablodev9.novainvest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    private ResponseEntity<UserResponseDto> registerUser(@RequestBody final UserDto userDto) {
        return ResponseEntity.ok(userService.registerUser(userDto));
    }

    @PutMapping("/update")
    private ResponseEntity<UserResponseDto> updateUser(@RequestBody final UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @GetMapping("/{userId}")
    private ResponseEntity<UserResponseDto> getUserById(@PathVariable final Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<Long> deleteUser(@PathVariable final Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

}
