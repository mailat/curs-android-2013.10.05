package com.androider.demo05_listeners;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ListenersActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //testam metoda onClick
        Button buttonTest1 = (Button)findViewById(R.id.buttonTest1);
        buttonTest1.setOnClickListener(eventOnClickListener);       
        
        //testam metoda onLongClick
        Button buttonTest2 = (Button)findViewById(R.id.buttonTest2);
        buttonTest2.setLongClickable(true);
        buttonTest2.setOnLongClickListener(eventOnLongClickListener);
        
        //testam metoda onFocusChange
        EditText editTest3 = (EditText)findViewById(R.id.editTest3);
        editTest3.setOnFocusChangeListener(eventOnFocusClickListener);           
        
        //testam monitorizarea modificarilor in un EditText
		EditText editTest4 = (EditText) findViewById(R.id.editTest4);						
		editTest4.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
             }
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }
             public void onTextChanged(CharSequence s, int start, int before, int count) {
					TextView textViewTest5 = (TextView) findViewById(R.id.textViewTest5);	
					textViewTest5.setText("Valoarea live: " + s.toString());
             }
		});
             
    }
    
    //reactioneaza la onClick prin interfata OnClickListener
    private OnClickListener eventOnClickListener = new OnClickListener() {
    	public void onClick(View v) {
    		Toast.makeText(ListenersActivity.this, "onClick a fost apasat", Toast.LENGTH_SHORT).show();
    	}    	
    };    
    
    //reactioneaza la onLongClick prin interfata OnLongClickListener
    private OnLongClickListener eventOnLongClickListener = new OnLongClickListener() {
    	public boolean onLongClick(View v) {
    		Toast.makeText(ListenersActivity.this, "onLongClick a fost apasat", Toast.LENGTH_SHORT).show();
			return true;
    	}    	
    }; 
    
    //reactioneaza la onFocusChange prin interfata OnFocusChangeistener
    private OnFocusChangeListener eventOnFocusClickListener = new OnFocusChangeListener() {
		@Override
		public void onFocusChange(View arg0, boolean arg1) {
			Toast.makeText(ListenersActivity.this, "onFocusChange a fost activat", Toast.LENGTH_SHORT).show();
		}    	
    };     

    //redirecteaza catre activitatea de testare
	public void reditectListViewActivity(View view) {
		//pentru a testa onCreateContextMenu folosim o activitate noua CreateContextMenuActivity
		Intent intent = new Intent(ListenersActivity.this, CreateContextMenuActivity.class);
		startActivity(intent);
	}
	
}