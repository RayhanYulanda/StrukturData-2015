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
public class Chat
{
    private Socket koneksi = null;
    public void send(String pesan) 
                throws UnknownHostException, IOException {
        // 0. Buka socket
        koneksi = new Socket("unsyiah.ac.id",80);//43 protokol whois

        kirimPerintah(pesan);
        
        terimapesan();
        // Tutup socket-nya => dengan sendirinya menutup semua stream
        koneksi.close();
    }
    
    public void kirimPerintah(String pesan) throws IOException {
        // 1 & 2. Minta socket untuk ditulis dan Langsung dibuka
        OutputStream keluaran = koneksi.getOutputStream();
        // Note: Karena protokol-nya berbasis teks pakai writer aja.
        Writer keluaranWriter = new OutputStreamWriter(keluaran); 
        // 3. Selagi ada data kirim
        keluaranWriter.write(pesan);
        // Syarat protokol-nya, semua perintah diakhiri dengan: CR & LF
        keluaranWriter.write("\n"); 
        keluaranWriter.flush(); // Paksa kirim data yang belum terkirim
    }
    
    public void terimapesan() throws IOException {
        System.out.print("Server : ");
        Reader masukan = new InputStreamReader(koneksi.getInputStream()); 
        BufferedReader masukanBuff = new BufferedReader(masukan);
        String baris = masukanBuff.readLine();
        System.out.println(baris);
    }
}
