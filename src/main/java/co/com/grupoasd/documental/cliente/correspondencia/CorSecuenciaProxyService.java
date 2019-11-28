/*
* Archivo: CorSecuenciaProxyService.java
* Fecha creacion = 03/04/2017
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

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import co.com.grupoasd.documental.cliente.comun.AuthorizationFilter;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.correspondencia.iface.CorSecuenciaRestIface;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorSecuencia;

/**
 * Proxy que permite la invocación remota de los servicios del recurso CorSecuencia.
 * @author JuanMojica
 *
 */
public class CorSecuenciaProxyService {
    
    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private CorSecuenciaProxyService(){
        
    }
    
    /**
     * Busca una CorSecuencia por su id.
     * @param id Identificador del CorSecuencia.
     * @return CorSecuencia asociado.
     */
    public static CorSecuencia obtenerPorId(final Integer id){
        return ServiciosEndpoint.get().proxy(CorSecuenciaRestIface.class).obtenerPorId(id);
    }
    
    /**
     * Busca una CorSecuencia por empresa.
     * @param id Identificador de la empresa.
     * @return CorSecuencia asociada.
     */
    public static CorSecuencia obtenerPorEmpresa(final Integer id){
        return ServiciosEndpoint.get().proxy(CorSecuenciaRestIface.class).obtenerPorEmpresa(id);
    }
    
    /**
     * Crea una nueva secuencia.
     * @param token Token con identificación del usuario.
     * @param corSecuencia Objeto CorSecuencia.
     * @return Objeto CorSecuencia creado con identificador asignado.
     */
    public static CorSecuencia crear(final Token token, final CorSecuencia corSecuencia){
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(CorSecuenciaRestIface.class).crear(corSecuencia);
    }
    
    
}
