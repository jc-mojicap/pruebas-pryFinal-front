/*
* Archivo: ParametrosKeycloak.java
* Fecha creacion = 13/03/2017
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
package co.com.grupoasd.documental.cliente.catalogo.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ParametrosKeycloack implements Serializable{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador de la empresa.
	 */
	private Integer empresaId;
	
	/**
	 * Usuario.
	 */
	private String usuario;
	
	/**
	 * Clave.
	 */
	private String clave;
	
	/**
	 * Id del cliente.
	 */
	private String cliente;
	
	/**
	 * Secret.
	 */
	private String secret;
	
	/**
	 * nombreEmpresa.
	 */
	private String nombreEmpresa;

	/**
	 * codigoEmpresa.
	 */
	private String codigoEmpresa; 
	
	/**
	 * @return the empresaId
	 */
	public Integer getEmpresaId() {
		return empresaId;
	}

	/**
	 * @param empresaId the empresaId to set
	 */
	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the clienteId
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param clienteId the clienteId to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * @return the empresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empresaId == null) ? 0 : empresaId.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		ParametrosKeycloack other = (ParametrosKeycloack) obj;
		if (empresaId == null) {
			if (other.empresaId != null)
				return false;
		} else if (!empresaId.equals(other.empresaId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ParametrosKeycloack [empresaId=" + empresaId + ", clienteId=" + cliente + ", nombreEmpresa=" + nombreEmpresa + "]";
	}

	/**
	 * @return the codigoEmpresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * @param codigoEmpresa the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	
}
