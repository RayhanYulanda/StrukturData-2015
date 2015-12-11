
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.Reader;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.BufferedInputStream;



import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Client {
    
    public void chat() 
                throws UnknownHostException, IOException {
        Socket koneksi = new Socket("google.co.id",80);
        try {         
            // Tulis ke socket
            Writer keluaranWriter = new OutputStreamWriter(koneksi.getOutputStream()); 
            BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
            keluaranBuff.write("GET index.html");
            //keluaranBuff.write("\r\n");
            keluaranBuff.newLine();
            keluaranBuff.flush();
                
            // Baca dari Server
            // 1 & 2. Minta socket untuk baca -> Langsung dibuka
            InputStream masukan = koneksi.getInputStream();
            // Karena keluarannya panjang sehingga harus dibuffer
            BufferedInputStream masukanBuffer = new BufferedInputStream(masukan);
        
            System.out.print("Server: ");
        
           // Selagi masih ada data baca 
         
           int data = masukanBuffer.read();
           while (data != -1) {
               System.out.write((char) data);
               data = masukanBuffer.read();                       
            }  
        }
        catch(IOException salah) {
            System.out.println(salah);
        }
        finally {
            if (koneksi != null)
             koneksi.close();
        }
    }
}
