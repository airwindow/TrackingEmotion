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
	}
	
	public static void getListenJobReady() {
		 ConfigurationBuilder cb = new ConfigurationBuilder();
	        cb.setDebugEnabled(true)
	          .setOAuthConsumerKey("aptXGK7o0Ry3SiYYb1KCIp2J8")
	          .setOAuthConsumerSecret("VBHP5mafKrBvaicwT1SaVDXPLPmMUrqkygTI3C6v1L2rSZOAET")
	          .setOAuthAccessToken("3239936099-CUY8INmN1bTdpBNC9uzU40lO4vGpojjiJLzMT4N")
	          .setOAuthAccessTokenSecret("MyNMoP6MbgJiUzw0TtOqsqMbOOFQs6S3t8R6GgWClhDzl");
	    
	       twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
	       listener = new StatusListener() {
	           @Override
	           public void onStatus(Status status) {
	           	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	           	
	            
	            TweetInfo temp_tweet = new TweetInfo();
	            temp_tweet.setStatus_id(String.valueOf(status.getId()));
	            temp_tweet.setScreen_name(status.getUser().getScreenName());
	            temp_tweet.setText(status.getText());
	            temp_tweet.setImage_url(status.getUser().getProfileImageURL());
	            temp_tweet.setCreate_time(status.getCreatedAt().toString());
	            temp_tweet.setRetweet_count(String.valueOf(status.getRetweetCount()));
	            
	            int sentiment_value = SentimentAnalysis.metricSentiment(status.getText());
	            temp_tweet.setSentiment_value(String.valueOf(sentiment_value));
	            
	            
	            TweetsCollection.addNewTweet(temp_tweet);
	            
	   	           	
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
	       String[] lang = {"en"};
	       filtre.track(lang);
	       filtre.track(keywords);
	       twitterStream.filter(filtre);
		}
}
