package org.pzone.text.unit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import org.pzone.text.main.PText;
import org.pzone.text.util.Function;

public class SetProfile extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 200325210876598165L;
	JPanel jp1,jp2,jp3,jp4,jp5;
	JLabel jl1,jl2,jl3,jl4;
	JTextField jtf;
	JButton jb2,jb3,jb5;
	PText mft;
	JCheckBox jcb1;
	public SetProfile(PText mft)
	{
		this.mft=mft;
		this.setLayout(new GridLayout(5, 1));
		jp1=new JPanel();
		jl1=new JLabel("窗口标题：");
		jtf=new JTextField(7);
		jtf.setText(Function.mainTitle);
		jp1.add(jl1,BorderLayout.WEST);
		jp1.add(jtf,BorderLayout.EAST);
		this.add(jp1);
		
		jp2=new JPanel();
		jl2=new JLabel("前景色：");
		jl2.setForeground(Function.foreground);
		jb2=new JButton("选择颜色");
		jb2.addActionListener(this);
		jp2.add(jl2,BorderLayout.WEST);
		jp2.add(jb2,BorderLayout.EAST);
		this.add(jp2);
		
		jp3=new JPanel();
		jl3=new JLabel("背景色：");
		jl3.setForeground(Function.background);
		jb3=new JButton("选择颜色");
		jb3.addActionListener(this);
		jp3.add(jl3,BorderLayout.WEST);
		jp3.add(jb3,BorderLayout.EAST);
		this.add(jp3);
		
		jp4=new JPanel();
		jl4=new JLabel("靠顶是否自动上拉");
		jcb1=new JCheckBox();
		jcb1.setSelected(Function.isUpHide);
		jp4.add(jl4,BorderLayout.WEST);
		jp4.add(jcb1,BorderLayout.EAST);
		this.add(jp4);
		
		jp5=new JPanel();
		jb5=new JButton("          确定          ");
		jb5.setLayout(null);
		jb5.setBounds(50, 5, 60, 20);
		jp5.add(jb5,Alignment.CENTER);
		jb5.addActionListener(this);
		this.add(jp5);
		
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				int tempindex=mft.jtp.getSelectedIndex();
				if(tempindex!=-1)
				{mft.jta.get(tempindex).setBackground(Function.background);
				mft.jta.get(tempindex).setForeground(Function.foreground);}
			}
		});
		this.setTitle("设置");
		this.setResizable(false);
		this.setLocation(470,250);
		this.setSize(250, 210);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource()==jb2)
		{
			Color temp=JColorChooser.showDialog(this, "前景色选择", Function.foreground);
			jl2.setForeground(temp);
			int tempindex=mft.jtp.getSelectedIndex();
			mft.jta.get(tempindex).setForeground(temp);
		}else if(e.getSource()==jb3)
		{
			Color temp=JColorChooser.showDialog(this, "背景色选择", Function.background);
			jl3.setForeground(temp);
			int tempindex=mft.jtp.getSelectedIndex();
			mft.jta.get(tempindex).setBackground(temp);
		}
		else if(e.getSource()==jb5)
		{
			Function.mainTitle=jtf.getText();
			Function.foreground=jl2.getForeground();
			Function.background=jl3.getForeground();
			Function.isUpHide=jcb1.isSelected();
			mft.setTitle(Function.mainTitle);
			for(int i=0;i<mft.jta.size();i++)
			{
				mft.jta.get(i).setBackground(Function.background);
				mft.jta.get(i).setForeground(Function.foreground);
			}
			this.dispose();
		}
	}
}
