package com.example.fabio.appstandcarrosv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class list_item extends AppCompatActivity {

    private String TAG = list_item.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    protected Button btn_marca;
    private String morada;
    private final String[] marcas = {"aston","bmw", "honda","maserati", "toyota", "mercedes", "ford", "nissan", "gm",
            "renault", "volkswagen", "volvo", "fiat", "mazda", "suzuki", "mitsubishi"};
    protected List<String> asMoradas;
    protected Spinner sp1;
    protected String aMarca;
    private String url;
    protected ArrayList<HashMap<String, String>> itemList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlist);
        itemList = new ArrayList<>();
        asMoradas = new ArrayList<String>();
        sp1 = (Spinner) findViewById(R.id.spinner);
        lv = (ListView) findViewById(R.id.listView);
        btn_marca = (Button)findViewById(R.id.btn_Marca);
        //ecrever fornecedores revenda
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, marcas);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adp);
        //return resultados da api
        btn_marca.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    aMarca = sp1.getSelectedItem().toString();
                    url = "https://vpic.nhtsa.dot.gov/api/vehicles/getmanufacturerdetails/" + aMarca + "?format=json";
                    new list_item.GetItems().execute();
                }catch(NullPointerException e){}
            }
        });
        //ir para localização do fornecedor
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                morada = asMoradas.get(position);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:?q= "+morada));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
    }

    private class GetItems extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(list_item.this);
            pDialog.setMessage("loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids){
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);
            itemList.clear();
            asMoradas.clear();
            if(jsonStr != null ){
                try{
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    //get json arr
                    JSONArray items = jsonObject.getJSONArray("Results");
                    for (int i = 0; i < items.length(); i++){
                        JSONObject c = items.getJSONObject(i);
                        //get object values
                        String name = c.getString("Mfr_Name");
                        String address = c.getString("Address");
                        HashMap<String, String> item = new HashMap<>();
                        //adiciona child nodes ao hashmap
                        item.put("Mfr_Name", name);
                        item.put("Address", address);
                        asMoradas.add(address);
                        itemList.add(item);
                    }
                }catch (final JSONException e){
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            Toast.makeText(list_item.this,
                                    "Erro de Parsing no JSON",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }else{
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        Toast.makeText(list_item.this,
                                "Sem resposta do servidor",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            //dismiss the dial
            if (pDialog.isShowing()){
                pDialog.dismiss();
            }
            //escrever no listview
            lv.setAdapter(null);
            ListAdapter adapter = new SimpleAdapter(
                    list_item.this, itemList,
                    R.layout.activity_list_item, new String[]{"Mfr_Name", "Address"},
                    new int[]{R.id.txt1, R.id.txt2});
            lv.setAdapter(adapter);
        }
    }
}
