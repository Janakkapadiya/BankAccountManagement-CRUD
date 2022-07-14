package com.accountmanagement.practice.dto.requests;

import lombok.Data;

@Data
public class CreateAccountReq {
    private int id;
    private String name;
    private int amount;
}
