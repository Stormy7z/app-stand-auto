package com.example.fabio.appstandcarrosv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class stock_carro extends AppCompatActivity {
    protected AdaptarBasededados a;
    protected String Marca, Modelo;
    protected Button btn_addcarro;
    protected TextView total_stock;
    protected List<String> Carros, osModelo, asMatriculas;
    protected List<String> osCarros;
    protected ListView lv;
    protected Integer posicao;
    protected ArrayList<HashMap<String, String>> osCar;

    private void executarOutraActivity(Class<?> subActividade, ArrayList<String> val, Integer pos) {
        Intent x = new Intent(this, subActividade);
        x.putExtra("osCarros", val);
        x.putExtra("posicao", pos);
        startActivity(x);
    }
    @Override
    protected void onStart() {
        super.onStart();
        try {
            a = new AdaptarBasededados(this).open();
            osCar = new ArrayList<>();
            //mostra total carros
            long tot_stock = a.getTotalCarros();
            total_stock = (TextView) findViewById(R.id.total_stock);
            lv = (ListView) findViewById(R.id.listView);
            total_stock.setText(Long.toString(tot_stock));
            //obter os val de cada linha
            Carros = a.obterTodosCarros();osModelo = a.obterTodosModelos();asMatriculas = a.obterTodasMatriculas();
            for (int i = 0; i < Carros.size(); i++){
                //adicionar items para por em cada linha do listview
                HashMap<String, String> infoCarros = new HashMap<>();
                    infoCarros.put("linha_marca", "Marca: " + Carros.get(i)+"   |   Modelo: "+osModelo.get(i));
                    infoCarros.put("linha_modelo", "Matrícula: "+asMatriculas.get(i));
                    osCar.add(infoCarros);
            }
            //adicionar ao listview
            ListAdapter adapter = new SimpleAdapter(this,
                    osCar, R.layout.activity_list_item, new String[]{"linha_marca", "linha_modelo"},
                    new int[]{R.id.txt1, R.id.txt2});
            lv.setAdapter(adapter);
        }catch(NullPointerException e){}catch(NumberFormatException e){}catch(IndexOutOfBoundsException e){}

    }
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        try {
            a.close();
        }catch(NullPointerException e){}catch(NumberFormatException e){}catch(IndexOutOfBoundsException e){}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_carro);
        btn_addcarro = (Button)findViewById(R.id.add_carro);
        lv=(ListView)findViewById(R.id.listView);

        //popular listview com carros
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Marca = a.retornaMarca(position);
                    Modelo = a.retornaModelo(position);
                    //set val to other activity
                    posicao = position;
                    osCarros = a.obterTodosC(position);
                    executarOutraActivity(ver_carro.class, (ArrayList) osCarros, posicao);
                }catch(NullPointerException e){}catch(NumberFormatException e){}catch(IndexOutOfBoundsException e){}
            }
        });
        //ir para adicionar carro
        btn_addcarro.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarOutraActivity(adicionar_carros.class, null, null);
            }
        });
    }
}
