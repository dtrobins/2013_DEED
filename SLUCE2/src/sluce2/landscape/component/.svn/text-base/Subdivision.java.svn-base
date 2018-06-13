package sluce2.landscape.component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * A Subdivision represents a residential subdivision: an area of land owned 
 * by many ResidentHousehold agents and containing many Parcels. (better way to say this?)  
 * <p>
 * The internal representation of a Subdivision is a set of Parcel objects. 
 * Subdivisions are created and destroyed in the model on an as-needed basis.
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public class Subdivision extends LandUnit
{

    private Set<Number> parcelIdSet; 
    
    /**
     * 
     */
    public Subdivision()
    {
	parcelIdSet = new HashSet<Number>();
    }
    
    /**
     * Constructs a new Subdivision of the given type
     */
    public Subdivision(int id, Collection<Number> parcelIdCollection)
    {
	super(id);
	parcelIdSet = new HashSet<Number>();
	parcelIdSet.addAll(parcelIdCollection);
    }

    
    
//-------------------------
// Output
     
     /**
      * @return String representation of a Subdivision
      */
     @Override
     public String toString()
     {
 	return "Subdivision(" + id + ")";
     }
     
     /**
      * Prints the Parcels contained in the Subdivision
      */
     @Override
     public void printDetails()
     {
	System.out.println("Subdivision(" + id + "):");
 	for(Number id : this.parcelIdSet)
 	{
 	    System.out.println("  Parcel: " + id);
 	}
     }
    
//---------------------------
// Accessors 
    
     public Set<Number> getParcelIdSet()
     {
         return parcelIdSet;
     }

     public void setParcelIdSet(Set<Number> parcelIdSet)
     {
         this.parcelIdSet = parcelIdSet;
     }    
     
}