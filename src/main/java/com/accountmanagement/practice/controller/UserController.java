package com.accountmanagement.practice.controller;

import com.accountmanagement.practice.dto.UserMapping.UserReq;
import com.accountmanagement.practice.model.User;
import com.accountmanagement.practice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(fileController.class);
    private final UserService userService;

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody UserReq dto) {
        logger.info("user saved successfully");
        return userService.saveUser(dto.getFirstName(), dto.getLastName(), dto.getUserName(), dto.getPassword());
    }

    @GetMapping("/getById/{userId}")
    public User getById(@PathVariable("userId") int userId) {
        return userService.getbyId(userId);
    }

    @GetMapping("/getAll")
    public List<User> getAll() {
        return userService.findAll();
    }

}
