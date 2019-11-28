/*
* Archivo: UsuarioProxyService.java
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
package co.com.grupoasd.documental.cliente.catalogo;

import java.util.List;

import co.com.grupoasd.documental.cliente.catalogo.iface.UsuarioRestIface;
import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;

/**
 * Proxy que permite la invocación remota de los servicios del recurso Usuario.
 * 
 * @author JuanMojica
 *
 */
public final class UsuarioProxyService {

    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private UsuarioProxyService() {

    }

    /**
     * Busca un usuario por su id.
     * 
     * @param id
     *            Identificador del usuario.
     * @return Usuario asociado.
     */
    public static Usuario obtenerPorId(final Integer id) {
        return ServiciosEndpoint.get().proxy(UsuarioRestIface.class).obtenerPorId(id);
    }

    /**
     * Busca un usuario por su número de identificación.
     * 
     * @param numeroIdentificacion
     *            Número de identificador del usuario.
     * @return Usuario asociado.
     */
    public static List<Usuario> listarPorNumeroIdentificacion(final String numeroIdentificacion) {
        return ServiciosEndpoint.get().proxy(UsuarioRestIface.class).listarPorNumeroIdentificacion(numeroIdentificacion);
    }

    /**
     * Busca usuarios por sus nombres y apellidos y por la empresa a la que
     * pertenecen.
     * 
     * @param nombresYApellidos
     *            Nombres y apellidos a buscar.
     * @param empresa
     *            Empresa sobre la cual se realiza la busqueda.
     * @return Lista de usuarios asociados.
     */
    public static List<Usuario> listarPorNombresYApellidos(final Integer empresaId, final String nombresYApellidos) {
        return ServiciosEndpoint.get().proxy(UsuarioRestIface.class).listarPorNombresYApellidos(empresaId, nombresYApellidos);
    }

    /**
     * Busca usuarios por su área y empresa.
     * 
     * @param area
     *            Área sobre la cual se va a realizar la busqueda.
     * @param empresa
     *            Empresa a la cual pertenece el área.
     * @return Lista de usuarios asociados.
     */
    public static List<Usuario> listarPorArea(final Integer areaId) {
        return ServiciosEndpoint.get().proxy(UsuarioRestIface.class).listarPorArea(areaId);
    }

    /**
     * Retorna un lista de Usuario por su número, nombre, rol y/o area.
     * 
     * @param empresaId
     *            Identificador de la empresa sobre la cual se va a realizar la
     *            búsqueda.
     * @param valor
     *            valor para realizar la búsqueda.
     * @return Lista con los resultados asociados.
     */
    public static List<Usuario> listarPorNumeroNombreRolArea(final Integer empresaId, final String valor) {
        return ServiciosEndpoint.get().proxy(UsuarioRestIface.class).listarPorNumeroNombreRolArea(empresaId, valor);
    }

    /**
     * Busca un usuario por su codigo.
     * 
     * @param codigo
     *            Código del usuario.
     * @return Usuario asociado.
     */
    public static Usuario buscarPorCodigo(final String codigo) {
        return ServiciosEndpoint.get().proxy(UsuarioRestIface.class).listarPorCodigo(codigo);
    }

    /**
     * Busca usuarios por nombre de usuario.
     * 
     * @param nombreUsuario
     *            nombre de usuario sobre la cual se va a realizar la busqueda.
     * @return Lista de usuarios asociados.
     */
    public static List<Usuario> listarPorNombreUsuario(final String nombreUsuario) {
        return ServiciosEndpoint.get().proxy(UsuarioRestIface.class).listarPorNombreUsuario(nombreUsuario);
    }
    
    /**
     * Busca un usuario por la empresa y su código de usuario.
     * @param empresaId Identificador de la empresa.
     * @param codigoUsuario Código del usuario.
     * @return Usuario asociado.
     */
    public static Usuario buscarPorEmpresaYCodigoUsuario(final Integer empresaId, final String codigoUsuario) {
        return ServiciosEndpoint.get().proxy(UsuarioRestIface.class).buscarPorEmpresaYCodigoUsuario(empresaId, codigoUsuario);
    }

    /**
     * Busca un usuario por su empresa, nombre de usuario y correo electrónico.
     * @param empresaId Identificador de la empresa.
     * @param nombreUsuario Nombre del usuario.
     * @param emailUsuario Correo electrónico del usuario.
     * @return Usuario asociado.
     */
    public static Usuario buscarPorEmpresaYNombreUsuarioYCorreo(final Integer empresaId, final String nombreUsuario, final String emailUsuario) {
        return ServiciosEndpoint.get().proxy(UsuarioRestIface.class).buscarPorEmpresaYNombreUsuarioYCorreo(empresaId, nombreUsuario, emailUsuario);
    }
    
    /**
     * Busca un usuario por su empresa y nombre de usuario.
     * @param empresaId Identificador de la empresa.
     * @param nombreUsuario Nombre del usuario.
     * @return Usuario asociado.
     */
    public static Usuario buscarPorEmpresaYNombreUsuario(final Integer empresaId, final String nombreUsuario) {
        return ServiciosEndpoint.get().proxy(UsuarioRestIface.class).buscarPorEmpresaYNombreUsuario(empresaId, nombreUsuario);
    }
}
