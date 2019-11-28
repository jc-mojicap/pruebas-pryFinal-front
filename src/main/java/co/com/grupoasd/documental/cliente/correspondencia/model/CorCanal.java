/*
* Archivo: CorCanal.java
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

public class CorCanal implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador del canal.
     */
    private Integer canalId;

    /**
     * Nombre del canal.
     */
    private String nombre;

    /**
     * Codigo canal.
     */
    private char codigo;

    /**
     * Canal inactivo.
     */
    private boolean inactivo;

    /**
     * @return the canalId
     */
    public Integer getCanalId() {
        return canalId;
    }

    /**
     * @param canalId
     *            the canalId to set
     */
    public void setCanalId(Integer canalId) {
        this.canalId = canalId;
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
    public char getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(char codigo) {
        this.codigo = codigo;
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
        result = prime * result + ((canalId == null) ? 0 : canalId.hashCode());
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
        CorCanal other = (CorCanal) obj;
        if (canalId == null) {
            if (other.canalId != null)
                return false;
        } else if (!canalId.equals(other.canalId))
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
        return "CorCanal [" + (canalId != null ? "canalId=" + canalId + ", " : "")
                + (nombre != null ? "nombre=" + nombre + ", " : "") + "codigo=" + codigo + ", inactivo=" + inactivo
                + "]";
    }

}
