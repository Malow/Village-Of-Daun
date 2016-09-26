package com.malow.villageofdaun.resources;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.gfx.Mesh;

public abstract class Resource
{
	protected Mesh mesh;
	boolean taken = false;
	boolean reserved = false;

	public abstract void dispose();
	
	public void setPosition(Vector3 pos)
	{
		mesh.setPosition(pos);
	}
	
	public void reserve()
	{
		this.reserved = true;
	}
	
	public void unreserve()
	{
		this.reserved = false;
	}
	
	public boolean isReserved()
	{
		return this.reserved;
	}
	
	public Vector3 getPosition()
	{
		return mesh.getPosition();
	}
}
