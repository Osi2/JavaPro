import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yegor2 on 10/25/2015.
 */
public class AuthenticateServlet  extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("AuthenticateAervlet invoked");

        String user = req.getParameter("nickName");
        System.out.println("get password for nickName: " + user);

        User u = UserList.getUserByName(user);
        String pass = u.getPassword();
        System.out.println("password: " + pass);

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(pass);
    }

}
