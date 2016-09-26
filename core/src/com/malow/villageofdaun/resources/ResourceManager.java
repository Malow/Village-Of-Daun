package com.malow.villageofdaun.resources;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector3;

public class ResourceManager
{
	private List<Food> foodList;
	private List<Wood> woodList;
	private List<Brick> brickList;
	private List<Iron> ironList;
	private List<Resource> allList;
	
	public void create()
	{
		foodList = new ArrayList<Food>();
		woodList = new ArrayList<Wood>();
		brickList = new ArrayList<Brick>();
		ironList = new ArrayList<Iron>();
		allList = new ArrayList<Resource>();
	}
	
	public Food createFood(Vector3 pos)
	{
		Food foodUnit = new Food(pos);
		foodList.add(foodUnit);
		allList.add(foodUnit);
		return foodUnit;
	}
	
	public Wood createWood(Vector3 pos)
	{
		Wood woodUnit = new Wood(pos);
		woodList.add(woodUnit);
		allList.add(woodUnit);
		return woodUnit;
	}
	
	public Brick createBrick(Vector3 pos)
	{
		Brick brickUnit = new Brick(pos);
		brickList.add(brickUnit);
		allList.add(brickUnit);
		return brickUnit;
	}
	
	public Iron createIron(Vector3 pos)
	{
		Iron ironUnit = new Iron(pos);
		ironList.add(ironUnit);
		allList.add(ironUnit);
		return ironUnit;
	}
	
	public void removeResource(Resource res)
	{
		allList.remove(res);
		if(res instanceof Wood)
		{
			woodList.remove(res);
		}
		else if(res instanceof Brick)
		{
			brickList.remove(res);
		}
		else if(res instanceof Iron)
		{
			ironList.remove(res);
		}
		else if(res instanceof Food)
		{
			foodList.remove(res);
		}
	}
	
	public List<Wood> getWood()
	{
		return this.woodList;
	}
	
	public List<Brick> getBrick()
	{
		return this.brickList;
	}
	
	public List<Iron> getIron()
	{
		return this.ironList;
	}
	
	public List<Food> getFood()
	{
		return this.foodList;
	}
	
	public List<Resource> getAll()
	{
		return this.allList;
	}
	
	public int getNrOfFood()
	{
		return foodList.size();
	}
	
	public int getNrOfWood()
	{
		return woodList.size();
	}
	
	public int getNrOfBrick()
	{
		return brickList.size();
	}
	
	public int getNrOfIron()
	{
		return ironList.size();
	}
	
	public void dispose()
	{
		for(Resource res : allList)
		{
			res.dispose();
		}
		allList.clear();
		foodList.clear();
		woodList.clear();
		brickList.clear();
		ironList.clear();
	}

	public void addResource(Resource res)
	{
		allList.add(res);
		if(res instanceof Wood)
		{
			woodList.add((Wood) res);
		}
		else if(res instanceof Brick)
		{
			brickList.add((Brick) res);
		}
		else if(res instanceof Iron)
		{
			ironList.add((Iron) res);
		}
		else if(res instanceof Food)
		{
			foodList.add((Food) res);
		}
	}
	
	public boolean hasResource(Class<? extends Resource> resClass)
	{
		boolean ret = false;
		if(resClass == Wood.class)
		{
			if(woodList.size() > 0)
				ret = true;
		}
		else if(resClass == Brick.class)
		{
			if(brickList.size() > 0)
				ret = true;
		}
		else if(resClass == Iron.class)
		{
			if(ironList.size() > 0)
				ret = true;
		}
		else if(resClass == Food.class)
		{
			if(foodList.size() > 0)
				ret = true;
		}
		return ret;
	}
}
