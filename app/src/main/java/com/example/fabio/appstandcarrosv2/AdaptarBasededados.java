package com.example.fabio.appstandcarrosv2;

/**
 * Created by fabio on 24/10/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;


public class AdaptarBasededados {
    private AjudaBasededados dbHelper;
    private SQLiteDatabase database;
    protected String nome;
    protected String morada;
    protected String telefone;

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
    public long insertNumeroNome(String aMarca, String oModelo, String aMatricula, Integer aCilindrada, Integer oAno, String oCombustivel, Double oPreco, Double osKilometros, Integer aQuantidade_donos ) {
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
    public List<String> obterTodosValores() {
        ArrayList<String> nomes = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                nomes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return nomes;
    }
    public String retornaNome(int n){
        ArrayList<String> nomes = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                nomes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        nome = nomes.get(n);
        return nome;
    }
    public String retornaMorada(int n){
        ArrayList<String> moradas = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                moradas.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        morada = moradas.get(n);
        return morada;
    }
    public String retornaTelefone(int n){
        ArrayList<String> telefones = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                telefones.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        telefone = telefones.get(n);
        return telefone;
    }
}

