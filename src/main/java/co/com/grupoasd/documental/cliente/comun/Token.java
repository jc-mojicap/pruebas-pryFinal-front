/*
 * Archivo: Token.java
 * Fecha creacion: 28/02/2017
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
package co.com.grupoasd.documental.cliente.comun;

/**
 * Token de autenticacion de tipo JWT.
 * @author jcastellanos
 */
public class Token {
    
    /**
     * Cadena con el token en formato JWT.
     */
    private final String cotenido;

    /**
     * Constructor.
     * @param cotenido Cadena en formato JWT.
     */
    public Token(final String cotenido) {
        this.cotenido = cotenido;
    }

    /**
     * Obtiene el contenido del token en formato JWT.
     * @return Cadena con el token.
     */
    public String getCotenido() {
        return cotenido;
    }
    
    
}
