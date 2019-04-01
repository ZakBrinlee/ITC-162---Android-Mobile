package com.brinlee.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button gameGrid[][] = new Button[3][3];
    private Button newGame;
    private TextView display;

    private String player ="Zak";

    String tl="";
    String t="";
    String tr="";
    String l="";
    String c="";
    String r="";
    String bl="";
    String b="";
    String br="";
    String p1win="Player 1 wins!";
    String p2win="Player 2 wins!";


    private SharedPreferences savedValues;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameGrid[0][0] = (Button) findViewById(R.id.top_left);
        gameGrid[0][1] = (Button) findViewById(R.id.top);
        gameGrid[0][2] = (Button) findViewById(R.id.top_right);
        gameGrid[1][0] = (Button) findViewById(R.id.left);
        gameGrid[1][1] = (Button) findViewById(R.id.center);
        gameGrid[1][2] = (Button) findViewById(R.id.right);
        gameGrid[2][0] = (Button) findViewById(R.id.bottom_left);
        gameGrid[2][1] = (Button) findViewById(R.id.bottom);
        gameGrid[2][2] = (Button) findViewById(R.id.bottom_right);
        newGame = (Button) findViewById(R.id.newGameButton);

        display = (TextView) findViewById(R.id.displayTextView);
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
        setStartingValues();

    }

    @Override
    protected void onPause(){
        Editor editor = savedValues.edit();

        super.onPause();
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    private void setStartingValues(){
        gameGrid[0][0].setOnClickListener(this);
        gameGrid[0][1].setOnClickListener(this);
        gameGrid[0][2].setOnClickListener(this);
        gameGrid[1][0].setOnClickListener(this);
        gameGrid[1][1].setOnClickListener(this);
        gameGrid[1][2].setOnClickListener(this);
        gameGrid[2][0].setOnClickListener(this);
        gameGrid[2][1].setOnClickListener(this);
        gameGrid[2][2].setOnClickListener(this);
        newGame.setOnClickListener(this);

    }//setStartingValues

    private void clearGrid(){

        //reset Text
        gameGrid[0][0].setText("");
        gameGrid[0][1].setText("");
        gameGrid[0][2].setText("");
        gameGrid[1][0].setText("");
        gameGrid[1][1].setText("");
        gameGrid[1][2].setText("");
        gameGrid[2][0].setText("");
        gameGrid[2][1].setText("");
        gameGrid[2][2].setText("");

        tl="";
        t="";
        tr="";
        l="";
        c="";
        r="";
        bl="";
        b="";
        br="";
        startNewGame();
    }//clearGrid

    private void startNewGame(){

        //enableButtons
        gameGrid[0][0].setEnabled(true);
        gameGrid[0][1].setEnabled(true);
        gameGrid[0][2].setEnabled(true);
        gameGrid[1][0].setEnabled(true);
        gameGrid[1][1].setEnabled(true);
        gameGrid[1][2].setEnabled(true);
        gameGrid[2][0].setEnabled(true);
        gameGrid[2][1].setEnabled(true);
        gameGrid[2][2].setEnabled(true);
        newGame.setEnabled(true);
        //reset Listeners
        gameGrid[0][0].setOnClickListener(this);
        gameGrid[0][1].setOnClickListener(this);
        gameGrid[0][2].setOnClickListener(this);
        gameGrid[1][0].setOnClickListener(this);
        gameGrid[1][1].setOnClickListener(this);
        gameGrid[1][2].setOnClickListener(this);
        gameGrid[2][0].setOnClickListener(this);
        gameGrid[2][1].setOnClickListener(this);
        gameGrid[2][2].setOnClickListener(this);
        newGame.setOnClickListener(this);
        display.setText("Player 1 turn");

    }//startNewGame

    private void checkForGameOver(){

        //across
        if(tl=="z" && t=="z" && tr=="z"){
            display.setText(p1win);
        }
        else if(l=="z" && c=="z" && r=="z"){
            display.setText(p1win);
        }
        else if(bl=="z" && b=="z" && br=="z"){
            display.setText(p1win);
        }
        else if(tl=="b" && t=="b" && tr=="b"){
            display.setText(p2win);
        }
        else if(l=="b" && c=="b" && r=="b"){
            display.setText(p2win);
        }
        else if(bl=="b" && b=="b" && br=="b"){
            display.setText(p2win);
        }

        //Top/Down
        else if(tl=="z" && l=="z" && bl=="z"){
            display.setText(p1win);
        }
        else if(t=="z" && c=="z" && b=="z"){
            display.setText(p1win);
        }
        else if(tr=="z" && r=="z" && br=="z"){
            display.setText(p1win);
        }
        else if(tl=="b" && l=="b" && bl=="b"){
            display.setText(p2win);
        }
        else if(t=="b" && c=="b" && b=="b"){
            display.setText(p2win);
        }
        else if(tr=="b" && r=="b" && br=="b"){
            display.setText(p2win);
        }

        //Diagonal

        else if(tl=="z" && c=="z" && br=="z"){
            display.setText(p1win);
        }
        else if(tr=="z" && c=="z" && bl=="z"){
            display.setText(p1win);
        }
        else if(tl=="b" && c=="b" && br=="b"){
            display.setText(p2win);
        }
        else if(tr=="b" && c=="b" && bl=="b"){
            display.setText(p2win);
        }


        else if(!gameGrid[0][0].isEnabled()
                && !gameGrid[0][1].isEnabled()
                && !gameGrid[0][2].isEnabled()
                && !gameGrid[1][1].isEnabled()
                && !gameGrid[1][2].isEnabled()
                && !gameGrid[2][1].isEnabled()
                && !gameGrid[2][2].isEnabled()
                && !gameGrid[1][0].isEnabled()
                && !gameGrid[2][0].isEnabled()) {
            display.setText("Tie!");
        }
    }//checkForGameOver

    @Override
    public void onClick(View v) {

        if(player=="Zak") {
            switch (v.getId()) {
                case R.id.top_left:
                    gameGrid[0][0].setText("O");
                    display.setText("Player 2 turn");
                    gameGrid[0][0].setEnabled(false);
                    tl="z";
                    break;
                case R.id.top:
                    gameGrid[0][1].setText("O");
                    display.setText("Player 2 turn");
                    gameGrid[0][1].setEnabled(false);
                    t="z";
                    break;
                case R.id.top_right:
                    gameGrid[0][2].setText("O");
                    display.setText("Player 2 turn");
                    gameGrid[0][2].setEnabled(false);
                    tr="z";
                    break;
                case R.id.left:
                    gameGrid[1][0].setText("O");
                    display.setText("Player 2 turn");
                    gameGrid[1][0].setEnabled(false);
                    l="z";
                    break;
                case R.id.center:
                    gameGrid[1][1].setText("O");
                    display.setText("Player 2 turn");
                    gameGrid[1][1].setEnabled(false);
                    c="z";
                    break;
                case R.id.right:
                    gameGrid[1][2].setText("O");
                    display.setText("Player 2 turn");
                    gameGrid[1][2].setEnabled(false);
                    r="z";
                    break;
                case R.id.bottom_left:
                    gameGrid[2][0].setText("O");
                    display.setText("Player 2 turn");
                    gameGrid[2][0].setEnabled(false);
                    bl="z";
                    break;
                case R.id.bottom:
                    gameGrid[2][1].setText("O");
                    display.setText("Player 2 turn");
                    gameGrid[2][1].setEnabled(false);
                    b="z";
                    break;
                case R.id.bottom_right:
                    gameGrid[2][2].setText("O");
                    display.setText("Player 2 turn");
                    gameGrid[2][2].setEnabled(false);
                    br="z";
                    break;
                case R.id.newGameButton:
                    newGame.setEnabled(false);
            }
            player="Bob";
            if(!newGame.isEnabled()){
                clearGrid();
            }
            checkForGameOver();

        }else if(player=="Bob"){

            switch(v.getId()) {
                case R.id.top_left:
                    gameGrid[0][0].setText("X");
                    display.setText("Player 1 turn");
                    gameGrid[0][0].setEnabled(false);
                    tl="b";
                    break;
                case R.id.top:
                    gameGrid[0][1].setText("X");
                    display.setText("Player 1 turn");
                    gameGrid[0][1].setEnabled(false);
                    t="b";
                    break;
                case R.id.top_right:
                    gameGrid[0][2].setText("X");
                    display.setText("Player 1 turn");
                    gameGrid[0][2].setEnabled(false);
                    tr="b";
                    break;
                case R.id.left:
                    gameGrid[1][0].setText("X");
                    display.setText("Player 1 turn");
                    gameGrid[1][0].setEnabled(false);
                    l="b";
                    break;
                case R.id.center:
                    gameGrid[1][1].setText("X");
                    display.setText("Player 1 turn");
                    gameGrid[1][1].setEnabled(false);
                    c="b";
                    break;
                case R.id.right:
                    gameGrid[1][2].setText("X");
                    display.setText("Player 1 turn");
                    gameGrid[1][2].setEnabled(false);
                    r="b";
                    break;
                case R.id.bottom_left:
                    gameGrid[2][0].setText("X");
                    display.setText("Player 1 turn");
                    gameGrid[2][0].setEnabled(false);
                    bl="b";
                    break;
                case R.id.bottom:
                    gameGrid[2][1].setText("X");
                    display.setText("Player 1 turn");
                    gameGrid[2][1].setEnabled(false);
                    b="b";
                    break;
                case R.id.bottom_right:
                    gameGrid[2][2].setText("X");
                    display.setText("Player 1 turn");
                    gameGrid[2][2].setEnabled(false);
                    br="b";
                    break;
                case R.id.newGameButton:
                    newGame.setEnabled(false);
            }
            player = "Zak";
            if(!newGame.isEnabled()){
                clearGrid();
            }
            checkForGameOver();

        }
    }//onClick
}//end main
