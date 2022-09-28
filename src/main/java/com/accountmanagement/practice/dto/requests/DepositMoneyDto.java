package com.accountmanagement.practice.dto.requests;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class DepositMoneyDto {
    @Min(value = 100, message = "must deposit more then 100 rupees")
    private int amount;
}
