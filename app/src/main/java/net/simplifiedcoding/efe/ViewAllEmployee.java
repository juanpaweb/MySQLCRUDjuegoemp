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

public class ViewAllEmployee extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employee);
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
                String id = jo.getString(Config.TAG_ID);
                String grupo = jo.getString(Config.TAG_GRUPO);
                String decision_mes = jo.getString(Config.TAG_DECISION_MES);
                String trasladocc_acdt = jo.getString(Config.TAG_TRASLADOCC_ACDT);
                String trasladocdt_acc = jo.getString(Config.TAG_TRASLADOCDT_ACC);
                String sol_cred_bancario = jo.getString(Config.TAG_SOL_CRED_BANCARIO);
                String pag_cred_bancario = jo.getString(Config.TAG_PAG_CRED_BANCARIO);
                String pag_ctas_por_pagar = jo.getString(Config.TAG_PAG_CTAS_POR_PAGAR);
                String plazo_meses_cdt = jo.getString(Config.TAG_PLAZO_MESES_CDT);
                String plazo_meses_bancario = jo.getString(Config.TAG_PLAZO_MESES_BANCARIO);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_ID,id);
                employees.put(Config.TAG_GRUPO,grupo);
                employees.put(Config.TAG_DECISION_MES,decision_mes);
                employees.put(Config.TAG_TRASLADOCC_ACDT,trasladocc_acdt);
                employees.put(Config.TAG_TRASLADOCDT_ACC,trasladocdt_acc);
                employees.put(Config.TAG_SOL_CRED_BANCARIO,sol_cred_bancario);
                employees.put(Config.TAG_PAG_CRED_BANCARIO,pag_cred_bancario);
                employees.put(Config.TAG_PAG_CTAS_POR_PAGAR,pag_ctas_por_pagar);
                employees.put(Config.TAG_PLAZO_MESES_CDT,plazo_meses_cdt);
                employees.put(Config.TAG_PLAZO_MESES_BANCARIO,plazo_meses_bancario);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewAllEmployee.this, list, R.layout.list_item,
                new String[]{Config.TAG_ID,Config.TAG_GRUPO},
                new int[]{R.id.id, R.id.id});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewAllEmployee.this,"Obteniendo datos","Espere...",false,false);
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
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewEmployee.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.EMP_ID,empId);
        startActivity(intent);
    }
}
