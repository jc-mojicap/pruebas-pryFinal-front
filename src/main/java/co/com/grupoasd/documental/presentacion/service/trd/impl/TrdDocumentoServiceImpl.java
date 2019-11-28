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

import co.com.grupoasd.documental.cliente.trd.TrdDocumentoProxyService;
import co.com.grupoasd.documental.cliente.trd.model.TrdDocumento;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdDocumentoService;

public class TrdDocumentoServiceImpl implements TrdDocumentoService {

	@Override
	public List<TrdDocumento> listarPorDocumento(Integer documentoId) {
		 return TrdDocumentoProxyService.listarPorDocumento(documentoId);
	}

	@Override
	public List<TrdDocumento> listarPorTrd(Integer trdId, Integer areaPadreId) {
		 return TrdDocumentoProxyService.listarPorTrd(trdId, areaPadreId);
	}

	@Override
	public List<TrdDocumento> listarTodos() {
		return TrdDocumentoProxyService.listarTodos();
	}

}
