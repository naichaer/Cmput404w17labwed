package com.example.hong8.as1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class more_stat_reaction extends Activity {
    public reaction_stat_helper helper = new reaction_stat_helper();
    private static final String FILENAME="file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_stat_reaction);

        //load data for reaction time from file
        loadFromFile();

        TextView min = (TextView)findViewById(R.id.min);
        TextView max = (TextView)findViewById(R.id.max);
        TextView average = (TextView)findViewById(R.id.average);
        TextView median = (TextView)findViewById(R.id.median);

        String min_time=helper.getMinTime();
        String tenmin=helper.getXMinTime(5);
        String hundredmin=helper.getXMinTime(100);
        min.setText("Overall:               "+min_time +"\n"+"last 10 times:     "+tenmin+"\n"+"last 100 times:   "+hundredmin);

        String max_time=helper.getMaxTime();
        String tenmax=helper.getXMaxTime(5);
        String hundredmax=helper.getXMaxTime(100);
        max.setText("Overall:               "+max_time +"\n"+"last 10 times:     "+tenmax+"\n"+"last 100 times:   "+hundredmax);

        String avg=helper.getAvgTime();
        String tenavg=helper.getXAvgTime(5);
        String hundredavg=helper.getXAvgTime(100);
        average.setText("Overall:               "+avg +"\n"+"last 10 times:     "+tenavg+"\n"+"last 100 times:   "+hundredavg);

        String med=helper.getMedTime();
        String tenmed=helper.getXMedTime(5);
        String hundredmed=helper.getXMedTime(100);
        median.setText("Overall:             "+med +"\n"+"last 10 times:     "+tenmed+"\n"+"last 100 times:     "+hundredmed);

        //email reaction time
        Button email = (Button) findViewById(R.id.email_result);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(more_stat_reaction.this, send_emails.class);
                //send minmum data to email activity
                intent.putExtra("email_record", "" + helper.getMinTime());
                startActivity(intent);
            }
        });
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            while (line != null) {
                line = line.trim();
                int time = Integer.valueOf(line);
                //add time to helper arraylist
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
        getMenuInflater().inflate(R.menu.menu_more_stat_reaction, menu);
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
