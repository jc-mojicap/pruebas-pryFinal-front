/**
 * 
 */
package co.com.grupoasd.documental.presentacion.service.trd.dto;

import java.io.Serializable;
import java.util.List;

import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.cliente.catalogo.model.Serie;
import co.com.grupoasd.documental.cliente.catalogo.model.Subserie;
import co.com.grupoasd.documental.cliente.catalogo.model.TipoDocumental;

/**
 * Clase que obtiene la relación entre area, serie, subserie, tipo documental
 * @author alopez
 * @since Junio 30 de 2017
 */
public class TrdNodoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Area> areas;
	
	private List<Serie> series;
	
	private List<Subserie> subseries;
	
	private List<TipoDocumental> tipoDocumentals;

	/**
	 * @return the areas
	 */
	public List<Area> getAreas() {
		return areas;
	}

	/**
	 * @param areas the areas to set
	 */
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	/**
	 * @return the series
	 */
	public List<Serie> getSeries() {
		return series;
	}

	/**
	 * @param series the series to set
	 */
	public void setSeries(List<Serie> series) {
		this.series = series;
	}

	/**
	 * @return the subseries
	 */
	public List<Subserie> getSubseries() {
		return subseries;
	}

	/**
	 * @param subseries the subseries to set
	 */
	public void setSubseries(List<Subserie> subseries) {
		this.subseries = subseries;
	}

	/**
	 * @return the tipoDocumentals
	 */
	public List<TipoDocumental> getTipoDocumentals() {
		return tipoDocumentals;
	}

	/**
	 * @param tipoDocumentals the tipoDocumentals to set
	 */
	public void setTipoDocumentals(List<TipoDocumental> tipoDocumentals) {
		this.tipoDocumentals = tipoDocumentals;
	}
}
