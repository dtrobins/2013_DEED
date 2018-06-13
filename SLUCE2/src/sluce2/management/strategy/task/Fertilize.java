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

public enum Fertilize implements Task
{
	NONE(ModelContext.getParameterDouble(ModelParam.fertilizerAdditionNone)),
	HIGH(ModelContext.getParameterDouble(ModelParam.fertilizerAdditionHigh)),
	MEDIUM(ModelContext.getParameterDouble(ModelParam.fertilizerAdditionMedium)),
	LOW(ModelContext.getParameterDouble(ModelParam.fertilizerAdditionLow));
	
	/** Amount of fertilizer added. **/
	private Double fertilizerAmount;
	/** The landcover type this task will be performed on. **/
	private LandcoverType landcover;
	
	/**
	 * 
	 */
	private Fertilize(Double fertilizerAmount)
	{
		this.fertilizerAmount = fertilizerAmount;
		this.landcover = null;
	}
	
	/**
	 * @param the LandcoverType this Task will act on
	 * @return this Task
	 */
	public Fertilize on(LandcoverType landcover)
	{
		this.landcover = landcover;
		return this;
	}
	
	/**
	 * Adds fertilizer to the lawn of the given parcel<p>
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
				RestartFileProxy restartProxy = EcosystemContext.getInstance().getBgcFileManager().getRestartInputFileProxy(landUnitID, landcover);
				
				// Update RestartFileProxy
				restartProxy.put( RestartType.sminn, Double.toString((Double.valueOf(restartProxy.get(RestartType.sminn))+fertilizerAmount)) );
	
				// Write the RestartFileProxy for this LandUnit-LandcoverType
				EcosystemContext.getInstance().getBgcFileManager().writeRestartInputProxy(restartProxy, landUnitID, landcover);
			}
		}
	}

//--------------------
// Accessors
	
	public Double getFertilizerAmount()
	{
		return fertilizerAmount;
	}

	public LandcoverType getLandcover()
	{
		return landcover;
	}

}
