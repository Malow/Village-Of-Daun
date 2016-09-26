package com.malow.villageofdaun.peasant.ai.task;

import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.peasant.ai.PeasantAIMover;
import com.malow.villageofdaun.peasant.ai.job.PeasantAIDataContainer;
import com.malow.villageofdaun.buildings.ConstructionSite;

public class DeliverResource implements PeasantAITask
{
	private boolean complete = false;
	private PeasantAIDataContainer container;
	private PeasantAIMover mover;
	
	@Override
	public void create(PeasantAIDataContainer container)
	{
		this.container = container;
		this.mover = new PeasantAIMover();
		this.mover.create();
	}

	@Override
	public void dispose()
	{
		if(!this.complete)
		{
			if(this.container.resource != null)
			{
				container.resource.unreserve();
				Singletons.getResourceManager().addResource(container.resource);
			}
			if(this.container.deliveryTarget instanceof ConstructionSite)
			{
				((ConstructionSite) this.container.deliveryTarget).unassignWorker(this.container.resourceType);
			}
		}
		this.mover.dispose();
		this.mover = null;
	}

	@Override
	public void update(float diff)
	{
		if(this.complete)
			return;
		
		if(this.container.deliveryTarget == null || this.container.mesh == null || this.container.resource == null)
		{
			this.complete = true;
			if(this.container.resource != null)
				Singletons.getResourceManager().addResource(container.resource);
				
			return;
		}
		
		this.mover.update(diff, container.deliveryTarget.getPositionCenter(), container.mesh);
		this.container.resource.setPosition(container.mesh.getPosition());
		if(mover.isComplete())
		{
			this.complete = true;
			this.container.deliveryTarget.addResource(this.container.resource);
		}
	}

	@Override
	public boolean isComplete()
	{
		return complete;
	}

}
