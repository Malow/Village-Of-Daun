package com.malow.villageofdaun.resources;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.AssetPaths;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.gfx.LibgdxRenderer;

public class Iron extends Resource
{
	private LibgdxRenderer gfx;
	
	public Iron(Vector3 pos)
	{
		this.gfx = Singletons.getGfx();
		mesh = gfx.createMesh(AssetPaths.IRONUNIT_MODEL);
		mesh.setPosition(pos);
	}
	
	@Override
	public void dispose()
	{
		gfx.deleteMesh(mesh);
	}
}
