package com.accountmanagement.practice.dto.requests;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class WithdrawAmountReq {
    @Min(value = 100, message = "min value you have to withdraw is 100")
    @Max(value = 5000, message = "max value you can withdraw is 5000")
    private int amount;
}
