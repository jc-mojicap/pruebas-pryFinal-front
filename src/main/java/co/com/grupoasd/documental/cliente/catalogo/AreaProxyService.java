/*
 * Archivo: AreaProxyService.java
 * Fecha creacion: 17/03/2017
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

import java.util.List;

import co.com.grupoasd.documental.cliente.catalogo.iface.AreaRestIface;
import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;

/**
 * Proxy que permite la invocacion remota de los servicios del recurso area. 
 * 
 * @author cestrada
 *
 */
public final class AreaProxyService {
    
    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private AreaProxyService(){
        
    }
    
    /**
     * Lista todas areas , o por id de la empresa y/o activas e inactivas.
     * @param empresaId Filtro Identificador de la empresa se puede enviar null.
     * @param inactivo Filtro Inactivo .
     * @return List<Area> listado de las areas, si los filtros son null, se consultan todas las áreas.
     */
    public static List<Area> listar(Integer empresaId, Boolean inactivo){
       return ServiciosEndpoint.get().proxy(AreaRestIface.class).listar(empresaId, inactivo);
    }
    
    public static Area obtenerPorAreaIdAndInactivo(Integer areaId, boolean isInactivo){
        return ServiciosEndpoint.get().proxy(AreaRestIface.class).obtenerPorAreaIdAndInactivo(areaId, isInactivo);
    }

}
