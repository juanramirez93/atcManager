package com.atc.util;

public class NumberConstants {

	public static final int MAINEMPLEADOS_WIDTH = 600;
	public static final int MAINEMPLEADOS_HEIGHT = 600;
	public static final int COLUMNS_TABLE_EMPLEADO = 3;
	public static final int COLUMNS_MAIN_TABLE_EMPRESA = 2;
	public static final int ADDCOMPANY_HEIGHT = 400;
	public static final int ADDCOMPANY_WIDTH = 500;
	public static final int ADDCOMPANY_FIELD = 15;
	public static final int MAIN_WIDTH = 400;
	public static final int MAIN_HEIGHT = 550;
	public static final int WAIT_DIALOG_WIDHT = 200;
	public static final int WAIT_DIALOG_HEIGHT = 100;
	public static final int MAINSEGUROS_WIDTH = 300;
	public static final int MAINSEGUROS_HEIGHT = 300;
	public static final int COLUMNS_TABLE_POLIZA = 5;
	public static final int ADDPOLIZA_FIELD = 15;
	public static final int ADDPOLIZA_WIDTH = 450;
	public static final int ADDPOLIZA_HEIGHT = 300;
	public static final int COLUMNS_TABLE_PRODUCTO = 3;
	public static final int MAINPRODUCTO_WIDTH = 500;
	public static final int MAINPRODUCTO_HEIGHT = 500;
	public static final int ADDPRODUCTO_FIELD = 15;
	public static final int DETAILPOLIZA_HEIGHT = 225;
	public static final int DETAILPOLIZA_WIDTH = 250;
	public static final int ADDPRODUCTO_WIDTH = 450;
	public static final int ADDPRODUCTO_HEIGHT = 450;
	public static final int MAINPOLIZA_WIDTH = 500;
	public static final int MAINPOLIZA_HEIGHT = 500;
	public static final int DETAILPRODUCTO_WIDTH = 500;
	public static final int DETAILPRODUCTO_HEIGHT = 350;
	public static final int DETAILEMPRESA_WIDTH = 300;
	public static final int DETAILEMPRESA_HEIGHT = 500;
	public static final int ADDEMPLEADO_WIDTH = 350;
	public static final int ADDEMPLEADO_HEIGHT = 200;
	public static final int ADDEMPLEADO_FIELD = 15;
	public static final int COLUMNS_TABLE_USUARIO = 3;
	public static final int ADDUSUARIO_WIDTH = 400;
	public static final int ADDUSUARIO_HEIGHT = 300;
	public static final int ADDUSUARIO_FIELD = 15;
	public static final int DETAILUSUARIO_WIDTH = 200;
	public static final int DETAILUSUARIO_HEIGHT = 400;
	public static final int AUTH_LENGTH = 10;
	public static final int AUTH_HEIGHT = 130;
	public static final int AUTH_WIDTH = 400;
	public static final int COLUMNS_TABLE_MOVIMIENTO = 8;
	public static final int ADDMOVIMIENTO_WIDTH = 450;
	public static final int ADDMOVIMIENTO_HEIGHT = 600;
	public static final int ADDMOVIMIENTO_FIELD = 10;
	public static final int MAINMOVIMIENTO_WIDTH = 1100;
	public static final int MAINMOVIMIENTO_HEIGHT = 600;
	public static final int COLUMNS_TABLE_STOCK = 6;
	public static final int ADDSTOCK_FIELD = 10;
	public static final int ADDSTOCK_WIDTH = 350;
	public static final int ADDSTOCK_HEIGHT = 250;
	public static final int MAINSTOCK_WIDTH = 500;
	public static final int MAINSTOCK_HEIGHT = 200;
	public static final int VER_REGISTRO_WIDTH = 300;
	public static final int VER_REGISTRO_HEIGHT = 100;
	public static final int VER_REPORTE_WIDTH = 300;
	public static final int VER_REPORTE_HEIGHT = 100;
	public static final int EDITMOVIMIENTO_WIDTH = 450;
	public static final int EDITMOVIMIENTO_HEIGHT = 600;
	public static final int DETAILMOVIMIENTO_WIDTH = 400;
	public static final int DETAILMOVIMIENTO_HEIGHT = 300;
	public static final int ADDNATURAL_WIDTH = 300;
	public static final int ADDNATURAL_HEIGHT = 400;
	public static final int ADDNATURAL_FIELD = 10;
	public static final int DETAILNATURAL_WIDTH = 400;
	public static final int DETAILNATURAL_HEIGHT = 260;
	public static final int MAINNATURAL_WIDTH = 600;
	public static final int MAINNATURAL_HEIGHT = 400;
	public static final int COLUMNS_MAIN_TABLE_NATURAL = 3;
	public static final int COLUMNS_TABLE_AFILIACION = 4;
	public static final int ADDAFILIADO_FIELD = 12;
	public static final int MAINAFILIACIONES_WIDTH = 700;
	public static final int MAINAFILIACIONES_HEIGHT = 500;
	public static final int ADDAFILIACION_WIDTH = 400;
	public static final int ADDAFILIACION_HEIGHT = 420;
	public static final int EDITAFILIACION_WIDTH = 400;
	public static final int EDITAFILIACION_HEIGHT = 300;
	public static final int COLUMNS_TABLE_PAGOS_AFILIACION = 6;
	public static final int MAINPAGOSAFILIACIONES_WIDTH = 600;
	public static final int MAINPAGOSAFILIACIONES_HEIGHT = 200;
	public static final int ADDPAGOAFILIACION_WIDTH = 500;
	public static final int ADDPAGOAFILIACION_HEIGHT = 300;
	public static final int ADDPAGOAFILIADO_FIELD = 10;
	public static final int EDITAFILIADO_FIELD = 12;
	public static final int PASS_WIDTH = 200;
	public static final int PASS_HEIGHT = 200;
	public static final int DETAILAFILIADO_WIDTH = 350;
	public static final int DETAILAFILIADO_HEIGHT = 300;

	public static boolean isNumber(String str) {
		boolean resultado;

		try {
			Long.parseLong(str);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}

		return resultado;
	}

	public static boolean isDigit(String str) {
		boolean resultado;
		if (str.length() == 1) {
			resultado = isNumber(str);
		} else {
			resultado = false;
		}
		return resultado;
	}

}
