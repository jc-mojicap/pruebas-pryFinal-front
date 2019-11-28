/*
* Archivo: AgregarTerceroController.java
* Fecha creacion = 22/04/2017
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
package co.com.grupoasd.documental.presentacion.controller.correspondencia;

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
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.correspondencia.model.CorTercero;
import co.com.grupoasd.documental.presentacion.service.correspondencia.CorrespondenciaServiceFactory;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTerceroService;

public class AgregarTerceroController {

    @Wire
    private Window windowAddTerceros;
    
    private List<CorTercero> terceros = new ArrayList<>();
    private List<CorTercero> tercerosSeleccionados = new ArrayList<>();
    
    private String callback;
    
    private Integer empresaId;
    
    /**
     * Variable para determinar si la respuesta la devuelve como MVC o MVVC
     */
    private boolean isRespuestaMvc;
    
    //Servicios
    
    private CorTerceroService corTerceroService;
    
    @Init
    public void init(@ExecutionArgParam("callback") String callback, @ExecutionArgParam("empresaId") Integer empresaId, 
    		@ExecutionArgParam("terceros") List<CorTercero> tercerosSeleccionados, @ExecutionArgParam("isRespuestaMvc") boolean isRespuestaMvc) {
        this.corTerceroService = CorrespondenciaServiceFactory.getCorTerceroService();
        setCallback(callback);
        setEmpresaId(empresaId);
        setTercerosSeleccionados(tercerosSeleccionados);
        this.isRespuestaMvc = isRespuestaMvc;
    }
    
    /**
     * Método que se ejecuta después de que ZK crea los componentes de la vista.
     * 
     * @param view
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
        cargarTerceros("");
    }
    
    @NotifyChange({"terceros", "tercerosSeleccionados"})
    @Command
    public void cargarTerceros(@BindingParam("arg1") String valor){
        try{
            terceros.clear();
            terceros.addAll(corTerceroService.listarPorNumeroNombreResponsableDireccionMunicipio(empresaId, valor));
        }catch (Exception e) {
            String mensaje = Labels.getLabel("commons.mensaje.consultaNoRealizada");
            String tipo = "error";
            
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        }
    }
    
    @Command
    public void aceptarSeleccion(){
        try {
            Map<String, Object> resultado = new HashMap<>();
            tercerosSeleccionados.sort((o1, o2) -> o1.getTerceroId().compareTo(o2.getTerceroId()));
            
            if (isRespuestaMvc) {
				Events.sendEvent(callback, windowAddTerceros.getParent(), tercerosSeleccionados);
            } else {
				resultado.put("tercerosElegidos", tercerosSeleccionados);
				BindUtils.postGlobalCommand(null, null, callback, resultado);
			}
            windowAddTerceros.detach();
        } catch (Exception e) {
            String mensaje = Labels.getLabel("commons.mensaje.informacionNoRetornada");
            String tipo = "error";
            
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        }
    }

    /**
     * @return the terceros
     */
    public List<CorTercero> getTerceros() {
        return terceros;
    }

    /**
     * @param terceros the terceros to set
     */
    public void setTerceros(List<CorTercero> terceros) {
        this.terceros = terceros;
    }

    /**
     * @return the tercerosSeleccionados
     */
    public List<CorTercero> getTercerosSeleccionados() {
        return tercerosSeleccionados;
    }

    /**
     * @param tercerosSeleccionados the tercerosSeleccionados to set
     */
    public void setTercerosSeleccionados(List<CorTercero> tercerosSeleccionados) {
        this.tercerosSeleccionados = tercerosSeleccionados;
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
     * @return the empresaId
     */
    public Integer getEmpresaId() {
        return empresaId;
    }

    /**
     * @param empresaId the empresaId to set
     */
    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }
    
}
