package org.pzone.text.util;

import javax.swing.JFrame;

public class UpHide extends Thread
{
	static JFrame jf;
	static int x,y;
	boolean isHide=false;
	public UpHide(JFrame jf)
	{
		this.jf=jf;
	}
	static  JFrame getJF()
	{
		return jf;
	}
	static void setX(int xt)
	{
		x=xt;
	}
	static void setY(int yt)
	{
		y=yt;
	}	
	public void run()
	{
		
		while(true)
		{
			if(Function.isUpHide)
			{
			try
			{
				Thread.sleep(300);
			} catch (InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(jf.getExtendedState()!=JFrame.MAXIMIZED_BOTH && jf.getExtendedState()!=JFrame.ICONIFIED)
			{
			if(!isHide)
			{
//				System.out.println(jf.getX()+" "+jf.getY()+"\n"+x+"  "+y );
				if(jf.getY()<=0 && !(x>=jf.getX() && x<=jf.getX()+800 && y>=-5 && y<=600))
				{
					boolean yn=true;
					while(yn)
					{
			jf.setLocation(jf.getX(), jf.getY()-50);
			jf.setVisible(true);
			try
			{
				Thread.sleep(20);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(jf.getY()<=-595)
				{jf.setLocation(jf.getX(), -595);
				isHide=true;
				yn=false;}
				}
				}
			}else
			{
				if( x>=jf.getX() && x<=jf.getX()+800 && y<=0 )
				{
					boolean yn=true;
					while(yn)
					{
				jf.setLocation(jf.getX(), jf.getY()+50);
				jf.setVisible(true);
				try
				{
					Thread.sleep(20);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(jf.getY()>=0)
					{jf.setLocation(jf.getX(), 0);
					isHide=false;
					jf.setAlwaysOnTop(true);
					jf.setVisible(true);
					jf.setAlwaysOnTop(false);
					yn=false;}
					}
				}
			}
		}
			}
			else {
				try
				{
					Thread.sleep(5000);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	}
}