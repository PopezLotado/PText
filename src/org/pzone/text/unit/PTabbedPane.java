package org.pzone.text.unit;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.pzone.text.main.PText;

public class PTabbedPane extends JTabbedPane
{
	private static final long serialVersionUID = 3045048397899523127L;
	PText mft;
	public PTabbedPane()
	{
		super();
	}
    public void  passMFT(PText mft)
    {
    	this.mft=mft;
    }
	public Component add(String title,Component component)
	{
		JPanel tab = new JPanel();  
		tab.setOpaque(false); 
		JLabel tablabel = new JLabel(title);  
		PLabel tabCloseButton = new PLabel("X"); 
		tabCloseButton.SetLabel(component, mft);
		
		tab.add(tablabel,BorderLayout.WEST);  
		tab.add(tabCloseButton,BorderLayout.EAST);  

		super.add(component); 
		this.setTabComponentAt(this.getTabCount()-1, tab);
		return component;
	}
	
}