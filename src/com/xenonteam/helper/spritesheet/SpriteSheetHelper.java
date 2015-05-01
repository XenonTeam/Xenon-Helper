package com.xenonteam.helper.spritesheet;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class SpriteSheetHelper
{
	
	
	
	public static String run(ArrayList<String> args)
	{
		
		if(args.size() > 1)
		{
			return "More then two arguments given!";
		}
		
		if(args.size() == 0)
		{
			return "No arguments given";
		}
		
		File spriteDir = new File(args.get(0));
		
		if(!spriteDir.exists())
		{
			return "Invalid path given!";
		}
		if(!spriteDir.isDirectory())
		{
			return "Path has to lead to a dirctory!";
		}
		
		File[] spriteFiles = spriteDir.listFiles(new FilenameFilter()
		{

			@Override
			public boolean accept(File file, String name)
			{
				if(name.endsWith(".png"))
					return true;
				
				return false;
			}
			
		});
		
		if(spriteFiles.length < 1)
		{
			return "No valid sprites found!";
		}
		
		
		
		return "none";
	}
	
}
