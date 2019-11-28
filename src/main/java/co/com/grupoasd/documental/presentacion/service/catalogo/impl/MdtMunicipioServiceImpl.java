/*
 * Archivo: MdtMunicipioServiceImpl.java
 * Fecha creacion: 12/05/2017
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

import co.com.grupoasd.documental.cliente.catalogo.MdtMunicipioProxyService;
import co.com.grupoasd.documental.cliente.catalogo.model.MdtMunicipio;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.MdtMunicipioService;

/**
 *  Implementacion del servicio municipio.
 * 
 * @author cestrada
 *
 */
public class MdtMunicipioServiceImpl implements MdtMunicipioService {

    @Override
    public List<MdtMunicipio> listar() {
        return MdtMunicipioProxyService.listar();
    }

    @Override
    public List<MdtMunicipio> listarPorDepartamento(String codigoDane) {
        return MdtMunicipioProxyService.listarPorDepartamento(codigoDane);
    }

}
