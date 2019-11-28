package co.com.grupoasd.documental.cliente.trd;

import java.util.List;

import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;
import co.com.grupoasd.documental.cliente.trd.iface.TrdSubserieOpcionAdcDispRestIface;
import co.com.grupoasd.documental.cliente.trd.model.TrdSubserieOpcionAdcDisp;

public class TrdSubserieOpcionAdcDispProxyService {

	public TrdSubserieOpcionAdcDispProxyService(){
		
	}
	
	
	public static List<TrdSubserieOpcionAdcDisp> getOpcionesDisposicionFinalBySubserieId(Integer subserieId){
		return ServiciosEndpoint.get().proxy(TrdSubserieOpcionAdcDispRestIface.class).getOpcionesDisposicionFinalBySubserieId(subserieId);
	}
}
