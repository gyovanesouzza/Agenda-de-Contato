package br.gov.sp.agendacontatos.validator;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
 
public class ApenasNumero extends PlainDocument {
 
    private static final long serialVersionUID = 1L;
 
    public void insertString(int offs, String str,
            javax.swing.text.AttributeSet a) throws BadLocationException {
       
        for (int i = 0; i < str.length(); i++)
        	
            if (!Character.isDigit(str.charAt(i)))
                return;
 
        super.insertString(offs, str, a);
 
    }
}