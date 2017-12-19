package com.example.fabio.appstandcarrosv2;

/**
 * Created by fabio on 24/10/2017.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AjudaBasededados extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "base-dados.db";
    private static final int VERSION = 1;
    public AjudaBasededados(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String s =
        "CREATE TABLE carros(_id integer primary key autoincrement, marca varchar(50), modelo varchar(50), matricula varchar(50), cilindrada int(30), ano int(30), combustivel varchar(50), preco double(50), kilometros double(50), quantidade_donos int(20))";
        db.execSQL(s);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS carros");
        onCreate(db);
    }
}
