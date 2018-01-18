package com.example.fabio.appstandcarrosv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_fornecedor extends AppCompatActivity {

    protected Button btn_guarda;
    protected EditText txt_nome, txt_numerotlm, txt_morada, txt_descricao;
    protected AdaptarBasededados a;
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Add fornec onStart()", Toast.LENGTH_SHORT).show();
        a = new AdaptarBasededados(this).open();
    }
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Add fornec onPause()", Toast.LENGTH_SHORT).show();
    }
    @Override

    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Add fornec onStop()", Toast.LENGTH_SHORT).show();
        a.close();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fornecedor);

        btn_guarda = (Button)findViewById(R.id.btn_guarda);
        txt_nome = (EditText)findViewById(R.id.txt_nome);
        txt_numerotlm = (EditText)findViewById(R.id.txt_numerotlm);
        txt_morada = (EditText)findViewById(R.id.txt_morada);
        txt_descricao = (EditText)findViewById(R.id.txt_descricao);

        btn_guarda.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                long rowInserted = a.insertFornecedor(txt_nome.getText().toString(), txt_numerotlm.getText().toString(), txt_morada.getText().toString(), txt_descricao.getText().toString());
                if(rowInserted != -1)
                    Toast.makeText(getApplicationContext() , "Fornecedor adicionado, linha id: " + rowInserted, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext() , "Erro", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
