/*
 * Archivo: AccionRestIface.java
 * Fecha creacion: 11/04/2017
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
package co.com.grupoasd.documental.cliente.catalogo.iface;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.presentacion.service.catalogo.dto.AccionDto;

/**
 * Interface recurso accion.
 * 
 * @author Juan Carlos Castellanos
 *
 */
@Path("/catalogo")
public interface AccionRestIface {

    @GET
    @Path("/accion/userAndRol")
    @Produces({ MediaType.APPLICATION_JSON })
    List<AccionDto> listarPorUsuarioYRol(@QueryParam("rolId") Integer rolId, @QueryParam("userId") Integer usuarioId);

    @GET
    @Path("/accion/obtenerPorUsuarioIdEInactivo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccionDto> listarPorUsuarioIdEInactivo(@QueryParam("usuarioId") Integer usuarioId, @QueryParam("inactivo") Boolean inactivo);

    @GET
    @Path("/accion/obtenerPorRolIdEInactivo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccionDto> listarPorRolIdEInactivo(@QueryParam("rolId") Integer rolId, @QueryParam("inactivo") Boolean inactivo);

}
