package co.com.grupoasd.documental.cliente.menu;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

import co.com.grupoasd.documental.cliente.autorizacion.AccionApp;
import co.com.grupoasd.documental.presentacion.comun.PropertiesMessagesUtil;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;

public class MenuCorrespondencia extends ControladorMenu {

    private MenuCorrespondencia() {
    }

    public static void construirMenu() {
        SessionUtil.getCurrentSession();
        Menu correspondencia = UiUtils.getComponent(BarraMenu.CORRESPONDENCIA, Menu.class);
        boolean visible = false;

        Menupopup popup = new Menupopup();
        popup.setParent(correspondencia);
        if (SessionUtil.accionPermitida(AccionApp.RADICAR_CORRESPONDENCIA)) {
            Menuitem mitDevol = new Menuitem(PropertiesMessagesUtil.instance().get("radicar_correspondencia"));
            mitDevol.setParent(popup);
            Map<String, Object> arg = new HashMap<String, Object>();
            mitDevol.addEventListener(Events.ON_OK, setContentEvent("/correspondencia/radicar/radicar_correspondencia.zul", arg));
            mitDevol.addEventListener(Events.ON_CLICK, setContentEvent("/correspondencia/radicar/radicar_correspondencia.zul", arg));
            visible = true;
        }
        if (SessionUtil.accionPermitida(AccionApp.CONSULTAR_RADICADO)) {
            Menuitem mitDevol = new Menuitem(PropertiesMessagesUtil.instance().get("consultar_radicado_correspondencia"));
            mitDevol.setParent(popup);
            Map<String, Object> arg = new HashMap<String, Object>();
            mitDevol.addEventListener(Events.ON_OK, setContentEvent("/correspondencia/actualizar/consultar_radicado.zul", arg));
            mitDevol.addEventListener(Events.ON_CLICK, setContentEvent("/correspondencia/actualizar/consultar_radicado.zul", arg));
            visible = true;
        }
        correspondencia.setVisible(visible);
    }
}
