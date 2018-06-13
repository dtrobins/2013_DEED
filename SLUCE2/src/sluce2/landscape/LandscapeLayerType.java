package sluce2.landscape;

/**
 * mdh: 
 * TODO: Not sure about having this as an enum. 
 * Might want to read in xml and save values in some sort of a dictionary dynamically
 * so that if we add or remove landscape layer names, we don't have to change any code. 
 */
public enum LandscapeLayerType
{
	// From ABM
    LANDUSE("Landuse"), 
    LANDCOVER("Landcover"), 
    SUBDIVISION("Subdivision"),
    PARCEL("Parcel"),
    COUNTYROAD("CountyRoad"),
    SECONDARYROAD("SecondaryRoad"),
    
    // From Annual Output File
    LITRC("litrc"),
    NEE("nee"),
    NEP("nep"),
    NPP("npp"),
    SOILC("soilc"),
    TOTALC("totalc"),
    VEGC("vegc"),
    CWDC("cwdc");
    
    // From Restart File
    
    // OLD
    //PRCNTSAND("PercentSand"), 
    //PRCNTSILT("PercentSilt"),
    //PRCNTCLAY("PercentClay"),
    //SOILDEPTH("SoilDepth"),
    //LITTER("Litter"),
    //COARSE_WOODY_LITTER("CoarseWoodyLitter");
    
    private String layerName;
   
    /*
     * Private constructor builds Enums with appropriate layer names.
     */
    private LandscapeLayerType(String layerName)
    {
    	this.layerName = layerName;
    }
        
    /**
     * 
     * @return code associated with this LanduseType
     */
    public String getName()
    {
	return layerName;
    }
      
}