package com.example.fabio.appstandcarrosv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected AdaptarBasededados a;
    protected String Marca, Modelo;
    protected Button btn_addcarro, btn_addCarroOnline;
    protected TextView total_stock, marca, modelo;
    List<String> Carros;
    List<String> osCarros;
    protected ListView lv;
    protected ArrayAdapter<String> listAdapter ;

    private void executarOutraActivity(Class<?> subActividade, ArrayList<String> val) {
        Intent x = new Intent(this, subActividade);
        x.putExtra("osCarros", val);
        startActivity(x);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "MainActivity onStart()", Toast.LENGTH_SHORT).show();
        a = new AdaptarBasededados(this).open();
        //mostra total carros
        long tot_stock = a.getTotalCarros();
        total_stock = (TextView)findViewById(R.id.total_stock);
        total_stock.setText(Long.toString(tot_stock));

        Carros = a.obterTodosCarros();
        //adicionar ao listview
        lv=(ListView)findViewById(R.id.listView);
        ArrayList<String> osCar = new ArrayList<String>();
        osCar.addAll( Carros );
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Carros);
        lv.setAdapter(listAdapter);

    }
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "MainActivity onPause()", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "MainActivity onStop()", Toast.LENGTH_SHORT).show();
        a.close();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        marca = (TextView)findViewById(R.id.vtxt_marca);
        modelo = (TextView)findViewById(R.id.vtxt_modelo);
        btn_addcarro = (Button)findViewById(R.id.add_carro);
        btn_addCarroOnline = (Button)findViewById(R.id.btn_addOnline);
        lv=(ListView)findViewById(R.id.listView);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Marca = a.retornaMarca(position);
                Modelo = a.retornaModelo(position);
                marca.setText(Marca);
                modelo.setText(Modelo);

                osCarros = a.obterTodosC(position);
                executarOutraActivity(ver_carro.class, (ArrayList)osCarros );
            }
        });

        btn_addcarro.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarOutraActivity(adicionar_carros.class, null);
            }
        });
        btn_addCarroOnline.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarOutraActivity(list_item.class, null);
            }
        });
    }

}
