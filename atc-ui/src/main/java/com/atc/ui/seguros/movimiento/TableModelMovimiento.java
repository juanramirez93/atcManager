package com.atc.ui.seguros.movimiento;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.atc.model.Movimiento;
import com.atc.util.NumberConstants;

public class TableModelMovimiento extends AbstractTableModel {

	private static final long serialVersionUID = 4010350872385861256L;


	private SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");

	private List<Movimiento> movimientos;
	private String[] columns = { "Fecha", "Empresa", "Cantidad", "Producto", "Numeración", "Factura Vida", "Remisión RCE",
			"Remisión Transportes" };

	public TableModelMovimiento() {
		this.movimientos = new ArrayList<Movimiento>();
	}

	public int getColumnCount() {
		return NumberConstants.COLUMNS_TABLE_MOVIMIENTO;
	}

	public int getRowCount() {
		return this.movimientos.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		Movimiento movimiento = movimientos.get(arg0);
		switch (arg1) {
		case 0:
			return formatDate.format(movimiento.getFecha());
		case 1:
			if (movimiento.getProducto().getEmpresa().getSiglas() != "") {
				return movimiento.getProducto().getEmpresa().getSiglas();
			}
			return movimiento.getProducto().getEmpresa().getRazonSocial();
		case 2:
			return movimiento.getCantidad();
		case 3:
			return movimiento.getProducto().getTipo();
		case 4:
			return movimiento.getNumeracion();
		case 5:
			if (movimiento.getFactVida() == 0) {
				return "";
			}
			return movimiento.getFactVida();
		case 6:
			if (movimiento.getRemRCE() == 0) {
				return "";
			}
			return movimiento.getRemRCE();
		case 7:
			if (movimiento.getRemTransporte() == 0) {
				return "";
			}
			return movimiento.getRemTransporte();
		}
		return null;
	}

	public void setMovimientosList(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public void updateTable() {
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int arg0) {
		return this.columns[arg0];
	}

	public Movimiento getSelected(int indice) {
		return movimientos.get(indice);
	}

}
