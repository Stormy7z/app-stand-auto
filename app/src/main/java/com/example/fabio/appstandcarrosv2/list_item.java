package com.example.fabio.appstandcarrosv2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class list_item extends AppCompatActivity {

    private String TAG = list_item.class.getSimpleName();
    private boolean veri = false;
    private ProgressDialog pDialog;
    private ListView lv;
    protected Button btn_marca;
    final String[] marcas = {"aston","bmw", "honda","maserati", "toyota", "mercedes", "ford", "nissan", "gm",
            "renault", "volkswagen", "volvo", "fiat", "mazda", "suzuki", "mitsubishi"};
    Spinner sp1;
    String aMarca;
    private String url;
    ArrayList<HashMap<String, String>> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlist);
        itemList = new ArrayList<>();
        sp1 = (Spinner) findViewById(R.id.spinner);
        lv = (ListView) findViewById(R.id.listView);
        btn_marca = (Button)findViewById(R.id.btn_Marca);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, marcas);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adp);

        btn_marca.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                veri = true;
                aMarca = sp1.getSelectedItem().toString();
                url = "https://vpic.nhtsa.dot.gov/api/vehicles/getmanufacturerdetails/"+aMarca+"?format=json";
                new list_item.GetItems().execute();
            }
        });
    }

    private class GetItems extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //show lod dia
            pDialog = new ProgressDialog(list_item.this);
            pDialog.setMessage("loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids){
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);
            //Log.e(TAG, "Response from url: ");
            itemList.clear();

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
                        //add child nodes to hashmap
                        item.put("Mfr_Name", name);
                        item.put("Address", address);
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
                                "Sem resposta do servidorS ",
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
            lv.setAdapter(null);
            ListAdapter adapter = new SimpleAdapter(
                    list_item.this, itemList,
                    R.layout.activity_list_item, new String[]{"Mfr_Name", "Address"},
                    new int[]{R.id.mfr_Name, R.id.address});
            lv.setAdapter(adapter);

        }
    }
}
