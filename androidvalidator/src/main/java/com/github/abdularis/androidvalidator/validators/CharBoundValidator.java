package com.github.abdularis.androidvalidator.validators;

import android.support.annotation.NonNull;

public class CharBoundValidator extends Validator {

    private int min;
    private int max;

    public CharBoundValidator(int min, int max, String message) {
        super(message);
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean validate(@NonNull String text) {
        return text.length() >= min && text.length() <= max;
    }

    @Override
    public String getMessage() {
        return String.format(super.getMessage(), min, max);
    }
}
