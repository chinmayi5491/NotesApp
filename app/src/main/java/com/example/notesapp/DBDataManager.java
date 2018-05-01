package com.example.notesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by mshehab on 4/27/18.
 */

public class DBDataManager {
    private Context mContext;
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private ContactDAO contactDAO;

    public DBDataManager(Context mContext) {
        this.mContext = mContext;

        dbOpenHelper = new DBOpenHelper(this.mContext);
        db = dbOpenHelper.getWritableDatabase();
        contactDAO = new ContactDAO(db);
    }

    public void close(){
        if(db != null && db.isOpen()){
            db.close();
        }
    }

    public long saveContact(Contact contact){
        return this.contactDAO.save(contact);
    }

    public boolean updateContact(Contact contact){
        return this.contactDAO.update(contact);
    }

    public boolean deleteContact(Contact contact){
        return this.contactDAO.delete(contact);
    }

    public Contact getContact(long id){
        return this.contactDAO.get(id);
    }

    public List<Contact> getAllContacts(){
        return this.contactDAO.getAll();
    }


}
