package com.github.abdularis.androidvalidator;

import android.widget.EditText;

import com.github.abdularis.androidvalidator.validators.CharBoundValidator;
import com.github.abdularis.androidvalidator.validators.CharMinValidator;
import com.github.abdularis.androidvalidator.validators.EmailValidator;
import com.github.abdularis.androidvalidator.validators.NonEmptyValidator;
import com.github.abdularis.androidvalidator.validators.NumberRangeValidator;
import com.github.abdularis.androidvalidator.validators.NumberValidator;
import com.github.abdularis.androidvalidator.validators.Validator;

import java.util.ArrayList;
import java.util.List;

public class ValidationChain {

    private List<Validator> mValidators = new ArrayList<>();
    private ErrorCallback mErrorCallback;
    private SuccessCallback mSuccessCallback;
    private EditText mText;

    public static ValidationChain create(EditText editText) {
        return new ValidationChain(editText);
    }

    private ValidationChain(EditText text) {
        mText = text;
    }

    public ValidationChain isNotEmpty(String errMessage) {
        mValidators.add(new NonEmptyValidator(errMessage));
        return this;
    }

    public ValidationChain isEmail(String errMessage) {
        mValidators.add(new EmailValidator(errMessage));
        return this;
    }

    public ValidationChain isNumber(String errMessage) {
        mValidators.add(new NumberValidator(errMessage));
        return this;
    }

    public ValidationChain numberBetween(long min, long max, String errMessage) {
        mValidators.add(new NumberRangeValidator(min, max, errMessage));
        return this;
    }

    public ValidationChain charBetween(int minChar, int maxChar, String errMessage) {
        mValidators.add(new CharBoundValidator(minChar, maxChar, errMessage));
        return this;
    }

    public ValidationChain charMin(int min, String errMessage) {
        mValidators.add(new CharMinValidator(min, errMessage));
        return this;
    }

    public ValidationChain addValidator(Validator validator) {
        mValidators.add(validator);
        return this;
    }

    public ValidationChain setErrorCallback(ErrorCallback errorCallback) {
        mErrorCallback = errorCallback;
        return this;
    }

    public ValidationChain setSuccessCallback(SuccessCallback successCallback) {
        mSuccessCallback = successCallback;
        return this;
    }

    public boolean check() {
        String text = mText.getText().toString();
        for (Validator validator : mValidators) {
            if (!validator.validate(text)) {
                if (mErrorCallback != null) {
                    mErrorCallback.onError(validator.getMessage());
                }
                return false;
            }
        }

        if (mSuccessCallback != null) {
            mSuccessCallback.onSuccess(text);
        }
        return true;
    }

    public interface ErrorCallback {
        void onError(String message);
    }

    public interface SuccessCallback {
        void onSuccess(String text);
    }
}
