package com.github.abdularis.androidvalidator;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ValidatorChain {

    private List<Validator> mValidators = new ArrayList<>();
    private ErrorCallback mErrorCallback;
    private EditText mText;

    public ValidatorChain(EditText text) {
        mText = text;
    }

    public ValidatorChain isNotEmpty(String errMessage) {
        mValidators.add(new NonEmptyValidator(errMessage));
        return this;
    }

    public ValidatorChain charBetween(String errMessage, int minChar, int maxChar) {
        mValidators.add(new CharBoundValidator(errMessage, minChar, maxChar));
        return this;
    }

    public ValidatorChain setErrorCallback(ErrorCallback errorCallback) {
        mErrorCallback = errorCallback;
        return this;
    }

    public boolean check() {
        String text = mText.getText().toString();
        for (Validator validator : mValidators) {
            boolean valid = validator.validate(text);

            if (!valid) {
                mText.setError(validator.getMessage());
                mErrorCallback.onError(validator.getMessage());
                return false;
            }
        }

        return true;
    }

    public interface ErrorCallback {
        void onError(String message);
    }
}
