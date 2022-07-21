package com.accountmanagement.practice.Services;
import com.accountmanagement.practice.Model.Transaction;
import com.accountmanagement.practice.Model.User;
import com.accountmanagement.practice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User saveUser(String firstName, String lastName)
    {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
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
    public List<User> findAll()
    {
        return userRepository.findAll();
    }
}

