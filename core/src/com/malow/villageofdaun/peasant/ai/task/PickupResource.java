package com.malow.villageofdaun.peasant.ai.task;

import com.malow.villageofdaun.peasant.ai.PeasantAIMover;
import com.malow.villageofdaun.peasant.ai.job.PeasantAIDataContainer;

public class PickupResource implements PeasantAITask
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
		this.mover.dispose();
		this.mover = null;
	}

	@Override
	public void update(float diff)
	{
		if(this.complete)
			return;
		
		if(this.container.resourceType == null || this.container.mesh == null || this.container.resource == null)
		{
			this.complete = true;
			return;
		}
		
		this.mover.update(diff, container.resource.getPosition(), container.mesh);
		if(this.mover.isComplete())
		{
			this.complete = true;
		}
	}

	@Override
	public boolean isComplete()
	{
		return complete;
	}
}
