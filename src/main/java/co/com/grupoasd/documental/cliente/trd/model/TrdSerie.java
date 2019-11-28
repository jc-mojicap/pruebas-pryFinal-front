/**
 * 
 */
package co.com.grupoasd.documental.cliente.trd.model;

import java.util.List;

/**
 * Dto TrdSerie
 * @author alopez
 * @since Junio 20 de 2017
 */
public class TrdSerie {
	
	private Integer trdSerieId;
	   
    private Integer trdAreaId;
    
    private Integer serieId;
    
    private List<TrdSubserie> subseriesList;

    public TrdSerie() {
    }

    public TrdSerie(Integer trdSerieId) {
        this.trdSerieId = trdSerieId;
    }

    public TrdSerie(Integer trdSerieId, Integer trdAreaId) {
        this.trdSerieId = trdSerieId;
        this.trdAreaId = trdAreaId;
    }

    public Integer getTrdSerieId() {
        return trdSerieId;
    }

    public void setTrdSerieId(Integer trdSerieId) {
        this.trdSerieId = trdSerieId;
    }

    public Integer getTrdAreaId() {
        return trdAreaId;
    }

    public void setTrdAreaId(Integer trdAreaId) {
        this.trdAreaId = trdAreaId;
    }

    public Integer getSerieId() {
        return serieId;
    }

    public void setSerieId(Integer serieId) {
        this.serieId = serieId;
    }

	/**
	 * @return the subseriesList
	 */
	public List<TrdSubserie> getSubseriesList() {
		return subseriesList;
	}

	/**
	 * @param subseriesList the subseriesList to set
	 */
	public void setSubseriesList(List<TrdSubserie> subseriesList) {
		this.subseriesList = subseriesList;
	}

}
