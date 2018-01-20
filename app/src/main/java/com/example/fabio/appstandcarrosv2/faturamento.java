package com.example.fabio.appstandcarrosv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class faturamento extends AppCompatActivity {

    protected TextView txt_valor_stock, txt_valor_vendas, txt_saldo, txt_sum_vendas_mes, txt_investimento;
    protected EditText etxt_despesas, etxt_Ano;
    protected Button btn_1, btn_saldo;
    protected AdaptarBasededados a;
    protected Double sum_vendas_mes, despesas;
    protected Spinner spinner_mes;
    protected final String[] meses = {"", "Janeiro","Fevereiro", "Março","Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
            "Outubro", "Novembro", "Dezembro"};
    protected Double valstock, investimento, valvendas;

    @Override
    protected void onStart() {
        super.onStart();
        a = new AdaptarBasededados(this).open();
        //sum stock e vendas
        try {
            //valor stock
            valstock = a.retornaStock();
            txt_valor_stock.setText(valstock + " €");
            //valor vendas
            valvendas = a.retornaVendas();
            txt_valor_vendas.setText(valvendas + " €");
            //valor investimento
            investimento = a.retornaInvestimentoStock();
            txt_investimento.setText(investimento + " €");
            //Diferença entre Investimento e Vendas
        }catch(NullPointerException e){}catch(IndexOutOfBoundsException e){}
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
        txt_investimento = (TextView)findViewById(R.id.txt_investimento);
        txt_saldo = (TextView)findViewById(R.id.txt_saldo);
        btn_1 = (Button)findViewById(R.id.btn_1);
        etxt_despesas = (EditText)findViewById(R.id.etxt_despesas);
        txt_sum_vendas_mes = (TextView)findViewById(R.id.txt_sum_vendas_mes);
        btn_saldo = (Button)findViewById(R.id.btn_saldo);
        etxt_Ano = (EditText)findViewById(R.id.etxt_Ano);
        spinner_mes = (Spinner)findViewById(R.id.spinner_mes);

        //spinner dos meses
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, meses);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mes.setAdapter(adp);
        btn_1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try{
                    int oAno = Integer.parseInt(etxt_Ano.getText().toString());
                    sum_vendas_mes = a.retornaVendaAnoMes(oAno, spinner_mes.getSelectedItemPosition());
                    txt_sum_vendas_mes.setText(sum_vendas_mes+ " €");
                }catch(NullPointerException e){
                    Toast.makeText(getApplicationContext() , "Valores Errados", Toast.LENGTH_SHORT).show();}
                catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext() , "Escrever Ano Válido", Toast.LENGTH_SHORT).show();
                }catch(IndexOutOfBoundsException e){
                    Toast.makeText(getApplicationContext() , "Número de Carateres excedido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_saldo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try {
                    despesas = Double.parseDouble(etxt_despesas.getText().toString());
                    double saldo = sum_vendas_mes - despesas;
                    txt_saldo.setText(saldo + " €");
                }catch(NullPointerException e){
                    Toast.makeText(getApplicationContext() , "Saldo Não Calculado", Toast.LENGTH_SHORT).show();}
                catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext() , "Escrever Despesas Válidas", Toast.LENGTH_SHORT).show();
                }catch(IndexOutOfBoundsException e){
                    Toast.makeText(getApplicationContext() , "Número de Carateres excedido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
