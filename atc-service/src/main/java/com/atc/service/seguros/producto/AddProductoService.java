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
import com.atc.model.RCE;
import com.atc.model.RCEProduct;
import com.atc.model.RCEyTransporte;
import com.atc.model.Transporte;
import com.atc.model.Vida;

public class AddProductoService {

	private EmpresaEM empresaEM;
	private PolizaEM polizaEM;
	private ProductoEM productoEM;
	private EmpleadoEM empleadoEM;

	public AddProductoService() {
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

	public void saveProducto(long empresa, String tipo, String modalidad, long rce, long vida, long transporte,
			int nroUrbanos, int valorVenta, int valorUrbanos, int RCEUrbanos, int vidaUrbanos) {
		if (tipo == "AAA") {
			AAA producto = new AAA();
			producto.setEmpresa(empresaEM.getEmpresaByNit(empresa));
			producto.setModalidad(modalidad);
			producto.setTipo(tipo);
			producto.setUrbanos(nroUrbanos);
			producto.setRce((RCE)polizaEM.getPolizaByNumero(rce));
			producto.setTransporte((Transporte)polizaEM.getPolizaByNumero(transporte));
			producto.setVida((Vida)polizaEM.getPolizaByNumero(vida));
			producto.setValorVentaVida(valorVenta);
			producto.setValorVentaUrbanosVida(valorUrbanos);
			producto.setUrbanoVida(vidaUrbanos);
			producto.setUrbanoRCE(RCEUrbanos);
			productoEM.save(producto);
		}else if(tipo == "RCE y Transporte"){
			RCEyTransporte producto = new RCEyTransporte();
			producto.setEmpresa(empresaEM.getEmpresaByNit(empresa));
			producto.setModalidad(modalidad);
			producto.setTipo(tipo);
			producto.setUrbanos(nroUrbanos);
			producto.setRce((RCE)polizaEM.getPolizaByNumero(rce));
			producto.setTransporte((Transporte)polizaEM.getPolizaByNumero(transporte));
			producto.setUrbanoVida(vidaUrbanos);
			producto.setUrbanoRCE(RCEUrbanos);
			productoEM.save(producto);
		}else if(tipo == "RCE"){
			RCEProduct producto = new RCEProduct();
			producto.setEmpresa(empresaEM.getEmpresaByNit(empresa));
			producto.setModalidad(modalidad);
			producto.setTipo(tipo);
			producto.setUrbanos(nroUrbanos);
			producto.setRce((RCE)polizaEM.getPolizaByNumero(rce));
			producto.setUrbanoVida(vidaUrbanos);
			producto.setUrbanoRCE(RCEUrbanos);
			productoEM.save(producto);
		}else if(tipo == "Camionera Vida"){
			Camionera producto = new Camionera();
			producto.setEmpresa(empresaEM.getEmpresaByNit(empresa));
			producto.setModalidad(modalidad);
			producto.setTipo(tipo);
			producto.setUrbanos(nroUrbanos);
			producto.setVida((Vida)polizaEM.getPolizaByNumero(vida));
			producto.setValorVentaVida(valorVenta);
			producto.setValorVentaUrbanosVida(valorUrbanos);
			producto.setUrbanoVida(vidaUrbanos);
			producto.setUrbanoRCE(RCEUrbanos);
			productoEM.save(producto);
		}else if(tipo == "Integral"){
			Integral producto = new Integral();
			producto.setEmpresa(empresaEM.getEmpresaByNit(empresa));
			producto.setModalidad(modalidad);
			producto.setTipo(tipo);
			producto.setUrbanos(nroUrbanos);
			producto.setRce((RCE)polizaEM.getPolizaByNumero(rce));
			producto.setVida((Vida)polizaEM.getPolizaByNumero(vida));
			producto.setValorVentaVida(valorVenta);
			producto.setValorVentaUrbanosVida(valorUrbanos);
			producto.setUrbanoVida(vidaUrbanos);
			producto.setUrbanoRCE(RCEUrbanos);
			productoEM.save(producto);			
		}
	}

}
