/*
* Archivo: CorTerceroService.java
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
package co.com.grupoasd.documental.presentacion.service.correspondencia.iface;

import java.util.List;

import co.com.grupoasd.documental.cliente.correspondencia.model.CorTercero;
import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.comun.dto.Paginacion;
import co.com.grupoasd.documental.presentacion.comun.dto.RespuestaServicio;
import co.com.grupoasd.documental.presentacion.service.correspondencia.dto.ResultadoImpoTerceros;

/**
 * Servicios del recurso CorTercero.
 * @author JuanMojica
 *
 */
public interface CorTerceroService {

	/**
	 * Lista los CorTercero por nombre.
	 * @param nombre Nombre de los CorTerceros a buscar.
	 * @param empresaId Empresa sobre la cual se realiza la búsqueda.
	 * @param inactivo: <code>true</code> si se desea que liste los usuarios inactivos, <code>false</code> en caso contrario.
	 * @return Lista con los CorTercero correspondientes.
	 */
	List<CorTercero> listarPorEmpresaYNombre(Integer empresaId, String nombre, boolean inactivo);
	
	/**
	 * Lista los CorTercero por numero.
	 * @param numero Numero de los CorTercero a buscar.
	 * @param empresaId Empresa sobre la cual se realiza la búsqueda.
	 * @param inactivo: <code>true</code> si se desea que liste los usuarios inactivos, <code>false</code> en caso contrario.
	 * @return Lista con los CorTercero correspondientes.
	 */
	List<CorTercero> listarPorEmpresaYNumero(Integer empresaId, String numero, boolean inactivo);
	
	/**
     * Busca los terceror por su número, nombre, responsable, dirección y/o municipio.
     * @param empresaId Identificador de la empresa sobre la cual se va a realizar la búsqueda.
     * @param valor valor para realizar la busqueda.
     * @return Lista con los resultados asociados.
     */
    List<CorTercero> listarPorNumeroNombreResponsableDireccionMunicipio(Integer empresaId, String valor);
    
    
    /**
    * @author cestrada
    * Busca todos los terceros por valores de paginación.
    * @param empresaId Id de la empresa.
    * @param paginacion valores la paginación y ordenamiento.
    * @return Lista con los terceros páginados.
    */
    List<CorTercero> listarTodos(Integer empresaId, Paginacion paginacion);
   
    /**
     * contarTodos.
     * @param empresaId Id de la empresa.
     * @return Integer cantidad de terceros.
     */
    Integer contarTodos(Integer empresaId);
    
    /**
    * @author cestrada
    * Busca los terceror por su número y valores de paginación.
    * @param empresaId Id de la empresa.
    * @param numero Número del tercero.
    * @param paginacion valores la paginación y ordenamiento.
    * @return Lista con los terceros páginados.
    */
    List<CorTercero> listarPorNumeroConPaginacion(Integer empresaId, String numero, Paginacion paginacion);
   
    /**
     * contarPorNumero.
     * @param empresaId Id de la empresa.
     * @param numero Número de identificación de los CorTerceros a buscar.
     * @return Integer cantidad de terceros.
     */
    Integer contarPorNumero(Integer empresaId, String numero);
    
    /**
    * @author cestrada
    * Busca los terceror por su nombre y valores de paginación.
    * @param empresaId Id de la empresa.
    * @param nombre Nombre del tercero.
    * @param paginacion valores la paginación y ordenamiento.
    * @return Lista con los terceros páginados.
    */
    List<CorTercero> listarPorNombreConPaginacion(Integer empresaId, String nombre, Paginacion paginacion);
   
    /**
     * contarPorNombre.
     * @param empresaId Id de la empresa.
     * @param nombre Nombre de los CorTerceros a buscar.
     * @return Integer cantidad de terceros.
     */
    Integer contarPorNombre(Integer empresaId, String nombre);
    
    /**
    * @author cestrada
    * Busca los terceror por su número, nombre y valores de paginación.
    * @param empresaId Id de la empresa.
    * @param numero Número del tercero.
    * @param nombre Nombre del tercero.
    * @param paginacion valores la paginación y ordenamiento.
    * @return Lista con los terceros páginados.
    */
    List<CorTercero> listarPorNumeroNombreConPaginacion(Integer empresaId, String numero, String nombre,
            Paginacion paginacion);

    /**
     * contarPorNumero.
     * @param empresaId Id de la empresa.
     * @param numero Número de identificación de los CorTerceros a buscar.
     * @param nombre Nombre del tercero.
     * @return Integer cantidad de terceros.
     */
    Integer contarPorNumeroNombre(Integer empresaId, String numero, String nombre);
    
    /**
     * Crea o actualiza un tercero.
     * @param tercero Tercero a actualizar o crear.
     * @return CorTercero.
     */
    CorTercero guardar(CorTercero tercero);
    
    /**
     * Inactiva un tercero.
     * @param tercero Tercero a inactivar.
     * @return CorTercero Tercero inactivado.
     */
    CorTercero inactivar(CorTercero tercero);
    
    /**
     * Valida la extensión del nombre del archivo a importar.
     * @param nombreArchivo Nombre del archivo a importar.
     * @return RespuestaServicio Respuesta de la validación.
     */
    public RespuestaServicio validar(final String nombreArchivo);
    
    /**
     * Importar terceros por medio de archivo.
     * @param archivoImporte El archivo a ser importado.
     * @param empresaId Id de la emprea.
     * @param usuarioId Id del usuario que modifica.
     * @return ResultadoImpoTerceros respuesta de la importación.
     */
    public ResultadoImpoTerceros importar(final InfoMedia archivoImporte, Integer empresaId, Integer usuarioId);
    
}
