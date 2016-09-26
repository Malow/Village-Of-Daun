package com.malow.villageofdaun.resources;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.AssetPaths;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.gfx.LibgdxRenderer;

public class Wood extends Resource
{
	private LibgdxRenderer gfx;
	
	public Wood(Vector3 pos)
	{
		this.gfx = Singletons.getGfx();
		mesh = gfx.createMesh(AssetPaths.WOODUNIT_MODEL);
		mesh.setPosition(pos);
	}
	
	@Override
	public void dispose()
	{
		if(this.mesh != null)
		{
			gfx.deleteMesh(mesh);
			this.mesh = null;
		}
	}
}
