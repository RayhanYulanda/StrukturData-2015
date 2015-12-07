import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class Chat
{
    private Socket koneksi = null;
    String jawaban = "";
    public void send(String pesan) 
                throws UnknownHostException, IOException {
        // 0. Buka socket
        koneksi = new Socket("192.168..193", 33333);//43 protokol whois

        kirimPerintah(pesan);
        
        terimapesan();
        
        kirimPerintah(jawaban);
        
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
        keluaranWriter.write("\r\n"); 
        keluaranWriter.flush(); // Paksa kirim data yang belum terkirim
    }
    
    public void terimapesan() throws IOException {
        // 1 & 2. Minta socket untuk baca -> Langsung dibuka
        
        InputStream masukan = koneksi.getInputStream();
        // Karena keluarannya panjang sehingga harus dibuffer
        BufferedInputStream masukanBuffer = new BufferedInputStream(masukan);
        // Selagi masih ada data baca
        System.out.println("Balasan :");
        int data = masukanBuffer.read();
        while (data != -1) {
            jawaban=jawaban + Character.toUpperCase(data);
            System.out.write((char) data);
            data = masukanBuffer.read();
        }
    }
    
}
