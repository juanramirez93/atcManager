package com.atc.ui.natural;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import com.atc.model.Natural;

public class TableNatural extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2944990787637616726L;
	private JTable naturalesTable;
	private TableModelNatural model;
	private JLabel countLabel;

	public TableNatural() {
		initialize();
	}

	private void initialize() {
		this.model = new TableModelNatural();
		this.naturalesTable = new JTable(model);
		this.naturalesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.countLabel = new JLabel();
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(15, 15, 15, 15));
		add(new JScrollPane(naturalesTable), BorderLayout.CENTER);
		add(countLabel, BorderLayout.SOUTH);
		setColumnLayout();
	}

	private void setColumnLayout() {
		TableColumnModel columnModel = naturalesTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(80);
	}

	public void setTableModel(List<Natural> naturales) {
		this.model.setNaturalList(naturales);
		this.countLabel.setText("Total registros: " + naturales.size());
	}

	public void updateTable() {
		this.model.updateTable();
	}

	public Natural getSelected() {
		if (naturalesTable.getSelectedRow() == -1) {
			return null;
		}
		return model.getSelected(naturalesTable.getSelectedRow());
	}
}
