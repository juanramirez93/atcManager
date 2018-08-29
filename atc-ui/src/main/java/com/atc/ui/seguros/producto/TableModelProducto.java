package com.atc.ui.seguros.producto;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.atc.model.Producto;
import com.atc.util.NumberConstants;

public class TableModelProducto extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2540503637089672579L;
	private List<Producto> productos;
	private String[] columns = { "Empresa", "Modalidad", "Producto" };

	public TableModelProducto() {
		this.productos = new ArrayList<Producto>();
	}

	public int getColumnCount() {
		return NumberConstants.COLUMNS_TABLE_PRODUCTO;
	}

	public int getRowCount() {
		return this.productos.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		Producto producto = productos.get(arg0);
		switch (arg1) {
		case 0:
			if (producto.getEmpresa().getSiglas() == "") {
				return producto.getEmpresa().getRazonSocial();
			} else {
				return producto.getEmpresa().getSiglas();
			}
		case 1:
			return producto.getModalidad();
		case 2:
			return producto.getTipo();
		}
		return null;
	}

	public void setProductoList(List<Producto> productos) {
		this.productos = productos;
	}

	public void updateTable() {
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int arg0) {
		return this.columns[arg0];
	}

	public Producto getSelected(int selectedRow) {
		return productos.get(selectedRow);
	}
}
