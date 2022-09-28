package com.accountmanagement.practice.dto.requests;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateAccountReq {
    @NotBlank
    @Size(min = 3, message = "user should at least have 3 characters ")
    private String name;
    @Min(value = 100, message = "user must add minimum amount of 100 rupees")
    private int amount;
}
