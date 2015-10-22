/**
 * Created by Yegor on 10/20/2015.
 */
public class Address {

        String country;
        String city;
        String street;

        @Override
        public String toString(){
            return "Address: "  + country + " " + city + " " + street;
        }

}
