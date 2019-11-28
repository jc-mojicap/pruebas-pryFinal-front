/*
 * Archivo: MdtDepartamentoDto.java
 * Fecha creacion: 02/03/2017
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
package co.com.grupoasd.documental.cliente.catalogo.model;

import java.io.Serializable;

/**
 * DTO MdtDepartamento.
 * @author cestrada
 */
public class MdtDepartamento implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Codigo dane del departamento.
     */
    private String codigoDane;
    /**
     * Nombre del departamento.
     */
    private String nombre;
    
    /**
     * Constructor.
     */
    public MdtDepartamento(){
        
    }

    /**
     * getCodigoDane.
     * @return String
     */
    public String getCodigoDane() {
        return codigoDane;
    }

    /**
     * setCodigoDane.
     * @param codigoDane
     */
    public void setCodigoDane(String codigoDane) {
        this.codigoDane = codigoDane;
    }

    /**
     * getNombre.
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setNombre.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
   

   
    
}
