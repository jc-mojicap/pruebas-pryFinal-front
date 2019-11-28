/*
* Archivo: TrdAreaRestIface.java
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

import co.com.grupoasd.documental.cliente.trd.model.TrdArea;

/**
 * Interface recurso TrdArea.
 * @author JuanMojica
 *
 */
@Path("/trd")
public interface TrdAreaRestIface {
    
    @GET
    @Path("/area/trdAreaId")
    @Produces({MediaType.APPLICATION_JSON})
    TrdArea obtenerPorTrdAreaId(@QueryParam("trdAreaId") Integer trdAreaId);
    
    @GET
    @Path("/area/areaId")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdArea> listarPorAreaId(@QueryParam("areaId") Integer areaId);
    
    @GET
    @Path("/area/trdId")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdArea> listarPorTrdId(@QueryParam("trdId") Integer trdId);
    
    @GET
    @Path("/area/empresaId")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdArea> listarPorEmpresa(@QueryParam("empresaId") Integer empresaId);
    
    @POST
    @Path("/area")
    @Consumes({MediaType.APPLICATION_JSON})
    TrdArea crearTrdArea(TrdArea trdArea);
    
    @PUT
    @Path("/area")
    @Consumes({MediaType.APPLICATION_JSON})
    TrdArea actualizarTrdArea(TrdArea trdArea);
    
}
