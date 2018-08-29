package com.atc.ui.seguros.movimiento;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.atc.app.DialogAbstract;
import com.atc.model.Movimiento;
import com.atc.model.Producto;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class DetailMovimiento extends DialogAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8233555006672143334L;

	private JTextArea datos;

	private JPanel dataPanel;
	private JPanel buttonPanel;

	private Movimiento movimiento;

	private MainMovimiento parent;

	private JButton atrasButton;

	public DetailMovimiento(MainMovimiento parent, Movimiento movimiento) {
		super(parent, StringsConstants.DETAILMOVIMIENTO_TITULO);
		this.parent = parent;
		this.movimiento = movimiento;
		initializeVariables();
		initializaLayout();
		setVisible(true);
	}

	private void initializaLayout() {
		BorderLayout layout = new BorderLayout();

		setLayout(layout);
		setSize(NumberConstants.DETAILMOVIMIENTO_WIDTH, NumberConstants.DETAILMOVIMIENTO_HEIGHT);
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

		datosStr += StringsConstants.MOVIMIENTO_FECHA + ": " + formatDate.format(movimiento.getFecha()) + "\n";

		Producto prod = movimiento.getProducto();

		datosStr += StringsConstants.EMPRESA_NIT + ": " + movimiento.getProducto().getEmpresa().getNit() + "\n";
		datosStr += StringsConstants.EMPRESA_RAZON_SOCIAL + ": "
				+ movimiento.getProducto().getEmpresa().getRazonSocial() + "\n";
		datosStr += StringsConstants.MOVIMIENTO_CANTIDAD + ": " + movimiento.getCantidad() + "\n";

		if (prod.getModalidad().equals("REPORTE") || prod.getUrbanos() > 0) {
			datosStr += StringsConstants.MOVIMIENTO_FECHA_REPORTE + ": "
					+ StringsConstants.getMonth(movimiento.getMesFechaReporte()) + "/"
					+ movimiento.getAnioFechaReporte() + "\n";
		} else {
			datosStr += StringsConstants.MOVIMIENTO_NUMERACION + ": " + movimiento.getNumeracion() + "\n";

		}

		if (prod.getTipo().equals("AAA")) {
			datosStr += StringsConstants.MOVIMIENTO_FAC_VIDA + ": " + movimiento.getFactVida() + "\n";
			datosStr += StringsConstants.MOVIMIENTO_RECIBO + ": " + movimiento.getReciboPago() + "\n";
			if (movimiento.getPagoVida() != null) {
				datosStr += StringsConstants.MOVIMIENTO_PAGO_VIDA + ": " + formatDate.format(movimiento.getPagoVida())
						+ "\n";
			}

			datosStr += StringsConstants.MOVIMIENTO_REM_RCE + ": " + movimiento.getRemRCE() + "\n";
			datosStr += StringsConstants.MOVIMIENTO_FAC_RCE + ": " + movimiento.getFactAllianzRCE() + "\n";
			if (movimiento.getPagoRCE() != null) {
				datosStr += StringsConstants.MOVIMIENTO_PAGO_RCE + ": " + formatDate.format(movimiento.getPagoRCE())
						+ "\n";
			}
			datosStr += StringsConstants.MOVIMIENTO_REM_TRANSPORTE + ": " + movimiento.getRemTransporte() + "\n";
			datosStr += StringsConstants.MOVIMIENTO_FAC_TRANSPORTE + ": " + movimiento.getFactAllianzTransporte()
					+ "\n";
			if (movimiento.getPagoTransporte() != null) {
				datosStr += StringsConstants.MOVIMIENTO_PAGO_TRANSPORTE + ": "
						+ formatDate.format(movimiento.getPagoTransporte()) + "\n";
			}
		} else if (prod.getTipo().equals("Integral")) {
			datosStr += StringsConstants.MOVIMIENTO_FAC_VIDA + ": " + movimiento.getFactVida() + "\n";
			datosStr += StringsConstants.MOVIMIENTO_RECIBO + ": " + movimiento.getReciboPago() + "\n";
			if (movimiento.getPagoVida() != null) {
				datosStr += StringsConstants.MOVIMIENTO_PAGO_VIDA + ": " + formatDate.format(movimiento.getPagoVida())
						+ "\n";
			}

			datosStr += StringsConstants.MOVIMIENTO_REM_RCE + ": " + movimiento.getRemRCE() + "\n";
			datosStr += StringsConstants.MOVIMIENTO_FAC_RCE + ": " + movimiento.getFactAllianzRCE() + "\n";
			if (movimiento.getPagoRCE() != null) {
				datosStr += StringsConstants.MOVIMIENTO_PAGO_RCE + ": " + formatDate.format(movimiento.getPagoRCE())
						+ "\n";
			}
		} else if (prod.getTipo().equals("RCE y Transporte")) {
			datosStr += StringsConstants.MOVIMIENTO_REM_RCE + ": " + movimiento.getRemRCE() + "\n";
			datosStr += StringsConstants.MOVIMIENTO_FAC_RCE + ": " + movimiento.getFactAllianzRCE() + "\n";
			if (movimiento.getPagoRCE() != null) {
				datosStr += StringsConstants.MOVIMIENTO_PAGO_RCE + ": " + formatDate.format(movimiento.getPagoRCE())
						+ "\n";
			}
			datosStr += StringsConstants.MOVIMIENTO_REM_TRANSPORTE + ": " + movimiento.getRemTransporte() + "\n";
			datosStr += StringsConstants.MOVIMIENTO_FAC_TRANSPORTE + ": " + movimiento.getFactAllianzTransporte()
					+ "\n";
			if (movimiento.getPagoTransporte() != null) {
				datosStr += StringsConstants.MOVIMIENTO_PAGO_TRANSPORTE + ": "
						+ formatDate.format(movimiento.getPagoTransporte()) + "\n";
			}
		} else if (prod.getTipo().equals("Camionera Vida")) {
			datosStr += StringsConstants.MOVIMIENTO_FAC_VIDA + ": " + movimiento.getFactVida() + "\n";
			datosStr += StringsConstants.MOVIMIENTO_RECIBO + ": " + movimiento.getReciboPago() + "\n";
			if (movimiento.getPagoVida() != null) {
				datosStr += StringsConstants.MOVIMIENTO_PAGO_VIDA + ": " + formatDate.format(movimiento.getPagoVida())
						+ "\n";
			}
		} else if (prod.getTipo().equals("RCE")) {
			datosStr += StringsConstants.MOVIMIENTO_FAC_VIDA + ": " + movimiento.getFactVida() + "\n";
			datosStr += StringsConstants.MOVIMIENTO_RECIBO + ": " + movimiento.getReciboPago() + "\n";
			if (movimiento.getPagoVida() != null) {
				datosStr += StringsConstants.MOVIMIENTO_PAGO_VIDA + ": " + formatDate.format(movimiento.getPagoVida())
						+ "\n";
			}
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
