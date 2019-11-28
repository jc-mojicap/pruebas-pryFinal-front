/*
* Archivo: CorComentarioRestIface.java
* Fecha creacion = 31/03/2017
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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.correspondencia.model.CorComentario;

/**
 * Interface recurso CorComentario.
 * @author JuanMojica
 *
 */
@Path("/correspondencia")
public interface CorComentarioRestIface {

    /**
     * Busca un corComentario por su id.
     * @param id Identificador del corComentario.
     * @return El corComentario asociado.
     */
    @GET
    @Path("/corComentario")
    @Produces({MediaType.APPLICATION_JSON})
    CorComentario obtenerPorId(@QueryParam("id") Long id);
    
    /**
     * Busca una lista de corComentario por el radicado al que pertenecen.
     * @param id Identificador del corRadicado.
     * @return Lista de corComentario.
     */
    @GET
    @Path("/corComentarios")
    @Produces({MediaType.APPLICATION_JSON})
    List<CorComentario> obtenerPorRadicado(@QueryParam("id") Long id);
    
    /**
     * Crea un nuevo corComentario.
     * @param corComentario CorComentario.
     * @return corComentario creado con id asignado.
     */
    @POST
    @Path("/corComentario")
    @Consumes({MediaType.APPLICATION_JSON})
    CorComentario crear(CorComentario corComentario);
    
}
