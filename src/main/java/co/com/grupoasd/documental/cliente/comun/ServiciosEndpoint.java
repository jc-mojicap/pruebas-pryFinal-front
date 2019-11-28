/*
 * Archivo: ServiciosEndpoint.java
 * Fecha creacion: 28/02/2017
 * Todos los derechos de propiedad intelectual e industrial sobre esta
 * aplicacion son de propiedad exclusiva del GRUPO ASESORIA EN
 * SISTEMATIZACION DE DATOS SOCIEDAD POR ACCIONES SIMPLIFICADA – GRUPO ASD S.A.S.
 * Su uso, alteracion, reproduccion o modificacion sin la debida
 * consentimiento por escrito de GRUPO ASD S.A.S.
 * autorizacion por parte de su autor quedan totalmente prohibidos.
 * 
 * Este programa se encuentra protegido por las disposiciones de la
 * Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
 * propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
 * previstas en la Ley.
 */
package co.com.grupoasd.documental.cliente.comun;

import javax.ws.rs.core.UriBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;

/**
 * Clase que permite las peticiones rest al endpoint de servicios utilizando
 * un pool de conexiones.
 * @author Juan Carlos Castellanos
 */
public final class ServiciosEndpoint {
    
    /**
     * Numero maximo de conexiones en el pool.
     */
    private static final int MAX_CONNECTION = 200;
    /**
     * Numero maximo de conexiones por route.
     */
    private static final int MAX_PER_ROUTE = 20;
    
    /**
     * ResteasyWebTarget.
     */
    private static final ResteasyWebTarget TARGET;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        cm.setMaxTotal(MAX_CONNECTION);
        cm.setDefaultMaxPerRoute(MAX_PER_ROUTE);
        ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(httpClient);
        ResteasyClient client = new ResteasyClientBuilder().httpEngine(engine).build();
        TARGET = client.target(UriBuilder.fromPath(
                PropertiesUtil.instance().get("servicios.endpoint")));
    }
    
    /**
     * Constructor privado. Esta clase no puede instancearse.
     */
    private ServiciosEndpoint() {
        
    }

    /**
     * Obtiene el objeto ResteasyWebTarget para realizar las invocaciones remotas.
     * @return ResteasyWebTarget
     */
    public static ResteasyWebTarget get() {
        return TARGET;
    }
}
