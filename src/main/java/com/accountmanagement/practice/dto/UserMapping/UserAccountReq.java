package com.accountmanagement.practice.dto.UserMapping;

import com.accountmanagement.practice.Model.Accounts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAccountReq {
    private int userId;
    private List<Accounts> accounts;
}
