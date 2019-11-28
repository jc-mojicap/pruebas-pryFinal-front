/*
* Archivo: TrdEstructura.java
* Fecha creacion = 06/06/2017
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
package co.com.grupoasd.documental.cliente.trd.model;

import java.io.Serializable;

public class TrdEstructura implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * estructuraId.
     */
    private int estructuraId;
    
    /**
     * nombre.
     */
    private String nombre;
    
    public TrdEstructura() {
        
    }

    public TrdEstructura(int estructuraId) {
        this.estructuraId = estructuraId;
    }

    public TrdEstructura(int estructuraId, String nombre) {
        this.estructuraId = estructuraId;
        this.nombre = nombre;
    }

    /**
     * @return the estructuraId
     */
    public int getEstructuraId() {
        return estructuraId;
    }

    /**
     * @param estructuraId the estructuraId to set
     */
    public void setEstructuraId(int estructuraId) {
        this.estructuraId = estructuraId;
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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + estructuraId;
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
        TrdEstructura other = (TrdEstructura) obj;
        if (estructuraId != other.estructuraId)
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TrdEstructuraDto [estructuraId=" + estructuraId + ", " + (nombre != null ? "nombre=" + nombre : "")
                + "]";
    }
    
}
