package org.pzone.text.unit;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.pzone.text.util.Function;

public class AddMenu extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7668903950508471448L;
	JLabel jl1,jl2;
	JTextField jtf;
	JButton jb1,jb2; 
	DefaultListModel<String> dlm;
    public AddMenu(String name,Color color,DefaultListModel<String> dlm)   //0添加  1修改
    {
    	this.dlm=dlm;
    	jl1=new JLabel("名称：");
    	jtf=new JTextField(8);
    	jtf.setText(name);
    	jl2=new JLabel("颜色：");
    	jb1=new JButton("选择颜色");
    	jb1.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Color temp=JColorChooser.showDialog(AddMenu.this, "尼玛快选", color);
				if(temp!=null)
					jl2.setForeground(temp);
			}
		});
    	if(name.equals(""))
    		this.setTitle("添加");
    	else
    		{this.setTitle("修改");
    		jl2.setForeground(color);}
    	jb2=new JButton("确定");
    	jb2.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				if(name.equals(""))
				{
					if(Function.ListItemsName.contains(jtf.getText()))
						JOptionPane.showMessageDialog(AddMenu.this, "每个颜色都想要个不一样的名字呢");
					else{
				Function.ListItemsName.add(jtf.getText());
				Function.ListItemsColor.add(jl2.getForeground());
				dlm.addElement(jtf.getText());
					AddMenu.this.dispose();}
				
				}else {
					int index=Function.ListItemsName.indexOf(name);
					boolean isContinue=true;
					for(int i=0;i<Function.ListItemsName.size();i++)
					{
						if(i==index)
							continue;
						if(Function.ListItemsName.get(i).equals(jtf.getText()))
						{
							JOptionPane.showMessageDialog(AddMenu.this, "每个颜色都想要个不一样的名字呢");
							isContinue=false;
							break;
						}
					}
					if(isContinue)
					{
					Function.ListItemsName.set(index, jtf.getText());
					Function.ListItemsColor.set(index, jl2.getForeground());
					dlm.set(index, jtf.getText());	
					AddMenu.this.dispose();}
				}
				
			}
		});
    	this.setLayout(new FlowLayout());
    	
    	this.add(jl1);
    	this.add(jtf);
    	this.add(jl2);
    	this.add(jb1);
    	this.add(jb2);
    	
    	this.setAlwaysOnTop(true);
    	this.setLocation(470, 350);
    	this.setSize(360, 75);
    	this.setResizable(false);
    	this.setVisible(true);
    }
}
