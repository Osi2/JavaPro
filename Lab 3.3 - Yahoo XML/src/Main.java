import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Yegor2 on 10/20/2015.
 */
public class Main {
    public static void main(String[] args) throws IOException,JAXBException{
        String request = "http://query.yahooapis.com/v1/public/yql?format=xml&q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(\"USDEUR\",%20\"USDUAH\")&env=store://datatables.org/alltableswithkeys";

        URL url = new URL(request);

        JAXBContext jaxbContext = JAXBContext.newInstance(Query.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Query query = (Query)unmarshaller.unmarshal(url);

        Results results = query.results;

        for(Rate rate : results.rate){
            System.out.println(rate.toString());
        }

    }
}
