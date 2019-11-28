/*
 * Archivo: CorTerceroXRadicado.java
 * Fecha creacion: 05/04/2017
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
package co.com.grupoasd.documental.cliente.correspondencia.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Modelo de dominio CorTerceroXRadicado.
 * 
 * @author cestrada
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CorTerceroXRadicado implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * radicadoId.
     */
    private Long radicadoId;
    /**
     * radicado.
     */
    private String radicado;
    /**
     * terceroId.
     */
    private Integer terceroId;
    /**
     * nombreTercero.
     */
    private String nombreTercero;
    /**
     * fechaAsignacion.
     */
    private Date fechaAsignacion;
    /**
     * usuarioAsignacion.
     */
    private Integer usuarioAsignacion;
    /**
     * remitente.
     */
    private boolean remitente;
    /**
     * principal.
     */
    private boolean principal;
    /**
     * reasignado.
     */
    private boolean reasignado;

    /**
     * Constructor.
     */
    public CorTerceroXRadicado() {

    }

    /**
     * getRadicadoId.
     * 
     * @return
     */
    public Long getRadicadoId() {
        return radicadoId;
    }

    /**
     * setRadicadoId.
     * 
     * @param radicadoId
     */
    public void setRadicadoId(Long radicadoId) {
        this.radicadoId = radicadoId;
    }

    /**
     * getRadicado.
     * 
     * @return String
     */
    public String getRadicado() {
        return radicado;
    }

    /**
     * setRadicado.
     * 
     * @param radicado
     */
    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    /**
     * getTerceroId.
     * 
     * @return Integer
     */
    public Integer getTerceroId() {
        return terceroId;
    }

    /**
     * setTerceroId.
     * 
     * @param terceroId
     */
    public void setTerceroId(Integer terceroId) {
        this.terceroId = terceroId;
    }

    /**
     * getNombreTercero.
     * 
     * @return String
     */
    public String getNombreTercero() {
        return nombreTercero;
    }

    /**
     * setNombreTercero.
     * 
     * @param nombreTercero
     */
    public void setNombreTercero(String nombreTercero) {
        this.nombreTercero = nombreTercero;
    }

    /**
     * getFechaAsignacion.
     * 
     * @return Date
     */
    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    /**
     * setFechaAsignacion.
     * 
     * @param fechaAsignacion
     */
    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    /**
     * getUsuarioAsignacion.
     * 
     * @return Integer
     */
    public Integer getUsuarioAsignacion() {
        return usuarioAsignacion;
    }

    /**
     * setUsuarioAsignacion.
     * 
     * @param usuarioAsignacion
     */
    public void setUsuarioAsignacion(Integer usuarioAsignacion) {
        this.usuarioAsignacion = usuarioAsignacion;
    }

    /**
     * getRemitente.
     * 
     * @return Boolean
     */
    public boolean getRemitente() {
        return remitente;
    }

    /**
     * setRemitente.
     * 
     * @param remitente
     */
    public void setRemitente(boolean remitente) {
        this.remitente = remitente;
    }

    /**
     * getPrincipal.
     * 
     * @return
     */
    public boolean getPrincipal() {
        return principal;
    }

    /**
     * setPrincipal.
     * 
     * @param principal
     */
    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    /**
     * getReasignado.
     * 
     * @return Boolean
     */
    public boolean getReasignado() {
        return reasignado;
    }

    /**
     * setReasignado.
     * 
     * @param reasignado
     */
    public void setReasignado(boolean reasignado) {
        this.reasignado = reasignado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((radicadoId == null) ? 0 : radicadoId.hashCode());
        result = prime * result + ((terceroId == null) ? 0 : terceroId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CorTerceroXRadicado other = (CorTerceroXRadicado) obj;
        if (radicadoId == null) {
            if (other.radicadoId != null)
                return false;
        } else if (!radicadoId.equals(other.radicadoId))
            return false;
        if (terceroId == null) {
            if (other.terceroId != null)
                return false;
        } else if (!terceroId.equals(other.terceroId))
            return false;
        return true;
    }

}
