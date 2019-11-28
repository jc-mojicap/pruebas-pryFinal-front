/*
 * Archivo: RespuestaServicio.java
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
package co.com.grupoasd.documental.presentacion.comun.dto;

import java.io.Serializable;

/**
 * @author cestrada
 *
 */
public class RespuestaServicio implements Serializable{
    
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * respuesta.
     */
    private String respuesta;
    /**
     * error.
     */
    private boolean error;
    
    /**
     * codigoError.
     */
    private String codigoError;
    
    /**
     * Constructor. 
     */
    public RespuestaServicio(){
        
    }
    
    /**
     * @param respuesta
     * @param error
     * @param codigoError
     * Constructor. 
     */
    public RespuestaServicio(String respuesta, boolean error, String codigoError){
        this.respuesta = respuesta;
        this.error = error;
        this.codigoError = codigoError;
    }
    
    /**
     * getRespuesta.
     * @return String.
     */
    public String getRespuesta() {
        return respuesta;
    }
    /**
     * setRespuesta.
     * @param respuesta.
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    /**
     * isError
     * @return boolean.
     */
    public boolean isError() {
        return error;
    }
    /**
     * setError.
     * @param error.
     */
    public void setError(boolean error) {
        this.error = error;
    }
    /**
     * getCodigoError.
     * @return String.
     */
    public String getCodigoError() {
        return codigoError;
    }
    /**
     * setCodigoError.
     * @param codigoError.
     */
    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

}
