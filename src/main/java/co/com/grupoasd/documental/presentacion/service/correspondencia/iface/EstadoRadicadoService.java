package co.com.grupoasd.documental.presentacion.service.correspondencia.iface;

import java.util.List;

import co.com.grupoasd.documental.cliente.correspondencia.model.EstadoRadicado;

/**
 * Servicio del recurso estado_radicado
 * @author adrian.lopez
 *
 */
public interface EstadoRadicadoService {
	
	/**
	 * Lista los diferentes estados que puede tener un radicado
	 * @return Lista de estadoRadicado
	 */
	List<EstadoRadicado> listar();
}
