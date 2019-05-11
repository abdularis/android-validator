package com.github.abdularis.anroidvalidatorsample;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.abdularis.androidvalidator.ValidationChain;
import com.github.abdularis.androidvalidator.ValidationChainGroup;

public class MainActivity extends AppCompatActivity {

    private ValidationChainGroup mValidationChainGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initValidationChains();
    }

    public void onCheckClick(View view) {
        TextInputLayout til1 = findViewById(R.id.tie1);
        TextInputLayout til2 = findViewById(R.id.tie2);

        til1.setError(null);
        til2.setError(null);
        til1.setErrorEnabled(false);
        til2.setErrorEnabled(false);

        if (mValidationChainGroup.check()) {
            Toast.makeText(this, "All data good", Toast.LENGTH_SHORT).show();
        }
    }

    private void initValidationChains() {
        TextInputLayout til1 = findViewById(R.id.tie1);
        TextInputLayout til2 = findViewById(R.id.tie2);

        EditText t1 = findViewById(R.id.et_1);
        EditText t2 = findViewById(R.id.et_2);

        mValidationChainGroup = new ValidationChainGroup();
        mValidationChainGroup.createNew(t1)
                .charMin(6, "Nama minimal 6 karakter")
                .setErrorCallback(til1::setError);
        mValidationChainGroup.createNew(t2)
                .isNotEmpty("Field harus diisi")
                .isNumber("Tahun harus berupa angka")
                .numberBetween(1990, 2000, "Tahun harus antara 1990 sampai 2000")
                .setErrorCallback(til2::setError);
    }
}
