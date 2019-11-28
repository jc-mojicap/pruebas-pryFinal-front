/*
* Archivo: TrdMetadatoSubserieProxyService.java
* Fecha creacion = 20/06/2017
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
import co.com.grupoasd.documental.cliente.trd.iface.TrdMetadatoSubserieRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdMetadatoSubserie;

/**
 * Proxy que permite la invocación remota de los servicios del recurso TrdMetadatoSubserie.
 * @author JuanMojica
 *
 */
public class TrdMetadatoSubserieProxyService {
    
    /**
     * obtenerPorMetadatoIdYSubserieId.
     * @param metadatoId Identificador del metadato.
     * @param subserieId Identificador de la subserie.
     * @return TrdMetadatoSubserie asociado.
     */
    public static TrdMetadatoSubserie obtenerPorMetadatoIdYSubserieId(Integer metadatoId, Integer subserieId){
        return ServiciosEndpoint.get().proxy(TrdMetadatoSubserieRestIface.class).obtenerPorMetadatoIdYSubserieId(metadatoId, subserieId);
    }
    
    /**
     * listarPorMetadato.
     * @param metadatoId Identificador del metadato.
     * @return TrdMetadatoSubserie asociado.
     */
    public static List<TrdMetadatoSubserie> listarPorMetadato(Integer metadatoId){
        return ServiciosEndpoint.get().proxy(TrdMetadatoSubserieRestIface.class).listarPorMetadato(metadatoId);
    }
    
    /**
     * listarPorSubserie.
     * @param subserieId Identificador de la subserie.
     * @return TrdMetadatoSubserie asociado.
     */
    public static List<TrdMetadatoSubserie> listarPorSubserie(Integer subserieId){
        return ServiciosEndpoint.get().proxy(TrdMetadatoSubserieRestIface.class).listarPorSubserie(subserieId);
    }
    
    /**
     * guardarTrdMetadatosSubserie.
     * @param token Token con identificación del usuario.
     * @param trdMetadatosSubserie Lista de trdMetadatoSubserie a guardar.
     * @return lista de trdMetadatoSubserie guardadas.
     */
    public static List<TrdMetadatoSubserie> guardarTrdMetadatosSubserie(Token token, List<TrdMetadatoSubserie> trdMetadatosSubserie){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdMetadatoSubserieRestIface.class).guardarTrdMetadatoSubserie(trdMetadatosSubserie);
    }
    
}
