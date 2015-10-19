import java.lang.Exception;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.*;

public class Client implements Runnable {
    private Socket socket;
    private FileManager fm;
    private BufferedReader inClient;
    private ByteArrayOutputStream binOutput = new ByteArrayOutputStream();
    
    public Client(Socket socket, String path) {
        this.socket = socket;
        fm = new FileManager(path);
    }

    private void returnStatusCode(int code, OutputStream os) throws IOException {
        String msg = null;

        switch (code) {
            case 400:
                msg = "HTTP/1.1 400 Bad Request";
                break;
            case 404:
                msg = "HTTP/1.1 404 Not Found";
                break;
            case 500:
                msg = "HTTP/1.1 500 Internal Server Error";
                break;
        }

        byte[] resp = msg.concat("\r\n\r\n").getBytes();
        os.write(resp);
    }
    
    private byte[] getBinaryHeaders(List<String> headers) {
        StringBuilder res = new StringBuilder();

        for (String s : headers) 
            res.append(s);
            
        return res.toString().getBytes();
    }

    private void process3(InputStream is, OutputStream os) throws IOException {

            DataOutputStream out = new DataOutputStream(new FileOutputStream("D:/Temp/_Work/fos.bin"));
            String s = patternSearch(is);
            out.write(s.getBytes());
            /*

            DataOutputStream out = new DataOutputStream(new FileOutputStream("D:/Temp/_Work/fos.bin"));

            int byteRead;
            // Read a raw byte, returns an int of 0 to 255.
            while ((byteRead = is.read()) != -1) {
                // Write the least-significant byte of int, drop the upper 3 bytes
                out.write(byteRead);
            }
            */
        out.flush();
        out.close();


    }

    private String patternSearch(InputStream inputStream){
        //Pattern currently being checked for
        Pattern name = Pattern.compile("^-*WebKitFormBoundary.*[\\r\\n].*[\\r\\n].*[]\\r\\n]+((.|\\r|\\n?)*)[\\r\\n]{1}.*$");

        Matcher m;
        //List<String> arrayList = new ArrayList<String>();

        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader buf = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String str = null;
        String s = null;

        try {
            while ((str = buf.readLine()) != null) {
                sb.append(str);
                }

            m = name.matcher(sb.toString());
            while(m.find()){
                s = m.group();
            }

            return  s;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    private void process2(InputStream is, OutputStream os) throws IOException {
        //InputStreamReader ir = new InputStreamReader(is);
        //System.out.println("Encoding:" + ir.getEncoding());
        inClient = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        String currentLine = inClient.readLine();
        System.out.println("line:" + currentLine);
        StringTokenizer tokenizer = new StringTokenizer(currentLine);
        String httpMethod = tokenizer.nextToken();
        String httpQueryString = tokenizer.nextToken();
        String responseString = null;
        String contentLength;
        //Charset charset = Charset.forName("UTF-8");

        System.out.println("ProcessFile method: " + httpMethod);
        System.out.println("ProcessFile queryString: " + httpQueryString);
        System.out.println("Current line: " + currentLine);

        if (httpMethod.equals("GET")) {
            System.out.println("GET request");
            if (httpQueryString.equals("/")) {
                // The default home page
                responseString = ""
                        + "<html><head><body>"
                        + "<form method = \"POST\" action=\"MyServlet\" enctype=\"multipart/form-data\">"
                        + "<input type=\"file\" name=\"file1\"/>"
                        + "<input type=\"file\" name=\"file2\"/>"
                        + "<input type=\"file\" name=\"file3\"/>"
                        + "<br/><br/>"
                        + "<button type=\"submit\">submit</button>"
                        + "</form></body></head></html>";

                System.out.println("Response string: " + responseString);
            } else {
                responseString = "<b>The Requested resource not found ...." +
                        "Usage: http://127.0.0.1:8080/upload</b>";
            }
            //os.write(responseString.getBytes());
            //returnStatusCode(200, os);

            List<String> headers = new ArrayList<String>();
            headers.add("HTTP/1.1 200 OK\r\n");

            headers.add("Connection: close\r\n\r\n");

            os.write(getBinaryHeaders(headers));
            os.write(responseString.getBytes());

        }
        else if(httpMethod.equals("POST")){

            process3(is,os);
            if (1==1)
                return;

            PrintWriter fout = null;
            FileOutputStream fs;
            String dirname = "D:\\Temp";
            String filename  = null;
            System.out.println("POST request");
            String boundary = "";


            while(true){
                currentLine = inClient.readLine();
                if (currentLine.indexOf("Content-Type: multipart/form-data") != -1) {
                    boundary = currentLine.split("boundary=")[1];
                    break;
                }
            }

            do {
                //currentLine = inClient.readLine();
                //System.out.println(currentLine);


                while (true) {
                    currentLine = inClient.readLine();
                    if (currentLine.indexOf("--" + boundary) != -1) {
                        filename = inClient.readLine().split("filename=")[1].replaceAll("\"", "");
                        String [] filelist = filename.split("\\" + System.getProperty("file.separator"));
                        filename = filelist[filelist.length - 1];
                        System.out.println("File to be uploaded = " + filename);
                        break;
                    }
                }

                String fileContentType = inClient.readLine().split(" ")[1];
                System.out.println("File content type = " + fileContentType);

                inClient.readLine(); //assert(inFromClient.readLine().equals("")) : "Expected line in POST request is "" ";

                fout = new PrintWriter(dirname + "\\" + filename);
                fs = new  FileOutputStream(dirname + "\\" + filename);
                Base64OutputStream bs64Out = new Base64OutputStream(new FileOutputStream(dirname + "\\" + filename));

                String prevLine = inClient.readLine();
                currentLine = inClient.readLine();

                //Here we upload the actual file contents
                while (true) {
                    System.out.println(prevLine);
                    if (currentLine.indexOf("--" + boundary) != -1) {
                        //fout.print(prevLine);
                        //fs.write(prevLine.getBytes());
                        bs64Out.write(prevLine.getBytes());
                        break;
                    }
                    else {
                        //fs.write(prevLine.getBytes());
                        bs64Out.write(prevLine.getBytes());
                        //fout.println(prevLine.getBytes());
                    }
                    prevLine = currentLine;
                    currentLine = inClient.readLine();
                }
                bs64Out.flush();
                bs64Out.close();
                //fout.flush();
                //fout.close();

                responseString = "<html><body>File " + filename + " has been uploaded </body></html>";

                List<String> headers = new ArrayList<String>();
                headers.add("HTTP/1.1 200 OK\r\n");
                headers.add("Connection: close\r\n\r\n");

                os.write(getBinaryHeaders(headers));
                os.write(responseString.getBytes());

             //if
            }while (inClient.ready()); //End of do-while


        }
    }
    
    private void process(String request, OutputStream os) throws IOException {
        //System.out.println(request);
        //System.out.println("---------------------------------------------");
        //System.out.println("New request created. Time:" + new Date().toString());

        int idx = request.indexOf("\r\n");
        request = request.substring(0, idx);

        String[] parts = request.split(" ");
        if (parts.length != 3) {
            returnStatusCode(400, os);
            return;
        }

        String method = parts[0], url = parts[1], version = parts[2];

        if (method.equalsIgnoreCase("POST")){
            System.out.println("------------------------------------------------");
            System.out.println("              RECEIVED POST REQUEST");
            System.out.println("url: " + url);
            System.out.println("version: " + version);
            InputStream is = socket.getInputStream();

            int size = is.available();
            System.out.println("------------------------------------------------");
        }

        if (url.contains("favicon.ico")) {
            returnStatusCode(400, os);
            return;
        }
        
        if (( ! version.equalsIgnoreCase("HTTP/1.0")) && ( ! version.equalsIgnoreCase("HTTP/1.1"))) {
            returnStatusCode(400, os);
            return;
        }
        if ( ! method.equalsIgnoreCase("GET")) {
            returnStatusCode(400, os);
            return;
        }
        if ("/".equals(url))
            url = "/index.html";
        
        List<String> headers = new ArrayList<String>();
        headers.add("HTTP/1.1 200 OK\r\n");
        
        byte[] content = fm.get(url);
        if (content == null) {
            returnStatusCode(404, os);
            return;
        }
        
        ProcessorsList pl = new ProcessorsList();
        pl.add(new Compressor(6));
        pl.add(new Chunker(30)); // comment
        content = pl.process(content, headers);

        if (content == null) {
            returnStatusCode(500, os);
            return;
        }

        // uncomment next line
        // headers.add("Content-Length: " + content.length + "\r\n");
        headers.add("Connection: close\r\n\r\n");
            
        os.write(getBinaryHeaders(headers));
        os.write(content);
    }

    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            process2(is,os);


        } catch (Exception ex) {
            //ex.printStackTrace();
            return;
        }
    }
}