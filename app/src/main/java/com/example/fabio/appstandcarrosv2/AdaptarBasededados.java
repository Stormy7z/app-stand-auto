package com.example.fabio.appstandcarrosv2;

/**
 * Created by fabio on 24/10/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;


public class AdaptarBasededados {
    private AjudaBasededados dbHelper;
    private SQLiteDatabase database;
    protected String marca, modelo;

    public AdaptarBasededados(Context context) {
        dbHelper = new AjudaBasededados(context.getApplicationContext());
    }
    public AdaptarBasededados open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }
    private Cursor obterTodosRegistos() {
        String[] colunas = new String[9];
        colunas[0] = "marca";
        colunas[1] = "modelo";
        colunas[2] = "matricula";
        colunas[3] = "cilindrada";
        colunas[4] = "ano";
        colunas[5] = "combustivel";
        colunas[6] = "preco";
        colunas[7] = "kilometros";
        colunas[8] = "quantidade_donos";
        return database.query("carros", colunas, null, null, null, null, "marca");
    }
    public long insertCarro(String aMarca, String oModelo, String aMatricula, Integer aCilindrada, Integer oAno, String oCombustivel, Double oPreco, Double osKilometros, Integer aQuantidade_donos ) {
        ContentValues values = new ContentValues() ;
        values.put("marca", aMarca);
        values.put("modelo", oModelo);
        values.put("matricula", aMatricula);
        values.put("cilindrada", aCilindrada);
        values.put("ano", oAno);
        values.put("combustivel", oCombustivel);
        values.put("preco", oPreco);
        values.put("kilometros", osKilometros);
        values.put("quantidade_donos", aQuantidade_donos);
        return database.insert("carros", null, values);
    }
    public List<String> obterTodosCarros() {
        ArrayList<String> carros = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                carros.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return carros;
    }
    public List<String> obterTodosC(int pos) {
        ArrayList<String> carros = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();

        cursor.moveToPosition(pos);
        carros.add(cursor.getString(0));
        carros.add(cursor.getString(1));
        carros.add(cursor.getString(2));
        carros.add(cursor.getString(3));
        carros.add(cursor.getString(4));
        carros.add(cursor.getString(5));
        carros.add(cursor.getString(6));
        carros.add(cursor.getString(7));
        carros.add(cursor.getString(8));
        cursor.close();
        return carros;
    }
    public long getTotalCarros() {
        long cnt  = DatabaseUtils.queryNumEntries(database, "carros");
        //database.close();
        return cnt;
    }
    public String retornaMarca(int n){
        ArrayList<String> nomes = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                nomes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        marca = nomes.get(n);
        return marca;
    }
    public String retornaModelo(int n){
        ArrayList<String> nomes = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                nomes.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        modelo = nomes.get(n);
        return modelo;
    }
}

