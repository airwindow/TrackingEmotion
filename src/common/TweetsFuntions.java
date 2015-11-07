package common;


import java.text.SimpleDateFormat;
import java.util.UUID;

import org.json.JSONObject;

import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;


public class TweetsFuntions {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] keywords = {"Jingwei Yang"};
		replyTweets("Chandler Young", 598522064715276288l, "I think so!");
		
		/*
		The tweet's ID: 598522955799334913
		The Screen name: Chandler Young
		*/
		
	}
	
	
	/*This function is used to reply a message to a designate tweet*/
	public static void replyTweets(String screen_name, long tweetID, String reply_text) {
		try {
		
			TwitterFactory factory = new TwitterFactory();
			Twitter twitter = factory.getInstance();
			twitter.setOAuthConsumer("aptXGK7o0Ry3SiYYb1KCIp2J8", "VBHP5mafKrBvaicwT1SaVDXPLPmMUrqkygTI3C6v1L2rSZOAET");
			AccessToken accessToken = new AccessToken("3239936099-CUY8INmN1bTdpBNC9uzU40lO4vGpojjiJLzMT4N", "MyNMoP6MbgJiUzw0TtOqsqMbOOFQs6S3t8R6GgWClhDzl");
			twitter.setOAuthAccessToken(accessToken);
		
			StatusUpdate statusUpdate = new StatusUpdate("@" + screen_name + " " + reply_text ).inReplyToStatusId(tweetID);
			Status reply = twitter.updateStatus(statusUpdate);
			System.out.println("Posted reply " + reply.getId() + " in response to tweet " + reply.getInReplyToStatusId());
			
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
