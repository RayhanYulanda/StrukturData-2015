
/**
 * Write a description of class Profil here.
 * 
 * @author Rayhan yulanda 
 * @version 06-11-2015
 */
public class Profil
{ 
    private String nama="Rayhan Yulanda";
    private String  nim ="1408107010076";

    public Profil()
    {
        
    }

    public Profil(String nama, String nim)
    {
        this.nama = nama;
        this.nim = nim;
    }
    
    public String getNama()
    {
      return nama;
    }
    
    public String getNim()
    {
      return nim;
    }
}
