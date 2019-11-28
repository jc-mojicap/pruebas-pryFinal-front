/*
* Archivo: FormatosPermitidosArchivoProxyService.java
* Fecha creacion = 22/05/2017
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

import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.correspondencia.iface.FormatosPermitidosArchivoRestIface;
import co.com.grupoasd.documental.cliente.correspondencia.model.FormatosPermitidosArchivo;

/**
 * Proxy que permite la invocación remota de los servicios del recurso FormatosPermitidosArchivo.
 * @author JuanMojica
 *
 */
public class FormatosPermitidosArchivoProxyService {

    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private FormatosPermitidosArchivoProxyService() {
        
    }
    
    /**
     * Obtiene un FormatosPermitidosArchivo por su id.
     * @param id Identificador del FormatosPermitidosArchivo.
     * @return FormatosPermitidosArchivo asociado.
     */
    public static FormatosPermitidosArchivo obtenerPorId(Integer id){
        return ServiciosEndpoint.get().proxy(FormatosPermitidosArchivoRestIface.class).obtenerPorId(id);
    }
    
    /**
     * Lista todos los FormatosPermitidosArchivo existentes en el sistema.
     * @return Lista con los FormatosPermitidosArchivo.
     */
    public static List<FormatosPermitidosArchivo> listarTodos(){
        return ServiciosEndpoint.get().proxy(FormatosPermitidosArchivoRestIface.class).listarTodos();
    }
    
}
