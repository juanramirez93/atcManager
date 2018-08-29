package com.atc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.atc.model.Afiliacion;
import com.atc.model.Empresa;
import com.atc.model.Natural;

public class Exportar {

	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow rowhead;
	private FileOutputStream fileOut;
	private File sv;
	private JFileChooser chooser;
	private FileNameExtensionFilter filter;

	public Exportar() {
		filter = new FileNameExtensionFilter("Excel Files", "xls", "csv");
		
	}

	public void afiliados(List<Afiliacion> afiliaciones) throws IOException {
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet("afiliados");
		rowhead = sheet.createRow((short) 0);
		rowhead.createCell((short) 0).setCellValue("Fecha Afiliación");
		rowhead.createCell((short) 1).setCellValue("Estado");
		rowhead.createCell((short) 2).setCellValue("Tipo Afiliado");
		rowhead.createCell((short) 3).setCellValue("Identificacion");
		rowhead.createCell((short) 4).setCellValue("Nombres");
		rowhead.createCell((short) 5).setCellValue("Celular");
		rowhead.createCell((short) 6).setCellValue("Ciudad");
		rowhead.createCell((short) 7).setCellValue("Vehículos de Carga");
		rowhead.createCell((short) 8).setCellValue("Placas/Carga");
		rowhead.createCell((short) 9).setCellValue("Vehículos particulares");
		rowhead.createCell((short) 10).setCellValue("Placas/Particulares");
		rowhead.createCell((short) 11).setCellValue("Fecha de Vencimiento");
		
		
		chooser = new JFileChooser();
		chooser.setFileFilter(filter);
		chooser.addChoosableFileFilter(filter);
		fileOut = null;
		int returnVal = chooser.showSaveDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {

			sv = new File(chooser.getSelectedFile() + ".xls");

			fileOut = new FileOutputStream(sv);

		} else {
			workbook.close();
			return;
		}
		int i = 1;
		
		for (Afiliacion afiliacion : afiliaciones) {
			HSSFRow row = sheet.createRow((short) i);

			row.createCell(0).setCellValue(afiliacion.getFechaAfiliacion());
			Date ultimo = afiliacion.getUltimoPago();
			if (ultimo != null) {
				if (ultimo.before(new Date())) {
					row.createCell(1).setCellValue("Inactivo");
				} else {
					row.createCell(1).setCellValue("Activo");
				}
			} else {
				row.createCell(1).setCellValue("Sin pagos registrados");
			}

			row.createCell(2).setCellValue(afiliacion.getTipoAfiliado());
			row.createCell(3).setCellValue(afiliacion.getAfiliado().getIdentificacion());
			row.createCell(4).setCellValue(afiliacion.getAfiliado().getNombre());
			row.createCell(7).setCellValue(afiliacion.getVehiculosCarga());
			row.createCell(8).setCellValue(afiliacion.getPlacasCarga());
			row.createCell(9).setCellValue(afiliacion.getVehiculosParticulares());
			row.createCell(10).setCellValue(afiliacion.getPlacasParticulares());
			row.createCell(11).setCellValue(afiliacion.getUltimoPago());

			if (afiliacion.getAfiliado().getTipo().equals("Empresa")) {
				Empresa empresa = (Empresa) afiliacion.getAfiliado();
				row.createCell(5).setCellValue(empresa.getCelular());	
				row.createCell(6).setCellValue(empresa.getCiudad());
			} else if (afiliacion.getAfiliado().getTipo().equals("Natural")) {
				Natural natural = (Natural) afiliacion.getAfiliado();
				row.createCell(5).setCellValue(natural.getCelular());	
				row.createCell(6).setCellValue(natural.getCiudad());
			}

			i++;

		}

		workbook.write(fileOut);
		fileOut.close();
		workbook.close();

	}

}
