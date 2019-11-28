/*
* Archivo: TrdTipoDatoProxyService.java
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
import co.com.grupoasd.documental.cliente.trd.iface.TrdTipoDatoRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdTipoDato;

/**
 * Proxy que permite la invocación remota de los servicios del recurso TrdTipoDato.
 * @author JuanMojica
 *
 */
public final class TrdTipoDatoProxyService {

    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private TrdTipoDatoProxyService() {
        
    }
    
    /**
     * Lista todos los tipos de dato.
     * @return Lista con todos los tipos de dato.
     */
    public static List<TrdTipoDato> listarTodos(){
        return ServiciosEndpoint.get().proxy(TrdTipoDatoRestIface.class).listarTodos();
    }

    /**
     * Obtiene un tipo de dato por su id.
     * @param id Identificador del tipo de dato. 
     * @return Tipo de dato asociado.
     */
    public static TrdTipoDato obtenerPorId(Integer id){
        return ServiciosEndpoint.get().proxy(TrdTipoDatoRestIface.class).obtenerPorId(id);
    }
}
