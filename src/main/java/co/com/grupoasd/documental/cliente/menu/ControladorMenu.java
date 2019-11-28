/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.documental.cliente.menu;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;

/**
 *
 * @author jmaldonado
 */
public abstract class ControladorMenu {

    protected static EventListener<Event> setContentEvent(final String fragment) {
        return setContentEvent(fragment, null);
    }
    
    protected static EventListener<Event> setContentEvent(final String fragment, final Map<String, Object> arg) {
        return new EventListener<Event>() {
            
            @Override
            public void onEvent(Event event) throws Exception {
                try {
                    Map<String, Object> args = arg;
                    if (args == null) {
                        args = new HashMap<String, Object>();
                    }
                    args.put(IndexController.FRAGMENT, fragment);
                    Executions.getCurrent().pushArg(args);
                    Events.sendEvent(IndexController.SET_CONTENT_EVENT, UiUtils.getComponent(IndexController.WCONTAINER_ID, Window.class), args);
                } finally {
                    Executions.getCurrent().popArg();
                }
            }
        };
    }
}
