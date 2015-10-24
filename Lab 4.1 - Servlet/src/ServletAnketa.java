import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Yegor on 10/23/2015.
 */
public class ServletAnketa extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Anketa anketa = new Anketa();
        anketa.firstName = req.getParameter("firstName");
        anketa.lastName = req.getParameter("lastName");
        anketa.age = req.getParameter("age");
        anketa.town = req.getParameter("town");
        anketa.primarySkills = req.getParameter("primarySkills");
        anketa.language = req.getParameter("language");
        anketa.sql = req.getParameter("sql");
        anketa.oracle = req.getParameter("oracle");
        anketa.core = req.getParameter("core");
        anketa.threading = req.getParameter("threading");
        anketa.j2ee = req.getParameter("j2ee");
        anketa.spring = req.getParameter("spring");
        anketa.hibernate = req.getParameter("hibernate");

        AnketaSaver.addAnketa(anketa);


        resp.setContentType("text/html");

        PrintWriter pw = resp.getWriter();

        List<Anketa> anketas = AnketaSaver.getAnketas();

        pw.println("<table><tr><th width=\"15%\"></th><th width=\"40%\"></th><th width=\"50%\"></th>");

        for (int i = 0; i<anketas.size();i++){
            Anketa a = anketas.get(i);
            pw.println("<tr><td></td><td><small>first Name:</small></td><td><small><p style=\"text-transform: uppercase\">" + a.firstName + "</p></small></td></tr>");
            pw.println("<tr><td></td><td><small>last Name:</small></td><td><small><p style=\"text-transform: uppercase\">" + a.lastName + "</p></small></td></tr>");
            pw.println("<tr><td></td><td><small>age:</small></td><td><small>" + a.age + "</small></td></tr>");
            pw.println("<tr><td></td><td><small>town:</small></td><td><small>" + a.town + "</small></td></tr>");
            pw.println("<tr><td></td><td><small>primary skills:</small></td><td><small>" + a.primarySkills + "</small></td></tr>");
            pw.println("<tr><td></td><td><small>languages:</small></td><td><small>" + a.language + "</small></td></tr>");
            pw.println("<tr><td></td><td><small>sql:</small></td><td><small>" + a.sql + "</small></td></tr>");
            pw.println("<tr><td></td><td><small>core java:</small></td><td><small>" + a.core + "</small></td></tr>");
            pw.println("<tr><td></td><td><small>oracle:</small></td><td><small>" + a.oracle + "</small></td></tr>");
            pw.println("<tr><td></td><td><small>threading:</small></td><td><small>" + a.threading + "</small></td></tr>");
            pw.println("<tr><td></td><td><small>j2ee:</small></td><td><small>" + a.j2ee + "</small></td></tr>");
            pw.println("<tr><td></td><td><small>spring:</small></td><td><small>" + a.spring + "</small></td></tr>");
            pw.println("<tr><td></td><td><small>hibernate:</small></td><td><small>" + a.hibernate + "</small></td></tr>");
            pw.println("<tr><td></td><td><hr></td><td><hr></td></tr>");
        }
        pw.println("</table>");

    }
}
