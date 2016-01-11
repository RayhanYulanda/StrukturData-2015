import java.net.Socket;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;

import java.io.*;

import java.util.Scanner;

public class Client {
    public void chat(String ip) 
                throws UnknownHostException, IOException {
        Socket socket = new Socket(ip, 33333);
        String ketikanSatuBaris;
        
        try {

            do{
              Scanner keyboard = new Scanner(System.in);
              System.out.println("\n\n================== Hermes Mall Parking ==================");
              System.out.println("\tMasukkan perintah sesuai dengan intruksi\n");
              System.out.println("1. Jika ada kendaraan yang masuk, perintahnya :");
              System.out.println("\n\tMASUK TIPEKENDARAAN PLAT_NO.PLAT\n\n");
              System.out.println("2. Jika ada kendaraan yang keluar, perintahnya :");
              System.out.println("\n\tKELUAR TIPEKENDARAAN PLAT_NO.PLAT/NOMOR.KARCIS\n\n");
              System.out.println("3. Jika ingin melihat data kendaraan di parkiran, perintahnya :");
              System.out.println("\n\tDATA TIPEKENDARAAN\n\n");
              System.out.println("4. Jika ingin melihat semua data kendaraan, perintahnya :");
              System.out.println("\n\tDATAALL TIPEKENDARAAN\n\n");
              System.out.println("5. Perintah \"Close\" untuk keluar dari program\n");
              System.out.println("==========================================================");
              System.out.println("\nNote : Gunakan no.plat jika karcis hilang\n\tdan dikenakan denda sebesar 15.000");
              System.out.print("\nMasukkan perintah : ");
              ketikanSatuBaris = keyboard.nextLine();
              ketikanSatuBaris = ketikanSatuBaris.trim().toUpperCase();
              
              String [] perintah = ketikanSatuBaris.split(" ");
              
              if(ketikanSatuBaris.compareTo("CLOSE")==0){
                Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
                BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
                keluaranBuff.write(ketikanSatuBaris);
                keluaranBuff.write("\n");
                keluaranBuff.flush();
                break;
             }
             
             else if(perintah[0].compareTo("DATA")==0 || perintah[0].compareTo("DATAALL")==0){
              if(perintah.length==2){
               if(perintah[1].compareTo("MOTOR")==0||perintah[1].compareTo("MOBIL")==0||perintah[1].compareTo("BECAK")==0){
                Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
                BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
                keluaranBuff.write(ketikanSatuBaris);
                keluaranBuff.write("\n");
                keluaranBuff.flush();
               
                Reader masukan = new InputStreamReader(socket.getInputStream()); 
                BufferedReader masukanBuff = new BufferedReader(masukan);
                String baris = masukanBuff.readLine();
                int banyakdata = Integer.parseInt(baris);
               
                if(banyakdata==0 && perintah[0].compareTo("DATA")==0 ){
                  System.out.println("\nTidak ada kendaraan "+perintah[1]+" pada parkiran");
                }
                
                if(banyakdata==0 && perintah[0].compareTo("DATAALL")==0 ){
                  System.out.println("\nTidak ada kendaraan "+perintah[1]+" yang masuk");
                }
               
                 while(banyakdata!=0){
                  Reader masukann = new InputStreamReader(socket.getInputStream()); 
                  BufferedReader masukannBuff = new BufferedReader(masukan);
                  String bariss="";
                  bariss = masukannBuff.readLine();
                  String [] data = bariss.split(" ");
                  System.out.print(data[0]+" "+data[1]+"\t"+gantispasi(data[2]));
                  System.out.println();
                  banyakdata--;
                }
               }
               else{
                System.out.println("\nTipe kendaraan tidak terdata");
               }
              }
              else{
                System.out.println("\nPerintah untuk data salah");
              }
            }
             
            else if(perintah[0].compareTo("KELUAR")==0){
              Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
              BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
              keluaranBuff.write(ketikanSatuBaris);
              keluaranBuff.write("\n");
              keluaranBuff.flush();
              
              Reader masukan = new InputStreamReader(socket.getInputStream()); 
              BufferedReader masukanBuff = new BufferedReader(masukan);
              String baris="";
              baris = masukanBuff.readLine();
              String [] keluar = baris.split(" ");
            
              if(keluar[0].equals("Ada")){
                if(keluar[1].equals("Sepeda")){
                  System.out.println("\n\n================= Hermes Mall Parking =================");
                  System.out.println("\t\t\tKARCIS KELUAR\n\n"); 
                  System.out.println("Tipe kendaraan\t: "+keluar[1]); 
                  System.out.println("Pintu Masuk\t: "+keluar[2]);
                  System.out.println("Nomor Karcis\t: "+keluar[3]);
                  System.out.println("Waktu Masuk\t: "+gantispasi(keluar[5]));
                  System.out.println("Waktu Keluar\t: "+gantispasi(keluar[6]));
                  System.out.println("\n\t\t    Rp. "+keluar[4]);
                  System.out.println("\n\n\tTerima kasih sudah berkunjung");
                  System.out.println("\tSemoga sampai tujuan dengan selamat");
                  System.out.println("=========================================================\n");
                }
                else if (keluar[1].equals("Mobil") || keluar[1].equals("Motor") || keluar[1].equals("Becak")){
                  System.out.println("\n\n================= Hermes Mall Parking =================");
                  System.out.println("\t\t\tKARCIS KELUAR\n\n"); 
                  System.out.println("Tipe kendaraan\t: "+keluar[1]); 
                  System.out.println("Plat\t\t: "+gantispasi(keluar[2]));
                  System.out.println("Pintu Masuk\t: "+keluar[3]);
                  System.out.println("Nomor Karcis\t: "+keluar[4]);
                  System.out.println("Waktu Masuk\t: "+gantispasi(keluar[6]));
                  System.out.println("Waktu Keluar\t: "+gantispasi(keluar[7]));
                  System.out.println("\n\n\t\t    Rp. "+keluar[5]);
                  if(keluar.length==9){
                    System.out.println("\nKarena karcis hilang, anda dikenakan denda sebesar");
                    System.out.println("\n\t\t    Rp. 15.000");
                    System.out.println("\nTotal\t:\t    Rp. "+(Integer.parseInt(keluar[5])+15000));
                  }
                  System.out.println("\n\n\tTerima kasih sudah berkunjung");
                  System.out.println("\tSemoga sampai tujuan dengan selamat");
                  System.out.println("=========================================================\n");
                }
              }
              else{
                  System.out.println(baris);
              }
            }
            else{
              Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
              BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
              keluaranBuff.write(ketikanSatuBaris);
              keluaranBuff.write("\n");
              keluaranBuff.flush();
            
              //terima dari server kemungkinan banyak, ada kemungkinan kirim karcis atau kesalahan
              //jika sudah di split dengan # panjangnya 1 maka kemungkinan adanya kesalahan
              //jika panjangnya lebih dari satu maka itu terimaan untuk karcis masuk
              
              System.out.print("\nDari server : ");
              Reader masukan = new InputStreamReader(socket.getInputStream()); 
              BufferedReader masukanBuff = new BufferedReader(masukan);
              String baris="";
              baris = masukanBuff.readLine();
              String [] tiketmasuk = baris.split("#");
              if(tiketmasuk.length>1){
              System.out.println("\n\n================= Hermes Mall Parking =================");
              System.out.println("\t\t\tKARCIS MASUK\n\n"); 
              if(tiketmasuk[1].equals("Mobil") || tiketmasuk[1].equals("Motor") || tiketmasuk[1].equals("Becak")){
               System.out.println("Plat\t\t: "+gantispasi(tiketmasuk[2]));
              }
              System.out.println("Pintu Masuk\t: "+tiketmasuk[3]);
              System.out.println("Nomor Karcis\t: "+tiketmasuk[4]);
              System.out.println("Waktu Masuk\t: "+gantispasi(tiketmasuk[5])+"\n");
              System.out.println("\n\t"+tiketmasuk[0]+"\n");
              System.out.println("=========================================================\n");
             }
             //jika panjang tiket masuk hanya terdiri dari 1 string
             else{
              System.out.println(baris);
             }
            }
           } while(true);
        }
        catch(IOException salah) {
            System.out.println(salah);
        }
        finally {
            if (socket != null)
             socket.close();
        }
    }
    
    public String gantispasi(String gantii){
        int index=0;
        String gantifix="";
        while(index<gantii.length()){
            if(gantii.charAt(index)=='_'){
                gantifix=gantifix+' ';
            }
            else{
                gantifix=gantifix+gantii.charAt(index);
            }
            index++;
        }
        return gantifix;
    }
}
