import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rate")
public class Rate {
    @XmlAttribute
    public String id;
    public String Name;
    public double Rate;
    public String Date;
    public String Time;
    public String Ask;
    public String Bid;

    @Override
    public String toString(){
        return "Rate id: " + id + " | Rate: " + String.valueOf(Rate) + " | Date: " + Date + " | Time: " + Time + " | Ask: " + Ask + " | Bid: " + Bid;
    }
}
