package com.accountmanagement.practice.dto.FamilyAccountReq;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FamilyAccountDto {
    private String name;
    private int amount;
}
