package com.example.fabio.appstandcarrosv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class faturamento extends AppCompatActivity {

    protected TextView txt_valor_stock, txt_valor_vendas, txt_saldo;
    protected Button btn_1;
    protected AdaptarBasededados a;

    @Override
    protected void onStart() {
        super.onStart();
        a = new AdaptarBasededados(this).open();
    }
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        a.close();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faturamento);
        txt_valor_stock = (TextView)findViewById(R.id.txt_valor_stock);
        txt_valor_vendas = (TextView)findViewById(R.id.txt_valor_vendas);
        txt_saldo = (TextView)findViewById(R.id.txt_saldo);
        btn_1 = (Button)findViewById(R.id.btn_1);

        //Double fsdf = 567.57576;
        //txt_valor_stock.setText();
        btn_1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Double teste = a.retornaVendas();
                txt_valor_stock.setText("" + teste);
            }
        });




    }
}
