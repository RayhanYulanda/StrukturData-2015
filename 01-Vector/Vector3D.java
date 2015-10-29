
/**
 * Write a description of class vector3d here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vector3D
{
    // instance variables - replace the example below with your own
    private double x;
    private double y;
    private double z;

    /**
     * Constructor for objects of class vector3d
     */
    public Vector3D(double x, double y, double z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public double getZ(){
        return z;
    }
    
}
