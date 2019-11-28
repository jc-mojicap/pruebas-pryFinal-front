/*
* Archivo: AdministrarMetadatosController.java
* Fecha creacion = 12/06/2017
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
package co.com.grupoasd.documental.presentacion.controller.administracion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.trd.model.TrdMetadato;
import co.com.grupoasd.documental.cliente.trd.model.TrdTipoDato;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.Utils;
import co.com.grupoasd.documental.presentacion.service.trd.TrdServiceFactory;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdMetadatoService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdTipoDatoService;

public class AdministrarMetadatosController {

    @Wire
    private Window windowAdminMetadatos;
    @Wire
    private Combobox comboTipoMetadato;
    @Wire
    private Combobox comboEstadoMetadato;
    @Wire
    private Textbox txtValoresMetadato;
    @Wire
    private Button btnAgregarValoresMetadato;
    @Wire
    private Button btnEliminarValoresMetadato;
    @Wire
    private Textbox txtTablaBdMetadato;
    @Wire
    private Textbox txtColumnaTextoMetadato;
    @Wire
    private Textbox txtColumnaValorMetadato;
    @Wire
    private Textbox txtExpresionRegularMetadato;
    @Wire
    private Listbox lstOpcionesEnumerado;
    @Wire
    private Textbox txtMetadatoNombre;
    @Wire
    private Intbox intLongitudMetadato;
    @Wire
    private Listbox lstMetadatos;
    
    //Servicios
    TrdTipoDatoService trdTipoDatoService;
    TrdMetadatoService trdMetadatoService;
    
    private List<TrdTipoDato> tipoDatos = new ArrayList<>();
    private List<TrdMetadato> metadatos = new ArrayList<>();
    private List<TrdMetadato> metadatosFiltrados = new ArrayList<>();
    private List<String> opcionesEnumerado = new ArrayList<>();
    
    private TrdMetadato metadatoSeleccionado;
    
    private TrdTipoDato tipoDatoSeleccionado;
    
    private Integer idEmpresa;
    
    private String opcionSeleccionada;
    
    private static final String SEPARADOR_VALORES = "|";
    
    /**
     * Inicialización del controller.
     */
    @Init
    public void init() {
        // Iniciar todos los servicios.
        this.trdTipoDatoService = TrdServiceFactory.getTrdTipoDatoService();
        this.trdMetadatoService = TrdServiceFactory.getTrdMetadatoService();
        
        this.idEmpresa = ((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getEmpresaId();
    }
    
    /**
     * Método que se ejecuta después de que ZK crea los componentes de la vista.
     * 
     * @param view
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        cargarDatosIniciales();
        
    }
    
    /**´
     * Método para cargar los datos iniciales de la ventana.
     */
    private void cargarDatosIniciales(){
        tipoDatos.addAll(trdTipoDatoService.listarTodos());
        cargarMetadatos();
    }
    
    /**
     * Carga los metadatos que ya existen en la base de datos.
     */
    private void cargarMetadatos(){
        metadatos = trdMetadatoService.listarTodos();
        metadatosFiltrados = metadatos;
        lstMetadatos.invalidate();
    }
    
    /**
     * Carga la información del metadato que se selecciona.
     */
    @NotifyChange({"opcionesEnumerado"})
    @Command
    public void cargarInformacionSeleccionado(){
        comboEstadoMetadato.setSelectedIndex(metadatoSeleccionado.isInactivo() ? 1 : 0);
        tipoDatoSeleccionado = new TrdTipoDato(metadatoSeleccionado.getTipoDatoId(), metadatoSeleccionado.getNombreTipoDato());
        comboTipoMetadato.setSelectedIndex(metadatoSeleccionado.getTipoDatoId()-1);
        txtExpresionRegularMetadato.setSclass("");
        txtExpresionRegularMetadato.setReadonly(true);
        if (metadatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_ENUMERADO){
            if (metadatoSeleccionado.getValoresEnumerado()!=null){
                opcionesEnumerado = cargarListaValores(metadatoSeleccionado.getValoresEnumerado());
            }
            habilitarEnumerado();
        } else {
            opcionesEnumerado = new ArrayList<>();
            deshabilitarEnumerado();
        }
        if (metadatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_ENUMERADO_BD){
            habilitarEnumeradoBd();
            txtTablaBdMetadato.setValue(metadatoSeleccionado.getEnumBdTabla());
            txtColumnaValorMetadato.setValue(metadatoSeleccionado.getEnumBdValor());
            txtColumnaTextoMetadato.setValue(metadatoSeleccionado.getEnumBdEtiqueta());
            
        } else {
            deshabilitarEnumeradoBd();
        }
        if (metadatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_EXPRESION_REGULAR){
            txtExpresionRegularMetadato.setReadonly(false);
            txtExpresionRegularMetadato.setSclass("obligatorio");
            deshabilitarEnumerado();
            deshabilitarEnumeradoBd();
            
        }
    }
    
    /**
     * Carga el listado de valores y lo convierte en una lista.
     * Se crea un arrayList y se llena para ser retornado en lugar
     * de usar la implementación de Arrays.asList ya que la lista retornada
     * por esta última no permite ser modificada.
     * @param valores String con todos los valores a poner en la lista.
     * @return Lista con cada valor como un elemento.
     */
    private List<String> cargarListaValores(String valores){
        List<String> lista = new ArrayList<>();
        /* 
         * El separador será usado en una expresión regular, así que si el separador elegido 
         * es un caracter especial (o metacaracter), será tomado su valor especial a menos que 
         * se use doble backslash para que sea tomado como un literal (ej: \\.) o sea encerrado 
         * dentro de la clase character (ej: [.])
         */
        for (String value : valores.split("\\"+SEPARADOR_VALORES)) {
            lista.add(value);
        }
        return lista;
    }
    
    /**
     * Permite filtrar la lista de metadatos con respecto al nombre.
     * @param valor Nombre o fragmento del nombre a buscar.
     */
    @Command
    @NotifyChange({"metadatosFiltrados"})
    public void filtrarMetadatos(@BindingParam("arg1") String valor){
        metadatosFiltrados = new ArrayList<>();
        if (valor.isEmpty()){
            metadatosFiltrados = metadatos;
        } else {
            for (TrdMetadato trdMetadato : metadatos) {
                if (trdMetadato.getNombre().toLowerCase().contains(valor.toLowerCase())){
                    metadatosFiltrados.add(trdMetadato);
                }
            }
        }
    }
    
    /**
     * Permite agregar un valor a la lista de opciones cuando el 
     * tipo de dato es <code>Enumerado</code>
     * @param valorNuevo
     */
    @NotifyChange({"opcionesEnumerado","opcionSeleccionada"})
    @Command
    public void agregarValorEnumerado(@BindingParam("arg1") String valorNuevo){
        lstOpcionesEnumerado.setSclass("");
        lstOpcionesEnumerado.invalidate();
        
        String rta = contieneValor(opcionesEnumerado, valorNuevo.trim());
        if (rta == null){
            if (!valorNuevo.trim().isEmpty()){
                opcionesEnumerado.add(new String(valorNuevo.trim()));
                opcionSeleccionada = valorNuevo.trim();
            }
        } else {
            opcionSeleccionada = rta;
        }
    }
    
    /**
     * Permite verificar si en una lista de String existe un elemento
     * igual (ignorando mayúsculas y minúsculas)
     * @param lista Lista sobre la cual se realiza la búsqueda.
     * @param valorNuevo Valor a buscar.
     * @return <code>String</code> con el valor de la lista en caso de existir,
     * <code>null</code> en caso contrario.
     */
    private String contieneValor(List<String> lista, String valorNuevo){
        for (String string : lista) {
            if (string.equalsIgnoreCase(valorNuevo)){
                return string;
            }
        }
        return null;
    }
    
    /**
     * Solicita confirmacón para eliminar un valor seleccionado de la lista 
     * de valores cuando el metadato es de tipo Enumerado.
     */
    @NotifyChange({"opcionesEnumerado", "opcionSeleccionada"})
    @Command
    public void eliminarValorEnumerado(){
        
        if (opcionSeleccionada != null){
            String preguntaConfirmacion = Labels.getLabel("trd.metadatos.mensaje.confirmarEliminar");
            String titulo = Labels.getLabel("commons.titulo.confirmacion");
            Messagebox.show(preguntaConfirmacion, titulo,
                    Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                    new EventListener<Event>() {
                
                @Override
                public void onEvent(Event evnt) throws Exception {
                    if (Messagebox.ON_YES.equals(evnt.getName())) {
                        confirmarEliminar();
                    }
                }
            });
        }
    }
    
    /**
     * Elimina la opción de la lista de opciones.
     */
    public void confirmarEliminar(){
        opcionesEnumerado.remove(opcionSeleccionada);
        opcionSeleccionada = null;
        BindUtils.postNotifyChange(null, null, AdministrarMetadatosController.this, "opcionesEnumerado");
        lstOpcionesEnumerado.invalidate();
    }
    
    /**
     * Verifica el tipo de dato seleccionado y de acuerdo a esto habilita,
     * deshabilita y carga valores en la interfaz.
     */
    @Command
    @NotifyChange({"opcionSeleccionada","opcionesEnumerado"})
    public void verificarTipoSeleccionado(){
        lstOpcionesEnumerado.setSclass("");
        lstOpcionesEnumerado.invalidate();
        
        deshabilitarEnumerado();
        deshabilitarEnumeradoBd();
        txtExpresionRegularMetadato.setReadonly(true);
        txtExpresionRegularMetadato.setSclass("");
        
        if (tipoDatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_ENUMERADO){
            habilitarEnumerado();
            if (metadatoSeleccionado != null){
                if (metadatoSeleccionado.getValoresEnumerado() != null){
                    opcionesEnumerado = cargarListaValores(metadatoSeleccionado.getValoresEnumerado());
                }
            }
            txtTablaBdMetadato.setValue(null);
            txtColumnaValorMetadato.setValue(null);
            txtColumnaTextoMetadato.setValue(null);
        } else if (tipoDatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_ENUMERADO_BD){
            habilitarEnumeradoBd();
            if (metadatoSeleccionado != null){
                txtTablaBdMetadato.setValue(metadatoSeleccionado.getEnumBdTabla());
                txtColumnaValorMetadato.setValue(metadatoSeleccionado.getEnumBdValor());
                txtColumnaTextoMetadato.setValue(metadatoSeleccionado.getEnumBdEtiqueta());
            }
            opcionSeleccionada = null;
            opcionesEnumerado.clear();
        } else if (tipoDatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_EXPRESION_REGULAR){
            txtExpresionRegularMetadato.setReadonly(false);
            txtExpresionRegularMetadato.setSclass("obligatorio");
            opcionSeleccionada = null;
            opcionesEnumerado.clear();
            txtTablaBdMetadato.setValue(null);
            txtColumnaValorMetadato.setValue(null);
            txtColumnaTextoMetadato.setValue(null);
        } else {
            opcionSeleccionada = null;
            opcionesEnumerado.clear();
            txtTablaBdMetadato.setValue(null);
            txtColumnaValorMetadato.setValue(null);
            txtColumnaTextoMetadato.setValue(null);
        }
    }
    
    /**
     * Habilita los campos de la pestaña Enumerado.
     */
    private void habilitarEnumerado(){
        
        txtValoresMetadato.setReadonly(false);
        txtValoresMetadato.setSclass("obligatorio");
        btnAgregarValoresMetadato.setDisabled(false);
        btnEliminarValoresMetadato.setDisabled(false);
    }
    
    /**
     * Deshabilita los campos de la pestaña Enumerado.
     */
    public void deshabilitarEnumerado(){
        
        txtValoresMetadato.setReadonly(true);
        txtValoresMetadato.setSclass("");
        btnAgregarValoresMetadato.setDisabled(true);
        btnEliminarValoresMetadato.setDisabled(true);
    }
    
    /**
     * Habilita los campos de la pestaña EnumeradoBD.
     */
    public void habilitarEnumeradoBd(){
        
        txtTablaBdMetadato.setDisabled(false);
        txtColumnaTextoMetadato.setDisabled(false);
        txtColumnaValorMetadato.setDisabled(false);
        txtTablaBdMetadato.setSclass("obligatorio");
        txtColumnaTextoMetadato.setSclass("obligatorio");
        txtColumnaValorMetadato.setSclass("obligatorio");
    }
    
    /**
     * Deshabilita los campos de la pestaña EnumeradoBD.
     */
    public void deshabilitarEnumeradoBd(){
        
        txtTablaBdMetadato.setDisabled(true);
        txtColumnaTextoMetadato.setDisabled(true);
        txtColumnaValorMetadato.setDisabled(true);
        txtTablaBdMetadato.setSclass("");
        txtColumnaTextoMetadato.setSclass("");
        txtColumnaValorMetadato.setSclass("");
    }
    
    /**
     * Convierte una lista en un String con cada elemento separado por
     * el caracter de la constante SEPARADOR_VALORES.
     * @param lista Lista a convertir en String.
     * @return String con cada elemento.
     */
    private String listaToString(List<String> lista){
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < lista.size(); i++) {
            sb.append(lista.get(i));
            if (i<(lista.size()-1)){
                sb.append(SEPARADOR_VALORES);
            }            
        }
        return sb.toString();
    }
    
    /**
     * Crea un metadato en el sistema.
     */
    @NotifyChange({"metadatos","metadatosFiltrados"})
    @Command
    public void crearMetadato(){
        
        if (validarCampos()){
            TrdMetadato nuevoMetadato = new TrdMetadato();
            
            nuevoMetadato.setNombre(txtMetadatoNombre.getValue().trim());
            nuevoMetadato.setTipoDatoId(tipoDatoSeleccionado.getTipoDatoId());
            nuevoMetadato.setNombreTipoDato(tipoDatoSeleccionado.getNombre());
            nuevoMetadato.setInactivo(tipoDatoSeleccionado.getTipoDatoId() == 1);
            nuevoMetadato.setLongitud(intLongitudMetadato.getValue());
            
            if (tipoDatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_EXPRESION_REGULAR){
                nuevoMetadato.setExpresionRegular(txtExpresionRegularMetadato.getValue());
            } else if (tipoDatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_ENUMERADO){
                nuevoMetadato.setValoresEnumerado(listaToString(opcionesEnumerado));
            } else if (tipoDatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_ENUMERADO_BD){
                nuevoMetadato.setEnumBdTabla(txtTablaBdMetadato.getValue().trim());
                nuevoMetadato.setEnumBdValor(txtColumnaValorMetadato.getValue().trim());
                nuevoMetadato.setEnumBdEtiqueta(txtColumnaTextoMetadato.getValue().trim());
            }
            
            trdMetadatoService.guardarMetadato(SessionUtil.getAuthToken(Sessions.getCurrent()), nuevoMetadato);
            
            cargarMetadatos();
            
            String mensaje = Labels.getLabel("trd.metadatos.mensaje.metadatoCreado");
            String tipo = "info";
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
            
            //Actualiza la información en la ventana de Agregar metadatos
            Map<String, Object> params = new HashMap<>();
            params.put("arg1", "");
            BindUtils.postGlobalCommand(null, null, "cargarMetadatos", params);
        }
    }
    
    /**
     * Actualiza un metadato en el sistema.
     */
    @NotifyChange({"metadatos","metadatosFiltrados","metadatoSeleccionado"})
    @Command
    public void actualizarMetadato(){
        
        if (validarCampos()){
            
            metadatoSeleccionado.setNombre(txtMetadatoNombre.getValue().trim());
            metadatoSeleccionado.setTipoDatoId(tipoDatoSeleccionado.getTipoDatoId());
            metadatoSeleccionado.setNombreTipoDato(tipoDatoSeleccionado.getNombre());
            metadatoSeleccionado.setInactivo(tipoDatoSeleccionado.getTipoDatoId() == 1);
            metadatoSeleccionado.setLongitud(intLongitudMetadato.getValue());
            
            if (tipoDatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_EXPRESION_REGULAR){
                metadatoSeleccionado.setExpresionRegular(txtExpresionRegularMetadato.getValue());
                metadatoSeleccionado.setValoresEnumerado(null);
                metadatoSeleccionado.setEnumBdTabla(null);
                metadatoSeleccionado.setEnumBdValor(null);
                metadatoSeleccionado.setEnumBdEtiqueta(null);
            } else if (tipoDatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_ENUMERADO){
                metadatoSeleccionado.setValoresEnumerado(listaToString(opcionesEnumerado));
                metadatoSeleccionado.setExpresionRegular(null);
                metadatoSeleccionado.setEnumBdTabla(null);
                metadatoSeleccionado.setEnumBdValor(null);
                metadatoSeleccionado.setEnumBdEtiqueta(null);
            } else if (tipoDatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_ENUMERADO_BD){
                metadatoSeleccionado.setExpresionRegular(null);
                metadatoSeleccionado.setValoresEnumerado(null);
                metadatoSeleccionado.setEnumBdTabla(txtTablaBdMetadato.getValue().trim());
                metadatoSeleccionado.setEnumBdValor(txtColumnaValorMetadato.getValue().trim());
                metadatoSeleccionado.setEnumBdEtiqueta(txtColumnaTextoMetadato.getValue().trim());
            } else {
                metadatoSeleccionado.setValoresEnumerado(null);
                metadatoSeleccionado.setExpresionRegular(null);
                metadatoSeleccionado.setEnumBdTabla(null);
                metadatoSeleccionado.setEnumBdValor(null);
                metadatoSeleccionado.setEnumBdEtiqueta(null);
            }
            
            trdMetadatoService.guardarMetadato(SessionUtil.getAuthToken(Sessions.getCurrent()), metadatoSeleccionado);
            
            cargarMetadatos();
            
            String mensaje = Labels.getLabel("trd.metadatos.mensaje.metadatoActualizado");
            String tipo = "info";
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
            
            //Actualiza la información en la ventana de Agregar metadatos
            Map<String, Object> params = new HashMap<>();
            params.put("arg1", "");
            BindUtils.postGlobalCommand(null, null, "cargarMetadatos", params);
        }
    }
    
    /**
     * Valida los campos obligatorios dependiendo del tipo de dato seleccionado.
     * @return
     */
    private boolean validarCampos(){
        
        if (txtMetadatoNombre.getValue().trim().isEmpty()){
            throw new WrongValueException(txtMetadatoNombre, Labels.getLabel("commons.tooltip.campoRequerido"));
        }
        if (tipoDatoSeleccionado == null){
            throw new WrongValueException(comboTipoMetadato, Labels.getLabel("commons.tooltip.campoRequerido"));
        }
        if (tipoDatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_EXPRESION_REGULAR){
            if (txtExpresionRegularMetadato.getValue().trim().isEmpty()){
                throw new WrongValueException(txtExpresionRegularMetadato, Labels.getLabel("commons.tooltip.campoRequerido"));
            }
        } else if (tipoDatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_ENUMERADO){
            if (opcionesEnumerado.isEmpty()){
                lstOpcionesEnumerado.setSclass("obligatorio");
                throw new WrongValueException(lstOpcionesEnumerado, Labels.getLabel("trd.metadatos.label.agregarValorEnumerado"));
            }
        } else if (tipoDatoSeleccionado.getTipoDatoId() == Utils.ID_TIPO_DATO_ENUMERADO_BD){
            if (txtTablaBdMetadato.getValue().trim().isEmpty()){
                throw new WrongValueException(txtTablaBdMetadato, Labels.getLabel("commons.tooltip.campoRequerido"));
            }
            if (txtColumnaValorMetadato.getValue().trim().isEmpty()){
                throw new WrongValueException(txtColumnaValorMetadato, Labels.getLabel("commons.tooltip.campoRequerido"));
            }
            if (txtColumnaTextoMetadato.getValue().trim().isEmpty()){
                throw new WrongValueException(txtColumnaTextoMetadato, Labels.getLabel("commons.tooltip.campoRequerido"));
            }
        }
        return true;
    }
    
    /**
     * Acción ejecutada por el botón cancelar.
     */
    @Command
    public void cancelar(){
        windowAdminMetadatos.detach();
    }

    /**
     * @return the tipoDatos
     */
    public List<TrdTipoDato> getTipoDatos() {
        return tipoDatos;
    }

    /**
     * @param tipoDatos the tipoDatos to set
     */
    public void setTipoDatos(List<TrdTipoDato> tipoDatos) {
        this.tipoDatos = tipoDatos;
    }

    /**
     * @return the idEmpresa
     */
    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the metadatos
     */
    public List<TrdMetadato> getMetadatos() {
        return metadatos;
    }

    /**
     * @param metadatos the metadatos to set
     */
    public void setMetadatos(List<TrdMetadato> metadatos) {
        this.metadatos = metadatos;
    }

    /**
     * @return the metadatosFiltrados
     */
    public List<TrdMetadato> getMetadatosFiltrados() {
        return metadatosFiltrados;
    }

    /**
     * @param metadatosFiltrados the metadatosFiltrados to set
     */
    public void setMetadatosFiltrados(List<TrdMetadato> metadatosFiltrados) {
        this.metadatosFiltrados = metadatosFiltrados;
    }

    /**
     * @return the opcionesEnumerado
     */
    public List<String> getOpcionesEnumerado() {
        return opcionesEnumerado;
    }

    /**
     * @param opcionesEnumerado the opcionesEnumerado to set
     */
    public void setOpcionesEnumerado(List<String> opcionesEnumerado) {
        this.opcionesEnumerado = opcionesEnumerado;
    }

    /**
     * @return the metadatoSeleccionado
     */
    public TrdMetadato getMetadatoSeleccionado() {
        return metadatoSeleccionado;
    }

    /**
     * @param metadatoSeleccionado the metadatoSeleccionado to set
     */
    public void setMetadatoSeleccionado(TrdMetadato metadatoSeleccionado) {
        this.metadatoSeleccionado = metadatoSeleccionado;
    }

    /**
     * @return the tipoDatoSeleccionado
     */
    public TrdTipoDato getTipoDatoSeleccionado() {
        return tipoDatoSeleccionado;
    }

    /**
     * @param tipoDatoSeleccionado the tipoDatoSeleccionado to set
     */
    public void setTipoDatoSeleccionado(TrdTipoDato tipoDatoSeleccionado) {
        this.tipoDatoSeleccionado = tipoDatoSeleccionado;
    }

    /**
     * @return the opcionSeleccionada
     */
    public String getOpcionSeleccionada() {
        return opcionSeleccionada;
    }

    /**
     * @param opcionSeleccionada the opcionSeleccionada to set
     */
    public void setOpcionSeleccionada(String opcionSeleccionada) {
        this.opcionSeleccionada = opcionSeleccionada;
    }
}
