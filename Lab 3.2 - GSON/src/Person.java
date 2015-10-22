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
    return "Person: " + name + " " + surname + " phones: " + getPhones() + " sites: " + getSites() + " " + address.toString();
    }

    private String getPhones(){
        String result = "";

        for(String s: phones){
            result += s + " ";
        }
        return result;
    }

    private String getSites(){
        String result = "";
        for(String s: sites){
            result += s + " ";
        }
        return result;
    }
}

