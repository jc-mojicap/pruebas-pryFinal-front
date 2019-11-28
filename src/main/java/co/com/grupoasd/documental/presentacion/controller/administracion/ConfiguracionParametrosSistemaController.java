/*
* Archivo: ConfiguracionParametrosSistemaController.java
* Fecha creacion = 06/06/2017
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
import java.util.Base64;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.parametrizacion.model.ParametroAplicacion;
import co.com.grupoasd.documental.cliente.trd.model.TrdCodificacion;
import co.com.grupoasd.documental.cliente.trd.model.TrdEstructura;
import co.com.grupoasd.documental.cliente.trd.model.TrdTipoDato;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.service.parametrizacion.ParametroAplicacionServiceFactory;
import co.com.grupoasd.documental.presentacion.service.parametrizacion.iface.ParametroAplicacionService;
import co.com.grupoasd.documental.presentacion.service.trd.TrdServiceFactory;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdCodificacionService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdEstructuraService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdTipoDatoService;

public class ConfiguracionParametrosSistemaController {

    @Wire
    private Listbox listCodificacion;
    @Wire
    private Window windowConfigurarParametros;
    @Wire
    private Textbox txtRutaInicial;
    
    //Servicios
    ParametroAplicacionService parametroAplicacionService;
    TrdTipoDatoService trdTipoDatoService;
    TrdEstructuraService trdEstructuraService;
    TrdCodificacionService trdCodificacionService;
    
    private ParametroAplicacion rutaInicialApp;
    private List<TrdTipoDato> tipoDatos = new ArrayList<>();
    private List<TrdEstructura> estructuras = new ArrayList<>();
    private List<TrdCodificacion> codificaciones = new ArrayList<>();
    
    private final String[] formatosImagen = {"PNG", "JPG", "GIF", "TIFF", "BMP"};
    
    private boolean firstTime = true;
    private String logoBase64;
    
    private static final String ID_RUTA_INICIAL_APLICACION = "rutaInicial";
    private static final String ID_LOGO_EMPRESA = "logoEmpresa";
    
    private Integer idEmpresa;
    
    /**
     * Inicialización del controller.
     */
    @Init
    public void init() {
        
        // Iniciar todos los servicios.
        this.parametroAplicacionService = ParametroAplicacionServiceFactory.getParametroAplicacionService();
        this.trdTipoDatoService = TrdServiceFactory.getTrdTipoDatoService();
        this.trdEstructuraService = TrdServiceFactory.getTrdEstructuraService();
        this.trdCodificacionService = TrdServiceFactory.getTrdCodificacionService();
        
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
        listCodificacion.invalidate();
    }
    
    private void cargarDatosIniciales(){
        rutaInicialApp = parametroAplicacionService.obtenerPorIdYEmpresa(ID_RUTA_INICIAL_APLICACION, idEmpresa);
        tipoDatos.addAll(trdTipoDatoService.listarTodos());
        estructuras.addAll(trdEstructuraService.listarTodos());
        codificaciones.addAll(trdCodificacionService.listarPorEmpresaId(idEmpresa));
        BindUtils.postNotifyChange(null, null, ConfiguracionParametrosSistemaController.this, "*");
        listCodificacion.renderAll();
    }
    
    @Command
    public void cargarLogo(@BindingParam("arg1") UploadEvent event){
        Media media = event.getMedia();
        
        String mensaje = "";
        String tipo = "";
        
        if (verificarTipoArchivo(media.getName().substring(media.getName().lastIndexOf(".")+1))){
            byte bytes[] = media.getByteData();
            logoBase64 = Base64.getEncoder().encodeToString(bytes);
            mensaje = Labels.getLabel("commons.mensaje.imagenCargada");
            tipo = "info";
        } else {
            mensaje = Labels.getLabel("commons.mensaje.imagenNoCargada");
            tipo = "error";
        }
        Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        
    }
    
    private boolean verificarTipoArchivo(String formato){
        for (String format : formatosImagen) {
            if (format.equalsIgnoreCase(formato)){
                return true;
            }
        }
        
        return false;
    }
    
    @Command
    public void guardarParametrosSistema(){
        try {
            if (txtRutaInicial.getValue().isEmpty()){
                throw new WrongValueException(txtRutaInicial, Labels.getLabel("commons.tooltip.campoRequerido"));
            }
            List<ParametroAplicacion> parametros = new ArrayList<>();
            parametros.add(rutaInicialApp);
            if (logoBase64 != null){
                ParametroAplicacion logo = new ParametroAplicacion(ID_LOGO_EMPRESA, idEmpresa, logoBase64, "Logo de la empresa");
                parametros.add(logo);
            }
            List<ParametroAplicacion> resultado = parametroAplicacionService.guardarParametros(SessionUtil.getAuthToken(Sessions.getCurrent()), parametros);
            String mensaje = Labels.getLabel("commons.mensaje.configuracionGuardada");
            String tipo = "info";
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        } catch (WrongValueException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            String mensaje = Labels.getLabel("commons.mensaje.configuracionNoGuardada");
            String tipo = "error";
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        }
        
    }
    
    @Command
    public void seleccionarCamposIniciales(){
        //Solo se debe ejecutar esta lógica la primera vez que se selecciona la ventana
        //Solución debido a que los listbox no tienen metodo doAfterRender
        if (firstTime){
            List<Listitem> items = listCodificacion.getItems();
            for (Listitem listitem : items) {
                TrdEstructura estructura = new TrdEstructura(obtenerIdEstructura(((Listcell)listitem.getChildren().get(1)).getLabel()));
                for (TrdCodificacion cod : codificaciones) {
                    if (cod.getEstructuraId() == estructura.getEstructuraId()){
                        listitem.setSelected(cod.isAplicar());
                        if (cod.isAplicar()){
                            ((Combobox)listitem.getChildren().get(2).getFirstChild()).setSclass("obligatorio");
                            ((Spinner)listitem.getChildren().get(3).getFirstChild()).setSclass("obligatorio");
                        }
                        if (cod.getTipoDatoId() != null){
                            ((Combobox)listitem.getChildren().get(2).getFirstChild()).setSelectedIndex(obtenerTipoDatoSeleccionado(cod.getTipoDatoId()));
                        }
                        if (cod.getLongitud() != null){
                            ((Spinner)listitem.getChildren().get(3).getFirstChild()).setValue(cod.getLongitud());
                        }
                        ((Spinner)listitem.getChildren().get(3).getFirstChild()).setDisabled((!cod.isAplicar()));
                        ((Combobox)listitem.getChildren().get(2).getFirstChild()).setDisabled(!cod.isAplicar());
                    }
                }
            }
            listCodificacion.invalidate();
        }
        firstTime = false;
    }
    
    private int obtenerTipoDatoSeleccionado(int id){
        
        for (int i = 0; i < tipoDatos.size(); i++){
            if (tipoDatos.get(i).getTipoDatoId() == id){
                return i;
            }
        }
        return -1;
    }
    
    @Command
    public void guardarCambiosCodificacion(){
        try {
            List<Listitem> items = listCodificacion.getItems();
            List<TrdCodificacion> codificacionesActualizar = new ArrayList<>();
            for (Listitem listitem : items) {
                TrdCodificacion codificacion = new TrdCodificacion();
                codificacion.setAplicar(listitem.isSelected());
                codificacion.setEmpresaId(idEmpresa);
                
                if (listitem.isSelected()){
                    if (((Combobox)listitem.getChildren().get(2).getFirstChild()).getSelectedIndex() == -1){
                        throw new WrongValueException((Component)listitem.getChildren().get(2).getFirstChild(), Labels.getLabel("commons.tooltip.campoRequerido"));
                    }
                    if (((Spinner)listitem.getChildren().get(3).getFirstChild()).getValue() == null || ((Spinner)listitem.getChildren().get(3).getFirstChild()).getValue()<1 || ((Spinner)listitem.getChildren().get(3).getFirstChild()).getValue()>100){
                        throw new WrongValueException((Component)listitem.getChildren().get(3).getFirstChild(), Labels.getLabel("commons.tooltip.campoRequerido"));
                    }
                    codificacion.setEstructuraId(obtenerIdEstructura(((Listcell)listitem.getChildren().get(1)).getLabel()));
                    codificacion.setTipoDatoId(((Comboitem)((Combobox)listitem.getChildren().get(2).getFirstChild()).getSelectedItem()).getValue());
                    codificacion.setLongitud(((Spinner)listitem.getChildren().get(3).getFirstChild()).getValue());
                } else {
                    codificacion.setEstructuraId(obtenerIdEstructura(((Listcell)listitem.getChildren().get(1)).getLabel()));
                    codificacion.setTipoDatoId(null);
                    codificacion.setLongitud(null);
                }
                codificacionesActualizar.add(codificacion);
            }
            codificaciones.clear();
            codificaciones.addAll(trdCodificacionService.guardarCodificaciones(SessionUtil.getAuthToken(Sessions.getCurrent()), codificacionesActualizar));
            listCodificacion.invalidate();
            
            String mensaje = Labels.getLabel("commons.mensaje.configuracionGuardada");
            String tipo = "info";
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        } catch (WrongValueException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            String mensaje = Labels.getLabel("commons.mensaje.configuracionNoGuardada");
            String tipo = "error";
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        }
        
    }
    
    public int obtenerIdEstructura(String nombre){
        for (TrdEstructura trdEstructura : estructuras) {
            if(nombre.equals(trdEstructura.getNombre())){
                return trdEstructura.getEstructuraId();
            }
        }
        return 0;
    }
    
    @Command
    public void verificarSeleccionados(){
        List<Listitem> items = listCodificacion.getItems();
        for (Listitem listitem : items) {
            if (!listitem.isSelected()){
                ((Combobox)listitem.getChildren().get(2).getFirstChild()).clearErrorMessage();
                ((Spinner)listitem.getChildren().get(3).getFirstChild()).clearErrorMessage();
                ((Combobox)listitem.getChildren().get(2).getFirstChild()).setSelectedIndex(-1);
                ((Spinner)listitem.getChildren().get(3).getFirstChild()).setRawValue(null);
                ((Combobox)listitem.getChildren().get(2).getFirstChild()).setSclass("");
                ((Spinner)listitem.getChildren().get(3).getFirstChild()).setSclass("");
                ((Combobox)listitem.getChildren().get(2).getFirstChild()).setDisabled(true);
                ((Spinner)listitem.getChildren().get(3).getFirstChild()).setDisabled(true);
            } else {
                ((Combobox)listitem.getChildren().get(2).getFirstChild()).setDisabled(false);
                ((Spinner)listitem.getChildren().get(3).getFirstChild()).setDisabled(false);
                ((Combobox)listitem.getChildren().get(2).getFirstChild()).setSclass("obligatorio");
                ((Spinner)listitem.getChildren().get(3).getFirstChild()).setSclass("obligatorio");
            }
        }
    }
    
    @Command
    public void cancelarCambios(){
        windowConfigurarParametros.detach();
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
     * @return the estructuras
     */
    public List<TrdEstructura> getEstructuras() {
        return estructuras;
    }

    /**
     * @param estructuras the estructuras to set
     */
    public void setEstructuras(List<TrdEstructura> estructuras) {
        this.estructuras = estructuras;
    }

    /**
     * @return the codificaciones
     */
    public List<TrdCodificacion> getCodificaciones() {
        return codificaciones;
    }

    /**
     * @param codificaciones the codificaciones to set
     */
    public void setCodificaciones(List<TrdCodificacion> codificaciones) {
        this.codificaciones = codificaciones;
    }

    /**
     * @return the rutaInicialApp
     */
    public ParametroAplicacion getRutaInicialApp() {
        return rutaInicialApp;
    }

    /**
     * @param rutaInicialApp the rutaInicialApp to set
     */
    public void setRutaInicialApp(ParametroAplicacion rutaInicialApp) {
        this.rutaInicialApp = rutaInicialApp;
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
}
