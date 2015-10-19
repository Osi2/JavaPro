import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


/**
 * Created by Yegor2 on 10/19/2015.
 */
public class Main {
    public static void main(String[] args) throws JAXBException{

        File file = new File("C:\\Users\\Yegor2\\IdeaProjects\\JavaPro\\Lab 3.11\\src\\Train.xml");
        Train train = new Train();
        TrainSchedule trainSchedule = new TrainSchedule();

        JAXBContext jaxbContext = JAXBContext.newInstance(Train.class);
        //Marshaller marshaller = jaxbContext.createMarshaller();

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        trainSchedule = (TrainSchedule)unmarshaller.unmarshal(file);

        System.out.println(trainSchedule);




    }
}
