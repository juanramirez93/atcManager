package com.atc.ui.seguros.stock;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.atc.app.FrameAbstract;
import com.atc.model.Stock;
import com.atc.service.stock.MainStockService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class MainStock extends FrameAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3239018869132160909L;
	private MainStockService mainFrameService;
	private TableStock mainTable;
	private JPanel optionPanel;
	private JButton ingresarButton;
	private JButton atrasButton;
	private JButton editButton;

	public MainStock() {
		super(StringsConstants.MAINSTOCK_TITULO);
		initialize();
		initializeVariables();
		constructLayout();
		refreshTable();
		setVisible(true);
	}

	public void refreshTable() {
		List<Stock> inventarios = this.mainFrameService.getAllInventario();
		this.mainTable.setTableModel(inventarios);
		mainTable.updateTable();
	}

	public void refreshTable(List<Stock> inventario) {
		this.mainTable.setTableModel(inventario);
		mainTable.updateTable();
	}

	private void constructLayout() {
		setLayout(new BorderLayout());
		optionPanel.setLayout(new FlowLayout());
		optionPanel.add(atrasButton);
		optionPanel.add(editButton);
		optionPanel.add(ingresarButton);
		add(mainTable, BorderLayout.CENTER);
		add(optionPanel, BorderLayout.SOUTH);
	}

	private void initializeVariables() {
		this.mainFrameService = new MainStockService();
		this.mainTable = new TableStock();
		this.optionPanel = new JPanel();

		this.atrasButton = new JButton(StringsConstants.BACK);
		this.atrasButton.addActionListener(this);

		this.editButton = new JButton(StringsConstants.EDITAR);
		this.editButton.addActionListener(this);

		this.ingresarButton = new JButton(StringsConstants.AGREGAR_STOCK);
		this.ingresarButton.addActionListener(this);

	}

	private void initialize() {
		setSize(NumberConstants.MAINSTOCK_WIDTH, NumberConstants.MAINSTOCK_HEIGHT);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.ingresarButton) {
			if (userSession.getPermisos().getStock() % AGREGAR == 0) {
				AddStock addStock = new AddStock(this);
				addStock.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		} else if (event.getSource() == this.atrasButton) {
			setVisible(false);
		} else if (event.getSource() == this.editButton) {
			if (userSession.getPermisos().getStock() % AGREGAR == 0) {
				Stock stock = mainTable.getSelected();
				EditStock editStock = new EditStock(this, stock);
				editStock.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		}

	}

}
