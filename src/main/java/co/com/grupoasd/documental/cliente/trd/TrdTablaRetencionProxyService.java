/**
 * 
 */
package co.com.grupoasd.documental.cliente.trd;

import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import co.com.grupoasd.documental.cliente.comun.AuthorizationFilter;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.trd.iface.TrdTablaRetencionRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdTablaRetencion;
import co.com.grupoasd.documental.presentacion.service.trd.dto.ResultadoGeneracionTablaRetencionExcel;
import co.com.grupoasd.documental.presentacion.service.trd.dto.TrdTablaRetencionGeneralDto;


/**
 * Proxy TrdTablaRetencion
 * 
 * @author gmora
 * @since Junio 22 de 2017
 */
public final class TrdTablaRetencionProxyService {

	/**
	 * Consumo de servicio listar por empresa TrdTablaRetencion
	 * 
	 * @param empresaId
	 *            id empresa
	 * @return Listado de TrdTablaRetencion
	 */
	public static List<TrdTablaRetencion> listarPorEmpresaId(Integer empresaId) {
		return ServiciosEndpoint.get().proxy(TrdTablaRetencionRestIface.class).listarPorEmpresaId(empresaId);
	}

	/**
	 * Consumo de servicio listar por empresa y estado TrdTablaRetencion
	 * 
	 * @param empresaId
	 *            id empresa
	 * @param estado
	 *            id estado
	 * @return Listado de TrdTablaRetencion
	 */
	public static List<TrdTablaRetencion> listarPorEmpresaIdAndEstado(Integer empresaId, Integer estado) {
		return ServiciosEndpoint.get().proxy(TrdTablaRetencionRestIface.class).listarPorEmpresaIdAndEstado(empresaId,
				estado);
	}

	/**
	 * Consumo de servicio listar por empresa y estado TrdTablaRetencion
	 * 
	 * @param empresaId
	 *            id empresa
	 * @param estado
	 *            id estado
	 * @return Listado de TrdTablaRetencion
	 */
	public static List<TrdTablaRetencion> listarPorEmpresaIdAndEstadoDelExpediente(Integer empresaId, Integer estado) {
		return ServiciosEndpoint.get().proxy(TrdTablaRetencionRestIface.class)
				.listarPorEmpresaIdAndConteoExpedientes(empresaId, estado);
	}

	/**
	 * Consumo de servicio eliminar una TRD
	 * 
	 * @param trdId
	 *            id de la TRD
	 * 
	 * @return boolean true: Si pudo eliminar el registro. false: Si no lo pudo
	 *         eliminar
	 */
	public static boolean eliminar(final Token token, Integer trdId) {
		ResteasyWebTarget target = ServiciosEndpoint.get();
		target.register(AuthorizationFilter.instance(token));
		return target.proxy(TrdTablaRetencionRestIface.class).eliminar(trdId);
	}


	/**
	 * Consumo de servicio obtener una TRD con sus Areas, Series y Subseries
	 * respectivas
	 * 
	 * @param trdId
	 *            id de la TRD
	 * 
	 * @return boolean TrdTablaRetencion
	 */
	public static TrdTablaRetencion getTrdById(Integer trdId) {
		return ServiciosEndpoint.get().proxy(TrdTablaRetencionRestIface.class).getTrdPorId(trdId);
	}

	/**
	 * Consumo de servicio de exportar a XLS para los datos de la TRD
	 * 
	 * @param trd  TRD a exportar
	 * @param media InfoMedia relacionado con los datos de la generacion del archivo
	 * 
	 * @return ResultadoGeneracionTablaRetencionExcel
	 */
	public static ResultadoGeneracionTablaRetencionExcel exportarToXSL(Integer trdId) {
		 return	 ServiciosEndpoint.get().proxy(TrdTablaRetencionRestIface.class).exportarXSL(trdId);
	}


	/**
	 * Crea un nuevo registro en las tabalas TRD
	 * @param token
	 * @param generalDto
	 * @return
	 */
	public static TrdTablaRetencion generarTrd(final Token token, final TrdTablaRetencionGeneralDto generalDto) {
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdTablaRetencionRestIface.class).generarTrd(generalDto);
    }
	
	/**
	 * Guarda parcialmente un registro en TRD
	 * @param token
	 * @param generalDto
	 * @return
	 */
	
	public static TrdTablaRetencion guardarParcial(final Token token, final TrdTablaRetencionGeneralDto generalDto) {
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdTablaRetencionRestIface.class).guardarParcial(generalDto);
    }

	/**
	 * Obtiene el nombre de la TRD
	 * @param empresaId Id empresa
	 * @param nombreEmpresa Nombre Empresa
	 * @return String
	 */
	public static String obtenerNombreTrd(Integer empresaId, String nombreEmpresa) {
		return ServiciosEndpoint.get().proxy(TrdTablaRetencionRestIface.class).obtenerNombreTrd(empresaId, nombreEmpresa);
	}
}
