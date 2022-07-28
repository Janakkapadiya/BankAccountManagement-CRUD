package com.accountmanagement.practice.Services;
import com.accountmanagement.practice.Model.User;
import com.accountmanagement.practice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(String firstName, String lastName , String userName , String password)
    {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public User getbyId(int userId)
    {
        Optional<User> byId = userRepository.findById(userId);
        if(byId.isPresent())
        {
            return byId.get();
        }else{
            throw new RuntimeException("User not found!");
        }
    }

    public User register(String userName,String password)
    {
        User user = new User();
        user.setUserName(userName);
        user.setPassword((password));
        userRepository.save(user);
        return user;
    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }
}

