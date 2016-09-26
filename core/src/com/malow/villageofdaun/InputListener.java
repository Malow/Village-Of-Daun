package com.malow.villageofdaun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.malow.villageofdaun.callback.Callback;

public abstract class InputListener extends Callback
{
	private int scrolledAmount = 0;
	private boolean touching = false;	
	public boolean isTouching()
	{
		return touching;
	}
	
	public void create()
	{
		Gdx.input.setInputProcessor(new LibgdxListener());
		this.createSpec();
	}
	
	abstract protected void createSpec();
	
	public void dispose()
	{
		this.disposeSpec();
	}

	abstract protected void disposeSpec();

	public void update(float diff)
	{
		float touchX = Gdx.input.getX();
		float touchY = Gdx.input.getY();
		
		if(Gdx.input.justTouched())
		{
			this.touching = true;
			this.touchDown(touchX, touchY);
		}
		else if(Gdx.input.isTouched() && touching)
		{
			this.touchContinued(touchX, touchY);
		}
		else if(!Gdx.input.isTouched())
		{
			this.touching = false;
			this.touchUp(touchX, touchY);
		}		
		
		
		if(scrolledAmount != 0)
		{
			this.scrolled(scrolledAmount);
			scrolledAmount = 0;
		}
		
		this.updateSpec(diff, touchX, touchY);
	}
	
	abstract protected void updateSpec(float diff, float touchX, float touchY);
	
	abstract protected void touchDown(float touchX, float touchY);
	
	abstract protected void touchContinued(float touchX, float touchY);
	
	abstract protected void touchUp(float touchX, float touchY);
	
	abstract protected void scrolled(int amount);
	
	public Vector2 getTouch()
	{
		return new Vector2(Gdx.input.getX(), Gdx.input.getY());
	}
	
	
	/////////////////////////////////////////////////////////////////
	/////	Input Processor for callback in instant pushes		/////
	/////////////////////////////////////////////////////////////////
	private class LibgdxListener implements InputProcessor
	{
		@Override
		public boolean keyDown(int keycode)
		{
			return false;
		}

		@Override
		public boolean keyUp(int keycode)
		{
			return false;
		}

		@Override
		public boolean keyTyped(char character)
		{
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,	int button)
		{
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button)
		{
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer)
		{
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY)
		{
			return false;
		}

		@Override
		public boolean scrolled(int amount)
		{
			scrolledAmount += amount;
			return false;
		}
	}
}
