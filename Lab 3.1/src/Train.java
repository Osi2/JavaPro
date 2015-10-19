import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Yegor2 on 10/19/2015.
 */

@XmlRootElement(name="trains")
public class Train {

    @XmlAttribute(name="id")
    public Integer id;
    @XmlElement
    public String from;
    @XmlElement
    public String to;
    @XmlElement
    public String date;
    @XmlElement
    public String time;

}
