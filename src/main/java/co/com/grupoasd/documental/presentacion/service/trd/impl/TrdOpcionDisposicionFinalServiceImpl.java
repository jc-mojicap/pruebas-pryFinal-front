/**
 * 
 */
package co.com.grupoasd.documental.presentacion.service.trd.impl;

import java.util.List;

import co.com.grupoasd.documental.cliente.trd.TrdOpcionDisposicionFinalProxyService;
import co.com.grupoasd.documental.cliente.trd.model.TrdOpcionDisposicionFinal;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdOpcionDisposicionFinalService;

/**
 * Implementacion servicio TrdOpcionDisposicionFinal
 * @author alopez
 * @since Junio 15 de 2017
 */
public class TrdOpcionDisposicionFinalServiceImpl implements TrdOpcionDisposicionFinalService {

	@Override
	public List<TrdOpcionDisposicionFinal> listarPorEmpresaAndEstado(Integer empresaId, boolean isEstado) {
		return TrdOpcionDisposicionFinalProxyService.listarPorEmpresaAndEstado(empresaId, isEstado);
	}

}
