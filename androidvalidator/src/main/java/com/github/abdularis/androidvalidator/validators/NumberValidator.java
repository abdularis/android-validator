package com.github.abdularis.androidvalidator.validators;

import android.support.annotation.NonNull;

public class NumberValidator extends Validator {

    public NumberValidator(String message) {
        super(message);
    }

    @Override
    public boolean validate(@NonNull String text) {
        try {
            Long.valueOf(text);
        } catch (NumberFormatException ignored) {
            return false;
        }
        return true;
    }
}
