/*
* Archivo: AgregarDocumentoController.java
* Fecha creacion = 22/06/2017
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
package co.com.grupoasd.documental.presentacion.controller.trd;


import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


import co.com.grupoasd.documental.cliente.trd.model.TrdExpediente;
import co.com.grupoasd.documental.presentacion.service.trd.TrdServiceFactory;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdExpedienteService;

public class ExpedientesAsociadosToDocumentoController {

    @Wire
    private Window windowExpedientesAsociados;
  
    @Wire
	private Listbox lstArchAdjuntos;
    private String callback;
    
    private Integer documentoId;
   
    /**
     * Services
     */
    private TrdExpedienteService documentoService;
    
    
	private List<TrdExpediente> expedientes;
	private List<TrdExpediente> documentosSeleccionados;
    
    @Init
    public void init(@ExecutionArgParam("documentoId") Integer documentoId) {
		
		this.documentoId = documentoId;
		documentoService = TrdServiceFactory.getTrdExpedienteService();
    }
    
    /**
     * Método que se ejecuta después de que ZK crea los componentes de la vista.
     * 
     * @param view
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
        windowExpedientesAsociados.setTitle("Expedientes asociados"); 
        expedientes = documentoService.listarPorDocumento(documentoId);	
        
    }
    
    
    public List<TrdExpediente> getExpedientes() {
		return expedientes;
	}

	public void setExpedientes(List<TrdExpediente> expedientes) {
		this.expedientes = expedientes;
	}



	/**
	 * Evento seleccion de la tabla
	 * @param event
	 */
	@Command
	public void seleccionarDocumentos() {
		System.out.println(documentosSeleccionados);
	}
	
    
	/**
	 * Action aceptar selección
	 * @param event
	 */
	@Command
	public void aceptarCerrarVentana() {
		windowExpedientesAsociados.detach();
    }

    
}
