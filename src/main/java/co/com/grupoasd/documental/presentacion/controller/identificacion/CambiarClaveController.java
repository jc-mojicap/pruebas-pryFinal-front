/*
* Archivo: CambiarClaveController.java
* Fecha creacion = 09/03/2017
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
package co.com.grupoasd.documental.presentacion.controller.identificacion;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.service.autenticacion.AutenticacionServiceFactory;
import co.com.grupoasd.documental.presentacion.service.autenticacion.iface.AutenticacionService;

public final class CambiarClaveController {

    private AutenticacionService autenticacionService;

    @Wire
    private Textbox txtClaveActual;
    @Wire
    private Textbox txtClaveNueva;
    @Wire
    private Textbox txtConfirmacionClaveNueva;
    @Wire
    private Window windowCambiarClave;

    /**
     * Inicializacion del controller.
     */
    @Init
    public void init(@ContextParam(ContextType.SESSION) Session session) {
        this.autenticacionService = AutenticacionServiceFactory.getAutenticacionService();
    }

    /**
     * Metodo que se ejecuta despues de que ZK crea los componentes de la vista.
     * 
     * @param view
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
    }

    /**
     * Cambia la clave del usuario.
     */
    @Command
    public void cambiarClave() {

        if (validarCampos()) {
            String mensaje;
            String tipo = "error";
            if (txtClaveNueva.getValue().equals(txtConfirmacionClaveNueva.getValue())) {
                boolean resultado = autenticacionService.cambiarClave(SessionUtil.getCurrentSession(), txtClaveActual.getValue(),
                        txtClaveNueva.getValue());
                if (resultado) {
                    mensaje = Labels.getLabel("autenticacion.mensaje.passwordActualizado");
                    tipo = "info";
                    windowCambiarClave.detach();
                } else {
                    mensaje = Labels.getLabel("autenticacion.mensaje.passwordNoActualizado");
                }
            } else {
                mensaje = Labels.getLabel("autenticacion.mensaje.passwordNoCoincide");
            }
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        }
    }

    private boolean validarCampos() {
        try {
            if (txtClaveActual.getValue().trim().isEmpty()) {
                throw new WrongValueException(txtClaveActual, Labels.getLabel("commons.tooltip.campoRequerido"));
            }
            if (txtClaveNueva.getValue().trim().isEmpty()) {
                throw new WrongValueException(txtClaveNueva, Labels.getLabel("commons.tooltip.campoRequerido"));
            }
            if (txtConfirmacionClaveNueva.getValue().trim().isEmpty()) {
                throw new WrongValueException(txtConfirmacionClaveNueva, Labels.getLabel("commons.tooltip.campoRequerido"));
            }
            return true;
        } catch (WrongValueException e) {
            throw e;
        }
    }
}
