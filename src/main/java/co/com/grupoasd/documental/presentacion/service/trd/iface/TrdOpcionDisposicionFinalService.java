/**
 * 
 */
package co.com.grupoasd.documental.presentacion.service.trd.iface;

import java.util.List;

import co.com.grupoasd.documental.cliente.trd.model.TrdOpcionDisposicionFinal;

/**
 * Interface TrdOpcionDisposicionFinal
 * @author alopez
 * @since Junio 15 de 2017
 */
public interface TrdOpcionDisposicionFinalService {

	/**
	 * Obtiene las opciones de disposición final
	 * @param empresaId Id de empresa
	 * @param isEstado Estado de la opcion, true inactivo, false en caso contrario
	 * @return Listado de opciones
	 */
	List<TrdOpcionDisposicionFinal> listarPorEmpresaAndEstado(Integer empresaId, boolean isEstado);
}
