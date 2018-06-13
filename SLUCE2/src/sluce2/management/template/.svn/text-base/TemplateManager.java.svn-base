package sluce2.management.template;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import repast.simphony.util.collections.Pair;
import sluce2.landscape.LandcoverType;
import sluce2.landscape.LandscapeContext;
import sluce2.landscape.LandscapeLayerType;
import sluce2.landscape.RasterRepository;
import sluce2.landscape.component.Cell;
import sluce2.management.ManagementContext;
import sluce2.utility.Coord;

/**
 * The TemplateManager keeps a collection of Templates used for land development.
 * <p>
 * The Template Manager also contains functionality for applying a particular Template 
 * to a given landscape (i.e. RasterRepository). Application of a Template permanently 
 * changes the data in the landscape to match those in the template, for all raster layers. 
 * <p>
 * There is only one instance of the TemplateManager at all times. 
 * 
 * @author Meghan Hutchins
 * @date 10/16/2010
 */
public class TemplateManager
{
    /** The one instance of TemplateManager for the model **/
    private static TemplateManager instance;
    
    /** A map to hold all template information **/
    private Map<String, Template> templateMap;
 
    /**
     * 
     */
    private TemplateManager()
    {
    	templateMap = new HashMap<String, Template>();
    }
   
   /**
    * 
    * @return the only instance of TemplateManager in the model.
    */
   public static TemplateManager instance()
   {
		if(instance == null)
		{
		    instance = new TemplateManager();
		}
		return instance;
   }
   
   /**
    * 
    */
   public static void destroy()
   {
	   instance = null;
   }
   
   /**
    * @param pathToTemplateDescfile
    * @return true if the templates in the descriptor file were loaded, false otherwise
    */
   public void loadTemplates(String pathToTemplateFile, String... templateNames)
   {   
       // Make sure we have an instance before we load templates
       if(instance == null)
       {
    	   instance = new TemplateManager();
       }
       
       for(String name : templateNames)
       {
    	   Template template = new Template(pathToTemplateFile, name);
    	   instance.templateMap.put(name, template);
       }
   }
   
   /**
    * 
    */
   public void applyTemplate(String templateName, RasterRepository landscape, Coord nwCornerLoc)
   {  
       if(this.containsTemplate(templateName))
       {
    	   applyTemplate(this.getTemplate(templateName), landscape, nwCornerLoc);
       }
   }
   
   /**
    * Applies the given development Template to the given landscape, 
    * using the Northwest Coordinate as a basis for Template location. 
    */
   public Map<Integer, Pair<LandcoverType, LandcoverType>> applyTemplate(Template template, RasterRepository landscape, Coord nwCornerLoc)
   {
	   Map<Integer, Pair<LandcoverType, LandcoverType>> changeMap = new HashMap<Integer, Pair<LandcoverType, LandcoverType>>(); // used for EcosystemComponent notification
	   
       boolean successPut = false;
       boolean successSave = false;
       
       // For each layer in the template..
       for(String layerName : template.getUniqueLayerNames())
       {
		   // Go through each (x,y) value...
		   for(int tempY=0; tempY<template.getHeight(); tempY++)
		   {
		       for(int tempX=0; tempX<template.getWidth(); tempX++)
		       {
		    	   Coord templateLocation = new Coord(tempX, tempY);
				   Number templateValue = template.getValue((String)layerName, templateLocation);  
				   
				   //... and change the value of the corresponding landscape location.
				   Coord landscapeLocation = new Coord(nwCornerLoc.getX()+tempX, nwCornerLoc.getY()+tempY);				   
				   Number oldLandcoverCode = LandscapeContext.getInstance().getValue(LandscapeLayerType.LANDCOVER, landscapeLocation);

				   successPut = landscape.putValue((String)layerName, landscapeLocation, templateValue);
				   successSave = landscape.saveAllLayers();
				   
				   assert(successPut==true && successSave==true) : "Unable to apply development template: " + template.getLayerGroupName();
				   
				   // Only saving changes for landcover type
				   if(layerName.equalsIgnoreCase(LandscapeLayerType.LANDCOVER.toString()))
				   {
					   changeMap.put(Cell.getId(landscapeLocation), new Pair<LandcoverType, LandcoverType>(LandcoverType.getLandcoverType(oldLandcoverCode.intValue()), LandcoverType.getLandcoverType(templateValue.intValue())));
				   }
				   
				   /** TODO: Subdivision ID **/
				   //System.out.println(template.getLayer((String)layerName).getLayerName() + " : " + tempX + "," + tempY + " = " + templateValue); 
		       }
		   }
       }
       
       return changeMap;
   }
   
   /**
    * 
    * @return A set containing the names of Templates contained in this TemplateManager
    */
   public Set<String> getTemplateNames()
   {
       return templateMap.keySet();
   }

   /**
    * 
    * @return A set containing the names of Templates contained in this TemplateManager
    */
   public Template getTemplate(String name)
   {
       Template template = null; 
       
       if(this.containsTemplate(name))
       {
	   template = templateMap.get(name);
       }

       return template;
   }
   
   /**
    * 
    * @return the number of templates contained in this TemplateManager
    */
   public int getNbrOfTemplates()
   {
       return templateMap.size();
   }
   
   /**
    * 
    * @return true if the TemplateManager has no Templates, false otherwise
    */
   public boolean isEmpty()
   {
       return templateMap.isEmpty();
   }
   
   /**
    * 
    * @return Return true if this TemplateManager contains the given template name, false otherwise
    */
   public boolean containsTemplate(String name)
   {
       return templateMap.containsKey(name);
   }
   
   /**
    * 
    */
   public String toString()
   {
       StringBuffer buffer = new StringBuffer();
       
       for(Entry<String, Template> entry : templateMap.entrySet())
       {
	   buffer.append(entry.getKey() + ":\n");
	   buffer.append(entry.getValue().toString() + "\n");
       }
       
       return buffer.toString();
   }
}