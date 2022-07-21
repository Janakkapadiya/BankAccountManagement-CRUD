package com.accountmanagement.practice.Exceptions;

public class NotSufficientBalance extends RuntimeException{
	
		    private static final long serialVersionUID = 1L;

			public NotSufficientBalance () {
		        super("Account Not Found!!");
		    }
		}

