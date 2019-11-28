/*
* Archivo: TrdAreaServiceImpl.java
* Fecha creacion = 24/06/2017
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
import co.com.grupoasd.documental.cliente.trd.TrdAreaProxyService;
import co.com.grupoasd.documental.cliente.trd.model.TrdArea;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdAreaService;

public class TrdAreaServiceImpl implements TrdAreaService {

    @Override
    public TrdArea obtenerPorTrdAreaId(Integer trdAreaId) {
        return TrdAreaProxyService.obtenerPorTrdAreaId(trdAreaId);
    }
    
    @Override
    public List<TrdArea> listarPorAreaId(Integer areaId) {
        return TrdAreaProxyService.listarPorAreaId(areaId);
    }

    @Override
    public List<TrdArea> listarPorTrdId(Integer trdId) {
        return TrdAreaProxyService.listarPorTrdId(trdId);
    }

    @Override
    public List<TrdArea> listarPorEmpresa(Integer empresaId) {
        return TrdAreaProxyService.listarPorEmpresa(empresaId);
    }

    @Override
    public TrdArea crearTrdArea(Token token, TrdArea trdArea) {
        return TrdAreaProxyService.crearTrdArea(token, trdArea);
    }

    @Override
    public TrdArea actualizarTrdArea(Token token, TrdArea trdArea) {
        return TrdAreaProxyService.actualizarTrdArea(token, trdArea);
    }

}
