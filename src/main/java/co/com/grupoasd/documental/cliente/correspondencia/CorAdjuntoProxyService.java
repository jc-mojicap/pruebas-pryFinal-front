/*
 * Archivo: CorAdjuntoProxyService.java
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
package co.com.grupoasd.documental.cliente.correspondencia;

import java.util.List;

import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.correspondencia.iface.CorAdjuntoRestIface;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorAdjunto;

/**
 * Proxy que permite la invocacion remota de los servicios del recurso CorAdjunto.
 * 
 * @author cestrada
 *
 */
public class CorAdjuntoProxyService {
    
    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private CorAdjuntoProxyService(){
        
    }
    
    /**
     * Lista los adjuntos de radicados.
     * @param radicadoId Identificador del radicado.
     * @param eliminado Adjunto eliminado.
     * @return Listado con adjuntos de radicados. Si no existen retorna vacio.
     * @author cestrada
     */
    public static List<CorAdjunto> listarPorRadicadoIdEliminado(Long radicadoId, Boolean eliminado) {
        return ServiciosEndpoint.get().proxy(CorAdjuntoRestIface.class).listarPorRadicadoIdEliminado(radicadoId, eliminado);
    }
    
    /**
     * Lista los adjuntos de radicados.
     * @param radicadoId Identificador del radicado.
     * @return Listado con adjuntos de radicados. Si no existen retorna vacio.
     * @author cestrada
     */
    public static List<CorAdjunto> listarPorRadicadoId(Long radicadoId) {
        return ServiciosEndpoint.get().proxy(CorAdjuntoRestIface.class).listarPorRadicadoId(radicadoId);
    }

    /**
     * Cantidad de adjuntos por radicado.
     * @param radicadoId Identificador del radicado.
     * @param eliminado Adjunto eliminado.
     * @return Integer cantidad de adjuntos.
     * @author cestrada
     */
    public static Integer contarPorRadicadoIdEliminado(Long radicadoId, Boolean eliminado) {
        return ServiciosEndpoint.get().proxy(CorAdjuntoRestIface.class).contarPorRadicadoIdEliminado(radicadoId, eliminado);
    }
    
    /**
     * Obtiene los byte de adjunto
     * @param radicadoId id radicado
     * @param adjuntoId id adjunto
     * @return byte[]
     */
    public static byte[] obtenerBytesAdjunto(Long radicadoId, Integer adjuntoId) {
    	return ServiciosEndpoint.get().proxy(CorAdjuntoRestIface.class).obtenerBytesAdjunto(radicadoId, adjuntoId);
    }
    
}
