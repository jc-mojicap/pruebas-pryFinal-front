/*
 * Archivo: CorRadicadoProxyService.java
 * Fecha creacion: 24/03/2017
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
package co.com.grupoasd.documental.cliente.correspondencia;

import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import co.com.grupoasd.documental.cliente.comun.AuthorizationFilter;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.correspondencia.iface.CorRadicadoRestIface;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorRadicado;
import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.service.correspondencia.dto.RadicadoDto;

/**
 * Proxy que permite la invocacion remota de los servicios del recurso radicado
 * de correspondencia.
 * 
 * @author cestrada
 *
 */
public final class CorRadicadoProxyService {

    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private CorRadicadoProxyService() {

    }

    /**
     * Lista los radicados de comuniación.
     * 
     * @return Listado con radicados de comuniación. Si no existen retorna
     *         vacio.
     * @author cestrada
     */
    public static List<CorRadicado> listar(CorRadicado filtros) {
        return ServiciosEndpoint.get().proxy(CorRadicadoRestIface.class).listarRadicadosPorFiltros(filtros);
    }

    /**
     * Lista los radicados de comuniación.
     * 
     * @return Listado con radicados de comuniación. Si no existen retorna
     *         vacio.
     * @author cestrada
     */
    public static List<RadicadoDto> listar(RadicadoDto filtros) {
        return ServiciosEndpoint.get().proxy(CorRadicadoRestIface.class).listarRadicadosPorFiltros(filtros);
    }

    /**
     * Cantidad de radicados filtrados.
     * 
     * @return Integer cantidad de radicados.
     * @author cestrada
     */
    public static Integer contar(RadicadoDto filtros) {
        return ServiciosEndpoint.get().proxy(CorRadicadoRestIface.class).contarRadicadosPorFiltros(filtros);
    }

    /**
     * Archivo de radicados filtrados enviando el tipo de archivo.
     * 
     * @return InfoMedia Archivo de radicados.
     * @author cestrada
     */
    public static InfoMedia obtenerArchivo(RadicadoDto filtros, String tipoArchivo) {
        return ServiciosEndpoint.get().proxy(CorRadicadoRestIface.class).obtenerArchivoRadicadosPorFiltros(filtros, tipoArchivo);
    }

    /**
     * Crea un nuevo radicado.
     * 
     * @param token
     *            Token con identificación del usuario.
     * @param corRadicadoComentario
     *            Objeto CorRadicadoComentario.
     * @return Objeto CorRadicado creado con identificador asignado.
     */
    public static CorRadicado crear(final Token token, final CorRadicado corRadicado) {
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(CorRadicadoRestIface.class).crear(corRadicado);
    }

    /**
     * Busca un radicado por radicado y empresa.
     * @param empresaId Id de la empresa.
     * @param radicado número de radicado.
     * @return Radicado.
     */
    public static CorRadicado buscarPorRadicadoYEmpresa(final Integer empresaId, final String radicado) {
        return ServiciosEndpoint.get().proxy(CorRadicadoRestIface.class).buscarPorRadicadoYEmpresa(empresaId, radicado);
    }
    
    /**
     * Obtiene la información del radicado por radicado_id
     * @param id Id del radicado
     * @return Objeto CorRadicado
     */
    public static CorRadicado obtenerPorId(final Long id) {
        return ServiciosEndpoint.get().proxy(CorRadicadoRestIface.class).obtenerPorId(id);
    }
    
    /**
     * Actualiza Radicado
     * @param token
     *            Token con identificación del usuario.
     * @param corRadicado Objeto CorRadicado
     * @return Objeto CorRadicado
     */
    public static CorRadicado actualizarRadicado(final Token token, final CorRadicado corRadicado) {
    	ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(CorRadicadoRestIface.class).actualizarRadicado(corRadicado);
    }
    
    /**
     * Anular Radicado
     * @param token
     *            Token con identificación del usuario.
     * @param corRadicado Objeto CorRadicado
     * @return Objeto CorRadicado
     */
    public static CorRadicado anularRadicado(final Token token, final CorRadicado corRadicado) {
    	ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(CorRadicadoRestIface.class).anularRadicado(corRadicado);
    }
    
    /**
     * Asignar responsables
     * @param token
     *            Token con identificación del usuario.
     * @param corRadicado Objeto CorRadicado
     * @return Objeto CorRadicado
     */
    public static CorRadicado asignarResponsables(final Token token, final CorRadicado corRadicado) {
    	ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(CorRadicadoRestIface.class).asignarResponsables(corRadicado);
    }
}
