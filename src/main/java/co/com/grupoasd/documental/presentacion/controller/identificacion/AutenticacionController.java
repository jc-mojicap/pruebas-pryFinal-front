/*
 * Archivo: AutenticacionController.java
 * Fecha creacion: 28/02/2017
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
package co.com.grupoasd.documental.presentacion.controller.identificacion;

import java.util.List;

import javax.management.openmbean.InvalidKeyException;
import javax.ws.rs.NotFoundException;

import org.keycloak.authorization.client.util.HttpResponseException;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.catalogo.model.Empresa;
import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.service.autenticacion.AutenticacionServiceFactory;
import co.com.grupoasd.documental.presentacion.service.autenticacion.iface.AutenticacionService;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.AccionService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.EmpresaService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.UsuarioService;
import io.jsonwebtoken.Claims;

/**
 * Controlador de la interfaz de autenticacion.
 * 
 * @author Juan Camilo Mojica
 */
public final class AutenticacionController {

    @Wire
    private Textbox txtUsuario;
    @Wire
    private Textbox txtContrasena;
    @Wire
    private Combobox comboEmpresa;

    private Empresa empresaSeleccionada;
    private List<Empresa> empresas;

    // Servicios
    private EmpresaService empresaService;
    private AutenticacionService autenticacionService;
    private UsuarioService usuarioService;
    private AccionService accionService;

    /**
     * Inicializacion del controller.
     */
    @Init
    public void init() {
        this.empresaService = CatalogoServiceFactory.getEmpresaService();
        this.autenticacionService = AutenticacionServiceFactory.getAutenticacionService();
        this.usuarioService = CatalogoServiceFactory.getUsuarioService();
        this.accionService = CatalogoServiceFactory.getAccionService();

        empresas = empresaService.listar(false);
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

    /**
     * Valida el acceso al sistema.
     */
    @Command
    public void validarAcceso() {

        if (validarCampos()) {
            try {
                Usuario user = usuarioService.buscarPorEmpresaYNombreUsuario(empresaSeleccionada.getEmpresaId(), txtUsuario.getValue());
                if (user != null){
                    Claims claims = autenticacionService.autenticarKeycloak(empresaSeleccionada,
                            txtUsuario.getValue(), txtContrasena.getValue());
                    Usuario usuarioAutenticado = usuarioService.buscarPorEmpresaYCodigoUsuario(empresaSeleccionada.getEmpresaId(), claims.getSubject());
                    SesionVo sesionVo = new SesionVo();
                    sesionVo.setEmpresaId(usuarioAutenticado.getEmpresaId());
                    sesionVo.setEmpresa(usuarioAutenticado.getNombreEmpresa());
                    sesionVo.setRolId(usuarioAutenticado.getRolId());
                    sesionVo.setRol(usuarioAutenticado.getNombreRol());
                    sesionVo.setUsuarioId(usuarioAutenticado.getUsuarioId());
                    sesionVo.setUsuario(usuarioAutenticado.getNombreUsuario());
                    sesionVo.setPrimerApellido(usuarioAutenticado.getPrimerApellido());
                    sesionVo.setSegundoApellido(usuarioAutenticado.getSegundoApellido());
                    sesionVo.setPrimerNombre(usuarioAutenticado.getPrimerNombre());
                    sesionVo.setSegundoNombre(usuarioAutenticado.getSegundoNombre());
                    sesionVo.setIdentificacion(usuarioAutenticado.getNumeroIdentificacion());
                    sesionVo.setIpCliente(Executions.getCurrent().getLocalAddr());
                    sesionVo.setIpServidor(Executions.getCurrent().getRemoteAddr());
                    
                    sesionVo.setAcciones(accionService.listarPorUsuarioYRol(usuarioAutenticado.getRolId(), usuarioAutenticado.getUsuarioId()));
                    
                    SessionUtil.iniciarSesion(sesionVo, claims.getSubject());
                    
                    comboEmpresa.setSelectedIndex(-1);
                    txtContrasena.setRawValue(null);
                    txtUsuario.setRawValue(null);
                    Clients.clearWrongValue(new Component[] { comboEmpresa, txtContrasena, txtUsuario });
                    Executions.sendRedirect("/index.zul");
                } else {
                    String mensaje = Labels.getLabel("autenticacion.mensaje.credencialesIncorrectas");
                    String tipo = "error";
                    Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
                }
            } catch (WrongValueException e) {
                throw e;
            } catch (IndexOutOfBoundsException | HttpResponseException | NotFoundException e) {
                String mensaje = Labels.getLabel("autenticacion.mensaje.credencialesIncorrectas");
                String tipo = "error";
                Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
            } catch (InvalidKeyException e) {
                Messagebox.show(Labels.getLabel("autenticacion.mensaje.usuarioBloqueado"), Labels.getLabel("commons.titulo.informacion"),
                        Messagebox.OK, Messagebox.INFORMATION,
                        new EventListener<Event>() {

                            @Override
                            public void onEvent(Event evnt) throws Exception {
                                String mensaje = Labels.getLabel("autenticacion.mensaje.usuarioBloqueadoInfo");
                                String tipo = "info";
                                Clients.showNotification(mensaje, tipo, null, "middle_center", 20000, true);
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
                String mensaje = Labels.getLabel("commons.mensaje.errorGeneral") + " " + e;
                String tipo = "error";
                Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
            }
        }
    }

    private boolean validarCampos() {
        try {
            if(txtUsuario.getValue().trim().isEmpty()){
                throw new WrongValueException(txtUsuario, Labels.getLabel("commons.tooltip.campoRequerido"));
            }
            if(txtContrasena.getValue().trim().isEmpty()){
                throw new WrongValueException(txtContrasena, Labels.getLabel("commons.tooltip.campoRequerido"));
            }
            if(comboEmpresa.getSelectedItem() == null || !(comboEmpresa.getValue() != null)){
                throw new WrongValueException(comboEmpresa, Labels.getLabel("commons.tooltip.campoRequerido"));
            }
            return true;
        } catch (WrongValueException e) {
            throw e;
        }
    }

    /**
     * Abre la ventana para solicitar la recuperacion de la clave.
     */
    @Command
    public void recuperarClave() {
        comboEmpresa.setSelectedIndex(-1);
        txtContrasena.setRawValue(null);
        txtUsuario.setRawValue(null);
        Clients.clearWrongValue(new Component[] { comboEmpresa, txtContrasena, txtUsuario });
        Window window = (Window) Executions.createComponents("/identificacion/recuperar_clave.zul", null, null);
        window.doModal();
    }

    /**
     * getEmpresaSeleccionada.
     * 
     * @return <code>Empresa</code> seleccionada en el combobox
     */
    public Empresa getEmpresaSeleccionada() {
        return empresaSeleccionada;
    }

    /**
     * setEmpresaSeleccionada.
     * 
     * @param empresaSeleccionada
     *            la empresa seleccionada en el combobox
     */
    public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
        this.empresaSeleccionada = empresaSeleccionada;
    }

    /**
     * getEmpresas.
     * 
     * @return <code>List&lt;Empresa&gt;</code>
     */
    public List<Empresa> getEmpresas() {
        return empresas;
    }

    /**
     * setEmpresas.
     * 
     * @param empresas
     */
    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
}