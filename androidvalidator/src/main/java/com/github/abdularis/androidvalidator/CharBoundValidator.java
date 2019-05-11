package com.github.abdularis.androidvalidator;

public class CharBoundValidator extends Validator {

    private int min;
    private int max;

    public CharBoundValidator(String message, int min, int max) {
        super(message);
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean validate(String text) {
        return text.length() >= min && text.length() <= max;
    }
}
