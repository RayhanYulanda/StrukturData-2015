import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Write a description of class Merge here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Merge
{

    /**
     * Constructor for objects of class Merge
     */
    public void merge(String file1,String file2, String file3,String sasaran)throws IOException
    {
        
        
        FileInputStream source1 = null;
        FileInputStream source2 = null;
        FileInputStream source3 = null;
        FileOutputStream copy = null;
        
        try {
         
            source1 = new FileInputStream(file1);
            source2 = new FileInputStream(file2);
            source3 = new FileInputStream(file3);
            copy = new FileOutputStream(sasaran);
           
            int karakter = source1.read();
            // Selama masih ada data yang masih bisa dibaca
            while (karakter != -1) {
                copy.write(karakter);
                karakter = source1.read();
            }
            
            copy = new FileOutputStream(sasaran,true);
            karakter = source2.read();
            while (karakter != -1) {
                copy.write(karakter);
                karakter = source2.read();
            }
            
            copy = new FileOutputStream(sasaran,true);
            karakter = source3.read();
            while (karakter != -1) {
                copy.write(karakter);
                karakter = source3.read();
            }
            
            copy.flush();
        } 
        finally {//udah dijamin akan dijalankan
            // Tutup stream masukan
            if (source1 != null)
                source1.close();
            if (source2 != null)
                source2.close();
            if (source3 != null)
                source3.close();
            if (copy != null)
                copy.close();
            }
    }
    
    public static void main(String[] args) {
        try {
            Merge m = new Merge();
            m.merge("pertama.txt", "kedua.txt","ketiga.txt","hasil.txt");
        }
        catch (IOException kesalahan) {
            System.out.printf("Terjadi kesalahan: %s", kesalahan);
        }
    }
}
