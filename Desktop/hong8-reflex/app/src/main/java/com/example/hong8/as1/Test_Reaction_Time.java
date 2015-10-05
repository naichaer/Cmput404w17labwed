package com.example.hong8.as1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Test_Reaction_Time extends Activity {
    private long reaction_time;

    public ImageButton red;
    public ImageButton green1;
    public ImageButton green2;
    public ArrayList<View> player = new ArrayList<View>((Arrays.asList(red,green1,green2)));
    public Button check_time;

    visibility_controller vc =new visibility_controller();
    time_controller tc= new time_controller();

    public static final String FILENAME="file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__reaction__time);

        red = (ImageButton) findViewById(R.id.reaction_red);
        green1 = (ImageButton) findViewById(R.id.reaction_green1);
        green2 = (ImageButton) findViewById(R.id.reaction_green2);
        check_time = (Button) findViewById(R.id.check_reaction_time_button);

        final ArrayList<View> player = new ArrayList<View>((Arrays.asList(red,green1,green2)));
        //at the beginning of the game, make only the red button visible

        vc.remain_red(player);
        check_time.setVisibility(View.INVISIBLE);

        //if the user press the button too early before the green button appears
        //turn to too early activity
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Test_Reaction_Time.this, too_early.class);
                startActivity(intent);
            }
        });

        //schedule a timer task that change the red button into a green one
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        vc.turn_green(player);
                    }
                });
            }
        };

        try {
            //use method in class "time_controller"
            //start timer and return start time
            final long start_time=tc.set_timer(task);

            //once the user presses the green button
            green1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //use method in class "time_controller"
                    //record current time and compute reaction time
                    reaction_time= tc.compute_time(start_time);

                    //let the user knows that he succeeds to press the button
                    vc.turn_greenNtick(player,1);
                    check_time.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception e) {
            System.out.print("Exception!");
        }

        //turn to next activity(check statistics of reaction time)
        check_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //write data into file
                save_in_file(Long.toString(reaction_time));

                //send data to statisics_reaction_time
                Intent intent = new Intent(Test_Reaction_Time.this, statisics_reaction_time.class);
                intent.putExtra("single_result", ""+reaction_time);
                startActivity(intent);
            }
        });
    }

    public void save_in_file(String reaction_time){
        try {
            FileOutputStream fos = openFileOutput(FILENAME,MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(reaction_time+"\n");
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Toast.makeText(Test_Reaction_Time.this, "file not found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Toast.makeText(Test_Reaction_Time.this, "IOException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test__reaction__time, menu);
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
