/*
 * Archivo: CorTercero.java
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
package co.com.grupoasd.documental.cliente.correspondencia.model;

import java.io.Serializable;
import java.util.Date;

import co.com.grupoasd.documental.presentacion.comun.dto.RespuestaServicio;

/**
 * Modelo de dominio de CorTercero.
 * 
 * @author cestrada
 */
public class CorTercero extends RespuestaServicio implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Identificador del tercero.
     */
    private Integer terceroId;
    /**
     * Número del tercero.
     */
    private String numero;

    /**
     * Nombre del tercero.
     */
    private String nombre;
    /**
     * Correo electrónico.
     */
    private String correo;
    /**
     * Dirección.
     */
    private String direccion;
    /**
     * Teléfonos.
     */
    private String telefonos;
    /**
     * Días de caída.
     */
    private int diasCaida;
    /**
     * Es inactivo.
     */
    private boolean inactivo;
    /**
     * Municipio de ubicación del tercero.
     */
    private String municipio;
    /**
     * nombreMunicipio.
     */
    private String nombreMunicipio;
    /**
     * Códgio del departamento.
     */
    private String departamento;
    /**
     * nombreDepartamento.
     */
    private String nombreDepartamento;
    /**
     * Identificación del responsable.
     */
    private String identificacionResp;
    /**
     * Responsable.
     */
    private String responsable;
    /**
     * Dependencia.
     */
    private String dependencia;
    /**
     * Cargo.
     */
    private String cargo;
    /**
     * Usuario modifica.
     */
    private int usuarioModifica;
    /**
     * Fecha modificación.
     */
    private Date fechaModifica;
    /**
     * Identificador de la empresa.
     */
    private Integer empresaId;
    /**
     * Nombre de la empresa.
     */
    private String nombreEmpresa;

    /**
     * Constructor
     */
    public CorTercero() {

    }

    /**
     * getTerceroId.
     * 
     * @return Integer
     */
    public Integer getTerceroId() {
        return terceroId;
    }

    /**
     * setTerceroId.
     * 
     * @param terceroId
     *            Identificador del tercero.
     */
    public void setTerceroId(Integer terceroId) {
        this.terceroId = terceroId;
    }

    /**
     * getNumero.
     * 
     * @return String
     */
    public String getNumero() {
        return numero;
    }

    /**
     * setNumero.
     * 
     * @param numero
     *            Número del tercero.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * getNombre.
     * 
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setNombre.
     * 
     * @param nombre
     *            Nombre del tercero.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * getCorreo.
     * 
     * @return String
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * setCorreo.
     * 
     * @param correo
     *            Correo electrónico.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * getDireccion.
     * 
     * @return String
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * setDireccion.
     * 
     * @param direccion
     *            Dirección.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * getTelefonos.
     * 
     * @return String
     */
    public String getTelefonos() {
        return telefonos;
    }

    /**
     * setTelefonos.
     * 
     * @param telefonos
     *            Teléfonos.
     */
    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    /**
     * getDiasCaida.
     * 
     * @return int
     */
    public int getDiasCaida() {
        return diasCaida;
    }

    /**
     * setDiasCaida.
     * 
     * @param diasCaida
     *            Días de caída.
     */
    public void setDiasCaida(int diasCaida) {
        this.diasCaida = diasCaida;
    }

    /**
     * isInactivo.
     * 
     * @return boolean
     */
    public boolean isInactivo() {
        return inactivo;
    }

    /**
     * setInactivo.
     * 
     * @param inactivo
     *            Es inactivo.
     */
    public void setInactivo(boolean inactivo) {
        this.inactivo = inactivo;
    }

    /**
     * getMunicipio.
     * 
     * @return
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * setMunicipio.
     * 
     * @param municipio
     *            Municipio.
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * getNombreMunicipio.
     * @return String
     */
    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    /**
     * setNombreMunicipio.
     * @param nombreMunicipio.
     */
    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the nombreDepartamento
     */
    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    /**
     * @param nombreDepartamento the nombreDepartamento to set
     */
    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    /**
     * getIdentificacionResp.
     * 
     * @return String
     */
    public String getIdentificacionResp() {
        return identificacionResp;
    }

    /**
     * setIdentificacionResp.
     * 
     * @param identificacionResp
     *            Identificación del responsable.
     */
    public void setIdentificacionResp(String identificacionResp) {
        this.identificacionResp = identificacionResp;
    }

    /**
     * getResponsable.
     * 
     * @return String
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * setResponsable.
     * 
     * @param responsable
     *            Responsable.
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    /**
     * getDependencia.
     * 
     * @return String
     */
    public String getDependencia() {
        return dependencia;
    }

    /**
     * setDependencia.
     * 
     * @param dependencia
     *            Dependencia.
     */
    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    /**
     * getCargo.
     * 
     * @return
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * setCargo.
     * 
     * @param cargo
     *            Cargo.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
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
     * getEmpresaId.
     * 
     * @return Integer
     */
    public Integer getEmpresaId() {
        return empresaId;
    }

    /**
     * setEmpresaId.
     * 
     * @param empresaId
     *            Identificador de la empresa.
     */
    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * getNombreEmpresa.
     * 
     * @return String
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * setNombreEmpresa.
     * 
     * @param nombreEmpresa
     *            Nombre de la empresa.
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (terceroId != null ? terceroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are
        // not set
        if (!(object instanceof CorTercero)) {
            return false;
        }
        CorTercero other = (CorTercero) object;
        if ((this.terceroId == null && other.terceroId != null)
                || (this.terceroId != null && !this.terceroId.equals(other.terceroId))) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CorTercero [" + (terceroId != null ? "terceroId=" + terceroId + ", " : "")
                + (numero != null ? "numero=" + numero + ", " : "") + (nombre != null ? "nombre=" + nombre + ", " : "")
                + "inactivo=" + inactivo + ", " + (empresaId != null ? "empresaId=" + empresaId + ", " : "")
                + (nombreEmpresa != null ? "nombreEmpresa=" + nombreEmpresa : "") + "]";
    }

}
