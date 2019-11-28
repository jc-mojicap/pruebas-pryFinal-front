/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.documental.cliente.menu;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

import co.com.grupoasd.documental.cliente.autorizacion.AccionApp;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;

/**
 *
 * @author jmaldonado
 */
public class MenuAdministracion extends ControladorMenu {
    
    private MenuAdministracion() {
    }
    
    public static void construirMenu() {
        SessionUtil.getCurrentSession();
        Menu documentos = UiUtils.getComponent(BarraMenu.ADMINISTRACION, Menu.class);
        boolean visible = false;
        Menupopup popup = new Menupopup();
        popup.setParent(documentos);
       
        if (SessionUtil.accionPermitida(AccionApp.ADMINISTRAR_TERCEROS)) {
            Menuitem mitDevol = new Menuitem(Labels.getLabel("menu_administrar_terceros")); 
            mitDevol.setParent(popup);
            Map<String, Object> arg = new HashMap<String, Object>();
            mitDevol.addEventListener(Events.ON_OK, setContentEvent("/administracion/terceros/administrar_terceros.zul", arg));
            mitDevol.addEventListener(Events.ON_CLICK, setContentEvent("/administracion/terceros/administrar_terceros.zul", arg));
            visible = true;
        }
        
        if (SessionUtil.accionPermitida(AccionApp.CONFIGURAR_PARAMETROS)) {
            Menuitem mitDevol = new Menuitem(Labels.getLabel("menu_configurar_parametros"));
            mitDevol.setParent(popup);
            Map<String, Object> arg = new HashMap<String, Object>();
            mitDevol.addEventListener(Events.ON_OK, setContentEvent("/administracion/sistema/configurar_parametros.zul", arg));
            mitDevol.addEventListener(Events.ON_CLICK, setContentEvent("/administracion/sistema/configurar_parametros.zul", arg));
            visible = true;
        }
        
        if (SessionUtil.accionPermitida(AccionApp.ADMINISTRAR_METADATOS)) {
            Menuitem mitDevol = new Menuitem(Labels.getLabel("menu_administrar_metadatos"));
            mitDevol.setParent(popup);
            Map<String, Object> arg = new HashMap<String, Object>();
            mitDevol.addEventListener(Events.ON_OK, setContentEvent("/administracion/sistema/administrar_metadatos.zul", arg));
            mitDevol.addEventListener(Events.ON_CLICK, setContentEvent("/administracion/sistema/administrar_metadatos.zul", arg));
            visible = true;
        }
        
        documentos.setVisible(visible);
    }
}
