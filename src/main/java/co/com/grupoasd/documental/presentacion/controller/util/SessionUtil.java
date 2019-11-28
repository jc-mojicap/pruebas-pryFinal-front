/*
 * Archivo: SessionUtil.java
 * Fecha creacion: 28/02/2017
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
package co.com.grupoasd.documental.presentacion.controller.util;

import java.util.List;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import co.com.grupoasd.documental.cliente.autorizacion.AccionApp;
import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.service.catalogo.dto.AccionDto;

/**
 * Clase utilitaria para obtener variables de la session.
 * 
 * @author Juan Carlos Castellanos
 */
public final class SessionUtil {

    /**
     * Constructor privado.
     */
    private SessionUtil() {

    }

    /**
     * Permite obtener el token desde la session.
     * 
     * @param session
     *            Session
     * @return Token
     */
    public static Token getAuthToken(final Session session) {
        return (Token) Sessions.getCurrent().getAttribute("token");
    }

    public static SesionVo getCurrentSession() {
        Session session = Sessions.getCurrent();
        SesionVo sesionVo = (SesionVo) session.getAttribute("usrAuth");
        return sesionVo;
    }

    public static boolean accionPermitida(AccionApp accionId) {
        return accionPermitida(accionId.getId());
    }
        public static boolean accionPermitida(int accionId) {
        if (getCurrentSession().isAdministrador())
            return true;

        List<AccionDto> acciones = getCurrentSession().getAcciones();
        if (acciones != null && !acciones.isEmpty()) {
            for (AccionDto accionDto : acciones) {
                if (accionDto.getAccionId() == accionId) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void iniciarSesion(SesionVo sesionVo, String token) {
        Sessions.getCurrent(true).setAttribute("usrAuth", sesionVo);
        Sessions.getCurrent(true).setAttribute("token", new Token(token));
    }

}
