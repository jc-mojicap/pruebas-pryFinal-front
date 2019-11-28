/*
 * Archivo: UiUtils.java
 * Fecha creacion: 14/03/2017
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

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;
import org.zkoss.zul.impl.NumberInputElement;

/**
 * Utilidades con componentes ZK 
 * 
 * @author cestrada
 *
 */
public class UiUtils {

    public static void closeWindowChilds(Component self) {
        List<Component> childs = self.getChildren();
        if (Utils.listNoEmpty(childs)) {
            for (int i = 0; i < childs.size(); i++) {
                Component child = childs.get(i);
                if (child instanceof Window && !child.equals(self)) {
                    ((Window) child).onClose();
                    --i;
                }
            }
        }
    }

    /**
     * setWindow
     * 
     * @param fragment
     * @param parent
     * @param position
     * @param arg
     * @param isMinimizable
     * @param isClosable
     * @param mode
     * @return Window
     * @throws Exception
     */
    public static Window setWindow(String fragment, Component parent, String position, Map<String, Object> arg, boolean isMinimizable,
            boolean isClosable, Window.Mode mode)
            throws Exception {
        Window win = (Window) Executions.createComponents(fragment, parent, arg);
        win.setMinimizable(isMinimizable);
        win.setClosable(isClosable);
        win.setMode(mode);
        win.setPosition(position);
        return win;
    }

    /**
     * Agrega una nueva ventana modal al DESKTOP
     *
     * @param fragment
     *            URL de la ventana
     * @param parent
     *            Componente padre, si tiene
     * @param arg
     *            Atributos que recibe la ventana
     * @param isMinimizable
     *            true para mostrar el control de minimizar
     * @param isClosable
     *            true para mostrar el control de cerrar
     * @throws Exception
     */
    public static Window setModalWindow(String fragment, Component parent, Map<String, Object> arg, boolean isMinimizable, boolean isClosable)
            throws Exception {
        return setWindow(fragment, parent, "center", arg, isMinimizable, isClosable, Window.Mode.MODAL);
    }

    /**
     * Agrega una nueva ventana modal al DESKTOP
     *
     * @param fragment
     *            URL de la ventana
     * @param parent
     *            Componente padre, si tiene
     * @param arg
     *            Atributos que recibe la ventana
     * @param isMinimizable
     *            true para mostrar el control de minimizar
     * @param isClosable
     *            true para mostrar el control de cerrar
     * @throws Exception
     */
    public static Window setPopupWindow(String fragment, Component parent, Map<String, Object> arg, boolean isMinimizable, boolean isClosable)
            throws Exception {
        return setWindow(fragment, parent, "center", arg, isMinimizable, isClosable, Window.Mode.OVERLAPPED);
    }

    /**
     * Muestra un mensaje parametrizado con cualquiera de las opciones indicadas
     *
     * @param mensaje
     * @param titulo
     * @param botones
     * @param imagen
     * @param listener
     * @return El botón presionado (Alguno de OK, CANCEL, YES, NO, ABORT, RETRY
     *         o IGNORE). Nota: si el manejo de eventos está deshabilitado,
     *         siempre devuelve OK.
     */
    public static int mostrarMensaje(String mensaje, String titulo,
            int botones, int boton_seleccionado, String imagen,
            EventListener<Event> listener) {
        return Messagebox.show(mensaje, titulo, botones, imagen,
                boton_seleccionado, listener);
    }

    /**
     * Muestra un mensaje de error al usuario con la opción OK
     *
     * @param mensaje
     * @return El botón presionado (OK). Nota: si el manejo de eventos está
     *         deshabilitado, siempre devuelve OK.
     */
    public static int mostrarError(String mensaje) {
        return mostrarMensaje(mensaje, "Error",
                Messagebox.OK, 0, Messagebox.ERROR, null);
    }
    
    public static int mostrarError(String mensaje,  EventListener<Event> listener) {
        return mostrarMensaje(mensaje, "Error",
                Messagebox.OK, 0, Messagebox.ERROR, listener);
    }

    /**
     * Muestra un mensaje de información al usuario con la opción OK
     *
     * @param mensaje
     * @return El botón presionado (OK). Nota: si el manejo de eventos está
     *         deshabilitado, siempre devuelve OK.
     */
    public static int mostrarInformacion(String mensaje) {
        return mostrarMensaje(mensaje, "Información", Messagebox.OK, 0,
                Messagebox.INFORMATION, null);
    }
    
    /**
     * Muestra un mensaje de alerta al usuario con las opciones YES y NO. La
     * opción por defecto es NO
     *
     * @param mensaje
     * @param listener
     *            Manejo del evento de seleccionar algún botón
     * @return El botón presionado (Alguno de YES o NO). Nota: si el manejo de
     *         eventos está deshabilitado, siempre devuelve OK.
     */
    public static int mostrarAlerta(String mensaje, EventListener<Event> listener) {
        return mostrarMensaje(mensaje, "Advertencia",
                Messagebox.YES | Messagebox.NO, Messagebox.NO,
                Messagebox.EXCLAMATION, listener);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Component> T getComponent(String componentID, Class<T> clazz) {
        try {
            Collection<Component> collection = Executions.getCurrent().getDesktop().getComponents();
            for (Component object : collection) {
                if (clazz.isAssignableFrom(object.getClass())) {
                    if (StringUtils.equals(object.getId(), componentID)) {
                        return (T) object;
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UiUtils.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }
    
    public static void seleccionarComboitemPorLabel(Combobox combobox, String label) throws Exception {
        if(label != null && !label.isEmpty() ){
            for(Comboitem comboitem : combobox.getItems()){
                if(label.equalsIgnoreCase(comboitem.getLabel())){
                    combobox.setSelectedItem(comboitem);
                    return;
                }
            }            
        }
        combobox.setSelectedItem(null);
    }
    
    public static boolean itemSelected(Combobox combobox) {
        return combobox.getSelectedItem() != null && combobox.getSelectedItem().getValue() != null;
    }
    
    public static void clearSelectedValue(InputElement input) {
        Constraint constraint = input.getConstraint();
        input.setConstraint((String) null);
        input.setText(null);
        input.setConstraint(constraint);
    }
    
    public static boolean validNumber(NumberInputElement input) {
        try {
            if (StringUtils.isNotBlank(input.getFormat())) {
                DecimalFormat df = new DecimalFormat(input.getFormat());
                df.parse(input.getText());
            } else {
                new BigDecimal(input.getText());
            }
            return true;
        } catch (Exception e) {
            // LOG.log(Level.SEVERE, e.getLocalizedMessage(), e);
            return false;
        }
    }
    
    @SafeVarargs
    public static <T> void setMethodBooleanAtComponents(boolean set, String setMethod, T... componentes){
        Method method;
        for(T component : componentes){
            try {
                method = ((Component)component).getClass().getMethod(setMethod, Boolean.TYPE);
                if(method != null){
                    method.invoke(((Component)component), set);
                }
            } catch (Exception e) {
            }  
        }
    }

}
