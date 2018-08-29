package com.atc.ui.seguros.producto;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.atc.model.Producto;
import com.atc.service.seguros.producto.SearchProductoService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class SearchProducto extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3067487130171402986L;
	JLabel buscarLabel;
	JTextField buscarField;
	JButton buscarButton;
	JButton deleteButton;
	SearchProductoService service;
	MainProducto parent;

	public SearchProducto(MainProducto parent) {
		initializeVariables();
		this.parent = parent;
		initializeLayout();
	}

	private void initializeLayout() {
		setLayout(new FlowLayout());
		add(buscarLabel);
		add(buscarField);
		add(buscarButton);
		add(deleteButton);
	}

	private void initializeVariables() {
		service = new SearchProductoService();
		buscarLabel = new JLabel(StringsConstants.SEARCH);
		buscarField = new JTextField(NumberConstants.ADDPOLIZA_FIELD);
		buscarButton = new JButton(StringsConstants.SEARCH);
		buscarButton.addActionListener(this);
		deleteButton = new JButton("X");
		deleteButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buscarButton) {
			List<Producto> productos;
			if (buscarField.getText() == "" || buscarField.getText().isEmpty()) {
				parent.refreshTable();
			} else {
				String str = buscarField.getText();
				System.out.println(str);
				productos = service.search(str);
				parent.refreshTable(productos);
			}

		}else if(e.getSource() == deleteButton) {
			buscarField.setText("");
			parent.refreshTable();
		}
	}

}
