package org.pzone.text.unit;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.pzone.text.main.PText;
import org.pzone.text.util.Function;

public class PLabel extends JLabel implements MouseListener
{
	private static final long serialVersionUID = 2931900598834385066L;
	Component jp;
	PText mft;
	public PLabel(String str)
	{
		super(str);
		this.addMouseListener(this);
	}
	public void SetLabel(Component jp,PText mft)
	{
		this.jp=jp;
		this.mft=mft;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getButton()==MouseEvent.BUTTON1)
		{
			int closerTabNumber = mft.jtp.indexOfComponent(jp);
			if(mft.jta.get(closerTabNumber).isChanged)
			{
				int info=JOptionPane.showConfirmDialog(this, mft.farr.get(closerTabNumber).getName()+"的文本内容已改变，是否保存？","关闭保存确认", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				if(info==JOptionPane.YES_OPTION)
				{
					Function.saveText(mft.farr.get(closerTabNumber), mft,closerTabNumber);
					mft.num--;
					mft.farr.remove(closerTabNumber);
					mft.jtp.remove(closerTabNumber);
					mft.jta.remove(closerTabNumber);
					mft.jsp.remove(closerTabNumber);
					Function.undoal.remove(closerTabNumber);
				}
				if(info==JOptionPane.NO_OPTION)
				{
					mft.num--;
					mft.farr.remove(closerTabNumber);
					mft.jtp.remove(closerTabNumber);
					mft.jta.remove(closerTabNumber);
					mft.jsp.remove(closerTabNumber);
					Function.undoal.remove(closerTabNumber);
				}
			}
			else {
				mft.num--;
				mft.farr.remove(closerTabNumber);
				mft.jtp.remove(closerTabNumber);
				mft.jta.remove(closerTabNumber);
				mft.jsp.remove(closerTabNumber);
				Function.undoal.remove(closerTabNumber);
			}
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		this.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		this.setForeground(Color.black);
	}		
}
