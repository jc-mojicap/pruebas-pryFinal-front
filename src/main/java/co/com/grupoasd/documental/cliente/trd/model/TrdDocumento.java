/**
 * 
 */
package co.com.grupoasd.documental.cliente.trd.model;

import java.util.Date;

/**
 * Dto TrdDocumento
 * @author LuisaHernandez
 * @since Junio 23 de 2017
 */
public class TrdDocumento {private Integer documentoId;

private String nombre;

private Integer observaciones;


private int usuarioCreaId;
private String usuarioCreaNombreCompleto;

private Date fechaCrea;

private int usuarioModifica;

private Date fechaModifica;

private Integer expedientePadreId;    
private String expedientePadreNombre;

private Integer trdTipoDocumentalId; 
private Integer tipoDocumentalId;    
private String tipoDocumentalNombre; 

private Integer trdSubserieId;
private Integer subserieId;
private String subserieNombre;

private Integer trdSerieId;
private Integer serieId;
private String serieNombre;

private Integer trdAreaId;
private Integer areaId;
private String areaNombre;

private Integer trdId;
private String trdNombre;

private Integer numeroExpedientes;

private Integer radicadoId;
private String radicado;


public TrdDocumento() {
}

public TrdDocumento(Integer documentoId) {
    this.documentoId = documentoId;
}

public TrdDocumento(Integer documentoId, String nombre, Date fechaCrea, int usuarioModifica, Date fechaModifica) {
    this.documentoId = documentoId;
    this.nombre = nombre;
    this.fechaCrea = fechaCrea;
    this.usuarioModifica = usuarioModifica;
    this.fechaModifica = fechaModifica;
}

public Integer getDocumentoId() {
	return documentoId;
}

public void setDocumentoId(Integer documentoId) {
	this.documentoId = documentoId;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public Integer getObservaciones() {
	return observaciones;
}

public void setObservaciones(Integer observaciones) {
	this.observaciones = observaciones;
}

public Date getFechaCrea() {
	return fechaCrea;
}

public void setFechaCrea(Date fechaCrea) {
	this.fechaCrea = fechaCrea;
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

public Integer getExpedientePadreId() {
	return expedientePadreId;
}

public void setExpedientePadreId(Integer expedientePadreId) {
	this.expedientePadreId = expedientePadreId;
}

public String getExpedientePadreNombre() {
	return expedientePadreNombre;
}

public void setExpedientePadreNombre(String expedientePadreNombre) {
	this.expedientePadreNombre = expedientePadreNombre;
}

public Integer getTrdTipoDocumentalId() {
	return trdTipoDocumentalId;
}

public void setTrdTipoDocumentalId(Integer trdTipoDocumentalId) {
	this.trdTipoDocumentalId = trdTipoDocumentalId;
}

public Integer getTipoDocumentalId() {
	return tipoDocumentalId;
}

public void setTipoDocumentalId(Integer tipoDocumentalId) {
	this.tipoDocumentalId = tipoDocumentalId;
}

public String getTipoDocumentalNombre() {
	return tipoDocumentalNombre;
}

public void setTipoDocumentalNombre(String tipoDocumentalNombre) {
	this.tipoDocumentalNombre = tipoDocumentalNombre;
}

public Integer getTrdSubserieId() {
	return trdSubserieId;
}

public void setTrdSubserieId(Integer trdSubserieId) {
	this.trdSubserieId = trdSubserieId;
}

public Integer getSubserieId() {
	return subserieId;
}

public void setSubserieId(Integer subserieId) {
	this.subserieId = subserieId;
}

public String getSubserieNombre() {
	return subserieNombre;
}

public void setSubserieNombre(String subserieNombre) {
	this.subserieNombre = subserieNombre;
}

public Integer getTrdSerieId() {
	return trdSerieId;
}

public void setTrdSerieId(Integer trdSerieId) {
	this.trdSerieId = trdSerieId;
}

public Integer getSerieId() {
	return serieId;
}

public void setSerieId(Integer serieId) {
	this.serieId = serieId;
}

public String getSerieNombre() {
	return serieNombre;
}

public void setSerieNombre(String serieNombre) {
	this.serieNombre = serieNombre;
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

public String getAreaNombre() {
	return areaNombre;
}

public void setAreaNombre(String areaNombre) {
	this.areaNombre = areaNombre;
}

public Integer getTrdId() {
	return trdId;
}

public void setTrdId(Integer trdId) {
	this.trdId = trdId;
}

public String getTrdNombre() {
	return trdNombre;
}

public void setTrdNombre(String trdNombre) {
	this.trdNombre = trdNombre;
}

public Integer getNumeroExpedientes() {
	return numeroExpedientes;
}

public void setNumeroExpedientes(Integer numeroExpedientes) {
	this.numeroExpedientes = numeroExpedientes;
}

public Integer getRadicadoId() {
	return radicadoId;
}

public void setRadicadoId(Integer radicadoId) {
	this.radicadoId = radicadoId;
}

public String getRadicado() {
	return radicado;
}

public void setRadicado(String radicado) {
	this.radicado = radicado;
}


public String getUsuarioCreaNombreCompleto() {
	return usuarioCreaNombreCompleto;
}

public void setUsuarioCreaNombreCompleto(String usuarioCreaNombreCompleto) {
	this.usuarioCreaNombreCompleto = usuarioCreaNombreCompleto;
}

public int getUsuarioCreaId() {
	return usuarioCreaId;
}

public void setUsuarioCreaId(int usuarioCreaId) {
	this.usuarioCreaId = usuarioCreaId;
}


}