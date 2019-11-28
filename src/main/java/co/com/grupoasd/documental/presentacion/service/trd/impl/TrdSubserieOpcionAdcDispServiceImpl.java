package co.com.grupoasd.documental.presentacion.service.trd.impl;

import java.util.List;

import co.com.grupoasd.documental.cliente.trd.TrdSubserieOpcionAdcDispProxyService;
import co.com.grupoasd.documental.cliente.trd.model.TrdSubserieOpcionAdcDisp;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdSubserieOpcionAdcDispService;

public class TrdSubserieOpcionAdcDispServiceImpl implements TrdSubserieOpcionAdcDispService {

	public List<TrdSubserieOpcionAdcDisp> getOpcionesDisposicionFinalBySubserieId(Integer subserieId){
	    return TrdSubserieOpcionAdcDispProxyService.getOpcionesDisposicionFinalBySubserieId(subserieId);
		
	}
}
