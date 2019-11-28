/*
 * Archivo: CorRadicadoRestIface.java
 * Fecha creacion: 24/03/2017
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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.correspondencia.model.CorRadicado;
import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.service.correspondencia.dto.RadicadoDto;

/**
 * @author cestrada
 *
 */

@Path("/correspondencia")
public interface CorRadicadoRestIface {

    /**
     * Lista radicados por filtros.
     * 
     * @return Listado radicados filtrados. Si no existen retorna vacio.
     */
    @POST
    @Path("/corRadicado/obtenerPorFiltros")
    @Consumes({ MediaType.APPLICATION_JSON })
    List<RadicadoDto> listarRadicadosPorFiltros(RadicadoDto filtros);

    /**
     * Cantidad de radicados filtrados.
     * 
     * @return Integer cantidad de radicados.
     */
    @POST
    @Path("/corRadicado/obtenerCantidadPorFiltros")
    @Consumes({ MediaType.APPLICATION_JSON })
    Integer contarRadicadosPorFiltros(RadicadoDto filtros);

    /**
     * Archivo de radicados filtrados enviando el tipo de archivo.
     * 
     * @return InfoMedia Archivo de radicados.
     */
    @POST
    @Path("/corRadicado/obtenerArchivoPorFiltros")
    @Consumes({ MediaType.APPLICATION_JSON })
    InfoMedia obtenerArchivoRadicadosPorFiltros(RadicadoDto filtros, @QueryParam("tipoArchivo") String tipoArchivo);

    /**
     * Lista los estados radicados.
     * 
     * @return Listado con estados radicados. Si no existen retorna vacio.
     */
    @POST
    @Path("/selcorRadicado")
    @Consumes({ MediaType.APPLICATION_JSON })
    List<CorRadicado> listarRadicadosPorFiltros(CorRadicado filtros);

    /**
     * Crea un nuevo radicado.
     * 
     * @param corRadicado
     *            CorRadicado.
     * @return corRadicado creado con id asignado.
     */
    @GET
    @Path("/corRadicado/obtenerPorRadicado")
    @Produces({ MediaType.APPLICATION_JSON })
    CorRadicado buscarPorRadicadoYEmpresa(@QueryParam("empresa") Integer empresaId, @QueryParam("radicado") String radicado);

    /**
     * Crea un nuevo radicado.
     * 
     * @param corRadicado
     *            CorRadicado.
     * @return corRadicado creado con id asignado.
     */
    @POST
    @Path("/corRadicado")
    @Consumes({ MediaType.APPLICATION_JSON })
    CorRadicado crear(CorRadicado corRadicado);
    
    /**
     * Actualiza un radicado.
     * 
     * @param corRadicado
     *            CorRadicado.
     * @return corRadicado actualizado.
     */
    @POST
    @Path("/corRadicado/update")
    @Consumes({ MediaType.APPLICATION_JSON })
    CorRadicado actualizar(CorRadicado corRadicado);

    /**
     * Obtiene la información del radicado por radicado_id
     * @param id Id del radicado
     * @return Objeto corRadicado
     */
    @GET
    @Path("/corRadicado/obtenerPorId/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    CorRadicado obtenerPorId(@PathParam("id") Long id);
    
    /**
     * Actualiza radicado
     * @param corRadicado Objeto CorRadicado
     * @return Objeto CorRadicado
     */
    @PUT
    @Path("/corRadicado/actualizarRadicado")
    @Consumes({ MediaType.APPLICATION_JSON })
    CorRadicado actualizarRadicado(CorRadicado corRadicado);
    
    /**
     * Anula radicado
     * @param corRadicado Objeto CorRadicado
     * @return Objeto CorRadicado
     */
    @PUT
    @Path("/corRadicado/anularRadicado")
    @Consumes({ MediaType.APPLICATION_JSON })
    CorRadicado anularRadicado(CorRadicado corRadicado);
    
    /**
     * Asignar responsables
     * @param corRadicado Objeto CorRadicado
     * @return Objeto CorRadicado
     */
    @PUT
    @Path("/corRadicado/asignarResponsables")
    @Consumes({ MediaType.APPLICATION_JSON })
    CorRadicado asignarResponsables(CorRadicado corRadicado);

}
