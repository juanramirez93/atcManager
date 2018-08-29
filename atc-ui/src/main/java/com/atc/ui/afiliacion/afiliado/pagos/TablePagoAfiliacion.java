package com.atc.ui.afiliacion.afiliado.pagos;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import com.atc.model.PagoAfiliado;

public class TablePagoAfiliacion extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8775707891174714018L;
	private JTable afiliacionTable;
	private TableModelPagoAfiliacion model;
	private JLabel countLabel;

	public TablePagoAfiliacion() {
		initialize();

	}

	private void initialize() {
		this.model = new TableModelPagoAfiliacion();
		this.afiliacionTable = new JTable(model);
		this.afiliacionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.countLabel = new JLabel();
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(15, 15, 15, 15));
		add(new JScrollPane(afiliacionTable), BorderLayout.CENTER);
		add(countLabel, BorderLayout.SOUTH);
		setColumnLayout();
	}

	private void setColumnLayout() {
		TableColumnModel columnModel = afiliacionTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(80);
	}

	public void setTableModel(List<PagoAfiliado> pagos) {
		this.model.setPagosAfiliacionesList(pagos);
		this.countLabel.setText("Total registros: " + pagos.size());
	}

	public void updateTable() {
		this.model.updateTable();
	}

	public PagoAfiliado getSelected() {
		if (afiliacionTable.getSelectedRow() == -1) {
			return null;
		}
		return model.getSelected(afiliacionTable.getSelectedRow());
	}
}
