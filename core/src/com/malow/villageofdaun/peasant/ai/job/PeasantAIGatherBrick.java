package com.malow.villageofdaun.peasant.ai.job;

import com.malow.villageofdaun.peasant.ai.task.AcquireBrickworks;
import com.malow.villageofdaun.peasant.ai.task.AcquireStorage;
import com.malow.villageofdaun.peasant.ai.task.DeliverResource;
import com.malow.villageofdaun.peasant.ai.task.GatherBrick;

public class PeasantAIGatherBrick extends PeasantAIJob
{
	@Override
	protected void createTasks(PeasantAIDataContainer container)
	{		
		AcquireBrickworks task1 = new AcquireBrickworks();
		task1.create(container);
		this.tasks.addLast(task1);
		
		GatherBrick task2 = new GatherBrick();
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
