/*
* Archivo: AgregarMetadatosController.java
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

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.trd.model.TrdMetadato;
import co.com.grupoasd.documental.presentacion.service.trd.TrdServiceFactory;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdMetadatoService;

public class AgregarMetadatosController {

    @Wire
    private Window winAddMetadatos;
    
    private List<TrdMetadato> metadatos = new ArrayList<>();
    private List<TrdMetadato> metadatosSeleccionados = new ArrayList<>();
    private String callback;
    private boolean isRespuestaMvc;
    private boolean administrarMetadatos;
    
    private TrdMetadatoService trdMetadatoService;
    
    /**
     * Inicialización del controller.
     */
    @Init
    public void init(@ExecutionArgParam("callback") String callback, @ExecutionArgParam("metadatos") List<TrdMetadato> metadatosSeleccionados, @ExecutionArgParam("isRespuestaMvc") boolean isRespuestaMvc) {
        this.trdMetadatoService = TrdServiceFactory.getTrdMetadatoService();
        setCallback(callback);
        setMetadatosSeleccionados(metadatosSeleccionados);
        this.isRespuestaMvc = isRespuestaMvc;
        //TODO agregar verificacion para saber si el usuario tiene permisos de administrar metadatos.
        this.administrarMetadatos = true;
    }
    
    /**
     * Método que se ejecuta después de que ZK crea los componentes de la vista.
     * 
     * @param view
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        cargarMetadatos("");
    }
    
    @NotifyChange({ "metadatos", "metadatosSeleccionados" })
    @Command
    @GlobalCommand
    public void cargarMetadatos(@BindingParam("arg1") String valor) {
        try {
            metadatos.clear();
            metadatos.addAll(trdMetadatoService.listarPorIdTipoDatoNombre(valor));
            Map<String, Object> params = new HashMap<>();
            params.put("arg1", metadatos);
            BindUtils.postGlobalCommand(null, null, "verificarMetadatos", params);
            
            List<TrdMetadato> temporalesSeleccionados = new ArrayList<>();
            
            if (metadatosSeleccionados != null && !metadatosSeleccionados.isEmpty()){
                for (TrdMetadato metadatoSeleccionado : metadatosSeleccionados) {
                    if (metadatos.contains(metadatoSeleccionado)){
                        TrdMetadato temporal = metadatos.get(metadatos.indexOf(metadatoSeleccionado));
                        if (temporal.toString().equals(metadatoSeleccionado.toString())){
                            temporalesSeleccionados.add(metadatoSeleccionado);
                        } else {
                            temporalesSeleccionados.add(temporal);
                        }
                        metadatosSeleccionados = temporalesSeleccionados;
                    } else {
                        System.err.println("se fue por otro lado");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String mensaje = Labels.getLabel("commons.mensaje.consultaNoRealizada");
            String tipo = "error";

            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        }
    }
    
    @Command
    public void aceptarSeleccion() {
        try {
            Map<String, Object> resultado = new HashMap<>();
            metadatosSeleccionados.sort((o1, o2) -> o1.getMetadatoId().compareTo(o2.getMetadatoId()));

            if (isRespuestaMvc) {
                Events.sendEvent(callback, winAddMetadatos.getParent(), metadatosSeleccionados);
            } else {
                resultado.put("metadatosElegidos", metadatosSeleccionados);
                BindUtils.postGlobalCommand(null, null, callback, resultado);
            }
            winAddMetadatos.detach();
        } catch (Exception e) {
            String mensaje = Labels.getLabel("commons.mensaje.informacionNoRetornada");
            String tipo = "error";
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        }
    }
    
    @Command
    public void cancelar(){
        String preguntaConfirmacion = Labels.getLabel("trd.metadatos.mensaje.confirmarSalir");
        String titulo = Labels.getLabel("commons.titulo.confirmacion");
        Messagebox.show(preguntaConfirmacion, titulo,
                Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                new EventListener<Event>() {

                    @Override
                    public void onEvent(Event evnt) throws Exception {
                        if (Messagebox.ON_YES.equals(evnt.getName())) {
                            winAddMetadatos.detach();
                        }
                    }
                });
    }
    
    @Command
    public void administrarMetadatos(){
        Window window = (Window) Executions.createComponents("/administracion/sistema/administrar_metadatos.zul", winAddMetadatos, null);
        window.doModal();
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
     * @return the metadatosSeleccionados
     */
    public List<TrdMetadato> getMetadatosSeleccionados() {
        return metadatosSeleccionados;
    }

    /**
     * @param metadatosSeleccionados the metadatosSeleccionados to set
     */
    public void setMetadatosSeleccionados(List<TrdMetadato> metadatosSeleccionados) {
        this.metadatosSeleccionados = metadatosSeleccionados;
    }

    /**
     * @return the callback
     */
    public String getCallback() {
        return callback;
    }

    /**
     * @param callback the callback to set
     */
    public void setCallback(String callback) {
        this.callback = callback;
    }

    /**
     * @return the isRespuestaMvc
     */
    public boolean isRespuestaMvc() {
        return isRespuestaMvc;
    }

    /**
     * @param isRespuestaMvc the isRespuestaMvc to set
     */
    public void setRespuestaMvc(boolean isRespuestaMvc) {
        this.isRespuestaMvc = isRespuestaMvc;
    }

    /**
     * @return the administrarMetadatos
     */
    public boolean isAdministrarMetadatos() {
        return administrarMetadatos;
    }

    /**
     * @param administrarMetadatos the administrarMetadatos to set
     */
    public void setAdministrarMetadatos(boolean administrarMetadatos) {
        this.administrarMetadatos = administrarMetadatos;
    }

}
