/*
 * Archivo: RadicadosDto.java
 * Fecha creacion: 29/03/2017
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
package co.com.grupoasd.documental.presentacion.service.correspondencia.dto;

import java.util.ArrayList;
import java.util.List;

import co.com.grupoasd.documental.cliente.correspondencia.model.CorTerceroXRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorUsuarioXRadicado;

/**
 * DTO UsuTerRadicadoDto. Dto que contiene la lista de usuarios y/o terceros radicados.
 * Extiende de RadicadoDto.
 * @author cestrada
 *
 */
public class UsuTerRadicadoDto extends RadicadoDto {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Lista de usuarios del radicado.
     */
    private List<CorUsuarioXRadicado> usuariosRadicado;
    /**
     * Lista de terceros del radicado.
     */
    private List<CorTerceroXRadicado> tercerosRadicado;

    /**
     * Constructor.
     */
    public UsuTerRadicadoDto(){
        
    }
    
    /**
     * getUsuariosRadicado.
     * @return List<Usuario
     */
    public List<CorUsuarioXRadicado> getUsuariosRadicado() {
        if(usuariosRadicado == null){
            usuariosRadicado = new ArrayList<>();
        }
        return usuariosRadicado;
    }

    /**
     * setUsuariosRadicado.
     * @param usuariosRadicado Lista de usuarios radicado.
     */
    public void setUsuariosRadicado(List<CorUsuarioXRadicado> usuariosRadicado) {
        this.usuariosRadicado = usuariosRadicado;
    }

    /**
     * getTercerosRadicado.
     * @return List<CorTercero>
     */
    public List<CorTerceroXRadicado> getTercerosRadicado() {
        if(tercerosRadicado == null){
            tercerosRadicado = new ArrayList<>();
        }
        return tercerosRadicado;
    }

    /**
     * setTercerosRadicado.
     * @param tercerosRadicado Lista de terceros radicado.
     */
    public void setTercerosRadicado(List<CorTerceroXRadicado> tercerosRadicado) {
        this.tercerosRadicado = tercerosRadicado;
    }

}
