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
package co.com.grupoasd.documental.cliente.correspondencia;

import java.util.List;

import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.correspondencia.iface.CorEstadoRadicadoRestIface;
import co.com.grupoasd.documental.cliente.correspondencia.model.EstadoRadicado;

/**
 * Proxy que permite la invocacion remota de los servicios del recurso estado
 * radicado.
 * 
 * @author cestrada
 *
 */
public final class EstadoRadicadoProxyService {

    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private EstadoRadicadoProxyService() {

    }

    /**
     * Lista los estados radicado.
     * 
     * @return Listado con estados radicado. Si no existen retorna vacio.
     * @author cestrada
     */
    public static List<EstadoRadicado> listar() {
        return ServiciosEndpoint.get().proxy(CorEstadoRadicadoRestIface.class).listarEstadosRadicado();
    }

}
