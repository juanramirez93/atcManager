package com.atc.ui.seguros.producto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.atc.app.FrameAbstract;
import com.atc.model.Producto;
import com.atc.service.seguros.producto.ProductoMainService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class MainProducto extends FrameAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7217255001078247937L;
	private ProductoMainService productoMainService;
	private TableProducto mainTable;
	private SearchProducto searchPanel;
	private JPanel optionPanel;
	private JButton ingresarButton;
	private JButton atrasButton;
	private JButton detallesButton;
	private JButton editarButton;

	public MainProducto() {
		super(StringsConstants.MAINPRODUCTO_TITULO);
		initialize();
		initializeVariables();
		constructLayout();
		refreshTable();
		setVisible(true);
	}

	public void refreshTable() {
		List<Producto> productos = this.productoMainService.getAllPolizas();
		this.mainTable.setTableModel(productos);
		mainTable.updateTable();
	}

	public void refreshTable(List<Producto> productos) {
		this.mainTable.setTableModel(productos);
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
		this.productoMainService = new ProductoMainService();
		this.mainTable = new TableProducto();
		this.optionPanel = new JPanel();
		this.ingresarButton = new JButton(StringsConstants.AGREGAR_PRODUCTO);
		this.ingresarButton.addActionListener(this);
		this.atrasButton = new JButton(StringsConstants.BACK);
		this.atrasButton.addActionListener(this);
		this.detallesButton = new JButton(StringsConstants.VER_DETALLE);
		this.detallesButton.addActionListener(this);
		this.editarButton = new JButton(StringsConstants.EDITAR);
		this.editarButton.addActionListener(this);
		this.searchPanel = new SearchProducto(this);
	}

	private void initialize() {
		setSize(NumberConstants.MAINPRODUCTO_WIDTH, NumberConstants.MAINPRODUCTO_HEIGHT);
		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.ingresarButton) {
			if (userSession.getPermisos().getProducto() % AGREGAR == 0) {
				new AddProducto(this);
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		} else if (event.getSource() == this.atrasButton) {
			setVisible(false);
		} else if (event.getSource() == this.detallesButton) {
			if (userSession.getPermisos().getProducto() % DETALLE == 0) {
				if (mainTable.getSelected() == null) {
					JOptionPane.showMessageDialog(this, StringsConstants.SELECT_REGISTER);
				} else {
					new DetailProducto(this, mainTable.getSelected());
				}
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		} else if (event.getSource() == this.editarButton) {
			if (userSession.getPermisos().getProducto() % EDITAR == 0) {
				if (mainTable.getSelected() == null) {
					JOptionPane.showMessageDialog(this, StringsConstants.SELECT_REGISTER);
				} else {
					new EditProducto(this, mainTable.getSelected());
				}
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		}
	}

}
