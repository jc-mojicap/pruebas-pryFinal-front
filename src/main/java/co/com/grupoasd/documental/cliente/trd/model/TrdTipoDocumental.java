/*
* Archivo: TrdTipoDocumental.java
* Fecha creacion = 24/06/2017
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
import java.util.List;

public class TrdTipoDocumental implements Serializable{

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private Integer trdTipoDocumentalId;

    private Integer tipoDocumentalId;
    
    private String nombreTipoDocumental;
    
    private Integer trdSubserieId;

    private List<TrdMetadatoTipoDocm> metadatosTipoDocm;
    
    /**
     * @return the trdTipoDocumentalId
     */
    public Integer getTrdTipoDocumentalId() {
        return trdTipoDocumentalId;
    }

    /**
     * @param trdTipoDocumentalId the trdTipoDocumentalId to set
     */
    public void setTrdTipoDocumentalId(Integer trdTipoDocumentalId) {
        this.trdTipoDocumentalId = trdTipoDocumentalId;
    }

    /**
     * @return the tipoDocumentalId
     */
    public Integer getTipoDocumentalId() {
        return tipoDocumentalId;
    }

    /**
     * @param tipoDocumentalId the tipoDocumentalId to set
     */
    public void setTipoDocumentalId(Integer tipoDocumentalId) {
        this.tipoDocumentalId = tipoDocumentalId;
    }

    /**
     * @return the nombreTipoDocumental
     */
    public String getNombreTipoDocumental() {
        return nombreTipoDocumental;
    }

    /**
     * @param nombreTipoDocumental the nombreTipoDocumental to set
     */
    public void setNombreTipoDocumental(String nombreTipoDocumental) {
        this.nombreTipoDocumental = nombreTipoDocumental;
    }

    /**
     * @return the trdSubserieId
     */
    public Integer getTrdSubserieId() {
        return trdSubserieId;
    }

    /**
     * @param trdSubserieId the trdSubserieId to set
     */
    public void setTrdSubserieId(Integer trdSubserieId) {
        this.trdSubserieId = trdSubserieId;
    }
    
    /**
     * @return the metadatosTipoDocm
     */
    public List<TrdMetadatoTipoDocm> getMetadatosTipoDocm() {
        return metadatosTipoDocm;
    }

    /**
     * @param metadatosTipoDocm the metadatosTipoDocm to set
     */
    public void setMetadatosTipoDocm(List<TrdMetadatoTipoDocm> metadatosTipoDocm) {
        this.metadatosTipoDocm = metadatosTipoDocm;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tipoDocumentalId == null) ? 0 : tipoDocumentalId.hashCode());
        result = prime * result + ((trdSubserieId == null) ? 0 : trdSubserieId.hashCode());
        result = prime * result + ((trdTipoDocumentalId == null) ? 0 : trdTipoDocumentalId.hashCode());
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
        TrdTipoDocumental other = (TrdTipoDocumental) obj;
        if (tipoDocumentalId == null) {
            if (other.tipoDocumentalId != null)
                return false;
        } else if (!tipoDocumentalId.equals(other.tipoDocumentalId))
            return false;
        if (trdSubserieId == null) {
            if (other.trdSubserieId != null)
                return false;
        } else if (!trdSubserieId.equals(other.trdSubserieId))
            return false;
        if (trdTipoDocumentalId == null) {
            if (other.trdTipoDocumentalId != null)
                return false;
        } else if (!trdTipoDocumentalId.equals(other.trdTipoDocumentalId))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TrdTipoDocumental ["
                + (trdTipoDocumentalId != null ? "trdTipoDocumentalId=" + trdTipoDocumentalId + ", " : "")
                + (tipoDocumentalId != null ? "tipoDocumentalId=" + tipoDocumentalId + ", " : "")
                + (trdSubserieId != null ? "trdSubserieId=" + trdSubserieId : "") + "]";
    }
    
}
