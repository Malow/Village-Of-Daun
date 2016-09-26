package com.malow.villageofdaun.peasant.ai.task;

import java.util.List;

import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.buildings.ConstructionSite;
import com.malow.villageofdaun.peasant.ai.PeasantAIHelper;
import com.malow.villageofdaun.peasant.ai.job.PeasantAIDataContainer;
import com.malow.villageofdaun.resources.Resource;

public class AcquireConstructionSiteAndResource implements PeasantAITask
{
	private PeasantAIDataContainer container;
	private boolean complete = false;

	@Override
	public void create(PeasantAIDataContainer container)
	{
		this.container = container;
	}

	@Override
	public void dispose()
	{
		
	}

	@Override
	public void update(float diff)
	{
		if(this.complete)
			return;
		
		if(this.container.mesh == null)
		{
			this.complete = true;
			return;
		}
		
		if(this.container.deliveryTarget == null)
		{
			List<ConstructionSite> sites = PeasantAIHelper.getConstructionSitesThatNeedsWorker();
			if(sites == null)
				return;
			
			for(ConstructionSite site : sites)
			{
				List<Class<? extends Resource>> requiredResources = site.getRequiredResources();
				for(Class<? extends Resource> requiredResource : requiredResources)
				{
					if(Singletons.getResourceManager().hasResource(requiredResource))
					{
						site.assignWorker(requiredResource);
						this.container.deliveryTarget = site;
						this.container.resourceType = requiredResource;
						this.complete = true;
						this.container.resource = PeasantAIHelper.acquireClosestResource(this.container.resourceType, this.container.mesh);
						break;
					}
				}
			
				if(this.complete)
					break;
			}
		}		
	}

	@Override
	public boolean isComplete()
	{
		return this.complete;
	}

}
