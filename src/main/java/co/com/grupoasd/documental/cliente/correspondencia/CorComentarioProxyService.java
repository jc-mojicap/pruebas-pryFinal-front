/*
* Archivo: CorComentarioProxyService.java
* Fecha creacion = 31/03/2017
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
package co.com.grupoasd.documental.cliente.correspondencia;

import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import co.com.grupoasd.documental.cliente.comun.AuthorizationFilter;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.correspondencia.iface.CorComentarioRestIface;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorComentario;

/**
 * Proxy que permite la invocación remota de los servicios del recurso CorComentario.
 * @author JuanMojica
 *
 */
public final class CorComentarioProxyService {

    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private CorComentarioProxyService(){
        
    }
    
    /**
     * Carga un comentario por su id.
     * @param id Identificador del comentario.
     * @return El comentario asociado.
     */
    public static CorComentario obtenerPorId(final Long id){
        return ServiciosEndpoint.get().proxy(CorComentarioRestIface.class).obtenerPorId(id);
    }
    
    /**
     * Lista comentarios por el radicado al que pertenecen.
     * @param id Identificador del radicado.
     * @return Lista con los radicados.
     */
    public static List<CorComentario> obtenerPorRadicado(final Long id){
        return ServiciosEndpoint.get().proxy(CorComentarioRestIface.class).obtenerPorRadicado(id);
    }
    
    /**
     * Crea un comentario.
     * @param token Token con identificacion del usuario.
     * @param corComentario Objeto CorComentario.
     * @return Objeto CorComentario creado con identificador asignado. 
     */
    public static CorComentario crear(Token token, CorComentario corComentario){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(CorComentarioRestIface.class).crear(corComentario);
    }
    
}
