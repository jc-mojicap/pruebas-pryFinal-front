/*
 * Archivo: EmpresaProxyService.java
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
package co.com.grupoasd.documental.cliente.catalogo;

import co.com.grupoasd.documental.cliente.catalogo.model.Empresa;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import java.util.List;
import co.com.grupoasd.documental.cliente.catalogo.iface.EmpresaRestIface;
import co.com.grupoasd.documental.cliente.comun.AuthorizationFilter;
import co.com.grupoasd.documental.cliente.comun.Token;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 * Proxy que permite la invocacion remota de los servicios del recurso empresa. 
 */
public final class EmpresaProxyService {

    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private EmpresaProxyService() {

    }

    /**
     * Lista las empresas activas e inactivas.
     * @param inactivo Filtro para aplicar.
     * @return Listado con empresas. Si no existen retorna vacio.
     * @author Juan Carlos Castellanos
     */
    public static List<Empresa> listar(final Boolean inactivo) {
        return ServiciosEndpoint.get().proxy(EmpresaRestIface.class).listar(inactivo);
    }

    /**
     * Crea una nueva empresa.
     * @param token Token con identificacion del usuario.
     * @param empresa Objeto Empresa, unicamente es obligatorio que se asignen 
     * los atributos nombre e inactivo del objeto.
     * @return Objeto Empresa creado con identificador asignado.
     * @author Juan Carlos Castellanos
     */
    public static Empresa crear(final Token token, final Empresa empresa) {
        ResteasyWebTarget target = ServiciosEndpoint.get();
        target.register(AuthorizationFilter.instance(token));
        return target.proxy(EmpresaRestIface.class).crear(empresa);
    }
    
}
