package com.github.abdularis.androidvalidator;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ValidationChainGroup {

    private List<ValidationChain> mValidationChains = new ArrayList<>();

    public ValidationChain createNew(EditText editText) {
        ValidationChain validationChain = ValidationChain.create(editText);
        add(validationChain);
        return validationChain;
    }

    public void add(ValidationChain validationChain) {
        mValidationChains.add(validationChain);
    }

    public void remove(ValidationChain validationChain) {
        mValidationChains.remove(validationChain);
    }

    public void clear() {
        mValidationChains.clear();
    }

    public boolean check() {
        boolean result = true;
        for (ValidationChain validationChain : mValidationChains) {
            result = validationChain.check() && result;
        }

        return result;
    }
}
