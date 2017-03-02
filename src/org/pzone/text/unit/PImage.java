package org.pzone.text.unit;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.StyleConstants;

public class PImage extends JLabel implements MouseListener,ActionListener
{
	
	private static final long serialVersionUID = 967262245884437113L;
	public JPopupMenu jpm;
	public JMenuItem copy,cut;
    public ImageIcon icon;
    public PTextArea mt;
    public PImage()
    {
    	super();
    }
	public PImage(String str,PTextArea mt)
	{
		// TODO Auto-generated constructor stub
		super();
		this.setOpaque(true);
		icon=new ImageIcon(str);
		this.setIcon(icon);
		jpm=new JPopupMenu();
		this.mt=mt;
		jpm.add(copy=new JMenuItem("复制图片"));
		jpm.add(cut=new JMenuItem("剪切图片"));
		cut.addActionListener(this);
		copy.addActionListener(this);
		this.addMouseListener(this);
	}
	public PImage(Image image,PTextArea mt)
	{
		// TODO Auto-generated constructor stub
		super();
		this.setOpaque(false);
		icon=new ImageIcon(image);
		this.setIcon(icon);
		jpm=new JPopupMenu();
		this.mt=mt;
		jpm.add(copy=new JMenuItem("复制图片"));
		jpm.add(cut=new JMenuItem("剪切图片"));
		cut.addActionListener(this);
		copy.addActionListener(this);
		this.addMouseListener(this);
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
		if(e.getButton()==MouseEvent.BUTTON3)
		{  
			jpm.show(this, e.getX(), e.getY());
		}
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
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource()==copy)
		{
			CpImage.setClipboardImage2(icon.getImage());
		}else if(e.getSource()==cut)
		{
			CpImage.setClipboardImage2(icon.getImage());
			for(int i=0;i<mt.getText().replaceAll("\r\n", "\n").length();i++) {  
//              System.out.println(mt.getStyledDocument().getCharacterElement(i).getName());
              if(mt.getStyledDocument().getCharacterElement(i).getName().equals("component"))
              {
              	PImage mi=(PImage)StyleConstants.getComponent((mt.getStyledDocument().getCharacterElement(i).getAttributes()));
              	if(mi==this)
              	{
              	mt.setSelectionStart(i);
              	mt.setSelectionEnd(i+1);
              	mt.replaceSelection("");
              	}

              }    
          }  
			
			//mt.validate();
			//mt.setVisible(true);
		}
	}
	
}
