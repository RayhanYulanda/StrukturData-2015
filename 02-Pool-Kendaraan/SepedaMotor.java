
/**
 * Write a description of class SepedaMotor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SepedaMotor implements Kendaraan
{
    // instance variables - replace the example below with your own
    private String plat;
   // Kendaraan sepedamotor = new Kendaraan();

    public SepedaMotor(String plat)
    {
        this.plat=plat;
    //  sepedamotor.plat=plat();
     // sepedamotor.tipe=tipe();
    }

    public String plat()
    {
      return plat;
    }
    
    public String tipe(){
     return "SepedaMotor";
    }
    
}
