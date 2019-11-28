/*
 * Archivo: TipoDocumentalServiceImpl.java
 * Fecha creacion: 21/03/2017
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

import co.com.grupoasd.documental.cliente.catalogo.TipoDocumentalProxyService;
import co.com.grupoasd.documental.cliente.catalogo.model.TipoDocumental;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.TipoDocumentalService;

/**
 * Implementacion de TipoDocumentalService.
 * 
 * @author cestrada
 *
 */


public class TipoDocumentalServiceImpl implements TipoDocumentalService {

    @Override
    public TipoDocumental obtenerPorId(Integer id) {
        return TipoDocumentalProxyService.obtenerPorId(id);
    }

    @Override
    public List<TipoDocumental> listar(Integer subserieId, Boolean inactivo) {
        return TipoDocumentalProxyService.listar(subserieId, inactivo);
    }

}
