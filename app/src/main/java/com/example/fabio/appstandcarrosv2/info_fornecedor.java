package com.example.fabio.appstandcarrosv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class info_fornecedor extends AppCompatActivity {

    protected Integer pos;
    protected AdaptarBasededados a;
    protected EditText nome, numerotlm, morada, descricao;
    protected Button btn_deletef, btn_guardar;
    protected ArrayList<String> osFornec;

    protected TextView teste;

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
        setContentView(R.layout.activity_info_fornecedor);

        nome = (EditText)findViewById(R.id.Ntxt_nome);
        numerotlm = (EditText)findViewById(R.id.Ntxt_numerotlm);
        morada = (EditText)findViewById(R.id.Ntxt_morada);
        descricao = (EditText)findViewById(R.id.Ntxt_descricao);
        btn_deletef = (Button)findViewById(R.id.btn_deletef);
        btn_guardar = (Button)findViewById(R.id.btn_guardar);
        teste = (TextView)findViewById(R.id.textView6);

        Intent oIntent = getIntent();
        osFornec = oIntent.getStringArrayListExtra("osFornec");
        nome.append(osFornec.get(0));
        numerotlm.append(osFornec.get(1));
        morada.append(osFornec.get(2));
        descricao.append(osFornec.get(3));

        btn_guardar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = getIntent();
                pos = getIntent().getExtras().getInt("posicao");
                //String fName = intent.getStringExtra("posicao");
                pos ++;
                a.updateF(pos, nome.getText().toString(), numerotlm.getText().toString() , morada.getText().toString(), descricao.getText().toString());
                Toast.makeText(getApplicationContext() , "Valores Alterados", Toast.LENGTH_SHORT).show();
                pos = null;
                finish();
            }
        });
        btn_deletef.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                pos = getIntent().getExtras().getInt("posicao");
                pos ++;
                a.deleteF(pos);
                Toast.makeText(getApplicationContext() , "Fornecedor Apagado", Toast.LENGTH_SHORT).show();
                pos = null;
                finish();
            }
        });

    }
}
