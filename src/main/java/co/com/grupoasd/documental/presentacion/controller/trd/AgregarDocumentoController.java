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



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.trd.model.TrdDocumento;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;
import co.com.grupoasd.documental.presentacion.service.trd.TrdServiceFactory;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdDocumentoService;

public class AgregarDocumentoController {

    @Wire
    private Window windowAgregarDocumento;
    
    @Wire
	private Textbox txtBusqueda;
    private Integer trdId=1;
    private Integer areaPadreId=1;
  
    @Wire
	private Listbox lstArchAdjuntos;
    private String callback;
    //private Integer empresaId;
   
    /**
     * Services
     */
    private TrdDocumentoService documentoService;
    
	private Integer idUsuarioSesion;
	private Integer idEmpresaSesion;
    
    
	private List<TrdDocumento> documentos;
	private List<TrdDocumento> documentosPersistencia;
	private List<TrdDocumento> documentosSeleccionados;
    
    @Init
    public void init(@ExecutionArgParam("callback") String callback) {
    	 
    	this.idUsuarioSesion = ((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getUsuarioId();
        this.idEmpresaSesion = ((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getEmpresaId();
    	this.callback = callback;
    	
		documentoService = TrdServiceFactory.getTrdDocumentoService();
		
    }
    
    /**
     * Método que se ejecuta después de que ZK crea los componentes de la vista.
     * 
     * @param view
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){

    	Selectors.wireComponents(view, this, false);      
    	windowAgregarDocumento.setTitle("Agregar documento"); 
    	try {
    		//Lista que se muestra en la ventana
    		documentos = documentoService.listarPorTrd(trdId, areaPadreId);        
    		documentosPersistencia = documentos;		

    		/*if (this.trdId == null || this.areaPadreId == null || this.callback == null) {
	        	windowAgregarDocumento.detach();
				return;
			}  */     
    	}  
    	catch (Exception e) {
    		Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorConsulta"),
    				Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
    		windowAgregarDocumento.detach();
    	}
        
    }
    
    
    
    public List<TrdDocumento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<TrdDocumento> documentos) {
		this.documentos = documentos;
	}

	public List<TrdDocumento> getDocumentosSeleccionados() {
		return documentosSeleccionados;
	}

	public void setDocumentosSeleccionados(List<TrdDocumento> documentosSeleccionados) {
		this.documentosSeleccionados = documentosSeleccionados;
	}

	/**
	 * Action boton de busqueda
	 * @param event
	 */
	@NotifyChange({ "documentos", "documentosSeleccionados" })
	@Command
	public void buscarDocumentoXButton(Event event) {
		buscarDocumento();
	}
	
	/**
	 * Action busqueda por Textbox
	 * @param event
	 */
	@NotifyChange({ "documentos", "documentosSeleccionados" })
	@Command
	public void buscarDocumentoXTextbox(Event event) {
		buscarDocumento();
	}
	
	
	/**
	 * Busca area por codigo o nombre
	 */
	private void buscarDocumento() {
		try{
			//System.out.println("-Buscando: "+txtBusqueda.getValue().toString()+" en docs"+documentosPersistencia);
			if (documentosPersistencia != null && documentosPersistencia.size() > 0) {
				if (txtBusqueda.getValue() != null && !txtBusqueda.getValue().toString().isEmpty()) {
					if (StringUtils.isNumeric(txtBusqueda.getValue().toString())) {
						documentos = documentosPersistencia.stream().filter(x -> x.getDocumentoId() == Integer.parseInt(txtBusqueda.getValue().toString())).collect(Collectors.toList());
					} else {
						documentos = documentosPersistencia.stream().filter(x -> StringUtils.containsIgnoreCase(x.getNombre(), txtBusqueda.getValue().toString())).collect(Collectors.toList());
					}
				} else {
					documentos = documentosPersistencia;
				}
			}
		}  
		catch (Exception e) {
			Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorConsulta"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
			windowAgregarDocumento.detach();
		}
	}
	
	@Command
	public void ver(@BindingParam("arg1") TrdDocumento trdDocumento) {
		try{
			// Se recibe el id del item asociado y se busca cual TRD seleccionó el
			// usuario para poder visualizar LA TRD
			if(trdDocumento != null){
				Map<String, Object> argumentosEnviar = new HashMap<>();
				argumentosEnviar.put("documentoId", trdDocumento.getDocumentoId());

				try {
					UiUtils.setModalWindow("/trd/documentos/expedientes_asociados.zul",
							null, argumentosEnviar, false, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}    
		}  
		catch (Exception e) {
			Messagebox.show(Labels.getLabel("trd.agregarDocumento.mensaje.mensajeErrorVerExpedientesAsociados"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
			windowAgregarDocumento.detach();
		}
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
	public void aceptarSeleccion() {
		try {
			//Map<String, Object> params = new HashMap<>();
			//params.put("documentosSeleccionados", documentosSeleccionados);
			Events.sendEvent(callback, windowAgregarDocumento.getParent(), documentosSeleccionados);
			windowAgregarDocumento.detach();
		}
		catch (Exception e) {
			Messagebox.show(Labels.getLabel("trd.agregarDocumento.mensaje.mensajeErrorAgregarSeleccionados"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
			windowAgregarDocumento.detach();
		}

	}
	
	/**
	 * Action crear documento nuevo
	 * @param event
	 */
	@Command
	public void crearDocumento(@BindingParam("arg1") TrdDocumento trdDocumento) {
		// Se recibe el id del item asociado y se busca cual TRD seleccionó el
		// usuario para poder visualizar LA TRD
		if(trdDocumento != null){
			Map<String, Object> argumentosEnviar = new HashMap<>();
			argumentosEnviar.put("documento", trdDocumento);
			
			try {
				UiUtils.setModalWindow("/trd/documentos/crear_documento.zul",
						null, argumentosEnviar, false, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}        
	}
	
	/**
	 * Action aceptar selección
	 * @param event
	 */
	@Command
	public void cancelarSeleccion() {
		windowAgregarDocumento.detach();
    }
    
}
