package com.brinlee.measurementconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class MainActivity extends AppCompatActivity implements OnEditorActionListener, OnClickListener, AdapterView.OnItemSelectedListener {

    private double conversionDesired;
    private double total;
    private String totalString;
    private String inputEditTextString;
    private double inputString;

    private TextView typeTextView;
    private Spinner conversionSpinner;
    private TextView convertedTextView;
    private TextView convertTextView;
    private TextView convertedDisplayTextView;
    private EditText inputEditText;

    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conversionSpinner = (Spinner) findViewById(R.id.conversionSpinner);
        conversionSpinner.setOnItemSelectedListener(this);

        //adapter for spinner
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.conversion_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conversionSpinner.setAdapter(adapter);

        typeTextView = (TextView) findViewById(R.id.typeTextView);
        convertedTextView = (TextView) findViewById(R.id.convertedTextView);
        convertTextView = (TextView) findViewById(R.id.convertTextView);
        inputEditText = (EditText) findViewById(R.id.inputEditText);
        convertedDisplayTextView = (TextView) findViewById(R.id.convertedDisplayTextView);
        conversionSpinner = (Spinner) findViewById(R.id.conversionSpinner);

        // set array adapter for spinner
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        conversionSpinner.setAdapter(adapter);


        //set Listeners

        inputEditText.setOnEditorActionListener(this);


        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

    }


    protected void Convert(){

        inputEditTextString = inputEditText.getText().toString();
        inputString = Double.parseDouble(inputEditTextString);
        total = inputString * conversionDesired;
        totalString = String.valueOf(total);
        convertedDisplayTextView.setText(totalString);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        int keyCode=-1;
        if (event !=null){
            keyCode= event.getKeyCode();
        }
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED ||
                keyCode == KeyEvent.KEYCODE_DPAD_CENTER ||
                keyCode == KeyEvent.KEYCODE_ENTER) {
            Convert();
        }
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);


        String selectedText = (String) conversionSpinner.getSelectedItem();
        int choice = conversionSpinner.getSelectedItemPosition();
        switch (choice){
            case 0:
                conversionSpinner.setSelection(0);
                convertTextView.setText("Miles");
                convertedTextView.setText("Kilometers");
                conversionDesired = 1.6093;
                break;

            case 1:
                conversionSpinner.setSelection(1);
                convertTextView.setText("Kilometers");
                convertedTextView.setText("Miles");
                conversionDesired = 0.6214;
                Convert();
                break;
            case 2:
                conversionSpinner.setSelection(2);
                convertTextView.setText("Inches");
                convertedTextView.setText("Centimeters");
                conversionDesired = 2.54;
                Convert();
                break;
            case 3:
                conversionSpinner.setSelection(3);
                convertTextView.setText("Centimeters");
                convertedTextView.setText("Inches");
                conversionDesired = 0.3937;
                Convert();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


