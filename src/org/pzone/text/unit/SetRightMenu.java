package org.pzone.text.unit;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.pzone.text.main.PText;
import org.pzone.text.util.Function;

public class SetRightMenu extends JDialog implements ActionListener
{
	private static final long serialVersionUID = -7382999743277400521L;
	DefaultListModel<String> dlm;
	JList<String> jl;
	JPanel leftPane,rightPane;
	JButton add,delete,change,upmove,downmove;
	JScrollPane jsp;
	PText mft;
    public SetRightMenu(PText owner, String title)
	{
    	super(owner,title);
    	this.mft=owner;
    	this.setLayout(new GridLayout(1, 2));
    	leftPane=new JPanel();
    	dlm=new DefaultListModel<String>();
    	jl=new JList<String>(dlm);
    	jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	jl.setCellRenderer(new CellRenderer());
//    	jl.ad
    	jsp=new JScrollPane(jl);
    	for(int i=0;i<Function.ListItemsName.size();i++)
    	{
    		dlm.addElement(Function.ListItemsName.get(i));
    	}
    	jsp.setBorder(BorderFactory.createTitledBorder("右键定制"));
    	jsp.setBounds(15, 35, 90, 150);
    	leftPane.setLayout(null);
    	leftPane.add(jsp);
    	this.add(leftPane);
    	
    	rightPane=new JPanel();
    	rightPane.setLayout(null);
    	//rightPane.setLayout(new GridLayout(5, 1));
    	add=new JButton("添加");
    	add.addActionListener(this);
    	add.setBounds(25, 15, 60, 30);
    	rightPane.add(add);
    	change=new JButton("修改");
    	change.addActionListener(this);
    	change.setBounds(25, 55, 60, 30);
    	rightPane.add(change);
    	delete=new JButton("删除");
    	delete.addActionListener(this);
    	delete.setBounds(25, 95, 60, 30);
    	rightPane.add(delete);
    	upmove=new JButton("上移");
    	upmove.addActionListener(this);
    	upmove.setBounds(25, 135, 60, 30);
    	rightPane.add(upmove);
    	downmove=new JButton("下移");
    	downmove.addActionListener(this);
    	downmove.setBounds(25, 175, 60, 30);
    	rightPane.add(downmove);
    	this.add(rightPane);
    	
    	this.addWindowListener(new WindowAdapter()
		{
    		public void windowClosing(WindowEvent e)
    		{
    			for(int j=0;j<mft.jta.size();j++)
    			{
    				JPopupMenu jpm=mft.jta.get(j).pop;
    				jpm.removeAll();
    				for(int i=0;i<Function.ListItemsColor.size();i++)
    				{
    					JMenuItem temp=new JMenuItem(Function.ListItemsName.get(i));
    					temp.setForeground(Function.ListItemsColor.get(i));
    					temp.addActionListener(mft.jta.get(j));
    					temp.setActionCommand(Function.ListItemsName.get(i));
    				    jpm.add(temp);
    				}
    				jpm.add(mft.jta.get(j).paste);
    			}
    		}
		});
    	this.setSize(250, 250);
    	this.setLocation(470, 250);
    	this.setResizable(false);
    	this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource()==add)
		{
			if(Function.ListItemsColor.size()<6)
			{
			AddMenu am=new AddMenu("", Color.white,dlm);
			}
			else {
				JOptionPane.showMessageDialog(this, "难道6666也满足不了你吗");
			}
		}else if(e.getSource()==change)
		{
			int index=jl.getSelectedIndex();
			if(index!=-1)
			{
			AddMenu am=new AddMenu(jl.getSelectedValue(), Function.ListItemsColor.get(index), dlm);
			}
			else {
				JOptionPane.showMessageDialog(this, "选一个吧少年");
			}
		}else if(e.getSource()==delete)
		{
			int index=jl.getSelectedIndex();
			if(index!=-1)
			{dlm.remove(index);
			Function.ListItemsColor.remove(index);
			Function.ListItemsName.remove(index);}
			else {
				JOptionPane.showMessageDialog(this, "选一个吧少年");
			}
		}else if(e.getSource()==upmove)
		{
			int index=jl.getSelectedIndex();
			if(index!=-1 && index!=0)
			{
				String temp=Function.ListItemsName.get(index);
				Color tempCo=Function.ListItemsColor.get(index);
				Function.ListItemsName.remove(index);
				Function.ListItemsName.add(index-1, temp);
				Function.ListItemsColor.remove(index);
				Function.ListItemsColor.add(index-1,tempCo);
				dlm.remove(index);
				dlm.add(index-1, temp);
				jl.setSelectedIndex(index-1);
			}
		}else if(e.getSource()==downmove)
		{
			int index=jl.getSelectedIndex();
			if(index!=-1 && index!=Function.ListItemsColor.size()-1)
			{
				String temp=Function.ListItemsName.get(index);
				Color tempCo=Function.ListItemsColor.get(index);
				Function.ListItemsName.remove(index);
				Function.ListItemsName.add(index+1, temp);
				Function.ListItemsColor.remove(index);
				Function.ListItemsColor.add(index+1,tempCo);
				dlm.remove(index);
				dlm.add(index+1, temp);
				jl.setSelectedIndex(index+1);
			}
		}
		
	}
}
