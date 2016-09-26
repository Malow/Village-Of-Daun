package com.malow.villageofdaun.activities;

import com.malow.villageofdaun.callback.Callback;
import com.malow.villageofdaun.callback.Message;
import com.malow.villageofdaun.callback.Messages;

public class ActivityManager extends Callback
{
	private Activity currentActivity;
	
	public void create()
	{
		switchToActivity(MainMenuActivity.class);
	}
	
	public boolean update(float diff)
	{
		Message msg = this.getNextMessage();
		while(msg != null)
		{
			if(msg instanceof Messages.GoToMainMenu)
			{
				switchToActivity(MainMenuActivity.class);
			}
			else if(msg instanceof Messages.GoToIngame)
			{
				switchToActivity(IngameActivity.class);
			}
			else if(msg instanceof Messages.StartBuilding)
			{
				currentActivity.addMessage(msg);
			}
			else if(msg instanceof Messages.ReassignPeasant)
			{
				currentActivity.addMessage(msg);
			}
			else if(msg instanceof Messages.Quit)
			{
				return true;
			}
			msg = this.getNextMessage();
		}
		currentActivity.update(diff);
		return false;
	}
	
	private void switchToActivity(Class<? extends Activity> clazz)
	{
		if(currentActivity != null)
		{
			currentActivity.dispose();
			currentActivity = null;
		}
		try
		{
			currentActivity = clazz.newInstance();
			currentActivity.create();
		}
		catch (Exception e)
		{}
	}
	
	public void dispose()
	{
		
	}
}
