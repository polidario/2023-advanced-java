import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

public class TestHTTPService {

    public static void main(String[] args) throws IOException {
        testHTTPServer();
    }

    @Test
    public static void testHTTPServer() throws IOException {
        HttpServer server = HttpServer.create(
                new InetSocketAddress("0.0.0.0", 80), 0);
        server.createContext("/", exchange -> {
            byte[] requestBytes = exchange.getRequestBody().readAllBytes();
            System.out.println( new String(requestBytes) );

            URI uri = exchange.getRequestURI();
            String response = "Hello " + uri.getPath();
            String ok = "ok";
            exchange.sendResponseHeaders(200, ok.length());

            exchange.close();
        });

        server.start();
    }
}
