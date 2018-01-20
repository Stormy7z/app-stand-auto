package com.example.fabio.appstandcarrosv2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class info_fornecedor extends AppCompatActivity {

    protected Integer pos;
    protected AdaptarBasededados a;
    protected EditText nome, numerotlm, morada, descricao;
    protected Button btn_deletef, btn_guardar;
    protected ArrayList<String> osFornec;

    @Override
    protected void onStart() {
        super.onStart();
        try {
            a = new AdaptarBasededados(this).open();
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
        setContentView(R.layout.activity_info_fornecedor);

        nome = (EditText)findViewById(R.id.Ntxt_nome);
        numerotlm = (EditText)findViewById(R.id.Ntxt_numerotlm);
        morada = (EditText)findViewById(R.id.Ntxt_morada);
        descricao = (EditText)findViewById(R.id.Ntxt_descricao);
        btn_deletef = (Button)findViewById(R.id.btn_deletef);
        btn_guardar = (Button)findViewById(R.id.btn_guardar);

        Intent oIntent = getIntent();
        osFornec = oIntent.getStringArrayListExtra("osFornec");
        nome.append(osFornec.get(1));
        numerotlm.append(osFornec.get(2));
        morada.append(osFornec.get(3));
        descricao.append(osFornec.get(4));

        btn_guardar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //update dados fornecedor
                try {
                    pos = Integer.parseInt(osFornec.get(0));
                    a.updateF(pos, nome.getText().toString(), numerotlm.getText().toString(), morada.getText().toString(), descricao.getText().toString());
                    Toast.makeText(getApplicationContext(), "Fornecedor Atualizado", Toast.LENGTH_SHORT).show();
                    finish();
                }catch(NullPointerException e){
                    Toast.makeText(getApplicationContext() , "Valores Errados", Toast.LENGTH_SHORT).show();}
                catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext() , "Escrever Formato Correto", Toast.LENGTH_SHORT).show();
                }catch(IndexOutOfBoundsException e){
                    Toast.makeText(getApplicationContext() , "Número de Carateres excedido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_deletef.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //apagar fornecedor
                try {
                    pos = Integer.parseInt(osFornec.get(0));
                    a.deleteF(pos);
                    Toast.makeText(getApplicationContext(), "Fornecedor Apagado", Toast.LENGTH_SHORT).show();
                    finish();
                }catch(NullPointerException e){
                    Toast.makeText(getApplicationContext() , "Valores Errados", Toast.LENGTH_SHORT).show();}
                catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext() , "Escrever Formato Correto", Toast.LENGTH_SHORT).show();
                }catch(IndexOutOfBoundsException e){
                    Toast.makeText(getApplicationContext() , "Número de Carateres excedido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        morada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String aMor = morada.getText().toString();
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:?q= " + aMor));
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(intent);
                }catch(NullPointerException e){}catch(NumberFormatException e){}catch(IndexOutOfBoundsException e){}
            }
        });

    }
}
