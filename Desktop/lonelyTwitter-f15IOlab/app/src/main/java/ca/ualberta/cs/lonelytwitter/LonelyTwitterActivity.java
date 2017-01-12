package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav"; //model
	private LonelyTwitterActivity activity= this;
	private EditText bodyText;    //view
	private ArrayList<Tweet> tweets= new ArrayList<Tweet>(); //model
	private ListView oldTweetsList;      //view
	private Date date;                   //model
	private String text;                 //model
	private ArrayAdapter<Tweet> adapter; //view
	private Button saveButton;

	public ListView getOldTweetsList() {
		return oldTweetsList;
	}

	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	public EditText getBodyText() {
		return bodyText;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);   //controller
		setContentView(R.layout.main);        //controller

		bodyText = (EditText) findViewById(R.id.body);   //view
		saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);  //view

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK); //controller
				String text = bodyText.getText().toString(); //model
				tweets.add(new NormalTweet(text)); //controller
				saveInFile(); //model
				//dataObject.saveInFile()   -- controller

				adapter.notifyDataSetChanged(); //view

			}
		});

		Button clear = (Button) findViewById(R.id.clear);
		clear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) { //controller
				setResult(RESULT_OK);   //controlelr
				clearFile();  //model
				adapter.notifyDataSetChanged(); // view

			}
		});

		oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent  = new Intent(activity ,EditTweetActivity.class);
				startActivity(intent);
			}
		});

	}

	@Override
	protected void onStart(){
		// TODO Auto-generated method stub
		super.onStart();   //controller
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets); //view
		oldTweetsList.setAdapter(adapter);   //controller
		adapter.notifyDataSetChanged();      //view


	}

	private void loadFromFile() throws TweetTooLongException  {
		tweets = new ArrayList<Tweet>();
		try {
			FileInputStream fis = openFileInput(FILENAME);  //controller
			BufferedReader in = new BufferedReader(new InputStreamReader(fis)); //model
			Gson gson = new Gson();  //model
			Type arraylistType = new TypeToken<ArrayList<NormalTweet>>(){}.getType(); //model
			tweets=gson.fromJson(in,arraylistType); //model

			String line = in.readLine(); //model
			while (line != null) {
				tweets.add(new NormalTweet(line)); //controller
				line = in.readLine();  // model
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block\
			tweets=new ArrayList<Tweet>();
			throw new RuntimeException(e); //controller
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e); //controller
		}

	}
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0);  //controller
			BufferedWriter out =new BufferedWriter(new OutputStreamWriter(fos)); //model

			Gson gson = new Gson(); //model
			gson.toJson(tweets, out); //model
			out.flush(); //con
			fos.close(); //con

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public  void clearFile(){

		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0);
			BufferedWriter out =new BufferedWriter(new OutputStreamWriter(fos));
			Gson gson = new Gson();

			Type arraylistType = new TypeToken<ArrayList<NormalTweet>>() {
			}.getType();

			tweets.clear();
			gson.toJson(tweets, out);
			out.flush();
			fos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}


	}


	public void Date(){
		date=this.date;
	}




}