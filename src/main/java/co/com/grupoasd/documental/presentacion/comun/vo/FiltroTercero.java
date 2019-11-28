/*
 * Archivo: FiltroTercero.java
 * Fecha creacion: 17/05/2017
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
 * @author cestrada
 *
 */
public enum FiltroTercero {
    IDENTIFICACION(1, "Número identificación"), NOMBRE(2, "Nombre");
    
    /**
     * id.
     */
    private int id;
    /**
     * nombre.
     */
    private String valor;
    
    /**
     * Constructor.
     * @param id
     * @param valor
     */
    private FiltroTercero(int id, String valor) {
        this.id = id;
        this.valor = valor;
    }
    
    /**
     * getId.
     * @return int
     */
    public int getId() {
        return id;
    }
    
    /**
     * getValor.
     * @return String
     */
    public String getValor() {
        return valor;
    }
    
    /**
     * getFiltroTercero.
     * @param id2
     * @return FiltroTercero
     */
    public static FiltroTercero getFiltroTercero(Integer id2) {
        for (FiltroTercero tipo : FiltroTercero.values()) {
            if (id2 == tipo.getId())
                return tipo;
        }
        return null;
    }

}
