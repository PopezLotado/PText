package org.pzone.text.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

public class WriteOrReadRecordFile {
	// 这个obj，其实对于这个模块就是用来接收SJTextPane的。
	private JTextPane m_Obj;
	private String m_filePath;
	public WriteOrReadRecordFile(JTextPane obj,String filePath) 
	{
		m_Obj = obj;
		m_filePath = filePath;
	}
	// 用来接收文件的路径(含文件名)。
	public boolean writeToFile(int index) {
			try {
				// 保存序列化的文件名默认为"serial_n.info"
				ObjectOutputStream oos = new ObjectOutputStream(
						new FileOutputStream(m_filePath));
				m_Obj.getStyledDocument().removeUndoableEditListener(Function.undoal.get(index));
				oos.writeObject(m_Obj.getStyledDocument());
				oos.close();
				m_Obj.getStyledDocument().addUndoableEditListener(Function.undoal.get(index));
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "保存记录时出错");
				return false;
			}
		return true;
	}
	// 用这个返回的Object时，要记得强制转换为序列化前的类型。
	public StyledDocument readFromFile() {
		StyledDocument rtnObj;
			try {
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(m_filePath));
				rtnObj = (StyledDocument) ois.readObject();
				ois.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "读取记录时出错");
				return null;
			}
		
		return rtnObj;
	}
}
