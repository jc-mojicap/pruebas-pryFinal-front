/*
* Archivo: Serie.java
* Fecha creacion = 21/03/2017
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
import java.util.List;

/**
 * Modelo de dominio Serie.
 * 
 * @author JuanMojica
 *
 */
public class Serie implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la serie.
     */
    private Integer serieId;

    /**
     * Nombre de la serie.
     */
    private String nombre;

    /**
     * Código de la serie.
     */
    private String codigo;

    /**
     * Serie inactiva.
     */
    private Boolean inactivo;
    
    /**
     * Subseries
     */
    private List<Subserie> subseries;

    /**
     * @return the serieId
     */
    public Integer getSerieId() {
        return serieId;
    }

    /**
     * @param serieId
     *            the serieId to set
     */
    public void setSerieId(Integer serieId) {
        this.serieId = serieId;
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
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the inactivo
     */
    public Boolean getInactivo() {
        return inactivo;
    }

    /**
     * @param inactivo
     *            the inactivo to set
     */
    public void setInactivo(Boolean inactivo) {
        this.inactivo = inactivo;
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

	/*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((serieId == null) ? 0 : serieId.hashCode());
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
        Serie other = (Serie) obj;
        if (serieId == null) {
            if (other.serieId != null)
                return false;
        } else if (!serieId.equals(other.serieId))
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
        return "Serie [" + (serieId != null ? "serieId=" + serieId + ", " : "")
                + (nombre != null ? "nombre=" + nombre + ", " : "") + "]";
    }

}
