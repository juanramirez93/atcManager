package com.atc.ui.afiliacion.afiliado;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import com.atc.model.Afiliacion;

public class TableAfiliado extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8775707891174714018L;
	private JTable afiliacionTable;
	private TableModelAfiliado model;
	private JLabel countLabel;

	public TableAfiliado() {
		initialize();

	}

	public List<Afiliacion> getTableModel(){
		return model.getAfiliaciones();
	}
	
	private void initialize() {
		this.model = new TableModelAfiliado();
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
		columnModel.getColumn(0).setPreferredWidth(70);
		columnModel.getColumn(1).setPreferredWidth(70);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(80);
	}

	public void setTableModel(List<Afiliacion> afiliaciones) {
		this.model.setAfiliacionesList(afiliaciones);
		this.countLabel.setText("Total registros: " + afiliaciones.size());
	}

	public void updateTable() {
		this.model.updateTable();
	}

	public Afiliacion getSelected() {
		if (afiliacionTable.getSelectedRow() == -1) {
			return null;
		}
		return model.getSelected(afiliacionTable.getSelectedRow());
	}
}
