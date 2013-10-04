package com.hello.brasov;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("MARUS", "onCreate");
	}
	
	public void redirectClick (View v)
	{
		//redirectare automat catre a doua activitate
		Intent intent = new Intent(MainActivity.this, SecondActivity.class);
		startActivity(intent);
	}
	
	@Override
	protected void onDestroy() {
		Log.d("MARUS", "onDestroy");
		
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		Log.d("MARUS", "onPause");
		
		super.onPause();
	}

	@Override
	protected void onRestart() {
		Log.d("MARUS", "onRestart");
		
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.d("MARUS", "onResume");
		
		super.onResume();
	}

	@Override
	protected void onStart() {
		Log.d("MARUS", "onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {
		Log.d("MARUS", "onStop");
		super.onStop();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

}
