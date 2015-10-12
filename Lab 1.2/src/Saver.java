import java.io.FileWriter;
import java.io.IOException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value= RetentionPolicy.RUNTIME)
@interface SaveTo{
    String path() default "C:\\Temp\\Outfile.txt";
}

@Retention(value= RetentionPolicy.RUNTIME)
@interface Save{
}

@SaveTo
public class Saver {

    @Save
    public void Save1(String file)
    {

        try(FileWriter writer = new FileWriter(file))
        {
            TextContainer container = new TextContainer();
            writer.write(container.getText());
            writer.flush();
        }
        catch (IOException ex)
        {

        }
    }

    public void Save2(String file, String text){}
}

class TextContainer
{
    private String text = "Some Text";
    public String getText() {return text;}
}
