package com.example.a3020.mydict;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.LogPrinter;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private Context mcContext;
    public static final String DATABASE_NAME = "Database.db";
    public static final int DATABASE_VERSION = 1;
    private String DATABASE_LOCATION = "";
    private String DATABASE_FULLPATH = "";
    public SQLiteDatabase mDB;

    private final String EnToAf = "AfToAm";
    private final String history = "history";
    private final String bookmark = "bookmark";

    private final String COL_KEY = "key";
    private final String COL_VALUE = "value";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mcContext = context;
        DATABASE_LOCATION = "data/data/" + mcContext.getPackageName() + "/database/";
        DATABASE_FULLPATH = DATABASE_LOCATION + DATABASE_NAME;

        if (!isExistingDB()) {

            try
            {
                File dbLocation = new File(DATABASE_LOCATION);
                dbLocation.mkdir();
                copyDataBase(DATABASE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        mDB = SQLiteDatabase.openOrCreateDatabase(DATABASE_FULLPATH, null);

    }


    boolean isExistingDB() {

        File file = new File(DATABASE_FULLPATH);
        return file.exists();

    }

    //copy database
    public void copyDataBase(String fileName) throws IOException {
        int mLength;
        InputStream sourceDatabase = this.mcContext.getAssets().open(fileName);
        File destinationPath = new File(DATABASE_FULLPATH);
        OutputStream mOutput = new FileOutputStream(destinationPath);

        byte[] mBuffer = new byte[1024];
        while ((mLength = sourceDatabase.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        sourceDatabase.close();
        mOutput.flush();
        mOutput.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // LIst of word from db
    public ArrayList<Word> getWord() {

        String q = "SELECT * FROM " + EnToAf;

        Cursor result = mDB.rawQuery(q, null);
        ArrayList<Word> source = new ArrayList<Word>();
        while (result.moveToNext()) {
            Word word = new Word(result.getString(result.getColumnIndex(COL_KEY)) , result.getString(result.getColumnIndex(COL_VALUE)));
            source.add(word);

        }

        return source;

    }


    // All about book mark Not Done !!!!!!!!!!!!!!!!!!!!!!!!
    public ArrayList<Word> getAllWordFromBookMark() {

        ArrayList<Word> source = new ArrayList<Word>();
        String query = "select * from  bookmark ";
        Cursor result = mDB.rawQuery(query,null);
        while (result.moveToNext()) {

            Word word = new Word(result.getString(result.getColumnIndex(COL_KEY)) , result.getString(result.getColumnIndex(COL_VALUE)));
            source.add(word);
        }


        return source;

    }
    // Inserting to bookmark
    public void addBookmark(Word word)
    {
        try
        {
            String query = "INSERT INTO  bookmark(["+COL_KEY+"],["+COL_VALUE+"]) VALUES (?,?); ";
            mDB.execSQL(query,new Object[]{word.key,word.value});

        }
        catch (SQLException sq)
        {

        }
    }

    public void removeBookmark(Word word)
    {
        try
        {
            String query = "DELETE FROM  bookmark WHERE ["+COL_KEY+"]=? AND ["+COL_VALUE+"]=? ; ";
            mDB.execSQL(query,new Object[]{word.key,word.value});

        }
        catch (SQLException sq)
        {


        }


    }
    public void clearBookmark()
    {
        try
        {
            String query = "DELETE FROM  bookmark ";
            mDB.execSQL(query);

        }
        catch (SQLException sq)
        {


        }


    }

    public boolean isWordMark(Word word)
    {
        String query = "select * from  bookmark where [key] =  ? and value= ? ";
        Cursor result = mDB.rawQuery(query,new String[]{word.key,word.value});

        return result.getCount()>0;
    }

    public Word getWordFormBookMark(String key)
    {
       Word word = null;
        String query = "select * from  bookmark where [key] =  (?) ";
        Cursor result = mDB.rawQuery(query,new String[]{key});
        while (result.moveToNext()) {
            word = new Word();
            word.key = result.getString(result.getColumnIndex(COL_KEY));
            word.value = result.getString(result.getColumnIndex(COL_VALUE));
        }

           return word;
    }


    public Word getWord(String key) {

        String q = "SELECT * FROM " +EnToAf + " WHERE [key] =  (?) ";
        Cursor result = mDB.rawQuery(q, new String[]{key});
        Word word = new Word();
        while (result.moveToNext()) {
            word.key = result.getString(result.getColumnIndex(COL_KEY));
            word.value = result.getString(result.getColumnIndex(COL_VALUE));

        }


        return word;


    }

    public ArrayList<String> getWordFromBookMark(String key) {

        String q = "select * from bookmark order by [date] desc";
        Cursor result = mDB.rawQuery(q, new String[]{key});
        ArrayList<String> source = new ArrayList<String>();
        while (result.moveToNext()) {
            source.add(result.getString(result.getColumnIndex(key)));

        }

        return source;


    }

    // Inserting to history
    public void addHistory(Word word)
    {
        try
        {
            String query = "INSERT INTO  history(["+COL_KEY+"],["+COL_VALUE+"]) VALUES (?,?); ";
            mDB.execSQL(query,new Object[]{word.key,word.value});

        }
        catch (SQLException sq)
        {

        }
    }

    // LIst of word from history table
    public ArrayList<Word> getHistoryWord() {

        String q = "SELECT * FROM " + history;

        Cursor result = mDB.rawQuery(q, null);
        ArrayList<Word> source = new ArrayList<Word>();
        while (result.moveToNext()) {
            Word word = new Word(result.getString(result.getColumnIndex(COL_KEY)) , result.getString(result.getColumnIndex(COL_VALUE)));
            source.add(word);

        }

        return source;

    }

    //clear history
    public void clearHistory()
    {
        try
        {
            String query = "DELETE FROM  history ";
            mDB.execSQL(query);

        }
        catch (SQLException sq)
        {


        }


    }

}
