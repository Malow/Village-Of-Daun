package com.malow.villageofdaun.world;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.AssetPaths;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.gfx.LibgdxRenderer;
import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.peasant.ai.astar.ExampleFactory;
import com.malow.villageofdaun.peasant.ai.astar.ExampleNode;
import com.malow.villageofdaun.peasant.ai.astar.Map;

public class World
{
	public final static float BLOCKSIZE = 10.0f;
	public final static int MAPSIZE = 200;
	public final static int NROFBLOCKS = (int) (MAPSIZE / BLOCKSIZE);
	
	private final static int TREE_PERCENT = 300;
	private final static int BRICK_PERCENT = 14;
	private final static int IRON_PERCENT = 11;
	private final static int FOODBUSH_PERCENT = 14;
	
	private LibgdxRenderer gfx;
	private List<List<WorldEntity>> worldBlocks;
	private List<WorldEntity> trees;
	private List<WorldEntity> bricks;
	private List<WorldEntity> irons;
	private List<WorldEntity> food;
	
	private Mesh terrain;
	
	public World()
	{
		this.gfx = Singletons.getGfx();
	}
	
	public WorldEntity getTile(Vector3 pos)
	{
		int i = (int) (pos.x / BLOCKSIZE);
		int u = (int) (pos.z / BLOCKSIZE);
		
		return worldBlocks.get(i).get(u);
	}
	
	public void create()
	{
		terrain = gfx.createMesh(AssetPaths.GRASS_MODEL);
		terrain.setPosition(new Vector3(MAPSIZE / 2.0f, 0.0f, MAPSIZE / 2.0f));
		terrain.setScale(new Vector3(MAPSIZE, 1.0f, MAPSIZE));
		
		// Astar Map
		Map<ExampleNode> astarMap = new Map<ExampleNode>(NROFBLOCKS, NROFBLOCKS, new ExampleFactory());
		Singletons.astarmap = astarMap;
		
		trees = new ArrayList<WorldEntity>();
		bricks = new ArrayList<WorldEntity>();
		irons = new ArrayList<WorldEntity>();
		food = new ArrayList<WorldEntity>();
		
		worldBlocks = new ArrayList<List<WorldEntity>>();
		for(int i = 0; i < NROFBLOCKS; i++)
		{
			worldBlocks.add(new ArrayList<WorldEntity>());
			for(int u = 0; u < NROFBLOCKS; u++)
			{
				worldBlocks.get(i).add(new Grass(new Vector3(i * BLOCKSIZE, 0.0f, u * BLOCKSIZE)));
				this.updateAstar(i, u, true);
			}
		}
		
		for(int i = 1; i < NROFBLOCKS - 1; i++)
		{
			for(int u = 1; u < NROFBLOCKS - 1; u++)
			{
				int random = (int) (Math.random() * 1000);
				
				if(random < TREE_PERCENT && this.trees.size() < ((NROFBLOCKS * NROFBLOCKS) * (TREE_PERCENT / 1000.0f)) * 1.5f)
				{
					Tree tree = new Tree(new Vector3(i * BLOCKSIZE, 0.0f, u * BLOCKSIZE));
					this.setTile(tree);
				}
				else if(random < TREE_PERCENT + BRICK_PERCENT && this.bricks.size() < ((NROFBLOCKS * NROFBLOCKS) * (BRICK_PERCENT / 1000.0f)) * 1.5f)
				{
					Brickvein brick = new Brickvein(new Vector3(i * BLOCKSIZE, 0.0f, u * BLOCKSIZE));
					this.setTile(brick);
				}
				else if(random < TREE_PERCENT + BRICK_PERCENT + IRON_PERCENT && this.irons.size() < ((NROFBLOCKS * NROFBLOCKS) * (IRON_PERCENT / 1000.0f)) * 1.5f)
				{
					Ironvein iron = new Ironvein(new Vector3(i * BLOCKSIZE, 0.0f, u * BLOCKSIZE));
					this.setTile(iron);
				}
				else if(random < TREE_PERCENT + BRICK_PERCENT + IRON_PERCENT + FOODBUSH_PERCENT && this.food.size() < ((NROFBLOCKS * NROFBLOCKS) * (FOODBUSH_PERCENT / 1000.0f)) * 1.5f)
				{
					Foodbush foodbush = new Foodbush(new Vector3(i * BLOCKSIZE, 0.0f, u * BLOCKSIZE));
					this.setTile(foodbush);
				}
			}
		}
		
		while(this.trees.size() < ((NROFBLOCKS * NROFBLOCKS) * (TREE_PERCENT / 1000.0f)) * 0.75f)
		{
			int i = (int) ((Math.random() - 0.001f) * NROFBLOCKS);
			int u = (int) ((Math.random() - 0.001f) * NROFBLOCKS);
				
			if(worldBlocks.get(i).get(u) instanceof Grass && i != 0 && i != NROFBLOCKS-1 && u != 0 && u != NROFBLOCKS-1)
			{
				Tree tree = new Tree(new Vector3(i * BLOCKSIZE, 0.0f, u * BLOCKSIZE));
				this.setTile(tree);
			}
		}
		
		fixTrees();
		fixTrees();
		fixTrees();
		
		
		while(this.bricks.size() < ((NROFBLOCKS * NROFBLOCKS) * (BRICK_PERCENT / 1000.0f)) * 0.75f)
		{
			int i = (int) ((Math.random() - 0.001f) * NROFBLOCKS);
			int u = (int) ((Math.random() - 0.001f) * NROFBLOCKS);
				
			if(worldBlocks.get(i).get(u) instanceof Grass && i != 0 && i != NROFBLOCKS-1 && u != 0 && u != NROFBLOCKS-1)
			{
				Brickvein brick = new Brickvein(new Vector3(i * BLOCKSIZE, 0.0f, u * BLOCKSIZE));
				this.setTile(brick);
			}
		}
		
		while(this.irons.size() < ((NROFBLOCKS * NROFBLOCKS) * (IRON_PERCENT / 1000.0f)) * 0.75f)
		{
			int i = (int) ((Math.random() - 0.001f) * NROFBLOCKS);
			int u = (int) ((Math.random() - 0.001f) * NROFBLOCKS);
				
			if(worldBlocks.get(i).get(u) instanceof Grass && i != 0 && i != NROFBLOCKS-1 && u != 0 && u != NROFBLOCKS-1)
			{
				Ironvein iron = new Ironvein(new Vector3(i * BLOCKSIZE, 0.0f, u * BLOCKSIZE));
				this.setTile(iron);
			}
		}
		
		while(this.food.size() < ((NROFBLOCKS * NROFBLOCKS) * (FOODBUSH_PERCENT / 1000.0f)) * 0.75f)
		{
			int i = (int) ((Math.random() - 0.001f) * NROFBLOCKS);
			int u = (int) ((Math.random() - 0.001f) * NROFBLOCKS);
				
			if(worldBlocks.get(i).get(u) instanceof Grass && i != 0 && i != NROFBLOCKS-1 && u != 0 && u != NROFBLOCKS-1)
			{
				Foodbush foodbush = new Foodbush(new Vector3(i * BLOCKSIZE, 0.0f, u * BLOCKSIZE));
				this.setTile(foodbush);
			}
		}
	}
	
	private void fixTrees()
	{
		for(int i = 1; i < NROFBLOCKS - 1; i++)
		{
			for(int u = 1; u < NROFBLOCKS - 1; u++)
			{
				if(worldBlocks.get(i).get(u) instanceof Tree)
				{
					int neighbourTrees = 0;
					if((worldBlocks.get(i+1).get(u) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i).get(u+1) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i).get(u-1) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i-1).get(u) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i+1).get(u+1) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i-1).get(u+1) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i+1).get(u-1) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i-1).get(u-1) instanceof Tree))
						neighbourTrees++;
					
					if(neighbourTrees < 2)
					{
						this.setTile(i, u, new Grass(new Vector3(i * BLOCKSIZE, 0.0f, u * BLOCKSIZE)));
					}
				}
				else
				{
					int neighbourTrees = 0;
					if((worldBlocks.get(i+1).get(u) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i).get(u+1) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i).get(u-1) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i-1).get(u) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i+1).get(u+1) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i-1).get(u+1) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i+1).get(u-1) instanceof Tree))
						neighbourTrees++;
					if((worldBlocks.get(i-1).get(u-1) instanceof Tree))
						neighbourTrees++;
					
					if(neighbourTrees > 4)
					{
						Tree tree = new Tree(new Vector3(i * BLOCKSIZE, 0.0f, u * BLOCKSIZE));
						this.setTile(tree);
					}
				}
			}
		}
	}
	
	public Vector3 getPossibleStartingPosition()
	{
		Vector3 pos = null;
		int attempts = 0;
		while(pos == null && attempts < 1000)
		{
			int i = (int) ((Math.random() - 0.001f) * (NROFBLOCKS / 2) + (NROFBLOCKS / 4));
			int u = (int) ((Math.random() - 0.001f) * (NROFBLOCKS / 2) + (NROFBLOCKS / 4));
		
			if(worldBlocks.get(i).get(u) instanceof Grass && worldBlocks.get(i).get(u+1) instanceof Grass)
			{
				pos = new Vector3(i * BLOCKSIZE, 0, u * BLOCKSIZE);
			}
			
			attempts++;
		}
		if(pos == null)
		{
			throw new RuntimeException("1000 attempts made and still failed.");
		}
		return pos;
	}
	
	public void update(float diff)
	{
		
	}
	
	public void dispose()
	{
		for(int i = 0; i < NROFBLOCKS; i++)
		{
			for(int u = 0; u < NROFBLOCKS; u++)
			{
				worldBlocks.get(i).get(u).dispose();
			}
			worldBlocks.get(i).clear();
		}
		worldBlocks.clear();
		
		trees.clear();
		bricks.clear();
		irons.clear();
		food.clear();
		
		gfx.deleteMesh(terrain);
	}
	
	public void setTile(int i, int u, WorldEntity entity)
	{
		WorldEntity we = worldBlocks.get(i).get(u);
		if(we instanceof Tree)
		{
			trees.remove(we);
		}
		else if(we instanceof Brickvein)
		{
			bricks.remove(we);
		}
		else if(we instanceof Ironvein)
		{
			irons.remove(we);
		}
		else if(we instanceof Foodbush)
		{
			food.remove(we);
		}
		we.dispose();

		// Set Astarmap
		if(entity instanceof Grass)
		{
			this.updateAstar(i, u, true);
		}
		else
		{
			this.updateAstar(i, u, false);
		}
		
		if(entity instanceof Tree)
		{
			trees.add(entity);
		}
		else if(entity instanceof Brickvein)
		{
			bricks.add(entity);
		}
		else if(entity instanceof Ironvein)
		{
			irons.add(entity);
		}
		else if(entity instanceof Foodbush)
		{
			food.add(entity);
		}
		worldBlocks.get(i).set(u, entity);
	}

	public void setTile(Vector3 pos, WorldEntity entity)
	{
		int i = (int) (pos.x / BLOCKSIZE);
		int u = (int) (pos.z / BLOCKSIZE);
		
		this.setTile(i, u, entity);
	}
	
	public void setTile(WorldEntity entity)
	{
		Vector3 pos = entity.getPosition();
		
		int i = (int) (pos.x / BLOCKSIZE);
		int u = (int) (pos.z / BLOCKSIZE);
		
		this.setTile(i, u, entity);
	}
	
	public List<WorldEntity> getTrees()
	{
		return this.trees;
	}
	
	public List<WorldEntity> getBrickveins()
	{
		return this.bricks;
	}
	
	public List<WorldEntity> getIronveins()
	{
		return this.irons;
	}
	
	public List<WorldEntity> getFoodbushes()
	{
		return this.food;
	}
	
	private void updateAstar(int i, int u, boolean walkable)
	{
		Singletons.astarmap.setWalkable(i, u, walkable);
		
//		for(int x = 0; x < NROFBLOCKS; x++)
//		{
//			for(int y = 0; y < NROFBLOCKS; y++)
//			{
//				Singletons.astarmap.setWalkable(((int)(i * BLOCKSIZE)) + x, ((int)(u * BLOCKSIZE)) + y, walkable);
//			}
//		}
	}
}
