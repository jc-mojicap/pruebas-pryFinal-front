/*
 * Archivo: AccionesRolDto.java
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
public class AccionUsuariosDto implements Serializable{
    
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * accionDto.
     */
    private AccionDto accion;

    /**
     * usuarios.
     */
    private List<UsuarioDto> usuarios;
    
    
    /**
     * Constructor por defecto.
     */
    public AccionUsuariosDto(){
        
    }

    /**
     * Costructor.
     * @param accion AccionDto.
     * @param usuarios Lista de UsuariosDto.
     */
    public AccionUsuariosDto(AccionDto accion, List<UsuarioDto> usuarios){
        this.accion = accion;
        this.usuarios = usuarios;
    }
    
    /**
     * getAccion.
     * @return AccionDto.
     */
    public AccionDto getAccion() {
        return accion;
    }

    /**
     * setAccion.
     * @param accion AccionDto.
     */
    public void setAccion(AccionDto accion) {
        this.accion = accion;
    }
    
    /**
     * getUsuarios.
     * @return Lista de UsuairosDto.
     */
    public List<UsuarioDto> getUsuarios() {
        if(usuarios == null){
            usuarios = new ArrayList<>();
        }
        return usuarios;
    }

    /**
     * setRoles.
     * @param roles RolesDto.
     */
    public void setUsuarios(List<UsuarioDto> usuarios) {
        this.usuarios = usuarios;
    }

}
