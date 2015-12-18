import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

public class ProcessClientThread implements Runnable {
    
    int jumlah=0;
    String kirimm="";
    
    public ProcessClientThread(Socket koneksiKiriman) {
        koneksi = koneksiKiriman;
    }

    public void run() {
        if (koneksi != null)
            prosesPermintaanClient();
    }

    private void prosesPermintaanClient() {
      // synchronized(kirimm) {
        try {
            String ip = koneksi.getInetAddress().getHostAddress();
            
            // Ambil dan tampilkan masukan
            InputStream masukan = koneksi.getInputStream();
            BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
            
            while(true){
               masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
               String baris = masukanReader.readLine();
               System.out.println("Client "+ip+": " +baris);
               
               if(baris.charAt(0)=='1'){
                 jumlah++;
              }
              
              else if(baris.charAt(0)=='2'){
                 jumlah--;
              }
              else if(baris.charAt(0)=='3'){
                OutputStream keluaran = koneksi.getOutputStream();
                BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                kirimm = Integer.toString(jumlah);
                keluaranBuf.write(kirimm);
                keluaranBuf.newLine();
                keluaranBuf.flush();
              }
              else if(baris.charAt(0)=='4'){
                //    koneksi.close();
                  break;
              }
            }
           
            koneksi.close();
            
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
        private Socket koneksi; 
    }

  