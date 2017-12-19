package com.example.fabio.appstandcarrosv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class adicionar_carros extends AppCompatActivity {
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


        btn_insert.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                long rowInserted = a.insertNumeroNome(nome.getText().toString(), morada.getText().toString(), telefone.getText().toString());
                if(rowInserted != -1)
                    Toast.makeText(getApplicationContext() , "Novos valores adicionados, linha id: " + rowInserted, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext() , "Erro", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
