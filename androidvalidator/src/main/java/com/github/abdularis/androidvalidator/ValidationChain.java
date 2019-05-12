package com.github.abdularis.androidvalidator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.EditText;

import com.github.abdularis.androidvalidator.validators.CharBoundValidator;
import com.github.abdularis.androidvalidator.validators.CharMinValidator;
import com.github.abdularis.androidvalidator.validators.EmailValidator;
import com.github.abdularis.androidvalidator.validators.NonEmptyValidator;
import com.github.abdularis.androidvalidator.validators.NumberRangeValidator;
import com.github.abdularis.androidvalidator.validators.NumberValidator;
import com.github.abdularis.androidvalidator.validators.SameEditTextContentValidato;
import com.github.abdularis.androidvalidator.validators.Validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ValidationChain {

    private List<Validator> mValidators = new ArrayList<>();
    private ErrorCallback mErrorCallback;
    private SuccessCallback mSuccessCallback;
    private boolean mShowEditTextError;
    private EditText mText;
    private Context context;

    public static ValidationChain create(@NonNull EditText editText) {
        return new ValidationChain(editText);
    }

    private ValidationChain(@NonNull EditText text) {
        mText = text;
        context = text.getContext();
        mShowEditTextError = true;
    }

    public ValidationChain enableEditTextError(boolean enable) {
        mShowEditTextError = enable;
        return this;
    }

    public ValidationChain isNotEmpty(String errMessage) {
        mValidators.add(new NonEmptyValidator(errMessage));
        return this;
    }

    public ValidationChain isNotEmpty() {
        return isNotEmpty(getString(R.string.av_field_empty));
    }

    public ValidationChain isEmail(String errMessage) {
        mValidators.add(new EmailValidator(errMessage));
        return this;
    }

    public ValidationChain isEmail() {
        return isEmail(getString(R.string.av_email_invalid));
    }

    public ValidationChain isNumber(String errMessage) {
        mValidators.add(new NumberValidator(errMessage));
        return this;
    }

    public ValidationChain isNumber() {
        return isNumber(getString(R.string.av_not_number));
    }

    public ValidationChain numberBetween(long min, long max, String errMessage) {
        mValidators.add(new NumberRangeValidator(min, max, errMessage));
        return this;
    }

    public ValidationChain numberBetween(long min, long max) {
        return numberBetween(min, max, getString(R.string.av_number_between));
    }

    public ValidationChain yearFromToNow(int yearFrom) {
        long max = Calendar.getInstance().get(Calendar.YEAR);
        return numberBetween(yearFrom, max, getString(R.string.av_year_to_now));
    }

    public ValidationChain charBetween(int minChar, int maxChar, String errMessage) {
        mValidators.add(new CharBoundValidator(minChar, maxChar, errMessage));
        return this;
    }

    public ValidationChain charBetween(int minChar, int maxChar) {
        return charBetween(minChar, maxChar, getString(R.string.av_string_between));
    }

    public ValidationChain charMin(int min, String errMessage) {
        mValidators.add(new CharMinValidator(min, errMessage));
        return this;
    }

    public ValidationChain charMin(int min) {
        return charMin(min, getString(R.string.av_char_min));
    }

    public ValidationChain equalsWith(EditText editText, String errMessage) {
        mValidators.add(new SameEditTextContentValidato(editText, errMessage));
        return this;
    }

    public ValidationChain passwordConfirmFrom(EditText editText) {
        return equalsWith(editText, getString(R.string.av_et_password_confirm_err));
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
                String errMessage = validator.getMessage();
                if (mShowEditTextError) {
                    mText.setError(errMessage);
                }

                if (mErrorCallback != null) {
                    mErrorCallback.onError(errMessage);
                }
                return false;
            }
        }

        if (mSuccessCallback != null) {
            mSuccessCallback.onSuccess(text);
        }
        return true;
    }

    private String getString(@StringRes int str) {
        return context.getString(str);
    }

    public interface ErrorCallback {
        void onError(String message);
    }

    public interface SuccessCallback {
        void onSuccess(String text);
    }
}
