package com.github.abdularis.androidvalidator.validators;

import android.support.annotation.NonNull;

public abstract class Validator {

    private String mMessage;

    public Validator(String message) {
        mMessage = message;
    }

    public abstract boolean validate(@NonNull String text);

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}
