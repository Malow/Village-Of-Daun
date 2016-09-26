package com.malow.villageofdaun.world;

import com.badlogic.gdx.math.Vector3;

public class Grass extends WorldEntity
{
	private Vector3 pos;
	
	public Grass(Vector3 pos)
	{
		this.pos = pos;
	}

	@Override
	public void dispose()
	{
		
	}

	@Override
	public Vector3 getPosition()
	{
		return pos;
	}
}
