/*
 * Archivo: AccionServiceImpl.java
 * Fecha creacion: 11/04/2017
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

import co.com.grupoasd.documental.cliente.catalogo.AccionProxyService;
import co.com.grupoasd.documental.presentacion.service.catalogo.dto.AccionDto;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.AccionService;

public class AccionServiceImpl implements AccionService {

    @Override
    public List<AccionDto> listarPorUsuarioYRol(Integer rolId, Integer usuarioId) {
        return AccionProxyService.listarPorUsuarioYRol(rolId, usuarioId);
    }

    @Override
    public List<AccionDto> listarPorUsuarioIdEInactivo(Integer usuarioId, Boolean inactivo) {
        return AccionProxyService.listarPorUsuarioIdEInactivo(usuarioId, inactivo);
    }

    @Override
    public List<AccionDto> listarPorRolIdEInactivo(Integer rolId, Boolean inactivo) {
        return AccionProxyService.listarPorRolIdEInactivo(rolId, inactivo);
    }

}
