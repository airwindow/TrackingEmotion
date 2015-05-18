package common;

import java.util.ArrayList;

public class TweetsCollection {
	/*This ArrayList record all latest tweets that has not sent to the front yet*/
	static public final ArrayList<TweetInfo> tweets_list = new ArrayList<TweetInfo> ();
	
	/*Add a new tweet into the Collection*/
	static public void addNewTweet(TweetInfo new_tweet) {
		tweets_list.add(new_tweet);
		System.out.println("a new tweet was added " + new_tweet.dict.toString());
		System.out.println("current collection size: " + tweets_list.size());
	}
	
	/*remove a new tweet from the Collection*/
	static public TweetInfo getNewTweet() {
		if (tweets_list.size() > 0) {
			System.out.println("romove one tweet from the collection!");
			return tweets_list.remove(tweets_list.size()-1);
		} else {
			return null;
		} 
	}
	
	/*get the current size of the Collection*/
	static public int getSize() {
		//System.out.println("the size is: " + String.valueOf(tweets_list.size()));
		return tweets_list.size();
	}
}
