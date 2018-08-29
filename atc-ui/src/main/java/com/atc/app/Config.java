package com.atc.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class Config {

	// fichero TMP
	private String appPath = System.getProperties().getProperty("user.dir");
	private File fichero = new File(appPath + "\\config.atc");
	// tiempo en que se actualiza el fichero TMP
	private int segundos = 20;

	/** Constructor de clase */
	public Config() {
	};

	/**
	 * Comprueba que archivo TMP exista, sino lo crea e inicia valores
	 */
	public String comprobar() {
		if (!fichero.exists()) {
			crearTMP();
		} 
		return leer(); 
	}

	/**
	 * Lee el archivo TMP y retorna su valor
	 * 
	 * @return LONG cantidad de milisegundos
	 */
	public String leer() {
		String linea = "0";
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(fichero));
			while (bufferedReader.ready()) {
				linea = bufferedReader.readLine();
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return linea;
	}

	/**
	 * Programa un proceso que se repite cada cierto tiempo
	 */
	public void programar_tarea() {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new Runnable() {
			public void run() {
				crearTMP();
			}
		}, 1000, segundos * 1000, TimeUnit.MILLISECONDS); // comienza dentro de 1 segundo y luego se repite cada N
															// segundos

	}

	/**
	 * Crea un archivo TMP con un unico valor, el tiempo en milisegundos
	 */
	public void crearTMP() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			int returnVal = chooser.showSaveDialog(null);
			
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				writer.write(chooser.getSelectedFile().getPath());
			}
			
			writer.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Resta el tiempo expresado en milisegundos
	 * 
	 * @param tiempoActual el tiempo actual del sistema expresado en milisegundos
	 * @return tiempo el resultado expresado en segundos
	 */
	public long restarTiempo(long tiempoActual) {
		Date date = new Date();
		long tiempoTMP = date.getTime();
		long tiempo = tiempoTMP - tiempoActual;
		tiempo = tiempo / 1000;
		return tiempo;
	}

	/**
	 * Elimina el fichero TMP si es que existe
	 */
	public void cerrarApp() {
		if (fichero.exists()) {
			fichero.delete();
		}
		System.exit(0);
	}

}// --> fin clase
