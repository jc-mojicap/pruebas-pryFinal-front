/*
* Archivo: TrdDocumentoServiceImpl.java
* Fecha creacion = 23/06/2017
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

import co.com.grupoasd.documental.cliente.trd.TrdExpedienteProxyService;
import co.com.grupoasd.documental.cliente.trd.model.TrdExpediente;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdExpedienteService;

public class TrdExpedienteServiceImpl implements TrdExpedienteService {

	@Override
	public List<TrdExpediente> listarPorDocumento(Integer documentoId) {
		 return TrdExpedienteProxyService.listarPorDocumento(documentoId);
	}

	@Override
	public List<TrdExpediente> listarPorTrdSubserie(Integer trdSubserieId) {
		 return TrdExpedienteProxyService.listarPorTrdSubserie(trdSubserieId);
	}
	
	@Override
	public List<TrdExpediente> listarPorExpediente(Integer expedienteId) {
		 return TrdExpedienteProxyService.listarPorExpediente(expedienteId);
	}

	@Override
	public List<TrdExpediente> listarTodos() {
		return TrdExpedienteProxyService.listarTodos();
	}

}
