/*
* Archivo: TrdTipoDocumetalProxyService.java
* Fecha creacion = 24/06/2017
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
import co.com.grupoasd.documental.cliente.trd.iface.TrdTipoDocumentalRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdTipoDocumental;

public class TrdTipoDocumentalProxyService {

    
    private TrdTipoDocumentalProxyService() {

    }

    public static List<TrdTipoDocumental> listarPorTipoDocumentalId(Integer tipoDocumentalId){
        return ServiciosEndpoint.get().proxy(TrdTipoDocumentalRestIface.class).listarPorTipoDocumentalId(tipoDocumentalId);
    }
    
    public static List<TrdTipoDocumental> listarPorSubserieId(Integer subserieId){
        return ServiciosEndpoint.get().proxy(TrdTipoDocumentalRestIface.class).listarPorSubserieId(subserieId);
    }
    
    public static TrdTipoDocumental crearTrdTipoDocumental(Token token, TrdTipoDocumental trdTipoDocumental){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdTipoDocumentalRestIface.class).crearTrdTipoDocumental(trdTipoDocumental);
    }
    
    public static TrdTipoDocumental actualizarTrdTipoDocumental(Token token, TrdTipoDocumental trdTipoDocumental){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdTipoDocumentalRestIface.class).actualizarTrdTipoDocumental(trdTipoDocumental);
    }
    
}
