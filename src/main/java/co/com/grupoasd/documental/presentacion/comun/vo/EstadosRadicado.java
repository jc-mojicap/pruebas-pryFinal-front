/*
 * Archivo: EstadoRadicado.java
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
 * Enum Estados radicado.
 * @author cestrada
 *
 */
public enum EstadosRadicado {
    RECIBIDO(1, "Recibido"), RADICADO(2, "Radicado"), INFORMADO(3, "Informado"), ASIGNADO(4, "Asignado"), GESTIONADO(5, "Gestionado"),;
    
    /**
     * id.
     */
    private int id;
    /**
     * nombre.
     */
    private String nombre;
    
    /**
     * Constructor.
     * @param id
     * @param nombre
     */
    private EstadosRadicado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    /**
     * getId.
     * @return int
     */
    public int getId() {
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
     * getEstadoRadicado.
     * @param id
     * @return
     */
    public static EstadosRadicado getEstadoRadicado(int id) {
        for (EstadosRadicado estado : EstadosRadicado.values()) {
            if (estado.getId() == id)
                return estado;
        }
        return null;
    }
    
}
