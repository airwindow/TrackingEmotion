package common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class responder
 */
/*Start the process of collecting tweets*/
@WebServlet("/responder")
public class responder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public responder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /*The keyword should be passed in, by a Ajax request*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String[] keywords = {"NWCEXE"};
		//String[] keywords_2 = {"Jingwei Yang"};
		
		//getListenJobReady();
		CollectTweetsByKeyword.getListenJobReady();
		CollectTweetsByKeyword.getTweetsByName(keywords);
		
		
		
		
		/*
		HashMap<String, String> map = new HashMap<String, String> ();
		
		int random_number = DistributedRandomNumberGenerator.getNumber();
		map.put("tweet_id", "101001");
		map.put("tweet_text", "This is a sample text");
		map.put("sentiment_vaule", String.valueOf(random_number));
		
		JSONObject jo = new JSONObject(map);
		response.getWriter().write(jo.toString());
		*/
		//response.getWriter().write("Hello!");
		//System.out.println("I have just sent a message to frontend");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
