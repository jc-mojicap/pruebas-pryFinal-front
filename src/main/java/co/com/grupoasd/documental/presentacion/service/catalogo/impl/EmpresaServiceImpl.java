/*
 * Archivo: EmpresaServiceImpl.java
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
package co.com.grupoasd.documental.presentacion.service.catalogo.impl;

import co.com.grupoasd.documental.cliente.catalogo.EmpresaProxyService;
import co.com.grupoasd.documental.cliente.catalogo.model.Empresa;
import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.EmpresaService;
import java.util.List;

/**
 * Implementacion de EmpresaService.
 * @author Juan Carlos Castellanos
 */
public final class EmpresaServiceImpl implements EmpresaService {

    @Override
    public List<Empresa> listar(final Boolean inactivo) {
        return EmpresaProxyService.listar(inactivo);
    }

    @Override
    public Empresa crear(final Token token, final Empresa empresa) {
        return EmpresaProxyService.crear(token, empresa);
    }
    
}