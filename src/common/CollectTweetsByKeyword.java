package common;

import java.text.SimpleDateFormat;

import org.json.JSONObject;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;


public class CollectTweetsByKeyword {
	public static TwitterStream twitterStream;
	public static StatusListener listener;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] keywords = {"NWCEXE"};
		String[] keywords_2 = {"Jingwei Yang"};
		
		getListenJobReady();
		getTweetsByName(keywords);
		
		/*
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getTweetsByName(keywords_2);
		*/
		
	}
	
	public static void getListenJobReady() {
		 ConfigurationBuilder cb = new ConfigurationBuilder();
	        cb.setDebugEnabled(true)
	          .setOAuthConsumerKey("Qrtfa7tRVdMnJVboGR9mtD84s")
	          .setOAuthConsumerSecret("5aOmn7azudhFhvvp75NjpjN4JLyZjZwYuELWX6BnjCcSHIlGuV")
	          .setOAuthAccessToken("1670776861-2IGs5zTnRBqxir81xG5J4gIKDG0CWU0O32MriQ4")
	          .setOAuthAccessTokenSecret("MTvR4acrwUkIF3v1PvS8yGu4CcldJ4PdE3VhtydFuer6s");
	    
	       twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
	       listener = new StatusListener() {
	           @Override
	           public void onStatus(Status status) {
	           	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	           	/*
	           	System.out.println("The tweet's ID: " + String.valueOf(status.getId()));
	           	System.out.println("The Screen name: " + status.getUser().getName());
	           	System.out.println("The text of the tweet: " + status.getText());
	           	System.out.println("The retweet count of the tweet: " + status.getRetweetCount());


	            System.out.println("The user's Name: " + status.getUser().getName());
	            System.out.println("The user's location: " + status.getUser().getLocation());
	            System.out.println("The user's profile url: " + status.getUser().getProfileImageURL());
	            System.out.println("The user's Description: " + status.getUser().getDescription());
	            System.out.println("The tweet was created at : " + status.getCreatedAt());
	            */
	            
	            TweetInfo temp_tweet = new TweetInfo();
	            temp_tweet.setStatus_id(String.valueOf(status.getId()));
	            temp_tweet.setScreen_name(status.getUser().getName());
	            temp_tweet.setText(status.getText());
	            temp_tweet.setImage_url(status.getUser().getProfileImageURL());
	            temp_tweet.setRetweet_count(status.getCreatedAt().toString());
	            temp_tweet.setRetweet_count(String.valueOf(status.getRetweetCount()));
	            
	            JSONObject json = new JSONObject(temp_tweet);
	            System.out.println("The tweet in JSON format:" + json.toString());
	            
	            TweetsCollection.addNewTweet(temp_tweet);
	            
	            
	            
	            //System.out.println("The user's place info latitude: " + status.getPlace().getBoundingBoxCoordinates()[0][0].getLatitude());
	            //System.out.println("The user's place info logitude: " + status.getPlace().getBoundingBoxCoordinates()[0][0].getLongitude());


	            
	            
	           	//int sent_value = SentimentAnalysis.metricSentiment(status.getText());
	           	//System.out.println("sentivalue:" + String.valueOf(sent_value));
	           	//TweetsFuntions.replyTweets(status.getUser().getScreenName(), status.getId(), "I trust you can do it!");;
	            
	   	           	
	           	if(status.getGeoLocation() != null){
	          
	           	/*
	           	if (true) {
	           	*/
	           		
	            	   try {
	            		   
	            		   System.out.println(status.getText());
	       	            	//int sent_value = SentimentAnalysis.metricSentiment(status.getText());
	            		   int sent_value = 2;
	       	            	System.out.println("sentivalue:" + String.valueOf(sent_value));
	            		   String dataString = dateFormatter.format(status.getCreatedAt());
	                   
	            		   String keyword = null;
	            		   String text = status.getText();
	            		   text = text.toUpperCase();
	                                               	
	                   		JSONObject jo = new JSONObject();

	                   		String lat = String.valueOf(status.getGeoLocation().getLatitude());
	                   		String lon = String.valueOf(status.getGeoLocation().getLongitude());
	                   		
	                   		
	                   		System.out.println(lat);
	                   		System.out.println(lon);
	                   		System.out.println("The tweet's ID: " + String.valueOf(status.getId()));
	                   	                    	
	                   		jo.put("lat", lat);
	                   		jo.put("lon", lon);
	                   		jo.put("keyword", keyword);
	                   
	                   	
	            	   } catch (Exception e) {
	            		   e.printStackTrace();
	            	   }
	            
	            	   System.out.println("tweetID:" + status.getId());
	            	   System.out.println("text:" + status.getText());
	            	   System.out.println("rweetCount:" + status.getRetweetCount());
	          
	               }
	           }
	           
	           @Override
	           public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	               //System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
	           }

	           @Override
	           public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	               //System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
	           }

	           @Override
	           public void onScrubGeo(long userId, long upToStatusId) {
	               //System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
	           }

	           @Override
	           public void onStallWarning(StallWarning warning) {
	               //System.out.println("Got stall warning:" + warning);
	           }

	           @Override
	           public void onException(Exception ex) {
	               ex.printStackTrace();
	           }
	       };
	       
	       //bind the listener over the twitterStream
	       twitterStream.addListener(listener);	       
	}
	
	
	
	
	
	static public void getTweetsByName(String[] keywords) {
		try {
		   twitterStream.cleanUp();
		}catch (Exception e){
			
		}
	       FilterQuery filtre = new FilterQuery();
	       filtre.track(keywords);
	       twitterStream.filter(filtre);
		}
}
