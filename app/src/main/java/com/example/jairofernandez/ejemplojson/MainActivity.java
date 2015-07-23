package com.example.jairofernandez.ejemplojson;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.*;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ListView listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listado = (ListView)findViewById(R.id.lvResultados);
        final EditText etNombre = (EditText)findViewById(R.id.etNombre);
        final EditText etEdad = (EditText)findViewById(R.id.etEdad);
        final EditText etHobbies = (EditText)findViewById(R.id.etHobbies);
        final TextView tvResultado = (TextView)findViewById(R.id.tvResultado);
        TextView tvWebService = (TextView)findViewById(R.id.tVWebService);

        Button btnGuardar = (Button)findViewById(R.id.btnGuardar);
        Button btnWebService = (Button)findViewById(R.id.btnWebService);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                Integer edad = Integer.parseInt(etEdad.getText().toString());
                String hobbies = etHobbies.getText().toString();
                JSONObject json = new JSONObject();
                String jsonString = "";
                try{
                    json.put("nombre",nombre);
                    json.put("hobbies",hobbies);
                    json.put("edad",edad);
                }catch (JSONException e){}

                jsonString = json.toString();
                tvResultado.setText(jsonString);


            }
        });

        btnWebService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public void ObtDatos(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url ="http://multikloset.tucompualdia.net/kloset/catalogo";
        RequestParams parametros = new RequestParams();
        parametros.put("Android","Android");
        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    CargaLista(obtDatosJSON(new String(responseBody)));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void CargaLista(ArrayList<String> datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listado.setAdapter(adapter);
    }

    public ArrayList<String> obtDatosJSON(String response){
        ArrayList<String> listado = new ArrayList<String>();
        try{
            JSONArray resulArray = new JSONArray(response);
            String texto;

            for (int i=0; i<resulArray.length();i++)
            {
                texto = resulArray.getJSONObject(i).getString("tipoLamina")+" "+
                        resulArray.getJSONObject(i).getString("alto")+" "+
                        resulArray.getJSONObject(i).getString("ancho");
                listado.add(texto);
            }

        }catch (Exception e){

        }
        return listado;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
