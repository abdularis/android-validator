package com.github.abdularis.androidvalidator.validators;

import android.support.annotation.NonNull;

public class CharMinValidator extends Validator {

    private int min;

    public CharMinValidator(int min, String message) {
        super(message);
        this.min = min;
    }

    @Override
    public boolean validate(@NonNull String text) {
        return text.length() >= min;
    }
}
