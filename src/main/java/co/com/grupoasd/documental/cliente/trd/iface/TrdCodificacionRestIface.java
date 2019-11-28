/*
* Archivo: TrdCodificacionRestIface.java
* Fecha creacion = 06/06/2017
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

import co.com.grupoasd.documental.cliente.trd.model.TrdCodificacion;

/**
 * Interface recurso TrdCodificacion.
 * @author JuanMojica
 *
 */
@Path("/trd")
public interface TrdCodificacionRestIface {

    @GET
    @Path("/codificacion/all")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdCodificacion> listarTodos();
    
    @GET
    @Path("/codificacion/empresa")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdCodificacion> listarPorEmpresa(@QueryParam("empresaId") Integer empresaId);
    
    @GET
    @Path("/codificacion/estructura")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdCodificacion> listarPorEstructura(@QueryParam("estructuraId") Integer estructuraId);
    
    @GET
    @Path("/codificacion/empresaAndEstructura")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdCodificacion> listarPorEmpresaYEstructura(@QueryParam("empresaId") Integer empresaId, @QueryParam("estructuraId") Integer estructuraId);
    
    @POST
    @Path("/codificacion")
    @Consumes({MediaType.APPLICATION_JSON})
    List<TrdCodificacion> guardarCodificaciones(List<TrdCodificacion> codificaciones);
}
