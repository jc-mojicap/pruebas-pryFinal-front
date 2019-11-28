/*
* Archivo: FormatosPermitidosArchivoRestIface.java
* Fecha creacion = 22/05/2017
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
package co.com.grupoasd.documental.cliente.correspondencia.iface;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.correspondencia.model.FormatosPermitidosArchivo;

/**
 * Interface recurso FormatosPermitidosArchivo.
 * @author JuanMojica
 *
 */
@Path("/correspondencia")
public interface FormatosPermitidosArchivoRestIface {
    
    
    /**
     * Busca un CorrespondenciaBitacoraRadicado por id.
     * @param id Identificador del CorrespondenciaBitacoraRadicado.
     * @return El CorrespondenciaBitacoraRadicado asociado.
     */
    @GET
    @Path("/formatosPermitidosArchivoid")
    @Produces({MediaType.APPLICATION_JSON})
    FormatosPermitidosArchivo obtenerPorId(@QueryParam("id") Integer id);
    
    /**
     * Busca un CorrespondenciaBitacoraRadicado por radicado.
     * @param radicadoId Identificador del radicado.
     * @return El CorrespondenciaBitacoraRadicado asociado.
     */
    @GET
    @Path("/formatosPermitidosArchivoall")
    @Produces({MediaType.APPLICATION_JSON})
    List<FormatosPermitidosArchivo> listarTodos();
    

}
