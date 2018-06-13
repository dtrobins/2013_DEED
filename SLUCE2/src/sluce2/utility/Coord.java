package sluce2.utility;


/**
 * A simple coordinate class that holds an x and y coordinate.
 * 
 * @author Meghan Hutchins
 * @date 11/5/2010
 */
public class Coord
{
    /** x-coordinate **/
    private int x; 
    /** y-coordinate **/
    private int y;
    
    /**
     * Default coordinate is (0,0)
     */
    public Coord()
    {
	this.x = 0;
	this.y = 0;
    }
    
    /**
     * Constructs a coordinate from the given x and y locations
     */
    public Coord(int xLoc, int yLoc)
    {
	this.x = xLoc;
	this.y = yLoc;
    }

    /**
     * 
     * @param that
     * @return true if this Coord is equal to that Coord.
     */
    @Override
    public boolean equals(Object that)
    {
    	boolean isEqual = false;
    	if(that instanceof Coord)
    	{
    		isEqual = ((Coord) that).getX()==this.getX() && ((Coord) that).getY()==this.getY();
    	}
    	
    	return isEqual;
    }
    
    /**
     * 
     */
    public String toString()
    {
	return("(" + this.x + "," + this.y + ")");
    }
    
//-------------------
// Accessors       
    
    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
       
}
