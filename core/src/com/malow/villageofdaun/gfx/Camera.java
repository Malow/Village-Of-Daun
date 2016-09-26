package com.malow.villageofdaun.gfx;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class Camera
{
	public com.badlogic.gdx.graphics.Camera cam;
	
	public Camera(com.badlogic.gdx.graphics.Camera cam)
	{
		this.cam = cam;
	}
	
	public void setPosition(Vector3 pos)
	{
		cam.position.set(pos);
	}
	
	public Vector3 getPosition()
	{
		return new Vector3(cam.position);
	}
	
	public void move(Vector3 moveBy)
	{
		cam.translate(moveBy);
	}
	
	public void lookAt(Vector3 at)
	{
		cam.lookAt(at);
	}
	
	public Vector3 get3DPickingRay(float x, float y)
	{
		Ray ray = cam.getPickRay(x, y);
		return ray.direction;
	}

	public Vector3 getDirection()
	{
		return new Vector3(cam.direction);
	}
	
	public Vector3 get3DPickingRayGroundCollisionPoint(float x, float y)
	{
		Vector3 camRay = this.get3DPickingRay(x, y);
		float nrOfRaysToGround = this.getPosition().y / camRay.y;
		Vector3 groundCollisionPosition = this.getPosition().sub(camRay.scl(nrOfRaysToGround));
		return groundCollisionPosition;
	}
}
