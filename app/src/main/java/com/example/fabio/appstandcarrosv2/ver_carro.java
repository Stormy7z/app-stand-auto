package com.example.fabio.appstandcarrosv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ver_carro extends AppCompatActivity {
    protected AdaptarBasededados a;
    protected TextView marca, modelo, matricula, cilindrada, ano, combustivel, preco, kilometros, quantidade_donos;
    protected ArrayList<String> osCarros;

    private void executarOutraActivity(Class<?> subActividade) {
        Intent x = new Intent(this, subActividade);
        startActivity(x);
    }
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

        marca = (TextView)findViewById(R.id.Ntxt_marca);
        modelo = (TextView)findViewById(R.id.Ntxt_modelo);
        matricula = (TextView)findViewById(R.id.Ntxt_matricula);
        cilindrada = (TextView)findViewById(R.id.Ntxt_cilindrada);
        ano = (TextView)findViewById(R.id.Ntxt_ano);
        combustivel = (TextView)findViewById(R.id.Ntxt_combustivel);
        preco = (TextView)findViewById(R.id.Ntxt_preco);
        kilometros = (TextView)findViewById(R.id.Ntxt_kilometros);
        quantidade_donos = (TextView)findViewById(R.id.Ntxt_quantidade_donos);

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
        quantidade_donos.append(osCarros.get(8));
    }
}
