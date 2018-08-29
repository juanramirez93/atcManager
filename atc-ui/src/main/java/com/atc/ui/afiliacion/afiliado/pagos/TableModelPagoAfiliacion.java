package com.atc.ui.afiliacion.afiliado.pagos;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.atc.model.PagoAfiliado;
import com.atc.util.NumberConstants;

public class TableModelPagoAfiliacion extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3264262636394831708L;

	private SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
	protected DecimalFormat formatID = new DecimalFormat("#,###");
	protected DecimalFormat formatMoney = new DecimalFormat("$#,###");

	private List<PagoAfiliado> pagos;
	private String[] columns = { "Fecha Pago", "Fecha Vencimieno", "Valor", "Recibo de Pago", "Captador", "Asesor" };

	public TableModelPagoAfiliacion() {
		this.pagos = new ArrayList<PagoAfiliado>();
	}

	public int getColumnCount() {
		return NumberConstants.COLUMNS_TABLE_PAGOS_AFILIACION;
	}

	public int getRowCount() {
		return this.pagos.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		PagoAfiliado pago = pagos.get(arg0);
		switch (arg1) {
		case 0:
			return formatDate.format(pago.getFecha());
		case 1:
			return formatDate.format(pago.getVencimiento());
		case 2:
			return formatMoney.format(pago.getValor());
		case 3:
			return pago.getReciboDeCaja();
		case 4:
			return pago.getCaptador();
		case 5:
			return pago.getAsesor();
		}
		return null;
	}

	public void setPagosAfiliacionesList(List<PagoAfiliado> pagos) {
		this.pagos = pagos;
	}

	public void updateTable() {
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int arg0) {
		return this.columns[arg0];
	}

	public PagoAfiliado getSelected(int indice) {
		return pagos.get(indice);
	}

}
