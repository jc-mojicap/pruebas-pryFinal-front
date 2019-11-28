/*
* Archivo: TrdMetadatoTipoDocmRestIface.java
* Fecha creacion = 20/06/2017
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
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.trd.model.TrdMetadatoTipoDocm;

/**
 * Interface recurso TrdMetadatoTipoDocm.
 * @author JuanMojica
 *
 */
@Path("/trd")
public interface TrdMetadatoTipoDocmRestIface {

    @GET
    @Path("/metadatoTipoDocumental/metadatoIdYTipoDocumentalId")
    @Produces({MediaType.APPLICATION_JSON})
    TrdMetadatoTipoDocm obtenerPorMetadatoIdYTipoDocumentalId(@QueryParam("metadatoId") Integer metadatoId, @QueryParam("tipoDocumentalId") Integer tipoDocumentalId);
    
    @GET
    @Path("/metadatoTipoDocumental/metadato")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdMetadatoTipoDocm> listarPorMetadato(@QueryParam("metadatoId") Integer metadatoId);
    
    @GET
    @Path("/metadatoTipoDocumental/tipoDocumental")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdMetadatoTipoDocm> listarPorTipoDocumental(@QueryParam("tipoDocumentalId") Integer tipoDocumentalId);
    
    @POST
    @Path("/metadatoTipoDocumental")
    @Consumes({MediaType.APPLICATION_JSON})
    List<TrdMetadatoTipoDocm> guardarTrdMetadatoTipoDocm(List<TrdMetadatoTipoDocm> trdMetadatosTipoDocm);
    
}
