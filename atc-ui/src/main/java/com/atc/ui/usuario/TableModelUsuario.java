package com.atc.ui.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.atc.model.Usuario;
import com.atc.util.NumberConstants;

public class TableModelUsuario extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2043292488407193792L;
	private List<Usuario> usuarios;
	private String[] columns = { "Usuario", "Cédula", "Nombres" };

	public TableModelUsuario() {
		this.usuarios = new ArrayList<Usuario>();
	}

	public int getColumnCount() {
		return NumberConstants.COLUMNS_TABLE_USUARIO;
	}

	public int getRowCount() {
		return this.usuarios.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		Usuario usuario = usuarios.get(arg0);
		switch (arg1) {
		case 0:
			return usuario.getUser();
		case 1:
			return usuario.getEmpleado().getCedula();
		case 2:
			return usuario.getEmpleado().getAbreviatura();
		}
		return null;
	}

	public void setUsuarioList(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void updateTable() {
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int arg0) {
		return this.columns[arg0];
	}
	public Usuario getSelected(int indice) {
		return usuarios.get(indice);
	}

}
