package sluce2.build;

import sluce2.GlobalConstants;
import sluce2.Model;
import sluce2.ModelContext;
import sluce2.agent.AgentContext;
import sluce2.agent.AgentRegistry;
import sluce2.ecosystem.EcosystemContext;
import sluce2.ecosystem.EcosystemModel;
import sluce2.ecosystem.biomebgc.BiomeBGCModel;
import sluce2.ecosystem.biomebgc.BiomeBGCProperties;
import sluce2.ecosystem.biomebgc.SimpleCellRunner;
import sluce2.institution.InstitutionContext;
import sluce2.landscape.LandscapeContext;
import sluce2.management.ManagementContext;
import sluce2.market.MarketContext;


/**
 * The SampleModelBuilder constructs a Model object inteded to be used for testing and debugging purposes.
 * 
 * @See: Builder Pattern in: Gamma, Erich; Richard Helm, Ralph Johnson, and John Vlissides (1995). Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley. ISBN 0-201-63361-2.
 *
 * @author Meghan Hutchins
 * @date 02.04.2011
 */
public class SampleModelBuilder extends ModelBuilder
{
	/** The current Model instance **/
	private Model currentModel;
	
	/**
	 * Builds an IEMSS Model from the given properties file and adds the model to the given Repast Simphony context.
	 */
	public SampleModelBuilder(String pathToModelProperties)
	{
		super(pathToModelProperties);
		currentModel = Model.createInstance();
		currentModel.setModelResources(this.modelResources);
	}
		
	/**
	 * Builds the Ecosystem Component
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
	 * Builds the Landscape Component
	 */
	@Override
	public void buildLandscapeComponent()
	{
		currentModel.setLandscapeComponent(LandscapeContext.createInstance(this.modelResources.getString(GlobalConstants.LANDSCAPE_DESCRIPTOR)));
		currentModel.getLandscapeComponent().attach(currentModel.getEcosystemComponent());
		ModelContext.getLogger().debug("LandscapeComponent Created.");
	}
	
	/**
	 * Builds the Agent Component
	 */
	@Override
	public void buildAgentComponent()
	{
		AgentRegistry agentRegistry = new AgentRegistry(ModelContext.model);
		currentModel.setAgentComponent(AgentContext.createInstance(agentRegistry));
		ModelContext.getLogger().debug("AgentComponent Created.");
	}
	
	/**
	 * Builds the Management Component
	 */
	@Override
	public void buildManagementComponent()
	{
		currentModel.setManagementComponent(ManagementContext.createInstance());
		
		String[] templateNames = {"IEMSS-1", "IEMSS-Init"};
		currentModel.getManagementComponent().loadDevelopmentTemplates(currentModel.getResource(GlobalConstants.DEVELOPMENT_TEMPLATE_DESCRIPTOR), templateNames);
		ModelContext.getLogger().debug("LandscapeComponent Created.");
	}
	
	/**
	 * Builds the Market Component
	 */
	@Override
	public void buildMarketComponent()
	{
		currentModel.setMarketComponent(MarketContext.createInstance());
		ModelContext.getLogger().debug("MarketComponent Created.");
	}
	
	/**
	 * Builds the Institution Component
	 */
	@Override
	public void buildInstitutionComponent()
	{
		currentModel.setInstitutionComponent(InstitutionContext.createInstance());
		ModelContext.getLogger().debug("InstitutionComponent Created.");
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
