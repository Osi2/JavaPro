import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Yegor2 on 10/9/2015.
 */
public class Tester {
    public static void main(String[] args) throws Exception{
        Tester tester = new Tester();
        tester.run("C:\\Temp\\serialFile.ser");
    }

    private void run(String file) throws Exception
    {
        SaveObject object1 = new SaveObject("Leonid","Agutin","London");
        readValues(object1);
        System.out.println("\nDo Serialization\n");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(object1);
        outputStream.flush();
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        SaveObject object2 = (SaveObject)inputStream.readObject();
        inputStream.close();
        readValues(object2);

    }

    private void readValues(SaveObject saveObject)
    {
        System.out.println("first Name: " + saveObject.getFirstName());
        System.out.println("last Name: " + saveObject.getLastName());
        System.out.println("address: " + saveObject.getAddress());
    }
}
