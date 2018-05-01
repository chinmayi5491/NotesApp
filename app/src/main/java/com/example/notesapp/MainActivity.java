package com.example.notesapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DBDataManager dbDataManager;
    private EditText editTextFilter;
    private final String TAG = "Demo";
    private final int CREATE_USERS_RES = 100;
    private SQLiteDatabase db;
    private Button addContactButton;
    private Button filterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbDataManager = new DBDataManager(this);

        Log.d("demo", "onCreate: " + dbDataManager.getAllContacts());


        //dbDataManager.saveContact(contact);

        dbDataManager.close();

        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivity(intent);
            }
        });


        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Contact> contacts = new ArrayList<>();

                Cursor cursor = db.query(ContactsTable.TABLENAME,
                        new String[]{ContactsTable.COLUMN_ID,
                                ContactsTable.COLUMN_NAME,
                                ContactsTable.COLUMN_EMAIL,
                                ContactsTable.COLUMN_DEPARTMENT,
                                ContactsTable.COLUMN_PHONE},
                        ContactsTable.COLUMN_NAME + "Like ?", new String[]{"%"+ "filter" +"%"},
                        null, null, null);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE_USERS_RES && resultCode == RESULT_OK) {

        }
    }
}
