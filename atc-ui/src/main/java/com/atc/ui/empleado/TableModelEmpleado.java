package com.atc.ui.empleado;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.atc.model.Empleado;
import com.atc.util.NumberConstants;

public class TableModelEmpleado extends AbstractTableModel {

	/**
	 * 
	 */
	
	protected DecimalFormat formatID = new DecimalFormat("#,###");

	private static final long serialVersionUID = -6506779577221369727L;
	private List<Empleado> empleados;
	private String[] columns = { "Cedula", "Nombres", "Apellidos" };

	public TableModelEmpleado() {
		this.empleados = new ArrayList<Empleado>();
	}

	public int getColumnCount() {
		return NumberConstants.COLUMNS_TABLE_EMPLEADO;
	}

	public int getRowCount() {
		return this.empleados.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		Empleado empleado = empleados.get(arg0);
		switch (arg1) {
		case 0:
			return formatID.format(empleado.getCedula());
		case 1:
			return empleado.getNombres();
		case 2:
			return empleado.getApellidos();
		}
		return null;
	}

	public void setEmpleadoList(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public void updateTable() {
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int arg0) {
		return this.columns[arg0];
	}
	public Empleado getSelected(int indice) {
		return empleados.get(indice);
	}

}
