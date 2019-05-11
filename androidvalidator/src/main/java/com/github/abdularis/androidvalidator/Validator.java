package com.github.abdularis.androidvalidator;

public abstract class Validator {

    private String mMessage;

    public Validator(String message) {
        mMessage = message;
    }

    public abstract boolean validate(String text);

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}
