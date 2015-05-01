package com.xenonteam.helper;

import java.util.ArrayList;
import java.util.Arrays;

import com.xenonteam.helper.spritesheet.SpriteSheetHelper;

public class XenonHelper
{

	public static void main(String[] args)
	{
		if(!(args.length >= 1))
		{
			System.out.println("No arguments given!");
			return;
		}
		
		String helper = args[0];
		
		ArrayList<String> toGiveArgs = new ArrayList<String>(Arrays.asList(args));
		toGiveArgs.remove(0);
		
		String error = "none";
		
		if(helper.equalsIgnoreCase("sprite") || helper.equalsIgnoreCase("spritesheet"))
		{
			error = SpriteSheetHelper.run(toGiveArgs);
		}
		else
		{
			System.out.println("No helpers with the name \"" + helper + "\" found!");
			return;
		}
		
		if(!error.equalsIgnoreCase("none"))
		{
			System.out.println("The helper " + helper + " failed to execute!");
			System.out.println("Error: " + error);
			return;
		}
		else
		{
			System.out.println("The helper " + helper + " finished executing!");
		}
		
	}

}
