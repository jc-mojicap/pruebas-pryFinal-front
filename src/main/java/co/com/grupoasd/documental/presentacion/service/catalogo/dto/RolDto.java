/*
 * Archivo: RolDto.java
 * Fecha creacion: 11/04/2017
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
package co.com.grupoasd.documental.presentacion.service.catalogo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cestrada
 *
 */
public class RolDto implements Serializable{
    
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * rolId.
     */
    private Integer rolId;
    /**
     * nombre.
     */
    private String nombre;
    /**
     * inactivo.
     */
    private boolean inactivo;
    
    /**
     * Constructor.
     */
    public RolDto(){
        
    }
    
    /**
     * 
     * @return
     */
    public Integer getRolId() {
        return rolId;
    }

    /**
     * 
     * @param rolId
     */
    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    /**
     * 
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return
     */
    public boolean getInactivo() {
        return inactivo;
    }

    /**
     * 
     * @param inactivo
     */
    public void setInactivo(boolean inactivo) {
        this.inactivo = inactivo;
    }



    /**
     * accionesDto.
     */
    private List<AccionDto> acciones;
    
    /**
     * getAcciones.
     * @return Lista de accionesDto.
     */
    public List<AccionDto> getAcciones() {
        if(acciones == null){
            acciones = new ArrayList<>();
        }
        return acciones;
    }

    /**
     * setAcciones.
     * @param acciones AccionesDto.
     */
    public void setAcciones(List<AccionDto> acciones) {
        this.acciones = acciones;
    }

}
