import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yegor2 on 10/19/2015.
 */
public class TrainSchedule {

    @XmlElement (name="train")
    private List<Train> trains = new ArrayList<Train>();

    @Override
    public String toString(){
        return Arrays.deepToString(trains.toArray());
    }


}
