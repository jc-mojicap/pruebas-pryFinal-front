/*
* Archivo: TrdTipoDocumentalRestIface.java
* Fecha creacion = 24/06/2017
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
package co.com.grupoasd.documental.cliente.trd.iface;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.trd.model.TrdTipoDocumental;

/**
 * Interface recurso TrdTipoDocumental.
 * @author JuanMojica
 *
 */
@Path("/trd")
public interface TrdTipoDocumentalRestIface {

    @GET
    @Path("/tipoDocumental/tipoDocumentalId")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdTipoDocumental> listarPorTipoDocumentalId(@QueryParam("tipoDocumentalId") Integer tipoDocumentalId);
    
    @GET
    @Path("/tipoDocumental/subserieId")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdTipoDocumental> listarPorSubserieId(@QueryParam("subserieId") Integer subserieId);
    
    @POST
    @Path("/tipoDocumental")
    @Consumes({MediaType.APPLICATION_JSON})
    TrdTipoDocumental crearTrdTipoDocumental(TrdTipoDocumental trdTipoDocumental);
    
    @PUT
    @Path("/tipoDocumental")
    @Consumes({MediaType.APPLICATION_JSON})
    TrdTipoDocumental actualizarTrdTipoDocumental(TrdTipoDocumental trdTipoDocumental);
    
}
