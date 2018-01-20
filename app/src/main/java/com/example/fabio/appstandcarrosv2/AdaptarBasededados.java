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
        String[] colunas = new String[10];
        colunas[0] = "_id";
        colunas[1] = "marca";
        colunas[2] = "modelo";
        colunas[3] = "matricula";
        colunas[4] = "cilindrada";
        colunas[5] = "ano";
        colunas[6] = "combustivel";
        colunas[7] = "preco";
        colunas[8] = "kilometros";
        colunas[9] = "preco_compra";
        return database.query("carros", colunas, null, null, null, null, "_id");
    }

    public long insertCarro(String aMarca, String oModelo, String aMatricula, Integer aCilindrada, Integer oAno, String oCombustivel, Double oPreco, Double osKilometros, Double oPreco_compra) {
        ContentValues values = new ContentValues() ;
        values.put("marca", aMarca);
        values.put("modelo", oModelo);
        values.put("matricula", aMatricula);
        values.put("cilindrada", aCilindrada);
        values.put("ano", oAno);
        values.put("combustivel", oCombustivel);
        values.put("preco", oPreco);
        values.put("kilometros", osKilometros);
        values.put("preco_compra",oPreco_compra);
        return database.insert("carros", null, values);
    }
    public List<String> obterTodosCarros() {
        ArrayList<String> carros = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                carros.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return carros;
    }
    public List<String> obterTodosModelos() {
        ArrayList<String> modelos = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                modelos.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return modelos;
    }
    public List<String> obterTodasMatriculas() {
        ArrayList<String> matriculas = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                matriculas.add(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return matriculas;
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
        carros.add(cursor.getString(9));
        cursor.close();
        return carros;
    }
    public long getTotalCarros() {
        long cnt  = DatabaseUtils.queryNumEntries(database, "carros");
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
    public int updateC(Integer oId, String aMarca, String oModelo, String aMatricula, Integer aCilindrada, Integer oAno, String oCombustivel, Double oPreco, Double osKilometros, Double oPreco_compra) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new Integer(oId).toString();
        ContentValues values = new ContentValues();
        values.put("marca", aMarca);
        values.put("modelo", oModelo);
        values.put("matricula", aMatricula);
        values.put("cilindrada", aCilindrada);
        values.put("ano", oAno);
        values.put("combustivel", oCombustivel);
        values.put("preco", oPreco);
        values.put("kilometros", osKilometros);
        values.put("preco_compra", oPreco_compra);
        return database.update("carros", values, whereClause, whereArgs);
    }
    public int deleteC(Integer oID) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new Integer(oID).toString();
        return database.delete("carros", whereClause, whereArgs);
    }
    //Fornecedores
    private Cursor TodosFornecedores() {
        String[] colunas = new String[5];
        colunas[0] = "_id";
        colunas[1] = "nome";
        colunas[2] = "numero_tlm";
        colunas[3] = "morada";
        colunas[4] = "descricao";
        return database.query("fornecedores", colunas, null, null, null, null, "nome");
    }
    public long insertFornecedor(String oNome, String oNumero_tlm, String aMorada, String aDescricao) {
        ContentValues values = new ContentValues() ;
        values.put("nome", oNome);
        values.put("numero_tlm", oNumero_tlm);
        values.put("morada", aMorada);
        values.put("descricao", aDescricao);
        return database.insert("fornecedores", null, values);
    }
    public List<String> obterTodosFornecedores() {
        ArrayList<String> fornec = new ArrayList<String>();
        Cursor curs = TodosFornecedores();
        if (curs.moveToFirst()) {
            do {
                fornec.add(curs.getString(1));
            } while (curs.moveToNext());
        }
        curs.close();
        return fornec;
    }
    public List<String> obterTodosF(int pos) {
        ArrayList<String> fornec = new ArrayList<String>();
        Cursor cursor = TodosFornecedores();
        cursor.moveToPosition(pos);
        fornec.add(cursor.getString(0));
        fornec.add(cursor.getString(1));
        fornec.add(cursor.getString(2));
        fornec.add(cursor.getString(3));
        fornec.add(cursor.getString(4));
        cursor.close();
        return fornec;
    }
    public int updateF(Integer oId, String oNome, String aNumero, String aMorada, String aDesccricao) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new Integer(oId).toString();
        ContentValues values = new ContentValues();
        values.put("nome", oNome);
        values.put("numero_tlm", aNumero);
        values.put("morada", aMorada);
        values.put("descricao", aDesccricao);
        return database.update("fornecedores", values, whereClause, whereArgs);
    }
    public int deleteF(Integer oId) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new Integer(oId).toString();
        return database.delete("fornecedores", whereClause, whereArgs);
    }

    //Faturamento
    public long insertVenda(String aMatricula, Double oValor_vendas, Integer oMes, Integer oAno) {
        ContentValues values = new ContentValues() ;
        values.put("matricula", aMatricula);
        values.put("valor_vendas", oValor_vendas);
        values.put("mes", oMes);
        values.put("ano", oAno);
        return database.insert("faturamento", null, values);
    }

    public double retornaVendas() {
        String query = "SUM(valor_vendas) ";
        String[] otherColumns = new String[]{ query};
        Cursor c = database.query("faturamento", otherColumns, null, null, null, null, null);
        c.moveToFirst();
        double res = c.getDouble(0);
        return res;
    }
    public double retornaStock() {
        String query = "SUM(preco) ";
        String[] otherColumns = new String[]{ query};
        Cursor c = database.query("carros", otherColumns, null, null, null, null, null);
        c.moveToFirst();
        double res = c.getDouble(0);
        return res;
    }
    public double retornaVendaAnoMes(int oAno, int oMes) {
        String query = "SELECT SUM(valor_vendas) FROM faturamento WHERE ano = "+oAno+" and mes = "+oMes;
        Cursor c = database.rawQuery(query, new String[]{});
        c.moveToFirst();
        double res = c.getDouble(0);
        return res;
    }
    public double retornaInvestimentoStock() {
        String query = "SUM(preco_compra) ";
        String[] otherColumns = new String[]{ query};
        Cursor c = database.query("carros", otherColumns, null, null, null, null, null);
        c.moveToFirst();
        double res = c.getDouble(0);
        return res;
    }
}

