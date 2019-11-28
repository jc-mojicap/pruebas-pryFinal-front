package co.com.grupoasd.documental.presentacion.service.trd.iface;

import java.util.List;
import co.com.grupoasd.documental.cliente.trd.model.TrdSubserieOpcionAdcDisp;

public interface TrdSubserieOpcionAdcDispService {
	
	List<TrdSubserieOpcionAdcDisp> getOpcionesDisposicionFinalBySubserieId(Integer subserieId);
}
