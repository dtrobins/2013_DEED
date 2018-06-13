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

public enum RemoveLitter implements Task
{
	NONE(ModelContext.getParameterDouble(ModelParam.prctLitterRemovedNone)),
	HIGH(ModelContext.getParameterDouble(ModelParam.prctLitterRemovedHigh)),
	MEDIUM(ModelContext.getParameterDouble(ModelParam.prctLitterRemovedMedium)),
	LOW(ModelContext.getParameterDouble(ModelParam.prctLitterRemovedLow));
	
	/** Percent removed. **/
	private Double prctRemoved;
	/** The landcover type this task will be performed on. **/
	private LandcoverType landcover;
	
	/**
	 * 
	 */
	private RemoveLitter(Double prctRemoved)
	{
		this.prctRemoved = prctRemoved;
		this.landcover = null;
	}
	
	/**
	 * @param the LandcoverType this Task will act on
	 * @return this Task
	 */
	public RemoveLitter on(LandcoverType landcover)
	{
		this.landcover = landcover;
		return this;
	}
	
	/**
	 * Removes litter from the given landUnit.<p>
	 * Litter is removed by applying a the user-defined parameter to the current litter value.
	 * @param LandUnit to remove litter from.
	 */
	@Override
	public void doit(Integer parcelID)
	{
		ManagementContext.logger.debug(this.getClass().getSimpleName() + " of type: " + this + " on parcel-" + parcelID + "|" + this.landcover); 
		
		/** TODO: Break up depending on modeling unit scale. For right now, assume cell scale. **/
		for(Cell cell : LandscapeContext.getInstance().getParcel(parcelID).getCellSet())
		{
			if(cell.getValue(LandscapeLayerType.LANDCOVER).equals(landcover.getCode()))
			{
				Integer landUnitID = cell.getId();
				
				// Get the RestartFileProxy for this LandUnit-LandcoverType
				RestartFileProxy restartProxy = EcosystemContext.getInstance().getBgcFileManager().getRestartInputFileProxy(landUnitID, landcover);
				
				// Determine percent to be left on the landscape
				double prctLeftOnLandcape = (1d-prctRemoved);
				
				// Update RestartFileProxy
				restartProxy.put( RestartType.litr1c, Double.toString((Double.valueOf(restartProxy.get(RestartType.litr1c))*prctLeftOnLandcape)) );
				restartProxy.put( RestartType.litr2c, Double.toString((Double.valueOf(restartProxy.get(RestartType.litr2c))*prctLeftOnLandcape)) );
				restartProxy.put( RestartType.litr3c, Double.toString((Double.valueOf(restartProxy.get(RestartType.litr3c))*prctLeftOnLandcape)) );
				restartProxy.put( RestartType.litr4c, Double.toString((Double.valueOf(restartProxy.get(RestartType.litr4c))*prctLeftOnLandcape)) );
				restartProxy.put( RestartType.litr1n, Double.toString((Double.valueOf(restartProxy.get(RestartType.litr1n))*prctLeftOnLandcape)) );
				restartProxy.put( RestartType.litr2n, Double.toString((Double.valueOf(restartProxy.get(RestartType.litr2n))*prctLeftOnLandcape)) );
				restartProxy.put( RestartType.litr3n, Double.toString((Double.valueOf(restartProxy.get(RestartType.litr3n))*prctLeftOnLandcape)) );
				restartProxy.put( RestartType.litr4n, Double.toString((Double.valueOf(restartProxy.get(RestartType.litr4n))*prctLeftOnLandcape)) );
				
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
