package com.atc.ui.seguros.producto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.atc.app.DialogAbstract;
import com.atc.model.AAA;
import com.atc.model.Camionera;
import com.atc.model.Integral;
import com.atc.model.Producto;
import com.atc.model.RCEProduct;
import com.atc.model.RCEyTransporte;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class DetailProducto extends DialogAbstract implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7203535858817047533L;

	private JTextArea datos;

	private JPanel dataPanel;
	private JPanel buttonPanel;

	private Producto producto;

	private MainProducto parent;

	private JButton atrasButton;

	public DetailProducto(MainProducto parent, Producto producto) {
		super(parent, StringsConstants.DETAILPRODUCTO_TITULO);
		this.parent = parent;
		this.producto = producto;
		initializeVariables();
		initializaLayout();
		setVisible(true);
	}

	private void initializaLayout() {
		BorderLayout layout = new BorderLayout();

		setLayout(layout);
		setSize(NumberConstants.DETAILPRODUCTO_WIDTH, NumberConstants.DETAILPRODUCTO_HEIGHT);
		setLocationRelativeTo(parent);
		setDataPanel();
		setButtonPanel();
	}

	private void setButtonPanel() {
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(atrasButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void setDataPanel() {
		BorderLayout layout = new BorderLayout();

		dataPanel.setLayout(layout);
		dataPanel.setBorder(BorderFactory.createTitledBorder(StringsConstants.DATOS));
		String datosStr = "";

		datosStr += StringsConstants.EMPRESA_NIT + ": " + formatID.format(producto.getEmpresa().getNit()) + "\n";
		datosStr += StringsConstants.EMPRESA_RAZON_SOCIAL + ": " + producto.getEmpresa().getRazonSocial() + "\n";
		datosStr += StringsConstants.EMPRESA_SIGLAS + ": " + producto.getEmpresa().getSiglas() + "\n";
		datosStr += StringsConstants.PRODUCTO_MODALIDAD + ": " + producto.getModalidad() + "\n";
		datosStr += StringsConstants.PRODUCTO_URBAN_VEHICLES + ": " + producto.getUrbanos() + "\n";
		datosStr += StringsConstants.PRODUCTO_TIPO + ": " + producto.getTipo() + "\n";

		if (producto.getTipo().equals("AAA")) {
			AAA aaa = (AAA) producto;
			datosStr += StringsConstants.PRODUCTO_RCE + ": " + aaa.getRce().getNumero() + "\n";
			datosStr += StringsConstants.PRODUCTO_TRANSPORTE + ": " + aaa.getTransporte().getNumero() + "\n";
			datosStr += StringsConstants.PRODUCTO_VIDA + ": " + aaa.getVida().getNumero() + "\n";
			datosStr += StringsConstants.PRODUCTO_VENTA_VIDA + ": " + formatMoney.format(aaa.getValorVentaVida())
					+ "\n";
			datosStr += StringsConstants.PRODUCTO_URBANO_VENTA_VIDA + ": "
					+ formatMoney.format(aaa.getValorVentaUrbanosVida()) + "\n";
		} else if (producto.getTipo().equals("Integral")) {
			Integral integral = (Integral) producto;
			datosStr += StringsConstants.PRODUCTO_RCE + ": " + integral.getRce().getNumero() + "\n";
			datosStr += StringsConstants.PRODUCTO_VIDA + ": " + integral.getVida().getNumero() + "\n";
			datosStr += StringsConstants.PRODUCTO_VENTA_VIDA + ": " + formatMoney.format(integral.getValorVentaVida())
					+ "\n";
			datosStr += StringsConstants.PRODUCTO_URBANO_VENTA_VIDA + ": "
					+ formatMoney.format(integral.getValorVentaUrbanosVida()) + "\n";
		} else if (producto.getTipo().equals("Camionera Vida")) {
			Camionera camionera = (Camionera) producto;
			datosStr += StringsConstants.PRODUCTO_VIDA + ": " + camionera.getVida().getNumero() + "\n";
			datosStr += StringsConstants.PRODUCTO_VENTA_VIDA + ": " + formatMoney.format(camionera.getValorVentaVida())
					+ "\n";
			datosStr += StringsConstants.PRODUCTO_URBANO_VENTA_VIDA + ": "
					+ formatMoney.format(camionera.getValorVentaUrbanosVida()) + "\n";
		} else if (producto.getTipo().equals("RCE")) {
			RCEProduct rce = (RCEProduct) producto;
			datosStr += StringsConstants.PRODUCTO_RCE + ": " + rce.getRce().getNumero() + "\n";
		} else if (producto.getTipo().equals("RCE y Transporte")) {
			RCEyTransporte transporte = (RCEyTransporte) producto;
			datosStr += StringsConstants.PRODUCTO_RCE + ": " + transporte.getRce().getNumero() + "\n";
			datosStr += StringsConstants.PRODUCTO_TRANSPORTE + ": " + transporte.getTransporte().getNumero() + "\n";
		}
		if (producto.getUrbanoRCE() == 1) {
			datosStr += "Urbanos RCE: Si \n";
		}

		if (producto.getUrbanoVida() == 1) {
			datosStr += "Urbanos Vida: Si \n";
		}

		datos.setText(datosStr);
		dataPanel.add(datos, BorderLayout.CENTER);
		add(dataPanel, BorderLayout.CENTER);

	}

	private void initializeVariables() {
		dataPanel = new JPanel();
		buttonPanel = new JPanel();
		datos = new JTextArea();
		datos.setEditable(false);
		atrasButton = new JButton(StringsConstants.BACK);
		atrasButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.atrasButton) {
			setVisible(false);
		}
	}
}
