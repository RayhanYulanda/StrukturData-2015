
/**
 * Write a description of class Pool here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pool
{
    private Kendaraan kendaraan[];
    
    public Pool(Kendaraan [] kendaraan)
    {
       if (kendaraan==null)
         kendaraan=new Kendaraan[0];
       this.kendaraan=kendaraan;
    }

    public int jumlah()
    {
      return kendaraan.length;
    }
    
    public Kendaraan[] daftarKendaraan(){
      return kendaraan;
    }
}