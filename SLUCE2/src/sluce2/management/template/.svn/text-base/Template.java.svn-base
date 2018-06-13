package sluce2.management.template;

import sluce2.landscape.RasterRepository;

/**
 * Templates are used to easily develop subdivisions and parcels within a landscape.
 * <p>
 * A Template refers to a development template that a Developer agent may use to create 
 * Subdivisions containing Parcels, Land-cover types and neighborhood roads. 
 * <p>
 * 
 * TODO: Add other associated information. 
 * Like do we want to easily keep track of how many of a particular template was applied?
 * 
 * @author Meghan Hutchins
 * @date 10/16/2010
 */
public class Template extends RasterRepository
{
    /** Cost associated applying this template to the landscape **/
    private double cost;
  
    
    /**
     * 
     */
    Template()
    {
	this.cost = 0; 
    }
    
    /**
     * 
     */
    public Template(String pathToTemplateFile)
    {
	super(pathToTemplateFile);
	this.cost = 0;
    }
    
    /**
     * 
     */
    public Template(String pathToTemplateFile, String templateName)
    {
	super(pathToTemplateFile, templateName);
	this.cost = 0;
    }
    
    public String toString()
    {
	StringBuffer buffer = new StringBuffer(super.toString());
	buffer.append("Cost: " + cost);
	
	return buffer.toString();
    }

//---------------------------
// Accessors    
    
    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }
         
}