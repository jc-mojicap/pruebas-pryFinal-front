/**
 * 
 */
package co.com.grupoasd.documental.cliente.trd;

import java.util.List;

import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.trd.iface.TrdOpcionDisposicionFinalRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdOpcionDisposicionFinal;

/**
 * Proxy TrdOpcionDisposicionFinal
 * @author alopez
 * @since Junio 15 de 2017
 */
public final class TrdOpcionDisposicionFinalProxyService {
	
	/**
	 * Obtiene las opciones de disposición final
	 * @param empresaId Id de empresa
	 * @param isEstado Estado de la opcion, true inactivo, false en caso contrario
	 * @return Listado de opciones
	 */
	public static List<TrdOpcionDisposicionFinal> listarPorEmpresaAndEstado(Integer empresaId, boolean isEstado) {
		return ServiciosEndpoint.get().proxy(TrdOpcionDisposicionFinalRestIface.class).listarPorEmpresaAndEstado(empresaId, isEstado);
	}
}
