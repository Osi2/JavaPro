import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor2 on 10/14/2015.
 */
public class ChunkEncodingTest {
    private static String fileIn = "D:\\_Projects\\JavaPro\\Lab 2.2\\src\\InputFile.txt";
    private static String fileOut = "D:\\_Projects\\JavaPro\\Lab 2.2\\src\\OutputFile.txt";

    public static void main(String[] args) throws IOException {

        try( ChunkEncoderInputStream is = new ChunkEncoderInputStream(new FileInputStream(fileIn), 10);
             ChunkDecoderOutputStream os = new ChunkDecoderOutputStream(new FileOutputStream(fileOut))){


            while (!is.IsEmpty()) {
                byte[] encodedChunk = is.encodeNextPortion();
                String encodedString = new String(encodedChunk, "UTF-8");
                System.out.println("encoded string: " + encodedString);
                System.out.println("encoded string: [" + encodedString.replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n") + "]");
                os.write(encodedChunk);
            }

        }catch (IOException ex){ex.printStackTrace();}





    }
}
