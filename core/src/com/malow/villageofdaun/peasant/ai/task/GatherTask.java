package com.malow.villageofdaun.peasant.ai.task;

import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.peasant.ai.PeasantAIMover;
import com.malow.villageofdaun.peasant.ai.job.PeasantAIDataContainer;
import com.malow.villageofdaun.resources.Resource;

public abstract class GatherTask implements PeasantAITask
{
	public abstract float getGatherTime();
	public abstract Resource createResource(Mesh mesh);
	
	private PeasantAIDataContainer container;
	private boolean complete = false;
	private PeasantAIMover mover;
	private float timeSpentGathering = 0.0f;

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
		
		if(container.gatherTarget == null || container.mesh == null)
		{
			this.complete = true;
			return;
		}
		
		if(!this.mover.isComplete())
		{
			this.mover.update(diff, container.gatherTarget.getPositionCenter(), container.mesh);
		}
		else
		{
			this.timeSpentGathering += diff;
			if(this.timeSpentGathering > this.getGatherTime())
			{
				container.resource = this.createResource(container.mesh);
				container.resourceType = container.resource.getClass();
				
				this.complete = true;
				this.timeSpentGathering = 0.0f;
			}
		}
	}
	
	@Override
	public boolean isComplete()
	{
		return this.complete;
	}
}

