package org.pzone.text.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.text.StyleConstants;
import org.pzone.text.main.PText;
import org.pzone.text.unit.PImage;
import org.pzone.text.unit.PTextArea;

public class Function
{
	public static ArrayList<UndoClass> undoal=new ArrayList<UndoClass>();
	public static boolean yn=true;   //设置标志位控制找不哒更多了呢~的显示
	public static boolean undoIsChanged=false;
	public static boolean canSaveEdit=true;
	public static String mainTitle="测试";
	public static Color foreground=Color.white;
	public static Color background=Color.black;
	public static String FileChooserPath;
	public static String FilesNames;
	public static boolean isUpHide=true;
	public static Vector<String> ListItemsName=new Vector<String>();
	public static Vector<Color> ListItemsColor=new Vector<Color>();
	
	public	static void FindNext(PTextArea mta,String toFind,FindAndReplace far)
	{
		String temp=mta.getText();
		mta.requestFocus();   //获取焦点
		int tempos=mta.getCaretPosition();
		temp=temp.replaceAll("\r\n", "\n");
		if(tempos==temp.length())
		{
			JOptionPane.showMessageDialog(far, "已到达文件尾，将从头开始查找！");
			tempos=0;
		}
		
		int start=temp.indexOf(toFind, tempos);   //起始Pos
		if(start==-1)
		{
			if(yn)
			   JOptionPane.showMessageDialog(far, "找不到更多了~");
			mta.setCaretPosition(temp.length());
		}
		else
		{mta.setSelectionStart(start);
		mta.setSelectionEnd(start+toFind.length());}
		//
		mta.repaint();
	}
	public static boolean ReplaceText(PTextArea mta,String toFind,String toReplace,FindAndReplace far)
	{
		mta.requestFocus();
		String temp=mta.getSelectedText();
		if(temp==null)
		{
			FindNext(mta, toFind, far);
			temp=mta.getSelectedText();
		}		
		if(!(temp==null))
		  {mta.replaceSelection(toReplace);
		 // mta.setVisible(true);
		  return true;}
		return false;
	}
	
	public static void ReplaceAllText(PTextArea mta,String toFind,String toReplace,FindAndReplace far)
	{
		String temp=mta.getText();
		temp=temp.replaceAll("\r\n", "\n");
		mta.requestFocus();
		int count=0;
		if(ReplaceText(mta, toFind, toReplace, far))
		     count++;
		while(mta.getCaretPosition()!=temp.length())
		{
			if(ReplaceText(mta, toFind, toReplace, far))
			     count++;
		}
		//mta.setVisible(true);
		JOptionPane.showMessageDialog(far, "共完成"+count+"次替换呢~");
		
	}
	public static void undoText(int index,PTextArea mta)
	{
		
		if(Function.undoal.get(index).canUndo())
			{Function.undoal.get(index).myUndo();
			mta.isChanged=true;}
		
		
//		int size=undoal.get(index).astr.size();
//		
//		
//		if(size>=2)
//		{
//			if(!Function.undoIsChanged)
//				{jt.setStyledDocument(undoal.get(index).astr.get(size-2));
//				jt.setVisible(true);
//				System.out.println("qk1");
//				undoal.get(index).astr.remove(size-1);}
//			else
//			{jt.setDocument(undoal.get(index).astr.get(size-1));
//			jt.setVisible(true);
//			System.out.println("qk2");
//			Function.undoIsChanged=false;
//			}
//		}
	}

	public static void saveText(File f,PText mft,int index)
	{
		PTextArea mta=mft.jta.get(index);
		String path=f.getAbsolutePath();
		boolean isContinue=true;
		if(path.endsWith(".txt"))
		{
			path=path.substring(0, path.lastIndexOf("txt"))+"ptxt";
			File fs[]=f.getParentFile().listFiles();
			for (int i=0;i<fs.length;i++)
				if(path.equals(fs[i].getAbsolutePath()))
				{
					int temp=JOptionPane.showConfirmDialog(mta, "报告老板！在为您将txt转换为ptxt时在同目录下发现一只同名文件，是否篡位？", "万全之策", JOptionPane.YES_NO_OPTION);
					if(temp==JOptionPane.YES_OPTION)
						isContinue=true;
					else
						isContinue=false;
					break;
				}
		}			
        if(isContinue)
			{
        	int tempSavePostion=mta.getCaretPosition();
        	ArrayList<Integer> pos=new ArrayList<Integer>();
        	ArrayList<String>  loc=new ArrayList<String>();
        	String savePath="ImageSrs/"+f.getName().substring(0,f.getName().lastIndexOf(".ptxt"));
        	File fw=new File(savePath);
        	String tempsave="";
        	if(fw.exists())
        	{
        	    File  tempFiles[]=fw.listFiles();
        	    for(int i=0;i<tempFiles.length;i++)
        	    {
        	    	tempFiles[i].delete();
        	    }
        	    fw.delete();}
        	fw.mkdir();
        	
        	try
			{
				FileOutputStream fos=new FileOutputStream(savePath+"/record.prd");
				
			
        	for(int i=0;i<mta.getText().replaceAll("\r\n", "\n").length();i++) {  
//                System.out.println(mta.getStyledDocument().getCharacterElement(i).getName());
                if(mta.getStyledDocument().getCharacterElement(i).getName().equals("component"))
                {
                	int rand=(int)(Math.random()*100);
                	PImage mi=(PImage)StyleConstants.getComponent((mta.getStyledDocument().getCharacterElement(i).getAttributes()));
                	Image tempImage=mi.icon.getImage();
                	BufferedImage bi = new BufferedImage(tempImage.getWidth(null),tempImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
                	java.awt.Graphics g = bi.getGraphics();
                	g.drawImage(tempImage, 0, 0, null);
                	ImageIO.write(bi, "jpg", new File(savePath+"/"+pos.size()+"-"+rand+".jpg"));
                	mta.setSelectionStart(i);
                	mta.setSelectionEnd(i+1);
                	mta.replaceSelection("");
                	loc.add(savePath+"/"+pos.size()+"-"+rand+".jpg");
                	pos.add(i);
                }    
            }  
        	for(int i=0;i<pos.size();i++)
        	{
        		fos.write((pos.get(i)+","+loc.get(i)+"\r\n").getBytes());
        		tempsave=tempsave+pos.get(i)+","+loc.get(i)+"\n";
        	}
        	fos.close();
        	
			
        	WriteOrReadRecordFile worrf=new WriteOrReadRecordFile(mta, path);
			worrf.writeToFile(index);
			
			Scanner strscan=new Scanner(tempsave);
			
			int count=0;
			System.gc();
			while(strscan.hasNext())
			{
			mta.requestFocus();
			String tempstr[]=strscan.nextLine().split(",");
			mta.setCaretPosition(Integer.parseInt(tempstr[0])+count);
//			System.out.println(tempstr[0]);
			mta.insertComponent(new PImage(tempstr[1],mta));
//			System.out.println(tempstr[1]);
			count++;
			}
			strscan.close();
			
			PText.echo=1;
			mta.isChanged=false;
			mta.setCaretPosition(tempSavePostion);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}
}
