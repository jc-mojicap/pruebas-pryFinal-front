/*
 * Archivo: CorTerceroXRadicadoRestIface.java
 * Fecha creacion: 5/04/2017
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
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.correspondencia.model.CorTerceroXRadicado;

/**
 * Interfaz consumo de los servicios CorTerceroXRadicado.
 * 
 * @author cestrada
 *
 */
@Path("/correspondencia")
public interface CorTerceroXRadicadoRestIface {
    
    @GET
    @Path("/corTerceroXRadicado/obtenerPorTerceroId")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CorTerceroXRadicado> listarPorTerceroId(@QueryParam("terceroId") Integer terceroId);
    
    @GET
    @Path("/corTerceroXRadicado/obtenerPorRadicadoId")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CorTerceroXRadicado> listarPorRadicadoId(@QueryParam("radicadoId") Long radicadoId);   

    @GET
    @Path("/corTerceroXRadicado/obtenerTodos")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CorTerceroXRadicado> listarTercerosConRadicados();   

    
}
