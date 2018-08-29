package com.atc.ui.seguros.movimiento;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import com.atc.model.Movimiento;

public class TableMovimiento extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3187022747510749036L;
	private JTable movimientoTable;
	private TableModelMovimiento model;
	private JLabel countLabel;

	public TableMovimiento() {
		initialize();

	}

	private void initialize() {
		this.model = new TableModelMovimiento();
		this.movimientoTable = new JTable(model);
		this.movimientoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.countLabel = new JLabel();
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(15, 15, 15, 15));
		add(new JScrollPane(movimientoTable), BorderLayout.CENTER);
		add(countLabel, BorderLayout.SOUTH);
		setColumnLayout();
	}

	private void setColumnLayout() {
		TableColumnModel columnModel = movimientoTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(67);
		columnModel.getColumn(1).setPreferredWidth(250);
		columnModel.getColumn(2).setPreferredWidth(40);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(40);
		columnModel.getColumn(6).setPreferredWidth(40);
		columnModel.getColumn(7).setPreferredWidth(40);
	}
	
	public void setTableModel(List<Movimiento> movimientos) {
		this.model.setMovimientosList(movimientos);
		this.countLabel.setText("Total registros: " + movimientos.size());
	}

	public void updateTable() {
		this.model.updateTable();
	}

	public Movimiento getSelected() {
		if (movimientoTable.getSelectedRow() == -1) {
			return null;
		}
		return model.getSelected(movimientoTable.getSelectedRow());
	}
}
