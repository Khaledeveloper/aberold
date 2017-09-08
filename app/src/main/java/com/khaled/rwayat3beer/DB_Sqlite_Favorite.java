package com.khaled.rwayat3beer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by khaled on 12/08/2017.
 */

public class DB_Sqlite_Favorite extends SQLiteOpenHelper {
    public static final String BNname = "Favorite.db";
    public  DB_Sqlite_Favorite(Context context) {
        super(context, BNname, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table favorite (id INTEGER PRIMARY KEY  AUTOINCREMENT, Title TEXT, page_id IMTEGER)");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS favorite");

        onCreate(db);
    }

    public boolean Insert_to_favorite(String Title, int page_id) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Title",Title);

        contentValues.put("page_id", page_id);

        long result = db.insert("favorite",null,contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

    public ArrayList get_All_Favorite() {//for main activity

        ArrayList<List_itme_Index> arraylist = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor rs = db.rawQuery("select * from favorite", null);

        rs.moveToFirst();

        while (!rs.isAfterLast()) {

            int id = rs.getInt(rs.getColumnIndex("id"));

            String Title = rs.getString(rs.getColumnIndex("Title"));

            int page_id = rs.getInt(rs.getColumnIndex("page_id"));

            arraylist.add(new List_itme_Index(id,Title,page_id));

            rs.moveToNext();

        }

        return arraylist;

    }

   /* public ArrayList get_All_Favorite(){
        ArrayList<List_itme_Index> arrayList= new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor rs = db.rawQuery("select* from favorite", null);
        if (rs!=null) {

            rs.moveToFirst();
        }
        if (rs!=null)

        while (!rs.isAfterLast()){
            int id = rs.getInt(rs.getColumnIndex("id"));

            String Title = rs.getString(rs.getColumnIndex("Title"));

            int page_id = rs.getInt(rs.getColumnIndex("page_id "));

            arrayList.add(new List_itme_Index(id,Title,page_id));

            rs.moveToNext();


        }
        if (rs!=null)
        rs.close();

        return  arrayList;
    }*/

    public int get_check_List_Favorite(String Title) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor rs = db.rawQuery("select * from favorite Where Title Like'" + Title + "'", null);

       int count = rs.getCount();

        rs.close();




       return count;

    }

    public Integer Delete_row(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("favorite", "id = ?", new String[]{id});

    }

}
