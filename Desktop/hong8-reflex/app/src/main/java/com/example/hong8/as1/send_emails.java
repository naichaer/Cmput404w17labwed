package com.example.hong8.as1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class send_emails extends Activity {
    public EditText recipient;
    public EditText subject;
    public EditText content;
    public Button send;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_emails);

        recipient = (EditText) findViewById(R.id.recipient);
        subject =(EditText) findViewById(R.id.subject);
        content =(EditText) findViewById(R.id.content);
        send = (Button) findViewById(R.id.send_email);

        Intent intent = getIntent();
        String result = intent.getStringExtra("email_record");

        content.setText("I got a record of "+result+ " ms when playing the game As1!");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get emial inf.
                send_email();
                recipient.setText("");
                subject.setText("");
                subject.setText("");
            }
        });
    }

    public void send_email(){

        Intent email= new Intent(Intent.ACTION_SEND, Uri.parse("mailTo"));
        email.setType("text/plain");
        //send recipent,content to email app
        email.putExtra(Intent.EXTRA_EMAIL, new String [] {recipient.getText().toString()});
        email.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
        email.putExtra(Intent.EXTRA_TEXT,content.getText().toString());

        try{
            startActivity(email);

        }catch (android.content.ActivityNotFoundException ex){
            //if no supported app
            Toast.makeText(send_emails.this, "No email client found",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_emails, menu);
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
