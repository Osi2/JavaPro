/**
 * Created by Yegor2 on 10/24/2015.
 */
public class User {

    private String nickName;

    private String firstName;

    private String lastName;

    private String password;

    private int status;

    private MessageList userMessages  = new MessageList();

    public User(String nickName, String firstName, String lastName, String password){
        this.nickName = nickName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public void setStatus(int status){this.status = status;}

    public String getFirstName(){return firstName;}

    public String getPassword(){return password;}

    public void addUserMessage(Message m){userMessages.add(m);}

    public MessageList getUserMessages(){return userMessages;}
}
