/*
 * Archivo: GenericController.java
 * Fecha creacion: 13/03/2017 9:12:40 a. m.
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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;

import co.com.grupoasd.documental.presentacion.comun.PropertiesMessagesUtil;
import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.comun.vo.ConstantAttributes;

/**
 * @author jmaldonado
 *
 */
public abstract class GenericController<T extends Component> extends GenericForwardComposer<T> {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6811015881532948598L;
    protected String idParent;
    protected Map<String, Object> args = new HashMap<String, Object>();
    protected static final Logger LOG = Logger.getLogger(GenericController.class.getName());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    private static final NumberFormat moneyFormat = new DecimalFormat("$ ###,##0.00");
    private static final NumberFormat decimalFormat = new DecimalFormat("#,##0.00");
    private static final NumberFormat decimalLongFormat = new DecimalFormat("###,##0.0000");
    
    protected static String moneyFormat(Number number) {
        if (number != null) {
            return String.format("%20s", moneyFormat.format(number));
        } else {
            return String.format("%20s", moneyFormat.format(0));
        }
    }
    
    protected static Number moneyFormat(String number) {
        if (number != null) {
            try {
                return moneyFormat.parse(Utils.trim(number));
            } catch (ParseException ex) {
                LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                return 0;
            }
        } else {
            return 0;
        }
    }
    
    protected static String decimalFormat(Number number) {
        if (number != null) {
            return String.format("%20s", decimalFormat.format(number));
        } else {
            return String.format("%20s", decimalFormat.format(0));
        }
    }
    
    protected static String decimalLongFormat(Number number) {
        if (number != null) {
            return String.format("%20s", decimalLongFormat.format(number));
        } else {
            return String.format("%20s", decimalLongFormat.format(0));
        }
    }
    
    protected static String dateFormat(Date date) {
        if (date != null) {
            return dateFormat.format(date);
        } else {
            return "";
        }
    }
    
    protected static String dateTimeFormat(Date date) {
        if (date != null) {
            return dateTimeFormat.format(date);
        } else {
            return "";
        }
    }
    
    protected static Date dateFormat(String date) throws ParseException {
        if (date != null) {
            return dateFormat.parse(date);
        } else {
            return null;
        }
    }
    
    protected static Date dateTimeFormat(String date) throws ParseException {
        if (date != null) {
            return dateTimeFormat.parse(date);
        } else {
            return null;
        }
    }
    
    protected String booleanToString(Boolean value) {
        if (value == null)
            return Labels.getLabel("commons.label.no");
        return value ? Labels.getLabel("commons.label.si") : Labels.getLabel("commons.label.no");
    }
    
    @Override
    public void doAfterCompose(T comp) throws Exception {
        super.doAfterCompose(comp);
        for (Object key : arg.keySet()) {
            args.put(String.valueOf(key), arg.get(key));
        }
        // AnnotateDataBinder binder = new AnnotateDataBinder(comp);
        // binder.loadAll();
        if (!Utils.strNoEmpty(self.getId())) {
            self.setId(self.getUuid());
        }
        idParent = self.getParent() != null ? self.getParent().getId() : null;
    }
    
    public abstract void onCreate(Event event);
    
    protected EventListener<KeyEvent> focusNextComponent() {
        return new EventListener<KeyEvent>() {
            
            @Override
            public void onEvent(KeyEvent event) throws Exception {
                Component comp = event.getReference();
                Component next = comp.getNextSibling();
                while (next != null) {
                    if (next instanceof InputElement) {
                        ((InputElement) next).setFocus(true);
                        ((InputElement) next).select();
                        break;
                    } else if (next instanceof Button) {
                        Events.sendEvent(next, event);
                        break;
                    } else {
                        next = next.getNextSibling();
                    }
                }
            }
        };
    }
    
//    protected Constraint noEmptyConstraint() {
//        return new Constraint() {
//            
//            @Override
//            public void validate(Component cmpnt, Object o) throws WrongValueException {
//                if (o == null || !Utils.strNoEmpty(String.valueOf(o))) {
//                    throw new WrongValueException(cmpnt, Utils.getMessage("no_empty"));
//                }
//            }
//        };
//    }
//    
//    public void mostrarVisorDocumentos(Integer idDoc, Integer idSerie) throws Exception {
//        args.put("idDoc", idDoc);
//        args.put("idSerie", idSerie);
//        
//        String idWindowDoc = "winDocVisor" + idDoc;
//        
//        Component comp = Utils.getComponent(idWindowDoc);
//        if (comp != null) {
//            if (comp instanceof Window) {
//                ((Window) comp).setTopmost();
//            }
//        } else {
//            Window winDoc = Utils.setModalWindow("/consultas/documento.zul", self, args, false, true);
//            winDoc.setId(idWindowDoc);
//        }
//    }
//    
    protected void mostrarBytesArchivo(InfoMedia infoMedia) {
        Media media = new AMedia(infoMedia.getNombreArchivo(), infoMedia.getTipoArchivo(), null, infoMedia.getBytesArchivo());
        if (!(media.getFormat() != null && media.getFormat().contains("tif"))
                && (media.getContentType() == null
                        || (!media.getContentType().contains("image")
                                && !media.getContentType().contains("pdf")))) {
            Sessions.getCurrent().setAttribute(ConstantAttributes.DOWNLOAD, media);
            Executions.getCurrent().sendRedirect(ConstantAttributes.DOWNLOAD_PAGE, "other");
        } else {
            Window window = new Window("Visor", "normal", true);
            window.setHeight("90%");
            window.setWidth("90%");
            window.setStyle("max-height:100%:max-width:100%;margin: auto;text-align: center;");
            window.setParent(self);
            window.doModal();
            window.setSizable(true);
            
            Iframe iframe = new Iframe();
            iframe.setHflex("true");
            iframe.setVflex("true");
            iframe.setStyle("max-height:100%:max-width:100%;margin: auto;text-align: center;");
            iframe.setParent(window);
            iframe.setContent(media);
        }
    }
    
    public void onCtrlKey(KeyEvent event) {
        // String ctrlKey = event.isCtrlKey() ? "Alt" : event.isCtrlKey() ?
        // "Ctrl" : event.isShiftKey() ? "Shift" : "";
        // LOG.info(String.format("Control %1s has captured key %1s %1s from
        // %1s.",
        // event.getReference(), ctrlKey, (char) event.getKeyCode(),
        // event.getTarget()));
    }
    
    public void onCancel(Event event) {
        // String ctrlKey = event.isCtrlKey() ? "Alt" : event.isCtrlKey() ?
        // "Ctrl" : event.isShiftKey() ? "Shift" : "";
        // LOG.info(String.format("Control %1s has captured key %1s %1s from
        // %1s.",
        // event.getReference(), ctrlKey, (char) event.getKeyCode(),
        // event.getTarget()));
        if (self instanceof Window) {
            Events.sendEvent(Events.ON_CLOSE, (Window) self, null);
        } else {
            self.detach();
        }
    }
    
    protected final void mostrarInformacion(String mensaje) {
        Clients.showNotification(mensaje, "info", self, "middle_center", 3000, true);
    }
    
    protected final void mostrarError(String mensaje) {
        Clients.showNotification(mensaje, "error", self, "middle_center", 3000, true);
    }
    
    protected final void mostrarAdvertencia(String mensaje) {
        Clients.showNotification(mensaje, "warning", self, "middle_center", 3000, true);
    }
    
    public void onClose(Event event) {
        UiUtils.closeWindowChilds(self);
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
    
}
