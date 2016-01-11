import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;

public class ProcessClientThread implements Runnable {
   private Socket koneksi; 
   Calendar kalender = Calendar.getInstance();
   final int maxmotor=10;
   final int maxmobil=7;
   final int maxbecak=7;
   final int maxsepeda=15;

   //untuk data yang masuk dan keluar
   static ArrayList<Becak> becakall = new ArrayList<Becak>();
   static ArrayList<Mobil> Carall = new ArrayList<Mobil>();
   static ArrayList<Motor> Motorcycleall = new ArrayList<Motor>();
   
   static int nomorparkiranmobil=0;
   static int nomorparkiranmotor=0;
   static int nomorparkiranbecak=0;
   static int nomorparkiransepeda=0;
   
   //untuk data yang hanya ada pada mall
   static ArrayList<Becak> becak = new ArrayList<Becak>();
   static ArrayList<Mobil> Car = new ArrayList<Mobil>();
   static ArrayList<Motor> Motorcycle = new ArrayList<Motor>();
   static ArrayList<Sepeda> sepeda = new ArrayList<Sepeda>();
   
   String kirimm="";
    
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
            
            // Ambil dan tampilkan masukan
            InputStream masukan = koneksi.getInputStream();
            BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
            
            while(true){
               masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
               String baris = masukanReader.readLine();
               System.out.println("Client "+ip+": " +baris);
               
               baris = baris.trim().toUpperCase();
               String [] perintah = baris.split(" ");
          synchronized(this) {   
            if(perintah[0].compareTo("MASUK") == 0){
                 //masuk motor,mobil, atau becak
                 if(perintah.length==3){
                   if(perintah[1].compareTo("MOBIL") == 0 && Car.size()<maxmobil){
                       if(Character.isLetter(perintah[2].charAt(0))){
                          nomorparkiranmobil++;
                          Mobil c = new Mobil(perintah[2],"PM01",nomorparkiranmobil);
                          Car.add(c);
                          Carall.add(c);
                          OutputStream keluaran = koneksi.getOutputStream();
                          BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                          keluaranBuf.write("Selamat bersenang-senang di mall kami#Mobil"+"#"+c.plat+"#"+c.pintumasuk+"#"+c.kodeparkir+"#"+c.strcalendarin);
                          keluaranBuf.newLine();
                          keluaranBuf.flush();
                       }
                       else{
                         OutputStream keluaran = koneksi.getOutputStream();
                         BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                         keluaranBuf.write("Plat kendaraan tidak valid");
                         keluaranBuf.newLine();
                         keluaranBuf.flush();
                       }
                   }
                   else if(perintah[1].compareTo("MOTOR") == 0 && Motorcycle.size()<maxmotor){
                      if(Character.isLetter(perintah[2].charAt(0))){
                         nomorparkiranmotor++;
                         Motor m = new Motor(perintah[2],"PM02",nomorparkiranmotor);
                         Motorcycle.add(m);
                         Motorcycleall.add(m);
                         OutputStream keluaran = koneksi.getOutputStream();
                         BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                         keluaranBuf.write("Selamat bersenang-senang di mall kami#Motor"+"#"+m.plat+"#"+m.pintumasuk+"#"+m.kodeparkir+"#"+m.strcalendarin);
                         keluaranBuf.newLine();
                         keluaranBuf.flush();
                      }
                       else{
                         OutputStream keluaran = koneksi.getOutputStream();
                         BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                
                         keluaranBuf.write("Plat kendaraan tidak valid");
                         keluaranBuf.newLine();
                         keluaranBuf.flush();
                      }
                   }
                   else if(perintah[1].compareTo("BECAK") == 0 && becak.size()<maxbecak){
                      if(Character.isLetter(perintah[2].charAt(0))){
                         nomorparkiranbecak++;
                         Becak b = new Becak(perintah[2],"PM03",nomorparkiranbecak);
                         becak.add(b);
                         becakall.add(b);
                         OutputStream keluaran = koneksi.getOutputStream();
                         BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                         keluaranBuf.write("Selamat bersenang-senang di mall kami#Becak"+"#"+b.plat+"#"+b.pintumasuk+"#"+b.kodeparkir+"#"+b.strcalendarin);
                         keluaranBuf.newLine();
                         keluaranBuf.flush();
                      }
                       else{
                         OutputStream keluaran = koneksi.getOutputStream();
                         BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                
                         keluaranBuf.write("Plat kendaraan tidak valid");
                         keluaranBuf.newLine();
                         keluaranBuf.flush();
                      }
                   }
                   else if(perintah[1].compareTo("MOTOR") != 0 && perintah[1].compareTo("MOBIL") != 0 && perintah[1].compareTo("BECAK") != 0){
                     OutputStream keluaran = koneksi.getOutputStream();
                     BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                
                     keluaranBuf.write("Tipe kendaraan tidak terdata");
                     keluaranBuf.newLine();
                     keluaranBuf.flush();
                   }
                   else if(Car.size()== maxmobil|| Motorcycle.size()== maxmotor || becak.size()== maxbecak){
                    OutputStream keluaran = koneksi.getOutputStream();
                    BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                
                    keluaranBuf.write("Maaf sekarang parkiran sedang penuh");
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                   }
                 }
                 //masuk sepeda
                 else if (perintah.length==2){
                    if(perintah[1].compareTo("SEPEDA") == 0 && sepeda.size()<maxsepeda){
                     nomorparkiransepeda++;
                     Sepeda s = new Sepeda("PM04",nomorparkiransepeda);
                     sepeda.add(s);
                     OutputStream keluaran = koneksi.getOutputStream();
                     BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                     keluaranBuf.write("Selamat bersenang-senang di mall kami#Sepeda"+"#"+"tidak ada plat"+"#"+s.pintumasuk+"#"+s.kodeparkir+"#"+s.strcalendarin);
                     keluaranBuf.newLine();
                     keluaranBuf.flush();
                   }
                   else if(perintah[1].compareTo("SEPEDA") != 0){
                     OutputStream keluaran = koneksi.getOutputStream();
                     BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                
                     keluaranBuf.write("Perintah salah");
                     keluaranBuf.newLine();
                     keluaranBuf.flush();
                   }
                   else if(sepeda.size()== maxsepeda){
                    OutputStream keluaran = koneksi.getOutputStream();
                    BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                
                    keluaranBuf.write("Maaf sekarang parkiran sedang penuh");
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                   }
                   else{
                    OutputStream keluaran = koneksi.getOutputStream();
                    BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                
                    keluaranBuf.write("Perintah salah");
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                   }
                 }
                 else{
                   OutputStream keluaran = koneksi.getOutputStream();
                   BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                
                   keluaranBuf.write("Perintah masih kurang atau berlebih, periksa kembali perintah yang dikirim");
                   keluaranBuf.newLine();
                   keluaranBuf.flush();
                 }
            }
              
            else if(perintah[0].compareTo("KELUAR") == 0){
                 if(perintah.length==3){
                   if(perintah[1].compareTo("MOBIL") == 0){
                       if(Character.isLetter(perintah[2].charAt(0))){
                           //cari dulu ada atau gak mobilnya
                           int ada = 0;
                           int index = 0;
                           while(index < Car.size()) {
                               if(Car.get(index).plat.equals(perintah[2]) || Car.get(index).kodeparkir.equals(perintah[2])){
                                   ada = 1;
                                   break;
                               }
                               index++;
                           }
                           
                           //kalo ada kirim data-datanya
                           if(ada==1){
                               OutputStream keluaran = koneksi.getOutputStream();
                               BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
                               int bayarmobil = Car.get(index).hitungbayar();
                               String semuadatamobil ="Ada Mobil "+Car.get(index).plat+" "+Car.get(index).pintumasuk+" "+Car.get(index).kodeparkir+" "+bayarmobil;
                               semuadatamobil = semuadatamobil+" "+Car.get(index).strcalendarin+" "+Car.get(index).strcalendarout;
                               if(Car.get(index).plat.equals(perintah[2])){
                                   semuadatamobil=semuadatamobil+" denda";
                               }
                               keluaranBuf.write(semuadatamobil);
                               keluaranBuf.newLine();
                               keluaranBuf.flush();
                               Car.remove(index);
                           }
                           else{
                               OutputStream keluaran = koneksi.getOutputStream();
                               BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                               keluaranBuf.write("Mobil tidak terdaftar !");
                               keluaranBuf.newLine();
                               keluaranBuf.flush();
                           }
                           
                       }
                       else{
                         OutputStream keluaran = koneksi.getOutputStream();
                         BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                         keluaranBuf.write("Plat kendaraan tidak valid");
                         keluaranBuf.newLine();
                         keluaranBuf.flush();
                       }
                   }
                   else if(perintah[1].compareTo("MOTOR") == 0){
                      if(Character.isLetter(perintah[2].charAt(0))){
                         //cari dulu ada atau gak motornya
                           int ada = 0;
                           int index = 0;
                         while(index < Motorcycle.size()) {
                               if(Motorcycle.get(index).plat.equals(perintah[2]) || Motorcycle.get(index).kodeparkir.equals(perintah[2])){
                                   ada = 1;
                                   break;
                               }
                               index++;
                         }
                           
                           //kalo ada kirim data-datanya
                         if(ada==1){
                               OutputStream keluaran = koneksi.getOutputStream();
                               BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
                               int bayarmotor = Motorcycle.get(index).hitungbayar();
                               String semuadatamotor ="Ada Motor "+Motorcycle.get(index).plat+" "+Motorcycle.get(index).pintumasuk+" "+Motorcycle.get(index).kodeparkir+" "+bayarmotor;
                               semuadatamotor = semuadatamotor+" "+Motorcycle.get(index).strcalendarin+" "+Motorcycle.get(index).strcalendarout;
                               keluaranBuf.write(semuadatamotor);
                               keluaranBuf.newLine();
                               keluaranBuf.flush();
                               Motorcycle.remove(index);
                         }
                         else{
                               OutputStream keluaran = koneksi.getOutputStream();
                               BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                               keluaranBuf.write("Motor tidak terdaftar !");
                               keluaranBuf.newLine();
                               keluaranBuf.flush();
                         }
                      }
                       else{
                         OutputStream keluaran = koneksi.getOutputStream();
                         BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                
                         keluaranBuf.write("Plat kendaraan tidak valid");
                         keluaranBuf.newLine();
                         keluaranBuf.flush();
                      }
                   }
                   else if(perintah[1].compareTo("BECAK") == 0){
                      if(Character.isLetter(perintah[2].charAt(0))){
                         //cari dulu ada atau gak motornya
                           int ada = 0;
                           int index = 0;
                         while(index < becak.size()) {
                               if(becak.get(index).plat.equals(perintah[2]) || becak.get(index).kodeparkir.equals(perintah[2])){
                                   ada = 1;
                                   break;
                               }
                               index++;
                         }
                           
                           //kalo ada kirim data-datanya
                         if(ada==1){
                               OutputStream keluaran = koneksi.getOutputStream();
                               BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
                               int bayarbecak = becak.get(index).hitungbayar();
                               String semuadatabecak ="Ada Becak "+becak.get(index).plat+" "+becak.get(index).pintumasuk+" "+becak.get(index).kodeparkir+" "+bayarbecak;
                               semuadatabecak = semuadatabecak+" "+becak.get(index).strcalendarin+" "+becak.get(index).strcalendarout;
                               keluaranBuf.write(semuadatabecak);
                               keluaranBuf.newLine();
                               keluaranBuf.flush();
                               becak.remove(index);
                         }
                         else{
                               OutputStream keluaran = koneksi.getOutputStream();
                               BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                               keluaranBuf.write("Becak tidak terdaftar !");
                               keluaranBuf.newLine();
                               keluaranBuf.flush();
                         }
                      }
                       else{
                         OutputStream keluaran = koneksi.getOutputStream();
                         BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                
                         keluaranBuf.write("Plat kendaraan tidak valid");
                         keluaranBuf.newLine();
                         keluaranBuf.flush();
                      }
                   }
                   else if(perintah[1].compareTo("SEPEDA") == 0){
                         //cari dulu ada atau gak sepedanya
                           int ada = 0;
                           int index = 0;
                         while(index < sepeda.size()) {
                               if(sepeda.get(index).kodeparkir.equals(perintah[2])){
                                   ada = 1;
                                   break;
                               }
                               index++;
                         }
                           
                           //kalo ada kirim data-datanya
                         if(ada==1){
                               OutputStream keluaran = koneksi.getOutputStream();
                               BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
                               int bayarsepeda = sepeda.get(index).hitungbayar();
                               String semuadatasepeda ="Ada Sepeda "+sepeda.get(index).pintumasuk+" "+sepeda.get(index).kodeparkir+" "+bayarsepeda;
                               semuadatasepeda = semuadatasepeda+" "+sepeda.get(index).strcalendarin+" "+sepeda.get(index).strcalendarout;
                               keluaranBuf.write(semuadatasepeda);
                               keluaranBuf.newLine();
                               keluaranBuf.flush();
                               sepeda.remove(index);
                         }
                         else{
                               OutputStream keluaran = koneksi.getOutputStream();
                               BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                               keluaranBuf.write("Sepeda tidak terdaftar !");
                               keluaranBuf.newLine();
                               keluaranBuf.flush();
                         }
                   }
                   else{
                     OutputStream keluaran = koneksi.getOutputStream();
                     BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                
                     keluaranBuf.write("Tipe kendaraan tidak terdata");
                     keluaranBuf.newLine();
                     keluaranBuf.flush();
                   }
                 }
                 else{
                   OutputStream keluaran = koneksi.getOutputStream();
                   BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                
                   keluaranBuf.write("Perintah masih kurang atau berlebih, periksa kembali perintah yang dikirim");
                   keluaranBuf.newLine();
                   keluaranBuf.flush();
                 }
            }
        
            else if(perintah[0].compareTo("DATA")== 0){
               int databecak=0;
               int datamotor=0;
               int datamobil=0;
               
               if (perintah.length==2){
                 if(perintah[1].compareTo("MOBIL")== 0){
                    OutputStream keluaran = koneksi.getOutputStream();
                    BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                    keluaranBuf.write(Integer.toString(Car.size()));
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                    
                   while(datamobil!=Car.size()){
                       OutputStream keluarann = koneksi.getOutputStream();
                       BufferedWriter keluarannBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                       
                       Thread.sleep(10);
                       keluarannBuf.write(Car.get(datamobil).kodeparkir+" "+Car.get(datamobil).plat+" "+Car.get(datamobil).strcalendarin);
                       datamobil++;
                       keluarannBuf.newLine();
                       keluarannBuf.flush();
                   }
                }
                else if(perintah[1].compareTo("MOTOR")== 0){
                    OutputStream keluaran = koneksi.getOutputStream();
                    BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                    keluaranBuf.write(Integer.toString(Motorcycle.size()));
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                    
                   while(datamotor!=Motorcycle.size()){
                       OutputStream keluarann = koneksi.getOutputStream();
                       BufferedWriter keluarannBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                       
                       Thread.sleep(10);
                       keluarannBuf.write(Motorcycle.get(datamotor).kodeparkir+" "+Motorcycle.get(datamotor).plat+" "+Motorcycle.get(datamobil).strcalendarin);
                       datamotor++;
                       keluarannBuf.newLine();
                       keluarannBuf.flush();
                   } 
                 }
                 else if(perintah[1].compareTo("BECAK")== 0){
                    OutputStream keluaran = koneksi.getOutputStream();
                    BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                    keluaranBuf.write(Integer.toString(becak.size()));
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                    
                   while(databecak!=becak.size()){
                       OutputStream keluarann = koneksi.getOutputStream();
                       BufferedWriter keluarannBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                       
                       Thread.sleep(10);
                       keluarannBuf.write(becak.get(databecak).kodeparkir+" "+becak.get(databecak).plat+" "+becak.get(datamobil).strcalendarin);
                       databecak++;
                       keluarannBuf.newLine();
                       keluarannBuf.flush();
                   } 
                 }
               }
            }
            
            else if(perintah[0].compareTo("DATAALL")== 0){
               int databecak=0;
               int datamotor=0;
               int datamobil=0;
               
               if (perintah.length==2){
                 if(perintah[1].compareTo("MOBIL")== 0){
                    OutputStream keluaran = koneksi.getOutputStream();
                    BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                    keluaranBuf.write(Integer.toString(Carall.size()));
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                    
                   while(datamobil!=Carall.size()){
                       OutputStream keluarann = koneksi.getOutputStream();
                       BufferedWriter keluarannBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                       
                       Thread.sleep(10);
                       keluarannBuf.write(Carall.get(datamobil).kodeparkir+" "+Carall.get(datamobil).plat+" "+Carall.get(datamobil).strcalendarin);
                       datamobil++;
                       keluarannBuf.newLine();
                       keluarannBuf.flush();
                   }
                }
                else if(perintah[1].compareTo("MOTOR")== 0){
                    OutputStream keluaran = koneksi.getOutputStream();
                    BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                    keluaranBuf.write(Integer.toString(Motorcycleall.size()));
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                    
                   while(datamotor!=Motorcycleall.size()){
                       OutputStream keluarann = koneksi.getOutputStream();
                       BufferedWriter keluarannBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                       
                       Thread.sleep(10);
                       keluarannBuf.write(Motorcycleall.get(datamotor).kodeparkir+" "+Motorcycleall.get(datamotor).plat+" "+Motorcycleall.get(datamobil).strcalendarin);
                       datamotor++;
                       keluarannBuf.newLine();
                       keluarannBuf.flush();
                   } 
                 }
                 else if(perintah[1].compareTo("BECAK")== 0){
                    OutputStream keluaran = koneksi.getOutputStream();
                    BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                    keluaranBuf.write(Integer.toString(becakall.size()));
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                    
                   while(databecak!=becakall.size()){
                       OutputStream keluarann = koneksi.getOutputStream();
                       BufferedWriter keluarannBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                       
                       Thread.sleep(10);
                       keluarannBuf.write(becakall.get(databecak).kodeparkir+" "+becakall.get(databecak).plat+" "+becakall.get(datamobil).strcalendarin);
                       databecak++;
                       keluarannBuf.newLine();
                       keluarannBuf.flush();
                   } 
                 }
               }
            }
            else if(perintah[0].compareTo("CLOSE")== 0){
               break;
            }
            else{
                //kirim ke client  
                  
                OutputStream keluaran = koneksi.getOutputStream();
                BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                
                keluaranBuf.write("Perintah salah");
                keluaranBuf.newLine();
                keluaranBuf.flush();
            }
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
}

  