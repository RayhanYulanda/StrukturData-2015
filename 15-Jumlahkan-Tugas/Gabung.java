
/**
 * Write a description of class Gabung here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gabung
{
    private double data [];
    private Kelompok [] kelompok = new Kelompok[5];

    public Gabung(double data[])
    {
        this.data=data;
        
        
        for (int i=0; i<5 ; i++){
         int awal=(i*data.length/5);
         int akhir=awal+((data.length/5)-1);
         kelompok[i]=new Kelompok(awal,akhir,data);
         
        }
        
        
        Thread thread1 = new Thread(kelompok[0]);
        Thread thread2 = new Thread(kelompok[1]);
        Thread thread3 = new Thread(kelompok[2]);
        Thread thread4 = new Thread(kelompok[3]);
        Thread thread5 = new Thread(kelompok[4]);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

    public double hasil()
    {
        try { 
            Thread.sleep(1000);
        }
        catch(InterruptedException e) { 
        }
        double hasil=0;
        for(int i=0;i<5;i++){
          hasil=hasil+kelompok[i].hasil();
        }
        return hasil;
    }
}
