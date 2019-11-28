/*
* Archivo: TrdMetadato.java
* Fecha creacion = 12/06/2017
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

public class TrdMetadato implements Serializable{

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador del metadato.
     */
    private Integer metadatoId;
    
    /**
     * Nombre del metadato.
     */
    private String nombre;
    
    /**
     * Metadato inactivo.
     */
    private boolean inactivo;
    
    /**
     * Expresión regular.
     */
    private String expresionRegular;
    
    /**
     * Longitud del metadato.
     */
    private Integer longitud;
    
    /**
     * Valores enumerado del metadato.
     */
    private String valoresEnumerado;
    
    /**
     * Tabla del enumerado.
     */
    private String enumBdTabla;
    
    /**
     * Columna que se usará como etiqueta.
     */
    private String enumBdEtiqueta;
    
    /**
     * Columna que se usará como valor.
     */
    private String enumBdValor;
    
    /**
     * Identificador del tipo de dato.
     */
    private Integer tipoDatoId;
    
    /**
     * Nombre del tipo de dato.
     */
    private String nombreTipoDato;

    public TrdMetadato() {

    }

    public TrdMetadato(Integer metadatoId) {
        this.metadatoId = metadatoId;
    }

    public TrdMetadato(Integer metadatoId, String nombre, boolean inactivo) {
        this.metadatoId = metadatoId;
        this.nombre = nombre;
        this.inactivo = inactivo;
    }

    /**
     * @return the metadatoId
     */
    public Integer getMetadatoId() {
        return metadatoId;
    }

    /**
     * @param metadatoId the metadatoId to set
     */
    public void setMetadatoId(Integer metadatoId) {
        this.metadatoId = metadatoId;
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
    public boolean isInactivo() {
        return inactivo;
    }

    /**
     * @param inactivo the inactivo to set
     */
    public void setInactivo(boolean inactivo) {
        this.inactivo = inactivo;
    }

    /**
     * @return the expresionRegular
     */
    public String getExpresionRegular() {
        return expresionRegular;
    }

    /**
     * @param expresionRegular the expresionRegular to set
     */
    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

    /**
     * @return the longitud
     */
    public Integer getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the valoresEnumerado
     */
    public String getValoresEnumerado() {
        return valoresEnumerado;
    }

    /**
     * @param valoresEnumerado the valoresEnumerado to set
     */
    public void setValoresEnumerado(String valoresEnumerado) {
        this.valoresEnumerado = valoresEnumerado;
    }

    /**
     * @return the enumBdTabla
     */
    public String getEnumBdTabla() {
        return enumBdTabla;
    }

    /**
     * @param enumBdTabla the enumBdTabla to set
     */
    public void setEnumBdTabla(String enumBdTabla) {
        this.enumBdTabla = enumBdTabla;
    }

    /**
     * @return the enumBdEtiqueta
     */
    public String getEnumBdEtiqueta() {
        return enumBdEtiqueta;
    }

    /**
     * @param enumBdEtiqueta the enumBdEtiqueta to set
     */
    public void setEnumBdEtiqueta(String enumBdEtiqueta) {
        this.enumBdEtiqueta = enumBdEtiqueta;
    }

    /**
     * @return the enumBdValor
     */
    public String getEnumBdValor() {
        return enumBdValor;
    }

    /**
     * @param enumBdValor the enumBdValor to set
     */
    public void setEnumBdValor(String enumBdValor) {
        this.enumBdValor = enumBdValor;
    }

    /**
     * @return the tipoDatoId
     */
    public Integer getTipoDatoId() {
        return tipoDatoId;
    }

    /**
     * @param tipoDatoId the tipoDatoId to set
     */
    public void setTipoDatoId(Integer tipoDatoId) {
        this.tipoDatoId = tipoDatoId;
    }

    /**
     * @return the nombreTipoDato
     */
    public String getNombreTipoDato() {
        return nombreTipoDato;
    }

    /**
     * @param nombreTipoDato the nombreTipoDato to set
     */
    public void setNombreTipoDato(String nombreTipoDato) {
        this.nombreTipoDato = nombreTipoDato;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((metadatoId == null) ? 0 : metadatoId.hashCode());
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
        TrdMetadato other = (TrdMetadato) obj;
        if (metadatoId == null) {
            if (other.metadatoId != null)
                return false;
        } else if (!metadatoId.equals(other.metadatoId))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TrdMetadato [" + (metadatoId != null ? "metadatoId=" + metadatoId + ", " : "")
                + (nombre != null ? "nombre=" + nombre + ", " : "") + "inactivo=" + inactivo + ", "
                + (expresionRegular != null ? "expresionRegular=" + expresionRegular + ", " : "")
                + (longitud != null ? "longitud=" + longitud + ", " : "")
                + (valoresEnumerado != null ? "valoresEnumerado=" + valoresEnumerado + ", " : "")
                + (enumBdTabla != null ? "enumBdTabla=" + enumBdTabla + ", " : "")
                + (enumBdEtiqueta != null ? "enumBdEtiqueta=" + enumBdEtiqueta + ", " : "")
                + (enumBdValor != null ? "enumBdValor=" + enumBdValor + ", " : "")
                + (tipoDatoId != null ? "tipoDatoId=" + tipoDatoId + ", " : "")
                + (nombreTipoDato != null ? "nombreTipoDato=" + nombreTipoDato : "") + "]";
    }
    
}
