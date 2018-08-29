package com.atc.ui.empresa;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.atc.model.Empresa;
import com.atc.service.empresa.SearchEmpresaService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class SearchEmpresa extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4466238143019415283L;
	JLabel buscarLabel;
	JTextField buscarField;
	JButton buscarButton;
	JButton deleteButton;
	SearchEmpresaService service;
	MainEmpresa parent;

	public SearchEmpresa(MainEmpresa parent) {
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
		service = new SearchEmpresaService();
		buscarLabel = new JLabel(StringsConstants.SEARCH);
		buscarField = new JTextField(NumberConstants.ADDPOLIZA_FIELD);
		buscarButton = new JButton(StringsConstants.SEARCH);
		buscarButton.addActionListener(this);
		deleteButton = new JButton("X");
		deleteButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buscarButton) {
			List<Empresa> empresas;
			if (buscarField.getText() == "" || buscarField.getText().isEmpty()) {
				parent.refreshTable();
			} else {
				String str = buscarField.getText();
				System.out.println(str);
				empresas = service.search(str);
				parent.refreshTable(empresas);
			}

		}else if(e.getSource() == deleteButton) {
			buscarField.setText("");
			parent.refreshTable();
		}
	}

}
