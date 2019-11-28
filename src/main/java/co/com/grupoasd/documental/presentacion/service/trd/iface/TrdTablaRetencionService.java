/**
 * 
 */
package co.com.grupoasd.documental.presentacion.service.trd.iface;

import java.util.List;

import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.trd.model.TrdTablaRetencion;
import co.com.grupoasd.documental.presentacion.service.trd.dto.ResultadoGeneracionTablaRetencionExcel;
import co.com.grupoasd.documental.presentacion.service.trd.dto.TrdTablaRetencionGeneralDto;


/**
 * Interface TrdTablaRetencion
 * 
 * @author gmora
 * @since Junio 22 de 2017
 */
public interface TrdTablaRetencionService {

	/**
	 * Consumo de servicio listar por empresa TrdTablaRetencion
	 * 
	 * @param empresaId
	 *            id empresa
	 * @return Listado de TrdTablaRetencion
	 */
	List<TrdTablaRetencion> listarPorEmpresaId(Integer empresaId);

	/**
	 * Consumo de servicio listar por empresa y estado TrdTablaRetencion
	 * 
	 * @param empresaId
	 *            id empresa
	 * @param estado
	 *            id estado
	 * @return Listado de TrdTablaRetencion
	 */
	List<TrdTablaRetencion> listarPorEmpresaIdAndEstado(Integer empresaId, Integer estado);

	/**
	 * Consumo de servicio listar por empresa y estado del expediente
	 * TrdTablaRetencion - Permite obtener las tablas de retencion documental y
	 * asociar la cantidad de expedientes en el estado que el usuario necesite.
	 * 1. Abierto - 2. Cerrado
	 * 
	 * @param empresaId
	 *            id empresa
	 * @param estado
	 *            id estado del expediente a consultar
	 * @return Listado de TrdTablaRetencion
	 */
	List<TrdTablaRetencion> listarPorEmpresaIdAndEstadoDelExpediente(Integer empresaId, Integer estado);

	/**
	 * Consumo de servicio para eliminar una TRD
	 * 
	 * @param trdId
	 *            id de la TRD
	 * @return boolean true: Si pudo eliminar el registro. false: Si no lo pudo
	 *         eliminar
	 */
	boolean eliminarTrd(Token token, Integer trdId);
	
	
	/**
	 * Consumo de servicio para traer los datos de una TRD junto con sus respectivas Areas, Series y Subseries
	 * 
	 * @param trdId id de la TRD	
	 * @return boolean true: Si pudo eliminar el registro. false: Si no lo pudo eliminar
	 */	
	TrdTablaRetencion getTrdById(Integer trdId);
	
	
	/**
	 * Consumo de servicio para traer los datos de una TRD junto con sus respectivas Areas, Series y Subseries
	 * 
	 * @param List<TrdTablaRetencion> list  lista de tabla de retencion documental	
	 * @param String search    parametro de busqueda
	 * @return List<TrdTablaRetencion>
	 */	
	List<TrdTablaRetencion> filtrar(List<TrdTablaRetencion> list, String search);
	
	/**
	 * Consumo de servicio para exportar la TRD seleccionada a formato XLS
	 * 
	 * @param media El archivo a ser importado.
	 * @param list 
	 * @return trd a exportar
	 */	
	ResultadoGeneracionTablaRetencionExcel exportarToXSL(Integer trdId);


	/**
	 * Crea un registro en las tablas TRD
	 * 
	 * @param token Token validación
	 * @param generalDto Objeto a crear
	 * @return TrdTablaRetencion
	 */
	TrdTablaRetencion generarTrd(Token token, TrdTablaRetencionGeneralDto generalDto);

	/**
	 * Guarda parcialmente la TRD
	 * 
	 * @param token Token validacion
	 * @param generalDto Objeto a guardar
	 * @return TrdTablaRetencion
	 */
	TrdTablaRetencion guardarParcial(Token token, TrdTablaRetencionGeneralDto generalDto);
	
	/**
	 * Obtiene el nombre de la TRD
	 * @param empresaId Id empresa Integer
	 * @param nombreEmpresa Nombre empresa String
	 * @return String
	 */
	String obtenerNombreTrd(Integer empresaId, String nombreEmpresa);
}
