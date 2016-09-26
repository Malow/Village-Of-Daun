package com.malow.villageofdaun.peasant.ai.job;

import com.malow.villageofdaun.peasant.ai.task.AcquireConstructionSiteAndResource;
import com.malow.villageofdaun.peasant.ai.task.DeliverResource;
import com.malow.villageofdaun.peasant.ai.task.PickupResource;

public class PeasantAIConstructor extends PeasantAIJob
{
	@Override
	protected void createTasks(PeasantAIDataContainer container)
	{		
		AcquireConstructionSiteAndResource task1 = new AcquireConstructionSiteAndResource();
		task1.create(container);
		this.tasks.addLast(task1);
		
		PickupResource task2 = new PickupResource();
		task2.create(container);
		this.tasks.addLast(task2);
		
		DeliverResource task3 = new DeliverResource();
		task3.create(container);
		this.tasks.addLast(task3);
	}
}
