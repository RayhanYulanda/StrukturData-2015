import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Write a description of class CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher
{
    private int shift;
    public CaesarCipher(int shift){
          this.shift=shift;  
    }

    public void enkripsi(String sumber, String sasaran)throws IOException
    {
        FileInputStream source = null;
        FileOutputStream copy = null;
        
        
        try {
         
            source = new FileInputStream(sumber);
            copy = new FileOutputStream(sasaran);
           
            int karakter = source.read();
            // Selama masih ada data yang masih bisa dibaca
            while (karakter != -1) {
                karakter=karakter+shift;
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
    
    public void dekripsi(String sumber, String sasaran)throws IOException
    {
       FileInputStream source = null;
       FileOutputStream copy = null;
        
        
        try {
         
            source = new FileInputStream(sumber);
            copy = new FileOutputStream(sasaran);
           
            int karakter = source.read();
            // Selama masih ada data yang masih bisa dibaca
            while (karakter != -1) {
                karakter=karakter-shift;
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
    
    public static void main(String[] args) {
        try {
            CaesarCipher k = new CaesarCipher(3);
            k.dekripsi("apa.txt","dekripsi.txt");
            k.enkripsi("apa.txt","inkripsi.txt");
        }
        catch (IOException kesalahan) {
            System.out.printf("Terjadi kesalahan: %s", kesalahan);
        }
    }
}
