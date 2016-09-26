package com.malow.villageofdaun.peasant.ai.task;

import com.malow.villageofdaun.buildings.ResourceHolder;
import com.malow.villageofdaun.peasant.ai.PeasantAIHelper;
import com.malow.villageofdaun.peasant.ai.job.PeasantAIDataContainer;

public class AcquireStorage implements PeasantAITask
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
			this.container.deliveryTarget = (ResourceHolder) PeasantAIHelper.getClosestStorage(container.mesh);
		}
		
		if(this.container.deliveryTarget != null)
		{
			this.complete = true;
		}
		
	}

	@Override
	public boolean isComplete()
	{
		return this.complete;
	}
}
