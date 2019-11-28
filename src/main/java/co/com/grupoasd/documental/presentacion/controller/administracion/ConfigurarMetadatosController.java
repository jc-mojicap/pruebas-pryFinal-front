/*
* Archivo: ConfigurarMetadatosController.java
* Fecha creacion = 16/06/2017
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

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.trd.model.TrdMetadato;
import co.com.grupoasd.documental.cliente.trd.model.TrdMetadatoSubserie;
import co.com.grupoasd.documental.cliente.trd.model.TrdMetadatoTipoDocm;

public class ConfigurarMetadatosController {
    
    @Wire
    private Window windowConfigMetadatos;
    @Wire
    private Listbox lstMetadatosExpediente;
    @Wire
    private Listbox lstMetadatosTipoDocumental;
    @Wire
    private Combobox cmbTipoDocumental;
    
    private List<TrdMetadato> metadatosExpediente;
    private List<TrdMetadatoSubserie> metadatosSubserieExpediente;
    private TrdMetadatoSubserie metadatoExpedienteSeleccionado;
    private List<TrdMetadato> metadatosTipoDoc;
    private List<TrdMetadatoTipoDocm> metadatosTipoDocumental;
    private TrdMetadatoTipoDocm metadatoTipoDocumentalSeleccionado;
    private int indexMetadatoExpedienteSeleccionado;
    private int indexMetadatoTipoDocumentalSeleccionado;
    
    /**
     * Inicialización del controller.
     */
    @Init
    public void init() {
        // Iniciar todos los servicios.
    }
    
    /**
     * Método que se ejecuta después de que ZK crea los componentes de la vista.
     * 
     * @param view
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        
    }
    
    /**
     * Hace el llamado a la ventana de agregar metadatos, a dicha ventana se le deben
     * pasar tres parámetros en un Map:
     * - callback: nombre del método de esta clase que recibirá la respuesta.
     *      si isRespuestaMvc es enviado con valor <code>false</code> dicho método debe 
     *      estar anotado con la etiqueta GlobalCommand.
     * - metadatos: lista con los metadatos que actualmente hay en la interfaz.
     * - isRespuestaMvc: <code>true</code> si se llama de una ventana que implementa MVC, 
     *      <code>false</code> para una que implementa MVVM.
     */
    @Command
    @NotifyChange({"metadatosSubserieExpediente","metadatoExpedienteSeleccionado"})
    public void agregarMetadatosExpediente(){
        Map<String, Object> params = new HashMap<>();
        params.put("callback", "cargarMetadatosExpediente");
        params.put("metadatos", metadatosExpediente);
        params.put("isRespuestaMvc", false); //true si se llama de una ventana creada con MVC, false para una creada con MVVM
        Window window = (Window) Executions.createComponents("/administracion/sistema/agregar_metadatos.zul", windowConfigMetadatos, params);
        window.doModal();
    }
    
    /**
     * Recibe los metadatos retornados por la ventana de agregar metadatos.
     * Debido a que se necesitan convertir los objetos TrdMetadato a TrdMetadatoSubserie
     * y se desea que no se pierda la información y modificaciones realizadas en la interfaz
     * es necesario validar si los TrdMetadatos recibidos ya se encuentran en la vista, para
     * no sobreescribirlos y no perder la información ya diligenciada. Para lograr esto se
     * implementó la siguiente lógica:
     * 
     *  Se reciben los metadatos, se transforman y se agregan a una lista (nueva).
     *  Se crea una lista (pintar) que será la que contenga los objetos que serán pintados 
     *  en la vista.
     *  Se recorre la lista (original) de los datos que estaban previamente en la interfaz:
     *      - Si los objetos de la lista original están en la lista nueva, 
     *      se agregan a la lista que se pintará.
     *  Se recorre la lista (nueva) recibida
     *      - Si los objetos de la lista nueva no están en la lista para pintar,
     *      se agregan a la lista que se pintará.
     *  Finalmente, a la lista que es enlazada con la interfaz (metadatosSubserieExpediente)
     *  se le asigna la lista para pintar creada en este método.
     * 
     * @param metadatosElegidos Lista con metadatos retornados por la ventana agregar metadatos.
     */
    @GlobalCommand
    @NotifyChange({"metadatosSubserieExpediente","metadatoExpedienteSeleccionado"})
    public void cargarMetadatosExpediente(@BindingParam("metadatosElegidos") List<TrdMetadato> metadatosElegidos){
        lstMetadatosExpediente.setSclass("");
        metadatoExpedienteSeleccionado = null;
        metadatosExpediente = metadatosElegidos;
        
        List<TrdMetadatoSubserie> metadatosSubserie = new ArrayList<>();
        
        for (TrdMetadato trdMetadato : metadatosElegidos) {
            TrdMetadatoSubserie temp = new TrdMetadatoSubserie();
            temp.setMetadatoId(trdMetadato.getMetadatoId());
            temp.setNombreMetadato(trdMetadato.getNombre());
            temp.setTipoDatoId(trdMetadato.getTipoDatoId());
            temp.setNombreTipoDato(trdMetadato.getNombreTipoDato());
            
            metadatosSubserie.add(temp);
            
        }
        
        List<TrdMetadatoSubserie> pintar = new ArrayList<>();

        if (metadatosSubserieExpediente != null){
            for (TrdMetadatoSubserie original : metadatosSubserieExpediente) {
                if (metadatosSubserie.contains(original)){
                    TrdMetadatoSubserie otro = metadatosSubserie.get(metadatosSubserie.indexOf(original));
                    if (otro.getNombreTipoDato().concat(otro.getNombreMetadato()).equals(original.getNombreTipoDato().concat(original.getNombreMetadato()))){
                        pintar.add(original);
                    } else {
                        otro.setObligatorio(original.isObligatorio());
                        otro.setInactivo(original.isInactivo());
                        otro.setObservaciones(original.getObservaciones());
                        pintar.add(otro);
                    }
                }
            }
        }
        
        for (TrdMetadatoSubserie nueva : metadatosSubserie) {
            if (!pintar.contains(nueva)){
                pintar.add(nueva);
            }
        }
        
        metadatosSubserieExpediente = pintar;
        
    }
    
    /**
     * Meétodo encargado de actualizar la información de las listas en caso de que se haya hecho
     * alguna edición desde la ventana de administrar metadatos sobre metadatos 
     * que ya hayan sido agregados a alguna de las dos listas.
     * @param metadatos
     */
    @NotifyChange({"metadatosSubserieExpediente","metadatoExpedienteSeleccionado", "metadatosTipoDocumental","metadatoTipoDocumentalSeleccionado"})
    @GlobalCommand
    public void verificarMetadatos(@BindingParam("arg1") List<TrdMetadato> metadatos){
        
        List<TrdMetadatoSubserie> metadatosSubserie = new ArrayList<>();
        List<TrdMetadatoTipoDocm> metadatosTipoDoc = new ArrayList<>();
        
        for (TrdMetadato trdMetadato : metadatos) {
            TrdMetadatoSubserie tempSubs = new TrdMetadatoSubserie();
            tempSubs.setMetadatoId(trdMetadato.getMetadatoId());
            tempSubs.setNombreMetadato(trdMetadato.getNombre());
            tempSubs.setTipoDatoId(trdMetadato.getTipoDatoId());
            tempSubs.setNombreTipoDato(trdMetadato.getNombreTipoDato());
            
            metadatosSubserie.add(tempSubs);
            
            TrdMetadatoTipoDocm tempTipoD = new TrdMetadatoTipoDocm();
            tempTipoD.setMetadatoId(trdMetadato.getMetadatoId());
            tempTipoD.setNombreMetadato(trdMetadato.getNombre());
            tempTipoD.setTipoDatoId(trdMetadato.getTipoDatoId());
            tempTipoD.setNombreTipoDato(trdMetadato.getNombreTipoDato());
            
            metadatosTipoDoc.add(tempTipoD);
        }
        
        if (metadatosSubserieExpediente != null){
            for (TrdMetadatoSubserie originalExp : metadatosSubserieExpediente) {
                if (metadatosSubserie.contains(originalExp)){
                    TrdMetadatoSubserie otroExp = metadatosSubserie.get(metadatosSubserie.indexOf(originalExp));
                    if (!otroExp.getNombreTipoDato().concat(otroExp.getNombreMetadato()).equals(originalExp.getNombreTipoDato().concat(originalExp.getNombreMetadato()))){
                        originalExp.setNombreTipoDato(otroExp.getNombreTipoDato());
                        originalExp.setNombreMetadato(otroExp.getNombreMetadato());
                    }
                }
            }
        }
        
        if (metadatosTipoDocumental != null){
            for (TrdMetadatoTipoDocm originalTipoD : metadatosTipoDocumental) {
                if (metadatosSubserie.contains(originalTipoD)){
                    TrdMetadatoSubserie otroExp = metadatosSubserie.get(metadatosSubserie.indexOf(originalTipoD));
                    if (!otroExp.getNombreTipoDato().concat(otroExp.getNombreMetadato()).equals(originalTipoD.getNombreTipoDato().concat(originalTipoD.getNombreMetadato()))){
                        originalTipoD.setNombreTipoDato(otroExp.getNombreTipoDato());
                        originalTipoD.setNombreMetadato(otroExp.getNombreMetadato());
                    }
                }
            }
        }
    }
    
    
    
    /**
     * Hace el llamado a la ventana de agregar metadatos, a dicha ventana se le deben
     * pasar tres parámetros en un Map:
     * - callback: nombre del método de esta clase que recibirá la respuesta.
     *      si isRespuestaMvc es enviado con valor <code>false</code> dicho método debe 
     *      estar anotado con la etiqueta GlobalCommand.
     * - metadatos: lista con los metadatos que actualmente hay en la interfaz.
     * - isRespuestaMvc: <code>true</code> si se llama de una ventana que implementa MVC, 
     *      <code>false</code> para una que implementa MVVM.
     */
    @Command
    @NotifyChange({"metadatosTipoDocumental","metadatoTipoDocumentalSeleccionado"})
    public void agregarMetadatosTipoDocumental(){
        Map<String, Object> params = new HashMap<>();
        params.put("callback", "cargarMetadatosTipoDocumental");
        params.put("metadatos", metadatosTipoDoc);
        params.put("isRespuestaMvc", false); //true si se llama de una ventana creada con MVC, false para una creada con MVVM
        Window window = (Window) Executions.createComponents("/administracion/sistema/agregar_metadatos.zul", windowConfigMetadatos, params);
        window.doModal();
    }
    
    /**
     * Recibe los metadatos retornados por la ventana de agregar metadatos.
     * Debido a que se necesitan convertir los objetos TrdMetadato a TrdMetadatoTipoDocm
     * y se desea que no se pierda la información y modificaciones realizadas en la interfaz
     * es necesario validar si los TrdMetadatos recibidos ya se encuentran en la vista, para
     * no sobreescribirlos y no perder la información ya diligenciada. Para lograr esto se
     * implementó la siguiente lógica:
     * 
     *  Se reciben los metadatos, se transforman y se agregan a una lista (nueva).
     *  Se crea una lista (pintar) que será la que contenga los objetos que serán pintados 
     *  en la vista.
     *  Se recorre la lista (original) de los datos que estaban previamente en la interfaz:
     *      - Si los objetos de la lista original están en la lista nueva, 
     *      se agregan a la lista que se pintará.
     *  Se recorre la lista (nueva) recibida
     *      - Si los objetos de la lista nueva no están en la lista para pintar,
     *      se agregan a la lista que se pintará.
     *  Finalmente, a la lista que es enlazada con la interfaz (metadatosTipoDocumental)
     *  se le asigna la lista para pintar creada en este método.
     * 
     * @param metadatosElegidos Lista con metadatos retornados por la ventana agregar metadatos.
     */
    @GlobalCommand
    @NotifyChange({"metadatosTipoDocumental","metadatoTipoDocumentalSeleccionado"})
    public void cargarMetadatosTipoDocumental(@BindingParam("metadatosElegidos") List<TrdMetadato> metadatosElegidos){
        lstMetadatosTipoDocumental.setSclass("");
        metadatoTipoDocumentalSeleccionado = null;
        metadatosTipoDoc = metadatosElegidos;
        
        List<TrdMetadatoTipoDocm> metadatosTipoDoc = new ArrayList<>();
        
        for (TrdMetadato trdMetadato : metadatosElegidos) {
            TrdMetadatoTipoDocm temp = new TrdMetadatoTipoDocm();
            temp.setMetadatoId(trdMetadato.getMetadatoId());
            temp.setNombreMetadato(trdMetadato.getNombre());
            temp.setTipoDatoId(trdMetadato.getTipoDatoId());
            temp.setNombreTipoDato(trdMetadato.getNombreTipoDato());
            
            metadatosTipoDoc.add(temp);
        }
        
        List<TrdMetadatoTipoDocm> pintar = new ArrayList<>();
        
        if (metadatosTipoDocumental != null){
            for (TrdMetadatoTipoDocm original : metadatosTipoDocumental) {
                if (metadatosTipoDoc.contains(original)){
                    TrdMetadatoTipoDocm otro = metadatosTipoDoc.get(metadatosTipoDoc.indexOf(original));
                    if (otro.getNombreTipoDato().concat(otro.getNombreMetadato()).equals(original.getNombreTipoDato().concat(original.getNombreMetadato()))){
                        pintar.add(original);
                    } else {
                        otro.setObligatorio(original.isObligatorio());
                        otro.setInactivo(original.isInactivo());
                        otro.setObservaciones(original.getObservaciones());
                        pintar.add(otro);
                    }
                }
            }
        }
        
        for (TrdMetadatoTipoDocm nueva : metadatosTipoDoc) {
            if (!pintar.contains(nueva)){
                pintar.add(nueva);
            }
        }
        
        metadatosTipoDocumental = pintar;
    }
    
    /**
     * Permite modificar el orden de los objetos de la lista metadatosSubserieExpediente
     * al subir un nivel el que haya sido seleccionado.
     */
    @Command
    @NotifyChange({"metadatosSubserieExpediente","metadatoExpedienteSeleccionado"})
    public void subirMetadatoExpediente(){
        if (metadatoExpedienteSeleccionado == null){
            return;
        }
        if (indexMetadatoExpedienteSeleccionado <= 0){
            return;
        }
        List<TrdMetadatoSubserie> metadatosTemporal = metadatosSubserieExpediente;
        metadatosTemporal.remove(indexMetadatoExpedienteSeleccionado);
        metadatosTemporal.add(--indexMetadatoExpedienteSeleccionado, metadatoExpedienteSeleccionado);
        metadatosSubserieExpediente = metadatosTemporal;
        
    }
    
    /**
     * Permite modificar el orden de los objetos de la lista metadatosSubserieExpediente
     * al bajar un nivel el que haya sido seleccionado.
     */
    @Command
    @NotifyChange({"metadatosSubserieExpediente","metadatoExpedienteSeleccionado"})
    public void bajarMetadatoExpediente(){
        if (metadatoExpedienteSeleccionado == null){
            return;
        }
        if (indexMetadatoExpedienteSeleccionado == metadatosExpediente.size()-1 || indexMetadatoExpedienteSeleccionado < 0){
            return;
        }
        List<TrdMetadatoSubserie> metadatosTemporal = metadatosSubserieExpediente;
        metadatosTemporal.remove(indexMetadatoExpedienteSeleccionado);
        metadatosTemporal.add(++indexMetadatoExpedienteSeleccionado, metadatoExpedienteSeleccionado);
        metadatosSubserieExpediente = metadatosTemporal;
        
    }
    
    /**
     * Permite modificar el orden de los objetos de la lista metadatosTipoDocumental
     * al subir un nivel el que haya sido seleccionado.
     */
    @Command
    @NotifyChange({"metadatosTipoDocumental","metadatoTipoDocumentalSeleccionado"})
    public void subirMetadatoTipoDocumental(){
        if (metadatoTipoDocumentalSeleccionado == null){
            return;
        }
        if (indexMetadatoTipoDocumentalSeleccionado <= 0){
            return;
        }
        List<TrdMetadatoTipoDocm> metadatosTemporal = metadatosTipoDocumental;
        metadatosTemporal.remove(indexMetadatoTipoDocumentalSeleccionado);
        metadatosTemporal.add(--indexMetadatoTipoDocumentalSeleccionado, metadatoTipoDocumentalSeleccionado);
        metadatosTipoDocumental = metadatosTemporal;
        
    }
    
    /**
     * Permite modificar el orden de los objetos de la lista metadatosTipoDocumental
     * al bajar un nivel el que haya sido seleccionado.
     */
    @Command
    @NotifyChange({"metadatosTipoDocumental","metadatoTipoDocumentalSeleccionado"})
    public void bajarMetadatoTipoDocumental(){
        if (metadatoTipoDocumentalSeleccionado == null){
            return;
        }
        if (indexMetadatoTipoDocumentalSeleccionado == metadatosTipoDoc.size()-1 || indexMetadatoTipoDocumentalSeleccionado < 0){
            return;
        }
        List<TrdMetadatoTipoDocm> metadatosTemporal = metadatosTipoDocumental;
        metadatosTemporal.remove(indexMetadatoTipoDocumentalSeleccionado);
        metadatosTemporal.add(++indexMetadatoTipoDocumentalSeleccionado, metadatoTipoDocumentalSeleccionado);
        metadatosTipoDocumental = metadatosTemporal;
        
    }
    
    /**
     * Guarda la configuración realizada en la ventana.
     */
    @Command
    public void guardarConfiguracion(){
        
        if (verificarCampos()){
            System.err.println("guardarConfiguracion");
            System.err.println("MetadatosExpediente");
            metadatosExpediente.forEach(temp -> System.err.println(temp));
            System.err.println("MetadatosTipoDocumental");
            metadatosTipoDoc.forEach(temp -> System.err.println(temp));
            
            for (Listitem item: lstMetadatosExpediente.getItems()) {
                System.err.println("Id " + ((Listcell)item.getChildren().get(0)).getLabel());
                System.err.println("Tipo " + ((Listcell)item.getChildren().get(1)).getLabel());
                System.err.println("Nombre " + ((Listcell)item.getChildren().get(2)).getLabel());
                System.err.println("Obligatorio " + ((Checkbox)item.getChildren().get(3).getFirstChild()).isChecked());
                System.err.println("Estado " + ((Combobox)item.getChildren().get(4).getFirstChild()).getSelectedItem().getValue() + " - " + ((Combobox)item.getChildren().get(4).getFirstChild()).getSelectedItem().getLabel());
                System.err.println("Observaciones " + ((Textbox)item.getChildren().get(5).getFirstChild()).getValue());
            }
            
            for (Listitem item: lstMetadatosTipoDocumental.getItems()) {
                System.err.println("Id " + ((Listcell)item.getChildren().get(0)).getLabel());
                System.err.println("Tipo " + ((Listcell)item.getChildren().get(1)).getLabel());
                System.err.println("Nombre " + ((Listcell)item.getChildren().get(2)).getLabel());
                System.err.println("Obligatorio " + ((Checkbox)item.getChildren().get(3).getFirstChild()).isChecked());
                System.err.println("Estado " + ((Combobox)item.getChildren().get(4).getFirstChild()).getSelectedItem().getValue() + " - " + ((Combobox)item.getChildren().get(4).getFirstChild()).getSelectedItem().getLabel());
                System.err.println("Observaciones " + ((Textbox)item.getChildren().get(5).getFirstChild()).getValue());
            }
        }
    }
    
    /**
     * Verifica si todos los campos obligatorios fueron diligenciados.
     * @return <code>true</code> si todos los campos obligatorios fueron diligenciados
     *      <code>false</code> en caso de que no se hayan agregado metadatos al expediente
     *      y al tipo documental, y en caso de que algún campo obligatorio de tipo Input 
     *      no haya sido diligenciado, lanza una excepción (WrongValueException) sobre
     *      dicho campo para informar al usuario que el mismo es obligatorio.
     */
    public boolean verificarCampos(){
        
        if (metadatosExpediente == null || metadatosExpediente.isEmpty() || metadatosTipoDoc == null || metadatosTipoDoc.isEmpty()){
            
            String mensaje = Labels.getLabel("trd.metadatos.mensaje.noAgregados");
            String tipo = "error";
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
            
            if (metadatosExpediente == null || metadatosExpediente.isEmpty()){
                lstMetadatosExpediente.setSclass("obligatorio");
            }
            
            if (metadatosTipoDoc == null || metadatosTipoDoc.isEmpty()){
                lstMetadatosTipoDocumental.setSclass("obligatorio");
            }
            
            return false;
        }
        
        for (Listitem item: lstMetadatosExpediente.getItems()) {
            if (((Combobox)item.getChildren().get(4).getFirstChild()).getSelectedItem() == null){
                throw new WrongValueException(((Combobox)item.getChildren().get(4).getFirstChild()), Labels.getLabel("commons.tooltip.campoRequerido"));
            }
        }
        
        for (Listitem item: lstMetadatosTipoDocumental.getItems()) {
            if (((Combobox)item.getChildren().get(4).getFirstChild()).getSelectedItem() == null){
                throw new WrongValueException(((Combobox)item.getChildren().get(4).getFirstChild()), Labels.getLabel("commons.tooltip.campoRequerido"));
            }
        }
        
        if (cmbTipoDocumental.getSelectedItem() == null){
            throw new WrongValueException(cmbTipoDocumental, Labels.getLabel("commons.tooltip.campoRequerido"));
        }
        
        return true;
    }

    /**
     * @return the metadatosExpediente
     */
    public List<TrdMetadato> getMetadatosExpediente() {
        return metadatosExpediente;
    }

    /**
     * @param metadatosExpediente the metadatosExpediente to set
     */
    public void setMetadatosExpediente(List<TrdMetadato> metadatosExpediente) {
        this.metadatosExpediente = metadatosExpediente;
    }

    /**
     * @return the metadatosSubserieExpediente
     */
    public List<TrdMetadatoSubserie> getMetadatosSubserieExpediente() {
        return metadatosSubserieExpediente;
    }

    /**
     * @param metadatosSubserieExpediente the metadatosSubserieExpediente to set
     */
    public void setMetadatosSubserieExpediente(List<TrdMetadatoSubserie> metadatosSubserieExpediente) {
        this.metadatosSubserieExpediente = metadatosSubserieExpediente;
    }

    /**
     * @return the metadatosTipoDocumental
     */
    public List<TrdMetadato> getMetadatosTipoDoc() {
        return metadatosTipoDoc;
    }

    /**
     * @param metadatosTipoDocumental the metadatosTipoDocumental to set
     */
    public void setMetadatosTipoDoc(List<TrdMetadato> metadatosTipoDoc) {
        this.metadatosTipoDoc = metadatosTipoDoc;
    }

    /**
     * @return the metadatosTipoDocumental
     */
    public List<TrdMetadatoTipoDocm> getMetadatosTipoDocumental() {
        return metadatosTipoDocumental;
    }

    /**
     * @param metadatosTipoDocumental the metadatosTipoDocumental to set
     */
    public void setMetadatosTipoDocumental(List<TrdMetadatoTipoDocm> metadatosTipoDocumental) {
        this.metadatosTipoDocumental = metadatosTipoDocumental;
    }

    /**
     * @return the metadatoExpedienteSeleccionado
     */
    public TrdMetadatoSubserie getMetadatoExpedienteSeleccionado() {
        return metadatoExpedienteSeleccionado;
    }

    /**
     * @param metadatoExpedienteSeleccionado the metadatoExpedienteSeleccionado to set
     */
    public void setMetadatoExpedienteSeleccionado(TrdMetadatoSubserie metadatoExpedienteSeleccionado) {
        this.metadatoExpedienteSeleccionado = metadatoExpedienteSeleccionado;
    }

    /**
     * @return the metadatoTipoDocumentalSeleccionado
     */
    public TrdMetadatoTipoDocm getMetadatoTipoDocumentalSeleccionado() {
        return metadatoTipoDocumentalSeleccionado;
    }

    /**
     * @param metadatoTipoDocumentalSeleccionado the metadatoTipoDocumentalSeleccionado to set
     */
    public void setMetadatoTipoDocumentalSeleccionado(TrdMetadatoTipoDocm metadatoTipoDocumentalSeleccionado) {
        this.metadatoTipoDocumentalSeleccionado = metadatoTipoDocumentalSeleccionado;
    }

    /**
     * @return the indexMetadatoExpedienteSeleccionado
     */
    public int getIndexMetadatoExpedienteSeleccionado() {
        return indexMetadatoExpedienteSeleccionado;
    }

    /**
     * @param indexMetadatoExpedienteSeleccionado the indexMetadatoExpedienteSeleccionado to set
     */
    public void setIndexMetadatoExpedienteSeleccionado(int indexMetadatoExpedienteSeleccionado) {
        this.indexMetadatoExpedienteSeleccionado = indexMetadatoExpedienteSeleccionado;
    }

    /**
     * @return the indexMetadatoTipoDocumentalSeleccionado
     */
    public int getIndexMetadatoTipoDocumentalSeleccionado() {
        return indexMetadatoTipoDocumentalSeleccionado;
    }

    /**
     * @param indexMetadatoTipoDocumentalSeleccionado the indexMetadatoTipoDocumentalSeleccionado to set
     */
    public void setIndexMetadatoTipoDocumentalSeleccionado(int indexMetadatoTipoDocumentalSeleccionado) {
        this.indexMetadatoTipoDocumentalSeleccionado = indexMetadatoTipoDocumentalSeleccionado;
    }
}
