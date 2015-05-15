package common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class GetLatestTweets
 */
@WebServlet("/GetLatestTweets")
public class GetLatestTweets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLatestTweets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (TweetsCollection.getSize() > 0) {
			response.getWriter().write("Something for you!");
			TweetInfo temp_tweet = TweetsCollection.getNewTweet();
			JSONObject temp_json = new JSONObject(temp_tweet.dict);
			System.out.println("The JSON String sent to front end :" + temp_json.toString());
			response.getWriter().write(temp_json.toString());
		} else {
			
			response.getWriter().write("null");			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
