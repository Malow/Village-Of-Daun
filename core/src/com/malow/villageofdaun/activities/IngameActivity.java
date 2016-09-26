package com.malow.villageofdaun.activities;

import com.badlogic.gdx.math.Vector3;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.buildings.BuildingManager;
import com.malow.villageofdaun.buildings.BuildingTypes;
import com.malow.villageofdaun.buildings.Storage;
import com.malow.villageofdaun.callback.Message;
import com.malow.villageofdaun.callback.Messages;
import com.malow.villageofdaun.callback.Messages.ReassignPeasant;
import com.malow.villageofdaun.gfx.Camera;
import com.malow.villageofdaun.gui.GUIView;
import com.malow.villageofdaun.gui.IngameGUI;
import com.malow.villageofdaun.peasant.PeasantManager;
import com.malow.villageofdaun.resources.ResourceManager;
import com.malow.villageofdaun.resources.Wood;
import com.malow.villageofdaun.world.World;

public class IngameActivity extends Activity
{
	private Camera cam;
	private GUIView gui;
	private BuildingManager builder;
	private World world;
	private PeasantManager peasants;
	private ResourceManager resources;
	
	private static final int TOUCH_NONE = 0;
	private static final int TOUCH_GUI = 1;
	private static final int TOUCH_BUILD = 2;
	private static final int TOUCH_CAMERA = 3;
	
	private int touchMode = TOUCH_NONE;
	private Vector3 origCamPos = new Vector3(250, 60, 250);
	
	private boolean buildingCollisionOnTouchStart = false;
	private float camTouchDownX = 0.0f;
	private float camTouchDownY = 0.0f;
	
	private ReassignPeasant.From lastMsg = null;
	
	public static float GAME_SPEED = 5.0f;
	
	private boolean doneLoading = false;
	
	@Override
	protected void createSpec()
	{
		cam = Singletons.getCam();
		gui = new IngameGUI();
		gui.create();
		world = new World();
		world.create();
		Singletons.world = world;
		builder = new BuildingManager();
		builder.create();
		Singletons.build = builder;
		peasants = new PeasantManager();
		peasants.create();
		resources = new ResourceManager();
		resources.create();
		Singletons.res = resources;
		
		
		Vector3 startingPos = world.getPossibleStartingPosition();
		Vector3 camPos = new Vector3(startingPos);
		cam.setPosition(camPos.add(new Vector3(-30, 60, 0)));
		cam.lookAt(startingPos);
		
		builder.build(BuildingTypes.HOUSE, new Vector3(startingPos));
		Storage sto = (Storage) builder.build(BuildingTypes.STORAGE, new Vector3(startingPos).add(new Vector3(0, 0, World.BLOCKSIZE)));
		
		Wood wood = new Wood(new Vector3(0, 0, 0));
		sto.addResource(wood);
		
		peasants.createPeasant(new Vector3(startingPos).add(new Vector3(2.5f, 0, 2.5f)));
		peasants.createPeasant(new Vector3(startingPos).add(new Vector3(7.5f, 0, 2.5f)));
		peasants.createPeasant(new Vector3(startingPos).add(new Vector3(2.5f, 0, 7.5f)));
		peasants.createPeasant(new Vector3(startingPos).add(new Vector3(7.5f, 0, 7.5f)));
		
		doneLoading = true;
	}

	@Override
	protected void disposeSpec()
	{
		gui.dispose();
		world.dispose();
		builder.dispose();
		peasants.dispose();
	}

	@Override
	protected void updateSpec(float diff, float touchX, float touchY)
	{
		if(!doneLoading)
			return;
		
		diff *= GAME_SPEED;
		
		Message msg = this.getNextMessage();
		while(msg != null)
		{
			if(msg instanceof Messages.StartBuilding)
			{
				int buildingType = ((Messages.StartBuilding)msg).buildingType;
				Vector3 pos = cam.get3DPickingRayGroundCollisionPoint(touchX, touchY);
				builder.startBuilding(buildingType, pos);
				touchMode = TOUCH_BUILD;
			}
			else if(msg instanceof Messages.ReassignPeasant)
			{
				if(msg instanceof Messages.ReassignPeasant.From)
				{
					this.lastMsg = (Messages.ReassignPeasant.From) msg;
				}
				else
				{
					if(this.lastMsg != null)
					{
						if(this.lastMsg.occupation == ((Messages.ReassignPeasant) msg).occupation)
						{
							this.lastMsg = null;
						}
						else
						{
							this.peasants.reassignPeasant(this.lastMsg.occupation, ((Messages.ReassignPeasant) msg).occupation);
							this.lastMsg = null;
						}
					}
				}
			}
			msg = this.getNextMessage();
		}
		
		builder.update(diff);
		world.update(diff);
		peasants.update(diff);
	}
	
	@Override
	protected void scrolled(int amount)
	{
		if(this.isTouching() && touchMode == TOUCH_CAMERA)
		{
			Vector3 camDir = cam.getDirection();
			origCamPos.sub(camDir.scl(amount * 2));
		}
		else
		{
			Vector3 camPos = cam.getPosition();
			Vector3 camDir = cam.getDirection();
			camPos.sub(camDir.scl(amount * 2));
			cam.setPosition(camPos);
		}		
	}

	@Override
	protected void touchDown(float touchX, float touchY)
	{		
		boolean guiAction = gui.cursorActionDown(touchX, touchY);
		if(guiAction)
		{
			touchMode = TOUCH_GUI;
			return;
		}
		
		Vector3 groundCollisionPosition = cam.get3DPickingRayGroundCollisionPoint(touchX, touchY);
		int buildCollision = builder.checkBuildingCollision(groundCollisionPosition.x, groundCollisionPosition.z);
		if(buildCollision != BuildingManager.NO_COLLISION)
		{
			if(buildCollision == BuildingManager.CURRENTLY_BUILDING_COLLISION)
			{
				buildingCollisionOnTouchStart = true;
				touchMode = TOUCH_BUILD;
			}
		}
		else
		{
			origCamPos = cam.getPosition();
			camTouchDownX = touchX;
			camTouchDownY = touchY;
			touchMode = TOUCH_CAMERA;
		}
	}

	@Override
	protected void touchContinued(float touchX, float touchY)
	{
		if(touchMode == TOUCH_CAMERA)
		{			
			Vector3 camPos = new Vector3(origCamPos);
			camPos.x += (touchY - camTouchDownY) * 0.05f;
			camPos.z -= (touchX - camTouchDownX) * 0.05f;
			cam.setPosition(camPos);
		}
		else if(touchMode == TOUCH_BUILD)
		{		
			Vector3 groundCollisionPosition = cam.get3DPickingRayGroundCollisionPoint(touchX, touchY);
			boolean movedBuilding = builder.updateBuildingPosition(groundCollisionPosition.x, groundCollisionPosition.z);
			if(movedBuilding)
			{
				buildingCollisionOnTouchStart = false;
			}
		}
	}
	
	@Override
	protected void touchUp(float touchX, float touchY)
	{
		if(touchMode == TOUCH_BUILD)
		{
			Vector3 groundCollisionPosition = cam.get3DPickingRayGroundCollisionPoint(touchX, touchY);
			int buildCollision = builder.checkBuildingCollision(groundCollisionPosition.x, groundCollisionPosition.z);
			if(buildingCollisionOnTouchStart && buildCollision == BuildingManager.CURRENTLY_BUILDING_COLLISION)
			{
				builder.buildCurrentBuilding();
			}
		}
		else if(touchMode == TOUCH_GUI)
		{
			boolean guiAction = gui.cursorActionUp(touchX, touchY);
			if(guiAction)
			{
				touchMode = TOUCH_GUI;
				return;
			}
		}
	}
}
