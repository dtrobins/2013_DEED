package sluce2.management.strategy;

import sluce2.landscape.LandcoverType;
import sluce2.management.strategy.task.Fertilize;
import sluce2.management.strategy.task.Irrigate;
import sluce2.management.strategy.task.RemoveCoarseWoodyDebris;
import sluce2.management.strategy.task.RemoveLitter;

public class IEMSS4Strategy extends ManagementStrategy
{
	/**
	 * 
	 */
	public IEMSS4Strategy()
	{
		super();
	}
	
	/**
	 * Constructs this Management Strategy to work on the given Parcel.
	 * @param parcel
	 */
	public IEMSS4Strategy(int parcelID)
	{
		super(parcelID);
	}
	
	@Override
	protected void createTaskList()
	{
		// Additions
		taskList.add(Fertilize.MEDIUM.on(LandcoverType.TURFGRASS));		
		taskList.add(Irrigate.SCHEDULED.on(LandcoverType.TURFGRASS));	
		
		// Removals
		taskList.add(RemoveLitter.NONE.on(LandcoverType.TURFGRASS));		
		taskList.add(RemoveCoarseWoodyDebris.NONE.on(LandcoverType.TREECOVER));	
		
	}

}
