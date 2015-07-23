package com.example.jairofernandez.ejemplojson;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.*;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
