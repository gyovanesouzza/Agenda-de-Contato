package br.gov.sp.agendacontatos.util;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class LookAndFeel {

public void Layout() {
	try {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (InstantiationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalAccessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (UnsupportedLookAndFeelException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		
	}


}
	
	
}

/*
 * COLA NA STRING lookAndFeel salva e executa a tela
 * 
 * 
 * com.jtattoo.plaf.mcwin.McWinLookAndFeel - Mistura de Windows com Mac
 * 
 * com.jtattoo.plaf.acryl.AcrylLookAndFeel - Bordas e alguns icones pretos
 * 
 * com.jtattoo.plaf.aero.AeroLookAndFeel - Borda Azul claro
 * 
 * com.jtattoo.plaf.bernstein.BernsteinLookAndFeel - Tudo Amarelo
 * 
 * com.jtattoo.plaf.aluminium.AluminiumLookAndFeel - Aluminio
 * 
 * com.jtattoo.plaf.fast.FastLookAndFeel - Normal estilo rapido
 * 
 * com.jtattoo.plaf.hifi.HiFiLookAndFeel - Tudo Preto com cinza
 * 
 * com.jtattoo.plaf.mint.MintLookAndFeel - Normal verde menta
 * 
 * com.jtattoo.plaf.luna.LunaLookAndFeel - Parecido com windows XP azul
 * 
 * com.jtattoo.plaf.texture.TextureLookAndFeel - cinza com textura
 * 
 * com.jtattoo.plaf.noire.NoireLookAndFeel - preto icones brancos
 * 
 * com.jtattoo.plaf.smart.SmartLookAndFeel - padrao com amarelo claro
 * 
 * com.jtattoo.plaf.graphite.GraphiteLookAndFeel - preto e branco grafite
 */
