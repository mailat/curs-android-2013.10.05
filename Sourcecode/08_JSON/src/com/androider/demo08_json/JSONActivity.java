package com.androider.demo08_json;

import java.util.ArrayList;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class JSONActivity extends ListActivity {

	ArrayList<String> valuesAvailable = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//seteaza titlul activitatii
		setTitle("Weather in Brasov");		
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valuesAvailable);
		setListAdapter(adapter);			
		
		new WeatherReader().execute("http://api.openweathermap.org/data/2.5/weather?q=Brasov,ro&units=metric");
	}

    private class WeatherReader extends AsyncTask<String, Void, Void> {
    	
        private final HttpClient client = new DefaultHttpClient();
        private String response;
        private ProgressDialog progressDialog = new ProgressDialog(JSONActivity.this);
        
        protected void onPreExecute() {
        	progressDialog.setMessage("Descarcam vremea ...");
        	progressDialog.show();
        }

        protected Void doInBackground(String... urls) {
            try {
                HttpGet httpget = new HttpGet(urls[0]);
                response = client.execute(httpget, new BasicResponseHandler());
            } catch (Throwable e) {
                cancel(true);
            }
            
            return null;
        }
        
        protected void onPostExecute(Void unused) {
        	
        	Log.d("MARUS", response);
        	//adaugam elementele de vreme in lista
        	
        	try {
        	// We create out JSONObject from the data
        	JSONObject jObj = new JSONObject(response);

        	JSONObject jsonObj = jObj.getJSONObject("coord");
        	adapter.add("lat:" + jsonObj.getString("lat"));
         	adapter.add("lon" + jsonObj.getString("lon"));
         	
        	jsonObj = jObj.getJSONObject("main");
        	adapter.add("humidity " + jsonObj.getString("humidity"));
         	adapter.add("pressure " + jsonObj.getString("pressure"));
         	adapter.add("temp_min " + jsonObj.getString("temp_min"));
         	adapter.add("temp_max " + jsonObj.getString("temp_max"));

        } catch (Throwable e) {
			//reactioneaza
		}

        //inchidem dialogul 
    	progressDialog.dismiss();
        }
    }
}