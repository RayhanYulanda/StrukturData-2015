import java.util.Calendar;
import java.lang.*;
/**
 * Write a description of class Mobil here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sepeda
{
    Calendar kalenderin = Calendar.getInstance();
    Calendar kalenderout;
    String pintumasuk;//cetak
    String kodeparkir;//cetak
    String strcalendarin;//cetak
    String strcalendarout="";//cetak
    int timein;
    int timeout;

    /**
     * Constructor for objects of class Mobil
     */
    public Sepeda(String pintumasuk ,int nomorparkiran)
    {
        this.pintumasuk=pintumasuk;
        this.strcalendarin=kalenderin.getTime().toString();
        this.strcalendarin=gantispasi(this.strcalendarin);
        this.timein=kalenderin.get(Calendar.HOUR_OF_DAY);
        kodeparkir="S"+nomorparkiran;
    }
    
    public int hitungbayar(){
      kalenderout = Calendar.getInstance();
      this.timeout=kalenderout.get(Calendar.HOUR_OF_DAY);
      this.strcalendarout=kalenderout.getTime().toString();
      this.strcalendarout=gantispasi(this.strcalendarout);
      int bayar=(timeout-timein)+1;
      if(bayar<0){
          bayar = bayar + 24;
      }
      bayar = bayar*500;
      return bayar;
    }
    
    public String gantispasi(String gantii){
        int index=0;
        String gantifix="";
        while(index<gantii.length()){
            if(gantii.charAt(index)==' '){
                gantifix=gantifix+'_';
            }
            else{
                gantifix=gantifix+gantii.charAt(index);
            }
            index++;
        }
        return gantifix;
    }
}
