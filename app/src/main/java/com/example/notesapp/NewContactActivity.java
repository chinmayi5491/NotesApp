package com.example.notesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

public class NewContactActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText emailInput;
    private EditText phoneInput;

    private RadioGroup departmentGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        nameInput = findViewById(R.id.editTextName);
        emailInput = findViewById(R.id.editTextEmail);
        phoneInput = findViewById(R.id.editTextPhoneNo);

        departmentGroup = findViewById(R.id.departmentSelection);

        departmentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
    }
}
