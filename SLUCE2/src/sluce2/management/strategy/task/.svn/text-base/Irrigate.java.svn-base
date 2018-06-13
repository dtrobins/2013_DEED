package sluce2.management.strategy.task;

import sluce2.GlobalConstants;
import sluce2.ecosystem.EcosystemContext;
import sluce2.ecosystem.biomebgc.BiomeBGCProperties;
import sluce2.ecosystem.biomebgc.proxy.InitFileProxy;
import sluce2.ecosystem.biomebgc.proxy.InitType;
import sluce2.landscape.LandcoverType;
import sluce2.landscape.LandscapeContext;
import sluce2.landscape.LandscapeLayerType;
import sluce2.landscape.component.Cell;
import sluce2.management.ManagementContext;

/**
 * 
 * @author Meghan Hutchins
 * @since 2011.03.07
 */
public enum Irrigate implements Task
{
	NONE(GlobalConstants.BGC_IRRIGATION_NONE), 
	SCHEDULED(GlobalConstants.BGC_IRRIGATION_SCHEDULED),
	DRY(GlobalConstants.BGC_IRRIGATION_DRY);
	
	/** BiomeBGC Meteorology File associated with each type of Irrigation. **/
	private String meteorologyFile;
	/** The landcover type this task will be performed on. **/
	private LandcoverType landcover;
	
	/**
	 * 
	 */
	private Irrigate(String meteorologyFile)
	{
		this.meteorologyFile = meteorologyFile;
		this.landcover = null;
	}
	
	/**
	 * @param the LandcoverType this Task will act on
	 * @return this Task
	 */
	public Irrigate on(LandcoverType landcover)
	{
		this.landcover = landcover;
		return this;
	}

	/**
	 * Sets the irrigation type for the given parcel and landcover
	 * @param ParcelID
	 */
	@Override
	public void doit(Integer parcelID)
	{
		ManagementContext.logger.debug(this.getClass().getSimpleName() + " of type: " + this + " on parcel-" + parcelID + "|" + this.landcover); 
		
		/** TODO: Break up depending on modeling unit scale. For right now, assume cell scale. **/
		for(Cell cell : LandscapeContext.getInstance().getParcel(parcelID).getCellSet())
		{
			// If the landunit contains the given landcover...
			if(cell.getValue(LandscapeLayerType.LANDCOVER).equals(landcover.getCode()))
			{
				Integer landUnitID = cell.getId();
				
				// Get the RestartFileProxy for this LandUnit-LandcoverType
				InitFileProxy initFileProxy = EcosystemContext.getInstance().getBgcFileManager().getInitFileProxy(landUnitID, landcover);
				
				// Get the appropriate Meteorology file for this Irrigation type
				BiomeBGCProperties bgcProperties = (BiomeBGCProperties)EcosystemContext.getInstance().getBgcProperties();
				String metFile = (bgcProperties.getBgcSharedFileLocation()+GlobalConstants.FILE_SEPARATOR+bgcProperties.getProperty(meteorologyFile));
		
				// Update RestartFileProxy
				initFileProxy.put(InitType.meteorologyInputFileLoc, metFile);
				
				// Write the RestartFileProxy for this LandUnit-LandcoverType
				EcosystemContext.getInstance().getBgcFileManager().writeInitFileProxy(initFileProxy, landUnitID, landcover);
			}
		}
	}

//--------------------
// Accessors
	
	public String getMeteorologyFile()
	{
		return meteorologyFile;
	}

	public LandcoverType getLandcover()
	{
		return landcover;
	}

}
