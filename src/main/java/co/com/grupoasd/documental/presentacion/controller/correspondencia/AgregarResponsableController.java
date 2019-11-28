/*
* Archivo: AgregarResponsableController.java
* Fecha creacion = 02/05/2017
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

import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.UsuarioService;

public class AgregarResponsableController {

    @Wire
    private Window winAddResponsables;

    private List<Usuario> usuarios = new ArrayList<>();
    private List<Usuario> usuariosSeleccionados = new ArrayList<>();
    private String callback;
    private Integer empresaId;

    /**
     * Variable para determinar si la respuesta la devuelve como MVC o MVVC
     */
    private boolean isRespuestaMvc;

    // Servicios
    private UsuarioService usuarioService;

    @Init
    public void init(@ExecutionArgParam("callback") String callback, @ExecutionArgParam("empresaId") Integer empresaId,
            @ExecutionArgParam("usuarios") List<Usuario> usuariosSeleccionados, @ExecutionArgParam("isRespuestaMvc") boolean isRespuestaMvc) {
        this.usuarioService = CatalogoServiceFactory.getUsuarioService();
        setCallback(callback);
        setEmpresaId(empresaId);
        setUsuariosSeleccionados(usuariosSeleccionados);
        this.isRespuestaMvc = isRespuestaMvc;
    }

    /**
     * Método que se ejecuta después de que ZK crea los componentes de la vista.
     * 
     * @param view
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        cargarResponsables("");
    }

    @NotifyChange({ "usuarios", "usuariosSeleccionados" })
    @Command
    public void cargarResponsables(@BindingParam("arg1") String valor) {
        try {
            usuarios.clear();
            usuarios.addAll(usuarioService.listarPorNumeroNombreRolArea(empresaId, valor));
        } catch (Exception e) {
            String mensaje = Labels.getLabel("commons.mensaje.consultaNoRealizada");
            String tipo = "error";

            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        }
    }

    @Command
    public void aceptarSeleccion() {
        try {
            Map<String, Object> resultado = new HashMap<>();
            usuariosSeleccionados.sort((o1, o2) -> o1.getUsuarioId().compareTo(o2.getUsuarioId()));

            if (isRespuestaMvc) {
                Events.sendEvent(callback, winAddResponsables.getParent(), usuariosSeleccionados);
            } else {
                resultado.put("usuariosElegidos", usuariosSeleccionados);
                BindUtils.postGlobalCommand(null, null, callback, resultado);
            }
            winAddResponsables.detach();
        } catch (Exception e) {
            String mensaje = Labels.getLabel("commons.mensaje.informacionNoRetornada");
            String tipo = "error";
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        }
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios
     *            the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the usuariosSeleccionados
     */
    public List<Usuario> getUsuariosSeleccionados() {
        return usuariosSeleccionados;
    }

    /**
     * @param usuariosSeleccionados
     *            the usuariosSeleccionados to set
     */
    public void setUsuariosSeleccionados(List<Usuario> usuariosSeleccionados) {
        this.usuariosSeleccionados = usuariosSeleccionados;
    }

    /**
     * @return the callback
     */
    public String getCallback() {
        return callback;
    }

    /**
     * @param callback
     *            the callback to set
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
     * @param empresaId
     *            the empresaId to set
     */
    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

}
