import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;


@Target(value= ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
@interface Save{
}

public class SaveObject implements Serializable {

    private static final long serialVersionUID = -2518143671167959230L;

    @Save
    private String firstName;
    @Save
    private String lastName;
    private String address;

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getAddress(){return address;}

    public SaveObject(String firstName, String lastName, String address)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    private void writeObject(ObjectOutputStream o) throws IOException, ClassNotFoundException
    {

        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    o.writeObject(field.get(this));
                }
            }
        }catch (IllegalAccessException ex){}
    }

    private void readObject(ObjectInputStream o) throws ClassNotFoundException, IOException{
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    field.set(this, o.readObject());
                }
            }
        }catch (IllegalAccessException ex){}

    }


}
