package com.geektrust.exceptions;

public class FundNotFoundException extends RuntimeException {

    public FundNotFoundException() {
        super();
    }

    public FundNotFoundException(String message) {
        super(message);
    }
}
