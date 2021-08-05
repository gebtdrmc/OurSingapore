package sg.edu.rp.c346.id20047223.oursingapore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {

        db.execSQL("ALTER TABLE " + TABLE_ISLAND + " ADD COLUMN  module_name TEXT ");
    }


    private static final String DATABASE_NAME = "island.db";
    private static final String TABLE_ISLAND = "island";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ISLAND_NAME = "island_name";
    private static final String COLUMN_ISLAND_DESC = "description";
    private static final String COLUMN_ISLAND_AREA = "_area";
    private static final String COLUMN_RATING = "_rating";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_ISLAND + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ISLAND_NAME + " TEXT ," + COLUMN_ISLAND_DESC
                + "TEXT , " + COLUMN_ISLAND_AREA + " INTEGER ," + COLUMN_RATING + "INTEGER ) ";
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

        //Dummy records, to be inserted when the database is created
        for (int i = 0; i< 4; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ISLAND_NAME, "Data number " + i);
            db.insert(TABLE_ISLAND, null, values);
            values.put(COLUMN_ISLAND_DESC, "Data number " + i);
            db.insert(TABLE_ISLAND, null, values);
            values.put(String.valueOf(COLUMN_ISLAND_AREA), "Data number " + i);
            db.insert(TABLE_ISLAND, null, values);
            values.put(String.valueOf(COLUMN_RATING), "Data number " + i);
            db.insert(TABLE_ISLAND, null, values);
        }
        Log.i("info", "dummy records inserted");

    }

    public long insertIslandName(String islandName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ISLAND_NAME, islandName);
        long result = db.insert(TABLE_ISLAND, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }

    public long insertIslandDesc(String islandDesc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ISLAND_DESC, islandDesc);
        long result = db.insert(TABLE_ISLAND, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }
    public long insertIslandArea(int area) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ISLAND_AREA, area);
        long result = db.insert(TABLE_ISLAND, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }

    public long insertIslandRating(String islandRating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(String.valueOf(COLUMN_RATING), islandRating);
        long result = db.insert(TABLE_ISLAND, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }
    public ArrayList<Island> getAllIslands() {
        ArrayList<Island> islands = new ArrayList<Island>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_ISLAND_NAME + COLUMN_ISLAND_DESC + COLUMN_ISLAND_AREA + COLUMN_RATING
                + " FROM " + TABLE_ISLAND;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String islandName = cursor.getString(1);
                String islandDesc = cursor.getString(2);
                int islandArea = cursor.getInt(3);
                int rating = cursor.getInt(4);
                Island island = new Island(id, islandName, islandDesc, islandArea, rating);
                islands.add(island);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return islands;
    }
    public int updateIsland(Island data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ISLAND_NAME, data.getIslandName());
        values.put(COLUMN_ISLAND_DESC, data.getIslandDesc());
        values.put(String.valueOf(COLUMN_ISLAND_AREA), data.getIslandArea());
        values.put(String.valueOf(COLUMN_RATING), data.getRating());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_ISLAND, values, condition, args);
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }

        db.close();
        return result;
    }
    public int deleteIsland(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_ISLAND, condition, args);
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }

        db.close();
        return result;
    }

    public ArrayList<Island> getAllSong(String keyword) {
        ArrayList<Island> islands = new ArrayList<Island>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_ISLAND_NAME, COLUMN_ISLAND_DESC, COLUMN_ISLAND_AREA, COLUMN_RATING};
        String condition = COLUMN_ISLAND_NAME + " Like ?";
        String[] args = { "%" +  keyword + "%"};
        Cursor cursor = db.query(TABLE_ISLAND, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String islandName = cursor.getString(1);
                String islandDesc = cursor.getString(2);
                int islandArea = cursor.getInt(3);
                int rating = cursor.getInt(4);

                Island island = new Island(id, islandName, islandDesc, islandArea, rating);
                islands.add(island);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return islands;
    }

    public ArrayList<Island> getSongByStars(){
        ArrayList<Island> islands = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] coloumns = {COLUMN_ID, COLUMN_ISLAND_NAME, COLUMN_ISLAND_DESC, COLUMN_ISLAND_AREA, COLUMN_RATING};
        String condition = COLUMN_RATING + " = 5";

        Cursor cursor;
        cursor = db.query(TABLE_ISLAND, coloumns, condition, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String islandName = cursor.getString(1);
                String islandDesc = cursor.getString(2);
                int islandArea = cursor.getInt(3);
                int rating = cursor.getInt(4);

                Island island = new Island(id, islandName, islandDesc, islandArea, rating);
                islands.add(island);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return islands;
    }
}
