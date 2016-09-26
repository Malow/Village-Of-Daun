package com.malow.villageofdaun.peasant.ai;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.buildings.Brickworks;
import com.malow.villageofdaun.buildings.ConstructionSite;
import com.malow.villageofdaun.buildings.Ironmine;
import com.malow.villageofdaun.buildings.Storage;
import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.resources.Resource;
import com.malow.villageofdaun.world.WorldEntity;

public class PeasantAIHelper
{

	public static Resource acquireClosestResource(Class<? extends Resource> resourceType, Mesh mesh)
	{
		List<Resource> resources = Singletons.getResourceManager().getAll();
		
		if(resources.size() == 0 || mesh == null || resourceType == null)
		{
			return null;
		}
		
		Resource closest = null;
		
		float closestDistance = 9999999.9f;
		for (Resource res : resources)
		{
			if (res != null && res.getClass().equals(resourceType) && res.isReserved() == false)
			{
				float distance = res.getPosition().sub(mesh.getPosition()).len();
				if (distance < closestDistance)
				{
					closest = res;
					closestDistance = distance;
				}
			}
		}
		if (closest != null)
		{
			Singletons.getResourceManager().removeResource(closest);
			closest.reserve();
		}
		
		return closest;
	}
	
	public static void standUp(Mesh mesh)
	{
		mesh.rotate(new Vector3(1, 0, 0), -90);
	}
	
	public static void lieDown(Mesh mesh)
	{
		mesh.rotate(new Vector3(1, 0, 0), 90);
	}

	public static List<ConstructionSite> getConstructionSitesThatNeedsWorker()
	{
		Deque<ConstructionSite> sites = Singletons.getBuildingManager().getConstructionSites();
		if(sites.size() == 0)
		{
			return null;
		}
		
		List<ConstructionSite> sitesNeedingWorkers = new ArrayList<ConstructionSite>();
		
		for(ConstructionSite site : sites)
		{
			List<Class<? extends Resource>> requiredResources = site.getRequiredResources();
			if(requiredResources.size() > 0)
			{
				sitesNeedingWorkers.add(site);
			}
		}
		
		return sitesNeedingWorkers;
	}

	public static WorldEntity getClosestTree(Mesh mesh)
	{
		List<WorldEntity> trees = Singletons.getWorld().getTrees();
		return getClosestTarget(trees, mesh);
	}
	
	public static WorldEntity getClosestBrickworks(Mesh mesh)
	{
		List<Brickworks> brickworks = Singletons.getBuildingManager().getBricksworks();
		return getClosestTarget(brickworks, mesh);
	}
	
	public static WorldEntity getClosestStorage(Mesh mesh)
	{
		List<Storage> storages = Singletons.getBuildingManager().getStorages();
		return getClosestTarget(storages, mesh);
	}

	public static WorldEntity getClosestIronmine(Mesh mesh)
	{
		List<Ironmine> ironmines = Singletons.getBuildingManager().getIronmines();
		return getClosestTarget(ironmines, mesh);
	}
	
	public static WorldEntity getClosestFoodbush(Mesh mesh)
	{
		List<WorldEntity> foodbushes = Singletons.getWorld().getFoodbushes();
		return getClosestTarget(foodbushes, mesh);
	}

	
	private static WorldEntity getClosestTarget(List<? extends WorldEntity> targets, Mesh mesh)
	{
		WorldEntity closest = null;
		float closestDistance = 9999999.9f;
		
		for(WorldEntity target : targets)
		{
			float distance = target.getPosition().sub(mesh.getPosition()).len();
			if(distance < closestDistance)
			{
				closest = target;
				closestDistance = distance;
			}
		}
		return closest;
	}
}
