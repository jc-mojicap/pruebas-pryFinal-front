package co.com.grupoasd.documental.presentacion.controller.trd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.catalogo.model.TipoDocumental;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.TipoDocumentalService;

public class AgregarTipoDocumentalesController {
	
	@Wire
	private Window windowAgregarTipoDoc;
	
	private TipoDocumentalService tipoDocumentalService;
	private String nombreTrd;
	private String callback;
	private List<TipoDocumental> tiposDocumentales;
	private List<TipoDocumental> tiposDocumentalesPersistencia;
	private Integer subSerieId;

	private List<TipoDocumental> tiposDocSeleccionados;
	private Treechildren treechildrenSubSerie;

	@Wire
	private Textbox txtBusqueda;
	
	@Init
	public void init(@ExecutionArgParam("nombreTrd") String nombreTrd, @ExecutionArgParam("callback") String callback, 
			@ExecutionArgParam("tiposDocumentales") List<TipoDocumental> tiposDocSeleccionados, @ExecutionArgParam("subSerieId") Integer subSerieId,  
			@ExecutionArgParam("treechildrenSubSerie") Treechildren treechildrenSubSerie) {
		
		this.nombreTrd = nombreTrd;
		this.callback = callback;
		this.tiposDocSeleccionados = tiposDocSeleccionados;
		this.subSerieId = subSerieId;
		this.treechildrenSubSerie = treechildrenSubSerie;
		
		if (this.tiposDocSeleccionados == null) {
			this.tiposDocSeleccionados = new ArrayList<>();
		}
		
		tipoDocumentalService = CatalogoServiceFactory.getTipoDocumentalService();
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		tiposDocumentales = tipoDocumentalService.listar(subSerieId, false);
		tiposDocumentalesPersistencia = tiposDocumentales;
		
		if (this.nombreTrd == null || this.callback == null || this.subSerieId == null || this.subSerieId <= 0 || this.treechildrenSubSerie == null) {
			windowAgregarTipoDoc.detach();
			return;
		}
		
		windowAgregarTipoDoc.setTitle("Agregar Tipos Documentales - ".concat(this.nombreTrd));
	}
	
	/**
	 * @return the tiposDocumentales
	 */
	public List<TipoDocumental> getTiposDocumentales() {
		return tiposDocumentales;
	}

	/**
	 * @param tiposDocumentales the areas to set
	 */
	public void setTiposDocumentales(List<TipoDocumental> tiposDocumentales) {
		this.tiposDocumentales = tiposDocumentales;
	}
	
	/**
	 * @return the tiposDocumentalesSeleccionados
	 */
	public List<TipoDocumental> getTiposDocSeleccionados() {
		return tiposDocSeleccionados;
	}

	/**
	 * @param tiposDocumentalesSeleccionados the tiposDocumentalesSeleccionados to set
	 */
	public void setTiposDocSeleccionados(List<TipoDocumental> tiposDocSeleccionados) {
		this.tiposDocSeleccionados = tiposDocSeleccionados;
	}
	
	/**
	 * Action boton de busqueda
	 * @param event
	 */
	@NotifyChange({ "tiposDocumentales", "tiposDocumentalesSeleccionados" })
	@Command
	public void buscarTipoDocumentalesXButton(Event event) {
		buscarTiposDocumentales();
	}
	
	/**
	 * Action busqueda por Textbox
	 * @param event
	 */
	@NotifyChange({ "tiposDocumentales", "tiposDocumentalesSeleccionados" })
	@Command
	public void buscarTipoDocumentalesXTextbox(Event event) {
		buscarTiposDocumentales();
	}
	
	/**
	 * Busca area por codigo o nombre
	 */
	private void buscarTiposDocumentales() {
		if (tiposDocumentalesPersistencia != null && tiposDocumentalesPersistencia.size() > 0) {
			if (txtBusqueda.getValue() != null && !txtBusqueda.getValue().toString().isEmpty()) {
				if (StringUtils.isNumeric(txtBusqueda.getValue().toString())) {
					tiposDocumentales = tiposDocumentalesPersistencia.stream().filter(x -> x.getTipoDocumentalId() == Integer.parseInt(txtBusqueda.getValue().toString())).collect(Collectors.toList());
				} else {
					tiposDocumentales = tiposDocumentalesPersistencia.stream().filter(x -> StringUtils.containsIgnoreCase(x.getNombre(), txtBusqueda.getValue().toString())).collect(Collectors.toList());
				}
			} else {
				tiposDocumentales = tiposDocumentalesPersistencia;
			}
		}
	}
	
	/**
	 * Evento seleccion de la tabla
	 * @param event
	 */
	@Command
	public void seleccionarTipoDocumentales() {
		System.out.println(tiposDocSeleccionados);
	}
	
	/**
	 * Action aceptar selección
	 * @param event
	 */
	@Command
	public void aceptarSeleccion() {
		Map<String, Object> params = new HashMap<>();
		params.put("tiposDocSeleccionados", tiposDocSeleccionados);
		params.put("treechildrenSubSerie", treechildrenSubSerie);
        Events.sendEvent(callback, windowAgregarTipoDoc.getParent(), params);
        windowAgregarTipoDoc.detach();
    }
	
	/**
	 * Action aceptar selección
	 * @param event
	 */
	@Command
	public void cancelarSeleccion() {
		windowAgregarTipoDoc.detach();
    }

}
