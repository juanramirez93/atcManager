package com.atc.ui.seguros.movimiento;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.atc.app.FrameAbstract;
import com.atc.model.Movimiento;
import com.atc.service.seguros.movimiento.MovimientoMainService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class MainMovimiento extends FrameAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -983152261048372067L;
	private MovimientoMainService mainFrameService;
	private TableMovimiento mainTable;
	private JPanel optionPanel;
	private SearchMovimiento searchPanel;
	private JButton ingresarButton;
	private JButton atrasButton;
	private JButton detallesButton;
	private JButton editarButton;

	public MainMovimiento() {
		super(StringsConstants.MAINMOVIMIENTO_TITULO);
		initialize();
		initializeVariables();
		constructLayout();
		refreshTable();
		setVisible(true);
	}

	public void refreshTable() {
		List<Movimiento> usuarios = this.mainFrameService.getAllMovimientos();
		this.mainTable.setTableModel(usuarios);
		mainTable.updateTable();
	}

	public void refreshTable(List<Movimiento> movimientos) {
		this.mainTable.setTableModel(movimientos);
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
		this.mainFrameService = new MovimientoMainService();
		this.mainTable = new TableMovimiento();

		this.searchPanel = new SearchMovimiento(this);

		this.optionPanel = new JPanel();

		this.atrasButton = new JButton(StringsConstants.BACK);
		this.atrasButton.addActionListener(this);

		this.detallesButton = new JButton(StringsConstants.VER_DETALLE);
		this.detallesButton.addActionListener(this);

		this.editarButton = new JButton(StringsConstants.EDITAR);
		this.editarButton.addActionListener(this);

		this.ingresarButton = new JButton(StringsConstants.AGREGAR_MOVIMIENTO);
		this.ingresarButton.addActionListener(this);

	}

	private void initialize() {
		setSize(NumberConstants.MAINMOVIMIENTO_WIDTH, NumberConstants.MAINMOVIMIENTO_HEIGHT);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.ingresarButton) {
			if (userSession.getPermisos().getMovimiento() % AGREGAR == 0) {
				AddMovimiento addMovimiento = new AddMovimiento(this);
				addMovimiento.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		} else if (event.getSource() == this.atrasButton) {
			setVisible(false);
		} else if (event.getSource() == this.detallesButton) {
			if (userSession.getPermisos().getMovimiento() % DETALLE == 0) {
				Movimiento movimiento = mainTable.getSelected();
				if (movimiento != null) {
					new DetailMovimiento(this, movimiento);
				} else {
					JOptionPane.showMessageDialog(this, StringsConstants.SELECT_REGISTER);
				}
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		} else if (event.getSource() == this.editarButton) {
			if (userSession.getPermisos().getMovimiento() % EDITAR == 0) {
				Movimiento movimiento = mainTable.getSelected();
				if (movimiento != null) {
					EditMovimiento editMovimiento = new EditMovimiento(this, movimiento);
					editMovimiento.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, StringsConstants.SELECT_REGISTER);
				}
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		}
	}

}
