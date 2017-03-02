package org.pzone.text.main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.DefaultStyledDocument;
import org.pzone.text.unit.PTabbedPane;
import org.pzone.text.unit.SetProfile;
import org.pzone.text.unit.SetRightMenu;
import org.pzone.text.unit.PImage;
import org.pzone.text.unit.PTextArea;
import org.pzone.text.util.FindAndReplace;
import org.pzone.text.util.Function;
import org.pzone.text.util.MouseHook;
import org.pzone.text.util.SafeImage;
import org.pzone.text.util.WriteOrReadRecordFile;
import org.pzone.text.util.UndoClass;
import org.pzone.text.util.UpHide;

import java.util.Properties;

import java.io.*;

public class PText extends JFrame implements ActionListener, Runnable,
		WindowListener
{

	private static final long serialVersionUID = 4546453608391709589L;
	JMenuBar jmb;
	JMenu jm1, jm2, jm3,jm4;
	JMenu jmi0;
	JMenuItem jmi01, jmi02;
	JMenuItem jmi1, jmi2, jmi3, jmi4, jmi5, jmi6, jmi7;
	JMenuItem jmi20,jmi21,jmi22;
	JMenuItem jmi30,jmi31,jmi40;
	JToolBar jtb;
	JButton jb1, jb2, jb3,jb4,jb5;
	JPanel jp;
	JLabel jl;
	public int num = 0;
	public static int echo = 0;

	public PTabbedPane jtp;
	public ArrayList<PTextArea> jta = new ArrayList<PTextArea>();
	public ArrayList<JScrollPane> jsp = new ArrayList<JScrollPane>();
	public ArrayList<File> farr = new ArrayList<File>();

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		PText test = new PText();
		Thread t = new Thread(test);
		t.start();
	}

	public PText()
	{

		jmb = new JMenuBar();
		jm1 = new JMenu("文件(F)");
		jm1.setMnemonic('F');
		// jm1.setForeground(Color.white);
		jm2 = new JMenu("编辑(E)");
		jm2.setMnemonic('E');
		// jm2.setForeground(Color.white);
		jm3 = new JMenu("格式(O)");
		jm3.setMnemonic('O');
		jm4=new JMenu("帮助(H)");
		jm4.setMnemonic('H');
		// jm3.setForeground(Color.white);
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
		jmi0 = new JMenu("新建");
		jmi01 = new JMenuItem("文件");
		jmi01.addActionListener(this);
		jmi02 = new JMenuItem("工程");
		jmi0.add(jmi01);
		jmi0.add(jmi02);
		jm1.add(jmi0);
		jmi1 = new JMenuItem("打开");
		jmi1.addActionListener(this);
		jmi2 = new JMenuItem("保存");
		jmi2.addActionListener(this);
		jmi3 = new JMenuItem("另存为");
		jmi3.addActionListener(this);
		jmi4 = new JMenuItem("退出");
		jmi4.addActionListener(this);
		jmi5 = new JMenuItem("关闭当前");
		jmi5.addActionListener(this);
		jmi6 = new JMenuItem("关闭所有");
		jmi6.addActionListener(this);
		jmi30 = new JMenuItem("设置");
		jmi30.addActionListener(this);
		jmi31 = new JMenuItem("右键菜单定制");
		jmi31.addActionListener(this);
		jmi7 = new JMenuItem("删除当前");
		jmi7.addActionListener(this);
		jmi20=new JMenuItem("撤销");
		jmi20.addActionListener(this);
		jmi21=new JMenuItem("查找与替换");
		jmi21.addActionListener(this);
		jmi22=new JMenuItem("插入图片");
		jmi22.addActionListener(this);
		jmi40=new JMenuItem("关于");
		jmi40.addActionListener(this);
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.addSeparator();
		jm1.add(jmi5);
		jm1.add(jmi6);
		jm1.addSeparator();
		jm1.add(jmi7);
		jm1.add(jmi4);
		jm2.add(jmi20);
		jm2.add(jmi21);
		jm2.add(jmi22);
		jm3.add(jmi30);
		jm3.add(jmi31);
		jm4.add(jmi40);
		// jmb.setBackground(Color.black);
		this.setJMenuBar(jmb);

		jtb = new JToolBar();
		jb1 = new JButton(SafeImage.readFromFile("sources/Source2.psrs")); //保存.jpg
		jb1.addActionListener(this);
		jb1.setMnemonic('s');
		jb1.setToolTipText("保存Alt+S");
		// jb1.setActionCommand("保存1");
		jb2 = new JButton(SafeImage.readFromFile("sources/Source5.psrs")); //打开.jpg
		jb2.addActionListener(this);
		jb3 = new JButton(SafeImage.readFromFile("sources/Source6.psrs")); //关闭当前.jpg
		jb3.addActionListener(this);
		jb3.setMnemonic('d');
		jb3.setToolTipText("关闭当前Alt+D");
		jb4 = new JButton(SafeImage.readFromFile("sources/Source4.psrs")); //撤销.jpg
		jb4.addActionListener(this);
		jb4.setMnemonic('z');
		jb4.setToolTipText("撤销Alt+Z");
		jb5 = new JButton(SafeImage.readFromFile("sources/Source3.psrs")); //插入图片.jpg
		jb5.addActionListener(this);
		jb5.setToolTipText("这是插入图片呢");
		// jb2.setActionCommand("打开1");
		jtb.add(jb1);
		jtb.add(jb2);
		jtb.add(jb3);
		jtb.add(jb4);
		jtb.add(jb5);

		// jp.setLayout(new GridLayout(1,2));
		this.add(jtb, BorderLayout.NORTH);

		jp = new JPanel();
		// jl=new JLabel(new ImageIcon("images/111.jpg"));
		// jp.setLayout(new BorderLayout());
		// jp.add(jl,BorderLayout.NORTH);

		jtp = new PTabbedPane();
		jtp.passMFT(this);
		// jp.add(jsp1,BorderLayout.CENTER);
		// jtp.add("文件1", jp);
		// jtp.add("文件2", jsp2);
		this.add(jtp, BorderLayout.CENTER);
		
		
	    
		this.addWindowListener(this);
		UpHide uh=new UpHide(this);
		MouseHook mh=new MouseHook();
		mh.setMouseHook();
		uh.start();
		this.setSize(800, 600);
		this.setLocation(250, 50);
		this.setIconImage((SafeImage.readFromFile("Sources/Source1.psrs")).getImage()); //图标.jpg
		//this.setTitle(Function.mainTitle);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		Properties prop=new Properties();
		InputStream is;
	    try
		{
	    	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			is=classloader.getResourceAsStream("profile.properties");
			prop.load(is);
			Function.FileChooserPath=prop.getProperty("FileChooserPath", "");
			Function.mainTitle=prop.getProperty("MainTitle","可以设置我哦");
			Function.foreground=new Color(Integer.parseInt(prop.getProperty("ForeColor", "-1")));
			Function.background=new Color(Integer.parseInt(prop.getProperty("BackColor", "-16777216")));
			Function.isUpHide=Boolean.parseBoolean(prop.getProperty("isUpHide", "true"));
			Function.FilesNames=prop.getProperty("FilesNames", "");
			int rmsize=Integer.parseInt(prop.getProperty("RightMenuSize", "0"));
			for(int i=0;i<rmsize;i++)
			{
				Function.ListItemsName.add(prop.getProperty("RightMenu_"+i+"_Name"));
				Function.ListItemsColor.add(new Color(Integer.parseInt(prop.getProperty("RightMenu_"+i+"_Color"))));
			}
			if(!Function.FilesNames.isEmpty())
			{
				String fs[]=Function.FilesNames.split("\n");
				for(int i=0;i<fs.length;i++)
				{
					File toOpenFile=new File(fs[i]);
					if(toOpenFile.exists())
					   this.openfile(toOpenFile);
				}
			}
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void openfile(File f)
	{
		if (f != null)
		{
			farr.add(f);
			if(f.getName().endsWith(".txt"))
			{
				jta.add(new PTextArea(new DefaultStyledDocument()));
			}
			else {
				WriteOrReadRecordFile worrf=new WriteOrReadRecordFile(null, f.getAbsolutePath());
				PTextArea tempjta=new PTextArea(worrf.readFromFile());
				//tempjta.setStyledDocument(worrf.readFromFile());
				jta.add(tempjta);
			}
			UndoClass um=new UndoClass();
			um.setLimit(300);
			jta.get(num).getStyledDocument().addUndoableEditListener(um);
			Function.undoal.add(um);
			jta.get(num).setCaretColor(Color.white);
			jta.get(num).setBackground(Function.background);
			jta.get(num).setForeground(Function.foreground);
			jta.get(num).setFont(new Font("宋体", Font.BOLD, 20));
			
			String savePath="ImageSrs/"+f.getName().substring(0, f.getName().lastIndexOf(".ptxt"));
			try
			{
				FileReader fis=new FileReader(savePath+"/record.prd");
				BufferedReader bis=new BufferedReader(fis);
				String str;
				int count=0;
				while((str=bis.readLine())!=null)
				{
				jta.get(num).requestFocus();
				String tempstr[]=str.split(",");
				jta.get(num).setCaretPosition(Integer.parseInt(tempstr[0])+count);
				jta.get(num).insertComponent(new PImage(tempstr[1], jta.get(num)));
				count++;
				}
				
				bis.close();
				fis.close();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//jta.get(num).;
			//jta.get(num).set
			jsp.add(new JScrollPane(jta.get(num)));
			if(f.getName().endsWith(".txt"))
			{
				jtp.add(f.getName().replace(".txt", ""), jsp.get(num));	
			try
			{
				FileReader fr = new FileReader(farr.get(num));
				char[] charr = new char[512];
				String temp="";
				int n;
				while ((n = fr.read(charr)) != -1)
				{
					temp=temp+new String(charr,0,n);	
				}
//				temp=temp.replaceAll("\r\r\n\r\r\n", "");
//				temp=temp.replaceAll("\r\r\n", "\r\n");
////				temp=temp.replaceAll("\r\r", "");
////                temp=temp.replaceAll("\n", "MYNN");
////			    temp=temp.replaceAll("\r", "MYRR");
//				//temp=temp.replaceAll("MyBlank", "\n");
				jta.get(num).setText(temp);
				fr.close();
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			else
				jtp.add(f.getName().replace(".ptxt", ""), jsp.get(num));
			
			jtp.setSelectedIndex(num);
			jta.get(num).setCaretPosition(0);
//			Function.undoal.add(new undoClass());
//			Function.undoal.get(num).astr.add(jta.get(num).getStyledDocument());
			num++;
			if(f.getName().endsWith(".txt"))
			{
			num--;
			Function.saveText(f, this,num);
			String path=f.getAbsolutePath();
			path=path.substring(0, path.lastIndexOf("txt"))+"ptxt";			
			farr.remove(num);
			jtp.remove(num);
			jta.remove(num);
			jsp.remove(num);
			Function.undoal.remove(num);
			openfile(new File(path));}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		echo = 0;
		if (e.getSource() == jmi01) // 新建
		{
			FileSystemView fsv = FileSystemView.getFileSystemView();
			JFileChooser fc;
			if (Function.FileChooserPath.equals(""))
				fc = new JFileChooser(fsv.getHomeDirectory());
			else
				fc = new JFileChooser(Function.FileChooserPath);
			fc.setFileFilter(new FileFilter()
			{

				@Override
				public String getDescription()
				{
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public boolean accept(File f)
				{
					// TODO Auto-generated method stub
					if (f.getName().endsWith(".ptxt") || f.isDirectory())
						return true;
					else
						return false;
				}
			});
			fc.showSaveDialog(this);
			File f = fc.getSelectedFile();
			
			if (f != null)
			{
				if (!f.getAbsolutePath().endsWith(".ptxt"))
					f = new File(f.getAbsolutePath() + ".ptxt");
			
				File fs[] = f.getParentFile().listFiles(
						new java.io.FileFilter()
						{

							@Override
							public boolean accept(File f)
							{
								// TODO Auto-generated method stub
								if (f.getAbsolutePath().endsWith(".ptxt"))
									return true;
								else
								{
									return false;
								}
							}
						});
				int j = 0;
				for (j = 0; j < fs.length; j++)
				{
					if (fs[j].getName().equals(f.getName()))
						break;
				}
				if (j < fs.length)
					{int k;
					for (k = 0; k < farr.size(); k++)
					{
						if (farr.get(k).getAbsolutePath()
								.equals(f.getAbsolutePath()))
						{
							jtp.setSelectedIndex(k);
							break;
						}
					}
					if(k==farr.size())
					   this.openfile(f);}
				else
				{
					farr.add(f);
					jta.add(new PTextArea(new DefaultStyledDocument()));
					jta.get(num).setCaretColor(Color.white);
					jta.get(num).setBackground(Function.background);
					jta.get(num).setForeground(Function.foreground);
					jta.get(num).setFont(new Font("宋体", Font.BOLD, 20));
					UndoClass um=new UndoClass();
					um.setLimit(300);
//					jta.get(num).getStyledDocument().addUndoableEditListener(um);
					Function.undoal.add(um);
					//jta.get(num).setLineWrap(true);
					jsp.add(new JScrollPane(jta.get(num)));
					jtp.add(farr.get(num).getName().replace(".ptxt", ""),
							jsp.get(num));
					jtp.setSelectedIndex(num);
					
					Function.FileChooserPath = fc.getCurrentDirectory().getAbsolutePath();
				
					Function.saveText(farr.get(num), this,num);
//					Function.undoal.add(new undoClass());
//					Function.undoal.get(num).astr.add(jta.get(num).getStyledDocument());
					num++;
				}
				
			}
		} else if (e.getSource() == jmi1 || e.getSource() == jb2) // 打开
		{
			FileSystemView fsv = FileSystemView.getFileSystemView();
			JFileChooser fc;
			if (Function.FileChooserPath.equals(""))
				fc = new JFileChooser(fsv.getHomeDirectory());
			else
				fc = new JFileChooser(Function.FileChooserPath);
			fc.setFileFilter(new FileFilter()
			{

				@Override
				public String getDescription()
				{
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public boolean accept(File f)
				{
					// TODO Auto-generated method stub
					if (f.getName().endsWith(".ptxt")||f.getName().endsWith(".txt") || f.isDirectory())
						return true;
					else
						return false;
				}
			});
			fc.showOpenDialog(this);
			File f = fc.getSelectedFile();
			if (f != null)
			{
				
					int k = 0;
					for (k = 0; k < farr.size(); k++)
					{
						if (farr.get(k).getAbsolutePath()
								.equals(f.getAbsolutePath()))
						{
							jtp.setSelectedIndex(k);
							break;
						}
					}
					if (k == farr.size())
						this.openfile(f);
					
					//jta.get(jtp.getSelectedIndex()).setCaretPosition(0);
				}
			
			Function.FileChooserPath = fc.getCurrentDirectory().getAbsolutePath();
		} else if (e.getSource() == jmi2 || e.getSource() == jb1) // 保存
		{
			int tempindex = jtp.getSelectedIndex();
			Function.saveText(farr.get(tempindex), this,tempindex);
		
		} else if (e.getSource() == jmi3) // 另存为
		{
			FileSystemView fsv = FileSystemView.getFileSystemView();
			JFileChooser fc;
			if (Function.FileChooserPath.equals(""))
				fc = new JFileChooser(fsv.getHomeDirectory());
			else
				fc = new JFileChooser(Function.FileChooserPath);
			fc.setFileFilter(new FileFilter()
			{

				@Override
				public String getDescription()
				{
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public boolean accept(File f)
				{
					// TODO Auto-generated method stub
					if (f.getName().endsWith(".ptxt"))
						return true;
					else
						return false;
				}
			});
			fc.showSaveDialog(this);
			File f = fc.getSelectedFile();
			if (!f.getAbsolutePath().endsWith(".ptxt"))
				f = new File(f.getAbsolutePath() + ".ptxt");
			if (f != null)
			{
				int tempindex = jtp.getSelectedIndex();
				Function.saveText(f, this,tempindex);
				echo=2;
			}
			Function.FileChooserPath = fc.getCurrentDirectory().getAbsolutePath();
		} else if (e.getSource() == jmi4) // 退出
		{
			if(JOptionPane.showConfirmDialog(this, "您真的要关关关闭我嘛？","防手滑确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
			{
			for (int i = num - 1; i >= 0; i--)      //退出保存确认
			{
				if(jta.get(i).isChanged)
				{
					int info=JOptionPane.showConfirmDialog(this, farr.get(i).getName()+"的文本内容已改变，是否保存？","关闭保存确认", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					
					if(info==JOptionPane.YES_OPTION)
						Function.saveText(farr.get(i), this,i);
				}
			}
			// TODO Auto-generated method stub
			try
			{
				String temp="";
				for(int i=0;i<farr.size();i++)
				{
					temp=temp+farr.get(i).getAbsolutePath()+"\n";
				}
				Function.FilesNames=temp;
				Properties prop=new Properties();
				FileOutputStream fos;
	         
	            	
	            	fos = new FileOutputStream("Profiles.properties"); 
	                    prop.setProperty("FileChooserPath", Function.FileChooserPath);
	                    prop.setProperty("FilesNames", Function.FilesNames);
	                    prop.setProperty("MainTitle", Function.mainTitle);
	                    prop.setProperty("ForeColor", ""+Function.foreground.getRGB());
	                    prop.setProperty("BackColor", ""+Function.background.getRGB());
	                    prop.setProperty("isUpHide", ""+Function.isUpHide);
	                    prop.setProperty("RightMenuSize", Function.ListItemsColor.size()+"");
	                    for(int i=0;i<Function.ListItemsColor.size();i++)
	                    {
	                    	prop.setProperty("RightMenu_"+i+"_Name", Function.ListItemsName.get(i));
	                    	prop.setProperty("RightMenu_"+i+"_Color", Function.ListItemsColor.get(i).getRGB()+"");
	                    }
	                    prop.store(fos, "配置文件");
	            
				fos.close();
			} catch (Exception e2)
			{
				// TODO: handle exception
			}
			System.exit(0);
			}
		} else if (e.getSource() == jmi5 || e.getSource() == jb3) // 关闭当前
		{
			
			int tempindex = jtp.getSelectedIndex();
			if(tempindex!=-1)
			{
			if(jta.get(tempindex).isChanged)
			{
				int info=JOptionPane.showConfirmDialog(this, farr.get(tempindex).getName()+"的文本内容已改变，是否保存？","关闭保存确认", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				if(info==JOptionPane.YES_OPTION)
				{
					Function.saveText(farr.get(tempindex), this,tempindex);
					num--;
					farr.remove(tempindex);
					jtp.remove(tempindex);
					jta.remove(tempindex);
					jsp.remove(tempindex);
					Function.undoal.remove(tempindex);
				}
				if(info==JOptionPane.NO_OPTION)
				{
					num--;
					farr.remove(tempindex);
					jtp.remove(tempindex);
					jta.remove(tempindex);
					jsp.remove(tempindex);
					Function.undoal.remove(tempindex);
				}
			}
			else {
				num--;
				farr.remove(tempindex);
				jtp.remove(tempindex);
				jta.remove(tempindex);
				jsp.remove(tempindex);
				Function.undoal.remove(tempindex);
			}
			}
			
		} else if (e.getSource() == jmi6) // 关闭所有
		{
			for (int i = num - 1; i >= 0; i--)
			{
				if(jta.get(i).isChanged)
				{
					int info=JOptionPane.showConfirmDialog(this, farr.get(i).getName()+"的文本内容已改变，是否保存？","关闭保存确认", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					
					if(info==JOptionPane.YES_OPTION)
						Function.saveText(farr.get(i),this,i);
				}
				jtp.remove(i);
				jta.remove(i);
				jsp.remove(i);
				farr.remove(i);
				Function.undoal.remove(i);
			}
			num = 0;
		}  else if (e.getSource() == jmi7)
		{
			int tempindex = jtp.getSelectedIndex();
			if(JOptionPane.showConfirmDialog(this, "您是否确认删除"+farr.get(tempindex).getName()+"这个文件？","删除确认", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE)==JOptionPane.YES_OPTION)
			{
			num--;
			farr.get(tempindex).delete();
			String path="ImageSrs/"+farr.get(tempindex).getName().substring(0, farr.get(tempindex).getName().lastIndexOf(".ptxt"));
			File parent=new File(path);
			File fs[]=parent.listFiles();
			for(int i=0;i<fs.length;i++)
			{
				fs[i].delete();
			}
			parent.delete();
			farr.remove(tempindex);
			jtp.remove(tempindex);
			jta.remove(tempindex);
			jsp.remove(tempindex);
			Function.undoal.remove(tempindex);
			}
		}
		else if(e.getSource()==jmi20 || e.getSource()==jb4) //撤销
		{
			int tempindex = jtp.getSelectedIndex();
			Function.undoText(tempindex,jta.get(tempindex));
		}
		else if (e.getSource()==jmi21)  //查找与替换
		{
			int tempindex = jtp.getSelectedIndex();
			FindAndReplace far=new FindAndReplace(this.getX()+250, this.getY()+225);
			far.setMta(jta.get(tempindex));
		}
		else if(e.getSource()==jmi22 || e.getSource()==jb5)  //插入图片
		{
			FileSystemView fsv = FileSystemView.getFileSystemView();
			JFileChooser fc;
			if (Function.FileChooserPath.equals(""))
				fc = new JFileChooser(fsv.getHomeDirectory());
			else
				fc = new JFileChooser(Function.FileChooserPath);
			fc.setFileFilter(new FileFilter()
			{

				@Override
				public String getDescription()
				{
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public boolean accept(File f)
				{
					// TODO Auto-generated method stub
					if (f.getName().endsWith(".jpg")||f.getName().endsWith(".gif") || f.isDirectory())
						return true;
					else
						return false;
				}
			});
			fc.showOpenDialog(this);
			File f = fc.getSelectedFile();
			if(f!=null)
			{
				int tempindex=jtp.getSelectedIndex();
				jta.get(tempindex).requestFocus();
				
				//	jta.get(tempindex).insertIcon(new ImageIcon(f.getAbsolutePath()));
				
               jta.get(tempindex).insertComponent(new PImage(f.getAbsolutePath(),jta.get(tempindex)));
				jta.get(tempindex).isChanged=true;
			}
		}
		else if(e.getSource()==jmi30)  //设置
		{
			
			SetProfile sp=new SetProfile(this);
			//JColorChooser.showDialog(this, "选择颜色", Color.black);
		}else if(e.getSource()==jmi31)  //右键菜单定制
		{
			SetRightMenu spm=new SetRightMenu(this,"右键菜单定制");
		}else if(e.getSource()==jmi40)
		{
			JOptionPane.showMessageDialog(this, ".....！", "什么g u i", JOptionPane.INFORMATION_MESSAGE,SafeImage.readFromFile("Sources/Source1.psrs"));
		}
		
	}

	@Override
	public void run()
	{
		int count=0;
		// TODO Auto-generated method stub
		try
		{
			while(true)
			{
				Thread.sleep(1000);
				switch (echo)
			{
			case 0:this.setTitle(Function.mainTitle);break;
			case 1:
				this.setTitle("保存成功！");
				echo=0;
				break;
			case 2:
				this.setTitle("另存为成功！");
				echo=0;
				break;
			default:
				break;
			}
				if(Function.undoIsChanged)
				    count++;
				if(count==4)
				{
			     Function.canSaveEdit=true;
			     count=0;
			     Function.undoIsChanged=false;
			    }
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		if(JOptionPane.showConfirmDialog(this, "您真的要关关关闭我嘛？","防手滑确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
		{
		
		for (int i = num - 1; i >= 0; i--)      //退出保存确认
		{
			if(jta.get(i).isChanged)
			{
				int info=JOptionPane.showConfirmDialog(this, farr.get(i).getName()+"的文本内容已改变，是否保存？","关闭保存确认", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				if(info==JOptionPane.YES_OPTION)
					Function.saveText(farr.get(i), this,i);
			}
		}
		// TODO Auto-generated method stub
		try
		{
			String temp="";
			for(int i=0;i<farr.size();i++)
			{
				temp=temp+farr.get(i).getAbsolutePath()+"\n";
			}
			Function.FilesNames=temp;
			Properties prop=new Properties();
			FileOutputStream fos;
         
            	
            	fos = new FileOutputStream("Profiles.properties"); 
                    prop.setProperty("FileChooserPath", Function.FileChooserPath);
                    prop.setProperty("FilesNames", Function.FilesNames);
                    prop.setProperty("MainTitle", Function.mainTitle);
                    prop.setProperty("ForeColor", ""+Function.foreground.getRGB());
                    prop.setProperty("BackColor", ""+Function.background.getRGB());
                    prop.setProperty("isUpHide", ""+Function.isUpHide);
                    prop.setProperty("RightMenuSize", Function.ListItemsColor.size()+"");
                    for(int i=0;i<Function.ListItemsColor.size();i++)
                    {
                    	prop.setProperty("RightMenu_"+i+"_Name", Function.ListItemsName.get(i));
                    	prop.setProperty("RightMenu_"+i+"_Color", Function.ListItemsColor.get(i).getRGB()+"");
                    }
                    prop.store(fos, "配置文件");
            
			fos.close();
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		} catch (Exception e2)
		{
			// TODO: handle exception
		}
		}else
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}
}


