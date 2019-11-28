/*
* Archivo: CorTipoEmbalajeRestIface.java
* Fecha creacion = 23/03/2017
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

import co.com.grupoasd.documental.cliente.correspondencia.model.CorTipoEmbalaje;

/**
 * Interface recurso CorTipoEmbalaje.
 * @author JuanMojica
 *
 */
@Path("/correspondencia")
public interface CorTipoEmbalajeRestIface {

	/**
	 * Busca un CorTipoEmbalaje por su id.
	 * @param id Identificador del CorTipoEmbalaje.
	 * @return El CorTipoEmbalaje asociado.
	 */
	@GET
	@Path("/tipoembalaje")
	@Produces({MediaType.APPLICATION_JSON})
	CorTipoEmbalaje obtenerPorId(@QueryParam("id") Integer id);
	
	/**
	 * Busca todos los CorTipoEmbalaje.
	 * @return Lista con todos los CorTipoEmbalaje.
	 */
	@GET
	@Path("/tipoembalajes")
	@Produces({MediaType.APPLICATION_JSON})
	List<CorTipoEmbalaje> listar();
	
}
