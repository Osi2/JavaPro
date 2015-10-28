import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Yegor2 on 10/25/2015.
 */
public class GetUserListServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("GetUserListServlet invoked");

        System.out.println("GetChatLRoomListServlet invoked");
        List<User> userList = UserList.getUserList();

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(userList.toArray());
        System.out.println("json: \n" + json);

        resp.setContentType("application/json");
        resp.getWriter().write(json);

    }
}
