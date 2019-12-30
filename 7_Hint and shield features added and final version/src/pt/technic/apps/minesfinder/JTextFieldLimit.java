package pt.technic.apps.minesfinder;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument{
	private int limitedField;
	private boolean toUppercase = false;
	JTextFieldLimit(int limitedField){
		super();
		this.limitedField = limitedField;
	}
	
	public void insertString(int offset, String str, javax.swing.text.AttributeSet attr)
			throws BadLocationException{
		if(str == null) return;
		if((getLength() + str.length()) <= limitedField) {
			if(toUppercase) str = str.toUpperCase();
			
			super.insertString(offset,str,attr);
		}
		if((getLength() + str.length()) > limitedField) {
			MinesFinder minesfinder = new MinesFinder();
			minesfinder.errorDialog();
		}
	}
}

