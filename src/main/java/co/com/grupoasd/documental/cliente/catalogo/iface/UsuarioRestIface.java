/*
* Archivo: UsuarioRestIface.java
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
package co.com.grupoasd.documental.cliente.catalogo.iface;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;

/**
 * Interface recurso Usuario.
 * 
 * @author JuanMojica
 *
 */
@Path("/catalogo")
public interface UsuarioRestIface {

    /**
     * Busca un usuario por su id.
     * 
     * @param id
     *            Identificador del usuario.
     * @return El usuario asociado.
     */
    @GET
    @Path("/usuarioid")
    @Produces({ MediaType.APPLICATION_JSON })
    public Usuario obtenerPorId(@QueryParam("id") Integer id);

    /**
     * Busca usuarios por su número de identificación.
     * 
     * @param numeroIdentificacion
     *            Número de identificación a buscar.
     * @return Lista de usuarios que corresponden con el número de
     *         identificación.
     */
    @GET
    @Path("/usuarios/id")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Usuario> listarPorNumeroIdentificacion(@QueryParam("id") String numeroIdentificacion);

    /**
     * Lista usuarios por sus nombres y apellidos.
     * @param empresa empresa a la cual pertenece el usuario.
     * @param nombresYApellidos Nombres y apellidos del usuario.
     * @return Lista de usuarios relacionados.
     */
    @GET
    @Path("/usuarios/nombres")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Usuario> listarPorNombresYApellidos(@QueryParam("empresa") Integer empresa,
            @QueryParam("nombresYApellidos") String nombresYApellidos);

    /**
     * Lista usuarios por el área a la que pertenecen.
     * @param areaId Identificador del área.
     * @return Lista de usuarios asociados.
     */
    @GET
    @Path("/usuarios/area")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Usuario> listarPorArea(@QueryParam("id") Integer areaId);

    /**
     * Lista usuarios por su identificación, nombre, rol y/o área.
     * @param empresa empresa a la cual pertenece el usuario.
     * @param valor String con los valores por los cual se desea buscar.
     * @return Lista de usuarios asociados.
     */
    @GET
    @Path("/usuarios/varios")
    @Produces({ MediaType.APPLICATION_JSON })
    List<Usuario> listarPorNumeroNombreRolArea(@QueryParam("empresaId") Integer empresa, @QueryParam("valor") String valor);

    /**
     * Busca un usuario por su codigo.
     * 
     * @param codigo
     *            Codigo del usuario.
     * @return El usuario asociado.
     */
    @GET
    @Path("/usuariocod")
    @Produces({ MediaType.APPLICATION_JSON })
    public Usuario listarPorCodigo(@QueryParam("codigo") String codigo);

    /**
     * Busca usuarios por su nombre de usuario (independiente de la empresa a la
     * que pertenecen)
     * 
     * @param username
     *            nombre de usuario para realizar la busqueda.
     * @return Lista de usuarios asociados.
     */
    @GET
    @Path("/usuarios/nombreusuario")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Usuario> listarPorNombreUsuario(@QueryParam("username") String username);
    
    /**
     * Busca un usuario por su empresa y su código de usuario. 
     * @param empresaId Identificador de la empreas sobre la cual se realiza la búsqueda.
     * @param codigoUsuario Código del usuario.
     * @return usuario asociado.
     */
    @GET
    @Path("/usuario/empresaAndCodigo")
    @Produces({ MediaType.APPLICATION_JSON })
    public Usuario buscarPorEmpresaYCodigoUsuario(@QueryParam("empresaId") Integer empresaId, @QueryParam("codigoUsuario") String codigoUsuario);

    /**
     * Busca un usuario por su empresa, nombre de usuario y correo electrónico.
     * @param empresaId Identificador de la empresa sobre la cual se realiza la búsqueda.
     * @param nombreUsuario Nombre de usuario a buscar.
     * @param emailUsuario Email del usuario que se busca.
     * @return Usuario asociado.
     */
    @GET
    @Path("/usuario/empresaAndUsuarioAndEmail")
    @Produces({ MediaType.APPLICATION_JSON })
    public Usuario buscarPorEmpresaYNombreUsuarioYCorreo(@QueryParam("empresaId") Integer empresaId, @QueryParam("nombreUsuario") String nombreUsuario, @QueryParam("email") String emailUsuario);

    /**
     * Busca un usuario por su empresa y nombre de usuario.
     * @param empresaId Identificador de la empresa sobre la cual se realiza la búsqueda.
     * @param nombreUsuario Nombre de usuario a buscar.
     * @return Usuario asociado.
     */
    @GET
    @Path("/usuario/empresaAndUsuario")
    @Produces({ MediaType.APPLICATION_JSON })
    public Usuario buscarPorEmpresaYNombreUsuario(@QueryParam("empresaId") Integer empresaId, @QueryParam("nombreUsuario") String nombreUsuario);
}
