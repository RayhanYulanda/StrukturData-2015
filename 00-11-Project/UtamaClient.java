import java.io.IOException;
import java.util.Scanner;
import java.net.UnknownHostException;

public class UtamaClient {
    public static void main(String[] args)
                  throws UnknownHostException, IOException {
        Scanner in = new Scanner(System.in);
        Client client = new Client();
        System.out.print("Masukkan IP server : ");String ip = in.nextLine();
        client.chat(ip);
    }
}
