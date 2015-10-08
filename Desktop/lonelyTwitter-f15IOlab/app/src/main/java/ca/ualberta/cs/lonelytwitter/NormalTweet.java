package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by joshua2 on 9/16/15.
 */
public class NormalTweet extends Tweet {
    public NormalTweet(String tweet, Date date)throws TweetTooLongException  {
        super(tweet, date);
    }

    public NormalTweet(String tweet)throws TweetTooLongException  {
        super(tweet);
    }

    public Boolean isImportant() {
        return Boolean.FALSE;
    }
}
