package com.example.notesapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private SQLiteDatabase db;

    public ContactDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Contact contact){
        ContentValues values = new ContentValues();
        values.put(ContactsTable.COLUMN_NAME, contact.getName());
        values.put(ContactsTable.COLUMN_DEPARTMENT, contact.getDepartment());
        values.put(ContactsTable.COLUMN_EMAIL, contact.getEmail());
        values.put(ContactsTable.COLUMN_PHONE, contact.getPhone());
        return db.insert(ContactsTable.TABLENAME, null, values);
    }

    public boolean update(Contact contact){
        ContentValues values = new ContentValues();
        values.put(ContactsTable.COLUMN_ID, contact.getId());
        values.put(ContactsTable.COLUMN_NAME, contact.getName());
        values.put(ContactsTable.COLUMN_EMAIL, contact.getEmail());
        values.put(ContactsTable.COLUMN_DEPARTMENT, contact.getDepartment());
        values.put(ContactsTable.COLUMN_PHONE, contact.getPhone());

        return db.update(ContactsTable.TABLENAME, values,
                ContactsTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(contact.getId())}) > 0;
    }

    public boolean delete(Contact contact){
        return db.delete(ContactsTable.TABLENAME,
                ContactsTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(contact.getId())}) > 0;
    }

    public Contact get(long id){
        Contact contact = null;

        Cursor cursor = db.query(ContactsTable.TABLENAME,
                new String[]{ContactsTable.COLUMN_ID,
                        ContactsTable.COLUMN_NAME,
                        ContactsTable.COLUMN_EMAIL,
                        ContactsTable.COLUMN_DEPARTMENT,
                        ContactsTable.COLUMN_PHONE},
                ContactsTable.COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);

        if(cursor != null && cursor.moveToFirst()){
            contact = buildContactFromCursor(cursor);
            if(!cursor.isClosed()){
                cursor.close();
            }
        }

        return contact;
    }

    public List<Contact> getAll(){
        ArrayList<Contact> contacts = new ArrayList<>();

        Cursor cursor = db.query(ContactsTable.TABLENAME,
                new String[]{ContactsTable.COLUMN_ID,
                        ContactsTable.COLUMN_NAME,
                        ContactsTable.COLUMN_EMAIL,
                        ContactsTable.COLUMN_DEPARTMENT,
                        ContactsTable.COLUMN_PHONE},
                null, null,
                null, null, null);


        if(cursor != null && cursor.moveToFirst()){

            do{
                Contact contact = buildContactFromCursor(cursor);
                contacts.add(contact);
            } while (cursor.moveToNext());


            if(!cursor.isClosed()){
                cursor.close();
            }
        }
        return contacts;
    }

    public Contact buildContactFromCursor(Cursor cursor){
        Contact contact = new Contact();
        contact.setId(cursor.getLong(0));
        contact.setName(cursor.getString(1));
        contact.setEmail(cursor.getString(2));
        contact.setDepartment(cursor.getString(3));
        contact.setPhone(cursor.getString(4));
        return contact;
    }

}
