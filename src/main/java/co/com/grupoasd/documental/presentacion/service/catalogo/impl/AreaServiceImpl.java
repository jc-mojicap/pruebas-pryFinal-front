/*
 * Archivo: AreaServiceImpl.java
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
package co.com.grupoasd.documental.presentacion.service.catalogo.impl;

import java.util.List;

import co.com.grupoasd.documental.cliente.catalogo.AreaProxyService;
import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.AreaService;

/**
 * Implementacion de AreaService.
 * 
 * @author cestrada
 *
 */
public class AreaServiceImpl implements AreaService {

    @Override
    public List<Area> listar(Integer empresaId, Boolean inactivo) {
        return AreaProxyService.listar(empresaId, inactivo);
    }

    @Override
    public Area obtenerPorAreaIdAndInactivo(Integer areaId, boolean isInactivo) {
        return AreaProxyService.obtenerPorAreaIdAndInactivo(areaId, isInactivo);
    }

}
