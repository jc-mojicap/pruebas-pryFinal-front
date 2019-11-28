/*
* Archivo: RestaurarClaveController.java
* Fecha creacion = 07/03/2017
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
package co.com.grupoasd.documental.presentacion.controller.identificacion;

import java.util.List;

import javax.ws.rs.NotFoundException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.catalogo.model.Empresa;
import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;
import co.com.grupoasd.documental.presentacion.service.autenticacion.AutenticacionServiceFactory;
import co.com.grupoasd.documental.presentacion.service.autenticacion.iface.AutenticacionService;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.EmpresaService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.UsuarioService;

public final class RecuperarClaveController {
	
	@Wire
    private Textbox txtUsuario;
    @Wire
    private Textbox txtCorreo;
    @Wire
    private Listbox listboxDatosBasicos;
    @Wire
    private Window windowRecuperarClave;
    @Wire
    private Combobox combo;
    
    private Empresa empresaSeleccionada;
    private List<Empresa> empresas;
    
    //Servicios
    private AutenticacionService autenticacionService;
    private EmpresaService empresaService;
    private UsuarioService usuarioService;

    /**
     * Inicializacion del controller.
     */
    @Init
    public void init(@ContextParam(ContextType.SESSION) Session session) {
    	this.autenticacionService = AutenticacionServiceFactory.getAutenticacionService();
    	this.empresaService = CatalogoServiceFactory.getEmpresaService();
    	this.usuarioService = CatalogoServiceFactory.getUsuarioService();
    	empresas = empresaService.listar(false);
    }
    
    /**
     * Metodo que se ejecuta despues de que ZK crea los componentes de la vista.
     * 
     * @param view
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
    
    /**
     * Solicita la recuperacion de la clave.
     */
    @Command
    public void enviarCorreoRecuperacion(){
        
        if(validarCampos()){
            try{
                Usuario user = usuarioService.buscarPorEmpresaYNombreUsuarioYCorreo(empresaSeleccionada.getEmpresaId(), txtUsuario.getValue(), txtCorreo.getValue());
                boolean resultado = autenticacionService.enviarCorreoRecuperacion(empresaSeleccionada.getEmpresaId(), user.getNombreUsuario());
                if(resultado){
                    Messagebox.show(Labels.getLabel("autenticacion.mensaje.correoEnviado"), Labels.getLabel("commons.titulo.informacion"), Messagebox.OK, Messagebox.INFORMATION);
                    windowRecuperarClave.detach();
                }else{
                    String mensaje = Labels.getLabel("autenticacion.mensaje.correoNoEnviado");
                    String tipo = "error";
                    Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
                }
            }catch (NotFoundException e) {
                String mensaje = Labels.getLabel("autenticacion.mensaje.correoNoEnviado");
                String tipo = "error";
                Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
            }
        }
    }
    
    /**
     * Valida que los campos obligatorios para recuperar clave hayan sido diligenciados.
     * @return <code>true</code> si los campos fueron diligenciados, lanza una WrongValueException en caso contrario.
     */
    private boolean validarCampos(){
        
        if (txtUsuario.getValue().trim().isEmpty()) {
            throw new WrongValueException(txtUsuario, Labels.getLabel("commons.tooltip.campoRequerido"));
        }
        if (txtCorreo.getValue().trim().isEmpty()) {
            throw new WrongValueException(txtCorreo, Labels.getLabel("commons.tooltip.campoRequerido"));
        }
        if (combo.getSelectedItem() == null || !(combo.getValue() != null)) {
            throw new WrongValueException(combo, Labels.getLabel("commons.tooltip.campoRequerido"));
        }
        return true;
    }
    
    /**
     * @return the empresaSeleccionada
     */
    public Empresa getEmpresaSeleccionada() {
        return empresaSeleccionada;
    }

    /**
     * @param empresaSeleccionada the empresaSeleccionada to set
     */
    public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
        this.empresaSeleccionada = empresaSeleccionada;
    }

    /**
     * @return the empresas
     */
    public List<Empresa> getEmpresas() {
        return empresas;
    }

    /**
     * @param empresas the empresas to set
     */
    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
}
