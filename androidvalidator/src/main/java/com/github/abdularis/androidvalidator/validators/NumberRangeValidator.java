package com.github.abdularis.androidvalidator.validators;

import android.support.annotation.NonNull;

public class NumberRangeValidator extends Validator {

    private long min;
    private long max;

    public NumberRangeValidator(long min, long max, String message) {
        super(message);
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean validate(@NonNull String text) {
        try {
            Long num = Long.valueOf(text);
            return num >= min && num <= max;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    @Override
    public String getMessage() {
        return String.format(super.getMessage(), min, max);
    }
}
