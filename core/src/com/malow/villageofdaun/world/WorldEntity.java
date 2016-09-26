package com.malow.villageofdaun.world;

import com.badlogic.gdx.math.Vector3;

public abstract class WorldEntity
{
	public abstract void dispose();
	
	public abstract Vector3 getPosition();
	
	public Vector3 getPositionCenter()
	{
		Vector3 pos = this.getPosition();
		pos.x += World.BLOCKSIZE / 2.0f;
		pos.z += World.BLOCKSIZE / 2.0f;
		return pos;
	}
}
