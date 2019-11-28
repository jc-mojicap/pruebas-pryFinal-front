/*
* Archivo: CorTipoEmbalaje.java
* Fecha creacion = 23/03/2017
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

public class CorTipoEmbalaje implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

    /**
     * Identificador del corTipoEmbalaje.
     */
    private Integer tipoEmbalajeId;
    
    /**
     * Nombre del tipo de embalaje.
     */
    private String nombre;
    
    /**
     * CorTipoEmbalaje inactivo,
     */
    private String inactivo;

    /**
     * @return the tipoEmbalajeId
     */
    public Integer getTipoEmbalajeId() {
        return tipoEmbalajeId;
    }

    /**
     * @param tipoEmbalajeId the tipoEmbalajeId to set
     */
    public void setTipoEmbalajeId(Integer tipoEmbalajeId) {
        this.tipoEmbalajeId = tipoEmbalajeId;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the inactivo
     */
    public String getInactivo() {
        return inactivo;
    }

    /**
     * @param inactivo the inactivo to set
     */
    public void setInactivo(String inactivo) {
        this.inactivo = inactivo;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipoEmbalajeId == null) ? 0 : tipoEmbalajeId.hashCode());
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
		CorTipoEmbalaje other = (CorTipoEmbalaje) obj;
		if (tipoEmbalajeId == null) {
			if (other.tipoEmbalajeId != null)
				return false;
		} else if (!tipoEmbalajeId.equals(other.tipoEmbalajeId))
			return false;
		return true;
	}
	
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CorTipoEmbalajeDTO [" + (tipoEmbalajeId != null ? "tipoEmbalajeId=" + tipoEmbalajeId + ", " : "")
                + (nombre != null ? "nombre=" + nombre + ", " : "") + (inactivo != null ? "inactivo=" + inactivo : "")
                + "]";
    }
    
}
