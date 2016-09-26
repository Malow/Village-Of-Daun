package com.malow.villageofdaun;

import com.malow.villageofdaun.buildings.BuildingManager;
import com.malow.villageofdaun.callback.Callback;
import com.malow.villageofdaun.gfx.Camera;
import com.malow.villageofdaun.gfx.LibgdxRenderer;
import com.malow.villageofdaun.peasant.ai.astar.ExampleNode;
import com.malow.villageofdaun.peasant.ai.astar.Map;
import com.malow.villageofdaun.resources.ResourceManager;
import com.malow.villageofdaun.world.World;

public class Singletons
{
	public static LibgdxRenderer gfx;
	public static Camera cam;
	public static Callback activityManager;
	
	public static World world;
	public static ResourceManager res;
	public static BuildingManager build;
	public static Map<ExampleNode> astarmap;

	public static LibgdxRenderer getGfx()
	{
		return gfx;
	}
	
	public static Camera getCam()
	{
		return cam;
	}
	
	public static Callback getActvitiyManager()
	{
		return activityManager;
	}
	
	public static World getWorld()
	{
		return world;
	}
	
	public static ResourceManager getResourceManager()
	{
		return res;
	}
	
	public static BuildingManager getBuildingManager()
	{
		return build;
	}
	
	public static Map<ExampleNode> getAstarMap()
	{
		return astarmap;
	}
}
