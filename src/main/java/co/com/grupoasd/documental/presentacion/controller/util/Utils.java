/*
 * Archivo: Utils.java
 * Fecha creacion: 13/03/2017 10:04:34 a. m.
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

import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;
import org.zkoss.zul.impl.NumberInputElement;

import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.cliente.catalogo.model.Serie;
import co.com.grupoasd.documental.cliente.catalogo.model.Subserie;
import co.com.grupoasd.documental.cliente.catalogo.model.TipoDocumental;
import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorCanal;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTercero;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTerceroXRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTipoEmbalaje;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTransportadora;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorUsuarioXRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.EstadoRadicado;
import co.com.grupoasd.documental.presentacion.comun.vo.ConstantAttributes;
import co.com.grupoasd.documental.presentacion.comun.vo.TipoRadicado;

/**
 * Clase con utilidades generales.
 * 
 * @author cestrada
 *
 */
public class Utils {
	
	public final static Integer EMPRESA_POR_DEFECTO = 1;
	public final static Integer USUARIO_POR_DEFECTO = 1;
	public final static boolean ESTADO_TIPO_DOCUMENTAL_INACTIVO = false;
	public final static boolean ESTADO_TERCERO_INACTIVO = false;
	public final static boolean ESTADO_EMPRESA_INACTIVA = false;
	public final static boolean ESTADO_SERIE_INACTIVA = false;
	public final static boolean ESTADO_SUBSERIE_INACTIVA = false;
	public final static int ID_CANAL_CORREO_CERTIFICADO = 2;
	public final static int ID_TIPO_DATO_ENUMERADO = 2;
	public final static int ID_TIPO_DATO_ENUMERADO_BD = 3;
	public final static int ID_TIPO_DATO_EXPRESION_REGULAR = 4;
	
	/**
	 * Estados radicado
	 */
	public final static int ID_ESTADO_RADICADO_RECIBIDO = 1;
	public final static int ID_ESTADO_RADICADO_RADICADO = 2;
	public final static int ID_ESTADO_RADICADO_INFORMADO = 3;
	public final static int ID_ESTADO_RADICADO_ASIGNADO = 4;
	public final static int ID_ESTADO_RADICADO_GESTIONADO = 5;
	public final static int ID_ESTADO_RADICADO_ANULADO = 6;
		
	/**
     * Codigos de error server
     */
    public final static String CODIGO_ERROR_EXCEPTION = "001";
    public final static String CODIGO_ERROR_IO_EXCEPTION = "002";
    public final static String CODIGO_ERROR_MESSAGING_EXCEPTION = "003"; // MessagingException
    public final static String CODIGO_ERROR_NULL_EXCEPTION = "004";
    public final static String CODIGO_ERROR_ESTADO_EXCEPTION = "005";
    public final static String LLAVE_CODIGOS_ERROR_SERVER = "codeError";
    public final static String CODIGO_ERROR_FILE_NOT_FOUND_EXCEPTION = "006";
    public final static String CODIGO_ERROR_ILLEGAL_ARGUMENT_EXCEPTION = "007";
    
    /**
     * TRD
     */
    public final static int ESTADO_TRD_ACTIVA = 1;
    public final static int ESTADO_TRD_INACTIVA = 2;
    public final static int ESTADO_TRD_EN_EDICION = 1;
    public final static boolean ESTADO_TRD_VIGENTE = true;
    public final static boolean ESTADO_TRD_NO_VIGENTE = false;
	
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static <T> boolean listNoEmpty(Collection<T> lista) {
        return lista != null && lista.size() > 0;
    }

    public static String formatDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, new Locale("es"));
        return dateFormat.format(date);
    }

    public static Date formatDate(String date, String format) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, new Locale("es"));
        return dateFormat.parse(date);
    }

    @SuppressWarnings("unchecked")
    public static <T> void quitarElementosRepetidosLista(List<T> lst) {
        Set<Object> hashSet = new LinkedHashSet<Object>();
        hashSet.addAll(lst);
        lst.clear();
        lst.addAll((Collection<? extends T>) hashSet);
    }

    public static void clearSelectedValue(Combobox combo) {
        Constraint constraint = combo.getConstraint();
        combo.setConstraint((String) null);
        boolean selected = false;
        for (Comboitem item : combo.getItems()) {
            if (item.getValue() == null) {
                combo.setSelectedItem(item);
                selected = true;
                break;
            }
        }
        if (combo.getSelectedIndex() == -1) {
            combo.setText("");
            combo.getItems().clear();
        }
        if (!selected)
            combo.setSelectedIndex(-1);
        combo.setConstraint(constraint);
    }

    public static void clearSelectedValue(InputElement input) {
        Constraint constraint = input.getConstraint();
        input.setConstraint((String) null);
        input.setText(null);
        input.setConstraint(constraint);
    }

    /**
     * Llena el combobox con la informacion de areas
     * 
     * @param list
     *            Lista con los objetos area
     * @param combobox
     *            combobox a llenar con la lista
     */
    public static void llenarComboboxArea(List<Area> list, Combobox combobox) {
        combobox.getItems().clear();
        Comboitem comboitem;
        for (Area area : list) {
            comboitem = new Comboitem();
            comboitem.setValue(area);
            comboitem.setLabel(area.getNombre());
            combobox.appendChild(comboitem);
        }
    }

    /**
     * Llena el combobox con la informacion de las series
     * 
     * @param list
     *            Lista con los objetos serie
     * @param combobox
     *            combobox a llenar con la lista
     */
    public static void llenarComboboxSerie(List<Serie> list, Combobox combobox) {
        Comboitem comboitem;
        combobox.getItems().clear();
        if (list != null && list.size() > 0) {
            for (Serie serie : list) {
                comboitem = new Comboitem();
                comboitem.setValue(serie);
                comboitem.setLabel(serie.getNombre());
                combobox.appendChild(comboitem);
            }
            combobox.setSelectedIndex(0);
        }
    }

    /**
     * Llena el combobox con la informacion de sub series
     * 
     * @param list
     *            Lista con los objetos sub serie
     * @param combobox
     *            combobox a llenar con la lista
     */
    public static void llenarComboboxSubSerie(List<Subserie> list, Combobox combobox) {
        combobox.getItems().clear();
        Comboitem comboitem;
        if (list != null && list.size() > 0) {
            for (Subserie subSerie : list) {
                comboitem = new Comboitem();
                comboitem.setValue(subSerie);
                comboitem.setLabel(subSerie.getNombre());
                combobox.appendChild(comboitem);
            }
            combobox.setSelectedIndex(0);
        }
    }

    /**
     * Llena el combobox con la informacion de CorCanal
     * 
     * @param list
     *            Lista con los objetos sub serie
     * @param combobox
     *            combobox a llenar con la lista
     */
    public static void llenarComboboxCorCanal(List<CorCanal> list, Combobox combobox) {
        combobox.getItems().clear();
        Comboitem comboitem;
        for (CorCanal corCanal : list) {
            comboitem = new Comboitem();
            comboitem.setValue(corCanal);
            comboitem.setLabel(corCanal.getNombre());
            combobox.appendChild(comboitem);
        }
    }

    /**
     * Llena el combobox con la informacion estado_radicado
     * 
     * @param list
     *            Lista con los objetos sub serie
     * @param combobox
     *            combobox a llenar con la lista
     */
    public static void llenarComboboxEstadoRadicado(List<EstadoRadicado> list, Combobox combobox) {
        combobox.getItems().clear();
        Comboitem comboitem;
        for (EstadoRadicado estadoRadicado : list) {
            comboitem = new Comboitem();
            comboitem.setValue(estadoRadicado);
            comboitem.setLabel(estadoRadicado.getNombre());
            combobox.appendChild(comboitem);
        }
    }

    /**
     * Llena el combobox con la informacion Transportadora
     * 
     * @param list
     *            Lista con los objetos CorTransportadora
     * @param combobox
     *            combobox a llenar con la lista
     */
    public static void llenarComboboxCorTransportadora(List<CorTransportadora> list, Combobox combobox) {
        combobox.getItems().clear();
        Comboitem comboitem;
        for (CorTransportadora corTransportadora : list) {
            comboitem = new Comboitem();
            comboitem.setValue(corTransportadora);
            comboitem.setLabel(corTransportadora.getNombre());
            combobox.appendChild(comboitem);
        }
    }

    /**
     * Llena el combobox con la informacion Tipo Embalaje
     * 
     * @param list
     *            Lista con los objetos CorTipoEmbalaje
     * @param combobox
     *            combobox a llenar con la lista
     */
    public static void llenarComboboxTipoEmbalaje(List<CorTipoEmbalaje> list, Combobox combobox) {
        combobox.getItems().clear();
        Comboitem comboitem;
        for (CorTipoEmbalaje corTipoEmbalaje : list) {
            comboitem = new Comboitem();
            comboitem.setValue(corTipoEmbalaje);
            comboitem.setLabel(corTipoEmbalaje.getNombre());
            combobox.appendChild(comboitem);
        }
    }

    /**
     * Llena el combobox con la informacion de los terceros
     * 
     * @param list
     *            Lista con los objetos sub serie
     * @param combobox
     *            combobox a llenar con la lista
     * @param isPorId
     *            Define si sale id o nombre tercero
     */
    public static void llenarComboboxCorTercero(List<CorTercero> list, Combobox combobox, boolean isPorId) {
        combobox.getItems().clear();
        Comboitem comboitem;
        for (CorTercero corTercero : list) {
            comboitem = new Comboitem();
            comboitem.setValue(corTercero);
            if (isPorId)
                comboitem.setLabel(corTercero.getNumero());
            else
                comboitem.setLabel(corTercero.getNombre());
            combobox.appendChild(comboitem);
        }
    }

    /**
     * Llena el combobox con la informacion de los TipoDocumentales
     * 
     * @param list
     *            Lista con los objetos sub serie
     * @param combobox
     *            combobox a llenar con la lista
     */
    public static void llenarComboboxTipoDocumental(List<TipoDocumental> list, Combobox combobox) {
        combobox.getItems().clear();
        Comboitem comboitem;
        for (TipoDocumental tipoDocumental : list) {
            comboitem = new Comboitem();
            comboitem.setValue(tipoDocumental);
            comboitem.setLabel(tipoDocumental.getNombre());
            combobox.appendChild(comboitem);
        }
    }

    /**
     * Llena el combobox con la informacion de los usuarios
     * 
     * @param list
     *            Lista con los objetos sub serie
     * @param combobox
     *            combobox a llenar con la lista
     */
    public static void llenarComboboxUsuario(List<Usuario> list, Combobox combobox) {
        combobox.getItems().clear();
        Comboitem comboitem;
        for (Usuario usuario : list) {
            comboitem = new Comboitem();
            comboitem.setValue(usuario);
            comboitem.setLabel(usuario.getNombreCompleto());
            combobox.appendChild(comboitem);
        }
    }

    /**
     * Valida si el combobox presenta items
     * 
     * @param combobox
     *            combobox a validar
     * @return true si el combobox no esta vacio
     */
    public static boolean isValidoCombobox(Combobox combobox) {
        if (combobox != null && combobox.getItems() != null && combobox.getItems().size() > 0)
            return true;
        return false;
    }

    /**
     * Valida si el Listbox presenta items
     * 
     * @param listbox
     *            Listbox a validar
     * @return true si el listbox no esta vacio
     */
    public static boolean isValidoListbox(Listbox listbox) {
        if (listbox != null && listbox.getItems() != null && listbox.getItems().size() > 0)
            return true;
        return false;
    }

    /**
     * Valida campo tipo Combobox
     * 
     * @param combobox
     * @return
     */
    public static boolean isValidoComboboxItem(Combobox combobox) {
        boolean isValid = true;
        Constraint constraint = combobox.getConstraint();
        if (constraint != null) {
            combobox.setConstraint((String) null);
        }

        if (combobox == null || combobox.getSelectedItem() == null || combobox.getSelectedItem().getValue() == null) {
            isValid = false;
        }

        if (constraint != null) {
            combobox.setConstraint(constraint);
        }

        return isValid;
    }

    /**
     * Valida campo tipo Textbox
     * 
     * @param textbox
     * @return
     */
    public static boolean isValidoTextbox(Textbox textbox) {
        boolean isValido = true;
        Constraint constraint = textbox.getConstraint();
        if (constraint != null) {
            textbox.setConstraint((String) null);
        }

        if (textbox == null || textbox.getText() == null || textbox.getText().trim().length() == 0) {
            isValido = false;
        }

        if (constraint != null) {
            textbox.setConstraint(constraint);
        }
        return isValido;
    }

    /**
     * 
     * @param fragment
     * @param parent
     * @param arg
     * @param isMinimizable
     * @param isClosable
     * @return
     */
    public static Window setModalWindow(String fragment, Component parent, Map<String, Object> arg,
            boolean isMinimizable, boolean isClosable) {
        return setWindow(fragment, parent, "center", arg, isMinimizable, isClosable, Window.Mode.MODAL);
    }

    /**
     * 
     * @param fragment
     * @param parent
     * @param position
     * @param arg
     * @param isMinimizable
     * @param isClosable
     * @param mode
     * @return
     */
    public static Window setWindow(String fragment, Component parent, String position, Map<String, Object> arg,
            boolean isMinimizable, boolean isClosable, Window.Mode mode) {
        Window win = (Window) Executions.createComponents(fragment, parent, arg);
        win.setMinimizable(isMinimizable);
        win.setClosable(isClosable);
        win.setMode(mode);
        win.setPosition(position);
        return win;
    }

    /**
     * Muestra el archivo adjunto
     * 
     * @param adjunto
     *            Objeto CorAdjunto
     * @param parent
     *            Objeto padre
     */
    public static void mostrarArchivoAdjunto(String ruta, String tipoArchivo, byte[] bytesArchivo, Component parent)
            throws Exception {
        Media media = new AMedia(ruta, tipoArchivo, null, bytesArchivo);
        mostrarArchivoAdjunto(media, parent);
    }
    
    /**
     * Muestra archivo adjunto
     * @param ruta nombre-ruta del archivo
     * @param bytesArchivo bytes del archivo
     * @param parent componente padre
     * @throws Exception
     */
    public static void mostrarArchivoAdjunto(String ruta, byte[] bytesArchivo, Component parent)
            throws Exception {
        Media media = new AMedia(ruta, null, null, bytesArchivo);
        mostrarArchivoAdjunto(media, parent);
    }

    /**
     * mostrarArchivoAdjunto
     * @param archivo
     * @param parent
     * @throws Exception
     */
    public static void mostrarArchivoAdjunto(File archivo, Component parent) throws Exception{
        Media media = new AMedia(archivo, null, null);
        mostrarArchivoAdjunto(media, parent);
    }
    
    /**
     * mostrarArchivoAdjunto
     * @param media
     * @param parent
     * @throws Exception
     */
    public static void mostrarArchivoAdjunto(Media media, Component parent) throws Exception{
        if (media.getFormat() != null && media.getContentType() != null
                && (media.getContentType().contains("image") || media.getContentType().contains("pdf"))) {
            Window window = new Window("Visor adjuntos", "normal", true);
            window.setHeight("90%");
            window.setWidth("90%");
            window.setStyle("max-height:100%:max-width:100%;margin: auto;text-align: center;");
            window.setParent(parent);
            window.doModal();
            window.setSizable(true);

            Iframe iframe = new Iframe();
            iframe.setHflex("true");
            iframe.setVflex("true");
            iframe.setStyle("max-height:100%:max-width:100%;margin: auto;text-align: center;");
            iframe.setParent(window);
            iframe.setContent(media);
        } else {
            Sessions.getCurrent().setAttribute(ConstantAttributes.DOWNLOAD, media);
            Executions.getCurrent().sendRedirect(ConstantAttributes.DOWNLOAD_PAGE, "other");
        }
    }

    /**
     * Valida numero
     * @param input
     * @return
     */
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

    /**
     * strNoEmpty
     * @param value
     * @return
     */
    public static boolean strNoEmpty(String value) {
        return StringUtils.isNotBlank(value) && !value.equals("null");
    }
    
    /**
     * Llena combobox respuesta SI/NO
     * @param combobox
     */
    public static void llenarComboboxResp(Combobox combobox) {
        combobox.getItems().clear();
         
        Comboitem comboitem = new Comboitem();
        comboitem.setValue("SI");
        comboitem.setLabel("SI");
        combobox.appendChild(comboitem);
        
        comboitem = new Comboitem();
        comboitem.setValue("NO");
        comboitem.setLabel("NO");
        combobox.appendChild(comboitem);
    }
    
    /**
     * Llena combobox respuesta SI/NO
     * @param combobox
     */
    public static void llenarComboboxTrd(Combobox combobox) {
        combobox.getItems().clear();
         
        Comboitem comboitem = new Comboitem();
        comboitem.setValue("M");
        comboitem.setLabel("M");
        combobox.appendChild(comboitem);
        
        comboitem = new Comboitem();
        comboitem.setValue("A");
        comboitem.setLabel("A");
        combobox.appendChild(comboitem);
        
        combobox.setSelectedItem(comboitem);
    }

    @SuppressWarnings("unchecked")
    public static <T> boolean adicionarElementoSeleccioneLista(List<T> list, Object object) {
        try {
            if (list == null) {
                list = new ArrayList<T>();
            }
            list.add(0, (T) object);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T, E> void setMethodsIdValueForList(List<E> list, Class<?> clase, String methodId, Object index,
            String methodValue, Object value) throws Exception {
        if(list == null){
            throw new NullPointerException("La lista es nula");
        }
        int listIndex = list.size();
        T object = (T) clase.newInstance();
        Method method;
        method = object.getClass().getMethod(methodId, Integer.class);
        if (method != null) {
            method.invoke(object, index);
        }
        method = object.getClass().getMethod(methodValue, String.class);
        if (method != null) {
            method.invoke(object, value);
        }
        list.add(listIndex, (E) object);
    }
    
    /**
     * Llena el combobox con la informacion tipo radicado
     * 
     * @param list
     *            Lista con los objetos TipoRadicado
     * @param combobox
     *            combobox a llenar con la lista
     */
    public static void llenarComboboxTipoRadicado(Combobox combobox) {
        combobox.getItems().clear();
        Comboitem comboitem = new Comboitem();
        comboitem.setValue(null);
        comboitem.setLabel("Seleccione");
        combobox.appendChild(comboitem);
        for (TipoRadicado tipoRadicado : TipoRadicado.values()) {
            comboitem = new Comboitem();
            comboitem.setValue(tipoRadicado);
            comboitem.setLabel(tipoRadicado.getNombre());
            combobox.appendChild(comboitem);
        }
    }
    
    public static List<EstadoRadicado> adicionarSeleccioneEstadosRadicado(List<EstadoRadicado> lista) throws Exception {
        if (lista == null) {
            lista = new ArrayList<>();
        }
        Set<EstadoRadicado> hashSet = new LinkedHashSet<EstadoRadicado>();
        hashSet.addAll(lista);
        lista.clear();
        Utils.setMethodsIdValueForList(lista, EstadoRadicado.class, "setEstadoRadId", -1, "setNombre", "Seleccione");
        lista.addAll((Collection<? extends EstadoRadicado>) hashSet);
        return lista;
    }
    
    public static List<CorCanal> adicionarSeleccioneCanales(List<CorCanal> lista) throws Exception {
        if (lista == null) {
            lista = new ArrayList<>();
        }
        Set<CorCanal> hashSet = new LinkedHashSet<CorCanal>();
        hashSet.addAll(lista);
        lista.clear();
        Utils.setMethodsIdValueForList(lista, CorCanal.class, "setCanalId", -1, "setNombre", "Seleccione");
        lista.addAll((Collection<? extends CorCanal>) hashSet);
        return lista;
    }
    
    public static List<CorUsuarioXRadicado> adicionarSeleccioneUsuariosRadicado(List<CorUsuarioXRadicado> lista) throws Exception {
        if (lista == null) {
            lista = new ArrayList<>();
        }
        Set<CorUsuarioXRadicado> hashSet = new LinkedHashSet<CorUsuarioXRadicado>();
        hashSet.addAll(lista);
        lista.clear();
        Utils.setMethodsIdValueForList(lista, CorUsuarioXRadicado.class, "setUsuarioId", -1, "setNombreUsuario", "Seleccione");
        lista.addAll((Collection<? extends CorUsuarioXRadicado>) hashSet);
        return lista;
    }
    
    public static List<CorTerceroXRadicado> adicionarSeleccioneTercerosRadicado(List<CorTerceroXRadicado> lista) throws Exception {
        if (lista == null) {
            lista = new ArrayList<>();
        }
        Set<CorTerceroXRadicado> hashSet = new LinkedHashSet<CorTerceroXRadicado>();
        hashSet.addAll(lista);
        lista.clear();
        Utils.setMethodsIdValueForList(lista, CorTerceroXRadicado.class, "setTerceroId", -1, "setNombreTercero", "Seleccione");
        lista.addAll((Collection<? extends CorTerceroXRadicado>) hashSet);
        return lista;
    }
    
    public static List<Area> adicionarSeleccioneAreas(List<Area> lista) throws Exception {
        if (lista == null) {
            lista = new ArrayList<>();
        }
        Set<Area> hashSet = new LinkedHashSet<Area>();
        hashSet.addAll(lista);
        lista.clear();
        Utils.setMethodsIdValueForList(lista, Area.class, "setAreaId", -1, "setNombre", "Seleccione");
        lista.addAll((Collection<? extends Area>) hashSet);
        return lista;
    }
    
    public static List<Serie> adicionarSeleccioneSeries(List<Serie> lista) throws Exception {
        if (lista == null) {
            lista = new ArrayList<>();
        }
        Set<Serie> hashSet = new LinkedHashSet<Serie>();
        hashSet.addAll(lista);
        lista.clear();
        Utils.setMethodsIdValueForList(lista, Serie.class, "setSerieId", -1, "setNombre", "Seleccione");
        lista.addAll((Collection<? extends Serie>) hashSet);
        return lista;
    }
    
    public static List<Subserie> adicionarSeleccioneSubseries(List<Subserie> lista) throws Exception {
        if (lista == null) {
            lista = new ArrayList<>();
        }
        Set<Subserie> hashSet = new LinkedHashSet<Subserie>();
        hashSet.addAll(lista);
        lista.clear();
        Utils.setMethodsIdValueForList(lista, Subserie.class, "setSubserieId", -1, "setNombre", "Seleccione");
        lista.addAll((Collection<? extends Subserie>) hashSet);
        return lista;
    }
    
    public static List<TipoDocumental> adicionarSeleccioneTiposDocumentales(List<TipoDocumental> lista) throws Exception {
        if (lista == null) {
            lista = new ArrayList<>();
        }
        Set<TipoDocumental> hashSet = new LinkedHashSet<TipoDocumental>();
        hashSet.addAll(lista);
        lista.clear();
        Utils.setMethodsIdValueForList(lista, TipoDocumental.class, "setTipoDocumentalId", -1, "setNombre", "Seleccione");
        lista.addAll((Collection<? extends TipoDocumental>) hashSet);
        return lista;
    }
 
}
