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

public class View_all_employee1 extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employee1);
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
                String id = jo.getString(Config1.TAG_ID1);
                String ventaunidadelite = jo.getString(Config1.TAG_VENTAUNIDADELITE);
                String ventaunidadejecutivo = jo.getString(Config1.TAG_VENTAUNIDADEJECUTIVO);
                String ventaunidadcombate = jo.getString(Config1.TAG_VENTAUNIDADCOMBATE);
                String publicidadelite = jo.getString(Config1.TAG_PUBLICIDADELITE);
                String publicidadejecutivo = jo.getString(Config1.TAG_PUBLICIDADEJECUTIVO);
                String publicidadcombate = jo.getString(Config1.TAG_PUBLICIDADCOMBATE);
                String numerodecamisasafabricarelite = jo.getString(Config1.TAG_NUMERODECAMISASFABRICARELITE);
                String numerodecamisasafabricarejecutivo = jo.getString(Config1.TAG_NUMERODECAMISASAFABRICAREJECUTIVO);
                String numerodecamisasafabricarcombate = jo.getString(Config1.TAG_NUMERODECAMISASAFABRICARCOMBATE);
                String numerodecamisasavenderelite = jo.getString(Config1.TAG_NUMERODECAMISASAVENDERELITE);
                String numerodecamisasavenderejecutivo = jo.getString(Config1.TAG_NUMERODECAMISASAVENDEREJECUTIVO);
                String numerodecamisasavendercombate = jo.getString(Config1.TAG_NUMERODECAMISASAVENDERCOMBATE);
                String kitdemaquinariaunidades = jo.getString(Config1.TAG_KITDEMAQUINARIAUNIDADES);
                String telanacionalenmts = jo.getString(Config1.TAG_TELANACIONALENMTS);
                String telaimportadamts = jo.getString(Config1.TAG_TELAIMPORTADAMTS);
                String otrosinsumoscombateunidades = jo.getString(Config1.TAG_OTROSINSUMOSCOMBATEUNIDAD);
                String otrosinsumoseliteunidad = jo.getString(Config1.TAG_OTROSINSUMOSELITEUNIDAD);
                String kitmaquinariasiono = jo.getString(Config1.TAG_KITMAQUINARIASIONO);
                String telanacionalsiono = jo.getString(Config1.TAG_TELANACIONALSIONO);
                String telaimportadasiono = jo.getString(Config1.TAG_TELAIMPORTADASIONO);
                String otrosinsumoscombatesiono = jo.getString(Config1.TAG_OTROSINSUMOSCOMBATESIONO);
                String otrosinsumoselitesiono = jo.getString(Config1.TAG_OTROSINSUMOSELITESIONO);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config1.TAG_ID1,id);
                employees.put(Config1.TAG_VENTAUNIDADELITE,ventaunidadelite);
                employees.put(Config1.TAG_VENTAUNIDADEJECUTIVO,ventaunidadejecutivo);
                employees.put(Config1.TAG_VENTAUNIDADCOMBATE,ventaunidadcombate);
                employees.put(Config1.TAG_PUBLICIDADELITE,publicidadelite);
                employees.put(Config1.TAG_PUBLICIDADEJECUTIVO,publicidadejecutivo);
                employees.put(Config1.TAG_PUBLICIDADCOMBATE,publicidadcombate);
                employees.put(Config1.TAG_NUMERODECAMISASFABRICARELITE,numerodecamisasafabricarelite);
                employees.put(Config1.TAG_NUMERODECAMISASAFABRICAREJECUTIVO,numerodecamisasafabricarejecutivo);
                employees.put(Config1.TAG_NUMERODECAMISASAFABRICARCOMBATE,numerodecamisasafabricarcombate);
                employees.put(Config1.TAG_NUMERODECAMISASAVENDERELITE,numerodecamisasavenderelite);
                employees.put(Config1.TAG_NUMERODECAMISASAVENDEREJECUTIVO,numerodecamisasavenderelite);
                employees.put(Config1.TAG_NUMERODECAMISASFABRICARELITE,numerodecamisasafabricarelite);
                employees.put(Config1.TAG_NUMERODECAMISASAFABRICAREJECUTIVO,numerodecamisasafabricarejecutivo);
                employees.put(Config1.TAG_NUMERODECAMISASAFABRICARCOMBATE,numerodecamisasafabricarcombate);
                employees.put(Config1.TAG_NUMERODECAMISASAVENDERELITE,numerodecamisasavenderelite);
                employees.put(Config1.TAG_NUMERODECAMISASAVENDEREJECUTIVO,numerodecamisasavenderelite);
                employees.put(Config1.TAG_NUMERODECAMISASAVENDEREJECUTIVO,numerodecamisasavenderelite);

                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                View_all_employee1.this, list, R.layout.list_item,
                new String[]{Config1.TAG_ID1,Config1.TAG_VENTAUNIDADELITE},
                new int[]{R.id.id, R.id.id});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(View_all_employee1.this,"Obteniendo datos","Espere...",false,false);
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
                String s = rh.sendGetRequest(Config1.URL_GET_ALL1);
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
        String empId = map.get(Config1.TAG_ID1).toString();
        intent.putExtra(Config1.EMP_ID1,empId);
        startActivity(intent);
    }
}
