/*
* Archivo: CorTerceroRestIface.java
* Fecha creacion = 23/03/2017
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
package co.com.grupoasd.documental.cliente.correspondencia.iface;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.correspondencia.model.CorTercero;
import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.comun.dto.RespuestaServicio;
import co.com.grupoasd.documental.presentacion.service.correspondencia.dto.ResultadoImpoTerceros;

/**
 * Interface recurso CorTercero.
 * @author JuanMojica
 *
 */
@Path("/correspondencia")
public interface CorTerceroRestIface {
	
	/**
	 * Busca los terceros por nombre.
	 * @param nombre Nombre de los terceros a buscar.
	 * @param empresaId Empresa sobre la cual se realiza la búsqueda.
	 * @param inactivo: <code>true</code> si se desea que liste los usuarios inactivos, <code>false</code> en caso contrario.
	 * @return Lista de CorTercero con los registros correspondientes.
	 */
	@GET
	@Path("/tercero/nombre")
	@Produces({MediaType.APPLICATION_JSON})
	List<CorTercero> listarPorEmpresaYNombre(@QueryParam("empresaId") Integer empresaId, @QueryParam("nombre") String nombre, @QueryParam("inactivo") final boolean inactivo);
	
	/**
	 * Busca los terceros por numero.
	 * @param numero Numero de los terceros a buscar.
	 * @param empresaId Empresa sobre la cual se realiza la búsqueda.
	 * @param inactivo: <code>true</code> si se desea que liste los usuarios inactivos, <code>false</code> en caso contrario.
	 * @return Lista de CorTercero con los registros correspondientes.
	 */
	@GET
	@Path("/tercero/numero")
	@Produces({MediaType.APPLICATION_JSON})
	List<CorTercero> listarPorEmpresaYNumero(@QueryParam("empresaId") Integer empresaId, @QueryParam("numero") String numero, @QueryParam("inactivo") final boolean inactivo);
	
	/**
     * Busca los terceror por su número, nombre, responsable, dirección y/o municipio.
     * @param valor valor para realizar la busqueda.
     * @return Lista con los resultados asociados.
     */
	@GET
	@Path("/tercero/varios")
	@Produces({MediaType.APPLICATION_JSON})
    List<CorTercero> listarPorNumeroNombreResponsableDireccionMunicipio(@QueryParam("empresaId") Integer empresa, @QueryParam("valor") String valor);
	
    /**
     * @author cestrada
     * Busca los terceror por su número y valores de paginación.
     * @param empresaId Id de la empresa.
     * @param numero Número del tercero.
     * @param firstResul número de página.
     * @param maxResults número de resultados.
     * @param strOrderBy campos por los cuales ordenar.
     * @param orderDesc Es ordenado Desc.
     * @return Lista con los terceros páginados.
     */
    @GET
    @Path("/tercero/todos")
    @Produces({ MediaType.APPLICATION_JSON })
    List<CorTercero> listar(@QueryParam("empresaId") Integer empresaId, @QueryParam("firstResult") Integer firstResul,
            @QueryParam("maxResults") Integer maxResults, @QueryParam("strOrderBy") String[] strOrderBy,
            @QueryParam("orderDesc") Boolean orderDesc);

    @GET
    @Path("/tercero/contarTodos")
    @Produces({ MediaType.APPLICATION_JSON })
    Integer contar(@QueryParam("empresaId") Integer empresaId);
    
	 /**
     * @author cestrada
     * Busca los terceror por su número y valores de paginación.
     * @param empresaId Id de la empresa.
     * @param numero Número del tercero.
     * @param firstResul número de página.
     * @param maxResults número de resultados.
     * @param strOrderBy campos por los cuales ordenar.
     * @param orderDesc Es ordenado Desc.
     * @return Lista con los terceros páginados.
     */
    @GET
    @Path("/tercero/numeroPaginacion")
    @Produces({ MediaType.APPLICATION_JSON })
    List<CorTercero> listarPorNumeroConPaginacion(@QueryParam("empresaId") Integer empresaId,
            @QueryParam("numero") String numero, @QueryParam("firstResult") Integer firstResul,
            @QueryParam("maxResults") Integer maxResults, @QueryParam("strOrderBy") String[] strOrderBy,
            @QueryParam("orderDesc") Boolean orderDesc);

    /**
     * contarPorNumero.
     * @param empresaId Id de la empresa.
     * @param numero Número de identificación de los terceros a buscar.
     * @return Integer cantidad de terceros.
     */
    @GET
    @Path("/tercero/contarNumero")
    @Produces({MediaType.APPLICATION_JSON})
    Integer contarPorNumero(@QueryParam("empresaId") Integer empresaId, @QueryParam("numero") String numero);

    /**
    * @author cestrada
    * @param empresaId Id de la empresa.
    * Busca los terceror por su nombre y valores de paginación.
    * @param nombre Nombre del tercero.
    * @param firstResul número de página.
    * @param maxResults número de resultados.
    * @param strOrderBy campos por los cuales ordenar.
    * @param orderDesc Es ordenado Desc.
    * @return Lista con los terceros páginados.
    */
    @GET
    @Path("/tercero/nombrePaginacion")
    @Produces({ MediaType.APPLICATION_JSON })
    List<CorTercero> listarPorNombreConPaginacion(@QueryParam("empresaId") Integer empresaId,
            @QueryParam("nombre") String nombre, @QueryParam("firstResult") Integer firstResul,
            @QueryParam("maxResults") Integer maxResults, @QueryParam("strOrderBy") String[] strOrderBy,
            @QueryParam("orderDesc") Boolean orderDesc);

   /**
    * contarPorNombre.
    * @param empresaId Id de la empresa.
    * @param nombre Nombre de los terceros a buscar.
    * @return Integer cantidad de terceros.
    */
   @GET
   @Path("/tercero/contarNombre")
   @Produces({MediaType.APPLICATION_JSON})
    Integer contarPorNombre(@QueryParam("empresaId") Integer empresaId, @QueryParam("nombre") String nombre);
   
   /**
    * @author cestrada
    * @param empresaId Id de la empresa.
    * Busca los terceror por su número, nombre y valores de paginación.
    * @param numero Número del tercero.
    * @param nombre Nombre del tercero.
    * @param firstResul número de página.
    * @param maxResults número de resultados.
    * @param strOrderBy campos por los cuales ordenar.
    * @param orderDesc Es ordenado Desc.
    * @return Lista con los terceros páginados.
    */
    @GET
    @Path("/tercero/numeroNombrePaginacion")
    @Produces({ MediaType.APPLICATION_JSON })
    List<CorTercero> listarPorNumeroNombreConPaginacion(@QueryParam("empresaId") Integer empresaId,
            @QueryParam("numero") String numero, @QueryParam("nombre") String nombre,
            @QueryParam("firstResult") Integer firstResul, @QueryParam("maxResults") Integer maxResults,
            @QueryParam("strOrderBy") String[] strOrderBy, @QueryParam("orderDesc") Boolean orderDesc);

   /**
    * contarPorNumeroNombre.
    * @param empresaId Id de la empresa.
    * @param numero Número de identificación de los terceros a buscar.
    * @param nombre Nombre del tercero.
    * @return Integer cantidad de terceros.
    */
   @GET
   @Path("/tercero/contarNumeroNombre")
   @Produces({MediaType.APPLICATION_JSON})
    Integer contarPorNumeroNombre(@QueryParam("empresaId") Integer empresaId, @QueryParam("numero") String numero,
            @QueryParam("nombre") String nombre);
   
   /**
    * Crea o actualiza un tercero.
    * @param tercero Tercero a ser creado o actualizado.
    * @return CorTercero Tercero creado o actualizado.
    */
   @POST
   @Path("/tercero/guardar")
   @Consumes({ MediaType.APPLICATION_JSON })
   CorTercero guardar(CorTercero tercero);
   
   /**
    * Inactiva un tercero.
    * @param tercero Tercero a ser inactivado.
    * @return CorTercero Tercero inactivado.
    */
   @POST
   @Path("/tercero/inactivar")
   @Consumes({ MediaType.APPLICATION_JSON })
   CorTercero inactivar(CorTercero tercero);
   
   /**
    * Valida la extensión del nombre del archivo para la importasión de terceros.
    * @param nombreArchivo.
    * @return RespuestaServicio La respuesta de la validación.
    */
   @GET
   @Path("/tercero/validar")
   @Consumes({ MediaType.APPLICATION_JSON })
   RespuestaServicio validar(@QueryParam("nombreArchivo") String nombreArchivo);
   
   
   /**
    * Importar terceros por medio de archivo.
    * @param archivoImporte El archivo a ser importado.
    * @param empresaId Id de la emprea.
    * @param usuarioId Id del usuario que modifica.
    * @return ResultadoImpoTerceros respuesta de la importación.
    */
    @POST
    @Path("/tercero/importar")
    @Consumes({ MediaType.APPLICATION_JSON })
    ResultadoImpoTerceros importar(InfoMedia archivoImporte, @QueryParam("empresaId") Integer empresaId,
            @QueryParam("usuarioId") Integer usuarioId);

}
