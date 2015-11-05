
/**
 * Write a description of class FiniteElement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FiniteElement
{
    private Gaya gaya[]=new Gaya[5];

    /**
     * Constructor for objects of class FiniteElement
     */
    public FiniteElement()
    {

    }

    public FiniteElement(Gaya[] gaya)
    {
        
    }
    
    public Gaya[] daftarGaya(){
     //kenapa harus tipe data array?
     return gaya;
    }
}
