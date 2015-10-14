import java.lang.Thread;

public class Main {
    public static void main(String[] args) {
        final HTTPServer server = new HTTPServer(8080,"C:\\Users\\Yegor2\\IdeaProjects\\JavaPro\\Lab 2.2\\src");
        server.start();

        System.out.println("Server 2 started...");

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                server.stop();
                System.out.println("Server stopped!");
            }
        });
    }
}
