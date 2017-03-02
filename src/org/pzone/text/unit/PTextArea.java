package org.pzone.text.unit;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.pzone.text.util.Function;

public class PTextArea extends JTextPane implements MouseListener,ActionListener,KeyListener
{
	private static final long serialVersionUID = -6557181475514913657L;
	JPopupMenu pop=null;
	JMenuItem paste;
	//Vector<JMenuItem> rightMenus=new Vector<JMenuItem>();
	StyledDocument model;
	public boolean isChanged=false;
	
		
	public PTextArea(StyledDocument model)
	{
		super(model);
		this.model=model;
		init();
	}
	private void init()
	{
		this.addMouseListener(this);
		this.addKeyListener(this);
		pop=new JPopupMenu();
		
		for(int i=0;i<Function.ListItemsColor.size();i++)
		{
			JMenuItem temp=new JMenuItem(Function.ListItemsName.get(i));
			temp.setForeground(Function.ListItemsColor.get(i));
			temp.addActionListener(this);
			temp.setActionCommand(Function.ListItemsName.get(i));
			pop.add(temp);
		}
		pop.add(paste=new JMenuItem("ճ��ͼƬ"));
		paste.addActionListener(this);
		
		this.add(pop);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		for(int i=0;i<Function.ListItemsName.size();i++)
		{
			final Color temp=Function.ListItemsColor.get(i);
			if(e.getActionCommand().equals(Function.ListItemsName.get(i)))
			{
				model.setCharacterAttributes(this.getSelectionStart(), this.getSelectionEnd()-this.getSelectionStart(),
						 new SimpleAttributeSet() {
						 {
						 addAttribute(StyleConstants.Foreground,
						 temp);
//						 addAttribute(StyleConstants.Bold,
//						 Boolean.FALSE);
						 }
						 }, true);
				isChanged=true;
				Function.undoIsChanged=true;
			}
		}
		if(e.getSource()==paste)
		{
			try
			{
				this.insertComponent(new PImage(CpImage.getImageFromClipboard(), this));
//				this.insertIcon(new ImageIcon(cpImage.getImageFromClipboard()));
				isChanged=true;
				Function.undoIsChanged=true;
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		//System.out.println(this.getCaretPosition());
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		
		if(e.getButton()==MouseEvent.BUTTON3)
		{
			try
			{
				if(CpImage.getImageFromClipboard()==null)
				{
					paste.setEnabled(false);
				}else {
					paste.setEnabled(true);
				}
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pop.show(this,e.getX(),e.getY());
		}
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		isChanged=true;
		Function.undoIsChanged=true;
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	
}
