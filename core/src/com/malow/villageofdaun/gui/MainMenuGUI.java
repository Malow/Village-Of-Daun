package com.malow.villageofdaun.gui;

import java.util.List;

import com.malow.villageofdaun.AssetPaths;
import com.malow.villageofdaun.Singletons;
import com.malow.villageofdaun.callback.Messages;
import com.malow.villageofdaun.gfx.Image;

public class MainMenuGUI extends GUIView
{
	private Image background;
	private Image newgame;
	private Image loadgame;
	private Image options;
	private Image quit;
	
	@Override
	public void createSpec()
	{
		background = Singletons.getGfx().createImage(AssetPaths.GUI_MENU_BG);
		newgame = Singletons.getGfx().createImage(AssetPaths.GUI_MENU_NEWGAME);
		newgame.setPosition(500, 400);
		loadgame = Singletons.getGfx().createImage(AssetPaths.GUI_MENU_LOADGAME);
		loadgame.setPosition(500, 300);
		options = Singletons.getGfx().createImage(AssetPaths.GUI_MENU_OPTIONS);
		options.setPosition(500, 200);
		quit = Singletons.getGfx().createImage(AssetPaths.GUI_MENU_QUIT);
		quit.setPosition(500, 100);
		
		this.addImageToCollisionChecks(newgame);
		this.addImageToCollisionChecks(loadgame);
		this.addImageToCollisionChecks(options);
		this.addImageToCollisionChecks(quit);
	}
	
	@Override
	public void disposeSpec()
	{
		if(background != null)
		{
			Singletons.getGfx().deleteImage(background);
			background = null;
		}
		
		if(newgame != null)
		{
			Singletons.getGfx().deleteImage(newgame);
			newgame = null;
		}
		
		if(loadgame != null)
		{
			Singletons.getGfx().deleteImage(loadgame);
			loadgame = null;
		}
		
		if(options != null)
		{
			Singletons.getGfx().deleteImage(options);
			options = null;
		}
		
		if(quit != null)
		{
			Singletons.getGfx().deleteImage(quit);
			quit = null;
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
		else if(imgs.contains(newgame))
		{
			Singletons.getActvitiyManager().addMessage(Messages.GoToIngame());
			return true;
		}
		else if(imgs.contains(quit))
		{
			Singletons.getActvitiyManager().addMessage(Messages.Quit());
			return true;
		}
		
		return false;
	}

	@Override
	public boolean cursorActionUp(float x, float y)
	{
		return false;
	}
}
