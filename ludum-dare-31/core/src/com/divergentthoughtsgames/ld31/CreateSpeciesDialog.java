package com.divergentthoughtsgames.ld31;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CreateSpeciesDialog extends Sprite
{
	private TextureRegion[] textRegions;
	
	private static final int TypeAnimal = 0;
	private static final int TypePlant = 1;
	
	private static final int DietAnimals = 2;
	private static final int DietPlants = 3;
	private static final int DietLight = 4;
	
	private static final int LifeSpanHigh = 5;
	private static final int LifespanAverage = 6;
	private static final int LifespanLow = 7;
	
	public CreateSpeciesDialog()
	{
		super(Textures.createSpeciesDialog);
		setPosition(getWidth()/2, getHeight()/2);
		
		Texture t = Textures.createSpeciesDialogText;
		textRegions = new TextureRegion[8];
		textRegions[TypeAnimal] = new TextureRegion(t, 1, 7, 113, 28);
		textRegions[TypePlant] = new TextureRegion(t, 1, 39, 113, 28);
		textRegions[DietAnimals] = new TextureRegion(t, 1, 71, 133, 36);
		textRegions[DietPlants] = new TextureRegion(t, 1, 104, 133, 36);
		textRegions[DietLight] = new TextureRegion(t, 1, 139, 133, 36);
	}
	
	@Override
	public void draw(Batch batch)
	{
		super.draw(batch);
		
	}
	
	public void onClick(float x, float y)
	{
		if (getBoundingRectangle().contains(x, y))
		{
			Gdx.app.debug("Dialog onClick", "Dialog: " + getX() + "," + getY());
		}
	}
	
	public void onHover(float x, float y)
	{
		if (getBoundingRectangle().contains(x, y))
		{
			Gdx.app.debug("Dialog onHover", "Dialog: " + getX() + "," + getY());
		}
	}
}
