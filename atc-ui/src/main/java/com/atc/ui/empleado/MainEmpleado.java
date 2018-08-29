package com.atc.ui.empleado;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.atc.app.FrameAbstract;
import com.atc.model.Empleado;
import com.atc.service.empleado.MainEmpleadoService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class MainEmpleado extends FrameAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -484022956571628166L;
	private MainEmpleadoService mainFrameService;
	private TableEmpleado mainTable;
	private JPanel optionPanel;
	private SearchEmpleado searchPanel;
	private JButton ingresarButton;
	private JButton atrasButton;
	private JButton detallesButton;
	private JButton editarButton;

	public MainEmpleado() {
		super(StringsConstants.MAINEMPLEADO_TITULO);
		initialize();
		initializeVariables();
		constructLayout();
		refreshTable();
		setVisible(true);
	}

	public void refreshTable() {
		List<Empleado> empleado = this.mainFrameService.getAllEmpleados();
		this.mainTable.setTableModel(empleado);
		mainTable.updateTable();
	}

	public void refreshTable(List<Empleado> empleados) {
		this.mainTable.setTableModel(empleados);
		mainTable.updateTable();
	}

	private void constructLayout() {
		setLayout(new BorderLayout());
		optionPanel.setLayout(new FlowLayout());
		optionPanel.add(atrasButton);
		optionPanel.add(detallesButton);
		optionPanel.add(editarButton);
		optionPanel.add(ingresarButton);
		add(mainTable, BorderLayout.CENTER);
		add(optionPanel, BorderLayout.SOUTH);
		add(searchPanel, BorderLayout.NORTH);
	}

	private void initializeVariables() {
		this.mainFrameService = new MainEmpleadoService();
		this.mainTable = new TableEmpleado();
		this.optionPanel = new JPanel();
		this.ingresarButton = new JButton(StringsConstants.AGREGAR_EMPLEADO);
		this.ingresarButton.addActionListener(this);
		this.atrasButton = new JButton(StringsConstants.BACK);
		this.atrasButton.addActionListener(this);
		this.detallesButton = new JButton(StringsConstants.VER_DETALLE);
		this.detallesButton.addActionListener(this);
		this.editarButton = new JButton(StringsConstants.EDITAR);
		this.editarButton.addActionListener(this);
		this.searchPanel = new SearchEmpleado(this);
	}

	private void initialize() {
		setSize(NumberConstants.MAINEMPLEADOS_WIDTH, NumberConstants.MAINEMPLEADOS_HEIGHT);
		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.ingresarButton) {
			if (userSession.getPermisos().getEmpleado() % AGREGAR == 0) {
				AddEmpleado addEmpleado = new AddEmpleado(this);
				addEmpleado.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		} else if (event.getSource() == this.atrasButton) {
			setVisible(false);
		} else if (event.getSource() == this.detallesButton) {
			if (userSession.getPermisos().getEmpleado() % DETALLE == 0) {
				Empleado empleado = mainTable.getSelected();
				if (empleado != null) {
					new DetailEmpleado(this, empleado);
				} else {
					JOptionPane.showMessageDialog(this, StringsConstants.SELECT_REGISTER);
				}
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		} else if (event.getSource() == this.editarButton) {
			if (userSession.getPermisos().getEmpleado() % EDITAR == 0) {
				Empleado empleado = mainTable.getSelected();
				if (empleado != null) {
					EditEmpleado editEmpleado = new EditEmpleado(this, empleado);
					editEmpleado.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, StringsConstants.SELECT_REGISTER);
				}
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		}
	}

}
