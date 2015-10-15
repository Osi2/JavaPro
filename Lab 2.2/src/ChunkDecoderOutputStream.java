import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yegor2 on 10/14/2015.
 */
public class ChunkDecoderOutputStream extends OutputStream {

    private byte[] data;
    private List<String> headers;
    private int chunkSize;
    private ByteArrayInputStream is = null;
    private OutputStream os = null;

    public ChunkDecoderOutputStream(OutputStream os){
        this.os = os;
    }

    public void write(int a){

    }

    public void write(byte[] b)throws IOException{
        os.write(decodeData(b));
    }

    public byte[] decodeDataRegEx(byte[] data)throws UnsupportedEncodingException{

        String input = new String(data,"UTF-8");
        String stringSize = null;
        String stringOutput = null;
        Pattern pattern = Pattern.compile("^(.*)[\\r\\n]{1}((.|\\r|\\n)*)[\\r\\n]{1}$");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()){
            stringSize = matcher.group(1);
            stringOutput = matcher.group(2);
        }

        int size = Integer.parseInt(stringSize,16);
        System.out.println("decoded string: [" + stringOutput + "]");
        System.out.println("string length matches: " + (size == stringOutput.length()));

        return stringOutput.getBytes();
    }

    public byte[] decodeData(byte[] data)throws UnsupportedEncodingException{

        String input = new String(data,"UTF-8");
        String stringSize = input.substring(0, input.indexOf("\r\n"));
        int size = Integer.parseInt(stringSize,16);
        String stringOutput = input.substring(input.indexOf("\r\n") + 2, input.lastIndexOf("\r\n"));

        System.out.println("decoded string: [" + stringOutput + "]");
        System.out.println("string length matches: " + (size == stringOutput.length()));

        return stringOutput.getBytes();
    }



    public int getChunkSize() {return chunkSize;}

    public void setChunkSize(int value) {
        chunkSize = value;
    }
}
