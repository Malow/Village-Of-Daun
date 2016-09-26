package com.malow.villageofdaun.gui;

import java.util.List;

import com.malow.villageofdaun.AssetPaths;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.buildings.BuildingTypes;
import com.malow.villageofdaun.callback.Messages;
import com.malow.villageofdaun.gfx.Image;
import com.malow.villageofdaun.peasant.PeasantStates;

public class IngameGUI extends GUIView
{
	private Image home;
	private Image divider1;
	private Image storage;
	private Image divider2;
	private Image brickworks;
	private Image divider3;
	private Image ironmine;
	private Image divider4;
	private Image house;
	
	private Image idle;
	private Image divider5;
	private Image building;
	private Image divider6;
	private Image wood;
	private Image divider7;
	private Image food;
	private Image divider8;
	private Image brick;
	private Image divider9;
	private Image iron;
	
	@Override
	public void createSpec()
	{
		home = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_HOME);
		home.setPosition(0, 645);
		divider1 = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_DIVIDER);
		divider1.setPosition(100, 660);
		storage = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_STORAGE);
		storage.setPosition(110, 660);
		divider2 = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_DIVIDER);
		divider2.setPosition(310, 660);
		brickworks = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_BRICKWORKS);
		brickworks.setPosition(320, 660);
		divider3 = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_DIVIDER);
		divider3.setPosition(520, 660);
		ironmine = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_IRONMINE);
		ironmine.setPosition(530, 660);
		divider4 = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_DIVIDER);
		divider4.setPosition(730, 660);
		house = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_HOUSE);
		house.setPosition(740, 660);
		
		idle = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_IDLE);
		idle.setPosition(0, 0);
		divider5 = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_DIVIDER);
		divider5.setPosition(200, 0);
		building = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_BUILDING);
		building.setPosition(210, 0);
		divider6 = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_DIVIDER);
		divider6.setPosition(410, 0);
		wood = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_WOOD);
		wood.setPosition(420, 0);
		divider7 = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_DIVIDER);
		divider7.setPosition(620, 0);
		food = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_FOOD);
		food.setPosition(630, 0);
		divider8 = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_DIVIDER);
		divider8.setPosition(830, 0);
		brick = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_BRICK);
		brick.setPosition(840, 0);
		divider9 = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_DIVIDER);
		divider9.setPosition(1040, 0);
		iron = Singletons.getGfx().createImage(AssetPaths.GUI_INGAME_IRON);
		iron.setPosition(1050, 0);
		
		this.addImageToCollisionChecks(home);
		this.addImageToCollisionChecks(storage);
		this.addImageToCollisionChecks(brickworks);
		this.addImageToCollisionChecks(ironmine);
		this.addImageToCollisionChecks(house);
		
		this.addImageToCollisionChecks(idle);
		this.addImageToCollisionChecks(building);
		this.addImageToCollisionChecks(wood);
		this.addImageToCollisionChecks(food);
		this.addImageToCollisionChecks(brick);
		this.addImageToCollisionChecks(iron);
	}
	
	@Override
	public void disposeSpec()
	{
		if(home != null)
		{
			Singletons.getGfx().deleteImage(home);
			home = null;
		}
		if(divider1 != null)
		{
			Singletons.getGfx().deleteImage(divider1);
			divider1 = null;
		}
		if(storage != null)
		{
			Singletons.getGfx().deleteImage(storage);
			storage = null;
		}
		if(divider2 != null)
		{
			Singletons.getGfx().deleteImage(divider2);
			divider2 = null;
		}
		if(brickworks != null)
		{
			Singletons.getGfx().deleteImage(brickworks);
			brickworks = null;
		}
		if(divider3 != null)
		{
			Singletons.getGfx().deleteImage(divider3);
			divider3 = null;
		}
		if(ironmine != null)
		{
			Singletons.getGfx().deleteImage(ironmine);
			ironmine = null;
		}
		if(divider4 != null)
		{
			Singletons.getGfx().deleteImage(divider4);
			divider4 = null;
		}
		if(house != null)
		{
			Singletons.getGfx().deleteImage(house);
			house = null;
		}
			
		if(idle != null)
		{
			Singletons.getGfx().deleteImage(idle);
			idle = null;
		}
		if(divider5 != null)
		{
			Singletons.getGfx().deleteImage(divider5);
			divider5 = null;
		}
		if(building != null)
		{
			Singletons.getGfx().deleteImage(building);
			building = null;
		}
		if(divider6 != null)
		{
			Singletons.getGfx().deleteImage(divider6);
			divider6 = null;
		}
		if(wood != null)
		{
			Singletons.getGfx().deleteImage(wood);
			wood = null;
		}
		if(divider7 != null)
		{
			Singletons.getGfx().deleteImage(divider7);
			divider7 = null;
		}
		if(food != null)
		{
			Singletons.getGfx().deleteImage(food);
			food = null;
		}
		if(divider8 != null)
		{
			Singletons.getGfx().deleteImage(divider8);
			divider8 = null;
		}
		if(brick != null)
		{
			Singletons.getGfx().deleteImage(brick);
			brick = null;
		}
		if(divider9 != null)
		{
			Singletons.getGfx().deleteImage(divider9);
			divider9 = null;
		}
		if(iron != null)
		{
			Singletons.getGfx().deleteImage(iron);
			iron = null;
		}
	}

	@Override
	public boolean cursorActionDown(float x, float y)
	{
		List<Image> imgs = this.getCursorCollisionImages(x, y);
		if(imgs.size() == 0)
		{
			return false;
		}
		else if(imgs.contains(home))
		{
			Singletons.getActvitiyManager().addMessage(Messages.GoToMainMenu());
			return true;
		}
		else if(imgs.contains(storage))
		{
			Singletons.getActvitiyManager().addMessage(Messages.StartBuilding(BuildingTypes.STORAGE));
			return true;
		}
		else if(imgs.contains(brickworks))
		{
			Singletons.getActvitiyManager().addMessage(Messages.StartBuilding(BuildingTypes.BRICKWORKS));
			return true;
		}
		else if(imgs.contains(ironmine))
		{
			Singletons.getActvitiyManager().addMessage(Messages.StartBuilding(BuildingTypes.IRONMINE));
			return true;
		}
		else if(imgs.contains(house))
		{
			Singletons.getActvitiyManager().addMessage(Messages.StartBuilding(BuildingTypes.HOUSE));
			return true;
		}
		else if(imgs.contains(idle))
		{
			Singletons.getActvitiyManager().addMessage(Messages.ReassignPeasantFrom(PeasantStates.IDLE));
			return true;
		}
		else if(imgs.contains(building))
		{
			Singletons.getActvitiyManager().addMessage(Messages.ReassignPeasantFrom(PeasantStates.CONSTRUCTING));
			return true;
		}
		else if(imgs.contains(wood))
		{
			Singletons.getActvitiyManager().addMessage(Messages.ReassignPeasantFrom(PeasantStates.GATHERING_WOOD));
			return true;
		}
		else if(imgs.contains(food))
		{
			Singletons.getActvitiyManager().addMessage(Messages.ReassignPeasantFrom(PeasantStates.GATHERING_FOOD));
			return true;
		}
		else if(imgs.contains(brick))
		{
			Singletons.getActvitiyManager().addMessage(Messages.ReassignPeasantFrom(PeasantStates.GATHERING_BRICK));
			return true;
		}
		else if(imgs.contains(iron))
		{
			Singletons.getActvitiyManager().addMessage(Messages.ReassignPeasantFrom(PeasantStates.GATHERING_IRON));
			return true;
		}
		return false;
	}

	@Override
	public boolean cursorActionUp(float x, float y)
	{
		List<Image> imgs = this.getCursorCollisionImages(x, y);
		if(imgs.size() == 0)
		{
			return false;
		}
		else if(imgs.contains(idle))
		{
			Singletons.getActvitiyManager().addMessage(Messages.ReassignPeasantTo(PeasantStates.IDLE));
			return true;
		}
		else if(imgs.contains(building))
		{
			Singletons.getActvitiyManager().addMessage(Messages.ReassignPeasantTo(PeasantStates.CONSTRUCTING));
			return true;
		}
		else if(imgs.contains(wood))
		{
			Singletons.getActvitiyManager().addMessage(Messages.ReassignPeasantTo(PeasantStates.GATHERING_WOOD));
			return true;
		}
		else if(imgs.contains(food))
		{
			Singletons.getActvitiyManager().addMessage(Messages.ReassignPeasantTo(PeasantStates.GATHERING_FOOD));
			return true;
		}
		else if(imgs.contains(brick))
		{
			Singletons.getActvitiyManager().addMessage(Messages.ReassignPeasantTo(PeasantStates.GATHERING_BRICK));
			return true;
		}
		else if(imgs.contains(iron))
		{
			Singletons.getActvitiyManager().addMessage(Messages.ReassignPeasantTo(PeasantStates.GATHERING_IRON));
			return true;
		}
		return false;
	}
}
