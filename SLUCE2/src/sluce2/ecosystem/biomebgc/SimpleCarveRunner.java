package sluce2.ecosystem.biomebgc;

import java.util.ArrayList;
import java.util.List;

import sluce2.ecosystem.EcosystemComponent;
import sluce2.ecosystem.EcosystemDecorator;
import sluce2.ecosystem.biomebgc.proxy.RestartFileProxy;
import sluce2.landscape.component.Cell;
import sluce2.landscape.component.LandUnit;

/**
 * TODO: The only difference between this class and the SimpleCellRunner is
 * that this class will have the additional responsiblity of aggregating the LandcoverTypes.
 * This class may be re-organized, perhaps extending the functionality of SimpleCellRunner?
 * Keep here a placeholder/reminder for now. 
 * 
 * The SimpleCarveRunner adds the additional responsibility of running 
 * a concrete EcosystemComponent (e.g. BiomeBGCModel) at the level of a LandUnit by 
 * aggregating the Landcover Types and running the ecosystem model on each Landcover Type 
 * within each LandUnit.<p>
 * 
 * The ConcreteComponent of the Decorator pattern.
 * 
 * @See: Gamma, Erich; Richard Helm, Ralph Johnson, and John Vlissides (1995). Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley. ISBN 0-201-63361-2.
 * @See: LandUnit
 * 
 * @author Meghan Hutchins, Shipeng Sun
 * @date 02.01.2011
 */
public class SimpleCarveRunner<T extends LandUnit> extends EcosystemDecorator
{

	/** LandUnit being processed **/
	private LandUnit landUnit; 
	
	/** Represents data stored in the BiomeBGC Restart File **/
	private RestartFileProxy restartProxy; 
	
	/**
	 * @param EcosystemComponent component
	 */
	public SimpleCarveRunner(EcosystemComponent modelComponent)
	{
		super(modelComponent);
		restartProxy = new RestartFileProxy();
	}

	/**
	 * 1. Read data from RasterFiles
	 * 2. Write select data RasterFiles to component restart file
	 * 3. Run model
	 * 4. Read data from component restart file
	 * 5. Write restart data to RasterFiles
	 */
	@Override
	public void run()
	{
		modelComponent.run();
	}

//--------------------------
// Accessors 	
	


}
