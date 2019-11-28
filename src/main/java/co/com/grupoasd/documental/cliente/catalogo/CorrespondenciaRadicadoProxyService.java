/*
 * Archivo: EstadoRadicadoProxyService.java
 * Fecha creacion: 14/03/2017
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
package co.com.grupoasd.documental.cliente.catalogo;

import java.util.List;
import co.com.grupoasd.documental.cliente.catalogo.iface.CorrespondenciaRadicadoRestIface;
import co.com.grupoasd.documental.cliente.catalogo.model.EstadoRadicado;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;

/**
 * Proxy que permite la invocacion remota de los servicios del recurso correspondencia radicado. 
 * 
 * @author cestrada
 *
 */
public final class CorrespondenciaRadicadoProxyService {
    
    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private CorrespondenciaRadicadoProxyService() {

    }
    
    /**
     * Lista los estados radicado.
     * @return Listado con estados radicado. Si no existen retorna vacio.
     * @author cestrada
     */
    public static List<EstadoRadicado> listar() {
        return ServiciosEndpoint.get().proxy(CorrespondenciaRadicadoRestIface.class).listarEstadosRadicado();
    }

}
