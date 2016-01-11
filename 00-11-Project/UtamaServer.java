import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;

public class UtamaServer {
    public static void main(String[] args) 
                       throws UnknownHostException, IOException {
        server server = new server();
        server.dengar();
    }
}
