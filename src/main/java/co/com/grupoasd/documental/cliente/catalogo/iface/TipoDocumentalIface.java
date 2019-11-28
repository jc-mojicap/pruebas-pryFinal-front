/*
 * Archivo: TipoDocumentalIface.java
 * Fecha creacion: 21/03/2017
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.catalogo.model.TipoDocumental;

/**
 * Interface recurso tipo documental.
 * 
 * @author cestrada
 *
 */

@Path("/catalogo")
public interface TipoDocumentalIface {

    public final static String ENTIDAD_TIPO_DOCUMENTAL = "tipoDocumental";

    @GET
    @Path("/" + ENTIDAD_TIPO_DOCUMENTAL + "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TipoDocumental obtenerPorId(@PathParam("id") Integer tipoDocumentalId);

    @GET
    @Path("/" + ENTIDAD_TIPO_DOCUMENTAL)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoDocumental> listar(@QueryParam("subserieId") Integer subserieId, @QueryParam("inactivo") Boolean inactivo);

}
