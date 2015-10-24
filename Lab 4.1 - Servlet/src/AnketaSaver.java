import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor on 10/24/2015.
 */
public class AnketaSaver {
    private static List<Anketa> anketas =new ArrayList<Anketa>();

    public static void addAnketa(Anketa anketa){
        anketas.add(anketa);
    }

    public static List<Anketa> getAnketas(){return anketas;}
}
