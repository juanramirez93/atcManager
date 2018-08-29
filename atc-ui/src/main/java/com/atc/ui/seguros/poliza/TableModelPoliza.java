package com.atc.ui.seguros.poliza;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.atc.model.Poliza;
import com.atc.util.NumberConstants;

public class TableModelPoliza extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1617666233866562943L;
	private SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
	private DecimalFormat formatMoney = new DecimalFormat("$#,###");

	private List<Poliza> polizas;
	private String[] columns = { "Número", "Tipo", "Inicio de Vigencia", "Fin de Vigencia", "Valor" };

	public TableModelPoliza() {
		this.polizas = new ArrayList<Poliza>();
	}

	public int getColumnCount() {
		return NumberConstants.COLUMNS_TABLE_POLIZA;
	}

	public int getRowCount() {
		return this.polizas.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		Poliza poliza = polizas.get(arg0);
		switch (arg1) {
		case 0:
			return poliza.getNumero();
		case 1:
			return poliza.getRamo();
		case 2:
			return formatDate.format(poliza.getInicio());
		case 3:
			return formatDate.format(poliza.getFin());
		case 4:
			return formatMoney.format(poliza.getValor());
		}
		return null;
	}

	public void setPolizaList(List<Poliza> polizas) {
		this.polizas = polizas;
	}

	public void updateTable() {
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int arg0) {
		return this.columns[arg0];
	}

	public Poliza getSelected(int selectedRow) {
		return polizas.get(selectedRow);
	}
}
