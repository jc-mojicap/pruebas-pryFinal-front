/*
* Archivo: TrdSerieProxyService.java
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
import co.com.grupoasd.documental.cliente.trd.iface.TrdSerieRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdSerie;

public class TrdSerieProxyService {

    private TrdSerieProxyService() {

    }

    public static TrdSerie obtenerPorTrdSerieId(Integer trdSerieId){
        return ServiciosEndpoint.get().proxy(TrdSerieRestIface.class).obtenerPorTrdSerieId(trdSerieId);
    }
    
    public static List<TrdSerie> listarPorSerieId(Integer serieId){
        return ServiciosEndpoint.get().proxy(TrdSerieRestIface.class).listarPorSerieId(serieId);
    }
    
    public static List<TrdSerie> listarPorAreaId(Integer areaId){
        return ServiciosEndpoint.get().proxy(TrdSerieRestIface.class).listarPorAreaId(areaId);
    }
    
    public static TrdSerie crearTrdSerie(Token token, TrdSerie trdSerie){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdSerieRestIface.class).crearTrdSerie(trdSerie);
    }
    
    public static TrdSerie actualizarTrdSerie(Token token, TrdSerie trdSerie){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdSerieRestIface.class).actualizarTrdSerie(trdSerie);
    }
    
}
