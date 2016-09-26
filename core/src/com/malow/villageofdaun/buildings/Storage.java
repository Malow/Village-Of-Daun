package com.malow.villageofdaun.buildings;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.AssetPaths;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.gfx.Mesh;
import com.malow.villageofdaun.resources.Resource;

public class Storage extends ResourceHolder
{
	protected Mesh mesh;
	
	public Storage(Vector3 pos)
	{
		this.createSpec();
		mesh = Singletons.getGfx().createMesh(AssetPaths.STORAGE_MODEL);
		mesh.setPosition(pos);
	}
	
	public void dispose()
	{
		this.disposeSpec();
		Singletons.getGfx().deleteMesh(mesh);
	}
	
	@Override
	public void addResource(Resource res)
	{
		this.addResourceToLine(res, mesh);
		Singletons.getResourceManager().addResource(res);
	}
	
	@Override
	public void removeResource(Resource res)
	{
		this.removeResourceFromLine(res, mesh);
	}

	@Override
	public Vector3 getPosition()
	{
		return mesh.getPosition();
	}
}
