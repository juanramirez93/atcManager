package com.atc.util;

public class Utils {

	public Utils() {
	}

	public static String calcularDigitoVerificacion(long cadena) {
		int dcParcial = 0;
		int[] tablaPesos = { 3, 7, 13, 17, 19, 23, 29, 37, 41 };
		int suma = 0;
		int p = 0;
		int i;

		for (i = 0; i < 9; i++) {
			p = (int) cadena % 10;
			cadena = cadena / 10;
			p = p * tablaPesos[i];
			suma = suma + p;
		}
		dcParcial = suma % 11;
		if (dcParcial == 0 || dcParcial == 1) {
			return String.valueOf(dcParcial);
		} else {
			return String.valueOf(11 - dcParcial);
		}
	}

	
}
