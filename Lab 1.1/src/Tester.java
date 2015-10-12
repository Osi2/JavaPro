import java.lang.reflect.*;

/**
 * Created by Yegor2 on 10/8/2015.
 */
public class Tester {
    public static void main(String[] args) throws  Exception {

        Tester tester = new Tester();
        tester.runAnnotation();


    }

    private void runAnnotation() throws  Exception{

        MySomeClass someClass = new MySomeClass();
        Class<?> cls = MySomeClass.class;

        Method[] methods = cls.getDeclaredMethods();

        for (Method method : methods)
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                System.out.println("Method: " + method.getName());
                MyAnnotation anno = method.getAnnotation(MyAnnotation.class);
                method.invoke(someClass, anno.a(), anno.b());
            }

        }


}
