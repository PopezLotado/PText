package org.pzone.text.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;

public class SafeImage
{
	public static ImageIcon readFromFile(String filename)
	{
		try
		{
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			ObjectInputStream ois=new ObjectInputStream(classloader.getResourceAsStream(filename));
			ImageIcon ii=(ImageIcon) ois.readObject();
			ois.close();
			return ii;
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void writeToFile(ImageIcon image,String filename)
	{
		try
		{
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(image);
			oos.close();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
