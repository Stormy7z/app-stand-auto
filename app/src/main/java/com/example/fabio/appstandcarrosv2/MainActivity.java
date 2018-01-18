package com.example.fabio.appstandcarrosv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    protected Button btn_stock, btn_addCarroOnline, btn_fornecedor, btn_faturamento;

    private void executarOutraActivity(Class<?> subActividade) {
        Intent x = new Intent(this, subActividade);
        startActivity(x);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_stock = (Button)findViewById(R.id.btn_stock);
        btn_addCarroOnline = (Button)findViewById(R.id.btn_addOnline);
        btn_fornecedor = (Button)findViewById(R.id.btn_fornecedor);
        btn_faturamento = (Button)findViewById(R.id.btn_faturamento);

        btn_stock.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarOutraActivity(stock_carro.class);
            }
        });
        btn_addCarroOnline.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarOutraActivity(list_item.class);
            }
        });
        btn_fornecedor.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarOutraActivity(vista_fornecedor.class);
            }
        });
        btn_faturamento.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarOutraActivity(faturamento.class);
            }
        });

    }

}
