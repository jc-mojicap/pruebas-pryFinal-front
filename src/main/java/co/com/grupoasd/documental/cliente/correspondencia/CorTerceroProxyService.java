/*
* Archivo: CorTerceroProxyService.java
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
package co.com.grupoasd.documental.cliente.correspondencia;

import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.zkoss.zk.ui.Sessions;

import co.com.grupoasd.documental.cliente.comun.AuthorizationFilter;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.correspondencia.iface.CorTerceroRestIface;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTercero;
import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.comun.dto.Paginacion;
import co.com.grupoasd.documental.presentacion.comun.dto.RespuestaServicio;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.service.correspondencia.dto.ResultadoImpoTerceros;

/**
 * Proxy que permite la invocación remota de los servicios del recurso CorTercero.
 * @author JuanMojica
 *
 */
public final class CorTerceroProxyService {

	/**
	 * Constructor privado. Esta clase no debe instanciarse.
	 */
	private CorTerceroProxyService(){
		
	}
	
	/**
	 * Lista los CorTercero por nombre.
	 * @param empresaId Empresa sobre la cual se realiza la búsqueda.
	 * @param nombre Nombre de los CorTerceros a buscar.
	 * @param empresaId Id de empresa a filtrar.
	 * @param inactivo: <code>true</code> si se desea que liste los usuarios inactivos, <code>false</code> en caso contrario.
	 * @return Lista con los CorTercero correspondientes.
	 */
	public static List<CorTercero> listarPorEmpresaYNombre(final Integer empresaId, final String nombre, final boolean inactivo){
		return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).listarPorEmpresaYNombre(empresaId, nombre, inactivo);
	}
	
	/**
	 * Lista los CorTercero por numero.
	 * @param empresaId Empresa sobre la cual se realiza la búsqueda.
	 * @param numero Numero de los CorTercero a buscar.
	 * @param empresaId Empresa sobre la cual se realiza la búsqueda.
	 * @return Lista con los CorTercero correspondientes.
	 */
	public static List<CorTercero> listarPorEmpresaYNumero(final Integer empresaId, final String numero, final boolean inactivo){
		return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).listarPorEmpresaYNumero(empresaId, numero, inactivo);
	}
	
	/**
     * Busca los terceror por su número, nombre, responsable, dirección y/o municipio.
     * @param empresaId Identificador de la empresa sobre la cual se va a realizar la búsqueda.
     * @param valor valor para realizar la busqueda.
     * @return Lista con los resultados asociados.
     */
    public static List<CorTercero> listarPorNumeroNombreResponsableDireccionMunicipio(final Integer empresaId, final String valor){
        return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).listarPorNumeroNombreResponsableDireccionMunicipio(empresaId, valor);
    }
    
    /**
    * @author cestrada
    * Busca todos los terceros por valores de paginación.
    * @param empresaId Id de la empresa.
    * @param paginacion Valores para la paginación.
    * @return Lista con los terceros páginados.
    */
    public static List<CorTercero> listarTodos(final Integer empresaId, final Paginacion paginacion) {
        return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).listar(empresaId, paginacion.getFirstResult(),
                paginacion.getMaxResults(), paginacion.getStrOrderBy(), paginacion.isOrderDesc());
    }
    
    /**
     * @author cestrada
     * contarTodos.
     * @param empresaId Id de la empresa.
     * @return Integer cantidad de terceros.
     */
    public static Integer contarTodos(final Integer empresaId) {
        return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).contar(empresaId);
    }
    
    /**
    * @author cestrada
    * Busca los terceror por su número y valores de paginación.
    * @param empresaId Id de la empresa.
    * @param numero Número del tercero.
    * @param paginacion Valores para la paginación.
    * @return Lista con los terceros páginados.
    */
    public static List<CorTercero> listarPorNumeroConPaginacion(final Integer empresaId, final String numero,
            final Paginacion paginacion) {
        return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).listarPorNumeroConPaginacion(empresaId, numero,
                paginacion.getFirstResult(), paginacion.getMaxResults(), paginacion.getStrOrderBy(),
                paginacion.isOrderDesc());
    }
    
    /**
     * @author cestrada
     * contarPorNumero.
     * @param empresaId Id de la empresa.
     * @param numero Número de identificación de los CorTerceros a buscar.
     * @return Integer cantidad de terceros.
     */
    public static Integer contarPorNumero(final Integer empresaId, final String numero) {
        return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).contarPorNumero(empresaId, numero);
    }

    /**
    * @author cestrada
    * Busca los terceror por su nombre y valores de paginación.
    * @param empresaId Id de la empresa.
    * @param nombre Nombre del tercero.
    * @param paginacion Valores para la paginación.
    * @return Lista con los terceros páginados.
    */
    public static List<CorTercero> listarPorNombreConPaginacion(final Integer empresaId, final String nombre,
            final Paginacion paginacion) {
        return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).listarPorNombreConPaginacion(empresaId, nombre,
                paginacion.getFirstResult(), paginacion.getMaxResults(), paginacion.getStrOrderBy(),
                paginacion.isOrderDesc());
    }

    /**
     * contarPorNombre.
     * @param empresaId Id de la empresa.
     * @param nombre Nombre de los CorTerceros a buscar.
     * @return Integer cantidad de terceros.
     */
    public static Integer contarPorNombre(final Integer empresaId, final String nombre) {
        return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).contarPorNombre(empresaId, nombre);
    }

    /**
    * @author cestrada
    * Busca los terceror por su número, nombre y valores de paginación.
    * @param empresaId Id de la empresa.
    * @param numero Número del tercero.
    * @param nombre Nombre del tercero.
    * @param paginacion Valores para la paginación.
    * @return Lista con los terceros páginados.
    */
    public static List<CorTercero> listarPorNumeroNombreConPaginacion(final Integer empresaId, final String numero,
            final String nombre, final Paginacion paginacion) {
        return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).listarPorNumeroNombreConPaginacion(empresaId,
                numero, nombre, paginacion.getFirstResult(), paginacion.getMaxResults(), paginacion.getStrOrderBy(),
                paginacion.isOrderDesc());
    }
    
    /**
     * @author cestrada
     * contarPorNumeroNombre.
     * @param empresaId Id de la empresa.
     * @param numero Número de identificación de los CorTerceros a buscar.
     * @param nombre Nombre del tercero.
     * @return Integer cantidad de terceros.
     */
    public static Integer contarPorNumeroNombre(final Integer empresaId, final String numero, final String nombre) {
        return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).contarPorNumeroNombre(empresaId, numero,
                nombre);
    }
    
    /**
     * @author cestrada
     * guardar.
     * @param tercero Tercero a guardar.
     * @return CorTercero el tercero creado o actualizado.
     */
    public static CorTercero guardar(final CorTercero tercero) {
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(SessionUtil.getAuthToken(Sessions.getCurrent())));
        return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).guardar(tercero);
    }
    
    /**
     * @author cestrada
     * inactivar.
     * @param tercero Tercero ser inactivar.
     * @return CorTercero el tercero inactivado.
     */
    public static CorTercero inactivar(final CorTercero tercero) {
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(SessionUtil.getAuthToken(Sessions.getCurrent())));
        return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).inactivar(tercero);
    }
    
    /**
     * @author cestrada
     * validar.
     * @param nombreArchivo Nombre del archivo a importar.
     * @return RespuestaServicio Respuesta de la validación.
     */
    public static RespuestaServicio validar(final String nombreArchivo) {
        return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).validar(nombreArchivo);
    }
    
    /**
     * Importar terceros por medio de archivo.
     * @param archivoImporte El archivo a ser importado.
     * @param empresaId Id de la emprea.
     * @param usuarioId Id del usuario que modifica.
     * @return ResultadoImpoTerceros respuesta de la importación.
     */
    public static ResultadoImpoTerceros importar(final InfoMedia archivoImporte, final Integer empresaId, final Integer usuarioId) {
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(SessionUtil.getAuthToken(Sessions.getCurrent())));
        return ServiciosEndpoint.get().proxy(CorTerceroRestIface.class).importar(archivoImporte, empresaId, usuarioId);
    }
    
}
