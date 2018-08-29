package com.atc.ui.seguros.producto;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import com.atc.model.Producto;

public class TableProducto extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -716882046182359192L;
	private JTable productosTable;
	private TableModelProducto model;

	public TableProducto() {
		initialize();
	}

	private void initialize() {
		this.model = new TableModelProducto();
		this.productosTable = new JTable(model);
		this.productosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(15, 15, 15, 15));
		add(new JScrollPane(productosTable), BorderLayout.CENTER);
		setColumnLayout();
	}

	private void setColumnLayout() {
		TableColumnModel columnModel = productosTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(50);
		columnModel.getColumn(2).setPreferredWidth(100);
	}

	public void setTableModel(List<Producto> productos) {
		this.model.setProductoList(productos);
	}

	public void updateTable() {
		this.model.updateTable();
	}

	public Producto getSelected() {
		if (productosTable.getSelectedRow() == -1) {
			return null;
		}
		return model.getSelected(productosTable.getSelectedRow());
	}
}
