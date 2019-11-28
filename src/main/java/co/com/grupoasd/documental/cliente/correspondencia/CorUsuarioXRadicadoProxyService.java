/*
 * Archivo: CorUsuarioXRadicadoProxyService.java
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
package co.com.grupoasd.documental.cliente.correspondencia;

import java.util.List;

import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.correspondencia.iface.CorUsuarioXRadicadoRestIface;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorUsuarioXRadicado;

/**
 * Proxy que permite la invocacion remota de los servicios del recurso CorUsuarioXRadicado.
 * @author cestrada
 *
 */
public class CorUsuarioXRadicadoProxyService {
       
    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private CorUsuarioXRadicadoProxyService(){
        
    }
    
    /**
     * listarPorUsuarioId.
     * @param usuarioId Identificador del usuario.
     * @return Lista de radicados por usuario.
     */
    public static List<CorUsuarioXRadicado> listarPorUsuarioId(Integer usuarioId){
        return ServiciosEndpoint.get().proxy(CorUsuarioXRadicadoRestIface.class).listarPorUsuarioId(usuarioId);
        
    }
    
    /**
     * listarPorRadicadoId.
     * @param radicadoId Identificador del radicado.
     * @return Lista de usuarios por radicado.
     */
    public static List<CorUsuarioXRadicado> listarPorRadicadoId(Long radicadoId){
        return ServiciosEndpoint.get().proxy(CorUsuarioXRadicadoRestIface.class).listarPorRadicadoId(radicadoId);
    }

    /**
     * listarTercerosConRadicados.
     * @return Lista de usuarios con radicados.
     */
    public static List<CorUsuarioXRadicado> listarUsuariosConRadicados(){
        return ServiciosEndpoint.get().proxy(CorUsuarioXRadicadoRestIface.class).listarUsuariosConRadicados();
    }
    
}
