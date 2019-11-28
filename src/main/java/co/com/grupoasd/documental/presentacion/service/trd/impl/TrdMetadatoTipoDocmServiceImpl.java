/*
* Archivo: TrdMetadatoTipoDocmServiceImpl.java
* Fecha creacion = 20/06/2017
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
import co.com.grupoasd.documental.cliente.trd.TrdMetadatoTipoDocmProxyService;
import co.com.grupoasd.documental.cliente.trd.model.TrdMetadatoTipoDocm;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdMetadatoTipoDocmService;

public class TrdMetadatoTipoDocmServiceImpl implements TrdMetadatoTipoDocmService {

    @Override
    public TrdMetadatoTipoDocm obtenerPorMetadatoIdYTipoDocumentalId(Integer metadatoId, Integer tipoDocumentalId) {
        return TrdMetadatoTipoDocmProxyService.obtenerPorMetadatoIdYTipoDocumentalId(metadatoId, tipoDocumentalId);
    }

    @Override
    public List<TrdMetadatoTipoDocm> listarPorMetadato(Integer metadatoId) {
        return TrdMetadatoTipoDocmProxyService.listarPorMetadato(metadatoId);
    }

    @Override
    public List<TrdMetadatoTipoDocm> listarPorTipoDocumental(Integer tipoDocumentalId) {
        return TrdMetadatoTipoDocmProxyService.listarPorTipoDocumental(tipoDocumentalId);
    }

    @Override
    public List<TrdMetadatoTipoDocm> guardarTrdMetadatosTipoDocm(Token token,
            List<TrdMetadatoTipoDocm> trdMetadatosTipoDocm) {
        return TrdMetadatoTipoDocmProxyService.guardarTrdMetadatosSubserie(token, trdMetadatosTipoDocm);
    }

}
