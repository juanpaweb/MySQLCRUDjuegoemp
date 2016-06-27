package net.simplifiedcoding.efe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class view_all_employee2 extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employee2);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config2.TAG_ID2);
                String norma9000 = jo.getString(Config2.TAG_NORMA9000);
                String licitacion_camisas_combate = jo.getString(Config2.TAG_LICITACIONCOMBATE);
                String preciounitario_licitacion = jo.getString(Config2.TAG_LICITACIONUNITARIO);
                String segurorobo = jo.getString(Config2.TAG_SEGUROSON);
                String incremento_salarios = jo.getString(Config2.TAG_INCREMENTOSALARIOS);
                String participacion_exp_elite = jo.getString(Config2.TAG_EXPORTACIONELITE);
                String cantidad_ofertada = jo.getString(Config2.TAG_ELITECANTIDADOFERTADA);

                                                                    //campos de la DB
                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config2.TAG_ID2,id);
                employees.put(Config2.TAG_NORMA9000,norma9000);
                employees.put(Config2.TAG_LICITACIONCOMBATE,licitacion_camisas_combate);
                employees.put(Config2.TAG_LICITACIONUNITARIO,preciounitario_licitacion);
                employees.put(Config2.TAG_SEGUROSON,segurorobo);
                employees.put(Config2.TAG_INCREMENTOSALARIOS,incremento_salarios);
                employees.put(Config2.TAG_EXPORTACIONELITE,participacion_exp_elite);
                employees.put(Config2.TAG_ELITECANTIDADOFERTADA,cantidad_ofertada);


                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                view_all_employee2.this, list, R.layout.list_item,
                new String[]{Config2.TAG_ID2,Config2.TAG_NORMA9000},
                new int[]{R.id.id, R.id.id});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(view_all_employee2.this,"Obteniendo datos","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config2.URL_GET_ALL2);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewEmployee1.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Config2.TAG_ID2).toString();
        intent.putExtra(Config2.EMP_ID2,empId);
        startActivity(intent);
    }
}
