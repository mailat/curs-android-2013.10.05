package com.hello.brasov;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends Activity {

	String loadUrl ;
	TextView textTitlu;
	private String response;
	
	@Override
	protected void onStart() {
		super.onStart();

		Log.d("MARUS", "on start SecondActivity");
		setContentView(R.layout.activity_second);

		// schimbam textul in textview cu cel transmis
		Intent intent = getIntent();
		String oras = intent.getStringExtra("oras");

		textTitlu = (TextView) findViewById(R.id.textTitlu);


		loadUrl = "http://api.openweathermap.org/data/2.5/weather?q="
				+ oras + ",ro&units=metric";

		new WeatherReaderUpdater().execute();
	}

	private class WeatherReaderUpdater extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {

			try {
				// transporter for our in->out call
				HttpClient client = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(loadUrl);

				response = client.execute(httpget, new BasicResponseHandler());
				Log.d("MARUS", "response back: " + response);
			} catch (Throwable e) {
				e.printStackTrace();
				Log.d("MARUS", "response back: " + e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			//din json reader luam Json objects
        	try {
        		StringBuffer buffer = new StringBuffer();
				JSONObject jObj = new JSONObject(response);
				
				//get JSONObject - coord
	        	JSONObject jsonObj = jObj.getJSONObject("coord");
	        	//get string from coord - lat, lon
	        	buffer.append(jsonObj.getString("lat")).append(" - ");
	        	buffer.append(jsonObj.getString("lon")).append(" , temp: ");
				
				//get JSONObject - main
	        	jsonObj = jObj.getJSONObject("main");
	        	//get temp
	        	buffer.append(jsonObj.getString("temp"));
	        	
	        	textTitlu.setText(buffer.toString());
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}
