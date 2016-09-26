package com.malow.villageofdaun.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.resources.Brick;
import com.malow.villageofdaun.resources.Food;
import com.malow.villageofdaun.resources.Iron;
import com.malow.villageofdaun.resources.Resource;
import com.malow.villageofdaun.resources.Wood;

public abstract class ResourceHolder extends Building
{
	private final float RESOURCE_SIZE = 1.2f;
	
	protected List<Wood> wood;
	protected List<Brick> brick;
	protected List<Iron> iron;
	protected List<Food> food;
	protected List<Resource> allRes;

	protected void createSpec()
	{
		wood = new ArrayList<Wood>();
		brick = new ArrayList<Brick>();
		iron = new ArrayList<Iron>();
		food = new ArrayList<Food>();
		allRes = new ArrayList<Resource>();
	}
	
	protected void disposeSpec()
	{
		for(Resource res : allRes)
		{
			res.dispose();
		}
		allRes.clear();
	}
	
	abstract public void addResource(Resource res);
	abstract public void removeResource(Resource res);
	
	protected void addResourceToLine(Resource res, Mesh myMesh)
	{
		int i = (int) (allRes.size() / 7.0f);
		int u = allRes.size() - i * 7;
		res.setPosition(myMesh.getPosition().add(new Vector3(i * RESOURCE_SIZE + 1, 0, u * RESOURCE_SIZE + 1)));
		allRes.add(res);
		
		if(res instanceof Wood)
		{
			wood.add((Wood) res);
		}
		else if(res instanceof Brick)
		{
			brick.add((Brick) res);
		}
		else if(res instanceof Iron)
		{
			iron.add((Iron) res);
		}
		else if(res instanceof Food)
		{
			food.add((Food) res);
		}
	}
	
	protected void removeResourceFromLine(Resource res, Mesh myMesh)
	{
		allRes.remove(res);
		
		for(int q = 0; q < allRes.size(); q++)
		{
			int i = (int) (q / 5.0f);
			int u = q - i * 5;
			allRes.get(q).setPosition(myMesh.getPosition().add(new Vector3(i * 2 + 1, 0, u * 2 + 1)));
		}
		
		if(res instanceof Wood)
		{
			wood.remove((Wood) res);
		}
		else if(res instanceof Brick)
		{
			brick.remove((Brick) res);
		}
		else if(res instanceof Iron)
		{
			iron.remove((Iron) res);
		}
		else if(res instanceof Food)
		{
			food.remove((Food) res);
		}
	}
}
