
/**
 * Write a description of class Alien here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alien extends Komodo implements Tumbuhan
{
    
    public boolean berbuah()
    {
        return true;
    }
    
    public String suara(){
        return "Bip";
    }
    
    public boolean merayap(){
        return false;
    }

}
