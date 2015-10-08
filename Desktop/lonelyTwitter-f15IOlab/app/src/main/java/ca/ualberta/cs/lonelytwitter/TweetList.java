package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hong8 on 9/30/15.
 */
public class TweetList implements  MyObservable{

    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    private ArrayList<Tweet> ordered_tweets = new ArrayList<Tweet>();
    private Date earlyest_date = new Date();
    private Tweet earlyest_tweet;
    private int count;


    public ArrayList<MyObserver> myObservers = new ArrayList<MyObserver>();


    public void addObserver(MyObserver observer){
        myObservers.add(observer);
    }


    public void notifyObservers(){
        for(MyObserver observer: myObservers){
            observer.myNotify();
        }
    }

    public void add(Tweet tweet){
        tweets.add(tweet);
        notifyObservers();

    }


    public void delete(Tweet tweet){
        tweets.remove(tweet);
    }
    /*
    public void add(Tweet tweet){
        if(tweets.contains(tweet)) {
            throw new IllegalArgumentException("Duplicated tweet") ;
        }else{
            tweets.add(tweet);
        }
    }*/

    public Tweet getTweet(){
        return tweets.get(0);
    }

    public int getCount(){
        return tweets.size();
    }

    public void removeTweet(){
        tweets.remove(tweets.get(0));
    }

    public Boolean hasTweet(Tweet tweet){
        return tweets.contains(tweet);
    }

    public Boolean hasTweets(){
        for (Tweet tweet:tweets){
            count = 0;
            for(int i = 0; i<tweets.size(); i++){
                if(tweet.equals(tweets.get(i))){
                    count++;
                }
            }
            if(count>1){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Tweet> getTweets(){
        ArrayList<Tweet> copied_tweets= new ArrayList<Tweet>();
        for (Tweet tweet:tweets){
            copied_tweets.add(tweet);
        }

        if(tweets.size()>=1){
            earlyest_date = tweets.get(0).getDate();
            earlyest_tweet = tweets.get(0);
        }else{
            return tweets;
        }
        while(copied_tweets.size()>=1) {
            for (Tweet tweet : copied_tweets) {
                Date other_date = tweet.getDate();
                if (other_date.before(earlyest_date)) {
                    earlyest_date = other_date;
                    earlyest_tweet = tweet;
                }
            }
            ordered_tweets.add(earlyest_tweet);
            copied_tweets.remove(copied_tweets.lastIndexOf(earlyest_tweet));
        }
        return ordered_tweets;
    }
}






