/*
* Archivo: TrdCodificacionRestIface.java
* Fecha creacion = 23/06/2017
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


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.trd.model.TrdDocumento;

/**
 * Interface recurso TrdDocumento.
 * @author Luisa Hernández
 *
 */
@Path("/trd")
public interface TrdDocumentoRestIface {

    @GET
    @Path("/documento/all")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdDocumento> listarTodos();
    
    @GET
    @Path("/documento/{trdId}/{areaPadreId}")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdDocumento> listarPorTrd(@PathParam("trdId") Integer trdId, @PathParam("areaPadreId") Integer areaPadreId);
    
    @GET
    @Path("/documento/documento")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdDocumento> listarPorDocumento(@QueryParam("documentoId") Integer documentoId);
  
}
