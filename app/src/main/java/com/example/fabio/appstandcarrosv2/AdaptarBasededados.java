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
        colunas[8] = "preco_compra";
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
    public int updateC(String aMarca, String oModelo, String aMatricula, Integer aCilindrada, Integer oAno, String oCombustivel, Double oPreco, Double osKilometros, Double oPreco_compra) {
        String whereClause = "matricula = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new String(aMatricula);
        //whereArgs[0] = new Integer(oId).toString();
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
    public int deleteC(Integer oId) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new Integer(oId).toString();
        return database.delete("carros", whereClause, whereArgs);
    }
    //Fornecedores
    private Cursor TodosFornecedores() {
        String[] colunas = new String[4];
        colunas[0] = "nome";
        colunas[1] = "numero_tlm";
        colunas[2] = "morada";
        colunas[3] = "descricao";
        return database.query("fornecedores", colunas, null, null, null, null, "_id");
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
                fornec.add(curs.getString(0));
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
    private Cursor obterTodosFaturamento() {
        String[] colunas = new String[3];
        colunas[0] = "valor_stock";
        colunas[1] = "valor_vendas";
        colunas[2] = "saldo";
        return database.query("faturamento", colunas, null, null, null, null, "_id");
    }

    public long insertStock(Double oValor_stock) {
        ContentValues values = new ContentValues() ;
        values.put("valor_stock", oValor_stock);
        return database.insert("faturamento", null, values);
    }
    public long insertVenda(Double oValor_vendas) {
        ContentValues values = new ContentValues() ;
        values.put("valor_vendas", oValor_vendas);
        return database.insert("faturamento", null, values);
    }
    public double retornaVendas() {
        //String q = "SELECT SUM(valor_vendas) FROM faturamento";
        //Cursor mCursor = database.rawQuery(q, null);
        //Cursor c = database.rawQuery("SELECT SUM(valor_vendas) FROM faturamento",null);
        //if (c != null){
        //    c.moveToFirst();
        //}
        String query = "SUM(valor_vendas) ";
        String[] otherColumns = new String[]{ query};
        String otherResult = "";
        Cursor c = database.query("faturamento", otherColumns, null, null, null, null, null);
        c.moveToFirst();
        double res = c.getDouble(0);
        return res;
    }
    public List<String> obterTodosFatur(int pos) {
        ArrayList<String> faturamento = new ArrayList<String>();
        Cursor cursor = obterTodosFaturamento();

        cursor.moveToPosition(pos);
        faturamento.add(cursor.getString(0));
        faturamento.add(cursor.getString(1));
        faturamento.add(cursor.getString(2));
        cursor.close();
        return faturamento;
    }


}

