/*
 * Archivo: CorAdjunto.java
 * Fecha creacion: 04/04/2017
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
 * Modelo de dominio CorAdjunto.
 * 
 * @author cestrada
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class CorAdjunto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * adjuntoId.
     */
    private Integer adjuntoId;
    /**
     * ruta.
     */
    private String ruta;
    /**
     * fecha.
     */
    private Date fecha;
    /**
     * eliminado.
     */
    private boolean eliminado;
    /**
     * secuencialVerificacion.
     */
    private String secuencialVerificacion;
    /**
     * radicadoId.
     */
    private Long radicadoId;
    /**
     * radicado.
     */
    private String radicado;
    /**
     * particionId.
     */
    private Integer particionId;
    /**
     * tipoDocumentalId.
     */
    private Integer tipoDocumentalId;
    /**
     * nombreTipoDocumental.
     */
    private String nombreTipoDocumental;
    /**
     * usuarioId.
     */
    private Integer usuarioId;
    /**
     * nombreUsuario.
     */
    private String nombreUsuario;
    
    /**
     * Bytes adjunto
     */
    private byte[] bytesAdjuntos;
    
    /**
     * Constructor
     */
    public CorAdjunto() {

    }

    /**
     * getAdjuntoId.
     * @return Integer
     */
    public Integer getAdjuntoId() {
        return adjuntoId;
    }

    /**
     * setAdjuntoId.
     * @param adjuntoId
     */
    public void setAdjuntoId(Integer adjuntoId) {
        this.adjuntoId = adjuntoId;
    }

    /**
     * getRuta.
     * @return String
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * setRuta.
     * @param ruta
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * getFecha.
     * @return Date
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * setFecha.
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * isEliminado.
     * @return boolean
     */
    public boolean isEliminado() {
        return eliminado;
    }

    /**
     * setEliminado.
     * @param eliminado
     */
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    /**
     * getSecuencialVerificacion.
     * @return String
     */
    public String getSecuencialVerificacion() {
        return secuencialVerificacion;
    }

    /**
     * setSecuencialVerificacion.
     * @param secuencialVerificacion
     */
    public void setSecuencialVerificacion(String secuencialVerificacion) {
        this.secuencialVerificacion = secuencialVerificacion;
    }

    /**
     * getRadicadoId.
     * @return Long
     */
    public Long getRadicadoId() {
        return radicadoId;
    }

    /**
     * setRadicadoId.
     * @param radicadoId
     */
    public void setRadicadoId(Long radicadoId) {
        this.radicadoId = radicadoId;
    }

    /**
     * getRadicado.
     * @return String
     */
    public String getRadicado() {
        return radicado;
    }

    /**
     * setRadicado.
     * @param radicado
     */
    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    /**
     * getParticionId.
     * @return Integer
     */
    public Integer getParticionId() {
        return particionId;
    }

    /**
     * setParticionId.
     * @param particionId
     */
    public void setParticionId(Integer particionId) {
        this.particionId = particionId;
    }

    /**
     * tipoDocumentalId.
     * @return Integer
     */
    public Integer getTipoDocumentalId() {
        return tipoDocumentalId;
    }

    /**
     * setTipoDocumentalId.
     * @param tipoDocumentalId
     */
    public void setTipoDocumentalId(Integer tipoDocumentalId) {
        this.tipoDocumentalId = tipoDocumentalId;
    }

    /**
     * getNombreTipoDocumental.
     * @return String
     */
    public String getNombreTipoDocumental() {
        return nombreTipoDocumental;
    }

    /**
     * setNombreTipoDocumental.
     * @param nombreTipoDocumental
     */
    public void setNombreTipoDocumental(String nombreTipoDocumental) {
        this.nombreTipoDocumental = nombreTipoDocumental;
    }

    /**
     * getUsuarioId.
     * @return Integer
     */
    public Integer getUsuarioId() {
        return usuarioId;
    }

    /**
     * setUsuarioId.
     * @param usuarioId
     */
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * getNombreUsuario.
     * @return String
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * setNombreUsuario.
     * @param nombreUsuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    /**
     * getBytesAdjuntos
     * @return byte[]
     */
    public byte[] getBytesAdjuntos() {
    	return this.bytesAdjuntos;
    }
    
    /**
     * setBytesAdjuntos
     * @param bytesAdjuntos arreglo de byte[]
     */
    public void setBytesAdjuntos(byte[] bytesAdjuntos) {
    	this.bytesAdjuntos = bytesAdjuntos;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adjuntoId == null) ? 0 : adjuntoId.hashCode());
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
        CorAdjunto other = (CorAdjunto) obj;
        if (adjuntoId == null) {
            if (other.adjuntoId != null)
                return false;
        } else if (!adjuntoId.equals(other.adjuntoId))
            return false;
        return true;
    }

}
