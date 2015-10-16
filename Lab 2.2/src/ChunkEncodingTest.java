import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor2 on 10/14/2015.
 */
public class ChunkEncodingTest {
    private static String file = "D:\\Projects\\JavaPro\\Lab 2.2\\src\\test.txt";

    public static void main(String[] args) throws FileNotFoundException,IOException {

        List<String> headers = new ArrayList<String>();
        FileInputStream fis = new FileInputStream(file);

        byte[] data = new byte[fis.available()];
        fis.read(data);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ChunkEncoderOutputStream os = new ChunkEncoderOutputStream(byteArrayOutputStream);

        ChunkDecoderInputStream is = new ChunkDecoderInputStream();
        os.setChunkSize(10);
        is.setChunkSize(10);

        while (!os.IsEmpty()){
            os.encodeNextChunk();
            String encodedString = new String(byteArrayOutputStream.toByteArray(),"UTF-8");

            //System.out.println("encoded string: [" + encodedString + "]");
            //String decodedChunk = is.decodeData(encodedChunk);

        }





    }
}
