import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Yegor on 10/28/2015.
 */
public class GetChatLRoomListServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("GetChatLRoomListServlet invoked");
        List<ChatRoom> chatRoomList = ChatRoomList.getChatRooms();

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(chatRoomList.toArray());
        System.out.println("json: \n" + json);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
