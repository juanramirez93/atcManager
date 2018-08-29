package com.atc.ui.natural;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.atc.model.Natural;
import com.atc.util.NumberConstants;

public class TableModelNatural extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3210773369599787470L;

	private DecimalFormat formatID = new DecimalFormat("#,###");

	private List<Natural> naturales;
	private String[] columns = { "Cédula", "Nombres", "Apellidos" };

	public TableModelNatural() {
		this.naturales = new ArrayList<Natural>();
	}

	public int getColumnCount() {
		return NumberConstants.COLUMNS_MAIN_TABLE_NATURAL;
	}

	public int getRowCount() {
		return this.naturales.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		Natural natural = naturales.get(arg0);
		switch (arg1) {
		case 0:
			return formatID.format(natural.getCedula());
		case 1:
			return natural.getNombres();
		case 2:
			return natural.getApellidos();
		}
		return null;
	}

	public Natural getSelected(int indice) {
		return naturales.get(indice);
	}

	public void setNaturalList(List<Natural> naturales) {
		this.naturales = naturales;
	}

	public void updateTable() {
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int arg0) {
		return this.columns[arg0];
	}

}
