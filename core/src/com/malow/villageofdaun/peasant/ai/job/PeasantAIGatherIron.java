package com.malow.villageofdaun.peasant.ai.job;

import com.malow.villageofdaun.peasant.ai.task.AcquireIronmine;
import com.malow.villageofdaun.peasant.ai.task.AcquireStorage;
import com.malow.villageofdaun.peasant.ai.task.DeliverResource;
import com.malow.villageofdaun.peasant.ai.task.GatherIron;

public class PeasantAIGatherIron extends PeasantAIJob
{
	@Override
	protected void createTasks(PeasantAIDataContainer container)
	{		
		AcquireIronmine task1 = new AcquireIronmine();
		task1.create(container);
		this.tasks.addLast(task1);
		
		GatherIron task2 = new GatherIron();
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