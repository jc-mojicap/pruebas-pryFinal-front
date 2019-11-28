/*
* Archivo: Subserie.java
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
import co.com.grupoasd.documental.cliente.trd.model.TrdOpcionDisposicionFinal;


/**
 * Modelo de dominio Subserie.
 * 
 * @author JuanMojica
 *
 */
public class Subserie implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la subserie.
     */
    private Integer subserieId;

    /**
     * Nombre de la subserie.
     */
    private String nombre;

    /**
     * Código de la subserie.
     */
    private String codigo;

    /**
     * Subserie inactiva.
     */
    private Boolean inactivo;

    /** Ag unidad
     */
    private char agUnidad;
    
    /**
     * Ag Valor
     */
    private int agValor;

    /**
     * Ac Unidad
     */
    private char acUnidad;
    
    /**
     * Ac valor
     */
    private int acValor;
    
    /**
     * Tipo disposición final
     */
    private int tipoDispFinal;
    
    /**
     * Procedimiento
     */
    private String procedimiento;
    
    /**
     * Opciones disposición final
     */
    private List<TrdOpcionDisposicionFinal> opcionDisposicionFinals;
    
    /**
	 * TipoDocumental Asociados.
	 */
	private List<TipoDocumental> tipoDocumentalList;
    
    /**
     * @return the subserieId
     */
    public Integer getSubserieId() {
        return subserieId;
    }

    /**
     * @param subserieId
     *            the subserieId to set
     */
    public void setSubserieId(Integer subserieId) {
        this.subserieId = subserieId;
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
	 * @return the agUnidad
	 */
	public char getAgUnidad() {
		return agUnidad;
	}

	/**
	 * @param agUnidad the agUnidad to set
	 */
	public void setAgUnidad(char agUnidad) {
		this.agUnidad = agUnidad;
	}

	/**
	 * @return the agValor
	 */
	public int getAgValor() {
		return agValor;
	}

	/**
	 * @param agValor the agValor to set
	 */
	public void setAgValor(int agValor) {
		this.agValor = agValor;
	}

	/**
	 * @return the acUnidad
	 */
	public char getAcUnidad() {
		return acUnidad;
	}

	/**
	 * @param acUnidad the acUnidad to set
	 */
	public void setAcUnidad(char acUnidad) {
		this.acUnidad = acUnidad;
	}

	/**
	 * @return the acValor
	 */
	public int getAcValor() {
		return acValor;
	}

	/**
	 * @param acValor the acValor to set
	 */
	public void setAcValor(int acValor) {
		this.acValor = acValor;
	}

	/**
	 * @return the tipoDispFinal
	 */
	public int getTipoDispFinal() {
		return tipoDispFinal;
	}

	/**
	 * @param tipoDispFinal the tipoDispFinal to set
	 */
	public void setTipoDispFinal(int tipoDispFinal) {
		this.tipoDispFinal = tipoDispFinal;
	}
	
	/**
	 * @return the opcionDisposicionFinals
	 */
	public List<TrdOpcionDisposicionFinal> getOpcionDisposicionFinals() {
		return opcionDisposicionFinals;
	}

	/**
	 * @param opcionDisposicionFinals the opcionDisposicionFinals to set
	 */
	public void setOpcionDisposicionFinals(List<TrdOpcionDisposicionFinal> opcionDisposicionFinals) {
		this.opcionDisposicionFinals = opcionDisposicionFinals;
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
	
	/**
	 * @return the tipoDocumentalList
	 */
	public List<TipoDocumental> getTipoDocumentalList() {
		return tipoDocumentalList;
	}

	/**
	 * @param tipoDocumentalList the tipoDocumentalList to set
	 */
	public void setTipoDocumentalList(List<TipoDocumental> tipoDocumentalList) {
		this.tipoDocumentalList = tipoDocumentalList;
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
        result = prime * result + ((subserieId == null) ? 0 : subserieId.hashCode());
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
        Subserie other = (Subserie) obj;
        if (subserieId == null) {
            if (other.subserieId != null)
                return false;
        } else if (!subserieId.equals(other.subserieId))
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
        return "Subserie [" + (subserieId != null ? "subserieId=" + subserieId + ", " : "")
                + (nombre != null ? "nombre=" + nombre + ", " : "") + "]";
    }

}
