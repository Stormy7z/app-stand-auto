package com.example.fabio.appstandcarrosv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ver_carro extends AppCompatActivity {
    protected AdaptarBasededados a;
    protected EditText marca, modelo, matricula, cilindrada, ano, combustivel, preco, kilometros, preco_compra;
    protected ArrayList<String> osCarros;
    protected Button btn_vender, btn_delete, btn_guardar;
    protected Integer pos;
    protected Long id;

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "MainActivity onStart()", Toast.LENGTH_SHORT).show();
        a = new AdaptarBasededados(this).open();
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
        setContentView(R.layout.activity_ver_carro);

        marca = (EditText)findViewById(R.id.Ntxt_marca);
        modelo = (EditText)findViewById(R.id.Ntxt_modelo);
        matricula = (EditText)findViewById(R.id.Ntxt_matricula);
        cilindrada = (EditText)findViewById(R.id.Ntxt_cilindrada);
        ano = (EditText)findViewById(R.id.Ntxt_ano);
        combustivel = (EditText)findViewById(R.id.Ntxt_combustivel);
        preco = (EditText)findViewById(R.id.Ntxt_preco);
        kilometros = (EditText)findViewById(R.id.Ntxt_kilometros);
        preco_compra = (EditText) findViewById(R.id.Ntxt_preco_compra);
        btn_vender = (Button) findViewById(R.id.btn_vender);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);

        Intent oIntent = getIntent();
        osCarros = oIntent.getStringArrayListExtra("osCarros");
        marca.append(osCarros.get(0));
        modelo.append(osCarros.get(1));
        matricula.append(osCarros.get(2));
        cilindrada.append(osCarros.get(3));
        ano.append(osCarros.get(4));
        combustivel.append(osCarros.get(5));
        preco.append(osCarros.get(6));
        kilometros.append(osCarros.get(7));
        preco_compra.append(osCarros.get(8));

        btn_guardar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = getIntent();
                a.updateC(marca.getText().toString(), modelo.getText().toString(), matricula.getText().toString(),
                        Integer.parseInt(cilindrada.getText().toString()), Integer.parseInt(ano.getText().toString()),
                        combustivel.getText().toString(), Double.parseDouble(preco.getText().toString()),
                        Double.parseDouble(kilometros.getText().toString()), Double.parseDouble(preco_compra.getText().toString()));
                Toast.makeText(getApplicationContext() , "Valores Alterados", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btn_delete.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                pos = getIntent().getExtras().getInt("posicao");
                pos ++;
                a.deleteC(pos);
                Toast.makeText(getApplicationContext() , "Carro Apagado do Stock", Toast.LENGTH_SHORT).show();
                pos = null;
                finish();
            }
        });
        btn_vender.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                a.insertVenda(Double.parseDouble(preco.getText().toString()));
                Toast.makeText(getApplicationContext() , "Carro Vendido", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
