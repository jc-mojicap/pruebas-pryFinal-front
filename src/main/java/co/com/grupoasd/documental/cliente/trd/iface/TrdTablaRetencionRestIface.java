/**
 * 
 */
package co.com.grupoasd.documental.cliente.trd.iface;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.trd.model.TrdTablaRetencion;
import co.com.grupoasd.documental.presentacion.service.trd.dto.ResultadoGeneracionTablaRetencionExcel;
import co.com.grupoasd.documental.presentacion.service.trd.dto.TrdTablaRetencionGeneralDto;


/**
 * Rest Easy para consumir servicios modulo TrdTablaRetencion
 * @author gmora
 * @since Junio 22 de 2017
 */
public interface TrdTablaRetencionRestIface {

	/**
	 * Obtiene Listado de TrdTablaRetencion buscando por empresa
	 * @param empresaId Id empresa por el cual hacer la busqueda
	 * @return Listado de TrdTablaRetencion
	 */
	@GET
	@Path("trd/trdTablaRetencion/{empresaId}")
	@Produces({MediaType.APPLICATION_JSON})
	List<TrdTablaRetencion> listarPorEmpresaId(@PathParam("empresaId") Integer empresaId);
	
	/**
	 * Obtiene Listado de TrdTablaRetencion buscando por empresa y estado
	 * @param empresaId Id de la empresa por el cual hacer la busqueda
	 * @param estado Id del estado por el cual hacer la busqueda
	 * @return Listado de TrdTablaRetencion
	 */
	@GET
	@Path("trd/trdTablaRetencion/{empresaId}/{estado}")
	@Produces({MediaType.APPLICATION_JSON})
	List<TrdTablaRetencion> listarPorEmpresaIdAndEstado(@PathParam("empresaId") Integer empresaId, @PathParam("estado") Integer estado);
	
	/**
	 * Obtiene Listado de TrdTablaRetencion buscando por empresa y estado del expediente
	 * @param empresaId Id de la empresa por el cual hacer la busqueda
	 * @param estado Id del estado del expediente 1. Abierto 2. Cerrado
	 * @return Listado de TrdTablaRetencion
	 */
	@GET
	@Path("trd/trdTablaRetencion/expediente/{empresaId}/{estado}")
	@Produces({MediaType.APPLICATION_JSON})
	List<TrdTablaRetencion> listarPorEmpresaIdAndConteoExpedientes(@PathParam("empresaId") Integer empresaId, @PathParam("estado") Integer estado);
	
	/**
	 * Obtiene Listado de TrdTablaRetencion buscando por empresa y estado del expediente
	 * @param empresaId Id de la empresa por el cual hacer la busqueda
	 * @param estado Id del estado del expediente 1. Abierto 2. Cerrado
	 * @return Listado de TrdTablaRetencion
	 */
	@DELETE
	@Path("trd/trdTablaRetencion/eliminar/{trdId}")	
	@Consumes
	boolean eliminar(@PathParam("trdId") Integer trdId);

	/**
	 * Crea un registro en las tablas TRD
	 * @param generalDto
	 * @return TrdTablaRetencion
	 */
	@POST
	@Path("/trd/trdTablaRetencion/generarTrd")
	@Consumes({MediaType.APPLICATION_JSON})
	TrdTablaRetencion generarTrd(TrdTablaRetencionGeneralDto generalDto);	

	
	/**
	 * Obtiene Listado de TrdTablaRetencion buscando por empresa y estado del expediente
	 * @param empresaId Id de la empresa por el cual hacer la busqueda
	 * @param estado Id del estado del expediente 1. Abierto 2. Cerrado
	 * @return Listado de TrdTablaRetencion
	 */
	@GET
	@Path("trd/trdTablaRetencion/get/{trdId}")
	@Produces({MediaType.APPLICATION_JSON})
	TrdTablaRetencion getTrdPorId(@PathParam("trdId") Integer trdId);
	
	
	
	/**
	 * Generacion del archivo de excel para la trd seleccionada
	 * @param media Datos del archivo de exporte
	 * @param trd TRD a exportar
	 * @return ResultadoGeneracionTablaRetencionExcel resultados del exporte del archivo de la TRD
	 */

	@GET
	@Path("trd/trdTablaRetencion/exportar/{trdId}")
	@Consumes({MediaType.APPLICATION_JSON})
	ResultadoGeneracionTablaRetencionExcel exportarXSL(@PathParam("trdId") Integer trdId);
	

	/**
	 * Guarda parcialmente la TRD
	 * @param generalDto
	 * @return TrdTablaRetencion
	 */
	@POST
	@Path("/trd/trdTablaRetencion/guardarParcial")
	@Consumes({MediaType.APPLICATION_JSON})
	TrdTablaRetencion guardarParcial(TrdTablaRetencionGeneralDto generalDto);

	/**
	 * Obtiene el nombre de la TRD a crear
	 * @param empresaId Integer Id empresa
	 * @param nombreEmpresa String Nombre empresa
	 * @return String nombre TRD
	 */
	@GET
	@Path("trd/trdTablaRetencion/obtenerNombreTrd/{empresaId}/{nombreEmpresa}")
	@Produces({MediaType.APPLICATION_JSON})
	String obtenerNombreTrd(@PathParam("empresaId") Integer empresaId, @PathParam("nombreEmpresa") String nombreEmpresa);
}
