/*
* Archivo: TrdMetadatoTipoDocmProxyService.java
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
import co.com.grupoasd.documental.cliente.trd.iface.TrdMetadatoTipoDocmRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdMetadatoTipoDocm;

/**
 * Proxy que permite la invocación remota de los servicios del recurso TrdMetadatoTipoDocm.
 * @author JuanMojica
 *
 */
public class TrdMetadatoTipoDocmProxyService {

    /**
     * obtenerPorMetadatoIdYTipoDocumentalId.
     * @param metadatoId Identificador del metadato.
     * @param tipoDocumentalId Identificador del tipo Documental.
     * @return TrdMetadatoTipoDocm asociado.
     */
    public static TrdMetadatoTipoDocm obtenerPorMetadatoIdYTipoDocumentalId(Integer metadatoId, Integer tipoDocumentalId){
        return ServiciosEndpoint.get().proxy(TrdMetadatoTipoDocmRestIface.class).obtenerPorMetadatoIdYTipoDocumentalId(metadatoId, tipoDocumentalId);
    }
    
    /**
     * listarPorMetadato.
     * @param metadatoId Identificador del metadato.
     * @return TrdMetadatoTipoDocm asociado.
     */
    public static List<TrdMetadatoTipoDocm> listarPorMetadato(Integer metadatoId){
        return ServiciosEndpoint.get().proxy(TrdMetadatoTipoDocmRestIface.class).listarPorMetadato(metadatoId);
    }
    
    /**
     * listarPorTipoDocumental.
     * @param tipoDocumentalId Identificador del tipo documental.
     * @return TrdMetadatoSubserie asociado.
     */
    public static List<TrdMetadatoTipoDocm> listarPorTipoDocumental(Integer tipoDocumentalId){
        return ServiciosEndpoint.get().proxy(TrdMetadatoTipoDocmRestIface.class).listarPorTipoDocumental(tipoDocumentalId);
    }
    
    /**
     * guardarTrdMetadatosSubserie.
     * @param token Token con identificación del usuario.
     * @param trdMetadatosTipoDoc Lista de TrdMetadatoTipoDocm a guardar.
     * @return lista de TrdMetadatoTipoDocm guardadas.
     */
    public static List<TrdMetadatoTipoDocm> guardarTrdMetadatosSubserie(Token token, List<TrdMetadatoTipoDocm> trdMetadatosTipoDoc){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdMetadatoTipoDocmRestIface.class).guardarTrdMetadatoTipoDocm(trdMetadatosTipoDoc);
    }
}
