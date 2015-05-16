package common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReplyTweet
 */
@WebServlet("/ReplyTweet")
public class ReplyTweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyTweet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			String send_ID = (String) request.getParameter("send_ID");
			String reply_text = (String) request.getParameter("send_text");
			
			
			String[] parts = send_ID.split("-");
			String status_id = parts[0];
			String screen_name = parts[1];
			
			System.out.println("the status id is: " + status_id);
			System.out.println("the screen_name is: " + screen_name);
			System.out.println("the reply_text is:" + reply_text);
			
			TweetsFuntions.replyTweets(screen_name, Long.valueOf(status_id).longValue(), reply_text);			
			response.getWriter().write("success");		
			
		} catch (Exception e) {
			response.getWriter().write("failure");					
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
