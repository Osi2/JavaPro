import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Yegor2 on 10/14/2015.
 */
public class ChunkEncoderOutputStream extends OutputStream{

    private ByteArrayOutputStream os = new ByteArrayOutputStream();
    private byte[] data;
    private List<String> headers;
    private int chunkSize;
    private int counter = 0;
    private int offset = 0;


    public ChunkEncoderOutputStream(byte[] data, List<String> headers){
        this.data = data;
        this.headers = headers;
    }

    public void write(int a){
            encodeData();
    }

    public byte[] encodeNextPortion(){
        return encodeData();
    }


    public boolean IsEmpty(){
        return (offset >= data.length);
    }

    private byte[] encodeData(){

        try {

            int n = data.length / chunkSize; // number of integer pieces that input string has been divided into
            int tail = data.length % chunkSize; // length of remainer part
            //int offset = 0;
            String head = Integer.toHexString(chunkSize) + "\r\n"; // first string with chunk size in hex
            os.flush();

            for (int i = counter; i < n; i++) { // write array of chunks
                os.write(head.getBytes());
                os.write(data, offset, chunkSize);
                os.write("\r\n".getBytes());
                offset += chunkSize;
                counter++;
                return os.toByteArray();
            }
            if (tail > 0) { // write tail part
                head = Integer.toHexString(tail) + "\r\n";
                os.write(head.getBytes());
                os.write(data, offset, tail);
                os.write("\r\n".getBytes());
            }

            os.write("0\r\n\r\n".getBytes());

            headers.add("Transfer-Encoding: chunked\r\n");

            return os.toByteArray();
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }

    }

    public int getChunkSize() {return chunkSize;}

    public void setChunkSize(int value) {
        chunkSize = value;
    }

}
