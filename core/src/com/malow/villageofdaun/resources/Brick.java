package com.malow.villageofdaun.resources;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.AssetPaths;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.gfx.LibgdxRenderer;

public class Brick extends Resource
{
	private LibgdxRenderer gfx;
	
	public Brick(Vector3 pos)
	{
		this.gfx = Singletons.getGfx();
		mesh = gfx.createMesh(AssetPaths.BRICKUNIT_MODEL);
		mesh.setPosition(pos);
	}
	
	@Override
	public void dispose()
	{
		gfx.deleteMesh(mesh);
	}
}
