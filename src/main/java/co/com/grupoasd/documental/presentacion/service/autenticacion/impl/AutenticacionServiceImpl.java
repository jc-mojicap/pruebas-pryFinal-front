/*
* Archivo: AutenticacionServiceImpl.java
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
package co.com.grupoasd.documental.presentacion.service.autenticacion.impl;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.management.openmbean.InvalidKeyException;

import org.apache.http.impl.client.HttpClientBuilder;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmsResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;

import co.com.grupoasd.documental.cliente.catalogo.model.Empresa;
import co.com.grupoasd.documental.cliente.catalogo.model.ParametrosKeycloack;
import co.com.grupoasd.documental.cliente.comun.PropertiesUtil;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.service.autenticacion.iface.AutenticacionService;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public final class AutenticacionServiceImpl implements AutenticacionService {

    @Override
    public Claims autenticarKeycloak(Empresa empresa, String usuario, String clave) throws NoSuchAlgorithmException, InvalidKeySpecException {
        ParametrosKeycloack params = CatalogoServiceFactory.getParametrosKeycloackService().buscarPorId(empresa.getEmpresaId());
        Map<String, Object> credentials = new HashMap<String, Object>();
        credentials.put("secret", params.getSecret());
        Configuration config = new Configuration(PropertiesUtil.instance().get("keycloak.url"), params.getCodigoEmpresa(), params.getCliente(),
                credentials,
                HttpClientBuilder.create().build());
        AuthzClient authzClient = AuthzClient.create(config);

        KeycloakBuilder builder = KeycloakBuilder.builder()
                .serverUrl(PropertiesUtil.instance().get("keycloak.url"))
                .realm(params.getCodigoEmpresa())
                .username(params.getUsuario())
                .password(params.getClave())
                .clientId(params.getCliente())
                .clientSecret(params.getSecret())
                .resteasyClient(new ResteasyClientBuilder().build());
        Keycloak keycloak = builder.build();
        
        byte[] publicBytes = Base64.getDecoder()
                .decode(keycloak.realm(params.getCodigoEmpresa()).keys().getKeyMetadata().getKeys().get(0).getPublicKey());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        RealmsResource realmsResource = keycloak.realms();
        UserResource user = realmsResource.realm(params.getCodigoEmpresa()).users()
                .get(realmsResource.realm(params.getCodigoEmpresa()).users().search(usuario, null, null, null, -1, -1).get(0).getId());
        if(!user.toRepresentation().isEnabled()){
            throw new InvalidKeyException("Usuario bloqueado");
        }
        
        AccessTokenResponse auth = authzClient.obtainAccessToken(usuario, clave);
        Claims claims = Jwts.parser()
                .setSigningKey(pubKey)
                .parseClaimsJws(auth.getIdToken()).getBody();

        // Limpiar intentos de acceso fallidos
        keycloak.realm(params.getCodigoEmpresa()).attackDetection().clearBruteForceForUser(user.toRepresentation().getId());
        
        return claims;
    }
    
    @Override
    public boolean enviarCorreoRecuperacion(Integer empresaId, String usuario) {
        try {
            ParametrosKeycloack params = CatalogoServiceFactory.getParametrosKeycloackService().buscarPorId(empresaId);
            KeycloakBuilder builder = KeycloakBuilder.builder()
                    .serverUrl(PropertiesUtil.instance().get("keycloak.url"))
                    .realm(params.getCodigoEmpresa())
                    .username(params.getUsuario())
                    .password(params.getClave())
                    .clientId(params.getCliente())
                    .clientSecret(params.getSecret())
                    .resteasyClient(new ResteasyClientBuilder().build());
            Keycloak keycloak = builder.build();

            RealmsResource realmsResource = keycloak.realms();
            UserResource user = realmsResource.realm(params.getCodigoEmpresa()).users()
                    .get(realmsResource.realm(params.getCodigoEmpresa()).users().search(usuario, null, null, null, -1, -1).get(0).getId());
            List<String> actions = new LinkedList<>();
            actions.add("UPDATE_PASSWORD");
            user.executeActionsEmail(actions);
            
            // Limpiar intentos de acceso fallidos
            keycloak.realm(params.getCodigoEmpresa()).attackDetection().clearBruteForceForUser(user.toRepresentation().getId());
            
            return true;
        } catch (Exception e) {
            Logger.getLogger(AutenticacionService.class).log(Level.ERROR, e.getLocalizedMessage(), e);
            return false;
        }
    }

    @Override
    public boolean restaurarClave(SesionVo empresa, String usuario, String claveConfirmacion) {
        try {
            ParametrosKeycloack params = CatalogoServiceFactory.getParametrosKeycloackService().buscarPorId(empresa.getEmpresaId());
            Map<String, Object> credentials = new HashMap<String, Object>();
            credentials.put("secret", params.getSecret());

            KeycloakBuilder builder = KeycloakBuilder.builder()
                    .serverUrl(PropertiesUtil.instance().get("keycloak.url"))
                    .realm(params.getCodigoEmpresa())
                    .username(params.getUsuario())
                    .password(params.getClave())
                    .clientId(params.getCliente())
                    .clientSecret(params.getSecret())
                    .resteasyClient(new ResteasyClientBuilder().build());
            Keycloak keycloak = builder.build();

            RealmsResource realmsResource = keycloak.realms();
            UserResource user = realmsResource.realm(params.getCodigoEmpresa()).users()
                    .get(realmsResource.realm(params.getCodigoEmpresa()).users().search(usuario, null, null, null, -1, -1).get(0).getId());

            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType("password");
            credential.setTemporary(false);
            credential.setValue(claveConfirmacion);
            user.resetPassword(credential);
            return true;
        } catch (Exception e) {
            Logger.getLogger(AutenticacionService.class).log(Level.ERROR, e.getLocalizedMessage(), e);
            return false;
        }
    }

    @Override
    public boolean cambiarClave(SesionVo empresa, String claveAnterior, String claveNueva) {
        try {
            String usuario = empresa.getUsuario();
            ParametrosKeycloack params = CatalogoServiceFactory.getParametrosKeycloackService().buscarPorId(empresa.getEmpresaId());
            Map<String, Object> credentials = new HashMap<String, Object>();
            credentials.put("secret", params.getSecret());
            Configuration config = new Configuration(PropertiesUtil.instance().get("keycloak.url"), params.getCodigoEmpresa(), params.getCliente(),
                    credentials,
                    HttpClientBuilder.create().build());

            AuthzClient authzClient = AuthzClient.create(config);
            AccessTokenResponse auth = authzClient.obtainAccessToken(usuario, claveAnterior);
            auth.getToken();

            KeycloakBuilder builder = KeycloakBuilder.builder()
                    .serverUrl(PropertiesUtil.instance().get("keycloak.url"))
                    .realm(params.getCodigoEmpresa())
                    .username(params.getUsuario())
                    .password(params.getClave())
                    .clientId(params.getCliente())
                    .clientSecret(params.getSecret())
                    .resteasyClient(new ResteasyClientBuilder().build());
            Keycloak keycloak = builder.build();

            RealmsResource realmsResource = keycloak.realms();
            UserResource user = realmsResource.realm(params.getCodigoEmpresa()).users()
                    .get(realmsResource.realm(params.getCodigoEmpresa()).users().search(usuario, null, null, null, -1, -1).get(0).getId());

            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType("password");
            credential.setTemporary(false);
            credential.setValue(claveNueva);
            user.resetPassword(credential);
            return true;
        } catch (Exception e) {
            Logger.getLogger(AutenticacionService.class).log(Level.ERROR, e.getLocalizedMessage(), e);
            return false;
        }
    }
}
