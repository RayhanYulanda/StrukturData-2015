/**
 * Kelas menu utama
 * 
 * @author Rayhan Yulanda
 * @version 15 Desember 2015
 */
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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
public class GUIclient
{
    JFrame f = new JFrame("Hermes Mall Parking");
    String ip;
    
    JButton exit=new JButton("Keluar");
    JButton in=new JButton("Masuk");
    JButton close=new JButton("Close");
    ImageIcon bg = new ImageIcon(getClass().getResource("image/bg2.jpg"));
    JLabel gambarBackground = new JLabel(bg);
    
    JRadioButton rmobil = new JRadioButton();
    JRadioButton rmotor= new JRadioButton();
    JRadioButton rbecak= new JRadioButton();
    ButtonGroup tipekendaraan = new ButtonGroup();
    
    JTextField tplat=new JTextField(25);
    
    Socket socket;
    String ketikanSatuBaris;
    /** Konstruktor tanpa argumen */
    public GUIclient() throws UnknownHostException, IOException {
      //this.ip=ip;
      try{
      socket = new Socket("localhost", 33333);      
           
      makeFrame();                // Tampilkan frame utama
      komponenVisual();                  // Perbarui semua visual               
      aksireaksi();
      
      while(true){
        if (socket == null){
          socket.close();
          break;
        }
      }
     }
     catch(IOException salah) {
          JOptionPane.showMessageDialog(null,salah,"Terjadi kesalahan",JOptionPane.INFORMATION_MESSAGE);
     }
     
    }
   
    
    /** Mengatur dan menampilkan frame utama */
    public void makeFrame() {                                               
       f.setIconImage(new ImageIcon("image/icon.png").getImage());          // Tambahkan ikon
       f.setSize(500,374);     // Atur dimensi frame
       f.setLocationRelativeTo(null);                                        // Tengahkan posisi frame
       f.setResizable(false);                                                 // Dapat diperbesar/tidak
       f.setVisible(true);                                                   // Ditampilkan/tidak
    }
    
     public void komponenVisual()
    {
       JPanel p = (JPanel)f.getContentPane();//jpanel kayak kanvas
       p.setLayout(null); 

       p.add(tplat);
         tplat.setBounds(170,165,200,20);
         
       p.add(rmobil);
         rmobil.setBounds(165,87,20,20);
         tipekendaraan.add(rmobil);
       p.add(rmotor);
         rmotor.setBounds(380,87,20,20);
         tipekendaraan.add(rmotor);
       p.add(rbecak);
         rbecak.setBounds(275,87,20,20);
         tipekendaraan.add(rbecak);
       
        //button play exit close
       p.add(in);
         in.setBackground(Color.green);
         in.setBounds(30,250,150,50);
         in.setCursor(new Cursor(Cursor.HAND_CURSOR));
       p.add(exit);
         exit.setBackground(Color.red);
         exit.setBounds(320,250,150,50);
         exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
       p.add(close);
         close.setBackground(Color.red);
         close.setBounds(220,310,80,30);
         //background
       p.add(gambarBackground);
         gambarBackground.setBounds(0,0,500,374);
    }
    
     public void aksireaksi(){
      in.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
          try{
            Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
            BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
            ketikanSatuBaris = "masuk "+tipe()+" "+gantiunder(tplat.getText());
            keluaranBuff.write(ketikanSatuBaris);
            keluaranBuff.write("\n");
            keluaranBuff.flush();
            
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
            else{
              JOptionPane.showMessageDialog(null,baris,"Pesan Server",JOptionPane.INFORMATION_MESSAGE);
            }
            
            tplat.setText("");
          }
          catch(IOException salah) {
              JOptionPane.showMessageDialog(null,salah,"Terjadi kesalahan",JOptionPane.INFORMATION_MESSAGE);
          }
         }
      });
      
       exit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
              Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
              BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
              ketikanSatuBaris = "keluar "+tipe()+" "+gantiunder(tplat.getText());
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
                  JOptionPane.showMessageDialog(null,baris,"Pesan Server",JOptionPane.INFORMATION_MESSAGE);
                  tplat.setText("");
              }
            }
            catch(IOException salah) {
              JOptionPane.showMessageDialog(null,salah,"Terjadi kesalahan",JOptionPane.INFORMATION_MESSAGE);
            }
       }
          
      });
      
      close.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
         try{
          Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
          BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
          keluaranBuff.write("close");
          keluaranBuff.write("\n");
          keluaranBuff.flush();
          System.exit(0);
         }
         catch(IOException salah) {
            JOptionPane.showMessageDialog(null,salah,"Terjadi kesalahan",JOptionPane.INFORMATION_MESSAGE);
          }
          
       } 
      });
   }
   
   //ganti ke
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
   
      public String gantiunder(String gantii){
        int index=0;
        String gantifix="";
        if(gantii==null){
         return gantifix;
        }
        
        while(index<gantii.length()){
            if(gantii.charAt(index)==' '){
                gantifix=gantifix+'_';
            }
            else{
                gantifix=gantifix+gantii.charAt(index);
            }
            index++;
        }
        return gantifix;
   }
   
   public String tipe(){
       if(rmotor.isSelected()==true){
         return "motor";
       }
       else if(rmobil.isSelected()==true){
         return "mobil";
       }
       else if(rbecak.isSelected()==true){
         return "becak";
       }
       else
        return "salah";
   }
}
