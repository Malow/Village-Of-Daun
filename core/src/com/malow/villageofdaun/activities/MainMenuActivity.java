package com.malow.villageofdaun.activities;

import com.malow.villageofdaun.gui.GUIView;
import com.malow.villageofdaun.gui.MainMenuGUI;

public class MainMenuActivity extends Activity
{
	private GUIView gui;
	
	@Override
	protected void createSpec()
	{
		gui = new MainMenuGUI();
		gui.create();
	}

	@Override
	protected void disposeSpec()
	{
		gui.dispose();
	}

	@Override
	protected void updateSpec(float diff, float touchX, float touchY)
	{
		
	}

	@Override
	protected void touchDown(float touchX, float touchY)
	{
		gui.cursorActionDown(touchX, touchY);
	}

	@Override
	protected void touchContinued(float touchX, float touchY)
	{
		
	}

	@Override
	protected void touchUp(float touchX, float touchY)
	{
		
	}

	@Override
	protected void scrolled(int amount)
	{
		
	}

}
