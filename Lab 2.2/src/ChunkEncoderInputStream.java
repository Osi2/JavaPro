import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Yegor2 on 10/14/2015.
 */
public class ChunkEncoderInputStream extends InputStream{

    private ByteArrayOutputStream os = new ByteArrayOutputStream();
    private InputStream is = null;
    private byte[] data;
    private List<String> headers;
    private int chunkSize;
    private int counter = 0;
    private int offset = 0;
    private boolean isEmpty = false;

    public int read() throws IOException{
        if (is.available() < 0){
            return -1;
        }
        return is.read();
    }


    public ChunkEncoderInputStream(InputStream is, int chunkSize)throws IOException{
        this.is = is;
        this.chunkSize = chunkSize;
        this.data = new byte[is.available()];
        is.read(data);
    }

    public byte[] encodeNextPortion(){
        return encodeData();
    }

    public boolean IsEmpty(){
        return isEmpty;
    }

    private byte[] encodeData(){

        try {

            int n = data.length / chunkSize; // number of integer pieces that input string has been divided into
            int tail = data.length % chunkSize; // length of remainer part
            //int offset = 0;
            String head = Integer.toHexString(chunkSize) + "\r\n"; // first string with chunk size in hex
            os.reset();

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

            isEmpty = true;

            //headers.add("Transfer-Encoding: chunked\r\n");

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
