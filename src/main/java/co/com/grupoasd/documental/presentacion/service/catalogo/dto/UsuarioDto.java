/*
 * Archivo: UsuarioDto.java
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
public class UsuarioDto implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Identificador del usuario.
     */
    private Integer usuarioId;
    /**
     * Número de identificación.
     */
    private String numeroIdentificacion;
    /**
     * Es administrador.
     */
    private boolean administrador;
    /**
     * Código del usuario.
     */
    private String codigoUsuario;
    /**
     * Primer nombre.
     */
    private String primerNombre;
    /**
     * Segundo nombre.
     */
    private String segundoNombre;
    /**
     * Primer apellido.
     */
    private String primerApellido;
    /**
     * Segundo apellido.
     */
    private String segundoApellido;
    /**
     * Nombre del usuario.
     */
    private String nombreUsuario;
    /**
     * Correo electrónico.
     */
    private String correo;
    /**
     * Es incactivo.
     */
    private boolean inactivo;
    /**
     * Identificador del área.
     */
    private Integer areaId;
    /**
     * Nombre del área.
     */
    private String nombreArea;
    /**
     * Identificador del rol.
     */
    private Integer rolId;
    /**
     * Nombre del rol.
     */
    private String nombreRol;
    /**
     * Identificador del tipo de identificador.
     */
    private Integer tipoIdentificacionId;
    /**
     * Nombre del tipo de identificación.
     */
    private String nombreTipoIdentificacion;
    
    /**
     * accionesDto.
     */
    private List<AccionDto> acciones;
    
    
    public UsuarioDto() {

    }

    /**
     * getUsuarioId.
     * @return Integer
     */
    public Integer getUsuarioId() {
        return usuarioId;
    }

    /**
     * setUsuarioId Identificador del usuario.
     * @param usuarioId
     */
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
   
    /**
     * getNumeroIdentificacion.
     * @return String
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * setNumeroIdentificacion.
     * @param numeroIdentificacion Número de identificación.
     */
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    /**
     * isAdministrador.
     * @return boolean
     */
    public boolean isAdministrador() {
        return administrador;
    }

    /**
     * setAdministrador.
     * @param administrador Es administrador.
     */
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    /**
     * getCodigoUsuario.
     * @return String
     */
    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    /**
     * setCodigoUsuario.
     * @param codigoUsuario Código del usuario.
     */
    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    /**
     * getPrimerNombre.
     * @return String
     */
    public String getPrimerNombre() {
        return primerNombre;
    }

    /**
     * setPrimerNombre.
     * @param primerNombre Primer nombre.
     */
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    /**
     * getSegundoNombre.
     * @return String
     */
    public String getSegundoNombre() {
        return segundoNombre;
    }

    /**
     * setSegundoNombre.
     * @param segundoNombre Segundo nombre.
     */
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    /**
     * getPrimerApellido.
     * @return String
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * setPrimerApellido.
     * @param primerApellido Primer apellido.
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * getSegundoApellido.
     * @return String
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * setSegundoApellido.
     * @param segundoApellido Segundo apellido.
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * getNombreUsuario.
     * @return String
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * setNombreUsuario.
     * @param nombreUsuario Nombre del usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * getCorreo.
     * @return String
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * setCorreo.
     * @param correo Correo electrónico.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * isInactivo.
     * @return boolean
     */
    public boolean isInactivo() {
        return inactivo;
    }

    /**
     * setInactivo.
     * @param inactivo Es incactivo.
     */
    public void setInactivo(boolean inactivo) {
        this.inactivo = inactivo;
    }

    /**
     * getAreaId.
     * @return Integer
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * setAreaId.
     * @param areaId Identificador del área.
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * getNombreArea.
     * @return String
     */
    public String getNombreArea() {
        return nombreArea;
    }

    /**
     * setNombreArea.
     * @param nombreArea Nombre del área.
     */
    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    /**
     * getRolId.
     * @return Integer
     */
    public Integer getRolId() {
        return rolId;
    }

    /**
     * setRolId.
     * @param rolId Identificador del rol.
     */
    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    /**
     * getNombreRol.
     * @return String
     */
    public String getNombreRol() {
        return nombreRol;
    }

    /**
     * setNombreRol.
     * @param nombreRol Nombre del rol.
     */
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    /**
     * getTipoIdentificacionId.
     * @return Integer
     */
    public Integer getTipoIdentificacionId() {
        return tipoIdentificacionId;
    }

    /**
     * setTipoIdentificacionId.
     * @param tipoIdentificacionId Identificador del tipo de identificador.
     */
    public void setTipoIdentificacionId(Integer tipoIdentificacionId) {
        this.tipoIdentificacionId = tipoIdentificacionId;
    }

    /**
     * getNombreTipoIdentificacion.
     * @return String
     */
    public String getNombreTipoIdentificacion() {
        return nombreTipoIdentificacion;
    }

    /**
     * setNombreTipoIdentificacion.
     * @param nombreTipoIdentificacion Nombre del tipo de identificador.
     */
    public void setNombreTipoIdentificacion(String nombreTipoIdentificacion) {
        this.nombreTipoIdentificacion = nombreTipoIdentificacion;
    }

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
