package com.example.fabio.appstandcarrosv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ver_carro extends AppCompatActivity {
    protected AdaptarBasededados a;
    protected EditText marca, modelo, matricula, cilindrada, ano, combustivel, preco, kilometros, preco_compra;
    protected ArrayList<String> osCarros;
    protected Button btn_vender, btn_delete, btn_guardar;
    protected Integer pos, carroID;
    protected String Matricula;
    protected Double Preco;
    protected Long id;

    private void executarOutraActivity(Class<?> subActividade, Integer OcarroID,String aMatricula, Double oPreco) {
        Intent x = new Intent(this, subActividade);
        x.putExtra("carroID", OcarroID);
        x.putExtra("Matricula", aMatricula);
        x.putExtra("Preco", oPreco);
        startActivity(x);
    }
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
        try{
            a.close();
        }catch(NullPointerException e){}catch(NumberFormatException e){}catch(IndexOutOfBoundsException e){}

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
        marca.append(osCarros.get(1));
        modelo.append(osCarros.get(2));
        matricula.append(osCarros.get(3));
        cilindrada.append(osCarros.get(4));
        ano.append(osCarros.get(5));
        combustivel.append(osCarros.get(6));
        preco.append(osCarros.get(7));
        kilometros.append(osCarros.get(8));
        preco_compra.append(osCarros.get(9));
        //atualizar carro
        btn_guardar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try {
                    pos = Integer.parseInt(osCarros.get(0));
                    a.updateC(pos, marca.getText().toString(), modelo.getText().toString(), matricula.getText().toString(),
                            Integer.parseInt(cilindrada.getText().toString()), Integer.parseInt(ano.getText().toString()),
                            combustivel.getText().toString(), Double.parseDouble(preco.getText().toString()),
                            Double.parseDouble(kilometros.getText().toString()), Double.parseDouble(preco_compra.getText().toString()));
                    Toast.makeText(getApplicationContext(), "Carro Atualizado", Toast.LENGTH_SHORT).show();
                    finish();
                }catch(NullPointerException e){
                    Toast.makeText(getApplicationContext() , "Valores Errados", Toast.LENGTH_SHORT).show();}
                catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext() , "Escrever Formato no Respetivo Campo", Toast.LENGTH_SHORT).show();
                }catch(IndexOutOfBoundsException e){
                    Toast.makeText(getApplicationContext() , "Número de Carateres excedido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //apagar carro do stock
        btn_delete.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try {
                    pos = Integer.parseInt(osCarros.get(0));
                    a.deleteC(pos);
                    Toast.makeText(getApplicationContext(), "Carro Apagado do Stock", Toast.LENGTH_SHORT).show();
                    pos = null;
                    finish();
                }catch(NullPointerException e){}
            }
        });
        //vender carro
        btn_vender.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try {
                    carroID = Integer.parseInt(osCarros.get(0));
                    Matricula = matricula.getText().toString();
                    Preco = Double.parseDouble(preco.getText().toString());
                    executarOutraActivity(vender_popup.class, carroID, Matricula, Preco);
                }catch(NullPointerException e){}catch(NumberFormatException e){}catch(IndexOutOfBoundsException e){}
            }
        });
    }
}
