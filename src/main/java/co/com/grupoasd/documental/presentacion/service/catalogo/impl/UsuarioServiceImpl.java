/*
* Archivo: UsuarioServiceImpl.java
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
package co.com.grupoasd.documental.presentacion.service.catalogo.impl;

import java.util.List;

import co.com.grupoasd.documental.cliente.catalogo.UsuarioProxyService;
import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

    @Override
    public Usuario obtenerPorId(Integer id) {
        return UsuarioProxyService.obtenerPorId(id);
    }

    @Override
    public List<Usuario> listarPorNumeroIdentificacion(String numeroIdentificacion) {
        return UsuarioProxyService.listarPorNumeroIdentificacion(numeroIdentificacion);
    }

    @Override
    public List<Usuario> listarPorNombresYApellidos(Integer empresaId, String nombresYApellidos) {
        return UsuarioProxyService.listarPorNombresYApellidos(empresaId, nombresYApellidos);
    }

    @Override
    public List<Usuario> listarPorArea(Integer areaId) {
        return UsuarioProxyService.listarPorArea(areaId);
    }

    @Override
    public List<Usuario> listarPorNumeroNombreRolArea(Integer empresaId, String valor) {
        return UsuarioProxyService.listarPorNumeroNombreRolArea(empresaId, valor);
    }

    @Override
    public Usuario buscarPorCodigo(String codigo) {
        return UsuarioProxyService.buscarPorCodigo(codigo);
    }

    @Override
    public List<Usuario> listarPorNombreUsuario(String nombreUsuario) {
        return UsuarioProxyService.listarPorNombreUsuario(nombreUsuario);
    }

    @Override
    public Usuario buscarPorEmpresaYCodigoUsuario(Integer empresaId, String codigoUsuario) {
        return UsuarioProxyService.buscarPorEmpresaYCodigoUsuario(empresaId, codigoUsuario);
    }

    @Override
    public Usuario buscarPorEmpresaYNombreUsuarioYCorreo(Integer empresaId, String nombreUsuario, String emailUsuario) {
        return UsuarioProxyService.buscarPorEmpresaYNombreUsuarioYCorreo(empresaId, nombreUsuario, emailUsuario);
    }
    
    @Override
    public Usuario buscarPorEmpresaYNombreUsuario(Integer empresaId, String nombreUsuario) {
        return UsuarioProxyService.buscarPorEmpresaYNombreUsuario(empresaId, nombreUsuario);
    }

}
