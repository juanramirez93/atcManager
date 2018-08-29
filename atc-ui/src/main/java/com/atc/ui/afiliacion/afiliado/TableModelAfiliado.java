package com.atc.ui.afiliacion.afiliado;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.atc.model.Afiliacion;
import com.atc.util.NumberConstants;

public class TableModelAfiliado extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3264262636394831708L;

	private SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
	protected DecimalFormat formatID = new DecimalFormat("#,###");

	private List<Afiliacion> afiliaciones;
	private String[] columns = { "Fecha", "Identificación", "Nombre", "Estado" };

	public TableModelAfiliado() {
		this.afiliaciones = new ArrayList<Afiliacion>();
	}

	public int getColumnCount() {
		return NumberConstants.COLUMNS_TABLE_AFILIACION;
	}

	public int getRowCount() {
		return this.afiliaciones.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		Afiliacion afil = afiliaciones.get(arg0);
		switch (arg1) {
		case 0:
			return formatDate.format(afil.getFechaAfiliacion());
		case 1:
			return formatID.format(afil.getAfiliado().getIdentificacion());
		case 2:
			return afil.getAfiliado().getNombre();
		case 3:
			Date ultimo = afil.getUltimoPago();
			if (ultimo != null) {
				if (ultimo.before(new Date())) {
					return "Inactivo";
				} else {
					return "Activo";
				}
			} else {
				return "No hay pagos registrados";
			}
		}
		return null;
	}

	public void setAfiliacionesList(List<Afiliacion> afiliaciones) {
		this.afiliaciones = afiliaciones;
	}

	public void updateTable() {
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int arg0) {
		return this.columns[arg0];
	}

	public Afiliacion getSelected(int indice) {
		return afiliaciones.get(indice);
	}

	public List<Afiliacion> getAfiliaciones() {
		return afiliaciones;
	}

}
