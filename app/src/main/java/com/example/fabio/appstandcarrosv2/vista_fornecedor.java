package com.example.fabio.appstandcarrosv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class vista_fornecedor extends AppCompatActivity {

    protected AdaptarBasededados a;
    protected Button add_fornecedor;
    protected ListView lv;
    List<String> Fornecedores;
    List<String> osFornec;
    protected Integer posicao;
    protected ArrayAdapter<String> listAdapter;

    private void executarOutraActivity(Class<?> subActividade, ArrayList<String> val, Integer pos) {
        Intent x = new Intent(this, subActividade);
        x.putExtra("osFornec", val);
        x.putExtra("posicao", pos);
        startActivity(x);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "fornec onStart()", Toast.LENGTH_SHORT).show();
        //mete fornecedores na lista
        a = new AdaptarBasededados(this).open();
        Fornecedores = a.obterTodosFornecedores();
        if(Fornecedores != null){
            //adicionar ao listview
            lv = (ListView) findViewById(R.id.listView);
            ArrayList<String> osFornc = new ArrayList<String>();
            osFornc.addAll(Fornecedores);
            listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Fornecedores);
            lv.setAdapter(listAdapter);
        }
    }

    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "fornec onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "fornec onStop()", Toast.LENGTH_SHORT).show();
        a.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_fornecedor);

        add_fornecedor = (Button) findViewById(R.id.add_fornecedor);
        lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicao = position;
                osFornec = a.obterTodosF(position);
                executarOutraActivity(info_fornecedor.class, (ArrayList)osFornec, posicao);
            }
        });

        add_fornecedor.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarOutraActivity(add_fornecedor.class, null, null);
            }
        });
    }
}