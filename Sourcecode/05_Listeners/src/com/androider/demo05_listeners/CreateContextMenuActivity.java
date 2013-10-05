package com.androider.demo05_listeners;

import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ArrayAdapter;

public class CreateContextMenuActivity extends ListActivity {
	
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		//seteaza titlul activitatii
		setTitle("Apasa lung pe un element din lista.");		

		//initializeaza o lista de test
		List<String> valuesAvailable  = new ArrayList<String>();
		valuesAvailable.add("elementul 1");
		valuesAvailable.add("elementul 2");
		valuesAvailable.add("elementul 3");
		valuesAvailable.add("elementul 4");
		valuesAvailable.add("elementul 5");

		//adauga adapterul in lista
		setListAdapter( new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, valuesAvailable ) );
		
	    //reactioneaza la onCreateContextMenu prin interfata OnCreateContextMenuListener
		getListView().setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
		    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		        menu.setHeaderTitle("Optiuni contextuale");
		        menu.add(0, 101, 1, "Sterge elementul");
		        menu.add(0, 102, 0, "Editeaza elementul");
		    }
		});

	}
}
