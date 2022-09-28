package com.accountmanagement.practice.dto.UserMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserReq {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;


    private String userName;

    private String password;
}
