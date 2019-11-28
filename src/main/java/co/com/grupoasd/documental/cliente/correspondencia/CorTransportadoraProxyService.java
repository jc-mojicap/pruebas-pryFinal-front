/*
* Archivo: CorTransportadoraProxyService.java
* Fecha creacion = 22/03/2017
* Todos los derechos de propiedad intelectual e industrial sobre esta
* aplicacion son de propiedad exclusiva del GRUPO ASESORIA EN
* SISTEMATIZACION DE DATOS SOCIEDAD POR ACCIONES SIMPLIFICADA – GRUPO ASD S.A.S.
* Su uso, alteracion, reproduccion o modificacion sin el debido
* consentimiento por escrito del GRUPO ASD S.A.S.
* autorizacion por parte de su autor quedan totalmente prohibidos.
* 
* Este programa se encuentra protegido por las disposiciones de la
* Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
* propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
* previstas en la Ley.
*/
package co.com.grupoasd.documental.cliente.correspondencia;

import java.util.List;

import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.correspondencia.iface.CorTransportadoraRestIface;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTransportadora;

/**
 * Proxy que permite la invocación remota de los servicios del recurso CorTransportadora.
 * @author JuanMojica
 *
 */
public final class CorTransportadoraProxyService {
	
	/**
	 * Constructor privado. Esta clase no debe instanciarse.
	 */
	private CorTransportadoraProxyService(){
		
	}
	
	/**
	 * Busca una CorTransportadora por su id.
	 * @param id Identificador de la CorTransportadora.
	 * @return CorTransportadora asociada.
	 */
	public static CorTransportadora obtenerPorId(final Integer id){
		return ServiciosEndpoint.get().proxy(CorTransportadoraRestIface.class).obtenerPorId(id);
	}
	
	/**
	 * Lista las CorTransportadora.
	 * @return lista de CorTransportadora.
	 */
	public static List<CorTransportadora> listar(){
		return ServiciosEndpoint.get().proxy(CorTransportadoraRestIface.class).listar();
	}
	
}
