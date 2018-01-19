package com.example.fabio.appstandcarrosv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adicionar_carros extends AppCompatActivity {
    protected Button btn_guarda;
    protected EditText txt_marca, txt_modelo, txt_matricula, txt_cilindrada, txt_ano, txt_combustivel, txt_preco, txt_kilometros, txt_preco_compra;
    protected AdaptarBasededados a;
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
        txt_preco_compra = (EditText)findViewById(R.id.txt_preco_compra);

        //guardar carro
        btn_guarda.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try {
                    long rowInserted = a.insertCarro(txt_marca.getText().toString(), txt_modelo.getText().toString(), txt_matricula.getText().toString(),
                            Integer.parseInt(txt_cilindrada.getText().toString()), Integer.parseInt(txt_ano.getText().toString()), txt_combustivel.getText().toString(), Double.parseDouble(txt_preco.getText().toString()),
                            Double.parseDouble(txt_kilometros.getText().toString()), Double.parseDouble(txt_preco_compra.getText().toString()));
                    if (rowInserted != -1)
                        Toast.makeText(getApplicationContext(), "Carro adicionado ao stock", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();

                    finish();
                }catch(NullPointerException e){
                    Toast.makeText(getApplicationContext() , "Valores Errados", Toast.LENGTH_SHORT).show();}
                catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext() , "Escrever formato correto nos campos indicados", Toast.LENGTH_SHORT).show();
                }catch(IndexOutOfBoundsException e){
                    Toast.makeText(getApplicationContext() , "Número de Carateres excedido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
