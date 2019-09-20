package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {


    EditText cityName;

    TextView resultTextView;

    String encodedCityName;

    public void findWeather(View view) {

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mgr.hideSoftInputFromWindow(cityName.getWindowToken(), 0);

        /*try {

            encodedCityName = URLEncoder.encode(cityName.getText().toString(), "UTf-8");

        } catch (UnsupportedEncodingException e) {

            Toast.makeText(getApplicationContext(), "Could not find weather or city!", Toast.LENGTH_LONG).show();

        }*/

        DownloadTask task = new DownloadTask();

        task.execute("https://api.openweathermap.org/data/2.5/weather?q="+cityName.getText().toString()+"&appid=257cc775b724dd8315d6214f6d4e5b62");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = (EditText) findViewById(R.id.cityName);

        resultTextView=(TextView) findViewById(R.id.resultTextView);
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection=null;

            try {

                url = new URL(urls[0]);
                urlConnection =(HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);

                int data= reader.read();

                while (data != -1) {

                    char current=(char) data;

                    result += current;

                    data =reader.read();

                }

                return result;

            } catch (Exception e) {

                Toast.makeText(getApplicationContext(), "Could not find weather or city!", Toast.LENGTH_LONG).show();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                String msg = "";

                JSONObject jsonObject = new JSONObject(result);

                String weatherInfo = jsonObject.getString("weather");

                Log.i("Weather content",weatherInfo);

                JSONArray arr = new JSONArray(weatherInfo);

                for (int i = 0; i < arr.length(); i++) {

                    JSONObject jsonPart = arr.getJSONObject(i);

                    String main = "";
                    String description = "";

                    main = jsonPart.getString("main");
                    description = jsonPart.getString("description");

                    if (main != "" && description != "") {

                        msg += main + " : " + description + "\r\n";

                    }
                }

                if (msg != "") {

                    resultTextView.setText(msg);

                } else {

                    Toast.makeText(getApplicationContext(), "Could not find weather or city!", Toast.LENGTH_LONG).show();

                }


            } catch (JSONException e) {

                Toast.makeText(getApplicationContext(), "Could not find weather or city!", Toast.LENGTH_LONG).show();

            }
        }
    }
}
