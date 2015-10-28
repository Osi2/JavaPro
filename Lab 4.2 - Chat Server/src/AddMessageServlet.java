import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddMessageServlet extends HttpServlet {

	private MessageList msgList = MessageList.getInstance();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		System.out.println("AddMessageServlet invoked");
		System.out.println("Content length: " + String.valueOf(req.getContentLength()));

		InputStream is = req.getInputStream();
		byte[] buf = new byte[req.getContentLength()];
		is.read(buf);

		System.out.println("json: " + new String(buf));
		
		Message msg = Message.fromJSON(new String(buf));
		if (msg != null) {
			System.out.println("new message created");
			System.out.println("to: " + msg.getTo());
			System.out.println("from: " + msg.getFrom());
			System.out.println("text: " + msg.getText());

			User u1 = UserList.getUserByName(msg.getTo());
			System.out.println("add message for user: " + u1.getFirstName());
			u1.addUserMessage(msg);

			User u2 = UserList.getUserByName(msg.getFrom());
			System.out.println("add message for user: " + u2.getFirstName());
			u2.addUserMessage(msg);
			//msgList.add(msg);
		}
		else {
			System.out.println("message is null");
			resp.setStatus(400); // Bad request
		}


	}
}
