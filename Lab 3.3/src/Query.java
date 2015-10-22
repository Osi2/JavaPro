import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="query")
public class Query {
    public int count;
    public String created;
    public String lang;
    public Results results;
}
