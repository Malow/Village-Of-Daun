package com.malow.villageofdaun.gui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.malow.villageofdaun.gfx.Image;

public abstract class GUIView
{
	private List<Image> images;
	
	protected void addImageToCollisionChecks(Image img)
	{
		this.images.add(img);
	}
	
	public void create()
	{
		images = new ArrayList<Image>();
		this.createSpec();
	}
	
	protected abstract void createSpec();
	
	public void dispose()
	{
		images.clear();
		this.disposeSpec();
	}
	
	protected abstract void disposeSpec();
	
	public abstract boolean cursorActionDown(float x, float y);
	
	public abstract boolean cursorActionUp(float x, float y);
	
	protected List<Image> getCursorCollisionImages(float x, float y)
	{
		// invert Y cuz images and cursor are different ways....
		y = Gdx.graphics.getHeight() - y;
		List<Image> imgs = new ArrayList<Image>();
		for(Image img : images)
		{
			float imgX = img.getPosition().x;
			float imgY = img.getPosition().y;
			float imgW = img.getDimensions().x;
			float imgH = img.getDimensions().y;
			
			if(x > imgX && x < imgX + imgW && y > imgY && y < imgY + imgH)
			{
				imgs.add(img);
			}
		}
		
		return imgs;
	}
}
