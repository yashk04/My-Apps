package com.example.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class Number {

        int number;

        public boolean isSquare() {

            double squareRoot = Math.sqrt(number);

            if(squareRoot == Math.floor(squareRoot)) {

                return true;
            } else {

                return false;
            }
        }

        public boolean isTriangular() {

            int x=1;
            int triangularNumber=1;
            while (triangularNumber < number) {

                x++;
                triangularNumber = triangularNumber + x;
            }

            if(triangularNumber == number) {

                return true;
            } else {

                return false;
            }
        }
    }

    public void testNum(View view) {

        EditText editText = (EditText)findViewById(R.id.editText2);

        String msg="";

        if(editText.getText().toString().isEmpty()) {

            msg="Please enter a number";
        } else {

            Number myNumber = new Number();

            myNumber.number=Integer.parseInt(editText.getText().toString());

            if(myNumber.isSquare()) {

                if(myNumber.isTriangular()) {

                    msg = myNumber.number + " is both Triangular and Square!";

                } else {

                    msg=myNumber.number + " is Square, but not Triangular.";
                }
            } else {

                if(myNumber.isTriangular()) {
                    msg=myNumber.number+" is Triangular, but not Square.";
                } else {
                    msg=myNumber.number + " is neither Square nor Triangular.";
                }
            }
        }

        Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
        /*System.out.println(myNumber.isTriangular());
        System.out.println(myNumber.isSquare());*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
