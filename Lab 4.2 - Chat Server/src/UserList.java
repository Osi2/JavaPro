import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor2 on 10/24/2015.
 */
public class UserList {

    private static List<User> userList = new ArrayList<User>();

    public static void addUser(User user){userList.add(user);}

    public static User getUserByName(String name){
        for(User u:userList){
            if (name.equals(u.getFirstName()))
                    return u;
        }
        return null;
    }
    static {
        userList.add(new User("Main chat","Main chat","Main","11111"));
        userList.add(new User("Ivan","Ivan","Ivanov","11111"));
        userList.add(new User("Petro","Petro","Petrov","11111"));
        userList.add(new User("Mykola","Mykola","Nikolaev","11111"));
        userList.add(new User("Yegor","Yegor","Osipov","11111"));
    }

    public static List<User> getUserList(){return userList;}

}
