package sluce2.ecosystem.biomebgc;

import sluce2.ecosystem.EcosystemComponent;
import sluce2.ecosystem.EcosystemDecorator;
import sluce2.ecosystem.biomebgc.proxy.BiomeBGCProxyManager;
import sluce2.ecosystem.biomebgc.proxy.InitFileProxy;
import sluce2.ecosystem.biomebgc.proxy.RestartFileProxy;

public class BiomeBGCModelRunner extends EcosystemDecorator
{
	private BiomeBGCProxyManager proxyManager;
	
	/**
	 * @param EcosystemComponent component
	 */
	public BiomeBGCModelRunner(EcosystemComponent modelComponent)
	{
		super(modelComponent);
		proxyManager = new BiomeBGCProxyManager();
	}
	
	@Override
	public void run()
	{
		this.modelComponent.run();
	}

	
	
}
