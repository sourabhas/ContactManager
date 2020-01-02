package com.soushetty.contactmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.soushetty.contactmanager.Model.Contacts;
import com.soushetty.contactmanager.R;
import com.soushetty.contactmanager.util.Util;

import java.util.ArrayList;
import java.util.List;

/* Database Class which performs the following functions:
creating the table Contacts,
update,delete and inserting of contacts
 retrieving one or all contacts and total number of contacts stored*/
public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context)
    {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating the table
       /* String CREATE_CONTACTS_TABLE="CREATE TABLE "+Util.TABLE_NAME+
                "("+Util.KEY_ID+"INTEGER PRIMARY KEY,"
                +Util.KEY_NAME+"TEXT"
                +Util.KEY_PHONE_NUMBER+"TEXT" +")";*/

        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";
        db.execSQL(CREATE_CONTACT_TABLE);
        Log.d("table_created","table created"+Util.KEY_NAME);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE=String.valueOf(R.string.DROP_TABLE);
        db.execSQL(DROP_TABLE,new String[]{Util.DATABASE_NAME});

        //create table again
        onCreate(db);
    }

    /*
    CRUD-create ,read,update,delete
     */
    public void addContact(Contacts contacts){

        SQLiteDatabase db=this.getWritableDatabase(); //since we want to add contacts ,we are importing this sql method
        //to store these values we have a database structure called -contentValues

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contacts.getName());
        values.put(Util.KEY_PHONE_NUMBER, contacts.getPhone_number());

        //Insert to row
        db.insert(Util.TABLE_NAME, null, values);

        Log.d("DBHandler", "addContact: " + "item added");
        db.close(); //closing db connection!
    }

    //Get a contact
    public Contacts getContact(int id){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(Util.TABLE_NAME,new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_PHONE_NUMBER},Util.KEY_ID+"=?",new String[]{String.valueOf(id)},null,null
        ,null);

        if(cursor!=null) {
            cursor.moveToFirst();
        }

        Contacts contacts=new Contacts();
        contacts.setId(Integer.parseInt(cursor.getString(0)));
        contacts.setName(cursor.getString(1));
        contacts.setPhone_number(cursor.getString(2));

        return contacts;
    }

    //get all the contacts
    public List<Contacts> getallcontacts(){
        List<Contacts> contactsList=new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();
        String selectall=" SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor=db.rawQuery(selectall,null);

        if(cursor.moveToFirst()){
            do{
                Contacts contacts=new Contacts();
                contacts.setId(Integer.parseInt(cursor.getString(0)));
                contacts.setName(cursor.getString(1));
                contacts.setPhone_number(cursor.getString(2));
                contactsList.add(contacts); //adding contact to the list
            }while (cursor.moveToNext());

        }
        return contactsList;

    }
    public int updatecontact(Contacts contacts){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Util.KEY_NAME,contacts.getName());
        values.put(Util.KEY_PHONE_NUMBER,contacts.getPhone_number());

        return db.update(Util.TABLE_NAME,values,Util.KEY_ID+ "=?", new String[]{String.valueOf(contacts.getId())});

    }
    public void deletecontact(Contacts contacts){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Util.TABLE_NAME,Util.KEY_ID+"=?", new String[]{String.valueOf(contacts.getId())});
        db.close();
    }

    public int getcount(){

        SQLiteDatabase db=this.getReadableDatabase();

        String count=" SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor=db.rawQuery(count,null);

        return cursor.getCount();
    }
}
