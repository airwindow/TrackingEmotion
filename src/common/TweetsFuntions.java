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
	/*crediential owned Jingwei Yang @yangjingwei1989
	 *.setOAuthConsumerKey("Qrtfa7tRVdMnJVboGR9mtD84s")
	  .setOAuthConsumerSecret("5aOmn7azudhFhvvp75NjpjN4JLyZjZwYuELWX6BnjCcSHIlGuV")
	  .setOAuthAccessToken("1670776861-2IGs5zTnRBqxir81xG5J4gIKDG0CWU0O32MriQ4")
	  .setOAuthAccessTokenSecret("MTvR4acrwUkIF3v1PvS8yGu4CcldJ4PdE3VhtydFuer6s");
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] keywords = {"Jingwei Yang"};
		//String[] keywords = {"Apple"};
		//getTweetsByName(598522064715276288l, );
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
			twitter.setOAuthConsumer("Qrtfa7tRVdMnJVboGR9mtD84s", "5aOmn7azudhFhvvp75NjpjN4JLyZjZwYuELWX6BnjCcSHIlGuV");
			AccessToken accessToken = new AccessToken("1670776861-2IGs5zTnRBqxir81xG5J4gIKDG0CWU0O32MriQ4", "MTvR4acrwUkIF3v1PvS8yGu4CcldJ4PdE3VhtydFuer6s");
			twitter.setOAuthAccessToken(accessToken);
		
			
			//Twitter twitter = new TwitterFactory().getInstance();			
			StatusUpdate statusUpdate = new StatusUpdate("@" + screen_name + " " + reply_text ).inReplyToStatusId(tweetID);
			Status reply = twitter.updateStatus(statusUpdate);
			
			System.out.println("Posted reply " + reply.getId() + " in response to tweet " + reply.getInReplyToStatusId());
			
			/*
			statusUpdate.inReplyToStatusId(tweetID);
			Status status = twitter.updateStatus(statusUpdate);	
			*/
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
