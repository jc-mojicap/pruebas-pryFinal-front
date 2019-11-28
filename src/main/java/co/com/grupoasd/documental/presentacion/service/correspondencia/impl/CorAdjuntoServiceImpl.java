/**
 * 
 */
package co.com.grupoasd.documental.presentacion.service.correspondencia.impl;

import co.com.grupoasd.documental.cliente.correspondencia.CorAdjuntoProxyService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorAdjuntoService;

/**
 * Servicio adjuntos
 * @author alopez
 * @since Junio 16 de 2017
 */
public class CorAdjuntoServiceImpl implements CorAdjuntoService {

	@Override
	public byte[] obtenerBytesAdjunto(Long radicadoId, Integer adjuntoId) {
		return CorAdjuntoProxyService.obtenerBytesAdjunto(radicadoId, adjuntoId);
	}

}
