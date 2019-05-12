package com.github.abdularis.androidvalidator.validators;

import android.support.annotation.NonNull;
import android.widget.EditText;

public class SameEditTextContentValidato extends Validator {

    private EditText mEditText;

    public SameEditTextContentValidato(EditText editText, String message) {
        super(message);
        mEditText = editText;
    }

    @Override
    public boolean validate(@NonNull String text) {
        return text.equals(mEditText.getText().toString());
    }
}
