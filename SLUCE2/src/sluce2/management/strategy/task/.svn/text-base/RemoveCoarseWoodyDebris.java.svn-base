package sluce2.management.strategy.task;

import sluce2.ModelContext;
import sluce2.ModelParam;
import sluce2.ecosystem.EcosystemContext;
import sluce2.ecosystem.biomebgc.proxy.RestartFileProxy;
import sluce2.ecosystem.biomebgc.proxy.RestartType;
import sluce2.landscape.LandcoverType;
import sluce2.landscape.LandscapeContext;
import sluce2.landscape.LandscapeLayerType;
import sluce2.landscape.component.Cell;
import sluce2.management.ManagementContext;

public enum RemoveCoarseWoodyDebris implements Task
{
	NONE(ModelContext.getParameterDouble(ModelParam.prctCoarseWoodyDebrisRemovedNone)),
	HIGH(ModelContext.getParameterDouble(ModelParam.prctCoarseWoodyDebrisRemovedHigh)),
	MEDIUM(ModelContext.getParameterDouble(ModelParam.prctCoarseWoodyDebrisRemovedMedium)),
	LOW(ModelContext.getParameterDouble(ModelParam.prctCoarseWoodyDebrisRemovedLow));
	
	/** Percent removed. **/
	private Double prctRemoved;
	/** The landcover type this task will be performed on. **/
	private LandcoverType landcover;
	
	/**
	 * 
	 */
	private RemoveCoarseWoodyDebris(Double prctRemoved)
	{
		this.prctRemoved = prctRemoved;
		this.landcover = null;
	}
	
	/**
	 * @param the LandcoverType this Task will act on
	 * @return this Task
	 */
	public RemoveCoarseWoodyDebris on(LandcoverType landcover)
	{
		this.landcover = landcover;
		return this;
	}
	
	/**
	 * Removes coarse woody debris from the given parcel<p>
	 * @param ParcelID to remove litter from.
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
				RestartFileProxy restartProxy = EcosystemContext.getInstance().getBgcFileManager().getRestartInputFileProxy(landUnitID, landcover);
				
				// Determine percent to be left on the landscape
				double prctLeftOnLandcape = (1d-prctRemoved);
				
				// Update RestartFileProxy
				restartProxy.put( RestartType.cwdc,Double.toString((Double.valueOf(restartProxy.get(RestartType.cwdc))*prctLeftOnLandcape)) );
				restartProxy.put( RestartType.cwdn, Double.toString((Double.valueOf(restartProxy.get(RestartType.cwdn))*prctLeftOnLandcape)) );
				
				// Write the RestartFileProxy for this LandUnit-LandcoverType
				EcosystemContext.getInstance().getBgcFileManager().writeRestartInputProxy(restartProxy, landUnitID, landcover);
			}
		}
	}

//--------------------
// Accessors
	
	public Double getPrctRemoved()
	{
		return prctRemoved;
	}

	public LandcoverType getLandcover()
	{
		return landcover;
	}
	
}
