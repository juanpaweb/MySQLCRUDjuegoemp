package net.simplifiedcoding.efe;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ViewEmployee1 extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextId1;
    private EditText ventau_elite;
    private EditText ventau_ejecutivo;
    private EditText ventau_combate;
    private EditText publicidad_elite;
    private EditText publicidad_ejecutivo;
    private EditText publicidad_combate;
    private EditText n_cam_fabricar_elite;
    private EditText n_cam_fabricar_ejecutivo;
    private EditText n_cam_fabricar_combate;
    private EditText n_cam_venta_elite;
    private EditText n_cam_venta_ejecutivo;
    private EditText n_cam_venta_combate;
    private EditText kit_maquinariaU;
    private EditText tela_nal_mts;
    private EditText telaimportada_mts;
    private EditText otros_insumos_com_U;
    private EditText otros_insumos_elite_U;
    private EditText kitmaquinaria_son;
    private EditText tela_nacional_son;
    private EditText tela_importada_son;
    private EditText otros_insumos_combate_son;
    private EditText otros_insumos_elite_son;


    private Button buttonUpdate1;
    private Button buttonDelete1;


    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee1);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config1.EMP_ID1);

        editTextId1 = (EditText) findViewById(R.id.editTextId1);
        ventau_elite = (EditText) findViewById(R.id.ventau_elite);
        ventau_ejecutivo = (EditText) findViewById(R.id.ventau_ejecutivo);
        ventau_combate = (EditText) findViewById(R.id.ventau_combate);
        publicidad_elite = (EditText) findViewById(R.id.publicidad_elite);
        publicidad_ejecutivo = (EditText) findViewById(R.id.publicidad_ejecutivo);
        publicidad_combate = (EditText) findViewById(R.id.publicidad_combate);
        n_cam_fabricar_elite = (EditText) findViewById(R.id.n_cam_fabricar_elite);
        n_cam_fabricar_ejecutivo = (EditText) findViewById(R.id.n_cam_fabricar_ejecutivo);
        n_cam_fabricar_combate = (EditText) findViewById(R.id.n_cam_fabricar_combate);
        n_cam_venta_elite = (EditText) findViewById(R.id.n_cam_venta_elite);
        n_cam_venta_ejecutivo = (EditText) findViewById(R.id.n_cam_venta_ejecutivo);
        n_cam_venta_combate = (EditText) findViewById(R.id.n_cam_venta_combate);
        kit_maquinariaU = (EditText) findViewById(R.id.kit_maquinariaU);
        tela_nal_mts = (EditText) findViewById(R.id.tela_nal_mts);
        telaimportada_mts = (EditText) findViewById(R.id.telaimportada_mts);
        otros_insumos_com_U = (EditText) findViewById(R.id.otros_insumos_com_U);
        otros_insumos_elite_U = (EditText) findViewById(R.id.otros_insumos_elite_U);
        kitmaquinaria_son = (EditText) findViewById(R.id.kitmaquinaria_son);
        tela_nacional_son = (EditText) findViewById(R.id.tela_nacional_son);
        tela_importada_son = (EditText) findViewById(R.id.tela_importada_son);
        otros_insumos_combate_son = (EditText) findViewById(R.id.otros_insumos_combate_son);
        otros_insumos_elite_son = (EditText) findViewById(R.id.otros_insumos_elite_son);


        buttonUpdate1 = (Button) findViewById(R.id.buttonUpdate1);
        buttonDelete1 = (Button) findViewById(R.id.buttonDelete1);

        buttonUpdate1.setOnClickListener(this);
        buttonDelete1.setOnClickListener(this);

        editTextId1.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewEmployee1.this,"Obteniendo datos...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config1.URL_GET_EMP1,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result1 = jsonObject.getJSONArray(Config1.TAG_JSON_ARRAY1);
            JSONObject c = result1.getJSONObject(0);
            String ventaunidadelite = c.getString(Config1.TAG_VENTAUNIDADELITE);
            String ventaunidadejecutivo = c.getString(Config1.TAG_VENTAUNIDADEJECUTIVO);
            String ventaunidadcombate = c.getString(Config1.TAG_VENTAUNIDADCOMBATE);
            String publicidadelite = c.getString(Config1.TAG_PUBLICIDADELITE);
            String publicidadejecutivo = c.getString(Config1.TAG_PUBLICIDADEJECUTIVO);
            String publicidadcombate = c.getString(Config1.TAG_PUBLICIDADCOMBATE);
            String numerodecamisasafabricarelite = c.getString(Config1.TAG_NUMERODECAMISASFABRICARELITE);
            String numerodecamisasafabricarejecutivo = c.getString(Config1.TAG_NUMERODECAMISASAFABRICAREJECUTIVO);
            String numerodecamisasafabricarcombate = c.getString(Config1.TAG_NUMERODECAMISASAFABRICARCOMBATE);
            String numerodecamisasavenderelite = c.getString(Config1.TAG_NUMERODECAMISASAVENDERELITE);
            String numerodecamisasavenderejecutivo = c.getString(Config1.TAG_NUMERODECAMISASAVENDEREJECUTIVO);
            String numerodecamisasavendercombate = c.getString(Config1.TAG_NUMERODECAMISASAVENDERCOMBATE);
            String kitdemaquinariaunidades = c.getString(Config1.TAG_KITDEMAQUINARIAUNIDADES);
            String telanacionalenmts = c.getString(Config1.TAG_TELANACIONALENMTS);
            String telaimportadamts = c.getString(Config1.TAG_TELAIMPORTADAMTS);
            String otrosinsumoscombateunidades = c.getString(Config1.TAG_OTROSINSUMOSCOMBATEUNIDAD);
            String otrosinsumoseliteunidad = c.getString(Config1.TAG_OTROSINSUMOSELITEUNIDAD);
            String kitmaquinariasiono = c.getString(Config1.TAG_KITMAQUINARIASIONO);
            String telanacionalsiono = c.getString(Config1.TAG_TELANACIONALSIONO);
            String telaimportadasiono = c.getString(Config1.TAG_TELAIMPORTADASIONO);
            String otrosinsumoscombatesiono = c.getString(Config1.TAG_OTROSINSUMOSCOMBATESIONO);
            String otrosinsumoselitesiono = c.getString(Config1.TAG_OTROSINSUMOSELITESIONO);



            ventau_elite.setText(ventaunidadelite);
            ventau_ejecutivo.setText(ventaunidadejecutivo);
            ventau_combate.setText(ventaunidadcombate);
            publicidad_elite.setText(publicidadelite);
            publicidad_ejecutivo.setText(publicidadejecutivo);
            publicidad_combate.setText(publicidadcombate);
            n_cam_fabricar_elite.setText(numerodecamisasafabricarelite);
            n_cam_fabricar_ejecutivo.setText(numerodecamisasafabricarejecutivo);
            n_cam_fabricar_combate.setText(numerodecamisasafabricarcombate);
            n_cam_venta_elite.setText(numerodecamisasavenderelite);
            n_cam_venta_ejecutivo.setText(numerodecamisasavenderejecutivo);
            n_cam_venta_combate.setText(numerodecamisasavendercombate);
            kit_maquinariaU.setText(kitdemaquinariaunidades);
            tela_nal_mts.setText(telanacionalenmts);
            telaimportada_mts.setText(telaimportadamts);
            otros_insumos_com_U.setText(otrosinsumoscombateunidades);
            otros_insumos_elite_U.setText(otrosinsumoseliteunidad);
            kitmaquinaria_son.setText(kitmaquinariasiono);
            tela_nacional_son.setText(telanacionalsiono);
            tela_importada_son.setText(telaimportadasiono);
            otros_insumos_combate_son.setText(otrosinsumoscombatesiono);
            otros_insumos_elite_son.setText(otrosinsumoselitesiono);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){
        final String ventaunidadelite = ventau_elite.getText().toString().trim();
        final String ventaunidadejecutivo = ventau_ejecutivo.getText().toString().trim();
        final String ventaunidadcombate = ventau_combate.getText().toString().trim();
        final String publicidadelite = publicidad_elite.getText().toString().trim();
        final String publicidadejecutivo = publicidad_ejecutivo.getText().toString().trim();
        final String publicidadcombate = publicidad_combate.getText().toString().trim();
        final String numerodecamisasafabricarelite = n_cam_fabricar_elite.getText().toString().trim();
        final String numerodecamisasafabricarejecutivo = n_cam_fabricar_ejecutivo.getText().toString().trim();
        final String numerodecamisasafabricarcombate = n_cam_fabricar_combate.getText().toString().trim();
        final String numerodecamisasavenderelite = n_cam_venta_elite.getText().toString().trim();
        final String numerodecamisasavenderejecutivo = n_cam_venta_ejecutivo.getText().toString().trim();
        final String numerodecamisasavendercombate = n_cam_venta_combate.getText().toString().trim();
        final String kitdemaquinariaunidades = kit_maquinariaU.getText().toString().trim();
        final String telanacionalenmts = tela_nal_mts.getText().toString().trim();
        final String telaimportadamts = telaimportada_mts.getText().toString().trim();
        final String otrosinsumoscombateunidades = otros_insumos_com_U.getText().toString().trim();
        final String otrosinsumoseliteunidad = otros_insumos_elite_U.getText().toString().trim();
        final String kitmaquinariasiono = kitmaquinaria_son.getText().toString().trim();
        final String telanacionalsiono = tela_nacional_son.getText().toString().trim();
        final String telaimportadasiono = tela_importada_son.getText().toString().trim();
        final String otrosinsumoscombatesiono = otros_insumos_combate_son.getText().toString().trim();
        final String otrosinsumoselitesiono = otros_insumos_elite_son.getText().toString().trim();


        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewEmployee1.this,"Actualizando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewEmployee1.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config1.KEY_EMP_ID1,id);
                hashMap.put(Config1.KEY_EMP_VENTAUNIDADELITE,ventaunidadelite);
                hashMap.put(Config1.KEY_EMP_VENTAUNIDADEJECUTIVO,ventaunidadejecutivo);
                hashMap.put(Config1.KEY_EMP_VENTAUNIDADCOMBATE,ventaunidadcombate);
                hashMap.put(Config1.KEY_EMP_PUBLICIDADELITE,publicidadelite);
                hashMap.put(Config1.KEY_EMP_PUBLICIDADEJECUTIVO,publicidadejecutivo);
                hashMap.put(Config1.KEY_EMP_PUBLICIDADCOMBATE,publicidadcombate);
                hashMap.put(Config1.KEY_EMP_NUMERODECAMISASAFABRICARELITE,numerodecamisasafabricarelite);
                hashMap.put(Config1.KEY_EMP_NUMERODECAMISASAFABRICAREJECUTIVO,numerodecamisasafabricarejecutivo);
                hashMap.put(Config1.KEY_EMP_NUMERODECAMISASAFABRICARCOMBATE,numerodecamisasafabricarcombate);
                hashMap.put(Config1.KEY_EMP_NUMERODECAMISASAVENDERELITE,numerodecamisasavenderelite);
                hashMap.put(Config1.KEY_EMP_NUMERODECAMISASAVENDEREJECUTIVO,numerodecamisasavenderejecutivo);
                hashMap.put(Config1.KEY_EMP_NUMERODECAMISASAVENDERCOMBATE,numerodecamisasavendercombate);
                hashMap.put(Config1.KEY_EMP_KITDEMAQUINARIAUNIDADES,kitdemaquinariaunidades);
                hashMap.put(Config1.KEY_EMP_TELANACIONALENMTS,telanacionalenmts);
                hashMap.put(Config1.KEY_EMP_TELAIMPORTADAENMTS,telaimportadamts);
                hashMap.put(Config1.KEY_EMP_OTROSINSUMOSCOMBATEUNIDADES,otrosinsumoscombateunidades);
                hashMap.put(Config1.KEY_EMP_OTROSINSUMOSELITEUNIDADES,otrosinsumoseliteunidad);
                hashMap.put(Config1.KEY_EMP_KITMAQUINARIASIONO,kitmaquinariasiono);
                hashMap.put(Config1.KEY_EMP_TELANACIONALSIONO,telanacionalsiono);
                hashMap.put(Config1.KEY_EMP_TELAIMPORTADASIONO,telaimportadasiono);
                hashMap.put(Config1.KEY_EMP_OTROSINSUMOSCOMBATESIONO,otrosinsumoscombatesiono);
                hashMap.put(Config1.KEY_EMP_OTROSINSUMOSELITESIONO,otrosinsumoselitesiono);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config1.URL_UPDATE_EMP1,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewEmployee1.this, "Actualizando...", "Espere...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewEmployee1.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config1.URL_DELETE_EMP1, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Esta seguro de borrar este grupo?");

        alertDialogBuilder.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(ViewEmployee1.this,View_all_employee1.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate1){
            updateEmployee();
        }

        if(v == buttonDelete1){
            confirmDeleteEmployee();
        }


    }
}
