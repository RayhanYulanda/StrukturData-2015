import java.io.FileInputStream; //untuk membaca berkas
import java.io.IOException;
import java.io.FileOutputStream;
/**
 * Write a description of class KopiBerkas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KopiBerkas
{
    public static void main(String[] args) {
        try {
            KopiBerkas k = new KopiBerkas();
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
        // baca berkas 
            FileInputStream source = null;
            FileOutputStream copy = null;
        
        
        try {
         
            source = new FileInputStream(sumber);
            copy = new FileOutputStream(sasaran);
           
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
