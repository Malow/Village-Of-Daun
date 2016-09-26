package com.malow.villageofdaun.peasant.ai;

import java.util.List;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.peasant.ai.astar.ExampleNode;
import com.malow.villageofdaun.world.World;

public class PeasantAIMover
{
	public final static float MOVEMENT_SPEED = 2.0f;
	
	private boolean complete = false;
	
	List<ExampleNode> path;
	
	public void create()
	{
	}
	
	public void update(float diff, Vector3 target, Mesh mesh)
	{
		if(this.complete)
		{
			return;
		}
		
		if(this.path == null)
		{
			int myX = (int) (mesh.getPosition().x / World.BLOCKSIZE);
			int myY = (int) (mesh.getPosition().z / World.BLOCKSIZE);
			int tX = (int) (target.x / World.BLOCKSIZE);
			int tY = (int) (target.z / World.BLOCKSIZE);
			Singletons.getAstarMap().setWalkable(myX, myY, true);
			Singletons.getAstarMap().setWalkable(tX, tY, true);
			this.path = Singletons.getAstarMap().findPath(myX, myY, tX, tY);
			Singletons.getAstarMap().setWalkable(myX, myY, false);
			Singletons.getAstarMap().setWalkable(tX, tY, false);
			// TODO: This is haxxfix as fuck, make it proper
		}
		
		if(this.path.size() == 0)
		{
			this.complete = true;
			return;
		}
		
		Vector3 subTarget = new Vector3(path.get(0).getxPosition() * World.BLOCKSIZE + World.BLOCKSIZE / 2, 0, path.get(0).getyPosition() * World.BLOCKSIZE + World.BLOCKSIZE / 2);
		
		boolean reachedTarget = this.moveTowardsLocationAndReturnReached(subTarget, mesh, diff);
		if(reachedTarget)
		{
			this.path.remove(0);
		}
	}
	
	private boolean moveTowardsLocationAndReturnReached(Vector3 location, Mesh mesh, float diff)
	{
		Vector3 direction = location.sub(mesh.getPosition());
		if(direction.len() < 1.0f)
		{
			return true;				
		}
		else
		{
			direction.nor();
			mesh.setPosition(mesh.getPosition().add(new Vector3(direction.scl(MOVEMENT_SPEED * diff))));
		}
		
		return false;
	}
	
	public void dispose()
	{
		
	}
	
	public boolean isComplete()
	{
		return this.complete;
	}
	
}
