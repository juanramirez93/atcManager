package com.atc.app;

import java.text.SimpleDateFormat;

import javax.swing.JFrame;

import com.atc.connection.Session;
import com.atc.model.Usuario;

public class FrameAbstract extends JFrame {

	/**
	 * 
	 */

	protected SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
	private static final long serialVersionUID = -7611581933380839698L;

	protected Usuario userSession;

	public static final int LISTADO = 3;
	public static final int DETALLE = 5;
	public static final int EDITAR = 7;
	public static final int AGREGAR = 11;

	public FrameAbstract(String name) {
		super(name);
		this.userSession = Session.INSTANCE.getUserSession();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
