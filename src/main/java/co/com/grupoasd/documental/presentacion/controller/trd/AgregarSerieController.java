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

import co.com.grupoasd.documental.cliente.catalogo.model.Serie;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SerieService;

public class AgregarSerieController {
	
	@Wire
	private Window windowAgregarSerie;
	
	private SerieService serieService;
	private String nombreTrd;
	private String callback;
	private List<Serie> series;
	private List<Serie> seriesPersistencia;
	private Integer areaId;

	private List<Serie> seriesSeleccionadas;
	private Treechildren treechildrenArea;

	@Wire
	private Textbox txtBusqueda;
	
	@Init
	public void init(@ExecutionArgParam("nombreTrd") String nombreTrd, @ExecutionArgParam("callback") String callback, 
			@ExecutionArgParam("series") List<Serie> seriesSeleccionadas, @ExecutionArgParam("areaId") Integer areaId,  @ExecutionArgParam("treechildrenArea") Treechildren treechildrenArea) {
		this.nombreTrd = nombreTrd;
		this.callback = callback;
		this.seriesSeleccionadas = seriesSeleccionadas;
		this.areaId = areaId;
		this.treechildrenArea = treechildrenArea;
		
		if (this.seriesSeleccionadas == null) {
			this.seriesSeleccionadas = new ArrayList<>();
		}
		
		serieService = CatalogoServiceFactory.getSerieService();
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		series = serieService.listarPorArea(this.areaId, false);
		seriesPersistencia = series;
		
		if (this.nombreTrd == null || this.callback == null || this.areaId == null || this.areaId <= 0 || this.treechildrenArea == null) {
			windowAgregarSerie.detach();
			return;
		}
		
		windowAgregarSerie.setTitle("Agregar Serie - ".concat(this.nombreTrd));
	}
	
	/**
	 * @return the series
	 */
	public List<Serie> getSeries() {
		return series;
	}

	/**
	 * @param series the areas to set
	 */
	public void setSeries(List<Serie> series) {
		this.series = series;
	}
	
	/**
	 * @return the seriesSeleccionadas
	 */
	public List<Serie> getSeriesSeleccionadas() {
		return seriesSeleccionadas;
	}

	/**
	 * @param seriesSeleccionadas the seriesSeleccionadas to set
	 */
	public void setSeriesSeleccionadas(List<Serie> seriesSeleccionadas) {
		this.seriesSeleccionadas = seriesSeleccionadas;
	}
	
	/**
	 * Action boton de busqueda
	 * @param event
	 */
	@NotifyChange({ "series", "seriesSeleccionadas" })
	@Command
	public void buscarSerieXButton(Event event) {
		buscarArea();
	}
	
	/**
	 * Action busqueda por Textbox
	 * @param event
	 */
	@NotifyChange({ "series", "seriesSeleccionadas" })
	@Command
	public void buscarSerieXTextbox(Event event) {
		buscarArea();
	}
	
	/**
	 * Busca area por codigo o nombre
	 */
	private void buscarArea() {
		if (seriesPersistencia != null && seriesPersistencia.size() > 0) {
			if (txtBusqueda.getValue() != null && !txtBusqueda.getValue().toString().isEmpty()) {
				if (StringUtils.isNumeric(txtBusqueda.getValue().toString())) {
					series = seriesPersistencia.stream().filter(x -> x.getSerieId() == Integer.parseInt(txtBusqueda.getValue().toString())).collect(Collectors.toList());
				} else {
					series = seriesPersistencia.stream().filter(x -> StringUtils.containsIgnoreCase(x.getNombre(), txtBusqueda.getValue().toString())).collect(Collectors.toList());
				}
			} else {
				series = seriesPersistencia;
			}
		}
	}
	
	/**
	 * Evento seleccion de la tabla
	 * @param event
	 */
	@Command
	public void seleccionarSeries() {
		System.out.println(seriesSeleccionadas);
	}
	
	/**
	 * Action aceptar selección
	 * @param event
	 */
	@Command
	public void aceptarSeleccion() {
		Map<String, Object> params = new HashMap<>();
		params.put("seriesSeleccionadas", seriesSeleccionadas);
		params.put("treechildrenArea", treechildrenArea);
        Events.sendEvent(callback, windowAgregarSerie.getParent(), params);
        windowAgregarSerie.detach();
    }
	
	/**
	 * Action aceptar selección
	 * @param event
	 */
	@Command
	public void cancelarSeleccion() {
		windowAgregarSerie.detach();
    }

}
