package com.malow.villageofdaun.buildings;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.AssetPaths;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.world.Brickvein;
import com.malow.villageofdaun.world.Grass;
import com.malow.villageofdaun.world.Ironvein;
import com.malow.villageofdaun.world.World;
import com.malow.villageofdaun.world.WorldEntity;

public class BuildingManager
{
	private List<Storage> storages;
	private List<Brickworks> brickworks;
	private List<Ironmine> ironmines;
	private List<House> houses;
	private Deque<ConstructionSite> constructionsites;
	
	public static final int NO_COLLISION = 0;
	public static final int CURRENTLY_BUILDING_COLLISION = 1;
	
	private boolean isBuilding = false;
	private int currentlyBuilding = 0;
	private Mesh buildingMesh;
	
	public void create()
	{
		storages = new ArrayList<Storage>();
		brickworks = new ArrayList<Brickworks>();
		ironmines = new ArrayList<Ironmine>();
		houses = new ArrayList<House>();
		constructionsites = new ArrayDeque<ConstructionSite>();
	}
	
	public void update(float diff)
	{
		List<ConstructionSite> completedSites = new ArrayList<ConstructionSite>();
		for(ConstructionSite site : constructionsites)
		{
			if(site.hasAllMaterials())
			{
				this.build(site.getBuildingType(), site.getPosition());
				site.dispose();
				completedSites.add(site);
			}
		}
		for(ConstructionSite site : completedSites)
		{
			constructionsites.remove(site);
		}
		completedSites.clear();
	}
	
	public boolean canBuild()
	{
		WorldEntity tile = Singletons.getWorld().getTile(buildingMesh.getPosition());
		if(currentlyBuilding == BuildingTypes.STORAGE)
		{
			if(tile instanceof Grass)
			{
				return true;
			}
			return false;
		}
		else if(currentlyBuilding == BuildingTypes.BRICKWORKS)
		{
			if(tile instanceof Brickvein)
			{
				return true;
			}
			return false;
		}		
		else if(currentlyBuilding == BuildingTypes.IRONMINE)
		{
			if(tile instanceof Ironvein)
			{
				return true;
			}
			return false;
		}
		else if(currentlyBuilding == BuildingTypes.HOUSE)
		{
			if(tile instanceof Grass)
			{
				return true;
			}
			return false;
		}
		
		
		return false;
	}
	
	public WorldEntity build(int buildingType, Vector3 pos)
	{
		WorldEntity building = null;
		if(buildingType == BuildingTypes.STORAGE)
		{
			building = new Storage(pos);
			storages.add((Storage) building);
		}
		else if(buildingType == BuildingTypes.BRICKWORKS)
		{
			building = new Brickworks(pos);
			brickworks.add((Brickworks) building);
		}
		else if(buildingType == BuildingTypes.IRONMINE)
		{
			building = new Ironmine(pos);
			ironmines.add((Ironmine) building);
		}
		else if(buildingType == BuildingTypes.HOUSE)
		{
			building = new House(pos);
			houses.add((House) building);
		}
		Singletons.getWorld().setTile(pos, building);
		return building;
	}
	
	public void startConstruction(int buildingType, Vector3 pos)
	{
		WorldEntity building = new ConstructionSite(pos, buildingType);
		constructionsites.addLast((ConstructionSite) building);
		Singletons.getWorld().setTile(pos, building);
	}
	
	public void buildCurrentBuilding()
	{
		if(isBuilding && this.canBuild())
		{
			this.startConstruction(currentlyBuilding, buildingMesh.getPosition());
			stopBuilding();
		}
	}
	
	public void startBuilding(int building, Vector3 pos)
	{
		if(isBuilding)
		{
			stopBuilding();
		}
		
		currentlyBuilding = building;		
		isBuilding = true;	
		if(building == BuildingTypes.STORAGE)
		{
			buildingMesh = Singletons.getGfx().createMesh(AssetPaths.STORAGE_MODEL);
		}
		else if(building == BuildingTypes.BRICKWORKS)
		{
			buildingMesh = Singletons.getGfx().createMesh(AssetPaths.BRICKWORKS_MODEL);
		}
		else if(building == BuildingTypes.IRONMINE)
		{
			buildingMesh = Singletons.getGfx().createMesh(AssetPaths.IRONMINE_MODEL);
		}
		else if(building == BuildingTypes.HOUSE)
		{
			buildingMesh = Singletons.getGfx().createMesh(AssetPaths.HOUSE_MODEL);
		}
		
		buildingMesh.setPosition(pos);
	}
	
	public void stopBuilding()
	{
		currentlyBuilding = 0;
		isBuilding = false;
		if(buildingMesh != null)
		{
			Singletons.getGfx().deleteMesh(buildingMesh);
			buildingMesh = null;
		}
	}
	
	public boolean updateBuildingPosition(float x, float z)
	{
		int posX = (int) (x / World.BLOCKSIZE);
		int posZ = (int) (z / World.BLOCKSIZE);
		
		int meshPosX = (int) (buildingMesh.getPosition().x / World.BLOCKSIZE);
		int meshPosZ = (int) (buildingMesh.getPosition().z / World.BLOCKSIZE);
		if(posX == meshPosX && posZ == meshPosZ)
		{
			return false;
		}
		
		posX *= World.BLOCKSIZE;
		posZ *= World.BLOCKSIZE;
		buildingMesh.setPosition(new Vector3(posX, 0, posZ));
		if(this.canBuild())
		{
			// Keep / set the model of the building TODO
		}
		else
		{
			// Keep / set the model of failed building TODO
		}
		return true;
	}
	
	public int checkBuildingCollision(float x, float z)
	{
		if(isBuilding)
		{
			Vector3 meshPos = buildingMesh.getPosition();
			if(x > meshPos.x && x < meshPos.x + World.BLOCKSIZE && z > meshPos.z && z < meshPos.z + World.BLOCKSIZE)
			{
				return CURRENTLY_BUILDING_COLLISION;
			}
		}
		
		return NO_COLLISION;
	}
	
	public void dispose()
	{
		this.stopBuilding();
	}
	
	public List<Storage> getStorages()
	{
		return this.storages;
	}
	
	public List<Brickworks> getBricksworks()
	{
		return this.brickworks;
	}
	
	public List<Ironmine> getIronmines()
	{
		return this.ironmines;
	}
	
	public List<House> getHouses()
	{
		return this.houses;
	}
	
	public Deque<ConstructionSite> getConstructionSites()
	{
		return this.constructionsites;
	}	
}
