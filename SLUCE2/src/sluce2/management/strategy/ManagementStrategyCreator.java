package sluce2.management.strategy;

public class ManagementStrategyCreator
{
	
	public static ManagementStrategy createStrategyProduct(ManagementStrategyType type, Integer parcelID)
	{
		ManagementStrategy strategy = null;
		
		switch(type)   
		{
			case IEMSS_1:
				
				strategy = new IEMSS1Strategy(parcelID);
				break;
				
			case IEMSS_2:
				
				strategy = new IEMSS2Strategy(parcelID);
				break;
				
			case IEMSS_3:
				
				strategy = new IEMSS3Strategy(parcelID);
				break;
				
			case IEMSS_4:
				
				strategy = new IEMSS4Strategy(parcelID);
				break;
		
		    default:

		    	strategy = new IEMSS1Strategy(parcelID);
				break;  
		}  
		
		// Create the task list for this strategy.
		strategy.createTaskList();
		return strategy;
	}
}
