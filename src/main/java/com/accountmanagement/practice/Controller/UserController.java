package com.accountmanagement.practice.Controller;

import com.accountmanagement.practice.Exceptions.AccountNotFoundException;
import com.accountmanagement.practice.Model.User;
import com.accountmanagement.practice.Services.UserService;
import com.accountmanagement.practice.dto.UserMapping.UserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserAndTransactionController {
    private final UserService userService;

    @PostMapping("/saveUser/{transactionId}")
    public User saveUser(@PathVariable("transactionId") int transactionId,@RequestBody UserReq dto)
    {
        return userService.saveUser(transactionId,dto.getFirstName(), dto.getLastName());
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
