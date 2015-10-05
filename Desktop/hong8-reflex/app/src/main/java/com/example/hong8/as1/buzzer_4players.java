package com.example.hong8.as1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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

public class buzzer_4players extends ActionBarActivity {

    public int player1_count=0;
    public int player2_count=0;
    public int player3_count=0;
    public int player4_count=0;
    public ArrayList<Integer> counts = new ArrayList<Integer>(Arrays.asList(player1_count, player2_count,player3_count,player4_count));
    game_buzzer gb=new game_buzzer();
    public static final String FILENAME="file4.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzer_4players);

        final ImageButton green1= (ImageButton)findViewById(R.id.buzz_green4_1);
        final ImageButton green2= (ImageButton)findViewById(R.id.buzz_green4_2);
        final ImageButton green3= (ImageButton)findViewById(R.id.buzz_green4_3);
        final ImageButton green4= (ImageButton)findViewById(R.id.buzz_green4_4);
        final ImageButton cgreen1= (ImageButton)findViewById(R.id.buzz_cgreen4_1);
        final ImageButton cgreen2= (ImageButton)findViewById(R.id.buzz_cgreen4_2);
        final ImageButton cgreen3= (ImageButton)findViewById(R.id.buzz_cgreen4_3);
        final ImageButton cgreen4= (ImageButton)findViewById(R.id.buzz_cgreen4_4);
        final TextView player1_buzzes= (TextView) findViewById(R.id.player1_buzzes4);
        final TextView player2_buzzes= (TextView) findViewById(R.id.player2_buzzes4);
        final TextView player3_buzzes= (TextView) findViewById(R.id.player3_buzzes4);
        final TextView player4_buzzes= (TextView) findViewById(R.id.player4_buzzes4);
        final Button correct = (Button)findViewById(R.id.correct4);
        final Button wrong = (Button)findViewById(R.id.wrong4);
        final Button clear = (Button)findViewById(R.id.clear_records4);
        final Button save = (Button)findViewById(R.id.save4);

        final ArrayList<View> players =new ArrayList<View>(Arrays.asList(green1, cgreen1, player1_buzzes, green2, cgreen2, player2_buzzes, green3, cgreen3, player3_buzzes,green4,cgreen4,player4_buzzes));
        final ArrayList<View> evaluation =new ArrayList<View>(Arrays.asList(correct,wrong));

        load_from_file();
        player1_buzzes.setText("" + counts.get(0));
        player2_buzzes.setText("" + counts.get(1));
        player3_buzzes.setText("" + counts.get(2));
        player4_buzzes.setText("" + counts.get(3));

        gb.show_green_buttons(players);

        gb.set_listener(buzzer_4players.this, players, evaluation, counts);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_in_file();
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gb.clear_buzzers(players,counts);
                clear_file();
            }
        });
    }

    public void clear_file(){
        try {
            //delete that file to delete data inside it
            File dir = getFilesDir();
            File file = new File(dir, FILENAME);
            file.delete();

        }catch (RuntimeException e){
            e.printStackTrace();
        }}

    public void load_from_file() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            if(line!= null){
                counts.clear();
            }

            while (line != null) {
                line = line.trim();
                int buzzer = Integer.valueOf(line);
                counts.add(buzzer);
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

    public void save_in_file(){

        try {
            FileOutputStream fos = openFileOutput(FILENAME, MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            for (int i: counts){
                writer.write(i + "\n");
            }
            writer.flush();
            writer.close();

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
        getMenuInflater().inflate(R.menu.menu_buzzer_4players, menu);
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
