package com.murach.tempconverter;


import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.view.inputmethod.EditorInfo;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.text.DecimalFormat;

public class TempConverterActivity extends Activity implements TextView.OnEditorActionListener
{
	//Widget variables
	private EditText fahrenheitEditText;
	private TextView celsiusTextView;

	//shared preferences
	private SharedPreferences savedValues;

	//Variables to be saved
	private String fahrenheitDegreeString = "";
	private float celsiusDegrees;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temp_converter);

		//get references to widgets
		fahrenheitEditText = (EditText)findViewById(R.id.fahrenheitEditText);
		celsiusTextView = (TextView)findViewById(R.id.celciusTextView);

		//set listeners
		fahrenheitEditText.setOnEditorActionListener(this);

		//Shared preferences
		savedValues = getSharedPreferences("savedValues", MODE_PRIVATE);
	}//end of onCreate

	@Override
	public void onPause()
	{
		//save instance variables
		Editor ed = savedValues.edit();
		ed.putString("fahrenheitDegreeString", fahrenheitEditText.getText().toString());
		ed.putFloat("celsiusDegrees", celsiusDegrees);
		ed.commit();
		super.onPause();
	}//end of onPause

	@Override
	public void onResume()
	{
		super.onResume();

		//instance variables
		fahrenheitEditText.setText(savedValues.getString("fahrenheitDegreeString", "0"));
		celsiusDegrees = savedValues.getFloat("celsiusDegrees", celsiusDegrees);

		//set celsius
		celsiusTextView.setText(fahrenheitDegreeString);

		//call calculateAndDisplay method
		calculateAndDisplay();
	}//end of onResume

	public void calculateAndDisplay()
	{
		//fahrenheit amount
		fahrenheitDegreeString = fahrenheitEditText.getText().toString();
		float fahrenheitDegrees = Float.parseFloat(fahrenheitDegreeString);

		//celsius calculation
		celsiusDegrees = ((fahrenheitDegrees - 32) * (5f/9f));
		DecimalFormat df = new DecimalFormat("#.##");
		celsiusTextView.setText(df.format(celsiusDegrees));
	}//end of calculateAndDisplay

	@Override
	public boolean onEditorAction(TextView tv, int actionId, KeyEvent event)
	{
		if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED)
		{
			calculateAndDisplay();
		}//end of if

		return false;
	}//end of onEditorAction

}//End of class