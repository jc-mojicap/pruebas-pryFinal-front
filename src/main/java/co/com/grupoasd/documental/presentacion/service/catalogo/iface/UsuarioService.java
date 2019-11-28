/*
* Archivo: UsuarioService.java
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
package co.com.grupoasd.documental.presentacion.service.catalogo.iface;

import java.util.List;

import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;

/**
 * Servicios del recurso Usuario.
 * 
 * @author JuanMojica
 *
 */
public interface UsuarioService {

    /**
     * Obtiene el usuario por su id.
     * 
     * @param id
     *            Identificación del usuario.
     * @return Usuario asociado.
     */
    Usuario obtenerPorId(final Integer id);

    /**
     * Lista usuarios por su número de identificación.
     * 
     * @param numeroIdentificacion
     *            Número de identificación a buscar.
     * @return Lista con los usuarios asociados.
     */
    List<Usuario> listarPorNumeroIdentificacion(final String numeroIdentificacion);

    /**
     * Lista usuarios por sus nombres y apellidos.
     * 
     * @param nombresYApellidos
     *            Nombres y apellidos a buscar.
     * @param empresa
     *            Empresa a la cual pertenecen los usuarios.
     * @return Lista con los usuarios asociados.
     */
    List<Usuario> listarPorNombresYApellidos(final Integer empresaId, final String nombresYApellidos);

    /**
     * Lista usuarios por su área y empresa.
     * 
     * @param area
     *            Área sobre la cual se realiza la busqueda.
     * @param empresa
     *            Empresa a la cual pertenece el área.
     * @return Lista con los usuarios asociados.
     */
    List<Usuario> listarPorArea(final Integer areaId);

    /**
     * Retorna un lista de Usuario por su número, nombre, rol y/o area.
     * 
     * @param empresaId
     *            Identificador de la empresa a la cual pertenecen los usuarios.
     * @param valor
     *            valor para realizar la busqueda.
     * @return Lista con los resultados asociados.
     */
    List<Usuario> listarPorNumeroNombreRolArea(final Integer empresaId, final String valor);

    /**
     * Obtiene el usuario por su id.
     * 
     * @param id
     *            Identificación del usuario.
     * @return Usuario asociado.
     */
    Usuario buscarPorCodigo(final String codigo);

    /**
     * Lista usuarios por su nombre de usuario.
     * 
     * @param nombreUsuario
     *            Nombre de usuario sobre el cual se realiza la busqueda.
     * @return Lista con los usuarios asociados.
     */
    List<Usuario> listarPorNombreUsuario(final String nombreUsuario);
    
    /**
     * Busca un usuario por su empresa y código de usuario.
     * @param empresaId Identificador de la empresa.
     * @param codigoUsuario Código del usuario.
     * @return usuario asociado.
     */
    Usuario buscarPorEmpresaYCodigoUsuario(final Integer empresaId, final String codigoUsuario);
    
    /**
     * Busca un usuario por su empresa, nombre de usuario y correo electrónico.
     * @param empresaId Identificador de la empresa.
     * @param nombreUsuario Nombre del usuario.
     * @param emailUsuario Correo electrónico del usuario.
     * @return Usuario asociado.
     */
    Usuario buscarPorEmpresaYNombreUsuarioYCorreo(final Integer empresaId, final String nombreUsuario, final String emailUsuario);
    
    /**
     * Busca un usuario por su empresa y nombre de usuario.
     * @param empresaId Identificador de la empresa.
     * @param nombreUsuario Nombre del usuario.
     * @return Usuario asociado.
     */
    Usuario buscarPorEmpresaYNombreUsuario(final Integer empresaId, final String nombreUsuario);

}
