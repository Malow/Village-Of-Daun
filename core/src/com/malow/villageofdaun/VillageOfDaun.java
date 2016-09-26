package com.malow.villageofdaun;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.activities.ActivityManager;
import com.malow.villageofdaun.gfx.Camera;
import com.malow.villageofdaun.gfx.LibgdxRenderer;
import com.malow.villageofdaun.gui.GUIScale;
import com.malow.villageofdaun.world.World;

public class VillageOfDaun implements ApplicationListener
{
	private LibgdxRenderer gfx;
	private Camera cam;
	private ActivityManager activityManager;
	
	private long lastTimeStamp = 0;
	
	public void create()
	{
		gfx = new LibgdxRenderer();
		gfx.create();
		Singletons.gfx = gfx;
		
		cam = gfx.camera;
		cam.setPosition(new Vector3(World.MAPSIZE / 2.0f - 30.0f, 60.0f, (World.MAPSIZE / 2.0f)));
		cam.lookAt(new Vector3(World.MAPSIZE / 2.0f, 0, World.MAPSIZE / 2.0f));
		Singletons.cam = cam;
		//cam.lookAt(cam.getPosition().add(new Vector3(-0.5f, -1.0f, 0.5f)));	// To see from sunlights POV
		
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		float scaleX = width / 1280.0f;
		float scaleY = height / 720.0f;
		GUIScale.ScaleX = scaleX;
		GUIScale.ScaleY = scaleY;
		
		activityManager = new ActivityManager();
		Singletons.activityManager = activityManager;

		activityManager.create();
		
		lastTimeStamp = System.nanoTime();
	}
	
	public void update(float diff)
	{
		boolean quit = activityManager.update(diff);
		if(quit)
		{
			Gdx.app.exit();
		}
	}
	
	@Override
	public void render()
	{
		long currentTimeStamp = System.nanoTime();
		float diff = (currentTimeStamp - lastTimeStamp) * 0.000000001f;	// Convert to seconds
		lastTimeStamp = currentTimeStamp;
		
		this.update(diff);
		gfx.render(diff);
	}

	@Override
	public void dispose()
	{
		activityManager.dispose();
		gfx.dispose();
	}
	
	
	

	
	
	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
		lastTimeStamp = System.nanoTime();
	}
}


/***
 * TODO:
 * Haxxfixed Pathfinding with Astar, current and target needs to be set to walkable for Astar to work. Find a good solution to Astar disregarding current and target's walkable flag. Might not always be desired however, maybe add a special flag for it.
 * 
 * Which of the tasks Dispose should take care of what cleanup? Currently DeliverResource takes care of cleaning of other tasks...
 * 
 * Allow user to reassign specific peasant by clicking on him.
 * 
 * Fix the mipmap of models, haxxfixed like fuck atm.
 * 
 * Make low res versions of all models for the phone, and high res for the computer. And have the different launch-projects send
 * 		in a setting for which ones to use, and then use that in assetpaths file or something.
 * 
 * ResourceHolder holds infinite amounts of resources, fix.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */

