import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Yegor2 on 10/19/2015.
 */
public class Main {
    public static void main(String[] args) throws JAXBException,ParseException{

        File file = new File("C:\\Users\\Yegor2\\IdeaProjects\\JavaPro\\Lab 3.1\\src\\Train.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(TrainSchedule.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        TrainSchedule trainSchedule = (TrainSchedule)unmarshaller.unmarshal(file);

        Date dateFrom = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse("19.12.2013 15:00");
        Date dateTo = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse("19.12.2013 19:00");

        for (Train train:trainSchedule.getTrains()){

            Date date = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(train.getDate() + " " + train.getTime());

            if (date.after(dateFrom) && date.before(dateTo)){
                System.out.println(train.toString() + " departures from 15:00 till 19:00");
            }


        }




    }
}
