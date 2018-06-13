package sluce2.management.strategy;

import sluce2.landscape.LandcoverType;
import sluce2.management.strategy.task.Fertilize;
import sluce2.management.strategy.task.Irrigate;
import sluce2.management.strategy.task.RemoveCoarseWoodyDebris;
import sluce2.management.strategy.task.RemoveLitter;

public class IEMSS1Strategy extends ManagementStrategy
{
	/**
	 * 
	 */
	public IEMSS1Strategy()
	{
		super();
	}
	
	/**
	 * Constructs this Management Strategy to work on the given Parcel.
	 * @param parcel
	 */
	public IEMSS1Strategy(int parcelID)
	{
		super(parcelID);
	}
	
	@Override
	protected void createTaskList()
	{
		// Additions
		taskList.add(Fertilize.NONE.on(LandcoverType.TURFGRASS));		
		taskList.add(Irrigate.NONE.on(LandcoverType.TURFGRASS));	
		
		// Removals
		taskList.add(RemoveLitter.NONE.on(LandcoverType.TURFGRASS));		
		taskList.add(RemoveCoarseWoodyDebris.NONE.on(LandcoverType.TREECOVER));	
	}

}
