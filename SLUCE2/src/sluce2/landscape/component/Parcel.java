package sluce2.landscape.component;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import sluce2.agent.SimpleAgent;
import sluce2.landscape.LandcoverType;


/**
 * A Parcel represents an area of land owned by one agent. (better way to say this?)  
 * <p>
 * The internal representation of a Parcel is a set of Cell objects. 
 * Parcels are created and destroyed in the model on an as-needed basis.
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public class Parcel extends LandUnit
{    
    /**
     * 
     */
    public Parcel()
    {
    	super();
    }
    
    /**
     * Constructs a new Parcel of the given type
     */
    public Parcel(int id, Collection<Cell> cellCollection)
    {
		super(id);
		cellSet = new HashSet<Cell>();
		cellSet.addAll(cellCollection);
    }

    /**
     * @return true if the given object is equal to this Parcel, false otherwise.
     */
    @Override
    public boolean equals(Object that)
    {
    	boolean equal = false;
    	
    	if( (that instanceof Parcel) && ( ((Parcel)that).getId()==this.getId()) )
    	{
    		equal = true;
    	}
    		
    	return equal;
    }
    
    
    
//-------------------------
// Output
    
    /**
     * @return String representation of a Parcel
     */
    @Override
    public String toString()
    {
    	return "Parcel(" + id + ")";
    }
    
    /**
     * Prints the cells contained in the Parcel
     */
    @Override
    public void printDetails()
    {
		System.out.println("Parcel(" + id + "):");
		for(Cell cell : this.cellSet)
		{
		    System.out.println("  " + cell);
		}
    }
           
}