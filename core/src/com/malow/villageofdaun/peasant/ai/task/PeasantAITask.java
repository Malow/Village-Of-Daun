package com.malow.villageofdaun.peasant.ai.task;

import com.malow.villageofdaun.peasant.ai.job.PeasantAIDataContainer;

public interface PeasantAITask
{
	public void create(PeasantAIDataContainer container);
	public void dispose();
	public void update(float diff);
	
	public boolean isComplete();
}
