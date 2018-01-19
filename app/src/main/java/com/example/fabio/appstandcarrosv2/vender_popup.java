package com.example.fabio.appstandcarrosv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class vender_popup extends AppCompatActivity {
    protected AdaptarBasededados a;

    protected Button btn_venda;
    protected Spinner spinner_mes;
    protected EditText etxt_ano;
    protected TextView txt_matricula, text_view;
    protected String matricula;
    protected Double preco;
    protected final String[] meses = {"", "Janeiro","Fevereiro", "Março","Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
            "Outubro", "Novembro", "Dezembro"};

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
        setContentView(R.layout.activity_vender_popup);

        //Popup options
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width *.8), (int)(height*.6));

        btn_venda = (Button) findViewById(R.id.btn_venda);
        etxt_ano = (EditText) findViewById(R.id.etxt_ano);
        spinner_mes = (Spinner) findViewById(R.id.spinner_mes);
        txt_matricula = (TextView) findViewById(R.id.txt_matricula);
        text_view = (TextView) findViewById(R.id.text_view);

        try {
            //get matricula preco
            matricula = getIntent().getExtras().getString("Matricula");
            preco = getIntent().getExtras().getDouble("Preco");
            txt_matricula.append(matricula);
            //spinnerMes
            ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, meses);
            adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_mes.setAdapter(adp);
        }catch(NullPointerException e){}catch(NumberFormatException e){}catch(IndexOutOfBoundsException e){}

        //marcar carro vendido (retira do stocck e adiciona a tabela das vendas)
        btn_venda.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try{
                    if(etxt_ano.getText() != null && spinner_mes.getSelectedItem() != ""){
                        a.insertVenda(matricula,preco, spinner_mes.getSelectedItemPosition(),Integer.parseInt(etxt_ano.getText().toString()));
                        //get pos carro na bd
                        Intent oIntent = getIntent();
                        int pos = oIntent.getIntExtra("carroID", 0);
                        a.deleteC(pos);
                        Toast.makeText(getApplicationContext() , "Carro Vendido", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext() , "Informação errada", Toast.LENGTH_SHORT).show();}
                }catch(NullPointerException e){}catch(NumberFormatException e){}catch(IndexOutOfBoundsException e){}
            }
        });
    }


}
