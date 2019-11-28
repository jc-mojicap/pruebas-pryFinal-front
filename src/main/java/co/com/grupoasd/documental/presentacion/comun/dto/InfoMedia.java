/*
 * Archivo: InfoMedia.java
 * Fecha creacion: 19/04/2017
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
package co.com.grupoasd.documental.presentacion.comun.dto;

import java.io.Serializable;

/**
 * DTO para transporte de archivos.
 * @author jmaldonado
 * @author cestrada
 */
public class InfoMedia implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * nombreArchivo.
     */
    private String nombreArchivo;
    /**
     * tipoArchivo.
     */
    private String tipoArchivo;
    /**
     * rutaArchivo.
     */
    private String rutaArchivo;
    /**
     * archivo.
     */
    private String archivo;
    
    /**
     * bytesArchivo.
     */
    private byte[] bytesArchivo;
    
    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the tipoArchivo
     */
    public String getTipoArchivo() {
        return tipoArchivo;
    }

    /**
     * @param tipoArchivo the tipoArchivo to set
     */
    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    /**
     * getRutaArchivo.
     * @return String.
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    /**
     * setRutaArchivo.
     * @param rutaArchivo.
     */
    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * getArchivo.
     * @return String.
     */
    public String getArchivo() {
        return archivo;
    }

    /**
     * setArchivo.
     * @param archivo.
     */
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    /**
     * getBytesArchivo.
     * @return Array de bytes.
     */
    public byte[] getBytesArchivo() {
        return bytesArchivo;
    }

    /**
     * bytesArchivo.
     * @param bytesArchivo
     */
    public void setBytesArchivo(byte[] bytesArchivo) {
        this.bytesArchivo = bytesArchivo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombreArchivo == null) ? 0 : nombreArchivo.hashCode());
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
        InfoMedia other = (InfoMedia) obj;
        if (nombreArchivo == null) {
            if (other.nombreArchivo != null)
                return false;
        } else if (!nombreArchivo.equals(other.nombreArchivo))
            return false;
        return true;
    }
    
    
    
    
}
