package com.example.fabio.appstandcarrosv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adicionar_carros extends AppCompatActivity {
    protected Button btn_guarda;
    protected EditText txt_marca, txt_modelo, txt_matricula, txt_cilindrada, txt_ano, txt_combustivel, txt_preco, txt_kilometros, txt_quantidade_donos;
    protected AdaptarBasededados a;
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
        setContentView(R.layout.activity_adicionar_carros);

        btn_guarda = (Button)findViewById(R.id.btn_guarda);
        txt_marca = (EditText)findViewById(R.id.txt_marca);
        txt_modelo = (EditText)findViewById(R.id.txt_modelo);
        txt_matricula = (EditText)findViewById(R.id.txt_matricula);
        txt_cilindrada = (EditText)findViewById(R.id.txt_cilindrada);
        txt_ano = (EditText)findViewById(R.id.txt_ano);
        txt_combustivel = (EditText)findViewById(R.id.txt_combustivel);
        txt_preco = (EditText)findViewById(R.id.txt_preco);
        txt_kilometros = (EditText)findViewById(R.id.txt_kilometros);
        txt_quantidade_donos = (EditText)findViewById(R.id.txt_quantidade_donos);


        btn_guarda.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                long rowInserted = a.insertCarro(txt_marca.getText().toString(), txt_modelo.getText().toString(), txt_matricula.getText().toString(),
                        Integer.parseInt(txt_cilindrada.getText().toString()), Integer.parseInt(txt_ano.getText().toString()), txt_combustivel.getText().toString(), Double.parseDouble(txt_preco.getText().toString()),
                        Double.parseDouble(txt_kilometros.getText().toString()), Integer.parseInt(txt_quantidade_donos.getText().toString()));
                if(rowInserted != -1)
                    Toast.makeText(getApplicationContext() , "Carro adicionado, linha id: " + rowInserted, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext() , "Erro", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
