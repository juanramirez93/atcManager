package com.atc.ui.seguros.reintegro;

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
import com.atc.model.Empleado;
import com.atc.reports.SegurosReports;
import com.atc.service.seguros.reintegro.VerReintegroService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import net.sf.jasperreports.engine.JRException;

public class VerReintegro extends FrameAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6351022332250901379L;
	JLabel mesLabel;
	JLabel anioLabel;

	JComboBox<String> reintegroField;
	JComboBox<Empleado> empleadoField;
	JMonthChooser mesField;
	JYearChooser anioField;
	JPanel mainPanel;
	JButton verButton;

	VerReintegroService service;

	public VerReintegro() {
		super(StringsConstants.VER_REINTEGRO_SEGUROS);
		initializeVariables();
		initializeLayout();
		setVisible(true);
	}

	private void initializeLayout() {
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(reintegroField);
		mainPanel.add(empleadoField);
		mainPanel.add(mesField);
		mainPanel.add(anioField);
		mainPanel.add(verButton);
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
		if (!userSession.getUser().equals("admin")) {
			empleadoField.setSelectedItem(userSession.getEmpleado());
			empleadoField.setEnabled(false);
		}
	}

	private void initializeVariables() {
		setSize(NumberConstants.VER_REGISTRO_WIDTH, NumberConstants.VER_REGISTRO_HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		service = new VerReintegroService();

		mainPanel = new JPanel();

		verButton = new JButton(StringsConstants.VER_REINTEGRO);
		verButton.addActionListener(this);

		mesLabel = new JLabel(StringsConstants.REINTEGRO_MES_REPORTE);
		anioLabel = new JLabel(StringsConstants.REINTEGRO_ANIO_REPORTE);

		String[] tipos = { "RCE" };
		reintegroField = new JComboBox<String>(tipos);
		empleadoField = new JComboBox<Empleado>(service.getAll());
		mesField = new JMonthChooser();
		anioField = new JYearChooser();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == verButton) {
			if (reintegroField.getSelectedItem().equals("RCE")) {
				SegurosReports sr = new SegurosReports();
				try {
					sr.reporteReintegroRCE((Empleado) empleadoField.getSelectedItem(), mesField.getMonth() + 1,
							anioField.getYear());
				} catch (JRException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
