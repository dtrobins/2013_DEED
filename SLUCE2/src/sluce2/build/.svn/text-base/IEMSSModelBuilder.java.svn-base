package sluce2.build;

import repast.simphony.context.Context;
import sluce2.GlobalConstants;
import sluce2.Model;
import sluce2.ModelContext;
import sluce2.landscape.LandscapeContext;
import sluce2.ecosystem.EcosystemContext;
import sluce2.ecosystem.EcosystemModel;
import sluce2.ecosystem.biomebgc.BiomeBGCModel;
import sluce2.ecosystem.biomebgc.BiomeBGCProperties;
import sluce2.ecosystem.biomebgc.SimpleCellRunner;

public class IEMSSModelBuilder extends ModelBuilder
{
	/** The current Model instance **/
	private Model currentModel;
	
	/**
	 * Builds an IEMSS Model from the given properties file and adds the model to the given Repast Simphony context.
	 */
	public IEMSSModelBuilder(String pathToModelProperties, Context<Object> repastContext)
	{
		super(pathToModelProperties);
		currentModel = Model.createInstance();
		currentModel.setModelResources(this.modelResources);
		currentModel.setContext(repastContext);
	}
	
	/**
	 * Builds the LandscapeComponent 
	 */
	@Override
	public void buildLandscapeComponent()
	{
		currentModel.setLandscapeComponent(LandscapeContext.createInstance(this.modelResources.getString(GlobalConstants.LANDSCAPE_DESCRIPTOR)));
	}
	
	/**
	 * Builds the EcosystemComponent 
	 */
	@Override
	public void buildEcosystemComponent()
	{
		BiomeBGCProperties bgcProperties = new BiomeBGCProperties(this.modelResources.getString(GlobalConstants.ECOSYSTEM_MODEL_DESCRIPTOR));
		EcosystemModel ecosystemModel = new EcosystemModel(new SimpleCellRunner(new BiomeBGCModel(bgcProperties)));
		currentModel.setEcosystemComponent(EcosystemContext.createInstance(ecosystemModel));
		ModelContext.getLogger().debug("EcosystemComponent Created.");
	}
	
	/**
	 * Finalizes and returns the current model.
	 * @return Model
	 */
	public Model getModel()
	{
		return currentModel;
	}
}
