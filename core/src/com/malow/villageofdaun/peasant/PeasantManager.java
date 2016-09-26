package com.malow.villageofdaun.peasant;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector3;

public class PeasantManager
{	
	private List<Peasant> allPeasants;
	private List<Peasant> idlePeasants;
	private List<Peasant> constructingPeasants;
	private List<Peasant> woodGatheringPeasants;
	private List<Peasant> foodGatheringPeasants;
	private List<Peasant> brickGatheringPeasants;
	private List<Peasant> ironGatheringPeasants;
	
	public PeasantManager()
	{
		
	}
	
	public void create()
	{
		allPeasants = new ArrayList<Peasant>();
		idlePeasants = new ArrayList<Peasant>();
		constructingPeasants = new ArrayList<Peasant>();
		woodGatheringPeasants = new ArrayList<Peasant>();
		foodGatheringPeasants = new ArrayList<Peasant>();
		brickGatheringPeasants = new ArrayList<Peasant>();
		ironGatheringPeasants = new ArrayList<Peasant>();
	}
	
	public void update(float diff)
	{
		for(Peasant p : allPeasants)
		{
			p.update(diff);
		}
	}
	
	public void reassignPeasant(int fromPeasantState, int toPeasantState)
	{
		if(fromPeasantState == toPeasantState)
		{
			return;
		}
		
		Peasant p = null;
		if(fromPeasantState == PeasantStates.IDLE && idlePeasants.size() > 0)
		{
			p = idlePeasants.get(0);
			idlePeasants.remove(0);
		}
		else if(fromPeasantState == PeasantStates.CONSTRUCTING && constructingPeasants.size() > 0)
		{
			p = constructingPeasants.get(0);
			constructingPeasants.remove(0);
		}
		else if(fromPeasantState == PeasantStates.GATHERING_WOOD && woodGatheringPeasants.size() > 0)
		{
			p = woodGatheringPeasants.get(0);
			woodGatheringPeasants.remove(0);
		}
		else if(fromPeasantState == PeasantStates.GATHERING_FOOD && foodGatheringPeasants.size() > 0)
		{
			p = foodGatheringPeasants.get(0);
			foodGatheringPeasants.remove(0);
		}
		else if(fromPeasantState == PeasantStates.GATHERING_BRICK && brickGatheringPeasants.size() > 0)
		{
			p = brickGatheringPeasants.get(0);
			brickGatheringPeasants.remove(0);
		}
		else if(fromPeasantState == PeasantStates.GATHERING_IRON && ironGatheringPeasants.size() > 0)
		{
			p = ironGatheringPeasants.get(0);
			ironGatheringPeasants.remove(0);
		}
		
		if(p == null)
		{
			return;
		}
		
		if(toPeasantState == PeasantStates.IDLE)
		{
			idlePeasants.add(p);
		}
		else if(toPeasantState == PeasantStates.CONSTRUCTING)
		{
			constructingPeasants.add(p);
		}
		else if(toPeasantState == PeasantStates.GATHERING_WOOD)
		{
			woodGatheringPeasants.add(p);
		}
		else if(toPeasantState == PeasantStates.GATHERING_FOOD)
		{
			foodGatheringPeasants.add(p);
		}
		else if(toPeasantState == PeasantStates.GATHERING_BRICK)
		{
			brickGatheringPeasants.add(p);
		}
		else if(toPeasantState == PeasantStates.GATHERING_IRON)
		{
			ironGatheringPeasants.add(p);
		}

		p.setState(toPeasantState);
	}
	
	public void dispose()
	{
		for(Peasant p : allPeasants)
		{
			p.dispose();
		}
		allPeasants.clear();
	}
	
	public void createPeasant(Vector3 pos)
	{
		Peasant p = new Peasant(pos);
		idlePeasants.add(p);
		allPeasants.add(p);
	}
}
