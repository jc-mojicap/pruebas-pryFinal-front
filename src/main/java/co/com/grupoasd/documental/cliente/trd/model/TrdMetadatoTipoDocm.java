/*
* Archivo: TrdMetadatoTipoDocm.java
* Fecha creacion = 20/06/2017
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
import java.util.Date;

public class TrdMetadatoTipoDocm implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private int orden;
    private boolean obligatorio;
    private boolean inactivo;
    private String observaciones;
    private int usuarioModifica;
    private Date fechaModifica;
    private int metadatoId;
    private String nombreMetadato;
    private int tipoDocumentalId;
    private int tipoDatoId;
    private String nombreTipoDato;
    
    public TrdMetadatoTipoDocm() {
    
    }
    
    public TrdMetadatoTipoDocm(int metadatoId, int tipoDocumentalId) {
        this.metadatoId = metadatoId;
        this.tipoDocumentalId = tipoDocumentalId;
    }

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }
    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }
    /**
     * @return the obligatorio
     */
    public boolean isObligatorio() {
        return obligatorio;
    }
    /**
     * @param obligatorio the obligatorio to set
     */
    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
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
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }
    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    /**
     * @return the usuarioModifica
     */
    public int getUsuarioModifica() {
        return usuarioModifica;
    }
    /**
     * @param usuarioModifica the usuarioModifica to set
     */
    public void setUsuarioModifica(int usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }
    /**
     * @return the fechaModifica
     */
    public Date getFechaModifica() {
        return fechaModifica;
    }
    /**
     * @param fechaModifica the fechaModifica to set
     */
    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }
    /**
     * @return the metadatoId
     */
    public int getMetadatoId() {
        return metadatoId;
    }
    /**
     * @param metadatoId the metadatoId to set
     */
    public void setMetadatoId(int metadatoId) {
        this.metadatoId = metadatoId;
    }
    /**
     * @return the nombreMetadato
     */
    public String getNombreMetadato() {
        return nombreMetadato;
    }
    /**
     * @param nombreMetadato the nombreMetadato to set
     */
    public void setNombreMetadato(String nombreMetadato) {
        this.nombreMetadato = nombreMetadato;
    }
    /**
     * @return the tipoDocumentalId
     */
    public int getTipoDocumentalId() {
        return tipoDocumentalId;
    }
    /**
     * @param tipoDocumentalId the tipoDocumentalId to set
     */
    public void setTipoDocumentalId(int tipoDocumentalId) {
        this.tipoDocumentalId = tipoDocumentalId;
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
        result = prime * result + metadatoId;
        result = prime * result + tipoDocumentalId;
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
        TrdMetadatoTipoDocm other = (TrdMetadatoTipoDocm) obj;
        if (metadatoId != other.metadatoId)
            return false;
        if (tipoDocumentalId != other.tipoDocumentalId)
            return false;
        return true;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TrdMetadatoTipoDocm [orden=" + orden + ", obligatorio=" + obligatorio + ", inactivo=" + inactivo + ", "
                + (observaciones != null ? "observaciones=" + observaciones + ", " : "") + "usuarioModifica="
                + usuarioModifica + ", " + (fechaModifica != null ? "fechaModifica=" + fechaModifica + ", " : "")
                + "metadatoId=" + metadatoId + ", "
                + (nombreMetadato != null ? "nombreMetadato=" + nombreMetadato + ", " : "") + "tipoDocumentalId="
                + tipoDocumentalId + "]";
    }
    
}
