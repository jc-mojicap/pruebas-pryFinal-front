package co.com.grupoasd.documental.cliente.trd.iface;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.trd.model.TrdSubserieOpcionAdcDisp;

public interface TrdSubserieOpcionAdcDispRestIface {
	
	@GET
	@Path("trd/trdTablaRetencion/dispFinal/{subserieId}")   
    @Produces({MediaType.APPLICATION_JSON})
	List<TrdSubserieOpcionAdcDisp> getOpcionesDisposicionFinalBySubserieId(@PathParam("subserieId") Integer subserieId);

}
