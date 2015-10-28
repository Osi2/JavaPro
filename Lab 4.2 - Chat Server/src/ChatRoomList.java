import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor2 on 10/28/2015.
 */
public class ChatRoomList {
    private static List<ChatRoom> chatRooms = new ArrayList<ChatRoom>();

    public static ChatRoom getChatRoom(String name){
        for (ChatRoom room: chatRooms){
            if (name.equals(room.getName()))
                return room;
        }
        return null;
    }

    public static void addRoom(ChatRoom room){
        chatRooms.add(room);
    }
}
