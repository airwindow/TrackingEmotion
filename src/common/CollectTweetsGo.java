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

public class CollectTweetsGo {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] keywords = {"Jingwei Yang"};
		//String[] keywords = {"Apple"};
		getTweetsByName(keywords);
		
	}
	
	
	static public void getTweetsByName(String[] keywords) {
	   	 ConfigurationBuilder cb = new ConfigurationBuilder();
	        cb.setDebugEnabled(true)
	          .setOAuthConsumerKey("OTXOGEHr86xIZotyMb2wtw89h")
	          .setOAuthConsumerSecret("hi2coUGt38DfzJPK3nMK7sd50iX8QGZVULfmutRgs0mN22tVG6")
	          .setOAuthAccessToken("3037011190-4v7hT2PF6Z9eBwyUqEUJ4iBXHMotLyO6udstomj")
	          .setOAuthAccessTokenSecret("ktj6COvFuNVTLJL0clSDfa45Lml5unHzDhRFwOKEd2rbt");
	    
	       TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
	       StatusListener listener = new StatusListener() {
	           @Override
	           public void onStatus(Status status) {
	           	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	           	//System.out.println(status.getText());
	           	//int sent_value = SentimentAnalysis.metricSentiment(status.getText());
	           	//System.out.println("sentivalue:" + String.valueOf(sent_value));
	           	//String text = status.getText();
	           	//System.out.println(text);
	           	
	           	if(status.getGeoLocation() != null){
	           		  System.out.println("Enter here!");
	           		
	            	   try {
	            		   
	            		   System.out.println(status.getText());
	       	            	int sent_value = SentimentAnalysis.metricSentiment(status.getText());
	       	            	System.out.println("sentivalue:" + String.valueOf(sent_value));
	            		    String dataString = dateFormatter.format(status.getCreatedAt());
	                   
	            		   String keyword = null;
	            		    //text = text.toUpperCase();
	                                               	
	                   		JSONObject jo = new JSONObject();

	                   		String lat = String.valueOf(status.getGeoLocation().getLatitude());
	                   		String lon = String.valueOf(status.getGeoLocation().getLongitude());
	                   		
	                   		System.out.println(lat);
	                   		System.out.println(lon);
	                   	                    	
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
	       FilterQuery filtre = new FilterQuery();
	       filtre.track(keywords);
	       twitterStream.filter(filtre);
		}

}
