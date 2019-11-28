/*
* Archivo: TrdCodificacionServiceImpl.java
* Fecha creacion = 06/06/2017
* Todos los derechos de propiedad intelectual e industrial sobre esta
* aplicacion son de propiedad exclusiva del GRUPO ASESORIA EN
* SISTEMATIZACION DE DATOS SOCIEDAD POR ACCIONES SIMPLIFICADA – GRUPO ASD S.A.S.
* Su uso, alteracion, reproduccion o modificacion sin el debido
* consentimiento por escrito del GRUPO ASD S.A.S.
* autorizacion por parte de su autor quedan totalmente prohibidos.
* 
* Este programa se encuentra protegido por las disposiciones de la
* Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
* propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
* previstas en la Ley.
*/
package co.com.grupoasd.documental.presentacion.service.trd.impl;

import java.util.List;

import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.trd.TrdCodificacionProxyService;
import co.com.grupoasd.documental.cliente.trd.model.TrdCodificacion;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdCodificacionService;

public class TrdCodificacionServiceImpl implements TrdCodificacionService {

    @Override
    public List<TrdCodificacion> listarTodos() {
        return TrdCodificacionProxyService.listarTodos();
    }

    @Override
    public List<TrdCodificacion> listarPorEmpresaId(Integer empresaId) {
        return TrdCodificacionProxyService.listarPorEmpresaId(empresaId);
    }

    @Override
    public List<TrdCodificacion> listarPorEstructuraId(Integer estructuraId) {
        return TrdCodificacionProxyService.listarPorEstructuraId(estructuraId);
    }

    @Override
    public List<TrdCodificacion> listarPorEmpresaIdYEstructuraId(Integer empresaId, Integer estructuraId) {
        return TrdCodificacionProxyService.listarPorEmpresaIdYEstructuraId(empresaId, estructuraId);
    }

    @Override
    public List<TrdCodificacion> guardarCodificaciones(Token token, List<TrdCodificacion> trdCodificaciones) {
        return TrdCodificacionProxyService.guardarCodificaciones(token, trdCodificaciones);
    }

}
