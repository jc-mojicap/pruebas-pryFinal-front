/*
* Archivo: TrdSerieServiceImpl.java
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
import co.com.grupoasd.documental.cliente.trd.TrdSerieProxyService;
import co.com.grupoasd.documental.cliente.trd.model.TrdSerie;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdSerieService;

public class TrdSerieServiceImpl implements TrdSerieService {

    @Override
    public TrdSerie obtenerPorTrdSerieId(Integer trdSerieId) {
        return TrdSerieProxyService.obtenerPorTrdSerieId(trdSerieId);
    }
    
    @Override
    public List<TrdSerie> listarPorSerieId(Integer serieId) {
        return TrdSerieProxyService.listarPorSerieId(serieId);
    }

    @Override
    public List<TrdSerie> listarPorAreaId(Integer areaId) {
        return TrdSerieProxyService.listarPorAreaId(areaId);
    }

    @Override
    public TrdSerie crearTrdSerie(Token token, TrdSerie trdSerie) {
        return TrdSerieProxyService.crearTrdSerie(token, trdSerie);
    }

    @Override
    public TrdSerie actualizarTrdSerie(Token token, TrdSerie trdSerie) {
        return TrdSerieProxyService.actualizarTrdSerie(token, trdSerie);
    }

}
