package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0=big dog, 1=small dog

    int activePlayer=0;

    //2 means unplayed

    int[] gameState={2,2,2,2,2,2,2,2,2};

    boolean gameIsActive=true;

    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {

        ImageView counter=(ImageView)view;

        int tappedCounter=Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameIsActive) {

            gameState[tappedCounter]=activePlayer;

            counter.setTranslationY(-1000f);

            if(activePlayer==0) {

                counter.setImageResource(R.drawable.dog2);

                activePlayer=1;

            } else {

                counter.setImageResource(R.drawable.dog1);

                activePlayer=0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPosition :winningPositions) {

                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]] &&
                            gameState[winningPosition[0]]!=2) {

                    gameIsActive=false;

                    String winner="Small dog";

                    if (gameState[winningPosition[0]]==0) {

                        winner="Big dog";
                    }

                    TextView winnerMessage=(TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner+ " has won!");

                    LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);
                } else {

                    boolean gameIsOver=true;

                    for (int counterState : gameState) {

                        if(counterState==2)
                            gameIsOver=false;

                    }

                    if(gameIsOver) {

                        TextView winnerMessage=(TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a draw.");

                        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                    }

                }
            }
        }
    }

    public void playAgain(View view) {

        gameIsActive=true;

        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i=0;i<gameState.length;i++) {

            gameState[i]=2;
        }

        GridLayout gridLayout1=(GridLayout)findViewById(R.id.gridLayout1);

        for(int i=0;i<gridLayout1.getChildCount();i++) {

            ((ImageView) gridLayout1.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
