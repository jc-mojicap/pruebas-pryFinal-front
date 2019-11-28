package co.com.grupoasd.documental.presentacion.comun.vo.autenticacion;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.grupoasd.documental.presentacion.service.catalogo.dto.AccionDto;

/**
 * 
 * @author cestrada
 * @author jmaldonado
 */
public class SesionVo {

    /**
     * ipCliente.
     */
    private String ipCliente;
    /**
     * ipServidor.
     */
    private String ipServidor;
    /**
     * empresaId.
     */
    private Integer empresaId;
    /**
     * empresa.
     */
    private String empresa;
    /**
     * rolId.
     */
    private Integer rolId;
    /**
     * rol.
     */
    private String rol;
    /**
     * usuarioId.
     */
    private Integer usuarioId;
    /**
     * usuario.
     */
    private String usuario;
    /**
     * primerApellido.
     */
    private String primerApellido;
    /**
     * segundoApellido.
     */
    private String segundoApellido;
    /**
     * primerNombre.
     */
    private String primerNombre;
    /**
     * segundoNombre.
     */
    private String segundoNombre;
    /**
     * identificacion.
     */
    private String identificacion;
    /**
     * <<<<<<< HEAD ======= usuarioCreador.
     */
    private String usuarioCreador;
    /**
     * eliminado.
     */
    private boolean eliminado;
    /**
     * fecha.
     */
    private Date fecha;
    /**
     * administrador.
     */
    private boolean administrador;
    /**
     * acciones.
     */
    private List<AccionDto> acciones;

    /**
     * getIpCliente.
     * 
     * @return String
     */
    public String getIpCliente() {
        return ipCliente;
    }

    /**
     * setIpCliente.
     * 
     * @param ipCliente
     */
    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }

    /**
     * getIpServidor.
     * 
     * @return String
     */
    public String getIpServidor() {
        return ipServidor;
    }

    /**
     * setIpServidor.
     * 
     * @param ipServidor
     */
    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
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
     */
    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * getEmpresa.
     * 
     * @return String
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * setEmpresa.
     * 
     * @param empresa
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * getRolId.
     * 
     * @return Integer
     */
    public Integer getRolId() {
        return rolId;
    }

    /**
     * setRolId
     * 
     * @param rolId
     */
    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    /**
     * getRol.
     * 
     * @return String
     */
    public String getRol() {
        return rol;
    }

    /**
     * setRol.
     * 
     * @param rol
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * getUsuarioId.
     * 
     * @return Integer
     */
    public Integer getUsuarioId() {
        return usuarioId;
    }

    /**
     * setUsuarioId.
     * 
     * @param usuarioId
     */
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * getUsuario.
     * 
     * @return String
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * setUsuario.
     * 
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * getPrimerApellido.
     * 
     * @return String
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * setPrimerApellido.
     * 
     * @param primerApellido
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * getSegundoApellido.
     * 
     * @return String
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * setSegundoApellido.
     * 
     * @param segundoApellido
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * getPrimerNombre.
     * 
     * @return String
     */
    public String getPrimerNombre() {
        return primerNombre;
    }

    /**
     * setPrimerNombre.
     * 
     * @param primerNombre
     */
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    /**
     * getSegundoNombre.
     * 
     * @return
     */
    public String getSegundoNombre() {
        return segundoNombre;
    }

    /**
     * setSegundoNombre.
     * 
     * @param segundoNombre
     */
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    /**
     * getIdentificacion.
     * 
     * @return String
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * setIdentificacion.
     * 
     * @param identificacion
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * <<<<<<< HEAD ======= getUsuarioCreador.
     * 
     * @return String
     */
    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    /**
     * setUsuarioCreador.
     * 
     * @param usuarioCreador
     */
    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    /**
     * isEliminado.
     * 
     * @return boolean
     */
    public boolean isEliminado() {
        return eliminado;
    }

    /**
     * setEliminado
     * 
     * @param eliminado
     */
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    /**
     * getFecha.
     * 
     * @return Date
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * setFecha.
     * 
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * isAdministrador.
     * 
     * @return
     */
    public boolean isAdministrador() {
        return administrador;
    }

    /**
     * setAdministrador.
     * 
     * @param administrador
     */
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    /**
     * <<<<<<< HEAD getAcciones.
     * 
     * @return Lista de acciones DTO del usuario en sesión.
     */
    public List<AccionDto> getAcciones() {
        return acciones;
    }

    /**
     * setAcciones.
     * 
     * @param acciones
     */
    public void setAcciones(List<AccionDto> acciones) {
        this.acciones = acciones;
    }

    /**
     * getNombreCompletoUsuario.
     * 
     * @return Nombre completo del usuario en sesión.
     */
    public String getNombreCompletoUsuario() {
        return StringUtils.join(primerNombre, " ", segundoNombre, " ", primerApellido, " ", segundoApellido)
                .replace("  ", " ");
    }
}
