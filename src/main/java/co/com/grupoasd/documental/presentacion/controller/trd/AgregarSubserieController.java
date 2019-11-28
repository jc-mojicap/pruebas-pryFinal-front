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

import co.com.grupoasd.documental.cliente.catalogo.model.Subserie;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SubserieService;

public class AgregarSubserieController {
	
	@Wire
	private Window windowAgregarSubserie;
	
	private SubserieService subserieService;
	private String nombreTrd;
	private String callback;
	private List<Subserie> subseries;
	private List<Subserie> subseriesPersistencia;
	private Integer serieId;

	private List<Subserie> subseriesSeleccionadas;
	private Treechildren treechildrenSerie;

	@Wire
	private Textbox txtBusqueda;
	
	@Init
	public void init(@ExecutionArgParam("nombreTrd") String nombreTrd, @ExecutionArgParam("callback") String callback, 
			@ExecutionArgParam("subseries") List<Subserie> subseriesSeleccionadas, @ExecutionArgParam("serieId") Integer serieId,  
			@ExecutionArgParam("treechildrenSerie") Treechildren treechildrenSerie) {
		this.nombreTrd = nombreTrd;
		this.callback = callback;
		this.subseriesSeleccionadas = subseriesSeleccionadas;
		this.serieId = serieId;
		this.treechildrenSerie = treechildrenSerie;
		
		if (this.subseriesSeleccionadas == null) {
			this.subseriesSeleccionadas = new ArrayList<>();
		}
		
		subserieService = CatalogoServiceFactory.getSubserieService();
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		subseries = subserieService.listarPorSerie(this.serieId, false);
		subseriesPersistencia = subseries;
		
		if (this.nombreTrd == null || this.callback == null || this.serieId == null || this.serieId <= 0 || this.treechildrenSerie == null) {
			windowAgregarSubserie.detach();
			return;
		}
		
		windowAgregarSubserie.setTitle("Agregar Serie - ".concat(this.nombreTrd));
	}
	
	/**
	 * @return the series
	 */
	public List<Subserie> getSubseries() {
		return subseries;
	}

	/**
	 * @param series the areas to set
	 */
	public void setSubseries(List<Subserie> subseries) {
		this.subseries = subseries;
	}
	
	/**
	 * @return the seriesSeleccionadas
	 */
	public List<Subserie> getSubseriesSeleccionadas() {
		return subseriesSeleccionadas;
	}

	/**
	 * @param seriesSeleccionadas the seriesSeleccionadas to set
	 */
	public void setSubseriesSeleccionadas(List<Subserie> subseriesSeleccionadas) {
		this.subseriesSeleccionadas = subseriesSeleccionadas;
	}
	
	/**
	 * Action boton de busqueda
	 * @param event
	 */
	@NotifyChange({ "subseries", "subseriesSeleccionadas" })
	@Command
	public void buscarSubserieXButton(Event event) {
		buscarSubserie();
	}
	
	/**
	 * Action busqueda por Textbox
	 * @param event
	 */
	@NotifyChange({ "subseries", "subseriesSeleccionadas" })
	@Command
	public void buscarSubserieXTextbox(Event event) {
		buscarSubserie();
	}
	
	/**
	 * Busca area por codigo o nombre
	 */
	private void buscarSubserie() {
		if (subseriesPersistencia != null && subseriesPersistencia.size() > 0) {
			if (txtBusqueda.getValue() != null && !txtBusqueda.getValue().toString().isEmpty()) {
				if (StringUtils.isNumeric(txtBusqueda.getValue().toString())) {
					subseries = subseriesPersistencia.stream().filter(x -> x.getSubserieId() == Integer.parseInt(txtBusqueda.getValue().toString())).collect(Collectors.toList());
				} else {
					subseries = subseriesPersistencia.stream().filter(x -> StringUtils.containsIgnoreCase(x.getNombre(), txtBusqueda.getValue().toString())).collect(Collectors.toList());
				}
			} else {
				subseries = subseriesPersistencia;
			}
		}
	}
	
	/**
	 * Evento seleccion de la tabla
	 * @param event
	 */
	@Command
	public void seleccionarSubseries() {
		System.out.println(subseriesSeleccionadas);
	}
	
	/**
	 * Action aceptar selección
	 * @param event
	 */
	@Command
	public void aceptarSeleccion() {
		Map<String, Object> params = new HashMap<>();
		params.put("subseriesSeleccionadas", subseriesSeleccionadas);
		params.put("treechildrenSerie", treechildrenSerie);
        Events.sendEvent(callback, windowAgregarSubserie.getParent(), params);
        windowAgregarSubserie.detach();
    }
	
	/**
	 * Action aceptar selección
	 * @param event
	 */
	@Command
	public void cancelarSeleccion() {
		windowAgregarSubserie.detach();
    }

}
