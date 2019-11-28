/*
 * Archivo: CorTerceroXRadicadoProxyService.java
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
import co.com.grupoasd.documental.cliente.correspondencia.iface.CorTerceroXRadicadoRestIface;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTerceroXRadicado;

/**
 * Proxy que permite la invocacion remota de los servicios del recurso CorTerceroXRadicado.
 * @author cestrada
 *
 */
public class CorTerceroXRadicadoProxyService {
       
    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private CorTerceroXRadicadoProxyService(){
        
    }
    
    /**
     * listarPorTerceroId.
     * @param terceroId Identificador del tercero.
     * @return Lista de radicados por tercero.
     */
    public static List<CorTerceroXRadicado> listarPorTerceroId(Integer terceroId){
        return ServiciosEndpoint.get().proxy(CorTerceroXRadicadoRestIface.class).listarPorTerceroId(terceroId);
        
    }
    
    /**
     * listarPorRadicadoId.
     * @param radicadoId Identificador del radicado.
     * @return Lista de terceros por radicado.
     */
    public static List<CorTerceroXRadicado> listarPorRadicadoId(Long radicadoId){
        return ServiciosEndpoint.get().proxy(CorTerceroXRadicadoRestIface.class).listarPorRadicadoId(radicadoId);
    }

    /**
     * listarTercerosConRadicados.
     * @return Lista de terceros con radicados.
     */
    public static List<CorTerceroXRadicado> listarTercerosConRadicados(){
        return ServiciosEndpoint.get().proxy(CorTerceroXRadicadoRestIface.class).listarTercerosConRadicados();
    }
}
