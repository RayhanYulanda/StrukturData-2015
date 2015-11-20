import java.io.FileInputStream; //untuk membaca berkas
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.*;
/**
 * Write a description of class aKeAt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AKeAt
{
    
    /**
     * Constructor for objects of class upper
     */
    
        public void aKeAt(String sumber, String sasaran) throws IOException {
        // baca berkas 
            FileInputStream source = null;
            FileOutputStream copy = null;
        
        
        try {
            source = new FileInputStream(sumber);
            copy = new FileOutputStream(sasaran);
           
            int karakter = source.read();
            // Selama masih ada data yang masih bisa dibaca
            while (karakter != -1) {
               if( karakter=='a'|| karakter =='A'){
                 karakter='@';
               }
                copy.write(karakter);
                karakter = source.read();
            }
            
            copy.flush();
        } 
        finally {//udah dijamin akan dijalankan
            // Tutup stream masukan
            if (source != null)
                source.close();
            if (copy != null)
                copy.close();
            }
        }
}
