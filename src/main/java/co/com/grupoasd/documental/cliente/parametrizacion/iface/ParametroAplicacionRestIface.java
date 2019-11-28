/*
* Archivo: ParametroAplicacionRestIface.java
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
package co.com.grupoasd.documental.cliente.parametrizacion.iface;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.parametrizacion.model.ParametroAplicacion;

@Path("/parametrizacion")
public interface ParametroAplicacionRestIface {

    @GET
    @Path("/parametroAplicacions")
    @Consumes({ MediaType.APPLICATION_JSON })
    List<ParametroAplicacion> listarTodos();
    
    @GET
    @Path("/parametroAplicacion")
    @Produces({ MediaType.APPLICATION_JSON })
    ParametroAplicacion obtenerPorId(@QueryParam("parametroId") String parametroId, @QueryParam("empresaId") int empresaId);
    
    @POST
    @Path("/parametroAplicacion")
    @Consumes({ MediaType.APPLICATION_JSON })
    List<ParametroAplicacion> guardarParametros(List<ParametroAplicacion> parametroAplicacion);
}
