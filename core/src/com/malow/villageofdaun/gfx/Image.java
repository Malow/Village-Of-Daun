package com.malow.villageofdaun.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.malow.villageofdaun.gui.GUIScale;

public class Image
{
	public Texture texture;
	public Sprite sprite;
	
	public Image(Texture texture, Sprite sprite)
	{
		this.texture = texture;
		this.sprite = sprite;
	}
	
	public void dispose()
	{
		if(this.texture != null)
		{
			this.texture.dispose();
			this.texture = null;
		}
	}
	
	public void setPosition(Vector2 pos)
	{
		this.setPosition(pos.x, pos.y);
	}
	
	public void setPosition(float x, float y)
	{
		sprite.setPosition(x * GUIScale.ScaleX, y * GUIScale.ScaleY);
	}
	
	public Vector2 getPosition()
	{
		return new Vector2(sprite.getX(), sprite.getY());
	}
	
	public Vector2 getDimensions()
	{
		return new Vector2(sprite.getWidth(), sprite.getHeight());
	}
}
