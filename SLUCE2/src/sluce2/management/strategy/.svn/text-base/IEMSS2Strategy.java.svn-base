package sluce2.management.strategy;

import sluce2.landscape.LandcoverType;
import sluce2.management.strategy.task.Fertilize;
import sluce2.management.strategy.task.Irrigate;
import sluce2.management.strategy.task.RemoveCoarseWoodyDebris;
import sluce2.management.strategy.task.RemoveLitter;

public class IEMSS2Strategy extends ManagementStrategy
{
	/**
	 * 
	 */
	public IEMSS2Strategy()
	{
		super();
	}
	
	/**
	 * Constructs this Management Strategy to work on the given Parcel.
	 * @param parcel
	 */
	public IEMSS2Strategy(int parcelID)
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
		taskList.add(RemoveLitter.HIGH.on(LandcoverType.TURFGRASS));		
		taskList.add(RemoveCoarseWoodyDebris.HIGH.on(LandcoverType.TREECOVER));	
	}

}
