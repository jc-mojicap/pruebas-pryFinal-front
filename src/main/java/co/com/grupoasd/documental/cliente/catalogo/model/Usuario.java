/*
* Archivo: Usuario.java
* Fecha creacion = 29/03/2017
* Todos los derechos de propiedad intelectual e industrial sobre esta
* aplicacion son de propiedad exclusiva del GRUPO ASESORIA EN
* SISTEMATIZACION DE DATOS SOCIEDAD POR ACCIONES SIMPLIFICADA – GRUPO ASD S.A.S.
* Su uso, alteracion, reproduccion o modificacion sin el debido
* consentimiento por escrito del GRUPO ASD S.A.S.
* autorizacion por parte de su autor quedan totalmente prohibidos.
* 
* Este programa se encuentra protegido por las disposiciones de la
* Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
* propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
* previstas en la Ley.
*/
package co.com.grupoasd.documental.cliente.catalogo.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Modelo de dominio Usuario.
 * 
 * @author JuanMojica
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Identificador del usuario.
     */
    private Integer usuarioId;

    /**
     * Número de identificación del usuario.
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
     * Primer nombre del usuario.
     */
    private String primerNombre;
    /**
     * Segundo nombre del usuario.
     */
    private String segundoNombre;

    /**
     * Primer apellido del usuario.
     */
    private String primerApellido;

    /**
     * Segundo apellido del usuario.
     */
    private String segundoApellido;

    /**
     * Nombre de usuario.
     */
    private String nombreUsuario;

    /**
     * Correo del usuario.
     */
    private String correo;

    /**
     * Usuario inactivo.
     */
    private boolean inactivo;

    /**
     * Identificador del área a la que pertenece el usuario.
     */
    private Integer areaId;

    /**
     * Nombre del área a la que pertenece el usuario.
     */
    private String nombreArea;

    /**
     * Identificador de la empresa a la que está relacionada el área.
     */
    private Integer empresaId;

    /**
     * Nombre de la empresa a la que está relacionada el área.
     */
    private String nombreEmpresa;

    /**
     * Identificador del rol del usuario.
     */
    private Integer rolId;

    /**
     * Nombre del rol del usuario.
     */
    private String nombreRol;
    /**
     * Usuario modifica.
     */
    private int usuarioModifica;
    /**
     * Fecha modificación.
     */
    private Date fechaModifica;
    /**
     * Identificador del tipo de identificador.
     */
    private Integer tipoIdentificacionId;
    /**
     * Nombre del tipo de identificación.
     */
    private String nombreTipoIdentificacion;

    /**
     * Constructor.
     */
    public Usuario() {

    }

    /**
     * @return the usuarioId
     */
    public Integer getUsuarioId() {
        return usuarioId;
    }

    /**
     * 
     * @param usuarioId
     *            the usuarioId to set 
     */
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * 
     * @return the numeroIdentificacion 
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * 
     * @param numeroIdentificacion
     *            the numeroIdentificacion to set 
     */
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    /**
     * 
     * @return the codigoUsuario 
     */
    public boolean isAdministrador() {
        return administrador;
    }

    /**
     * setAdministrador.
     * 
     * @param administrador
     *            Es administrador.
     */
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    /**
     * getCodigoUsuario.
     * 
     * @return String 
     */
    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    /**
     * 
     * @param codigoUsuario
     *            Código del usuario.
     */
    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    /**
     * 
     * @return the primerNombre
     */
    public String getPrimerNombre() {
        return primerNombre;
    }

    /**
     * 
     * @param primerNombre
     *            the primerNombre to set
     */
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    /**
     * 
     * @return the segundoNombre
     */
    public String getSegundoNombre() {
        return segundoNombre;
    }

    /**
     * 
     * @param segundoNombre
     *            the segundoNombre to set 
     */
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    /**
     * 
     * @return the primerApellido 
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * 
     * @param primerApellido
     *            the primerApellido to set 
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * 
     * @return the segundoApellido 
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * 
     * @param segundoApellido
     *            the segundoApellido to set
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * 
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * 
     * @param nombreUsuario
     *            the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * 
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * 
     * @param correo
     *            the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * 
     * @return the inactivo
     */
    public boolean isInactivo() {
        return inactivo;
    }

    /**
     * 
     * @param inactivo
     *            the inactivo to set
     */
    public void setInactivo(boolean inactivo) {
        this.inactivo = inactivo;
    }

    /**
     * getUsuarioModifica.
     * 
     * @return int
     */
    public int getUsuarioModifica() {
        return usuarioModifica;
    }

    /**
     * setUsuarioModifica.
     * 
     * @param usuarioModifica
     *            Usuario modifica.
     */
    public void setUsuarioModifica(int usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    /**
     * getFechaModifica.
     * 
     * @return Date
     */
    public Date getFechaModifica() {
        return fechaModifica;
    }

    /**
     * setFechaModifica.
     * 
     * @param fechaModifica
     *            Fecha modificación.
     */
    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    /**
     * 
     * @return the areaId
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * 
     * @param areaId
     *            the areaId to set
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * @return the nombreArea
     */
    public String getNombreArea() {
        return nombreArea;
    }

    /**
     * @param nombreArea
     *            the nombreArea to set
     */
    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    /**
     * @return the empresaId
     */
    public Integer getEmpresaId() {
        return empresaId;
    }

    /**
     * 
     * @param empresaId
     *            the empresaId to set
     */
    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * 
     * @param nombreEmpresa
     *            the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * @return the rolId
     */
    public Integer getRolId() {
        return rolId;
    }

    /**
     * 
     * @param rolId
     *            the rolId to set
     */
    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    /**
     * 
     * @return the nombreRol
     */
    public String getNombreRol() {
        return nombreRol;
    }

    /**
     * getTipoIdentificacionId.
     * 
     * @return Integer
     */
    public Integer getTipoIdentificacionId() {
        return tipoIdentificacionId;
    }

    /**
     * setTipoIdentificacionId.
     * 
     * @param tipoIdentificacionId
     *            Identificador del tipo de identificador.
     */
    public void setTipoIdentificacionId(Integer tipoIdentificacionId) {
        this.tipoIdentificacionId = tipoIdentificacionId;
    }

    /**
     * getNombreTipoIdentificacion.
     * 
     * @return String
     */
    public String getNombreTipoIdentificacion() {
        return nombreTipoIdentificacion;
    }

    /**
     * setNombreTipoIdentificacion.
     * 
     * @param nombreTipoIdentificacion
     *            Nombre del tipo de identificador.
     */
    public void setNombreTipoIdentificacion(String nombreTipoIdentificacion) {
        this.nombreTipoIdentificacion = nombreTipoIdentificacion;
    }

    /*
     * 
     * 
     * @param nombreRol the nombreRol to set
     */
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (usuarioId == null) {
            if (other.usuarioId != null)
                return false;
        } else if (!usuarioId.equals(other.usuarioId))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UsuarioDto [" + (usuarioId != null ? "usuarioId=" + usuarioId + ", " : "")
                + (numeroIdentificacion != null ? "numeroIdentificacion=" + numeroIdentificacion + ", " : "")
                + (primerNombre != null ? "primerNombre=" + primerNombre + ", " : "")
                + (segundoNombre != null ? "segundoNombre=" + segundoNombre + ", " : "")
                + (primerApellido != null ? "primerApellido=" + primerApellido + ", " : "")
                + (segundoApellido != null ? "segundoApellido=" + segundoApellido + ", " : "")
                + (nombreUsuario != null ? "nombreUsuario=" + nombreUsuario + ", " : "") + "inactivo=" + inactivo + ", "
                + (nombreArea != null ? "nombreArea=" + nombreArea + ", " : "")
                + (nombreEmpresa != null ? "nombreEmpresa=" + nombreEmpresa + ", " : "")
                + (nombreRol != null ? "nombreRol=" + nombreRol : "") + "]";
    }

    public String getNombreCompleto() {
        return ((primerNombre != null ? primerNombre + " " : "")
                + (segundoNombre != null ? segundoNombre + " " : "")
                + (primerApellido != null ? primerApellido + " " : "")
                + (segundoApellido != null ? segundoApellido : "")).trim();
    }
}