/*
* Archivo: TrdDocumentoProxyService.java
* Fecha creacion = 23/06/2017
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
import co.com.grupoasd.documental.cliente.trd.iface.TrdExpedienteRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdExpediente;

/**
 * Proxy que permite la invocación remota de los servicios del recurso TrdDocumento.
 * @author Luisa Hernández.
 *
 */
public final class TrdExpedienteProxyService {

    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private TrdExpedienteProxyService() {
        
    }

    /**
     * Lista todas los documentos.
     * @return Lista con todos los documentos.
     */
    public static List<TrdExpediente> listarTodos(){
        return ServiciosEndpoint.get().proxy(TrdExpedienteRestIface.class).listarTodos();
    }
    
    /**
     * Lista todos los documentos.
     * @return Lista con todos los documentos.
     */
    public static List<TrdExpediente> listarPorTrdSubserie(Integer trdSubserieId){
        return ServiciosEndpoint.get().proxy(TrdExpedienteRestIface.class).listarPorTrdSubserie(trdSubserieId);
    }
    
    /**
     * Lista todas las documentos.
     * @return Lista con todas los documentos.
     */
    public static List<TrdExpediente> listarPorDocumento(Integer documentoId){
        return ServiciosEndpoint.get().proxy(TrdExpedienteRestIface.class).listarPorDocumento(documentoId);
    }
    
    /**
     * Lista todas las documentos.
     * @return Lista con todas los documentos.
     */
    public static List<TrdExpediente> listarPorExpediente(Integer documentoId){
        return ServiciosEndpoint.get().proxy(TrdExpedienteRestIface.class).listarPorExpediente(documentoId);
    }
    
    
}
