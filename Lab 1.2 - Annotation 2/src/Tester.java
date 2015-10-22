import java.lang.reflect.Method;
/**
 * Created by Yegor2 on 10/8/2015.
 */
public class Tester {
    public static void main(String[] args) throws  Exception{
        Tester tester = new Tester();
        tester.runTest();

    }

    private void runTest() throws Exception
    {
        Saver saver = new Saver();
        Class<?> cls = saver.getClass();

        Method[] methods = cls.getDeclaredMethods();
        for(Method method: methods)
        {
            if (method.isAnnotationPresent(Save.class))
            {
                SaveTo anno = cls.getAnnotation(SaveTo.class);
                method.invoke(saver,anno.path());
            }
        }

    }



}
