package com.atc.service.seguros.producto;

import java.util.List;

import com.atc.connection.EmpleadoEM;
import com.atc.connection.EmpresaEM;
import com.atc.connection.PolizaEM;
import com.atc.connection.ProductoEM;
import com.atc.model.AAA;
import com.atc.model.Camionera;
import com.atc.model.Empleado;
import com.atc.model.Empresa;
import com.atc.model.Integral;
import com.atc.model.Poliza;
import com.atc.model.Producto;
import com.atc.model.RCE;
import com.atc.model.RCEProduct;
import com.atc.model.RCEyTransporte;
import com.atc.model.Transporte;
import com.atc.model.Vida;

public class EditProductoService {

	private EmpresaEM empresaEM;
	private PolizaEM polizaEM;
	private ProductoEM productoEM;
	private EmpleadoEM empleadoEM;

	public EditProductoService() {
		empresaEM = new EmpresaEM();
		polizaEM = new PolizaEM();
		productoEM = new ProductoEM();
		empleadoEM = new EmpleadoEM();
	}

	public boolean existEmpresa(long nit) {
		return empresaEM.exist(nit);
	}

	public String getEmpresa(long nit) {

		Empresa empresa = empresaEM.getEmpresaByNit(nit);
		if (empresa.getSiglas() != "") {
			return empresa.getSiglas();
		} else {
			return empresa.getRazonSocial();
		}
	}

	public Empleado[] getEmpleados() {
		List<Empleado> empleado = empleadoEM.getAll();
		Empleado[] empleadosArray = new Empleado[empleado.size()];
		empleadosArray = empleado.toArray(empleadosArray);
		return empleadosArray;
	}

	public boolean existRCE(long poliza) {

		if (polizaEM.getPolizaByNumero(poliza) != null) {
			Poliza pol = polizaEM.getPolizaByNumero(poliza);
			if (pol.getRamo().equals("RCE")) {
				return true;
			}
		}
		return false;
	}

	public boolean exist(long poliza) {
		if (polizaEM.getPolizaByNumero(poliza) != null) {
			return true;
		}
		return false;
	}

	public boolean existTransporte(long poliza) {
		if (polizaEM.getPolizaByNumero(poliza) != null) {
			Poliza pol = polizaEM.getPolizaByNumero(poliza);
			if (pol.getRamo().equals("Transporte")) {
				return true;
			}
		}
		return false;
	}

	public boolean existVida(long poliza) {
		if (polizaEM.getPolizaByNumero(poliza) != null) {
			Poliza pol = polizaEM.getPolizaByNumero(poliza);
			if (pol.getRamo().equals("Vida")) {
				return true;
			}
		}
		return false;
	}

	public void updateProducto(Producto producto, long empresa, String tipo, String modalidad, long rce, long vida,
			long transporte, int nroUrbanos, int valorVenta, int valorUrbanos, int RCEUrbanos, int vidaUrbanos) {
		if (tipo == "AAA") {
			AAA aaa = (AAA) producto;
			aaa.setEmpresa(empresaEM.getEmpresaByNit(empresa));
			aaa.setModalidad(modalidad);
			aaa.setTipo(tipo);
			aaa.setUrbanos(nroUrbanos);
			aaa.setRce((RCE) polizaEM.getPolizaByNumero(rce));
			aaa.setTransporte((Transporte) polizaEM.getPolizaByNumero(transporte));
			aaa.setVida((Vida) polizaEM.getPolizaByNumero(vida));
			aaa.setValorVentaVida(valorVenta);
			aaa.setValorVentaUrbanosVida(valorUrbanos);
			aaa.setUrbanoRCE(RCEUrbanos);
			aaa.setUrbanoVida(vidaUrbanos);
			productoEM.update(aaa);
		} else if (tipo == "RCE y Transporte") {
			RCEyTransporte rceytr = (RCEyTransporte) producto;
			rceytr.setEmpresa(empresaEM.getEmpresaByNit(empresa));
			rceytr.setModalidad(modalidad);
			rceytr.setTipo(tipo);
			rceytr.setUrbanos(nroUrbanos);
			rceytr.setRce((RCE) polizaEM.getPolizaByNumero(rce));
			rceytr.setTransporte((Transporte) polizaEM.getPolizaByNumero(transporte));
			rceytr.setUrbanoRCE(RCEUrbanos);
			productoEM.update(rceytr);
		} else if (tipo == "RCE") {
			RCEProduct rcep = (RCEProduct) producto;
			rcep.setEmpresa(empresaEM.getEmpresaByNit(empresa));
			rcep.setModalidad(modalidad);
			rcep.setTipo(tipo);
			rcep.setUrbanos(nroUrbanos);
			rcep.setRce((RCE) polizaEM.getPolizaByNumero(rce));
			rcep.setUrbanoRCE(RCEUrbanos);
			productoEM.update(rcep);
		} else if (tipo == "Camionera Vida") {
			Camionera camionera = (Camionera) producto;
			camionera.setEmpresa(empresaEM.getEmpresaByNit(empresa));
			camionera.setModalidad(modalidad);
			camionera.setTipo(tipo);
			camionera.setUrbanos(nroUrbanos);
			camionera.setVida((Vida) polizaEM.getPolizaByNumero(vida));
			camionera.setValorVentaVida(valorVenta);
			camionera.setValorVentaUrbanosVida(valorUrbanos);
			camionera.setUrbanoVida(vidaUrbanos);
			productoEM.update(camionera);
		} else if (tipo == "Integral") {
			Integral integral = (Integral) producto;
			integral.setEmpresa(empresaEM.getEmpresaByNit(empresa));
			integral.setModalidad(modalidad);
			integral.setTipo(tipo);
			integral.setUrbanos(nroUrbanos);
			integral.setRce((RCE) polizaEM.getPolizaByNumero(rce));
			integral.setVida((Vida) polizaEM.getPolizaByNumero(vida));
			integral.setValorVentaVida(valorVenta);
			integral.setValorVentaUrbanosVida(valorUrbanos);
			integral.setUrbanoRCE(RCEUrbanos);
			integral.setUrbanoVida(vidaUrbanos);
			productoEM.update(integral);
		}
	}

}
