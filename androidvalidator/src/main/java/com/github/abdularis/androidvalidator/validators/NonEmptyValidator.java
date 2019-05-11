package com.github.abdularis.androidvalidator.validators;

import android.support.annotation.NonNull;

public class NonEmptyValidator extends Validator {

    public NonEmptyValidator(String message) {
        super(message);
    }

    @Override
    public boolean validate(@NonNull String text) {
        return !text.isEmpty();
    }
}
