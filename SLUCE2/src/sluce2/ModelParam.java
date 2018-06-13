package sluce2;

/**
 * ModelParam contains names of the parameters found in the parameter.xml file.
 * @see: sluce2.rs/parameters.xml 
 * 
 * @author Meghan Hutchins
 * @date 09/16/2010
 */
public enum ModelParam
{
	runLength,
	randomSeed,
	startYear,
	initNbrOfDeveloperAgents,
	initNbrOfLandBrokerAgents,
	initNbrOfResidentHouseholdAgents,
	
	litterRemovalMultiplier,
	coarseWoodyDebrisRemovalMultiplier,
	
	prctLitterRemovedNone,
	prctLitterRemovedHigh,
	prctLitterRemovedMedium,
	prctLitterRemovedLow,
	
	prctCoarseWoodyDebrisRemovedNone,
	prctCoarseWoodyDebrisRemovedHigh,
	prctCoarseWoodyDebrisRemovedMedium,
	prctCoarseWoodyDebrisRemovedLow,
	
	fertilizerAdditionNone,
	fertilizerAdditionHigh,
	fertilizerAdditionMedium,
	fertilizerAdditionLow;

	
   // public static int START_YEAR = 1930;
   // public static String initNbrOfDevelopers; 
    
}
