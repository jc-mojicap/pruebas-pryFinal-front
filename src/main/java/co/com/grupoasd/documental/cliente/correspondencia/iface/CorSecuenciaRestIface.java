/*
* Archivo: CorSecuenciaRestIface.java
* Fecha creacion = 03/04/2017
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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.correspondencia.model.CorSecuencia;

/**
 * Interface recurso CorSecuencia.
 * @author JuanMojica
 *
 */
@Path("/correspondencia")
public interface CorSecuenciaRestIface {

    /**
     * Busca un corCanal por su id.
     * @param id Identificador del corCanal.
     * @return El corCanal asociado.
     */
    @GET
    @Path("/secuencia/id")
    @Produces({MediaType.APPLICATION_JSON})
    CorSecuencia obtenerPorId(@QueryParam("id") Integer id);
    
    /**
     * Busca un corCanal por su id.
     * @param id Identificador del corCanal.
     * @return El corCanal asociado.
     */
    @GET
    @Path("/secuencia/empresa")
    @Produces({MediaType.APPLICATION_JSON})
    CorSecuencia obtenerPorEmpresa(@QueryParam("id") Integer id);
    
    /**
     * Crea un nuevo radicado.
     * @param corRadicado CorRadicado.
     * @return corRadicado creado con id asignado.
     */
    @POST
    @Path("/secuencia")
    @Consumes({ MediaType.APPLICATION_JSON })
    CorSecuencia crear(CorSecuencia corSecuencia);
    
}
