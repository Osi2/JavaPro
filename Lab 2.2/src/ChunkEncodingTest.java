import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor2 on 10/14/2015.
 */
public class ChunkEncodingTest {
    private static String file = "C:\\Users\\Yegor2\\IdeaProjects\\JavaPro\\Lab 2.2\\src\\test.txt";

    public static void main(String[] args) throws FileNotFoundException,IOException {

        List<String> headers = new ArrayList<String>();
        FileInputStream fis = new FileInputStream(file);

        byte[] data = new byte[fis.available()];
        fis.read(data);

        ChunkEncoderOutputStream os = new ChunkEncoderOutputStream(data,headers);
        ChunkDecoderInputStream is = new ChunkDecoderInputStream();
        os.setChunkSize(10);
        is.setChunkSize(10);

        while (!os.IsEmpty()){
            byte[] encodedChunk = os.encodeNextPortion();
            String encodedString = new String(encodedChunk,"UTF-8");

            System.out.println("encoded string: [" + encodedString + "]");
            String decodedChunk = is.decodeData(encodedChunk);

        }





    }
}
