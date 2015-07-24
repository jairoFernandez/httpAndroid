package com.example.jairofernandez.ejemplojson;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ListView listado;
    ArrayAdapter<Lamina> adapter;
    List<Lamina> registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        listado = (ListView) findViewById(R.id.lvResultados);
        long numberRegistro = Lamina.count(Lamina.class, null, null);

        if (numberRegistro == 0) {
            ObtDatos();
            registros = Lamina.findWithQuery(Lamina.class, "SELECT TIPO_LAMINA FROM Lamina");
        }else{
            CargarLista();
        }

        Button btnWebService = (Button) findViewById(R.id.btnWebService);

        btnWebService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObtDatos();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void ObtDatos() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://multikloset.tucompualdia.net/kloset/catalogo";
        RequestParams parametros = new RequestParams();
        parametros.put("Android", "Android");
        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    // CargaLista(obtDatosJSON(new String(responseBody)));
                    obtDatosJSON(new String(responseBody));

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void CargarLista() {
        //registros = Lamina.findAll(Lamina.class);
        //registros = Lamina.listAll(Lamina.class);

        registros = Lamina.findWithQuery(Lamina.class, "SELECT TIPO_LAMINA FROM Lamina");
        // List<Note> notes = Note.findWithQuery(Note.class, "Select * from Note where name = ?", "satya");

        if (registros == null) {
            ObtDatos();
            registros = Lamina.findWithQuery(Lamina.class, "SELECT TIPO_LAMINA FROM Lamina");
            //registros = Lamina.listAll(Lamina.class);
        }
        adapter = new ArrayAdapter<Lamina>(this.getApplicationContext(), R.layout.abc_list_menu_item_layout, R.id.lvResultados, registros);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listado.setAdapter(adapter);
    }

    public ArrayList<String> obtDatosJSON(String response) {
        ArrayList<String> listado = new ArrayList<String>();
        try {
            JSONArray resulArray = new JSONArray(response);
            String texto;
            String tipoLamina;
            Integer ancho;
            Integer alto;
            Integer valor;
            Integer valorCm;
            Lamina.deleteAll(Lamina.class);
            for (int i = 0; i < resulArray.length(); i++) {
                texto = resulArray.getJSONObject(i).getString("tipoLamina") + " (" +
                        resulArray.getJSONObject(i).getString("alto") + "X" +
                        resulArray.getJSONObject(i).getString("ancho") + ")";
                listado.add(texto);

                tipoLamina = resulArray.getJSONObject(i).getString("tipoLamina");
                ancho = Integer.parseInt(resulArray.getJSONObject(i).getString("ancho"));
                alto = Integer.parseInt(resulArray.getJSONObject(i).getString("alto"));
                valor = Integer.parseInt(resulArray.getJSONObject(i).getString("valor"));
                valorCm = Integer.parseInt(resulArray.getJSONObject(i).getString("valorCm"));

                Lamina lamina = new Lamina(tipoLamina, ancho, alto, valor, valorCm);
                lamina.save();
                Log.d("results", texto);
            }

            CargarLista();

        } catch (Exception e) {

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
