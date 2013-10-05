package com.androider.demo05_dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class DialogsActivity extends Activity {

	private static final int DIALOG_TYPE_ALERT_SIMPLE = 101;
	private static final int DIALOG_TYPE_ALERT_COMPLEX = 102;
	private static final int DIALOG_TYPE_DATE = 104;
	private static final int DIALOG_TYPE_TIME = 105;
	
/** Called when the activity is first created. */


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	public void alertDialogSimpleClick(View view) {
		showDialog(DIALOG_TYPE_ALERT_SIMPLE);
	}
	
	public void alertDialogComplexClick(View view) {
		showDialog(DIALOG_TYPE_ALERT_COMPLEX);
	}
	
	public void progressDialogClick(View view) {
		// creeaza ProgressDialog-ul
		final ProgressDialog progressDialog;
		progressDialog = new ProgressDialog(DialogsActivity.this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setIndeterminate(true);
		progressDialog.setMessage("Incarcam datele ...");
		progressDialog.setCancelable(true);
		progressDialog.show();
		
		//inchidem dupa un anumit timp dialogul de incarcare
		new Thread() {
			public void run() {
				try
				{
					sleep(3000);
				} catch (Exception e) {
				}
				progressDialog.dismiss();
			}
		}.start();
		
	}
	
	public void datePickerDialogClick(View view) {
		showDialog(DIALOG_TYPE_DATE);
	}
	
	public void timePickerDialogClick(View view) {
		showDialog(DIALOG_TYPE_TIME);
	}	

	@Override
	protected Dialog onCreateDialog(int id,Bundle args) {
		switch (id) {
		case DIALOG_TYPE_ALERT_SIMPLE:
			// creeaza AlterDialog-ul simplu cu optiunea da/nu
			Builder builderSimple = new AlertDialog.Builder(this);
			builderSimple.setMessage("Mesaj ce poate fi modificat");
			builderSimple.setCancelable(true);
			builderSimple.setPositiveButton("Da", new exempluOnClickListener());
			builderSimple.setNegativeButton("Nu", new exempluOnClickListener());
			AlertDialog dialogSimple = builderSimple.create();
			dialogSimple.show();
			break;
			
		case DIALOG_TYPE_ALERT_COMPLEX:
			// creeaza AlterDialog-ul simplu cu o lista de optiuni
			final CharSequence[] items = {"Berlin", "Londra", "Paris"};
			Builder builderComplex = new AlertDialog.Builder(this);
			builderComplex.setTitle("Unde doresti sa zbori?");
			builderComplex.setItems(items, new exempluOnClickListener() );
			AlertDialog dialogComplex = builderComplex.create();
			dialogComplex.show();		
			break;
			
		case DIALOG_TYPE_DATE:
			// creeaza dialogul DatePicker			
			return ( new DatePickerDialog(this, datePickerListener, 2012, 12, 3));
			
		case DIALOG_TYPE_TIME:
			// creeaza dialogul DatePicker			
			return ( new TimePickerDialog(this, timePickerListener, 12, 7, false));			
		}		
		return super.onCreateDialog(id);
	}

	private final class exempluOnClickListener implements DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			Toast.makeText(getApplicationContext(), "Ai apasat pe un buton sau pe un choice." , Toast.LENGTH_LONG).show();
		}
	}
	
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		//atunci cand dialogul se inchide se cheama metoda de mai jos
		//adauga ziua in edit text
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			EditText editData = (EditText) findViewById(R.id.editData);
			editData.setText(new StringBuilder().append(selectedYear + 1).append(".").append(selectedMonth).append(".").append(selectedDay));
		}
	};	
	
	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			EditText editOra = (EditText) findViewById(R.id.editOra);
			editOra.setText(new StringBuilder().append(hourOfDay).append(":").append(minute));

        }
    };
		    
}