/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.documental.cliente.menu;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;

import co.com.grupoasd.documental.presentacion.comun.PropertiesMessagesUtil;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;

/**
 *
 * @author jmaldonado
 */
public class MenuCuenta extends ControladorMenu {
    
    private MenuCuenta() {
    }
    
    public static void construirMenu() {
        Menu mnuArchivo = UiUtils.getComponent(BarraMenu.ARCHIVO, Menu.class);
        Menupopup popup = new Menupopup();
        popup.setParent(mnuArchivo);
        
        boolean visible = false;
        
        if (true) {
            Menuitem mitCambiarContra = new Menuitem(PropertiesMessagesUtil.instance().get("cambiar_contra_menu"));
            mitCambiarContra.setParent(popup);
            mitCambiarContra.addEventListener(Events.ON_OK, setContentEvent("/identificacion/cambiar_clave.zul"));
            mitCambiarContra.addEventListener(Events.ON_CLICK, setContentEvent("/identificacion/cambiar_clave.zul"));
            visible = true;
        }
        
        if (visible) {
            popup.appendChild(new Menuseparator());
        }
        
        Menuitem mitSalir = new Menuitem(PropertiesMessagesUtil.instance().get("salir_menu"));
        mitSalir.setParent(popup);
        mitSalir.addEventListener(Events.ON_OK, logout());
        mitSalir.addEventListener(Events.ON_CLICK, logout());
        visible = true;
        
        mnuArchivo.setVisible(visible);
    }
    
    public static EventListener<Event> logout() {
        return new EventListener<Event>() {
            
            @Override
            public void onEvent(Event event) throws Exception {
                Clients.confirmClose(null);
                Executions.getCurrent().getSession().invalidate();
                Executions.sendRedirect("/index.zul");
            }
        };
    }
}
