package com.example.numberguessing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber;

    public void makeToast(String str) {

        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public void guessNumber(View view) {

        EditText editText=(EditText)findViewById(R.id.editText);

        if(editText.getText().toString().isEmpty()) {

            makeToast("Please enter a number.");
        } else {
            int guessInt=Integer.parseInt(editText.getText().toString());

            if(guessInt > randomNumber) {

                makeToast("Think Lower!");
            } else if(guessInt < randomNumber){

                makeToast("Think Higher!");
            } else {

                makeToast("You guessed correct! Try again!");

                Random rand=new Random();

                randomNumber=rand.nextInt(20)+1;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand=new Random();

        randomNumber=rand.nextInt(20)+1;
    }
}
