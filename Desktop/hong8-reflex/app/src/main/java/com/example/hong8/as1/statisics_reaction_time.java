package com.example.hong8.as1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class statisics_reaction_time extends Activity {
    reaction_stat_helper helper= new reaction_stat_helper();
    public static final String FILENAME="file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statisics_reaction_time);

        //load data for reaction time from file
        loadFromFile();

        Button more_stat=(Button)findViewById(R.id.more_statistics);
        Button restart= (Button) findViewById(R.id.restart_reaction);
        Button quit= (Button)findViewById(R.id.quit_reaction);

        //turn to next activity(restart, quit, get more statistics)
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(statisics_reaction_time.this, Test_Reaction_Time.class);
                startActivity(intent);
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent = new Intent(statisics_reaction_time.this, MainActivity.class);
                startActivity(intent);
            }
        });

        more_stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(statisics_reaction_time.this, more_stat_reaction.class);
                startActivity(intent);
            }
        });

        //receive data from game and display reaction time
        final Intent intent = getIntent();
        String single_result = intent.getStringExtra("single_result");

        TextView single_time = (TextView) findViewById(R.id.result);
        single_time.setText(single_result + "ms");

        //helper.load_from_file();

        //get data from shared application
        //display best reaction time
        TextView best=(TextView)findViewById(R.id.best);
        String best_time = helper.getMinTime();
        best.setText("Best reaction time: " + best_time);

        //get data from shared application
        //show the number of times the user has played
        TextView counts= (TextView) findViewById(R.id.count);
        Integer count= helper.getCount();
        counts.setText("You have played for " + count + " times");

    }

    public void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();

            //read file until it reaches end
            while (line != null) {
                //remove \n
                line = line.trim();
                int time = Integer.valueOf(line);
                //add time to reaction time arrays in data helper
                helper.add_reaction_time(time);
                line = in.readLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statisics_reaction_time, menu);
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
