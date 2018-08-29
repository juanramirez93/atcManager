package com.atc.reports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.atc.model.Empleado;
import com.atc.util.StringsConstants;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class SegurosReports {

	private String ruta = StringsConstants.RUTA_REPORTE;

	public void reporteReintegroRCE(Empleado empleado, int mes, int anio) throws JRException {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Empleado", empleado.getId());
		parametros.put("Titulo", StringsConstants.TITULO_REINEGRO_RCE);
		parametros.put("mesReporte", mes);
		parametros.put("anioReporte", anio);
		parametros.put("mesString", StringsConstants.getMonth(mes));
		
		String file = ruta + "Reintegro RCE.jasper";
		JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(file);

		JasperPrint pr = JasperFillManager.fillReport(report, parametros, conectar());
		JasperViewer jasperViewer = new JasperViewer(pr, false);
		jasperViewer.setVisible(true);

//		JasperExportManager.exportReportToPdfFile(pr, ruta + "ReintRce" + empleado.getAbreviatura() + mes + "-" + anio);

	}
	
	public void reporteRCE(int mes, int anio) throws JRException {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("mesReporte", mes);
		parametros.put("anioReporte", anio);
		parametros.put("mesString", StringsConstants.getMonth(mes));
		
		String file = ruta + "Reporte RCE.jasper";
		JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(file);

		JasperPrint pr = JasperFillManager.fillReport(report, parametros, conectar());
		JasperViewer jasperViewer = new JasperViewer(pr, false);
		jasperViewer.setVisible(true);

//		JasperExportManager.exportReportToPdfFile(pr, ruta + "ReintRce" + empleado.getAbreviatura() + mes + "-" + anio);

	}

	public Connection conectar() {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://192.168.10.75:3306/atc?serverTimezone=UTC", "atc",
					"Metallica.91");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conexion;
	}
}
