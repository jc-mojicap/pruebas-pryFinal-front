/*
* Archivo: TrdTipoDatoRestIface.java
* Fecha creacion = 06/06/2017
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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.trd.model.TrdTipoDato;

/**
 * Interface recurso TrdTipoDato.
 * @author JuanMojica
 *
 */
@Path("/trd")
public interface TrdTipoDatoRestIface {
    
    /**
     * Lista todos los tipos de dato.
     * @return Lista con todos los tipos de dato.
     */
    @GET
    @Path("/tipoDato/all")
    @Produces({MediaType.APPLICATION_JSON})
    List<TrdTipoDato> listarTodos();
    
    /**
     * Busca un tipo de dato por su id.
     * @param id Identificador del tipo de dato.
     * @return Tipo de dato asociado.
     */
    @GET
    @Path("/tipoDato/id")
    @Produces({MediaType.APPLICATION_JSON})
    TrdTipoDato obtenerPorId(@QueryParam("id") Integer id);
    
}
