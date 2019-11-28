/*
* Archivo: CorTransportadora.java
* Fecha creacion = 22/03/2017
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
package co.com.grupoasd.documental.cliente.correspondencia.model;

import java.io.Serializable;

public class CorTransportadora implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador de la transportadora.
	 */
	private Integer transportadoraId;

	/**
	 * Nombre de la transportadora.
	 */
	private String nombre;

	/**
	 * Transportadora inactiva.
	 */
	private boolean inactivo;

	/**
	 * @return the transportadoraId
	 */
	public Integer getTransportadoraId() {
		return transportadoraId;
	}

	/**
	 * @param transportadoraId
	 *            the transportadoraId to set
	 */
	public void setTransportadoraId(Integer transportadoraId) {
		this.transportadoraId = transportadoraId;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the inactivo
	 */
	public boolean isInactivo() {
		return inactivo;
	}

	/**
	 * @param inactivo
	 *            the inactivo to set
	 */
	public void setInactivo(boolean inactivo) {
		this.inactivo = inactivo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transportadoraId == null) ? 0 : transportadoraId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CorTransportadora other = (CorTransportadora) obj;
		if (transportadoraId == null) {
			if (other.transportadoraId != null)
				return false;
		} else if (!transportadoraId.equals(other.transportadoraId))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CorTransportadoraDTO ["
				+ (transportadoraId != null ? "transportadoraId=" + transportadoraId + ", " : "")
				+ (nombre != null ? "nombre=" + nombre + ", " : "") + "inactivo=" + inactivo + "]";
	}

}
