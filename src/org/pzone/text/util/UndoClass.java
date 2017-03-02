package org.pzone.text.util;

import java.util.ArrayList;

import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

public class UndoClass extends UndoManager
{
	ArrayList<UndoableEdit> uedits=new ArrayList<UndoableEdit>();
	public void myUndo()
	{
		if(uedits.size()>0)
		{
		this.undoTo(uedits.get(uedits.size()-1));
		uedits.remove(uedits.size()-1);}
		else {
			this.undo();
		}
	}
	@Override
	public void undoableEditHappened(UndoableEditEvent e)
	{
		this.addEdit(e.getEdit());
		if(Function.canSaveEdit)
		   {
			if(uedits.size()==6)
			   uedits.remove(0);
			uedits.add(e.getEdit());
		   Function.canSaveEdit=false;}
	}

}