import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Yegor on 10/20/2015.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException{

        JsonReader jsonReader = new JsonReader(new FileReader("D:\\Projects\\JavaPro\\Lab 3.2\\src\\InputData.json"));

        Gson gson = new GsonBuilder().create();
        Person person = gson.fromJson(jsonReader.toString(),Person.class);
        System.out.println(person.toString());
    }
}
