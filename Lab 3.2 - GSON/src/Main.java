import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;

/**
 * Created by Yegor on 10/20/2015.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException{

        BufferedReader reader = new BufferedReader(new FileReader("D:\\_Projects\\JavaPro\\Lab 3.2\\src\\InputData.json"));
        StringBuilder sb = new StringBuilder();
        String s  = null;

        while ( (s = reader.readLine())!=null){
            sb.append(s);
        }

        Gson gson = new GsonBuilder().create();
        Person person = gson.fromJson(sb.toString(),Person.class);
        System.out.println(person.toString());
    }
}
