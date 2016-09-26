package com.malow.villageofdaun.world;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.AssetPaths;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.gfx.LibgdxRenderer;
import com.malow.villageofdaun.gfx.Mesh;

public class Ironvein extends WorldEntity
{
	private LibgdxRenderer gfx;
	private Mesh mesh;
	
	public Ironvein(Vector3 pos)
	{
		this.gfx = Singletons.getGfx();
		mesh = gfx.createMesh(AssetPaths.IRON_MODEL);
		mesh.setPosition(pos);
	}
	
	@Override
	public void dispose()
	{
		gfx.deleteMesh(mesh);
	}

	@Override
	public Vector3 getPosition()
	{
		return mesh.getPosition();
	}
}
