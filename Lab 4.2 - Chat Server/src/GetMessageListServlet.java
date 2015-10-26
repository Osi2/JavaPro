import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetMessageListServlet extends HttpServlet {
	
	private MessageList msgList = MessageList.getInstance();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException 
	{
		String fromStr = req.getParameter("from");
		int from = Integer.parseInt(fromStr);
		
		String json = msgList.toJSON(from);
		if (json != null) {
			OutputStream os = resp.getOutputStream();
			os.write(json.getBytes());
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println("GetMessageListServlet invoked");

		String user = req.getParameter("getUserChat");
		System.out.println("get chat messages for user: " + user);

		msgList = UserList.getUserByName(user).getUserMessages();
		System.out.println("Number of messages by user " + user + ": " + String.valueOf(msgList.getCount()));
		String json = msgList.toJSON(0);

		System.out.println("Json: " + json);

		resp.setContentType("application/json");
		resp.getWriter().write(json);
		//OutputStream os = resp.getOutputStream();
		//os.write(json.getBytes());

		//resp.setContentType("text/plain");
		//resp.setCharacterEncoding("UTF-8");
		//resp.getWriter().write("Some response");

	}
}
