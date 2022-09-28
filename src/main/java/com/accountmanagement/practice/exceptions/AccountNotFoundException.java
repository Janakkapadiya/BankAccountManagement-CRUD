package com.accountmanagement.practice.exceptions;

import java.io.Serial;

public class AccountNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public AccountNotFoundException() {
        super("Account Not Found!!");
    }
}


