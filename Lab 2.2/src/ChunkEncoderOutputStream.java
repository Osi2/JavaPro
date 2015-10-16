import java.io.*;
import java.util.List;

/**
 * Created by Yegor2 on 10/14/2015.
 */
public class ChunkEncoderOutputStream extends OutputStream{

    private DataOutputStream dos = null;
    private byte[] data;
    private List<String> headers;
    private int chunkSize;
    private int counter = 0;
    private int offset = 0;

    public ChunkEncoderOutputStream(OutputStream os){
        dos = new DataOutputStream(os);
    }


    public ChunkEncoderOutputStream(byte[] data, List<String> headers){
        this.data = data;
        this.headers = headers;
    }

    public void write(int a){

    }


    public boolean IsEmpty(){
        return (offset >= data.length);
    }

    public void encodeNextChunk(){

        try {

            int n = data.length / chunkSize; // number of integer pieces that input string has been divided into
            int tail = data.length % chunkSize; // length of remainer part
            //int offset = 0;
            String head = Integer.toHexString(chunkSize) + "\r\n"; // first string with chunk size in hex
            dos.flush();

            for (int i = counter; i < n; i++) { // write array of chunks
                dos.write(head.getBytes());
                dos.write(data, offset, chunkSize);
                dos.write("\r\n".getBytes());
                offset += chunkSize;
                counter++;
                return;
            }
            if (tail > 0) { // write tail part
                head = Integer.toHexString(tail) + "\r\n";
                dos.write(head.getBytes());
                dos.write(data, offset, tail);
                dos.write("\r\n".getBytes());
            }

            dos.write("0\r\n\r\n".getBytes());

            headers.add("Transfer-Encoding: chunked\r\n");

        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public int getChunkSize() {return chunkSize;}

    public void setChunkSize(int value) {
        chunkSize = value;
    }

}
