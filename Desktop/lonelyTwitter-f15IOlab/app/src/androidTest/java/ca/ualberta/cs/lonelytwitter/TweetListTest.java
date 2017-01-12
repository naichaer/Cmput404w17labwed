package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by hong8 on 9/30/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 implements  MyObserver{
    Tweet tweet = new NormalTweet("hihihi");

    private boolean wasNotified = false;

    public TweetListTest(){
         super(LonelyTwitterActivity.class);
    }

    /*
    public void testDeleteTweet(){
        TweetList tweetList= new TweetList();
        tweetList.add(tweet);
        tweetList.delete(tweet);
        assertFalse(tweetList.hasTweet(tweet));
    }

     public void testGetTweet(){
        TweetList tweetList= new TweetList();
        Tweet tweet = new NormalTweet("hihihi");
        assertFalse(tweetList.hasTweet(tweet));
        Tweet returnedTweet = tweetList.getTweet();
        assertEquals( (tweet.date)==(returnedTweet.date) , tweet.getText().equals(returnedTweet.getText()));
    }

    */

    public  void testTweetListChanged(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihi");
        tweetList.addObserver(this);
        wasNotified=false;
        assertFalse(wasNotified);
        tweetList.add(tweet);
        assertTrue(wasNotified);

    }

    public void myNotify(){
        wasNotified = true;

    }


    public void testAddTweet(){
        TweetList tweetList= new TweetList();
        tweetList.add(tweet);
        assertTrue(tweetList.hasTweet(tweet));
    }

    public void testHastweet(){
        TweetList tweetList= new TweetList();
        assertFalse(tweetList.hasTweet(tweet));
        tweetList.add(tweet);
        assertTrue(tweetList.hasTweet(tweet));
    }

    public void testTweetCount(){

    }

}