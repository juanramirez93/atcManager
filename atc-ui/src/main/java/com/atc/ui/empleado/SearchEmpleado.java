package com.atc.ui.empleado;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.atc.model.Empleado;
import com.atc.service.empleado.SearchEmpleadoService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class SearchEmpleado extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8395157577595297811L;
	JLabel buscarLabel;
	JTextField buscarField;
	JButton buscarButton;
	JButton deleteButton;
	SearchEmpleadoService service;
	MainEmpleado parent;

	public SearchEmpleado(MainEmpleado parent) {
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
		service = new SearchEmpleadoService();
		buscarLabel = new JLabel(StringsConstants.SEARCH);
		buscarField = new JTextField(NumberConstants.ADDPOLIZA_FIELD);
		buscarButton = new JButton(StringsConstants.SEARCH);
		buscarButton.addActionListener(this);
		deleteButton = new JButton("X");
		deleteButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buscarButton) {
			List<Empleado> empleados;
			if (buscarField.getText() == "" || buscarField.getText().isEmpty()) {
				parent.refreshTable();
			} else {
				String str = buscarField.getText();
				System.out.println(str);
				empleados = service.search(str);
				parent.refreshTable(empleados);
			}

		}else if(e.getSource() == deleteButton) {
			buscarField.setText("");
			parent.refreshTable();
		}
	}

}
