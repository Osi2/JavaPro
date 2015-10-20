import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Yegor2 on 10/19/2015.
 */

@XmlRootElement(name="train")
public class Train {

    @XmlAttribute(name="id")
    public Integer id;
    @XmlElement
    public String from;
    @XmlElement
    public String to;
    @XmlElement
    public String date;
    @XmlElement(name="departure")
    public String time;

    public Train(){}

    public Train(Integer id, String from, String to, String date, String time){
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
    }

    public String getDate(){return date;}
    public String getTime(){return time;}

    @Override
    public String toString(){return "Train № " + id.toString() + " " + from + " - " + to + " " + "(dep. time " + time + ")";}


}
