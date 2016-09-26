package com.malow.villageofdaun.peasant.ai.job;

import java.util.LinkedList;

import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.peasant.ai.task.PeasantAITask;

public abstract class PeasantAIJob
{
	protected LinkedList<PeasantAITask> tasks;
	protected Mesh mesh;
	
	public void create(Mesh mesh)
	{
		this.mesh = mesh;
		tasks = new LinkedList<PeasantAITask>();
		
		PeasantAIDataContainer container = new PeasantAIDataContainer();
		container.mesh = this.mesh;
		
		this.createTasks(container);
	}
	
	public void dispose()
	{
		for(PeasantAITask task : tasks)
		{
			task.dispose();
		}
		tasks.clear();
	}
	
	public void update(float diff)
	{
		if(tasks.size() == 0)
		{
			PeasantAIDataContainer container = new PeasantAIDataContainer();
			container.mesh = this.mesh;
			
			this.createTasks(container);
			return;
		}
		
		PeasantAITask currentTask = tasks.getFirst();
		currentTask.update(diff);
		if(currentTask.isComplete())
		{
			tasks.removeFirst();
			currentTask.dispose();
		}
	}
	
	abstract protected void createTasks(PeasantAIDataContainer container);
}
