import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

public class ProcessClientThread implements Runnable {
    
    public ProcessClientThread(Socket koneksiKiriman) {
        koneksi = koneksiKiriman;
    }

    public void run() {
        if (koneksi != null)
            prosesPermintaanClient();
    }

    private void prosesPermintaanClient() {
       
        try {
            String ip = koneksi.getInetAddress().getHostAddress();
            String benar = "Selamat! anda benar";
            String salah = "Jawaban anda salah!";
            // Ambil dan tampilkan masukan
            InputStream masukan = koneksi.getInputStream();
            BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
            

            int banyaktebak=0;
            while(banyaktebak!=3){
               masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
               String baris = masukanReader.readLine();
               System.out.println("Tebakan client "+ip+": "+baris);
               
               if(baris.charAt(0)== '7'){
                 // Kirim ke client
                 OutputStream keluaran = koneksi.getOutputStream();
                 BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                 keluaranBuf.write(benar);
                 keluaranBuf.newLine();
                 keluaranBuf.flush();
                 koneksi.close();
                 break;
              }
              
              else{
                 // Kirim ke client
                 OutputStream keluaran = koneksi.getOutputStream();
                 BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                 keluaranBuf.write(salah);
                 keluaranBuf.newLine();
                 banyaktebak++;
                 keluaranBuf.flush();
              }
            }
            

            // Tunggu 2 detik
            System.out.println("Tunggu...");
            Thread.sleep(2000);
            System.out.println("Selesai tunggu...");
            
            koneksi.close();
        }
        catch(IOException err) {
            System.out.println(err);
        }
        catch(InterruptedException err) {
            System.out.println(err);
        }
    }

    private Socket koneksi; 
}