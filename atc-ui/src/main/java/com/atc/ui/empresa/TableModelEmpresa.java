package com.atc.ui.empresa;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.atc.model.Empresa;
import com.atc.util.NumberConstants;

public class TableModelEmpresa extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3210773369599787470L;

	private DecimalFormat formatID = new DecimalFormat("#,###");

	private List<Empresa> empresas;
	private String[] columns = { "Razón Social", "NIT" };

	public TableModelEmpresa() {
		this.empresas = new ArrayList<Empresa>();
	}

	public int getColumnCount() {
		return NumberConstants.COLUMNS_MAIN_TABLE_EMPRESA;
	}

	public int getRowCount() {
		return this.empresas.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		Empresa empresa = empresas.get(arg0);
		switch (arg1) {
		case 1:
			return formatID.format(empresa.getNit());
		case 0:
			if (empresa.getSiglas() == "") {
				return empresa.getRazonSocial();
			} else {
				return empresa.getSiglas();
			}
		}
		return null;
	}

	public Empresa getSelected(int indice) {
		return empresas.get(indice);
	}

	public void setEmpresaList(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public void updateTable() {
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int arg0) {
		return this.columns[arg0];
	}

}
