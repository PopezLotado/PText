package org.pzone.text.util;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.pzone.text.unit.PTextArea;

public class FindAndReplace extends JFrame implements ActionListener
{
	JPanel jp1,jp2,jp3;
	JButton jb1,jb2,jb3;
	JLabel jl1,jl2;
	JTextField jtf1,jtf2;
	PTextArea mta;
	public PTextArea getMta()
	{
		return mta;
	}
	public void setMta(PTextArea mta)
	{
		this.mta = mta;
	}
	public FindAndReplace(int x ,int y)
	{
		this.setLayout(new GridLayout(3,1));
		jp1=new JPanel();
		jl1=new JLabel("查找内容：");
		jtf1=new JTextField(10);
		jb1=new JButton("查找下一个");
		jb1.addActionListener(this);
		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jb1);
		this.add(jp1);
		
		jp2=new JPanel();
		jl2=new JLabel("替换内容：");
		jtf2=new JTextField(10);
		jb2=new JButton("      替换       ");
		jb2.addActionListener(this);
		jp2.add(jl2);
		jp2.add(jtf2);
		jp2.add(jb2);
		this.add(jp2);
		
		jp3=new JPanel();
		jb3=new JButton("替换全部");
		jb3.addActionListener(this);
		jp3.add(jb3);
		this.add(jp3);
		
		this.setTitle("棒棒哒查找与替换");
		this.setAlwaysOnTop(true);		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(330, 150);
		this.setLocation(x,y);
		this.setResizable(false);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jb1)  //查找
		{
			Function.yn=true;
			if(!jtf1.getText().isEmpty())
			   Function.FindNext(mta, jtf1.getText(),this);
			else {
				JOptionPane.showMessageDialog(this, "查找的内容不能为空呢~");
			}
		}else if(e.getSource()==jb2)  //替换
		{
			Function.yn=true;
			if((!jtf1.getText().isEmpty()) && (!jtf2.getText().isEmpty()))
			Function.ReplaceText(mta, jtf1.getText(), jtf2.getText(), this);
			else
				JOptionPane.showMessageDialog(this, "查找和替换都必须有内容呢呢呢~");
		}
		else if(e.getSource()==jb3)  //替换全部
		{
			Function.yn=false;
			if((!jtf1.getText().isEmpty()) && (!jtf2.getText().isEmpty()))
				Function.ReplaceAllText(mta, jtf1.getText(), jtf2.getText(), this);
				else
					JOptionPane.showMessageDialog(this, "查找和替换都必须有内容呢呢呢~");
		}
	}
}


