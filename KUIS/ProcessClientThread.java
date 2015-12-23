import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class ProcessClientThread implements Runnable {
    private Socket koneksi=null; 
    Calendar kalender = Calendar.getInstance();
    
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
            String waktu;
            
            // Ambil dan tampilkan masukan
            InputStream masukan = koneksi.getInputStream();
            BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
            
               
           String perintah = masukanReader.readLine();
           System.out.println("Dari Client "+ip+": "+perintah);
           perintah = perintah.toUpperCase();
           String words[]=perintah.split(" ");
            
          // Tangani perintahnya
          if (perintah.compareTo("SIAPA") == 0){
            OutputStream keluaran = koneksi.getOutputStream();
            BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
            keluaranBuf.write(ip);
            keluaranBuf.newLine();
            keluaranBuf.flush();
          }
          else if(perintah.compareTo("WAKTU")==0){ 
            if(words.length==2){
             OutputStream keluaran = koneksi.getOutputStream();
             BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
             int n = Integer.parseInt(words[1]);
             kalender.add(Calendar.HOUR_OF_DAY,n);
             waktu = kalender.getTime().toString();
             keluaranBuf.write(waktu); 
            }
            else{
               waktu = kalender.getTime().toString();
               OutputStream keluaran = koneksi.getOutputStream();
               BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
               keluaranBuf.write(waktu);
               keluaranBuf.newLine();
               keluaranBuf.flush();
            }
          }
           
          /*else if(words[1].equals("salah")){
             OutputStream keluaran = koneksi.getOutputStream();
             BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
             int n = Integer.parseInt(words[1]);
             kalender.add(Calendar.HOUR_OF_DAY,n);
             waktu = kalender.getTime().toString();
             keluaranBuf.write(waktu);
            }*/
          else{
            OutputStream keluaran = koneksi.getOutputStream();
            BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
            keluaranBuf.write("Perintah tidak dikenal");
            keluaranBuf.newLine();
            keluaranBuf.flush();
          }            

          koneksi.close();
            // Tunggu 2 detik
            System.out.println("Tunggu...");
            Thread.sleep(2000);
            System.out.println("Selesai tunggu...");
          }
          catch(IOException err) {
            System.out.println(err);
          }
          catch(InterruptedException err) {
            System.out.println(err);
          }
    }
}