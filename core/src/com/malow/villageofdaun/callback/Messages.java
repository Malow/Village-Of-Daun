package com.malow.villageofdaun.callback;

import com.malow.villageofdaun.callback.Messages.ReassignPeasant.From;
import com.malow.villageofdaun.callback.Messages.ReassignPeasant.To;

public class Messages
{
	private static Messages msgs = new Messages();
	
	public class GoToMainMenu implements Message
	{
		
	}
	
	public static Message GoToMainMenu()
	{
		return msgs.new GoToMainMenu();
	}
	
	
	public static Message GoToIngame()
	{
		return msgs.new GoToIngame();
	}
	
	public class GoToIngame implements Message
	{
		
	}
	
	
	public class StartBuilding implements Message
	{
		public int buildingType;
		public StartBuilding(int buildingType)
		{
			this.buildingType = buildingType;
		}
	}
	
	public static Message StartBuilding(int buildingType)
	{
		return msgs.new StartBuilding(buildingType);
	}

	
	public class Quit implements Message
	{
		
	}
	
	public static Message Quit()
	{
		return msgs.new Quit();
	}
	

	public class ReassignPeasant implements Message
	{
		public class From extends ReassignPeasant
		{
			public From(int occupation)
			{
				super(occupation);
			}
		}
		public class To extends ReassignPeasant
		{
			public To(int occupation)
			{
				super(occupation);
			}
		}
		public int occupation;
		public ReassignPeasant(int occupation)
		{
			this.occupation = occupation;
		}
	}
	
	public static Message ReassignPeasantFrom(int occupation)
	{
		ReassignPeasant ra = msgs.new ReassignPeasant(occupation);
		From f = ra.new From(occupation);
		return f;
	}
	
	public static Message ReassignPeasantTo(int occupation)
	{
		ReassignPeasant ra = msgs.new ReassignPeasant(occupation);
		To t = ra.new To(occupation);
		return t;
	}
}
