/**
 * 
 */
package co.com.grupoasd.documental.cliente.trd.model;

import java.io.Serializable;
import java.util.Date;

import co.com.grupoasd.documental.cliente.catalogo.model.Empresa;

/**
 * Dto modelo TrdOpcionDisposicionFinal
 * 
 * @author alopez
 * @since Junio 15 de 2017
 */
public class TrdOpcionDisposicionFinal implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer opcDispId;

	private String nombre;

	private boolean inactivo;

	private int usuarioModifica;

	private Date fechaModifica;

	private Empresa empresaId;
	
	private boolean activo;

	/**
	 * Constructor
	 */
	public TrdOpcionDisposicionFinal() {
	}

	public TrdOpcionDisposicionFinal(Integer opcDispId) {
		this.opcDispId = opcDispId;
	}

	public TrdOpcionDisposicionFinal(Integer opcDispId, String nombre, boolean inactivo, int usuarioModifica,
			Date fechaModifica) {
		this.opcDispId = opcDispId;
		this.nombre = nombre;
		this.inactivo = inactivo;
		this.usuarioModifica = usuarioModifica;
		this.fechaModifica = fechaModifica;
	}

	public Integer getOpcDispId() {
		return opcDispId;
	}

	public void setOpcDispId(Integer opcDispId) {
		this.opcDispId = opcDispId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean getInactivo() {
		return inactivo;
	}

	public void setInactivo(boolean inactivo) {
		this.inactivo = inactivo;
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

	public Empresa getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Empresa empresaId) {
		this.empresaId = empresaId;
	}

	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
