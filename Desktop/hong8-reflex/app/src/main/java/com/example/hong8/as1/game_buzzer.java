package com.example.hong8.as1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class game_buzzer extends Activity {

    public Boolean not_pressed = true;
    public int winner=5;
    final visibility_controller vc= new visibility_controller();

    public void show_green_buttons(ArrayList<View> players){
        //at the beginning of the game, make only red buttons visible
        vc.remain_green(players);
    }

    public void set_listener(Context context1, final ArrayList<View> player,ArrayList<View> evaluations, ArrayList<Integer>count) {

        //Views in Arraylist player is ordered according to players
        //index/3=0 -> player1    index/3= 1 ->player2 ......
        //index%3=0 -> green button   index%3=1 ->green button with tick   index%3=2 ->player buzzes textview
        final ArrayList<View> players = player;

        //element with index 0 in Arraylist evaluations is button "correct", and index 1 is button "wrong"
        final ArrayList<View> evaluation = evaluations;

        final ArrayList<Integer> counts =count;

        //records which activity that this method is used in.
        final Context context=context1;

        //use for loop find all index of green buttons
        for(int index=0; index < players.size(); index=index+3){
            final int i=index;

            //set listener on all green buttons
            players.get(i).setOnClickListener(new View.OnClickListener() {

                Boolean not_evaluated = true;
                @Override
                public void onClick(View v) {
                    not_evaluated = true;

                    //if no player has pressed the green button
                    if (not_pressed){

                        //turns to the button with tick on it
                        vc.turn_greenNtick(players,i);

                        //shows the buzzer
                        winner= i/3+1;
                        Toast.makeText(context, "Player" + winner + " buzzes", Toast.LENGTH_SHORT).show();

                        //correct button listener
                        (evaluation.get(0)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //if the hoster has not evaluated the correctness of the player's answer
                                if (not_evaluated) {

                                    //add 1 to the buzzer's count
                                    int count = counts.get(i / 3) + 1;
                                    counts.set(i / 3, count);
                                    ((TextView) players.get(i + 2)).setText("" + count);

                                    //back to green button
                                    vc.back_to_green(players, i);
                                    not_evaluated = false;
                                }
                                not_pressed = true;
                            }
                        });

                        //wrong button listener
                        (evaluation.get(1)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //if the hoster has not evaluated the correctness of the player's answer
                                if(not_evaluated){

                                    //minus 1 to the buzzer's count
                                    int count = counts.get(i / 3) - 1;
                                    counts.set(i / 3, count);
                                    ((TextView) players.get(i + 2)).setText("" + count);

                                    //back to green button
                                    vc.back_to_green(players, i);
                                    not_evaluated=false;
                                }
                                not_pressed=true;
                            }

                        });
                    }

                    //start new buzzer
                    not_pressed=false;
                }
            });
        }

    }

    public void clear_buzzers(ArrayList<View> players , ArrayList<Integer> counts){
        for(int index=0; index< players.size();index=index+3){
            counts.set(index/3, 0);
            ((TextView) players.get(index + 2)).setText("" + 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_buzzer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_buzzer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
