import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

class WrapObject
{
    byte[] bytes;
    long time = 0;
    public WrapObject(byte[] b, long t)
    {
        this.bytes = b;
        this.time = t;
    }
}
public class FileManager {
    private String path;
    private static ConcurrentHashMap<String, WrapObject> map = new ConcurrentHashMap<String,WrapObject>();
    private  static final long TTL = 1000*60;

    public FileManager(String path) {
        // "c:\folder\" --> "c:\folder"
        if (path.endsWith("/") || path.endsWith("\\"))
            path = path.substring(0, path.length() - 1);

        this.path = path;
    }

    public byte[] get(String url) {
        try {

            System.out.println("New request created, time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            WrapObject wrapObject = map.get(url);
            //byte[] buf = map.get(url);
            byte[] buf = null;
            long elapsed = 0;

            if (wrapObject!=null)
            {
                buf = wrapObject.bytes;
                elapsed = System.currentTimeMillis() - wrapObject.time;
            }
            if (buf != null && elapsed <= TTL) // in cache
            {
                System.out.println("elapsed time: (" + Math.round(elapsed/1000) + ") seconds, do read from buffer");
                return buf;
            }

            System.out.println("elapsed time: (" + Math.round(elapsed/1000) + ") seconds, do read from file");

            // "c:\folder" + "/index.html" -> "c:/folder/index.html"
            String fullPath = path.replace('\\', '/') + url;

            RandomAccessFile f = new RandomAccessFile(fullPath, "r");
            try {
                buf = new byte[(int) f.length()];
                f.read(buf, 0, buf.length);
            } finally {
                f.close();
            }

            map.put(url, new WrapObject(buf,System.currentTimeMillis())); // put to cache
            return buf;
        } catch (IOException ex) {
            //ex.printStackTrace();
            return null;
        }
    }
}
