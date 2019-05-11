package com.github.abdularis.androidvalidator;

public class NonEmptyValidator extends Validator {

    public NonEmptyValidator(String message) {
        super(message);
    }

    @Override
    public boolean validate(String text) {
        return !text.isEmpty();
    }
}
