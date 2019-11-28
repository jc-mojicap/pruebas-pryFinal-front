package co.com.grupoasd.documental.presentacion.comun.exception;

import org.apache.commons.lang3.StringUtils;

public class GeneralException extends RuntimeException {
    
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -854790277825958196L;
    /**
    *exceptionType.
    */
    private ExceptionType exceptionType = ExceptionType.NO_IDENTIFICADO;

    /**
     * Enum Tipos de excepciones.
     * @author cestrada
     *
     */
    public enum ExceptionType {
        CONTRASENA_INCORRECTA,
        ERROR_AUTENTICACION, 
        NO_IDENTIFICADO, 
        USUARIO_NO_ENCONTRADO; 
    }
    
    /**
     * Constructor.
     */
    public GeneralException() {
        super();
    }
    
    /**
     * Constructor.
     * @param message
     * @param exceptionType
     * @param cause
     */
    public GeneralException(String message, ExceptionType exceptionType, Throwable cause) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }
    
    /**
     * Constructor.
     * @param exceptionType
     * @param message
     * @param cause
     */
    public GeneralException(ExceptionType exceptionType, String message, Throwable cause) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }
    
    /**
     * Constructor.
     * @param message
     * @param exceptionType
     */
    public GeneralException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }
    
    /**
     * Constructor.
     * @param exceptionType
     * @param message
     */
    public GeneralException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }
    
    /**
     * Constructor.
     * @param message
     * @param cause
     */
    public GeneralException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructor.
     * @param message
     */
    public GeneralException(String message) {
        super(message);
    }
    
    /**
     * Constructor.
     * @param exceptionType
     */
    public GeneralException(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
    
    /**
     * Constructor.
     * @param cause
     */
    public GeneralException(Throwable cause) {
        super(cause);
    }
    
    /**
     * Constructor.
     * @return ExceptionType
     */
    public ExceptionType getExceptionType() {
        return exceptionType;
    }
    
    /**
     * setExceptionType.
     * @param exceptionType
     */
    public void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
    
    @Override
    public String getMessage() {
        return StringUtils.isNotBlank(super.getMessage()) ? super.getMessage() : getExceptionType().name();
    }
}