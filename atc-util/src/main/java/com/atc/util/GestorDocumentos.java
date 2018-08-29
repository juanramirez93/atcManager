package com.atc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.atc.connection.Session;

public class GestorDocumentos {

	public void prueba() throws IOException {
		// RUTA DONDE SE ENCUENTRA EL DOCUMENTO .doc
		String fileName = Session.INSTANCE.getPathFolderDocs() + "/Documentos/PRUEBA.doc";
		// STRING QUE ALMACENARA EL CONTENIDO DE CADA PARRAFO DEL DOCUMENTO
		String linea = "";

		InputStream fis = new FileInputStream(fileName);
		POIFSFileSystem fs = new POIFSFileSystem(fis);
		HWPFDocument doc = new HWPFDocument(fs);

		// ASIGNA A LA VARIABLE range COMO VALOR EL RANGO DEL DOCUMENTO
		Range range = doc.getRange();

		// IMPRIME POR CONSOLA EL CONTENIDO DEL DOCUMENTO
		System.out.println(doc.getDocumentText());
		System.out.println("---------------------------");
		// IMPRIME POR CONSOLA NUMERO DE PARRAFOS
		System.out.println(range.numParagraphs());
		System.out.println("---------------------------");

		// BUCLE RECORRE EL DOCUMENTO POR PARRAFOS
		for (int i = 0; i < range.numParagraphs(); i++) {
			// CREA EL PARRAFO DEL DOCUMENTO
			Paragraph par = range.getParagraph(i);
			// PASAMOS EL PARRAFO EN EL QUE NOS ENCONTRAMOS A UN STRING (linea)
			linea = par.text().toString();

			// IMPRIME EL CONTENIDO DEL PARRAFO
			System.out.println(linea);

			// DEVUELVE true SI LA PALABRA (Hola) SE ENCUENTRA DENTRO DEL PARRAFO
			System.out.println(linea.indexOf("NOMBRE") >= 0);

			// SI EL PARRAFO CONTIENE (Hola) INDICA QUE AHÍ ESTÁ Y QUE CAMBIARÁ
			if (par.text().toString().indexOf("NOMBRE") >= 0) {
				System.out.print("          Aquí Cambiará!! --> ");

			}

			// REEMPLAZA (SI CONTIENE) LA PALABRA (Hola) DE DICHO PARRAFO, POR LA PALABRA
			// (Cambio)
			par.replaceText("NOMBRE", "Cambio");
			// DEBE IMPRIMIR POR PANTALLA EL PARRAFO MODIFICADO SI SE DIO EL CASO..
			System.out.println(par.text().toString());
			doc.write(new File(Session.INSTANCE.getPathFolderDocs() + "/Documentos/PRU.docx"));
		} // fin for

		// VOLVEMOS A IMPRIMIR EL DOCUMENTO POR CONSOLA Y COMPROBAMOS QUE SE HA
		// MODIFICADO
		System.out.println("---------------------------");
		System.out.println(doc.getDocumentText().toString());

	}

	public void prueba1() throws InvalidFormatException, IOException {
		XWPFDocument doc = new XWPFDocument(
				OPCPackage.open(Session.INSTANCE.getPathFolderDocs() + "/Documentos/PRUEBA.docx"));
		for (XWPFParagraph p : doc.getParagraphs()) {
			List<XWPFRun> runs = p.getRuns();
			if (runs != null) {
				for (XWPFRun r : runs) {
					String text = r.getText(0);
					if (text != null && text.contains("needle")) {
						text = text.replace("needle", "haystack");
						r.setText(text, 0);
					}
				}
			}
		}
	}

}
