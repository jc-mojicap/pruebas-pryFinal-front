/*
* Archivo: TrdTipoDato.java
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

public class TrdTipoDato implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * tipoDatoId.
     */
    private int tipoDatoId;
    
    /**
     * nombre.
     */
    private String nombre;
    
    public TrdTipoDato() {
        
    }

    public TrdTipoDato(int tipoDatoId) {
        this.tipoDatoId = tipoDatoId;
    }

    public TrdTipoDato(int tipoDatoId, String nombre) {
        this.tipoDatoId = tipoDatoId;
        this.nombre = nombre;
    }

    /**
     * @return the tipoDatoId
     */
    public int getTipoDatoId() {
        return tipoDatoId;
    }

    /**
     * @param tipoDatoId the tipoDatoId to set
     */
    public void setTipoDatoId(int tipoDatoId) {
        this.tipoDatoId = tipoDatoId;
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
        result = prime * result + tipoDatoId;
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
        TrdTipoDato other = (TrdTipoDato) obj;
        if (tipoDatoId != other.tipoDatoId)
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TrdTipoDatoDto [tipoDatoId=" + tipoDatoId + ", " + (nombre != null ? "nombre=" + nombre : "") + "]";
    }

}
