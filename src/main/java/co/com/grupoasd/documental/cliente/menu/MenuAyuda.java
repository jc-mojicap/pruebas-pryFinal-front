/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.documental.cliente.menu;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.presentacion.comun.PropertiesMessagesUtil;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;

/**
 *
 * @author jmaldonado
 */
public class MenuAyuda extends ControladorMenu {
    
    public static void construirMenu() {
        SessionUtil.getCurrentSession();
        Menu prestamos = UiUtils.getComponent(BarraMenu.AYUDA, Menu.class);
        boolean visible = false;
        
        Menupopup popup = new Menupopup();
        popup.setParent(prestamos);
        
        Menuitem mitDevol = new Menuitem(PropertiesMessagesUtil.instance().get("manual_usuario"), "/resources/icons/manual.png");
        mitDevol.setParent(popup);
        mitDevol.addEventListener(Events.ON_OK, mostrarMenuUsuario());
        mitDevol.addEventListener(Events.ON_CLICK, mostrarMenuUsuario());
        visible = true;
        
        prestamos.setVisible(visible);
    }
    
    public static EventListener<Event> mostrarMenuUsuario() {
        return new EventListener<Event>() {
            
            Window win;
            
            @Override
            public void onEvent(Event t) throws Exception {
                if (win != null) {
                    win.detach();
                }
                
                win = new Window(PropertiesMessagesUtil.instance().get("manual_usuario"), "normal", true);
                win.setParent(UiUtils.getComponent(IndexController.WCONTAINER_ID, Window.class));
                win.setPosition("center");
                win.setHeight("85%");
                win.setWidth("75%");
                win.setMode(Window.Mode.OVERLAPPED);
                win.setSizable(true);
                
                Iframe frame = new Iframe("/ayuda/Manual de Usuario.pdf");
                frame.setParent(win);
                frame.setHflex("1");
                frame.setVflex("1");
            }
        };
    }
}
