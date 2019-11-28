/*
* Archivo: TrdMetadatoProxyService.java
* Fecha creacion = 12/06/2017
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
import co.com.grupoasd.documental.cliente.trd.iface.TrdMetadatoRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdMetadato;

/**
 * Proxy que permite la invocación remota de los servicios del recurso TrdMetadato.
 * @author JuanMojica
 *
 */
public final class TrdMetadatoProxyService {
    
    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private TrdMetadatoProxyService() {

    }

    /**
     * ListarTodos.
     * @return Lista de todos los metadatos.
     */
    public static List<TrdMetadato> listarTodos(){
        return ServiciosEndpoint.get().proxy(TrdMetadatoRestIface.class).listarTodos();
    }
    
    /**
     * listarPorIdTipoDatoNombre.
     * @return Lista de todos los metadatos.
     */
    public static List<TrdMetadato> listarPorIdTipoDatoNombre(String valor){
        return ServiciosEndpoint.get().proxy(TrdMetadatoRestIface.class).listarPorIdTipoDatoNombre(valor);
    }


    /**
     * obtenerPorId.
     * @param metadatoId Identificador del metadato.
     * @return Metadato asociado.
     */
    public static TrdMetadato obtenerPorId(Integer metadatoId){
        return ServiciosEndpoint.get().proxy(TrdMetadatoRestIface.class).obtenerPorId(metadatoId);
    }
    
    /**
     * guardarMetadato.
     * @param token Token con identificación del usuario.
     * @param metadatos Lista de metadatos a guardar.
     * @return Lista de metadatos guardados.
     */
    public static TrdMetadato guardarMetadato(Token token, TrdMetadato metadato){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(TrdMetadatoRestIface.class).guardarMetadato(metadato);
        
    }

}
