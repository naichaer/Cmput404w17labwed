package com.example.hong8.as1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {
    public static final String FILENAME="file.sav";
    public static final String FILENAME2="file2.sav";
    public static final String FILENAME3="file3.sav";
    public static final String FILENAME4="file4.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //when press the "reaction_time" button,turn to that activity
        Button reaction_time= (Button)findViewById(R.id.SelectReactionTime);
        reaction_time.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, inf_reaction_time.class);
                startActivity(intent);
            }
        });

        //when press the "game buzzer" button ,turn to that activity
        Button game_buzzer= (Button)findViewById(R.id.SelectGameBuzzer);
        game_buzzer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, inf_game_buzzer.class);
                startActivity(intent);
            }
        });

        //check statistics for reaction time
        Button more_stat = (Button) findViewById(R.id.ReactionTimeStat);
        more_stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, more_stat_reaction.class);
                startActivity(intent);
            }
        });

        //delete statistics for reaction time
        Button clear = (Button) findViewById(R.id.Cleardata);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ask the user if he really wants to delete
                delete_data_dialog();

            }
        });
    }

    public void delete_data_dialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setMessage("Confirm to clear data?");

        //if the user actually wants to delete data
        dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clear_data();
            }
        });
        dialog.setPositiveButton("No", null);
        dialog.create();
        dialog.show();
    }

    public void clear_data(){
        try {
            //delete that file to delete data inside it
            File dir = getFilesDir();
            File file = new File(dir, FILENAME);
            Boolean deleted = file.delete();

            File dir2 = getFilesDir();
            File file2 = new File(dir2, FILENAME2);
            Boolean deleted2 = file2.delete();

            File dir3 = getFilesDir();
            File file3 = new File(dir3, FILENAME3);
            Boolean deleted3 = file3.delete();

            File dir4 = getFilesDir();
            File file4 = new File(dir4, FILENAME4);
            Boolean deleted4 = file4.delete();

            if (deleted && deleted2 && deleted3 && deleted4){
                Toast.makeText(MainActivity.this, "Data cleared", Toast.LENGTH_SHORT).show();
            }

        }catch (RuntimeException e){
            Toast.makeText(MainActivity.this, "Exception", Toast.LENGTH_SHORT).show();

        }}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
