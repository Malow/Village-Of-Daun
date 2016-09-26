package com.malow.villageofdaun.peasant.ai.task;

import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.resources.Iron;
import com.malow.villageofdaun.resources.Resource;

public class GatherIron extends GatherTask
{
	public static final float GATHERING_TIME = 35.0f;
	
	@Override
	public float getGatherTime()
	{
		return GATHERING_TIME;
	}

	@Override
	public Resource createResource(Mesh mesh)
	{
		return new Iron(mesh.getPosition());
	}
}

