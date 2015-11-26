
/**
 * Write a description of class prime here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class prime
{   
   //  int prima [] = new int[1000000];

    /**
     * Constructor for objects of class prime
     */
   public prime()
   {   int angka = 50;
       
       for(int j=1;j<angka;j++){
       int periksa=0;
       for(int i=2;i<j;i++){
         if((j%i)==0){
           periksa=1;
           break;
         }
       }
       
       if (periksa==0){
           System.out.println(j);
       }
      }
   }
}
