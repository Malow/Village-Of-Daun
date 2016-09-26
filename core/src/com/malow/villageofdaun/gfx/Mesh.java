package com.malow.villageofdaun.gfx;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public class Mesh
{
	public ModelInstance instance;
	
	public Mesh(ModelInstance modelInstance)
	{
		instance = modelInstance;
	}
	
	public void setPosition(Vector3 pos)
	{
		instance.transform.setTranslation(pos);
	}
	
	public void move(Vector3 moveBy)
	{
		instance.transform.trn(moveBy);
	}

	public Vector3 getPosition()
	{
		Vector3 pos = new Vector3();
		return instance.transform.getTranslation(pos);
	}
	
	public void setScale(Vector3 scale)
	{
		instance.transform.scl(scale);
	}
	
	public void rotate(Vector3 axis, float degrees)
	{
		instance.transform.rotate(axis, degrees);
	}
	
	public Quaternion getRotation()
	{
		Quaternion quat = new Quaternion();
		instance.transform.getRotation(quat);
		return quat;
	}
	
	public void setRotation(Quaternion quat)
	{
		instance.transform.set(quat);
	}
}
