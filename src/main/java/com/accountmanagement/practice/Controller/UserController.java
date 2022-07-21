package com.accountmanagement.practice.Controller;

import com.accountmanagement.practice.Model.User;
import com.accountmanagement.practice.Services.UserService;
import com.accountmanagement.practice.dto.UserMapping.UserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody UserReq dto)
    {
        return userService.saveUser(dto.getFirstName(), dto.getLastName());
    }

    @GetMapping("/getById/{userId}")
    @PreAuthorize("hasRole('USER')")
    public User getById(@PathVariable("userId") int userId){
        return userService.getbyId(userId);
    }

    @GetMapping("/getAll")
    public List<User> getAll()
    {
        return userService.findAll();
    }

}
