package com.example.dell.dice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.dice.R;

public class MainActivity extends AppCompatActivity {

    Button roll,reset,hold;
    ImageView img_dice;
    TextView game_status;
    int user_overall_score,user_turn_score,computer_overall_score,computer_turn_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_dice=(ImageView)findViewById(R.id.dice);
        game_status=(TextView)findViewById(R.id.textview_gamestatus);
        user_overall_score=0;
        user_turn_score=0;
        computer_overall_score=0;
        computer_turn_score=0;

        roll=(Button)findViewById(R.id.Roll);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int max=6;
                int min=1;

                int random_dice_value=rollDice();



                switch (random_dice_value)
                {
                    case 1:
                        user_turn_score=0;
                        break;
                    case 2:
                        user_turn_score+=2;
                        break;
                    case 3:
                        user_turn_score+=3;
                        break;
                    case 4:
                        user_turn_score+=4;
                        break;
                    case 5:
                        user_turn_score+=5;
                        break;
                    case 6:
                        user_turn_score+=6;
                        break;


                }
                game_status.setText("Your turn score: "+user_turn_score +"Computer Score:"+ computer_overall_score);

                if(random_dice_value==0)
                {
                    computer_turn_score=0;
                    user_overall_score+=user_turn_score;
                    game_status.setText("Your score: "+user_overall_score +"Computer Score:"+ computer_overall_score);
                    ComputerTurn();
                }
            }
        });
        hold=(Button)findViewById(R.id.Hold);


        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_overall_score+=user_turn_score;
                user_turn_score=0;
                game_status.setText("Your Score:"+user_overall_score+"Computer Score:"+computer_overall_score);
                computer_turn_score=0;
                ComputerTurn();;
            }
        });
        reset=(Button)findViewById(R.id.Reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Yogita","Inside Hold");
                user_overall_score=0;
                user_turn_score=0;
                computer_overall_score=0;
                computer_turn_score=0;
                game_status.setText("Your Score: 0 Computer Score: 0");

            }
        });

    }

    public int rollDice()
    {
        int max=6;
        int min=1;
        int random_dice_value=min+(int)(Math.random()*((max-min)+1));
        switch (random_dice_value)
        {
            case 1:
                img_dice.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
                break;
            case 2:
                img_dice.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
                break;
            case 3:
                img_dice.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
                break;
            case 4:
                img_dice.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
                break;
            case 5:
                img_dice.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
                break;
            case 6:
                img_dice.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
                break;

        }

        return random_dice_value;
    }

    public void ComputerTurn()
    {
        int random_dice_value=rollDice();

        hold.setEnabled(false);
        roll.setEnabled(false);
        switch(random_dice_value)
        {
            case 1:
                computer_turn_score=0;
                hold.setEnabled(true);
                roll.setEnabled(true);
                break;
            case 2:
                computer_turn_score+=2;
                break;
            case 3:
                computer_turn_score+=3;
                break;
            case 4:
                computer_turn_score+=4;
                break;
            case 5:
                computer_turn_score+=5;
                break;
            case 6:
                computer_turn_score+=6;
                break;
        }
        if(computer_turn_score<20)
        {
            if(computer_turn_score==0)
            {
                hold.setEnabled(true);
                roll.setEnabled(true);
                game_status.setText("Your score:"+user_overall_score+"Computer Score: "+computer_overall_score);
            }
            else{
                game_status.setText("Your score:"+user_overall_score+"Computer Score: "+computer_overall_score+"Computer Turn Score:"+computer_turn_score+"User Turn Score:"+user_turn_score);



                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        ComputerTurn();
                    }
                },2000);




            }
        }
        else
        {
            hold.setEnabled(true);
            roll.setEnabled(true);
            computer_overall_score+=computer_turn_score;
            game_status.setText("Your score:"+user_overall_score+"Computer Score: "+computer_overall_score);
        }

    }
}
