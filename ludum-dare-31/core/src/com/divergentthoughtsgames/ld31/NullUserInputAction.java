package com.divergentthoughtsgames.ld31;

public class NullUserInputAction implements UserInputAction
{
	private static NullUserInputAction instance = new NullUserInputAction();

	public static NullUserInputAction getInstance()
	{
		return instance;
	}
	
	private NullUserInputAction()
	{
	}
	
	@Override
	public void onEvent(float x, float y)
	{	
	}
}
