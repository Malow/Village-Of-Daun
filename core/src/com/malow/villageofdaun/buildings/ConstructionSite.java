package com.malow.villageofdaun.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.AssetPaths;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.buildings.BuildingRequirements.Recipe;
import com.malow.villageofdaun.gfx.LibgdxRenderer;
import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.resources.Brick;
import com.malow.villageofdaun.resources.Food;
import com.malow.villageofdaun.resources.Iron;
import com.malow.villageofdaun.resources.Resource;
import com.malow.villageofdaun.resources.Wood;


public class ConstructionSite extends ResourceHolder
{
	private LibgdxRenderer gfx;
	private Mesh mesh;
	private int buildingType;
	private boolean isFinished = false;
	
	private int assignedWood = 0;
	private int assignedBrick = 0;
	private int assignedIron = 0;
	private int assignedFood = 0;
	
	public ConstructionSite(Vector3 pos, int buildingType)
	{
		this.createSpec();
		this.gfx = Singletons.getGfx();
		mesh = gfx.createMesh(AssetPaths.CONSTRUCTION_MODEL);
		mesh.setPosition(pos);
		this.buildingType = buildingType;
	}
	
	public boolean hasAllMaterials()
	{
		Recipe recipe = BuildingRequirements.getRecipeFor(this.buildingType);
		if(wood.size() >= recipe.wood && brick.size() >= recipe.brick && iron.size() >= recipe.iron && food.size() >= recipe.food)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void addResource(Resource res)
	{
		this.addResourceToLine(res, mesh);
	}
	
	@Override
	public void removeResource(Resource res)
	{
		this.removeResourceFromLine(res, mesh);
	}
	
	public Vector3 getPosition()
	{
		
		//TODO nullpoint exception here when many builders, probably due to construction site completing and removing model but workers still try to work on it.
		return mesh.getPosition();
	}
	
	public int getBuildingType()
	{
		return this.buildingType;
	}
	
	public boolean isFinished()
	{
		return this.isFinished;
	}
	
	public void assignWorker(Class<? extends Resource> resourceType)
	{
		if(resourceType.equals(Wood.class))
		{
			assignedWood++;
		}
		else if(resourceType.equals(Brick.class))
		{
			assignedBrick++;
		}
		else if(resourceType.equals(Iron.class))
		{
			assignedIron++;
		}
		else if(resourceType.equals(Food.class))
		{
			assignedFood++;
		}
	}
	
	public void unassignWorker(Class<? extends Resource> resourceType)
	{
		if(resourceType.equals(Wood.class))
		{
			assignedWood--;
		}
		else if(resourceType.equals(Brick.class))
		{
			assignedBrick--;
		}
		else if(resourceType.equals(Iron.class))
		{
			assignedIron--;
		}
		else if(resourceType.equals(Food.class))
		{
			assignedFood--;
		}
	}
	
	public List<Class<? extends Resource>> getRequiredResources()
	{
		List<Class<? extends Resource>> list = new ArrayList<Class<? extends Resource>>();
		
		Recipe recipe = BuildingRequirements.getRecipeFor(this.buildingType);
		if(assignedWood < recipe.wood)
		{
			list.add(Wood.class);
		}
		if(assignedBrick < recipe.brick)
		{
			list.add(Brick.class);
		}
		if(assignedIron < recipe.iron)
		{
			list.add(Iron.class);
		}
		if(assignedFood < recipe.food)
		{
			list.add(Food.class);
		}
		
		return list;
	}
	
	@Override
	public void dispose()
	{
		this.isFinished = true;
		this.disposeSpec();
		if(this.mesh != null)
		{
			gfx.deleteMesh(this.mesh);
			this.mesh = null;
		}
	}
}
