/*
* Archivo: AutenticacionService.java
* Fecha creacion = 06/03/2017
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
package co.com.grupoasd.documental.presentacion.service.autenticacion.iface;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import co.com.grupoasd.documental.cliente.catalogo.model.Empresa;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import io.jsonwebtoken.Claims;

public interface AutenticacionService {

    /**
     * Genera la autenticacion a traves de keycloak.
     * 
     * @param empresa
     *            Empresa sobre la que se intenta autenticar el usuario
     * @param user
     *            Usuario que se ve a autenticar
     * @param password
     *            Password del usuario que se va a autenticar
     * @return Claims con la informacion de la sesion
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    Claims autenticarKeycloak(Empresa empresa, String user, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;

    /**
     * Envia el correo para recuperación de la clave.
     * 
     * @param empresa
     *            Empresa sobre la que se intenta autenticar el usuario
     * @param usuario
     *            El usuario cuya clave se va a recuperar
     * @return <code>true</code> si el envio del correo es exitoso,
     *         <code>false</code> en caso contrario
     */
    boolean enviarCorreoRecuperacion(Integer empresaId, String usuario);

    /**
     * Restaura la clave de acceso.
     * 
     * @param empresa
     *            Empresa sobre la que se intenta autenticar el usuario
     * @param claveConfirmacion
     *            Clave de confirmacion
     * @return <code>true</code> si el cambio es exitoso, <code>false</code> en
     *         caso contrario
     */
    boolean restaurarClave(SesionVo empresa, String usuario, String claveConfirmacion);

    /**
     * Modifica la clave del usuario.s
     * 
     * @param empresa
     *            Empresa sobre la que se intenta autenticar el usuario
     * @param claveAnterior
     *            Clave anterior del usuario
     * @param claveNueva
     *            Clave nueva del usuario
     * @return <code>true</code> si el cambio es exitoso, <code>false</code> en
     *         caso contrario
     */
    boolean cambiarClave(SesionVo empresa, String claveAnterior, String claveNueva);

}
