package com.malow.villageofdaun.peasant.ai.task;

import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.resources.Brick;
import com.malow.villageofdaun.resources.Resource;

public class GatherBrick extends GatherTask
{
	public static final float GATHERING_TIME = 30.0f;
	
	@Override
	public float getGatherTime()
	{
		return GATHERING_TIME;
	}

	@Override
	public Resource createResource(Mesh mesh)
	{
		return new Brick(mesh.getPosition());
	}
}

