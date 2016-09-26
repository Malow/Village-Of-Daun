package com.malow.villageofdaun.peasant.ai.job;

import com.malow.villageofdaun.peasant.ai.task.AcquireFoodbush;
import com.malow.villageofdaun.peasant.ai.task.AcquireStorage;
import com.malow.villageofdaun.peasant.ai.task.DeliverResource;
import com.malow.villageofdaun.peasant.ai.task.GatherFood;

public class PeasantAIGatherFood extends PeasantAIJob
{
	@Override
	protected void createTasks(PeasantAIDataContainer container)
	{		
		AcquireFoodbush task1 = new AcquireFoodbush();
		task1.create(container);
		this.tasks.addLast(task1);
		
		GatherFood task2 = new GatherFood();
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