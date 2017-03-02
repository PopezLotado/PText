package org.pzone.text.unit;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.pzone.text.util.Function;

public class CellRenderer extends JLabel implements ListCellRenderer<String>
{
	private static final long serialVersionUID = -1888527186409531378L;

	public CellRenderer()
	{
		// TODO Auto-generated constructor stub
		 setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends String> list,
			String value, int index, boolean isSelected, boolean cellHasFocus)
	{
		// TODO Auto-generated method stub
		if(value!=null)
		{
			setText(value);
			setForeground(Function.ListItemsColor.get(Function.ListItemsName.indexOf(value)));
		}
		if(isSelected)
		{
			setBackground(list.getSelectionBackground());
		}
		else {
			setBackground(list.getBackground());
		}
		return this;
	}
	
}
