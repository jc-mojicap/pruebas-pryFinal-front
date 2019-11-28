/*
 * Archivo: CorUsuarioXRadicado.java
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
 * Modelo de dominio CorUsuarioXRadicado.
 * 
 * @author cestrada
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CorUsuarioXRadicado implements Serializable {

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
     * usuarioId.
     */
    private Integer usuarioId;
    /**
     * nombreUsuario.
     */
    private String nombreUsuario;
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
    public CorUsuarioXRadicado() {

    }

    /**
     * getRadicadoId.
     * 
     * @return Long.
     */
    public Long getRadicadoId() {
        return radicadoId;
    }

    /**
     * setRadicadoId.
     * 
     * @param radicadoId.
     */
    public void setRadicadoId(Long radicadoId) {
        this.radicadoId = radicadoId;
    }

    /**
     * getRadicado.
     * 
     * @return String.
     */
    public String getRadicado() {
        return radicado;
    }

    /**
     * setRadicado.
     * 
     * @param radicado.
     */
    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    /**
     * getUsuarioId.
     * 
     * @return Integer.
     */
    public Integer getUsuarioId() {
        return usuarioId;
    }

    /**
     * setUsuarioId.
     * 
     * @param usuarioId.
     */
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * getNombreUsuario.
     * 
     * @return nombreUsuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * setNombreUsuario.
     * 
     * @param nombreUsuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * getFechaAsignacion.
     * 
     * @return Date.
     */
    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    /**
     * setFechaAsignacion.
     * 
     * @param fechaAsignacion.
     */
    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    /**
     * getUsuarioAsignacion.
     * 
     * @return Integer.
     */
    public Integer getUsuarioAsignacion() {
        return usuarioAsignacion;
    }

    /**
     * setUsuarioAsignacion.
     * 
     * @param usuarioAsignacion.
     */
    public void setUsuarioAsignacion(Integer usuarioAsignacion) {
        this.usuarioAsignacion = usuarioAsignacion;
    }

    /**
     * getRemitente.
     * 
     * @return Boolean.
     */
    public boolean getRemitente() {
        return remitente;
    }

    /**
     * setRemitente.
     * 
     * @param remitente.
     */
    public void setRemitente(boolean remitente) {
        this.remitente = remitente;
    }

    /**
     * getPrincipal.
     * 
     * @return Boolean.
     */
    public boolean getPrincipal() {
        return principal;
    }

    /**
     * setPrincipal.
     * 
     * @param principal.
     */
    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    /**
     * getReasignado.
     * 
     * @return Boolean.
     */
    public boolean getReasignado() {
        return reasignado;
    }

    /**
     * setReasignado.
     * 
     * @param reasignado.
     */
    public void setReasignado(boolean reasignado) {
        this.reasignado = reasignado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((radicadoId == null) ? 0 : radicadoId.hashCode());
        result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
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
        CorUsuarioXRadicado other = (CorUsuarioXRadicado) obj;
        if (radicadoId == null) {
            if (other.radicadoId != null)
                return false;
        } else if (!radicadoId.equals(other.radicadoId))
            return false;
        if (usuarioId == null) {
            if (other.usuarioId != null)
                return false;
        } else if (!usuarioId.equals(other.usuarioId))
            return false;
        return true;
    }

}
