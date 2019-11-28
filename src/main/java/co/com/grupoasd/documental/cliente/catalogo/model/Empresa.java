/*
 * Archivo: Empresa.java
 * Fecha creacion: 28/02/2017
 * Todos los derechos de propiedad intelectual e industrial sobre esta
 * aplicacion son de propiedad exclusiva del GRUPO ASESORIA EN
 * SISTEMATIZACION DE DATOS SOCIEDAD POR ACCIONES SIMPLIFICADA – GRUPO ASD S.A.S.
 * Su uso, alteracion, reproduccion o modificacion sin la debida
 * consentimiento por escrito de GRUPO ASD S.A.S.
 * autorizacion por parte de su autor quedan totalmente prohibidos.
 * 
 * Este programa se encuentra protegido por las disposiciones de la
 * Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
 * propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
 * previstas en la Ley.
 */
package co.com.grupoasd.documental.cliente.catalogo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Modelo de dominio Empresa.
 * @author Juan Carlos Castellanos
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Empresa implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Identificador de la empresa.
     */
    private Integer empresaId;
   
    /**
     * Nombre de la empresa.
     */
    private String nombre;
   
    /**
     * Codigo de la empresa
     */
    private String codigoEmpresa;
    
    /**
     * Empresa inactiva.
     */
    private Boolean inactivo;
    
    /**
     * Ultimo usuario que modifica la empresa.
     */
    private Long usuarioModifica;
    
    /**
     * Ultima fecha de modificacion.
     */
    private Date fechaModifica;

    /**
     * Constructor por defecto.
     */
    public Empresa() {
    }

    /**
     * Constructor.
     * @param nombre Nombre de la empresa.
     * @param inactivo La empresa es inactiva.
     */
    public Empresa(final String nombre, final Boolean inactivo) {
        this.nombre = nombre;
        this.inactivo = inactivo;
    }

    /**
     * getEmpresaId.
     * @return Integer
     */
    public Integer getEmpresaId() {
        return empresaId;
    }

    /**
     * setEmpresaId.
     * @param empresaId Identitificador de la empresa.
     */
    public void setEmpresaId(final Integer empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * getNombre.
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setNombre.
     * @param nombre Nombre de la empresa.
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * getCodigoEmpresa.
     * @return String
     */
    public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

    /**
     * setCodigoEmpresa.
     * @param codigoEmpresa Codigo de la empresa.
     */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
     * getInactivo.
     * @return Boolean
     */
    public Boolean getInactivo() {
        return inactivo;
    }

    /**
     * setInactivo.
     * @param inactivo Empresa inactiva.
     */
    public void setInactivo(final Boolean inactivo) {
        this.inactivo = inactivo;
    }

    /**
     * getUsuarioModifica.
     * @return Long
     */
    public Long getUsuarioModifica() {
        return usuarioModifica;
    }

    /**
     * setUsuarioModifica.
     * @param usuarioModifica Identificador del ultimo usuario que modifica la empresa.
     */
    public void setUsuarioModifica(final Long usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    /**
     * getFechaModifica.
     * @return Date
     */
    public Date getFechaModifica() {
        return fechaModifica;
    }

    /**
     * setFechaModifica.
     * @param fechaModifica Ultima fecha de modificacion.
     */
    public void setFechaModifica(final Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    /**
     * hashCode.
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.empresaId);
        return hash;
    }

    /**
     * equals.
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empresa other = (Empresa) obj;
        if (!Objects.equals(this.empresaId, other.empresaId)) {
            return false;
        }
        return true;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Empresa [" + (empresaId != null ? "empresaId=" + empresaId + ", " : "")
				+ (nombre != null ? "nombre=" + nombre + ", " : "")
				+ (codigoEmpresa != null ? "codigoEmpresa=" + codigoEmpresa + ", " : "")
				+ (inactivo != null ? "inactivo=" + inactivo : "") + "]";
	}

}
