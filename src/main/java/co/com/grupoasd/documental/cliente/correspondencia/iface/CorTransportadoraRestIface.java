/*
* Archivo: CorTransportadoraIface.java
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
package co.com.grupoasd.documental.cliente.correspondencia.iface;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.correspondencia.model.CorTransportadora;

/**
 * Interface recurso CorTransportadora.
 * @author JuanMojica
 *
 */
@Path("/correspondencia")
public interface CorTransportadoraRestIface {

	/**
	 * Busca una corTransportadora por su id.
	 * @param id Identificador de la CorTransportadora.
	 * @return La CorTransportadora asociada.
	 */
	@GET
	@Path("/transportadora")
	@Produces({MediaType.APPLICATION_JSON})
	CorTransportadora obtenerPorId(@QueryParam("id") Integer id);
	
	/**
	 * Busca todas las corTransportadora.
	 * @return Lista con todas las corTransportadora.
	 */
	@GET
	@Path("/transportadoras")
	@Produces({MediaType.APPLICATION_JSON})
	List<CorTransportadora> listar();
	
}
