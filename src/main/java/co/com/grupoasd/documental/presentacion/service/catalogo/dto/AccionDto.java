/*
 * Archivo: AccionDto.java
 * Fecha creacion: 11/04/2017
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
package co.com.grupoasd.documental.presentacion.service.catalogo.dto;

import java.io.Serializable;

/**
 * @author cestrada
 *
 */
public class AccionDto implements Serializable, Cloneable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * accionId.
     */
    private Integer accionId;
    /**
     * etiqueta.
     */
    private String etiqueta;
    /**
     * inactivo.
     */
    private boolean inactivo;

    /**
     * Constructor.
     */
    public AccionDto() {

    }

    /**
     * getAccionId.
     * 
     * @return Integer.
     */
    public Integer getAccionId() {
        return accionId;
    }

    /**
     * setAccionId.
     * 
     * @param accionId.
     */
    public void setAccionId(Integer accionId) {
        this.accionId = accionId;
    }

    /**
     * getEtiqueta.
     * 
     * @return String.
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * setEtiqueta.
     * 
     * @param etiqueta.
     */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * getInactivo.
     * 
     * @return boolean.
     */
    public boolean getInactivo() {
        return inactivo;
    }

    /**
     * setInactivo.
     * 
     * @param inactivo.
     */
    public void setInactivo(boolean inactivo) {
        this.inactivo = inactivo;
    }

    public AccionDto clone() throws CloneNotSupportedException {
        return (AccionDto) super.clone();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AccionDto [" + (accionId != null ? "accionId=" + accionId + ", " : "")
                + (etiqueta != null ? "etiqueta=" + etiqueta + ", " : "") + "inactivo=" + inactivo + "]";
    }

}
