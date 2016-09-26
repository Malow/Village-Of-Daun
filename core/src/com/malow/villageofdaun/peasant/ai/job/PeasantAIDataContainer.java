package com.malow.villageofdaun.peasant.ai.job;

import com.malow.villageofdaun.buildings.ResourceHolder;
import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.resources.Resource;
import com.malow.villageofdaun.world.WorldEntity;

public class PeasantAIDataContainer
{
	public Mesh mesh;
	public Resource resource;
	public Class<? extends Resource> resourceType;
	public WorldEntity gatherTarget;
	public ResourceHolder deliveryTarget;
	
}
