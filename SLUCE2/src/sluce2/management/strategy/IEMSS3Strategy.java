package sluce2.management.strategy;

import sluce2.landscape.LandcoverType;
import sluce2.landscape.component.Parcel;
import sluce2.management.strategy.task.Fertilize;
import sluce2.management.strategy.task.Irrigate;
import sluce2.management.strategy.task.RemoveCoarseWoodyDebris;
import sluce2.management.strategy.task.RemoveLitter;

public class IEMSS3Strategy extends ManagementStrategy
{
	/**
	 * 
	 */
	public IEMSS3Strategy()
	{
		super();
	}
	
	/**
	 * Constructs this Management Strategy to work on the given Parcel.
	 * @param parcel
	 */
	public IEMSS3Strategy(int parcelID)
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
		taskList.add(RemoveLitter.HIGH.on(LandcoverType.TURFGRASS));		
		taskList.add(RemoveCoarseWoodyDebris.HIGH.on(LandcoverType.TREECOVER));
		
	}

}
