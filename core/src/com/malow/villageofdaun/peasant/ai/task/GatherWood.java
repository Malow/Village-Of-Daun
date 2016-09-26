package com.malow.villageofdaun.peasant.ai.task;

import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.resources.Resource;
import com.malow.villageofdaun.resources.Wood;

public class GatherWood extends GatherTask
{
	public static final float GATHERING_TIME = 20.0f;
	
	@Override
	public float getGatherTime()
	{
		return GATHERING_TIME;
	}

	@Override
	public Resource createResource(Mesh mesh)
	{
		return new Wood(mesh.getPosition());
	}
}
