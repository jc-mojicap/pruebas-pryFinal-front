/*
 * Archivo: ResultadoImportacionTerceros.java
 * Fecha creacion: 22/05/2017
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
package co.com.grupoasd.documental.presentacion.service.correspondencia.dto;

import java.io.Serializable;

import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.comun.dto.RespuestaServicio;


/**
 * DTO para el resultado de la importación de terceros.
 * @author cestrada
 *
 */
public class ResultadoImpoTerceros extends RespuestaServicio implements Serializable {
    
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * regisInser.
     */
    private int regisInser;
    /**
     * regisError. 
     */
    private int regisError;
    /**
     * regisActua.
     */
    private int regisActua;
    /**
     * mensaje.
     */
    private String mensaje;
    /**
     * file.
     */
    private InfoMedia file;

    /**
     * getRegisInser.
     * @return int.
     */
    public int getRegisInser() {
        return regisInser;
    }

    /**
     * setRegisInser.
     * @param regisInser.
     */
    public void setRegisInser(int regisInser) {
        this.regisInser = regisInser;
    }

    /**
     * getRegisError.
     * @return int.
     */
    public int getRegisError() {
        return regisError;
    }

    /**
     * setRegisError.
     * @param regisError.
     */
    public void setRegisError(int regisError) {
        this.regisError = regisError;
    }

    /**
     * getRegisActua.
     * @return int.
     */
    public int getRegisActua() {
        return regisActua;
    }

    /**
     * setRegisActua.
     * @param regisActua.
     */
    public void setRegisActua(int regisActua) {
        this.regisActua = regisActua;
    }

    /**
     * getMensaje.
     * @return String.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * setMensaje.
     * @param mensaje.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * getFile.
     * @return InfoMedia.
     */
    public InfoMedia getFile() {
        return file;
    }

    /**
     * InfoMedia.
     * @param file.
     */
    public void setFile(InfoMedia file) {
        this.file = file;
    }

}
