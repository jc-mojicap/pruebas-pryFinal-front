/*
 * Archivo: AreaService.java
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
package co.com.grupoasd.documental.presentacion.service.catalogo.iface;

import java.util.List;

import co.com.grupoasd.documental.cliente.catalogo.model.Area;

/**
 * Servicios del recurso area. 
 * 
 * @author cestrada
 *
 */
public interface AreaService {
    
    /**
     * Lista todas areas , o por id de la empresa y/o activas e inactivas.
     * @param empresaId Filtro Identificador de la empresa se puede enviar null.
     * @param inactivo Filtro Inactivo .
     * @return List<Area> listado de las areas, si los filtros son null, se consultan todas las áreas.
     */
    public List<Area> listar(Integer empresaId, Boolean inactivo);
    
    Area obtenerPorAreaIdAndInactivo(Integer areaId, boolean isInactivo);

}