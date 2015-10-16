import java.lang.Exception;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Client implements Runnable {
    private Socket socket;
    private FileManager fm;
    private BufferedReader inClient;
    
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

    private void process2(InputStream is, OutputStream os) throws IOException {
        inClient = new BufferedReader(new InputStreamReader(is));
        String currentLine = inClient.readLine();
        StringTokenizer tokenizer = new StringTokenizer(currentLine);
        String httpMethod = tokenizer.nextToken();
        String httpQueryString = tokenizer.nextToken();
        String responseString = null;

        System.out.println("ProcessFile method: " + httpMethod);
        System.out.println("ProcessFile queryString: " + httpQueryString);
        System.out.println("Current line: " + currentLine);

        if (httpMethod.equals("GET")) {
            System.out.println("GET request");
            if (httpQueryString.equals("/")) {
                // The default home page
                responseString =
                        "<form action=\"http://127.0.0.1:5000\" enctype=\"multipart/form-data\"" +
                                "method=\"post\">" +
                                "Enter the name of the File <input name=\"file\" type=\"file\"><br>" +
                                "<input value=\"Upload\" type=\"submit\"></form>" +
                                "Upload only text files.";
            } else {
                responseString = "<b>The Requested resource not found ...." +
                        "Usage: http://127.0.0.1:5000</b>";
            }
            os.write(responseString.getBytes());
            returnStatusCode(200, os);

        }
        else if(httpMethod.equals("POST")){
            System.out.println("POST request");

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