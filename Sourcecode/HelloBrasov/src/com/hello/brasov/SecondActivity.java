package com.hello.brasov;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		Log.d("MARUS", "on start SecondActivity");
		setContentView(R.layout.activity_main);
		
		// facem invizibil butonul
		Button buttonRedirect = (Button) findViewById(R.id.buttonRedirect);
		buttonRedirect.setVisibility(View.GONE);
		
		// schimbam textul in textview
		TextView textTitlu = (TextView) findViewById(R.id.textTitlu);
		textTitlu.setText("A doua activitate");

	}

}
