/*
* Archivo: TrdEstructuraProxyService.java
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

import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.trd.iface.TrdEstructuraRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdEstructura;

/**
 * Proxy que permite la invocación remota de los servicios del recurso TrdEstructura.
 * @author JuanMojica
 *
 */
public final class TrdEstructuraProxyService {

    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private TrdEstructuraProxyService() {

    }

    /**
     * Lista todas las estructuras.
     * @return Lista con todas las estructuras.
     */
    public static List<TrdEstructura> listarTodos(){
        return ServiciosEndpoint.get().proxy(TrdEstructuraRestIface.class).listarTodos();
    }

    /**
     * Obtiene una estructura por su id.
     * @param id Identificador de la estructura. 
     * @return Estructura asociada.
     */
    public static TrdEstructura obtenerPorId(Integer id){
        return ServiciosEndpoint.get().proxy(TrdEstructuraRestIface.class).obtenerPorId(id);
    }
    
}
