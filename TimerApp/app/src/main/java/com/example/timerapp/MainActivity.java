package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Button controllerButton;
    Boolean counterIsActive=false;

    CountDownTimer countDownTimer;

    public void updateTimer(int progress) {

        int minutes =(int) progress/60;

        int seconds =progress-minutes*60;

        String secondString =Integer.toString(seconds);

        if (seconds <= 9){

            secondString="0"+secondString;

        }

        timerTextView.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
    }

    public void resetTimer() {

        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);

        countDownTimer.cancel();

        controllerButton.setText("GO!");
        timerSeekBar.setEnabled(true);

        counterIsActive=false;
    }

    public void controlTimer(View view) {

        if(counterIsActive == false) {

            controllerButton.setText("STOP");
            counterIsActive=true;

            timerSeekBar.setEnabled(false);

            countDownTimer=new CountDownTimer(timerSeekBar.getProgress() * 1000+100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished/1000);
                }

                @Override
                public void onFinish() {

                    timerTextView.setText("0:00");

                    MediaPlayer mPlayer=MediaPlayer.create(getApplicationContext(),R.raw.sound);

                    mPlayer.start();

                    resetTimer();

                }
            }.start();
        } else {
                    resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar)findViewById(R.id.timerSeekBar);

        timerTextView =(TextView)findViewById(R.id.timerTextView);

        controllerButton=(Button)findViewById(R.id.controllerButton);

        timerSeekBar.setMax(600);

        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
