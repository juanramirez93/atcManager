package com.atc.ui.seguros.reporte;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.atc.app.FrameAbstract;
import com.atc.reports.SegurosReports;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import net.sf.jasperreports.engine.JRException;

public class VerReporte extends FrameAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 916994551168770933L;
	JLabel mesLabel;
	JLabel anioLabel;

	JComboBox<String> reporteField;
	JMonthChooser mesField;
	JYearChooser anioField;
	JPanel mainPanel;
	JButton verButton;

	public VerReporte() {
		super(StringsConstants.VER_REP_SEGUROS);
		initializeVariables();
		initializeLayout();
		setVisible(true);
	}

	private void initializeLayout() {
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(reporteField);
		mainPanel.add(mesField);
		mainPanel.add(anioField);
		mainPanel.add(verButton);
		setLayout(new BorderLayout());
		add(mainPanel);
	}

	private void initializeVariables() {
		setSize(NumberConstants.VER_REPORTE_WIDTH, NumberConstants.VER_REPORTE_HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		mainPanel = new JPanel();

		verButton = new JButton(StringsConstants.VER_REP_SEGUROS);
		verButton.addActionListener(this);

		mesLabel = new JLabel(StringsConstants.REINTEGRO_MES_REPORTE);
		anioLabel = new JLabel(StringsConstants.REINTEGRO_ANIO_REPORTE);

		String[] tipos = { "RCE" };
		reporteField = new JComboBox<String>(tipos);
		mesField = new JMonthChooser();
		anioField = new JYearChooser();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == verButton) {
			SegurosReports sr = new SegurosReports();
			if (reporteField.getSelectedItem().equals("RCE")) {
				try {
					sr.reporteRCE(mesField.getMonth() + 1, anioField.getYear());
				} catch (JRException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
