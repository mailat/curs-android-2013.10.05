package com.androider.demo08_xml;

import java.io.StringReader;
import java.util.ArrayList;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class XMLActivity extends ListActivity {

	ArrayList<String> valuesAvailable = new ArrayList<String>(0);
	ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//seteaza titlul activitatii
		setTitle("Mesajele din Twitter via XML.");		
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valuesAvailable);
		setListAdapter(adapter);			
		
		geTwitterTimeline("https://api.twitter.com/1/statuses/user_timeline/ed.xml");
		//http://weather.yahooapis.com/forecastrss?w=868274
	}
	

   public void geTwitterTimeline(String url) {
        new TwitterTimelineReader().execute(url);
    }
    
    private class TwitterTimelineReader extends AsyncTask<String, Void, Void> {
    	
        private final HttpClient client = new DefaultHttpClient();
        private String response;
        private String error = null;
        private ProgressDialog progressDialog = new ProgressDialog(XMLActivity.this);
        
        protected void onPreExecute() {
        	progressDialog.setMessage("Descarcam mesajele Twitter ...");
        	progressDialog.show();
        }

        protected Void doInBackground(String... urls) {
            try {
                HttpGet httpget = new HttpGet(urls[0]);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                response = client.execute(httpget, responseHandler);
            } catch (Throwable e) {
            	error = e.getMessage();
                cancel(true);
            }
            
            return null;
        }
        
        protected void onPostExecute(Void unused) {
            if (error != null) {
            	Toast toast = Toast.makeText(getApplicationContext(), "Eroare la descarcare: " + error, Toast.LENGTH_LONG);
				toast.show();	
            } else {
            	//adaugam tweeturile in lista de pe ecran
                try {            	
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();  
                    XmlPullParser parser = factory.newPullParser();  
                    parser.setInput( new StringReader( response ) );  

                    int event;
                    while (true) {  
                        event = parser.next();  
                        if (event == XmlPullParser.START_TAG) {  
                            String name = parser.getName();  
                            if (name.equals("status")) {  
                                String text = null;  
                                while (true) {  
                                    event = parser.next();  
                                    if (event == XmlPullParser.START_TAG) {  
                                        name = parser.getName();  
                                        if (name.equals("text")) {  
                                            text = parser.nextText();  
                                        }
                                    } else if (event == XmlPullParser.END_TAG  
                                            && parser.getName().equals("status")){  
                                        break;  
                                    }  
                                }  
                                adapter.add(text);
                            }  
                        } else if (event == XmlPullParser.END_TAG  
                                && parser.getName().equals("statuses"))  
                            break;  
                    }  
        		} catch (Throwable e) {
                	Toast toast = Toast.makeText(getApplicationContext(), "Eroare la descarcare: " + e.getMessage(), Toast.LENGTH_LONG);
    				toast.show();	        			
        		}  
            }
        	//inchidem dialogul 
        	progressDialog.dismiss();
        }
    }
}