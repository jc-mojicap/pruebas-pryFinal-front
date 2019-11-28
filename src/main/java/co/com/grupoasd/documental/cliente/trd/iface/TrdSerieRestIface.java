/*
* Archivo: TrdSerieRestIface.java
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
package co.com.grupoasd.documental.cliente.trd.iface;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.trd.model.TrdSerie;

/**
 * Interface recurso TrdSerie.
 * @author JuanMojica
 *
 */
@Path("/trd")
public interface TrdSerieRestIface {

    @GET
    @Path("/serie/trdSerieId")
    @Produces({MediaType.APPLICATION_JSON})
    TrdSerie obtenerPorTrdSerieId(@QueryParam("trdSerieId") Integer trdSerieId);
    
    @GET
    @Path("/serie/serieId")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdSerie> listarPorSerieId(@QueryParam("serieId") Integer serieId);
    
    @GET
    @Path("/serie/areaId")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdSerie> listarPorAreaId(@QueryParam("areaId") Integer areaId);
    
    @POST
    @Path("/serie")
    @Consumes({MediaType.APPLICATION_JSON})
    TrdSerie crearTrdSerie(TrdSerie trdSerie);
    
    @PUT
    @Path("/serie")
    @Consumes({MediaType.APPLICATION_JSON})
    TrdSerie actualizarTrdSerie(TrdSerie trdSerie);
    
}
