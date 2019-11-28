/*
 * Archivo: TipoRadicado.java
 * Fecha creacion: 14/03/2017
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
package co.com.grupoasd.documental.presentacion.comun.vo;

/**
 * Enum Tipos radicado.
 * @author cestrada
 *
 */
public enum TipoRadicado {
    ENTRADA('E', "Entrada"), SALIDA('S', "Salida"), INTERNA('I', "Interna"),;
    
    /**
     * id.
     */
    private char id;
    /**
     * nombre.
     */
    private String nombre;
    
    /**
     * Constructor.
     * @param id
     * @param nombre
     */
    private TipoRadicado(char id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    /**
     * getId.
     * @return char
     */
    public char getId() {
        return id;
    }
    
    /**
     * getNombre.
     * @return String
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * getTipoCorrespondencia.
     * @param id2
     * @return TipoRadicado
     */
    public static TipoRadicado getTipoCorrespondencia(char id2) {
        for (TipoRadicado tipo : TipoRadicado.values()) {
            if (id2 == tipo.getId())
                return tipo;
        }
        return null;
    }
    
}
