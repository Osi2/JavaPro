import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yegor2 on 10/14/2015.
 */
public class ChunkDecoderInputStream extends InputStream {

    private byte[] data;
    private List<String> headers;
    private int chunkSize;
    private ByteArrayInputStream is = null;

    public ChunkDecoderInputStream(){

    }

    public int read(){
        if (is.available() < 0){
            return -1;
        }
        return is.read();
    }

    public String decodeData(byte[] data)throws UnsupportedEncodingException{

        int n = data.length / chunkSize; // number of integer pieces that input string has been divided into
        int tail = data.length % chunkSize; // length of remainer part
        //int offset = 0;
        String head = Integer.toHexString(chunkSize) + "\r\n"; // first string with chunk size in hex


        String input = new String(data,"UTF-8");
        String stringSize = null;
        String stringOutput = null;
        Pattern pattern = Pattern.compile("^(.*)[\\r\\n]+(.*)[\\r\\n]+$");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()){
            stringSize = matcher.group(1);
            stringOutput = matcher.group(2);
        }

        int size = Integer.parseInt(stringSize,16);
        System.out.println("decoded string: [" + stringOutput + "]");
        System.out.println("string length matches: " + (size == stringOutput.length()));

        return stringOutput;
    }



    public int getChunkSize() {return chunkSize;}

    public void setChunkSize(int value) {
        chunkSize = value;
    }
}
