import java.net.Socket;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.Reader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.util.Scanner;

public class Chat {    
    public void send()
                throws UnknownHostException, IOException {
        Socket socket2 = new Socket("192.168.43.218", 33333);//dibawah kominfo untuk registrasi utama diIndonesia protocol whois di port 43

        try{
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Pesan: ");
            String pesan = keyboard.nextLine();
                    
            // Tulis ke socket
            Writer keluaranWriter = new OutputStreamWriter(socket2.getOutputStream()); 
            BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
            keluaranBuff.write(pesan);
            keluaranBuff.write("\n");
            keluaranBuff.flush();
                
            // Baca dari Server
            System.out.print("Server: ");
            Reader masukan = new InputStreamReader(socket2.getInputStream()); 
            BufferedReader masukanBuff = new BufferedReader(masukan);
            String baris = masukanBuff.readLine();
            System.out.println(baris);    
            
            baris = baris.toUpperCase();
            
            keluaranBuff.write(baris);
            keluaranBuff.flush();
        }
        catch(IOException salah) {
            System.out.println(salah);
        }
        finally {
            if (socket2 != null)
            socket2.close();
        }
    }
}

            
       