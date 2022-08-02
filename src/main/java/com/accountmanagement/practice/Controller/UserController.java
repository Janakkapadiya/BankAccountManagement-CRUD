package com.accountmanagement.practice.Controller;

import com.accountmanagement.practice.Model.User;
import com.accountmanagement.practice.Services.UserService;
import com.accountmanagement.practice.dto.UserMapping.UserReq;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(fileController.class);

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody UserReq dto)
    {
        logger.info("user saved successfully");
        return userService.saveUser(dto.getFirstName(), dto.getLastName(),dto.getUserName(),dto.getPassword());
    }

    @GetMapping("/getById/{userId}")
    public User getById(@PathVariable("userId") int userId){
        return userService.getbyId(userId);
    }

    @GetMapping("/getAll")
    public List<User> getAll()
    {
        return userService.findAll();
    }

}
