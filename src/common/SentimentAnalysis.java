package common;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

//import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;


public class SentimentAnalysis {
	
	static private final String SENTIMENTS_BASE_URL = "http://www.sentiment140.com/api/bulkClassifyJson";
	static final String MEDIA_TYPE = "application/json";
	
	/*pass sentimental value back. -1 : negative, 0 : neutral, +1 : positive*/
	static public int metricSentiment(String s) {
		
		try {
			String params = new String("{\"data\": [{\"text\": \"" + s + "\"}]}");
					
			URL url = new URL(SENTIMENTS_BASE_URL);
			System.out.println(params);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);//set to true if need output
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(params);
			writer.flush();

			String line;
			String ret = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			while ((line = reader.readLine()) != null) {
				ret = line;
			    //System.out.println(line);
			}
			
			/*request format: {"data":[{"text":"I don't know","polarity":2,"meta":{"language":"en"}}]}*/
			JSONObject data = new JSONObject(ret);
			JSONArray data1 = new JSONArray(data.get("data").toString());
			JSONObject data2 = data1.getJSONObject(0);
			ret = data2.get("polarity").toString();
			
			writer.close();
			reader.close(); 
			
			//return Integer.valueOf(ret); 
			
			if (ret.equals("0"))
				return -1;
			else if(ret.equals("1") || ret.equals("2"))
				return 0;
			else
				return 1;
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(metricSentiment("I hate it"));
		System.out.println(metricSentiment("I like it"));
		System.out.println(metricSentiment("I don't know"));
	}
}
