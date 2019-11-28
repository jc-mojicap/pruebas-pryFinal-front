/*
 * Archivo: CorAdjuntoRestIface.java
 * Fecha creacion: 4/04/2017
 * Todos los derechos de propiedad intelectual e industrial sobre esta
 * aplicacion son de propiedad exclusiva del GRUPO ASESORIA EN
 * SISTEMATIZACION DE DATOS SOCIEDAD POR ACCIONES SIMPLIFICADA – GRUPO ASD S.A.S.
 * Su uso, alteracion, reproduccion o modificacion sin la debida
 * consentimiento por escrito de GRUPO ASD S.A.S.
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.correspondencia.model.CorAdjunto;

/**
 * Interfaz consumo de los servicios CorAdjunto.
 * @author cestrada
 *
 */
@Path("/correspondencia")
public interface CorAdjuntoRestIface {
    
    
    @GET
    @Path("/corAdjunto/obtener")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CorAdjunto> listarPorRadicadoIdEliminado(@QueryParam("radicadoId") Long radicadoId, @QueryParam("elimnado") Boolean eliminado);
    
    @GET
    @Path("/corAdjunto/obtener")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CorAdjunto> listarPorRadicadoId(@QueryParam("radicadoId") Long radicadoId);
    
    @GET
    @Path("/corAdjunto/obtenerCantidad")
    @Produces({MediaType.APPLICATION_JSON})
    public Integer contarPorRadicadoIdEliminado(@QueryParam("radicadoId") Long radicadoId, @QueryParam("eliminado") Boolean eliminado);
    
    @GET
    @Path("/corAdjunto/obtenerBytes/{radicadoId}/{adjuntoId}")
    @Produces({MediaType.APPLICATION_JSON})
    public byte[] obtenerBytesAdjunto(@PathParam ("radicadoId") Long radicadoId, @PathParam("adjuntoId") Integer adjuntoId);
    
}
