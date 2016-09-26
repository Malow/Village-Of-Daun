package com.malow.villageofdaun.peasant.ai.job;

import com.malow.villageofdaun.peasant.ai.task.AcquireStorage;
import com.malow.villageofdaun.peasant.ai.task.AcquireTree;
import com.malow.villageofdaun.peasant.ai.task.DeliverResource;
import com.malow.villageofdaun.peasant.ai.task.GatherWood;

public class PeasantAIGatherWood extends PeasantAIJob
{
	@Override
	protected void createTasks(PeasantAIDataContainer container)
	{		
		AcquireTree task1 = new AcquireTree();
		task1.create(container);
		this.tasks.addLast(task1);
		
		GatherWood task2 = new GatherWood();
		task2.create(container);
		this.tasks.addLast(task2);
		
		AcquireStorage task3 = new AcquireStorage();
		task3.create(container);
		this.tasks.addLast(task3);
		
		DeliverResource task4 = new DeliverResource();
		task4.create(container);
		this.tasks.addLast(task4);
	}
}
