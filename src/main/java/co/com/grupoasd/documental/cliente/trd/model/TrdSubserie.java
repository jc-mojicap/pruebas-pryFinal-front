/**
 * 
 */
package co.com.grupoasd.documental.cliente.trd.model;

import java.util.Date;

/**
 * Dto TrdSubserie
 * 
 * @author alopez
 * @since Junio 20 de 2017
 */
public class TrdSubserie {
	
	private Integer trdSubserieId;

	private Integer trdSerieId;

	private Character agUnidad;

	private Integer agValor;

	private Character acUnidad;

	private Integer acValor;

	private int usuarioModifica;

	private Date fechaModifica;

	private Integer subserieId;

	private Integer tipoDispId;
	
	private String procedimiento;
	

	public TrdSubserie() {
	}

	public TrdSubserie(Integer trdSubserieId) {
		this.trdSubserieId = trdSubserieId;
	}

	public TrdSubserie(Integer trdSubserieId, Integer trdSerieId, int usuarioModifica, Date fechaModifica) {
		this.trdSubserieId = trdSubserieId;
		this.trdSerieId = trdSerieId;
		this.usuarioModifica = usuarioModifica;
		this.fechaModifica = fechaModifica;
	}

	public Integer getTrdSubserieId() {
		return trdSubserieId;
	}

	public void setTrdSubserieId(Integer trdSubserieId) {
		this.trdSubserieId = trdSubserieId;
	}

	public Integer getTrdSerieId() {
		return trdSerieId;
	}

	public void setTrdSerieId(Integer trdSerieId) {
		this.trdSerieId = trdSerieId;
	}

	public Character getAgUnidad() {
		return agUnidad;
	}

	public void setAgUnidad(Character agUnidad) {
		this.agUnidad = agUnidad;
	}

	public Integer getAgValor() {
		return agValor;
	}

	public void setAgValor(Integer agValor) {
		this.agValor = agValor;
	}

	public Character getAcUnidad() {
		return acUnidad;
	}

	public void setAcUnidad(Character acUnidad) {
		this.acUnidad = acUnidad;
	}

	public Integer getAcValor() {
		return acValor;
	}

	public void setAcValor(Integer acValor) {
		this.acValor = acValor;
	}

	public int getUsuarioModifica() {
		return usuarioModifica;
	}

	public void setUsuarioModifica(int usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}

	public Date getFechaModifica() {
		return fechaModifica;
	}

	public void setFechaModifica(Date fechaModifica) {
		this.fechaModifica = fechaModifica;
	}

	public Integer getSubserieId() {
		return subserieId;
	}

	public void setSubserieId(Integer subserieId) {
		this.subserieId = subserieId;
	}

	public Integer getTipoDispId() {
		return tipoDispId;
	}

	public void setTipoDispId(Integer tipoDispId) {
		this.tipoDispId = tipoDispId;
	}

	/**
	 * @return the procedimiento
	 */
	public String getProcedimiento() {
		return procedimiento;
	}

	/**
	 * @param procedimiento the procedimiento to set
	 */
	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}	

}
