/**
 * 
 */
package co.com.grupoasd.documental.cliente.trd.model;

import java.util.List;

/**
 * Dto TrdArea
 * 
 * @author alopez
 * @since Junio 20 de 2017
 */
public class TrdArea {

	private Integer trdAreaId;

	private Integer areaId;

	private Integer trdId;
	
	private List<TrdSerie> serieList;

	public TrdArea() {
	}

	public TrdArea(Integer trdAreaId) {
		this.trdAreaId = trdAreaId;
	}

	public Integer getTrdAreaId() {
		return trdAreaId;
	}

	public void setTrdAreaId(Integer trdAreaId) {
		this.trdAreaId = trdAreaId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getTrdId() {
		return trdId;
	}

	public void setTrdId(Integer trdId) {
		this.trdId = trdId;
	}

	/**
	 * @return the serieList
	 */
	public List<TrdSerie> getSerieList() {
		return serieList;
	}

	/**
	 * @param serieList the serieList to set
	 */
	public void setSerieList(List<TrdSerie> serieList) {
		this.serieList = serieList;
	}	
	
}

