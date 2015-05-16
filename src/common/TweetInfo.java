package common;

import java.util.HashMap;


/*This class is use to record basic information of a tweet*/
public class TweetInfo {
	
	public HashMap<String, String> dict = new HashMap<String, String> ();
	private String status_id;
	private String screen_name;
	private String text;
	private String create_time;
	private String retweet_count;
	private String image_url;
	private String sentiment_value;
	
	
	public String getStatus_id() {
		return status_id;
	}
	public void setStatus_id(String status_id) {
		this.status_id = status_id;
		dict.put("status_id", status_id);
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
		dict.put("screen_name", screen_name);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
		dict.put("text", text);
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
		dict.put("create_time", create_time);
	}
	public String getRetweet_count() {
		return retweet_count;
	}
	public void setRetweet_count(String retweet_count) {
		this.retweet_count = retweet_count;
		dict.put("retweet_count", retweet_count);
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
		dict.put("image_url", image_url);
	}
	public String getSentiment_value() {
		return sentiment_value;
	}
	public void setSentiment_value(String sentiment_value) {
		this.sentiment_value = sentiment_value;
		dict.put("sentiment_value", sentiment_value);
	}
	
}
