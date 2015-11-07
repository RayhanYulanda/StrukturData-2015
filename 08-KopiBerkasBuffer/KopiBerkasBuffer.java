import java.io.FileInputStream; //untuk membaca berkas
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
/**
 * Write a description of class KopiBerkas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KopiBerkasBuffer
{
    public static void main(String[] args) {
        try {
            KopiBerkasBuffer k = new KopiBerkasBuffer();
            k.kopi("apa.txt","kopi.txt");
        }
        catch (IOException kesalahan) {
            System.out.printf("Terjadi kesalahan: %s", kesalahan);
        }
    }
    
    /**
     * Constructor for objects of class KopiBerkas
     */
    
        public void kopi(String sumber, String sasaran) throws IOException {
            FileInputStream sourcebiasa = null;
            BufferedInputStream source = null;     
            sourcebiasa = new FileInputStream(sumber);
            source = new BufferedInputStream(sourcebiasa);
           
            FileOutputStream copybiasa = null;
            BufferedOutputStream copy = null;
            copybiasa = new FileOutputStream(sasaran);
            copy = new BufferedOutputStream(copybiasa);
        
        try {
            
           
            int karakter = source.read();
            // Selama masih ada data yang masih bisa dibaca
            while (karakter != -1) {
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
