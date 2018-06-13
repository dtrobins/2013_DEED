package sluce2.management.strategy;

import java.util.ArrayList;
import java.util.List;

import sluce2.landscape.component.Parcel;
import sluce2.management.ManagementContext;
import sluce2.management.strategy.task.Task;

public abstract class ManagementStrategy
{
	/** The Parcel that this Management Strategy will be applied to. **/
	//protected Integer parcelID;
	
	/** A list of individual Tasks that make up this Management Strategy **/
	protected List<Task> taskList;
	
	/**
	 * 
	 */
	public ManagementStrategy()
	{
		//this.parcelID = null;
		this.taskList = new ArrayList<Task>();
	}
	
	/**
	 * Creates a NetworkStrategy that will act on the given parcel.
	 * @param populationList
	 */
	public ManagementStrategy(int parcelID)
	{
		//this.parcelID = parcelID;
		this.taskList = new ArrayList<Task>();
	}
	
	/** 
	 * Each ManagementStrategy concrete implementation will define its own task list.
	 * @return void
	 */
	protected abstract void createTaskList();
	
	/**
	 * Performs all tasks in the task list.
	 * @return void
	 */
	public void doTaskList(Integer parcelID)
	{
		ManagementContext.logger.debug("Doing task list for: " + this.getClass().getSimpleName() + " on parcel-" + parcelID);
		for(Task task : this.taskList)
		{
			task.doit(parcelID);
		}
		
	}

}

