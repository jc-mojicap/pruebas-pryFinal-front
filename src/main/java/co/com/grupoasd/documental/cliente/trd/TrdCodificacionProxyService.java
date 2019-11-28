/*
* Archivo: TrdCodificacionProxyService.java
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
package co.com.grupoasd.documental.cliente.trd;

import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import co.com.grupoasd.documental.cliente.comun.AuthorizationFilter;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.trd.iface.TrdCodificacionRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdCodificacion;

/**
 * Proxy que permite la invocación remota de los servicios del recurso TrdCodificacion.
 * @author JuanMojica
 *
 */
public final class TrdCodificacionProxyService {

    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private TrdCodificacionProxyService() {
        
    }

    /**
     * Lista todas las codificaciones.
     * @return Lista con todas las codificaciones.
     */
    public static List<TrdCodificacion> listarTodos(){
        return ServiciosEndpoint.get().proxy(TrdCodificacionRestIface.class).listarTodos();
    }
    
    /**
     * Lista todas las codificaciones.
     * @return Lista con todas las codificaciones.
     */
    public static List<TrdCodificacion> listarPorEmpresaId(Integer empresaId){
        return ServiciosEndpoint.get().proxy(TrdCodificacionRestIface.class).listarPorEmpresa(empresaId);
    }
    
    /**
     * Lista todas las codificaciones.
     * @return Lista con todas las codificaciones.
     */
    public static List<TrdCodificacion> listarPorEstructuraId(Integer estructuraId){
        return ServiciosEndpoint.get().proxy(TrdCodificacionRestIface.class).listarPorEstructura(estructuraId);
    }
    
    /**
     * Lista todas las codificaciones.
     * @return Lista con todas las codificaciones.
     */
    public static List<TrdCodificacion> listarPorEmpresaIdYEstructuraId(Integer empresaId, Integer estructuraId){
        return ServiciosEndpoint.get().proxy(TrdCodificacionRestIface.class).listarPorEmpresaYEstructura(empresaId, estructuraId);
    }
    
    /**
     * guardarCodificaciones.
     * @param token Token con identificación del usuario.
     * @param trdCodificaciones Lista de codificaciones a guardar.
     * @return Lista de codificaciones guardadas.
     */
    public static List<TrdCodificacion> guardarCodificaciones(Token token, List<TrdCodificacion> trdCodificaciones){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdCodificacionRestIface.class).guardarCodificaciones(trdCodificaciones);
    }
    
}
