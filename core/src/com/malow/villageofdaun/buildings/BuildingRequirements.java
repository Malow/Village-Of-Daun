package com.malow.villageofdaun.buildings;

public class BuildingRequirements
{
	private static BuildingRequirements br = new BuildingRequirements();
	public class Recipe
	{
		int wood;
		int brick;
		int iron;
		int food;
		public Recipe(int w, int b, int i, int f)
		{
			wood = w;
			brick = b;
			iron = i;
			food = f;
		}
	}
	
	
	public static Recipe getRecipeFor(int buildingType)
	{
		Recipe r = null;
		if(buildingType == BuildingTypes.STORAGE)
		{
			r = br.new Recipe(2, 0, 0, 0);
		}
		else if(buildingType == BuildingTypes.BRICKWORKS)
		{
			r = br.new Recipe(4, 0, 0, 0);
		}
		else if(buildingType == BuildingTypes.IRONMINE)
		{
			r = br.new Recipe(4, 3, 0, 0);
		}
		else if(buildingType == BuildingTypes.HOUSE)
		{
			r = br.new Recipe(4, 4, 1, 0);
		}
		
		return r;
	}
}
