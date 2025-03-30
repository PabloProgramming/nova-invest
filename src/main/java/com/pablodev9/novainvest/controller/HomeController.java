package com.pablodev9.novainvest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public Map<String, Object> home() {
        return Map.of(
                "message", "Welcome to Nova Invest API 🚀",
                "endpoints", List.of(
                        Map.of(
                                "method", "POST",
                                "url", "/api/user/register",
                                "description", "Register a new user",
                                "requestBody", Map.of(
                                        "userName", "investorX99",
                                        "email", "investorX99@example.com",
                                        "password", "SecurePass2025!",
                                        "firstName", "Xavier",
                                        "lastName", "Fernandez",
                                        "phoneNumber", "+1 617-555-1234",
                                        "role", "USER"
                                ),
                                "responseBody", Map.of(
                                        "userId", 102,
                                        "userName", "investorX99",
                                        "email", "investorX99@example.com",
                                        "firstName", "Xavier",
                                        "lastName", "Fernandez",
                                        "phoneNumber", "+1 617-555-1234",
                                        "role", "USER"
                                )
                        ),
                        Map.of(
                                "method", "PUT",
                                "url", "/api/user/update",
                                "description", "Update an existing user",
                                "requestBody", Map.of(
                                        "userId", 456,
                                        "userName", "Maverick22",
                                        "email", "maverick22@fintech.com",
                                        "firstName", "Emily",
                                        "lastName", "Clarkson",
                                        "phoneNumber", "+44 7911 432567"
                                ),
                                "responseBody", Map.of(
                                        "userId", 456,
                                        "userName", "Maverick22",
                                        "email", "maverick22@fintech.com",
                                        "firstName", "Emily",
                                        "lastName", "Clarkson",
                                        "phoneNumber", "+44 7911 432567"
                                )
                        ),
                        Map.of(
                                "method", "GET",
                                "url", "/api/user/{userId}",
                                "description", "Retrieve user by ID",
                                "responseBody", Map.of(
                                        "userId", 789,
                                        "userName", "AlphaTrader",
                                        "email", "alphatrader@marketplace.com",
                                        "firstName", "Jordan",
                                        "lastName", "Brooks",
                                        "phoneNumber", "+49 170 3456789"
                                )
                        ),
                        Map.of(
                                "method", "DELETE",
                                "url", "/api/user/{userId}",
                                "description", "Delete user by ID",
                                "responseBody", "User with ID {userId} has been successfully removed."
                        )
                )
        );
    }
}
