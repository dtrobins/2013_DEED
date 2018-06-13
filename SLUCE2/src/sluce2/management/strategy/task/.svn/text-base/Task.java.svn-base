package sluce2.management.strategy.task;

import sluce2.landscape.LandcoverType;


/**
 * 
 * @author Meghan Hutchins
 * @since 2011.03.07
 */
public interface Task
{	
	/** Each concrete task will implement how the task will be done for the given parcel. */
	public Task on(LandcoverType landcover);
	
	/** Perform the task. */
	public void doit(Integer parcelID);
	
}
