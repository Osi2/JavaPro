/**
 * Created by Yegor on 10/20/2015.
 */
public class Person {
    String name;
    String surname;
    String[] phones;
    String[] sites;
    Address address;

    @Override
    public String toString(){
    return "Person: " + name + " " + surname + " " + phones[0].toString() + " " + sites[0].toString() + " " + address.toString();
    }
}

