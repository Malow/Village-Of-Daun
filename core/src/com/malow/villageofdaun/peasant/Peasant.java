package com.malow.villageofdaun.peasant;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.AssetPaths;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.gfx.LibgdxRenderer;
import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.peasant.ai.job.PeasantAIConstructor;
import com.malow.villageofdaun.peasant.ai.job.PeasantAIGatherBrick;
import com.malow.villageofdaun.peasant.ai.job.PeasantAIGatherFood;
import com.malow.villageofdaun.peasant.ai.job.PeasantAIGatherIron;
import com.malow.villageofdaun.peasant.ai.job.PeasantAIGatherWood;
import com.malow.villageofdaun.peasant.ai.job.PeasantAIIdle;
import com.malow.villageofdaun.peasant.ai.job.PeasantAIJob;

public class Peasant
{
	private LibgdxRenderer gfx;
	private int state = -1;
	private Mesh mesh;
	private PeasantAIJob ai;
	
	public Peasant(Vector3 pos)
	{
		this.gfx = Singletons.getGfx();
		
		mesh = gfx.createMesh(AssetPaths.PEASANT_MODEL);
		mesh.setPosition(pos);
		
		this.setState(PeasantStates.IDLE);
		
		ai = new PeasantAIIdle();
		ai.create(mesh);
	}
	
	public void update(float diff)
	{
		if(ai != null)
		{
			ai.update(diff);
		}
	}
	
	public void dispose()
	{
		ai.dispose();
		gfx.deleteMesh(mesh);
	}
	

	public int getState()
	{
		return state;
	}

	public void setState(int newState)
	{		
		if(newState == PeasantStates.IDLE)
		{
			setAI(new PeasantAIIdle());
		}
		else if(newState == PeasantStates.CONSTRUCTING)
		{
			setAI(new PeasantAIConstructor());
		}
		else if(newState == PeasantStates.GATHERING_WOOD)
		{
			setAI(new PeasantAIGatherWood());
		}
		else if(newState == PeasantStates.GATHERING_FOOD)
		{
			setAI(new PeasantAIGatherFood());
		}
		else if(newState == PeasantStates.GATHERING_BRICK)
		{
			setAI(new PeasantAIGatherBrick());
		}
		else if(newState == PeasantStates.GATHERING_IRON)
		{
			setAI(new PeasantAIGatherIron());
		}
		
		this.state = newState;
	}
	
	
	private void setAI(PeasantAIJob newAI)
	{
		if(ai != null)
		{
			ai.dispose();
		}
		ai = newAI;
		ai.create(mesh);
	}
}
