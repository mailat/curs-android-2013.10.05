package com.hello.brasov;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		Log.d("MARUS", "on start SecondActivity");
		setContentView(R.layout.activity_second);
		
		// schimbam textul in textview cu cel transmis
		Intent intent = getIntent();
		String oras = intent.getStringExtra("oras");
		
		TextView textTitlu = (TextView) findViewById(R.id.textTitlu);
		textTitlu.setText(oras);
	}

}
