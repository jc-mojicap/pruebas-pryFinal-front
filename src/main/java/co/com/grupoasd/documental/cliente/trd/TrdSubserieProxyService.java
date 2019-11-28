/*
* Archivo: TrdSubserieProxyService.java
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
import co.com.grupoasd.documental.cliente.trd.iface.TrdSubserieRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdSubserie;

public class TrdSubserieProxyService {

    private TrdSubserieProxyService() {

    }

    public static TrdSubserie obtenerPorTrdSubserieId(Integer trdSubserieId){
        return ServiciosEndpoint.get().proxy(TrdSubserieRestIface.class).obtenerPorTrdSubserieId(trdSubserieId);
    }
    
    public static List<TrdSubserie> listarPorSubserieId(Integer subserieId){
        return ServiciosEndpoint.get().proxy(TrdSubserieRestIface.class).listarPorSubserieId(subserieId);
    }
    
    public static List<TrdSubserie> listarPorSerieId(Integer serieId){
        return ServiciosEndpoint.get().proxy(TrdSubserieRestIface.class).listarPorSerieId(serieId);
    }
    
    public static TrdSubserie crearTrdSubserie(Token token, TrdSubserie trdSubserie){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdSubserieRestIface.class).crearTrdSubserie(trdSubserie);
    }
    
    public static TrdSubserie actualizarTrdSubserie(Token token, TrdSubserie trdSubserie){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdSubserieRestIface.class).actualizarTrdSubserie(trdSubserie);
    }
    
}
