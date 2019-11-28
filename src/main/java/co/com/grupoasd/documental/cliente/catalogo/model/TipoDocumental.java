/*
 * Archivo: TipoDocumentalDto.java
 * Fecha creacion: 21/03/2017
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
package co.com.grupoasd.documental.cliente.catalogo.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author cestrada
 */

public class TipoDocumental implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador del tipo documental.
     */
    private Integer tipoDocumentalId;

    /**
     * Nombre del tipo documental.
     */
    private String nombre;

    /**
     * Tipo documental inactivo.
     */
    private boolean inactivo;

    /**
     * Usuario modifica.
     */
    private int usuarioModifica;

    /**
     * Identificador de la empresa.
     */
    private Date fechaModifica;

    /**
     * Constructor por defecto
     */
    public TipoDocumental() {

    }

    /**
     * Constructor.
     * 
     * @param nombre
     *            Nombre de la empresa.
     * @param inactivo
     *            La empresa es inactiva.
     * @param subserieId
     *            Identificador de la subserie
     */
    public TipoDocumental(String nombre, boolean inactivo) {
        this.nombre = nombre;
        this.inactivo = inactivo;
    }

    /**
     * getTipoDocumentalId.
     * 
     * @return Integer
     */
    public Integer getTipoDocumentalId() {
        return tipoDocumentalId;
    }

    /**
     * setTipoDocumentalId.
     * 
     * @param setTipoDocumentalId
     *            Identificador del tipo documental.
     */
    public void setTipoDocumentalId(Integer tipoDocumentalId) {
        this.tipoDocumentalId = tipoDocumentalId;
    }

    /**
     * getNombre.
     * 
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setNombre.
     * 
     * @param nombre
     *            Nombre del tipo documental.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * getInactivo.
     * 
     * @return boolean
     */
    public boolean getInactivo() {
        return inactivo;
    }

    /**
     * setInactivo.
     * 
     * @param inactivo
     *            TipoDocumental inactivo.
     */
    public void setInactivo(boolean inactivo) {
        this.inactivo = inactivo;
    }

    /**
     * getUsuarioModifica.
     * 
     * @return int
     */
    public int getUsuarioModifica() {
        return usuarioModifica;
    }

    /**
     * setUsuarioModifica.
     * 
     * @param usuarioModifica
     *            Usuario modificacion tipo documental.
     */
    public void setUsuarioModifica(int usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    /**
     * getFechaModifica.
     * 
     * @return Date
     */
    public Date getFechaModifica() {
        return fechaModifica;
    }

    /**
     * setFechaModifica.
     * 
     * @param fechaModifica
     *            Fecha modificacion tipo documental.
     */
    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoDocumentalId != null ? tipoDocumentalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are
        // not set
        if (!(object instanceof TipoDocumental)) {
            return false;
        }
        TipoDocumental other = (TipoDocumental) object;
        if ((this.tipoDocumentalId == null && other.tipoDocumentalId != null)
                || (this.tipoDocumentalId != null && !this.tipoDocumentalId.equals(other.tipoDocumentalId))) {
            return false;
        }
        return true;
    }

}
