package co.com.grupoasd.documental.cliente.menu;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import javax.imageio.ImageIO;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.parametrizacion.model.ParametroAplicacion;
import co.com.grupoasd.documental.presentacion.comun.PropertiesMessagesUtil;
import co.com.grupoasd.documental.presentacion.controller.util.GenericController;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;
import co.com.grupoasd.documental.presentacion.service.parametrizacion.ParametroAplicacionServiceFactory;
import co.com.grupoasd.documental.presentacion.service.parametrizacion.iface.ParametroAplicacionService;

public class IndexController extends GenericController<Window> {
    
    /**
     * 
     */
    private static final long serialVersionUID = 9104191386001734611L;
    Window IndexWindow;
    Menubar menubar;
    Div mainContainer;
    Label lblUsuActual;
    Image imageFondo;
    public static final String WCONTAINER_ID = "IndexWindow";
    public static final String SET_CONTENT_EVENT = "onSetContent";
    public static final String FRAGMENT = "fragment";
    private static final String ID_LOGO_EMPRESA = "logoEmpresa";
    private ParametroAplicacion logoEmpresa;
    ParametroAplicacionService parametroAplicacionService = ParametroAplicacionServiceFactory.getParametroAplicacionService();;
    
    @Override
    public void onCreate(Event event) {
        Clients.confirmClose(PropertiesMessagesUtil.instance().get("confirmar_salir_index"));
        
        self.setTitle(PropertiesMessagesUtil.instance().get("ventana_menu"));
        
        lblUsuActual.setValue(SessionUtil.getCurrentSession().getEmpresa() + " - " + SessionUtil.getCurrentSession().getNombreCompletoUsuario());
        
        Menu mnuArchivo = new Menu(PropertiesMessagesUtil.instance().get("cuenta_menu"));
        mnuArchivo.setParent(menubar);
        mnuArchivo.setId(BarraMenu.ARCHIVO);
        mnuArchivo.setVisible(false);
        
        Menu mnuAdministracion = new Menu(PropertiesMessagesUtil.instance().get("administracion_menu"));
        mnuAdministracion.setParent(menubar);
        mnuAdministracion.setId(BarraMenu.ADMINISTRACION);
        mnuAdministracion.setVisible(false);
        
        Menu mnuCorrespondencia = new Menu(PropertiesMessagesUtil.instance().get("correspondencia_menu"));
        mnuCorrespondencia.setParent(menubar);
        mnuCorrespondencia.setId(BarraMenu.CORRESPONDENCIA);
        mnuCorrespondencia.setVisible(false);
                
        Menu mnuAyuda = new Menu(PropertiesMessagesUtil.instance().get("ayuda_menu"));
        mnuAyuda.setParent(menubar);
        mnuAyuda.setId(BarraMenu.AYUDA);
        mnuAyuda.setVisible(false);

        MenuCuenta.construirMenu();
        MenuAdministracion.construirMenu();
        MenuCorrespondencia.construirMenu();
        MenuAyuda.construirMenu();
        System.err.println("SessionUtil.getCurrentSession().getEmpresaId() " + SessionUtil.getCurrentSession().getEmpresaId());
        logoEmpresa = parametroAplicacionService.obtenerPorIdYEmpresa(ID_LOGO_EMPRESA, SessionUtil.getCurrentSession().getEmpresaId());
        if (logoEmpresa != null){
            byte[] logoBase64 = Base64.getDecoder().decode(logoEmpresa.getValor());
            try {
                imageFondo.setContent(ImageIO.read(new ByteArrayInputStream(logoBase64)));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public EventListener<Event> setContentEvent(final String fragment) {
        return new EventListener<Event>() {
            
            @Override
            public void onEvent(Event event) throws Exception {
                args.put(FRAGMENT, fragment);
                Events.sendEvent(IndexController.SET_CONTENT_EVENT, self, args);
            }
        };
    }
    
    @SuppressWarnings("unchecked")
    public void onSetContent(Event event) throws Exception {
        Map<String, Object> _args = (Map<String, Object>) event.getData();
        this.args.putAll(_args);
        setContent(String.valueOf(_args.get(FRAGMENT)));
    }
    
    private void setContent(String fragment) throws Exception {
        UiUtils.closeWindowChilds(mainContainer);
        Window win = UiUtils.setPopupWindow(fragment, mainContainer, args, false, true);
        win.setId(win.getUuid());
        Clients.evalJavaScript("jq('$" + win.getId() + "').hide().fadeIn('fast')");
    }
    
    @Override
    public void onCancel(Event event) {
        // DO NOTHING
    }
}
