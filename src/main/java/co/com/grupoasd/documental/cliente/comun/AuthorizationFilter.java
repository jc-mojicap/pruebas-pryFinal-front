/*
 * Archivo: AuthorizationFilter.java
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

import java.io.IOException;
import java.util.Arrays;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Filtro resteasy que permite el envio de las cabeceras de autenticacion por token.
 * @author Juan Carlos Castellanos
 */
public final class AuthorizationFilter implements ClientRequestFilter {

    /**
     * Token JWT.
     */
    private final Token token;
    /**
     * Nombre de la cabecera.
     */
    private static final String AUTH_HEADER = "Authorization";
    /**
     * Contenido prefijo de la cabecera.
     */
    private static final String AUTH_BEARER = "Bearer";

    /**
     * Constructor privado. Esta clase no puede ser instanciada directamente.
     * @param pToken Token de autenticacion.
     */
    private AuthorizationFilter(final Token pToken) {
        this.token = pToken;
    }

    /**
     * Permite obtener una instancia nueva.
     * @param token Token de autenticacion.
     * @return AuthorizationFilter
     */
    public static AuthorizationFilter instance(final Token token) {
        return new AuthorizationFilter(token);
    }
    
    /**
     * Filtro que agregar la cabecera.
     * @param crc ClientRequestContext
     * @throws IOException 
     */
    @Override
    public void filter(final ClientRequestContext crc) throws IOException {
        MultivaluedMap<String, Object> mmap = crc.getHeaders();
        mmap.put(AUTH_HEADER, Arrays.asList(
                String.format("%s %s", AUTH_BEARER, token.getCotenido())));
    }

}
