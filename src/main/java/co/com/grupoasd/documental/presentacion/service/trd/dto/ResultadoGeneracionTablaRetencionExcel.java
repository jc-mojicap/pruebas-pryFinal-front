package co.com.grupoasd.documental.presentacion.service.trd.dto;

import java.io.Serializable;

import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.comun.dto.RespuestaServicio;

public class ResultadoGeneracionTablaRetencionExcel extends RespuestaServicio implements Serializable {

	/**
	 * 
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
