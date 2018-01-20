package com.example.fabio.appstandcarrosv2;

/**
 * Created by fabio on 24/10/2017.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AjudaBasededados extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "base-dados.db";
    private static final int VERSION = 13;
    public AjudaBasededados(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //carros
        String c =
        "CREATE TABLE carros(_id integer primary key autoincrement, marca varchar(50), modelo varchar(50), matricula varchar(50), cilindrada int(30), ano int(30), combustivel varchar(50), preco double(200), kilometros double(50), preco_compra double(200))";
        db.execSQL(c);
        //fornecedores
        String f =
                "CREATE TABLE fornecedores(_id integer primary key autoincrement, nome varchar(50), numero_tlm varchar(50), morada varchar(50), descricao varchar(200))";
        db.execSQL(f);
        String s =
                "CREATE TABLE faturamento(_id integer primary key autoincrement, matricula varchar(50), valor_vendas double(200), mes int(50), ano int(50))";
        db.execSQL(s);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS carros");
        db.execSQL("DROP TABLE IF EXISTS fornecedores");
        db.execSQL("DROP TABLE IF EXISTS faturamento");
        onCreate(db);
    }
}
