package com.atc.ui.seguros.stock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.atc.model.Stock;
import com.atc.util.NumberConstants;

public class TableModelStock extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8546145640814013275L;

	private SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");

	private List<Stock> stock;
	private String[] columns = { "Fecha", "Producto", "Incial", "Final", "Cantidad", "Restantes" };

	public TableModelStock() {
		this.stock = new ArrayList<Stock>();
	}

	public int getColumnCount() {
		return NumberConstants.COLUMNS_TABLE_STOCK;
	}

	public int getRowCount() {
		return this.stock.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		Stock inventario = stock.get(arg0);
		switch (arg1) {
		case 0:
			return formatDate.format(inventario.getFecha());
		case 1:

			return inventario.getProducto();
		case 2:
			return inventario.getInicio();
		case 3:
			return inventario.getFin();
		case 4:
			return inventario.getFin() - inventario.getInicio() + 1;
		case 5:
			return inventario.getRestantes();
		}
		return null;
	}

	public void setStockList(List<Stock> stock) {
		this.stock = stock;
	}

	public void updateTable() {
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int arg0) {
		return this.columns[arg0];
	}

	public Stock getSelected(int indice) {
		return stock.get(indice);
	}

}
