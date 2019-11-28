package co.com.grupoasd.documental.cliente.trd.iface;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.trd.model.TrdOpcionDisposicionFinal;

/**
 * Consumo de servicios TrdOpcionDisposicionFinal
 * @author alopez
 * @since Junio 15 de 2017
 */
public interface TrdOpcionDisposicionFinalRestIface {

	/**
	 * Obtiene las opciones de disposición final
	 * @param empresaId Id de empresa
	 * @param isEstado Estado de la opcion, true inactivo, false en caso contrario
	 * @return Listado de opciones
	 */
	@GET
    @Path("trd/trdOpcionDisposicionFinal/{empresaId}/{isEstado}")
    @Consumes({ MediaType.APPLICATION_JSON })
	List<TrdOpcionDisposicionFinal> listarPorEmpresaAndEstado(@PathParam("empresaId") Integer empresaId, @PathParam("isEstado") boolean isEstado);
	
}
