package com.example.a3020.mydict;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private Context mcContext;
    public static final String DATABASE_NAME = "MyDictDb.db";
    public static final int DATABASE_VERSION = 1;
    private String DATABASE_LOCATION = "";
    private String DATABASE_FULLPATH = "";
    public SQLiteDatabase mDB;

    private final String SoToAm = "SoToAm";
    private final String AmToSo = "AmToSo";
    private final String AmToAm = "AmToAm";
    private final String bookmark = "bookmark";

    private final String key = "key";
    private final String value = "value";

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
    public ArrayList<String> getWord(int dicType) {
        String tableName = getTableName(dicType);

        String q = "select * from" + tableName;
        Cursor result = mDB.rawQuery(q, null);
        ArrayList<String> source = new ArrayList<String>();
        while (result.moveToNext()) {
            source.add(result.getString(result.getColumnIndex(key)));

        }

        return source;

    }


    // All about book mark Not Done !!!!!!!!!!!!!!!!!!!!!!!!
    public ArrayList<String> getAllWordFromBookMark(String key) {

        ArrayList<String> source = new ArrayList<String>();


        return source;

    }

    public void addBookmark() {


    }

    public void removeBookmark() {


    }

    public boolean isWordMark(Word word) {

        return true;
    }

    public Word getWordFormBookMark(String key) {
        Word word = new Word();

        return word;
    }


    public Word getWord(String key, int dicType) {
        String tableName = getTableName(dicType);

        String q = "select * from " + tableName + "where [key] =  (?) ";
        Cursor result = mDB.rawQuery(q, new String[]{key});
        Word word = new Word();
        while (result.moveToNext()) {
            word.key = result.getString(result.getColumnIndex(this.key));
            word.value = result.getString(result.getColumnIndex(value));
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


    public String getTableName(int dictType) {
        String tableName = "";
        if (dictType == R.id.action_entk) {
            tableName = AmToSo;

        } else if (dictType == R.id.action_kten) {

            tableName = SoToAm;
        } else if (dictType == R.id.action_ktk) {
            tableName = AmToAm;

        }


        return tableName;
    }
}
