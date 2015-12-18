import java.net.Socket;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;

import java.util.Scanner;

public class Client {
    public void chat(String ip) 
                throws UnknownHostException, IOException {
        Socket socket = new Socket(ip, 33333);
        String ketikanSatuBaris;
        
        try {
            // Ketik
            //int tebak = 0;
            do{
            Scanner keyboard = new Scanner(System.in);
            System.out.println("1.Tambah");
            System.out.println("2.Kurang");
            System.out.println("3.Jumlah");
            System.out.println("4.Selesai");
            System.out.print("Masukkan pilihan : ");
            ketikanSatuBaris = keyboard.nextLine();
            
            
            // Tulis ke socket
            Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
            BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
            keluaranBuff.write(ketikanSatuBaris);
            keluaranBuff.write("\n");
            keluaranBuff.flush();
            
            
            if(ketikanSatuBaris.charAt(0)=='3'){
              System.out.print("Dari server : ");
              Reader masukan = new InputStreamReader(socket.getInputStream()); 
              BufferedReader masukanBuff = new BufferedReader(masukan);
              String baris = masukanBuff.readLine();
              System.out.println(baris);
            }
            
            
           } while(ketikanSatuBaris.charAt(0)!='4');
        }
        catch(IOException salah) {
            System.out.println(salah);
        }
        finally {
            if (socket != null)
            socket.close();
        }
    }
}
