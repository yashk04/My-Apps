package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert(View view) {

        EditText rsEditText=(EditText) findViewById(R.id.editText);
        if(rsEditText.getText().toString().isEmpty()) {

            Toast.makeText(this, "Please enter an amount.", Toast.LENGTH_SHORT).show();
        } else {

            Double rsAmount=Double.parseDouble(rsEditText.getText().toString());
            Double dollarAmount=rsAmount*0.014;
            Toast.makeText(this, dollarAmount.toString()+"$", Toast.LENGTH_SHORT).show();
        }
    }
}
