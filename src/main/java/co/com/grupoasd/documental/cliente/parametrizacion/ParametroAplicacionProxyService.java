/*
* Archivo: ParametroAplicacionProxyService.java
* Fecha creacion = 07/06/2017
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
package co.com.grupoasd.documental.cliente.parametrizacion;

import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import co.com.grupoasd.documental.cliente.comun.AuthorizationFilter;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.parametrizacion.iface.ParametroAplicacionRestIface;
import co.com.grupoasd.documental.cliente.parametrizacion.model.ParametroAplicacion;

public class ParametroAplicacionProxyService {

    private ParametroAplicacionProxyService() {

    }

    public static List<ParametroAplicacion> listarTodos(){
        return ServiciosEndpoint.get().proxy(ParametroAplicacionRestIface.class).listarTodos();
    }
    
    public static ParametroAplicacion obtenerPorIdYEmpresa(String parametroId, int empresaId){
        return ServiciosEndpoint.get().proxy(ParametroAplicacionRestIface.class).obtenerPorId(parametroId, empresaId);
    }
    
    public static List<ParametroAplicacion> guardarParametros(Token token, List<ParametroAplicacion> parametrosAplicacion){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(ParametroAplicacionRestIface.class).guardarParametros(parametrosAplicacion);
    }
    
}
