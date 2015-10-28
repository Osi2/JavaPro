/**
 * Created by Yegor2 on 10/28/2015.
 */
public class ChatRoom {

    private  String name;
    private  String password;

    private User owner;
    private UserList userList;

    public ChatRoom(String name, String password, User owner){
        this.name = name;
        this.owner = owner;
        this.password = password;
    }

    public UserList getUserList(){
        return userList;
    }

    public void addUser(User user){
        this.userList.addUser(user);
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }
}
