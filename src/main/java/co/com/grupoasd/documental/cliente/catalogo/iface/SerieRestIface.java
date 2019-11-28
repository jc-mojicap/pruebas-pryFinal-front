/*
* Archivo: SerieRestIface.java
* Fecha creacion = 21/03/2017
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
package co.com.grupoasd.documental.cliente.catalogo.iface;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.catalogo.model.Serie;

/**
 * Interface recurso Serie.
 * 
 * @author JuanMojica
 *
 */
@Path("/catalogo")
public interface SerieRestIface {

    /**
     * Busca una serie por su id.
     * 
     * @param id
     *            Identificador de la serie.
     * @param isInactivo Define si la serie se encuentra inactiva, true si está inactiva, false activa
     * @return La serie asociada.
     */
    @GET
    @Path("/serie/{id}/{isInactivo}")
    @Produces({ MediaType.APPLICATION_JSON })
    Serie obtenerPorId(@PathParam("id") Integer id, @PathParam("isInactivo") boolean isInactivo);

    /**
     * Lista series por el área a la que pertenecen
     * 
     * @param id
     *            Identificador del área.
     * @param isInactivo Define si la serie se encuentra inactiva, true si está inactiva, false activa
     * @return Lista de series asociadas al área.
     */
    @GET
    @Path("/series")
    @Produces({ MediaType.APPLICATION_JSON })
    List<Serie> listarPorArea(@QueryParam("id") Integer id, @QueryParam("isInactivo") boolean isInactivo);

}
