package sluce2.ecosystem.biomebgc;

import java.util.Map;

import repast.simphony.util.collections.Pair;
import sluce2.ecosystem.EcosystemComponent;
import sluce2.ecosystem.EcosystemContext;
import sluce2.ecosystem.EcosystemDecorator;
import sluce2.ecosystem.biomebgc.proxy.AnnualOutputFileProxy;
import sluce2.ecosystem.biomebgc.proxy.InitFileProxy;
import sluce2.ecosystem.biomebgc.proxy.RestartFileProxy;
import sluce2.landscape.LandcoverType;
import sluce2.landscape.LandscapeLayerType;
import sluce2.landscape.component.Cell;

/**
 * The SimpleCellRunner adds the additional responsibility of running 
 * a concrete EcosystemComponent (e.g. BiomeBGCModel) at the level of a Cell.<p>
 * 
 * The ConcreteComponent of the Decorator pattern.
 * 
 * @See: Gamma, Erich; Richard Helm, Ralph Johnson, and John Vlissides (1995). Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley. ISBN 0-201-63361-2.
 * @See: LandUnit, Cell
 * 
 * @author Meghan Hutchins, Shipeng Sun
 * @date 02.01.2011
 */

public class SimpleCellRunner extends EcosystemDecorator
{
	/** Cell being processed **/
	private Cell cell; 
	
	/**
	 * @param EcosystemComponent component
	 */
	public SimpleCellRunner(EcosystemComponent modelComponent)
	{
		super(modelComponent);
	}

	/**
	 * Run BiomeBGC
	 */
	@Override
	public void run()
	{
		this.checkLandUnit(); // Make sure what we have is a cell.
		Integer landUnitID = this.landUnit.getId();
		LandcoverType landcover = LandcoverType.getLandcoverType( ((Cell)this.landUnit).getValue(LandscapeLayerType.LANDCOVER).intValue());
		BiomeBGCFileManager bgcFileManager = BiomeBGCFileManager.getInstance();

		if(bgcFileManager.isLandcoverToBeProcessed(landcover))
		{
			// Set up and run BiomeBGC on this LandUnit and for this Landcover
			String currentDirectoryName = bgcFileManager.getCurrentDirectoryName(landUnitID, landcover);
			String currentInitFile = bgcFileManager.getCurrentInitFileName(landUnitID, landcover);
			modelComponent.run(currentDirectoryName, currentInitFile);
	
			/*
			 * Create the necessary proxy objects to record data and prepare for the next run.
			 */
			InitFileProxy initProxy = bgcFileManager.getInitFileProxy(landUnitID, landcover);
			RestartFileProxy restartOutputProxy = bgcFileManager.getRestartOutputFileProxy(landUnitID, landcover);
			AnnualOutputFileProxy annualOutputProxy = bgcFileManager.getAnnualOutputFileProxy(landUnitID, landcover);
			
			//EcosystemContext.logger.debug("-- Landcover: " + landcover + " --");
			//restartOutputProxy.print();
			
			//bgcFileManager.transferDataToLandscapeRaster(this.landUnit, initProxy, restartOutputProxy);
			bgcFileManager.transferDataToLandscapeRaster(this.landUnit, annualOutputProxy);
			//annualOutputProxy.print();
			
			// Prepare the files for the next BiomeBGC run (do this last).
			bgcFileManager.prepareForNextBiomeBGCRun(initProxy, restartOutputProxy, landUnitID, landcover);
		}
		else
		{
			//EcosystemContext.logger.debug("Landcover is: " + landcover + ". skipping...");
		}
	}
	
	/**
	 * Updates the landcover in this ecosystem
	 * @param changeList
	 */
	@Override
	public void updateLandcover(Map<Integer, Pair<LandcoverType, LandcoverType>> changeMap)
	{
		modelComponent.updateLandcover(changeMap);	
	}
	
	/**
	 * Checks to ensure that the LandUnit acted upon is of type Cell.
	 * If not, an assertion error is thrown and model execution stops. 
	 */
	private void checkLandUnit()
	{
		assert (this.landUnit instanceof Cell) : "Attempting to run SimpleCell decroator on LandUnit type: " + landUnit.getClass() + " - expecting " + Cell.class;
	}
		
	/**
	 * 
	 */
	@Override
	public String toString()
	{
		return this.getClass().getName();
	}
	
//--------------------------
// Accessors	
			
	public Cell getCell()
	{
		return cell;
	}

	public void setCell(Cell cell)
	{
		this.cell = cell;
	}
			
}
