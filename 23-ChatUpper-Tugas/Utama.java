import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;
public class Utama {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            Chat kirim = new Chat();
            System.out.println ("Masukkan pesan :"); String pesan = in.nextLine();
            kirim.send(pesan);
            
        }
        catch (UnknownHostException ex) {
            System.err.println(ex);
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
