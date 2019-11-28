/*
* Archivo: TrdAreaProxyService.java
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
import co.com.grupoasd.documental.cliente.trd.iface.TrdAreaRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdArea;

public final class TrdAreaProxyService {

    private TrdAreaProxyService() {

    }

    public static TrdArea obtenerPorTrdAreaId(Integer trdAreaId){
        return ServiciosEndpoint.get().proxy(TrdAreaRestIface.class).obtenerPorTrdAreaId(trdAreaId);
    }
    
    public static List<TrdArea> listarPorAreaId(Integer areaId){
        return ServiciosEndpoint.get().proxy(TrdAreaRestIface.class).listarPorAreaId(areaId);
    }
    
    public static List<TrdArea> listarPorTrdId(Integer trdId){
        return ServiciosEndpoint.get().proxy(TrdAreaRestIface.class).listarPorTrdId(trdId);
    }
    
    public static List<TrdArea> listarPorEmpresa(Integer empresaId){
        return ServiciosEndpoint.get().proxy(TrdAreaRestIface.class).listarPorEmpresa(empresaId);
    }
    
    public static TrdArea crearTrdArea(Token token, TrdArea trdArea){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdAreaRestIface.class).crearTrdArea(trdArea);
    }
    
    public static TrdArea actualizarTrdArea(Token token, TrdArea trdArea){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdAreaRestIface.class).actualizarTrdArea(trdArea);
    }
    
}
