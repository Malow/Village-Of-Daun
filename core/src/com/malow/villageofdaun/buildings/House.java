package com.malow.villageofdaun.buildings;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.AssetPaths;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.gfx.LibgdxRenderer;
import com.malow.villageofdaun.gfx.Mesh;

public class House extends Building
{
	private LibgdxRenderer gfx;
	private Mesh mesh;
	
	public House(Vector3 pos)
	{
		this.gfx = Singletons.getGfx();
		mesh = gfx.createMesh(AssetPaths.HOUSE_MODEL);
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
