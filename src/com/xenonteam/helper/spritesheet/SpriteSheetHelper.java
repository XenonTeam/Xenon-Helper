package com.xenonteam.helper.spritesheet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteSheetHelper
{

	public static String run(ArrayList<String> args)
	{

		if (args.size() < 1)
		{
			return "Not enough argument given!";
		}

		ArrayList<String> toGiveArgs = new ArrayList<String>(args);
		
		toGiveArgs.remove(0);
		
		String type = args.get(0);
		
		if(type.equalsIgnoreCase("cmd"))
		{
			return loadSimple(toGiveArgs);
		}
		else if(type.equalsIgnoreCase("gui"))
		{
			return loadGui(toGiveArgs);
		}
		
		return "\"" + type + "\" is not a valid argument!";
		
	}
	
	private static String loadGui(ArrayList<String> args)
	{
		
		
		return "none";
	}
	
	private static String loadSimple(ArrayList<String> args)
	{
		String outName = args.get(0);
		File spriteDir = new File(args.get(1));

		if (!spriteDir.exists())
		{
			return "Invalid path given!";
		}
		if (!spriteDir.isDirectory())
		{
			return "Path has to lead to a dirctory!";
		}

		File[] spriteFiles = spriteDir.listFiles(new FilenameFilter()
		{

			@Override
			public boolean accept(File file, String name)
			{
				if (name.endsWith(".png"))
					return true;

				return false;
			}

		});

		if (spriteFiles.length < 1)
		{
			return "No valid sprites found!";
		}

		BufferedImage[] sprites = new BufferedImage[spriteFiles.length];

		int maxHeight = 0;
		int width = 0;

		ArrayList<String> lines = new ArrayList<String>();

		for (int i = 0; i < spriteFiles.length; i++)
		{
			try
			{
				sprites[i] = ImageIO.read(spriteFiles[i]);
			} catch (IOException e)
			{
				return "Invalid image file found at: " + spriteFiles[i];
			}

			String line = "S:" + spriteFiles[i].getName().replaceAll(".png", "") + "," + width + ",0," + sprites[i].getWidth() + "," + sprites[i].getHeight();

			lines.add(line);

			width += sprites[i].getWidth();

			if (sprites[i].getHeight() > maxHeight)
			{
				maxHeight = sprites[i].getHeight();
			}

			System.out.println("Loaded sprite from: " + spriteFiles[i]);
		}

		BufferedImage out = new BufferedImage(width, maxHeight, BufferedImage.TYPE_INT_ARGB);

		Graphics gfx = out.getGraphics();

		width = 0;

		for (BufferedImage img : sprites)
		{
			gfx.drawImage(img, width, 0, null);

			width += img.getWidth();
		}

		File spritesOutFile = new File(spriteDir + "/" + outName + ".png.sprites");

		try
		{
			ImageIO.write(out, "PNG", new File(spriteDir + "/" + outName + ".png"));

			spritesOutFile.createNewFile();

			BufferedWriter writer = new BufferedWriter(new FileWriter(spritesOutFile));

			writer.write("0.0.2");
			writer.newLine();

			for (String s : lines)
			{
				writer.write(s);
				writer.newLine();
			}

			writer.close();

		} catch (IOException e)
		{
			return "Could not write sprites file!";
		}

		return "none";
	}

}
