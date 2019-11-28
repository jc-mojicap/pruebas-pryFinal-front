package co.com.grupoasd.documental.presentacion.service.correspondencia.impl;

import java.util.List;

import co.com.grupoasd.documental.cliente.correspondencia.EstadoRadicadoProxyService;
import co.com.grupoasd.documental.cliente.correspondencia.model.EstadoRadicado;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.EstadoRadicadoService;

public class EstadoRadicadoServiceImpl implements EstadoRadicadoService {

	/**
	 * Lista Los diferentes estado que puede tener un radicado
	 */
	@Override
	public List<EstadoRadicado> listar() {
		return EstadoRadicadoProxyService.listar();
	}

}
