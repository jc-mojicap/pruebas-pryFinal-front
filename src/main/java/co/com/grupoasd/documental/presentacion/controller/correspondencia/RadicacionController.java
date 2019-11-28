/*
 * Archivo: RadicacionController.java
* Fecha creacion = 10/03/2017
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

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.NonReadableChannelException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;

import org.apache.commons.lang3.StringUtils;
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
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;

import co.com.grupoasd.documental.cliente.autorizacion.AccionApp;
import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.cliente.catalogo.model.Serie;
import co.com.grupoasd.documental.cliente.catalogo.model.Subserie;
import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorCanal;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorSecuencia;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTercero;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTipoEmbalaje;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTransportadora;
import co.com.grupoasd.documental.cliente.correspondencia.model.RotuloInfo;
import co.com.grupoasd.documental.cliente.parametrizacion.model.ParametroAplicacion;
import co.com.grupoasd.documental.presentacion.comun.vo.CanalRecepcionCorrespondencia;
import co.com.grupoasd.documental.presentacion.comun.vo.EstadosRadicado;
import co.com.grupoasd.documental.presentacion.comun.vo.TipoRadicado;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.controller.util.Reportes;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.Utils;
import co.com.grupoasd.documental.presentacion.controller.util.DateUtils;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.AreaService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SerieService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SubserieService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.UsuarioService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.CorrespondenciaServiceFactory;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorCanalService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorSecuenciaService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTerceroService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTipoEmbalajeService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTransportadoraService;
import co.com.grupoasd.documental.presentacion.service.parametrizacion.ParametroAplicacionServiceFactory;
import co.com.grupoasd.documental.presentacion.service.parametrizacion.iface.ParametroAplicacionService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Controlador de la interfaz de radicación de correspondencia.
 * 
 * @author JuanMojica
 *
 */
public class RadicacionController {

    @Wire
    private Window windowRadicar;
    @Wire
    private Button btnCorreoCertificado;
    @Wire
    private Label lblFechaSistema;
    @Wire
    private Label lblSecuencia;
    @Wire
    private Checkbox checkAnexo;
    @Wire
    private Label lblRadicado;
    @Wire
    private Groupbox grbRemTercero;
    @Wire
    private Groupbox grbRemInterno;
    @Wire
    private Groupbox grbDestTercero;
    @Wire
    private Groupbox grbDestInterno;
    @Wire
    private Center center;
    @Wire
    private Combobox cmbTipoRadicacion;
    @Wire
    private Combobox cmbAreaProceso;
    @Wire
    private Combobox cmbSerie;
    @Wire
    private Combobox cmbSubserie;
    @Wire
    private Spinner spinCantFolios;
    @Wire
    private Combobox cmbCanal;
    @Wire
    private Textbox txtAsunto;
    @Wire
    private Textbox txtComentario;
    @Wire
    private Textbox txtGuia;
    @Wire
    private Combobox cmbCourier;
    @Wire
    private Datebox dtxFecha;
    @Wire
    private Combobox cmbTipoEmbalaje;
    @Wire
    private Spinner spinCantEmbalaje;
    @Wire
    private Popup popCorreoCert;
    @Wire
    private Textbox txtRadicadoAsociado;
    @Wire
    private Combobox cmbTercero;
    @Wire
    private Combobox cmbNroIdentificacion;
    @Wire
    private Textbox txtNombreFuncionario;
    @Wire
    private Textbox txtIdFuncionario;
    @Wire
    private Textbox txtRadRemitente;
    @Wire
    private Label lblDireccion;
    @Wire
    private Label lblCiudad;
    @Wire
    private Label lblResponsable;
    @Wire
    private Label lblCargoTercero;
    @Wire
    private Label lblDependencia;
    @Wire
    private Combobox cmbUsuario;
    @Wire
    private Label lblCargoUsuario;
    @Wire
    private Label lblAreaProcesoRemitente;
    @Wire
    private Image imgRotulo;
    @Wire
    private Listbox lstDestInternos;
    @Wire
    private Listbox lstDestTerceros;
    @Wire
    private Hbox hboxImage;
    
    
    private ListModelList<TipoRadicado> tipoCorrespondencia;

    private TipoRadicado tipoCorrespondenciaSeleccionada;

    private List<Area> areas = new ArrayList<Area>();

    private Area areaSeleccionada;

    private List<CorCanal> canalesRecepcionCorrespondencia = new ArrayList<CorCanal>();

    private CorCanal canalRecepcionCorrespondenciaSeleccionado;

    private ListModelList<Serie> series;

    private Serie serieSeleccionada;

    private ListModelList<Subserie> subseries;

    private Subserie subserieSeleccionada;

    private List<CorTransportadora> transportadoras = new ArrayList<CorTransportadora>();

    private CorTransportadora transportadoraSeleccionada;

    private List<CorTipoEmbalaje> tipoEmbalajes = new ArrayList<CorTipoEmbalaje>();

    private CorTipoEmbalaje tipoEmbalajeSeleccionado;

    private List<CorTercero> terceros = new ArrayList<CorTercero>();

    private CorTercero terceroSeleccionado;

    private List<Usuario> usuarios = new ArrayList<Usuario>();

    private Usuario usuarioSeleccionado;

    private CorSecuencia secuencia;

    private List<Usuario> usuariosDestinatario = new ArrayList<>();

    private Usuario usuarioDestinatarioSeleccionado;

    private List<CorTercero> tercerosDestinatario = new ArrayList<CorTercero>();

    private CorTercero terceroDestinatarioSeleccionado;

    // Servicios

    private SerieService serieService;

    private SubserieService subserieService;

    private CorCanalService corCanalService;

    private AreaService areaService;

    private CorTransportadoraService corTransportadoraService;

    private CorTipoEmbalajeService corTipoEmbalajeService;

    private CorTerceroService corTerceroService;

    private UsuarioService usuarioService;

    private CorRadicadoService corRadicadoService;

    private CorSecuenciaService corSecuenciaService;

    private static final char CODIGO_CORREO_CERTIFICADO = 'C';

    private Integer idUsuario;

    private Integer idEmpresa;
    
    private boolean administrarTerceros;
    
    private static final String ID_LOGO_EMPRESA = "logoEmpresa";
    
    private ParametroAplicacion logoEmpresa;
    
    ParametroAplicacionService parametroAplicacionService;

    /**
     * Inicialización del controller.
     */
    @Init
    public void init() {

        this.idUsuario = ((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getUsuarioId();
        this.idEmpresa = ((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getEmpresaId();
        
        // Iniciar todos los servicios.
        this.serieService = CatalogoServiceFactory.getSerieService();
        this.subserieService = CatalogoServiceFactory.getSubserieService();
        this.corCanalService = CorrespondenciaServiceFactory.getCorCanalService();
        this.areaService = CatalogoServiceFactory.getAreaService();
        this.usuarioService = CatalogoServiceFactory.getUsuarioService();
        this.corTransportadoraService = CorrespondenciaServiceFactory.getCorTransportadoraService();
        this.corTipoEmbalajeService = CorrespondenciaServiceFactory.getCorTipoEmbalajeService();
        this.corTerceroService = CorrespondenciaServiceFactory.getCorTerceroService();
        this.corRadicadoService = CorrespondenciaServiceFactory.getCorRadicadoService();
        this.corSecuenciaService = CorrespondenciaServiceFactory.getCorSecuenciaService();
        this.parametroAplicacionService = ParametroAplicacionServiceFactory.getParametroAplicacionService();
        
        tipoCorrespondencia = new ListModelList<>(TipoRadicado.values());
        
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

    /**
     * Carga la información inicial de una radicación: Secuencia, radicado,
     * fecha del sistema, canales de recepción, transportadoras, tipo de
     * embalajes, areas.
     */
    private void cargarDatosIniciales() {
        lblFechaSistema.setValue(MessageFormat.format("{0,date," + Labels.getLabel("commons.config.dateTimeFormatLong") + "}", new Date()));
        setSecuencia(corSecuenciaService.obtenerPorEmpresa(idEmpresa));
        lblSecuencia.setValue(String.valueOf(getSecuencia().getValor() + 1));
        lblRadicado.setValue("E-" + StringUtils.leftPad(String.valueOf(getSecuencia().getValor() + 1), 12, '0'));
        canalesRecepcionCorrespondencia.addAll(corCanalService.listar());
        transportadoras.addAll(corTransportadoraService.listar());
        tipoEmbalajes.addAll(corTipoEmbalajeService.listar());
        areas.addAll(areaService.listar(idEmpresa, false));
        administrarTerceros = SessionUtil.accionPermitida(AccionApp.ADMINISTRAR_TERCEROS.getId());
    }

    /**
     * Control para cerrar ventana de radicación.
     * 
     * @param event
     */
    @Command
    public void cerrarVentana(@BindingParam("arg1") Event event) {
        event.stopPropagation();
        String preguntaConfirmacion = Labels.getLabel("commons.mensaje.salirSinGuardar");
        String titulo = Labels.getLabel("commons.titulo.confirmacion");
        Messagebox.show(preguntaConfirmacion, titulo,
                Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                new EventListener<Event>() {

                    @Override
                    public void onEvent(Event evnt) throws Exception {
                        if (Messagebox.ON_YES.equals(evnt.getName())) {
                            windowRadicar.detach();
                        }
                    }
                });
    }

    /**
     * Control para limpiar los campos de la ventana.
     * 
     * @param conservarRemitente
     *            <code>true</code> si se desea conservar la información del
     *            remitente, <code>false</code> en caso contrario.
     */
    @GlobalCommand
    public void limpiarVentana(@BindingParam("conservar") Boolean conservarRemitente) {
        limpiarFormulario(conservarRemitente);
    }

    /**
     * Limpia los campos de la ventana.
     * 
     * @param conservarRemitente
     *            <code>true</code> si se desea conservar la información del
     *            remitente, <code>false</code> en caso contrario.
     */
    @NotifyChange({ "usuariosDestinatario", "tercerosDestinatario" })
    private void limpiarFormulario(boolean conservarRemitente) {
        Utils.clearSelectedValue(cmbTipoRadicacion);

        Utils.clearSelectedValue(cmbAreaProceso);
        Utils.clearSelectedValue(cmbSerie);
        Utils.clearSelectedValue(cmbSubserie);
        spinCantFolios.setValue(1);
        Utils.clearSelectedValue(cmbCanal);
        Utils.clearSelectedValue(txtGuia);
        Utils.clearSelectedValue(cmbCourier);
        dtxFecha.setValue(new Date());
        Utils.clearSelectedValue(cmbTipoEmbalaje);
        spinCantEmbalaje.setValue(1);
        Utils.clearSelectedValue(txtAsunto);
        Utils.clearSelectedValue(txtComentario);
        Utils.clearSelectedValue(txtRadicadoAsociado);
        imgRotulo.setSrc(null);

        if (!conservarRemitente) {
            Utils.clearSelectedValue(cmbTercero);
            Utils.clearSelectedValue(cmbNroIdentificacion);
            Utils.clearSelectedValue(txtNombreFuncionario);
            Utils.clearSelectedValue(txtIdFuncionario);
            Utils.clearSelectedValue(txtRadRemitente);
            lblDireccion.setValue(null);
            lblCiudad.setValue(null);
            lblResponsable.setValue(null);
            lblCargoTercero.setValue(null);
            lblDependencia.setValue(null);
            Utils.clearSelectedValue(cmbUsuario);
            lblCargoUsuario.setValue(null);
            lblAreaProcesoRemitente.setValue(null);
        }

        canalesRecepcionCorrespondencia.clear();
        setCanalRecepcionCorrespondenciaSeleccionado(null);
        series.clear();
        setSerieSeleccionada(null);
        subseries.clear();
        setSubserieSeleccionada(null);
        setTerceroDestinatarioSeleccionado(null);
        lstDestTerceros.clearSelection();
        setUsuarioDestinatarioSeleccionado(null);
        lstDestInternos.clearSelection();
        usuariosDestinatario.clear();
        tercerosDestinatario.clear();
        lstDestTerceros.getItems().clear();
        lstDestInternos.getItems().clear();

        cargarDatosIniciales();
    }

    /**
     * Verifica si el canal de recepción es correo certificado, en ese caso
     * habilita el botón para diligenciar los campos respectivos.
     */
    @Command
    public void verificarCanalRecepcion() {
        btnCorreoCertificado.setDisabled(canalRecepcionCorrespondenciaSeleccionado == null
                || !(canalRecepcionCorrespondenciaSeleccionado.getCodigo() == CODIGO_CORREO_CERTIFICADO));
    }

    /**
     * Modifica el label de radicación de acuerdo al tipo de radicación que se
     * esté realizando.
     */
    @Command
    public void cargarInformacionTipoRadicacion() {
        grbRemTercero.setVisible(false);
        grbRemInterno.setVisible(false);
        grbDestTercero.setVisible(false);
        grbDestInterno.setVisible(false);

        String radicacion = "E-";

        switch (tipoCorrespondenciaSeleccionada) {
            case ENTRADA:
                grbRemTercero.setVisible(true);
                grbDestInterno.setVisible(true);
                txtRadicadoAsociado.setRawValue(null);
                txtRadicadoAsociado.setDisabled(true);
                break;
            case SALIDA:
                grbRemInterno.setVisible(true);
                grbDestTercero.setVisible(true);
                radicacion = "S-";
                txtRadicadoAsociado.setDisabled(false);
                break;
            case INTERNA:
                grbRemInterno.setVisible(true);
                grbDestInterno.setVisible(true);
                radicacion = "I-";
                txtRadicadoAsociado.setDisabled(false);
                break;
            default:
                radicacion = "E-";
                txtRadicadoAsociado.setRawValue(null);
                txtRadicadoAsociado.setDisabled(true);
                break;
        }
        center.invalidate();
        String valorRad = StringUtils.leftPad(String.valueOf(getSecuencia().getValor() + 1), 12, '0');
        radicacion += valorRad;
        lblRadicado.setValue(radicacion);
    }

    /**
     * Carga la información de las series dependiendo del área seleccionada.
     */
    @Command
    @NotifyChange({ "series", "serieSeleccionada" })
    public void cargarInformacionSerie() {
        if (series != null) {
            limpiarCombos("serie");
        }
        ListModelList<Serie> seriesTmp = new ListModelList<>(serieService.listarPorArea(areaSeleccionada.getAreaId(), false));
        setSeries(seriesTmp);
    }

    /**
     * Carga la información de las subseries dependiendo de la serie
     * seleccionada.
     */
    @Command
    @NotifyChange({ "subseries", "subserieSeleccionada" })
    public void cargarInformacionSubserie() {
        if (subseries != null) {
            limpiarCombos("subserie");
        }
        ListModelList<Subserie> subserieTmp = new ListModelList<>(subserieService.listarPorSerie(serieSeleccionada.getSerieId(), false));
        setSubseries(subserieTmp);
    }

    /**
     * Actualiza la lista con la que se llena el combobox de nombre de tercero.
     * Se llama cuando un valor es ingresado en el mismo combobox.
     * 
     * @param event
     */
    @Command
    @NotifyChange({ "terceros", "terceroSeleccionado" })
    public void actualizarTercerosPorNombre(@BindingParam("arg1") InputEvent event) {
        terceros.clear();
        terceros.addAll(corTerceroService.listarPorEmpresaYNombre(idEmpresa, event.getValue(), false));
        cmbTercero.open();
    }

    /**
     * Actualiza la lista con la que se llena el combobox de Número de
     * identificación. Se llama cuando un valor es ingresado en el mismo
     * combobox.
     * 
     * @param event
     */
    @Command
    @NotifyChange({ "terceros", "terceroSeleccionado" })
    public void actualizarTercerosPorNumero(@BindingParam("arg1") InputEvent event) {
        terceros.clear();
        terceros.addAll(corTerceroService.listarPorEmpresaYNumero(idEmpresa, event.getValue(), false));
        cmbNroIdentificacion.open();
    }

    /**
     * Carga la información de un tercero en los campos respectivos. Es llamado
     * cuando se selecciona un item del combobox de nombre tercero o número
     * identificación tercero.
     */
    @Command
    @NotifyChange({ "terceros", "terceroSeleccionado" })
    public void cargarInformacionTercero() {
        CorTercero tercero = terceroSeleccionado;
        if (tercero != null) {
            lblDireccion.setValue(tercero.getDireccion());
            lblCiudad.setValue(tercero.getNombreMunicipio().concat(" (").concat(tercero.getNombreDepartamento().concat(")")));
            lblResponsable.setValue(tercero.getResponsable());
            lblCargoTercero.setValue(tercero.getCargo());
            lblDependencia.setValue(tercero.getDependencia());
        } else {
            lblDireccion.setValue(null);
            lblCiudad.setValue(null);
            lblResponsable.setValue(null);
            lblCargoTercero.setValue(null);
            lblDependencia.setValue(null);
        }
    }

    /**
     * Actualiza la lista con la que se llena el combobox de usuarios. Es
     * llamado cuando un valor es ingresado en el mismo combobox.
     * 
     * @param event
     */
    @Command
    @NotifyChange({ "usuarios", "usuarioSeleccionado" })
    public void actualizarUsuariosPorNombresYApellidos(@BindingParam("arg1") InputEvent event) {

        usuarios.clear();
        usuarios.addAll(usuarioService.listarPorNombresYApellidos(idEmpresa, event.getValue()));
        cmbUsuario.open();
    }

    /**
     * Carga la información de un usuario en los campos respectivos. Es llamado
     * cuando se selecciona un item del combobox de usuarios.
     */
    @Command
    @NotifyChange({ "usuarios", "usuarioSeleccionado" })
    public void cargarInformacionUsuario() {
        Usuario usuario = usuarioSeleccionado;
        if (usuario != null) {
            lblCargoUsuario.setValue(usuarioSeleccionado.getNombreRol());
            lblAreaProcesoRemitente.setValue(usuarioSeleccionado.getNombreArea());
        } else {
            lblCargoUsuario.setValue(null);
            lblAreaProcesoRemitente.setValue(null);
        }
    }

    /**
     * Encargado de radicar la correspondencia con la información del
     * formulario.
     */
    @Command
    public void radicarCorrespondencia() {
        String mensaje = "";
        if (validarFormulario()) {
            boolean requiereRespuesta = false;
            try {
                CorRadicado radicado = new CorRadicado();

                if (tipoCorrespondenciaSeleccionada.getId() == 'I'){
                    if (usuarioSeleccionado.equals(usuarioDestinatarioSeleccionado)){
                        throw new NonReadableChannelException();
                    }
                }
                radicado.setTipoRadicacion(tipoCorrespondenciaSeleccionada.getId());
                radicado.setSubserieId(subserieSeleccionada.getSubserieId());
                radicado.setSerieId(serieSeleccionada.getSerieId());
                radicado.setAreaId(areaSeleccionada.getAreaId());
                radicado.setSecuencia(Long.valueOf(lblSecuencia.getValue()));
                radicado.setAnexos(checkAnexo.isChecked());
                radicado.setRadicado(lblRadicado.getValue());
                radicado.setFechaRadicacion(DateUtils.stringToDate(lblFechaSistema.getValue(), Labels.getLabel("commons.config.dateTimeFormat")));
                radicado.setCantidadFolios(spinCantFolios.getValue());
                radicado.setCanalId(canalRecepcionCorrespondenciaSeleccionado.getCanalId());
                if (canalRecepcionCorrespondenciaSeleccionado.getCanalId() == CanalRecepcionCorrespondencia.CORREO_CERTIFICADO.getId()) {
                    radicado.setNumeroGuia(txtGuia.getValue());
                    radicado.setNombreTransportadora(transportadoraSeleccionada.getNombre());
                    radicado.setTransportadoraId(transportadoraSeleccionada.getTransportadoraId());
                    radicado.setFechaOperacion(dtxFecha.getValue());
                    radicado.setTipoEmbalajeId(tipoEmbalajeSeleccionado.getTipoEmbalajeId());
                    radicado.setCantidadEmbalaje(spinCantEmbalaje.getValue());
                }
                radicado.setAsunto(txtAsunto.getValue().trim());

                if (grbRemTercero.isVisible()) {
                    radicado.setNombreFuncionario(txtNombreFuncionario.getValue());
                    radicado.setNroIdentificacionFunc(txtIdFuncionario.getValue());
                    radicado.setRadicadoExterno(txtRadRemitente.getValue());
                    radicado.setTerceroRemitente(terceroSeleccionado);
                }
                if (grbRemInterno.isVisible()) {
                    radicado.setUsuarioRemitente(usuarioSeleccionado);
                }
                if (grbDestTercero.isVisible()) {
                    radicado.setTercerosDestinatarios(tercerosDestinatario);
                    radicado.setTerceroPrincipal(terceroDestinatarioSeleccionado);
                }
                if (grbDestInterno.isVisible()) {
                    radicado.setUsuariosDestinatarios(usuariosDestinatario);
                    radicado.setUsuarioPrincipal(usuarioDestinatarioSeleccionado);
                }
                if (!txtRadicadoAsociado.getValue().trim().isEmpty()) {
                    CorRadicado radAsociado = corRadicadoService.buscarPorEmpresaYRadicado(idEmpresa, txtRadicadoAsociado.getValue().trim());
                    if (radAsociado.isRequiereRespuesta()){
                        if (radicado.getTipoRadicacion() == 'S' && radAsociado.getTipoRadicacion() == 'E') {
                            //si estado es asignado, pasa a gestionado, de lo contrario error.
                            if (radAsociado.getEstadoId() == EstadosRadicado.ASIGNADO.getId()) {
                                radAsociado.setEstadoId(EstadosRadicado.GESTIONADO.getId());
                            } else {
                                throw new NotAcceptableException("101");
                            }
                        } else if (radicado.getTipoRadicacion() == 'I' && radAsociado.getTipoRadicacion() == 'I') {
                            //si es Interno, el asociado solo puede ser interno. 
                            //si estado es asignado, pasa a gestionado, de lo contrario error.
                            if (radAsociado.getEstadoId() == EstadosRadicado.ASIGNADO.getId()) {
                                radAsociado.setEstadoId(EstadosRadicado.GESTIONADO.getId());
                            } else {
                                throw new NotAcceptableException("102");
                            }
                        } else {
                            if (radicado.getTipoRadicacion() == 'S') {
                                throw new NotAcceptableException("105");
                            } else {
                                throw new NotAcceptableException("106");
                            }
                        }
                    } else {
                        //si no requiere respuesta se verifica el tipo de radicado nuevo y el asociado y el estado del asociado.
                        //si es de Salida, el asociado solo puede ser de entrada.
                        if (radicado.getTipoRadicacion() == 'S' && radAsociado.getTipoRadicacion() == 'E') {
                            //si estado es informado o gestionado, se asocia y no se cambia el estado, de lo contrario error.
                            if (radAsociado.getEstadoId() == EstadosRadicado.INFORMADO.getId() || radAsociado.getEstadoId() == EstadosRadicado.GESTIONADO.getId()) {
                                
                            } else {
                                throw new NotAcceptableException("103");
                            }
                        } else if (radicado.getTipoRadicacion() == 'I' && radAsociado.getTipoRadicacion() == 'I') {
                            //si es Interno, el asociado solo puede ser interno. 
                            //si estado es informado o gestionado, se asocia y no se cambia el estado, de lo contrario error.
                            if (radAsociado.getEstadoId() == EstadosRadicado.INFORMADO.getId() || radAsociado.getEstadoId() == EstadosRadicado.GESTIONADO.getId()) {
                                
                            } else {
                                throw new NotAcceptableException("104");
                            }
                        } else {
                            if (radicado.getTipoRadicacion() == 'S') {
                                throw new NotAcceptableException("105");
                            } else {
                                throw new NotAcceptableException("106");
                            }
                        }
                    }
                    
                    radicado.setRadicadoAsociadoId(radAsociado.getRadicadoId());
                    radicado.setRadicadoAsociado(radAsociado.getRadicado());
                    requiereRespuesta = radAsociado.isRequiereRespuesta();
                    if (requiereRespuesta){
                        mensaje = String.format(Labels.getLabel("correspondencia.radicacion.mensaje.radicadoCreado"), radicado.getRadicado()) + "<br/><br/>" + String.format(Labels.getLabel("correspondencia.radicacion.mensaje.radicadoAsociadoActualizado"), radAsociado.getRadicado(), EstadosRadicado.GESTIONADO.getNombre());
                    } else {
                        mensaje = String.format(Labels.getLabel("correspondencia.radicacion.mensaje.radicadoCreado"), radicado.getRadicado());
                    } 
                } else {
                    mensaje = String.format(Labels.getLabel("correspondencia.radicacion.mensaje.radicadoCreado"), radicado.getRadicado());
                }

                radicado.setUsuarioRadicacionId(idUsuario);
                radicado.setComentario(txtComentario.getValue());
                radicado.setEmpresaId(idEmpresa);
                radicado.setUsuarioModifica(idUsuario);
                CorRadicado res = corRadicadoService.crear(SessionUtil.getAuthToken(Sessions.getCurrent()), radicado);
                Map<String, Object> params = new HashMap<>();
                params.put("radicado", res);
                params.put("isCreacion", true);
                if (radicado.getTerceroRemitente() != null) {
                    params.put("remitente", radicado.getTerceroRemitente().getNombre());
                } else if (radicado.getUsuarioRemitente() != null) {
                    params.put("remitente", radicado.getUsuarioRemitente().getNombreCompleto());
                }

                String tipo = "info";
                Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
                
                Window window = (Window) Executions.createComponents("/correspondencia/radicar/imprimir_rotulos.zul", windowRadicar, params);
                window.doModal();

            } catch (NotFoundException e) {
                
                mensaje = Labels.getLabel("correspondencia.commons.mensaje.radicadoAsociadoNoExiste");
                String tipo = "error";
                Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);

            } catch (NotAcceptableException e) {

                mensaje = "";
                String tipo = "error";
                
                if (tipoCorrespondenciaSeleccionada == TipoRadicado.ENTRADA) {
                    mensaje = Labels.getLabel("correspondencia.commons.mensaje.radicadoAsociadoNoValidoEntrada");
                } else if (e.getMessage().equals("101")) {
                    mensaje = Labels.getLabel("correspondencia.commons.mensaje.radicadoAsociadoNoValidoSalidaRequiere");
                } else if (e.getMessage().equals("102")) {
                    mensaje = Labels.getLabel("correspondencia.commons.mensaje.radicadoAsociadoNoValidoInternoRequiere");
                } else if (e.getMessage().equals("103")) {
                    mensaje = Labels.getLabel("correspondencia.commons.mensaje.radicadoAsociadoNoValidoSalidaNoRequiere");
                } else if (e.getMessage().equals("104")) {
                    mensaje = Labels.getLabel("correspondencia.commons.mensaje.radicadoAsociadoNoValidoInternoNoRequiere");
                } else if (e.getMessage().equals("105")) {
                    mensaje = Labels.getLabel("correspondencia.commons.mensaje.radicadoAsociadoNoValidoSalida");
                } else if (e.getMessage().equals("106")) {
                    mensaje = Labels.getLabel("correspondencia.commons.mensaje.radicadoAsociadoNoValidoInterno");
                }
                
                Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
                
            } catch (NonReadableChannelException e){
                
                String tipo = "error";
                mensaje = Labels.getLabel("correspondencia.commons.mensaje.usuarioRemitenteNoIgualDestinatario");
                Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
            
            } catch (Exception e) {
                
                e.printStackTrace();
                mensaje = Labels.getLabel("correspondencia.commons.mensaje.radicadoNoCreado");
                String tipo = "error";
                Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);

            }
        }
    }

    /**
     * Verifica que todos los campos obligatorios hayan sido diligenciados.
     * 
     * @return <code>true</code> si la validación es correcta,
     *         <code>false</code> en caso contrario.
     */
    private boolean validarFormulario() {

        try {
            if (cmbTipoRadicacion.getSelectedItem() != null && !(cmbTipoRadicacion.getSelectedItem().getValue() != null)) {
                return false;
            }
            if (cmbAreaProceso.getSelectedItem() != null && !(cmbAreaProceso.getSelectedItem().getValue() != null)) {
                return false;
            }
            if (cmbSerie.getSelectedItem() != null && !(cmbSerie.getSelectedItem().getValue() != null)) {
                return false;
            }
            if (cmbSubserie.getSelectedItem() != null && !(cmbSubserie.getSelectedItem().getValue() != null)) {
                return false;
            }
            if (spinCantFolios.getValue() < 1) {
                return false;
            }
            if (cmbCanal.getSelectedItem() != null && !(cmbCanal.getSelectedItem().getValue() != null)) {
                return false;
            } else if (canalRecepcionCorrespondenciaSeleccionado.getNombre().equalsIgnoreCase("correo certificado")) {
                try {
                    if (txtGuia.getValue().isEmpty()) {
                        return false;
                    }
                    if (cmbCourier.getSelectedItem() != null && !(cmbCourier.getSelectedItem().getValue() != null)) {
                        return false;
                    }
                    if (!(dtxFecha.getValue() != null)) {
                        return false;
                    }
                    if (cmbTipoEmbalaje.getSelectedItem() != null && !(cmbTipoEmbalaje.getSelectedItem().getValue() != null)) {
                        return false;
                    }
                    if (spinCantEmbalaje.getValue() < 1) {
                        return false;
                    }
                } catch (WrongValueException e) {
                    popCorreoCert.open(btnCorreoCertificado, "after_start");
                    throw e;
                }
            }
            if (txtAsunto.getValue().isEmpty()) {
                return false;
            }
            if (txtComentario.getValue().isEmpty()) {
                return false;
            }
            if (grbRemTercero.isVisible()) {
                if (cmbTercero.getSelectedItem() == null || !(cmbTercero.getValue() != null)) {
                    cmbTercero.setConstraint("no empty:"+Labels.getLabel("commons.tooltip.campoRequerido"));
                    cmbTercero.getValue();
                }
            }
            if (grbRemInterno.isVisible()) {
                if (cmbUsuario.getSelectedItem() == null || !(cmbUsuario.getValue() != null)) {
                    cmbUsuario.setConstraint("no empty:"+Labels.getLabel("commons.tooltip.campoRequerido"));
                    cmbUsuario.getValue();
                }
            }
            if (grbDestInterno.isVisible()) {
                if (usuarioDestinatarioSeleccionado == null) {
                    lstDestInternos.setSclass("obligatorio");
                    throw new WrongValueException(lstDestInternos, Labels.getLabel("correspondencia.commons.label.destinatarioSeleccionar"));
                }
            }
            if (grbDestTercero.isVisible()) {
                if (terceroDestinatarioSeleccionado == null) {
                    lstDestTerceros.setSclass("obligatorio");
                    throw new WrongValueException(lstDestTerceros, Labels.getLabel("correspondencia.commons.label.destinatarioSeleccionar"));
                }
            }
            return true;
        } catch (WrongValueException e) {
            try {
                ((InputElement) e.getComponent()).setFocus(true);
            } catch (Exception ex) {
                // Ignore
            }
            throw e;
    }
    }

    /**
     * Permite borrar la información de los combos que están enlazados.
     * 
     * @param nivel
     *            <code>String</code> para ejecutar el borrado:<br>
     *            <code>"serie"</code> para borrar los combos de serie y subserie
     *            y tipo documental<br>
     *            <code>"subserie"</code> para borrar unicamente el combo de subserie.
     */
    private void limpiarCombos(String nivel) {

        switch (nivel) {
            case "serie":
                if (series != null) {
                    series.clear();
                }
                setSerieSeleccionada(null);
            case "subserie":
                if (subseries != null) {
                    subseries.clear();
                }
                setSubserieSeleccionada(null);
                break;
        }

        cmbSerie.clearErrorMessage();
        cmbSubserie.clearErrorMessage();
    }

    /**
     * Borra el estilo que actua como si disparara un "constraint" en los listbox.
     */
    @Command
    public void limpiarErrorDestinatario() {
        lstDestInternos.setSclass("");
        lstDestInternos.invalidate();
        lstDestTerceros.setSclass("");
        lstDestTerceros.invalidate();
    }

    /**
     * Carga una imagen del rótulo que será asignado a la radicación y la
     * muestra en pantalla en el campo respectivo.
     */
    @Command
    public void mostrarVistaPreviaRotulo() {

        validarVistaPrevia();

        RotuloInfo rotuloInfo = new RotuloInfo();
        rotuloInfo.setRadicado(lblRadicado.getValue());
        rotuloInfo.setCodigoBarras(lblRadicado.getValue().substring(2));// Se
                                                                        // borra
                                                                        // el
                                                                        // prefijo
                                                                        // y el
                                                                        // guión
        rotuloInfo.setFecha(lblFechaSistema.getValue());
        if (grbRemTercero.isVisible()) {
            rotuloInfo.setRemitente(cmbTercero.getValue());
        } else if (grbRemInterno.isVisible()) {
            rotuloInfo.setRemitente(cmbUsuario.getValue());
        }

        JasperPrint jasperPrint;
        Map<String, Object> params = new HashMap<>();
        List<RotuloInfo> rotulos = new ArrayList<>();
        rotulos.add(rotuloInfo);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            logoEmpresa = parametroAplicacionService.obtenerPorIdYEmpresa(ID_LOGO_EMPRESA, idEmpresa);
            
            if (logoEmpresa != null){
                byte[] logoBase64 = Base64.getDecoder().decode(logoEmpresa.getValor());
                params.put("logo", ImageIO.read(new ByteArrayInputStream(logoBase64)));
                
            } else {
                params.put("logo", ImageIO.read(getClass().getResource("/images/logo.png")));
            }
            
            InputStream report = getClass().getResourceAsStream("/reports/rotulo.jasper");

            jasperPrint = Reportes.llenarReporte(report, params, new JRBeanCollectionDataSource(rotulos));

            Reportes.generarReportePdf(jasperPrint, out);

            ByteArrayInputStream is = new ByteArrayInputStream(out.toByteArray());
            BufferedImage img = Reportes.convertirPdfAImagen(is);
            imgRotulo.setWidth(hboxImage.getWidth());
            imgRotulo.setContent(img);
            
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Valida que los campos necesarios para generar la vista previa del rótulo
     * hayan sido diligenciados.
     * 
     * @return <code>true</code> si la validación es correcta,
     *         <code>false</code> en caso contrario.
     */
    private boolean validarVistaPrevia() {

        try {
            if (cmbTipoRadicacion.getSelectedItem() != null && !(cmbTipoRadicacion.getSelectedItem().getValue() != null)) {
                return false;
            }
            if (grbRemTercero.isVisible()) {
                if (cmbTercero.getSelectedItem() == null || !(cmbTercero.getValue() != null)) {
                    cmbTercero.setConstraint("no empty:"+Labels.getLabel("commons.tooltip.campoRequerido"));
                    cmbTercero.isValid();
                }
            } else if (grbRemInterno.isVisible()) {
                if (cmbUsuario.getSelectedItem() == null || !(cmbUsuario.getValue() != null)) {
                    cmbUsuario.setConstraint("no empty:"+Labels.getLabel("commons.tooltip.campoRequerido"));
                    cmbUsuario.isValid();
                }
            }
            return true;
        } catch (WrongValueException e) {
            try {
                ((InputElement) e.getComponent()).setFocus(true);
            } catch (Exception ex) {
                // Ignore
            }
            throw e;
        }
    }

    /**
     * Método para llamar a la ventana de administrar terceros.
     */
    @Command
    public void administrarTerceros(){
        Window window = (Window) Executions.createComponents("/administracion/terceros/administrar_terceros.zul", windowRadicar, null);
        window.doModal();
    }
    
    
    /**
     * Método que llama a la ventana de agregar terceros destinatarios.
     */
    @Command
    @NotifyChange({"tercerosDestinatario","terceroDestinatarioSeleccionado"})
    public void agregarDestinatarioTercero() {

        Map<String, Object> params = new HashMap<>();
        params.put("callback", "cargarTerceros");
        params.put("empresaId", idEmpresa);
        params.put("terceros", tercerosDestinatario);
        params.put("isRespuestaMvc", false); //true si se llama de una ventana creada con MVC, false para una creada con MVVM
        Window window = (Window) Executions.createComponents("/correspondencia/radicar/agregar_tercero.zul", windowRadicar, params);
        window.doModal();

    }

    /**
     * Método que es ejecutado por la ventana de agregar terceros destinatarios
     * que carga los datos retornados por la misma en el listbox de terceros
     * destinatarios.
     * @param tercerosElegidos Lista retornada por la ventana de agregar terceros
     * destinatarios.
     */
    @GlobalCommand
    @NotifyChange({"tercerosDestinatario","terceroDestinatarioSeleccionado"})
    public void cargarTerceros(@BindingParam("tercerosElegidos") List<CorTercero> tercerosElegidos) {
        if(!tercerosElegidos.contains(terceroDestinatarioSeleccionado)){
            setTerceroDestinatarioSeleccionado(null);
        }
        tercerosDestinatario = tercerosElegidos;
        lstDestTerceros.invalidate();
    }

    /**
     * Método que llama a la ventana de agregar usuarios destinatarios.
     */
    @Command
    @NotifyChange({ "usuariosDestinatario", "usuariosDestinatarioSeleccionado" })
    public void agregarDestinatarioUsuario() {
        Map<String, Object> params = new HashMap<>();
        params.put("callback", "cargarUsuarios");
        params.put("empresaId", idEmpresa);
        params.put("usuarios", usuariosDestinatario);
        params.put("isRespuestaMvc", false); //true si se llama de una ventana creada con MVC, false para una creada con MVVM
        Window window = (Window) Executions.createComponents("/correspondencia/radicar/agregar_responsable.zul", windowRadicar, params);
        window.doModal();
    }

    /**
     * Método que es ejecutado por la ventana de agregar usuarios destinatarios
     * que carga los datos retornados por la misma en el listbox de usuarios
     * destinatarios.
     * @param usuariosElegidos Lista retornada por la ventana de agregar usuarios
     * destinatarios.
     */
    @GlobalCommand
    @NotifyChange({ "usuariosDestinatario", "usuariosDestinatarioSeleccionado" })
    public void cargarUsuarios(@BindingParam("usuariosElegidos") List<Usuario> usuariosElegidos) {

        if(!usuariosElegidos.contains(usuarioDestinatarioSeleccionado)){
            setUsuarioDestinatarioSeleccionado(null);
        }
        usuariosDestinatario = usuariosElegidos;
        lstDestInternos.invalidate();

    }

    /**
     * Borra el tercero elegido de la lista de terceros destinatarios.
     * @param tercero El tercero que se desea borrar de la lista.
     */
    @Command
    @NotifyChange({ "tercerosDestinatario", "terceroDestinatarioSeleccionado" })
    public void eliminarTerceroDestino(@BindingParam("tercero") CorTercero tercero) {
        tercerosDestinatario.remove(tercero);
        if (terceroDestinatarioSeleccionado != null && terceroDestinatarioSeleccionado.equals(tercero)) {
            terceroDestinatarioSeleccionado = null;
        }
        lstDestTerceros.invalidate();
    }

    /**
     * Borra el usuario elegido de la lista de usuarios destinatarios.
     * @param usuario El usuario que se desea borrar de la lista.
     */
    @Command
    @NotifyChange({ "usuariosDestinatario", "usuariosDestinatarioSeleccionado" })
    public void eliminarUsuarioDestino(@BindingParam("usuario") Usuario usuario) {
        usuariosDestinatario.remove(usuario);
        if (usuarioDestinatarioSeleccionado != null && usuarioDestinatarioSeleccionado.equals(usuario)) {
            usuarioDestinatarioSeleccionado = null;
        }
        lstDestTerceros.invalidate();
    }

    /**
     * @return the tipoCorrespondencia
     */
    public ListModelList<TipoRadicado> getTipoCorrespondencia() {
        return tipoCorrespondencia;
    }

    /**
     * @param tipoCorrespondencia
     *            the tipoCorrespondencia to set
     */
    public void setTipoCorrespondencia(ListModelList<TipoRadicado> tipoCorrespondencia) {
        this.tipoCorrespondencia = tipoCorrespondencia;
    }

    /**
     * @return the tipoCorrespondenciaSeleccionada
     */
    public TipoRadicado getTipoCorrespondenciaSeleccionada() {
        return tipoCorrespondenciaSeleccionada;
    }

    /**
     * @param tipoCorrespondenciaSeleccionada
     *            the tipoCorrespondenciaSeleccionada to set
     */
    public void setTipoCorrespondenciaSeleccionada(TipoRadicado tipoCorrespondenciaSeleccionada) {
        this.tipoCorrespondenciaSeleccionada = tipoCorrespondenciaSeleccionada;
    }

    /**
     * @return the area
     */
    public List<Area> getAreas() {
        return areas;
    }

    /**
     * @param area
     *            the area to set
     */
    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    /**
     * @return the areaSeleccionada
     */
    public Area getAreaSeleccionada() {
        return areaSeleccionada;
    }

    /**
     * @param areaSeleccionada
     *            the areaSeleccionada to set
     */
    public void setAreaSeleccionada(Area areaSeleccionada) {
        this.areaSeleccionada = areaSeleccionada;
    }

    /**
     * @return the canalCorrespondencia
     */
    public List<CorCanal> getCorCanal() {
        return canalesRecepcionCorrespondencia;
    }

    /**
     * @param canalCorrespondencia
     *            the canalCorrespondencia to set
     */
    public void setCorCanal(List<CorCanal> canalRecepcionCorrespondencia) {
        this.canalesRecepcionCorrespondencia = canalRecepcionCorrespondencia;
    }

    /**
     * @return the canalCorrespondenciaSeleccionado
     */
    public CorCanal getCanalRecepcionCorrespondenciaSeleccionado() {
        return canalRecepcionCorrespondenciaSeleccionado;
    }

    /**
     * @param canalCorrespondenciaSeleccionado
     *            the canalCorrespondenciaSeleccionado to set
     */
    public void setCanalRecepcionCorrespondenciaSeleccionado(CorCanal canalCorrespondenciaSeleccionado) {
        this.canalRecepcionCorrespondenciaSeleccionado = canalCorrespondenciaSeleccionado;
    }

    /**
     * @return the serie
     */
    public List<Serie> getSeries() {
        return series;
    }

    /**
     * @param serie
     *            the serie to set
     */
    public void setSeries(ListModelList<Serie> series) {
        this.series = series;
    }

    /**
     * @return the serieSeleccionada
     */
    public Serie getSerieSeleccionada() {
        return serieSeleccionada;
    }

    /**
     * @param serieSeleccionada
     *            the serieSeleccionada to set
     */
    public void setSerieSeleccionada(Serie serieSeleccionada) {
        this.serieSeleccionada = serieSeleccionada;
    }

    /**
     * @return the subserie
     */
    public List<Subserie> getSubseries() {
        return subseries;
    }

    /**
     * @param subserie
     *            the subserie to set
     */
    public void setSubseries(ListModelList<Subserie> subseries) {
        this.subseries = subseries;
    }

    /**
     * @return the subserieSeleccionada
     */
    public Subserie getSubserieSeleccionada() {
        return subserieSeleccionada;
    }

    /**
     * @param subserieSeleccionada
     *            the subserieSeleccionada to set
     */
    public void setSubserieSeleccionada(Subserie subserieSeleccionada) {
        this.subserieSeleccionada = subserieSeleccionada;
    }

    /**
     * @return the transportadoras
     */
    public List<CorTransportadora> getTransportadoras() {
        return transportadoras;
    }

    /**
     * @param transportadoras
     *            the transportadoras to set
     */
    public void setTransportadoras(List<CorTransportadora> transportadoras) {
        this.transportadoras = transportadoras;
    }

    /**
     * @return the transportadoraSeleccionada
     */
    public CorTransportadora getTransportadoraSeleccionada() {
        return transportadoraSeleccionada;
    }

    /**
     * @param transportadoraSeleccionada
     *            the transportadoraSeleccionada to set
     */
    public void setTransportadoraSeleccionada(CorTransportadora transportadoraSeleccionada) {
        this.transportadoraSeleccionada = transportadoraSeleccionada;
    }

    /**
     * @return the tipoEmbalajes
     */
    public List<CorTipoEmbalaje> getTipoEmbalajes() {
        return tipoEmbalajes;
    }

    /**
     * @param tipoEmbalajes
     *            the tipoEmbalajes to set
     */
    public void setTipoEmbalajes(List<CorTipoEmbalaje> tipoEmbalajes) {
        this.tipoEmbalajes = tipoEmbalajes;
    }

    /**
     * @return the tipoEmbalajeSeleccionado
     */
    public CorTipoEmbalaje getTipoEmbalajeSeleccionado() {
        return tipoEmbalajeSeleccionado;
    }

    /**
     * @param tipoEmbalajeSeleccionado
     *            the tipoEmbalajeSeleccionado to set
     */
    public void setTipoEmbalajeSeleccionado(CorTipoEmbalaje tipoEmbalajeSeleccionado) {
        this.tipoEmbalajeSeleccionado = tipoEmbalajeSeleccionado;
    }

    /**
     * @return the terceros
     */
    public List<CorTercero> getTerceros() {
        return terceros;
    }

    /**
     * @param terceros
     *            the terceros to set
     */
    public void setTerceros(List<CorTercero> terceros) {
        this.terceros = terceros;
    }

    /**
     * @return the terceroSeleccionado
     */
    public CorTercero getTerceroSeleccionado() {
        return terceroSeleccionado;
    }

    /**
     * @param terceroSeleccionado
     *            the terceroSeleccionado to set
     */
    public void setTerceroSeleccionado(CorTercero terceroSeleccionado) {
        this.terceroSeleccionado = terceroSeleccionado;
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
     * @return the usuarioSeleccionado
     */
    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    /**
     * @param usuarioSeleccionado
     *            the usuarioSeleccionado to set
     */
    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    /**
     * @return the secuencia
     */
    public CorSecuencia getSecuencia() {
        return secuencia;
    }

    /**
     * @param secuencia
     *            the secuencia to set
     */
    public void setSecuencia(CorSecuencia secuencia) {
        this.secuencia = secuencia;
    }

    /**
     * @return the usuariosDestinatario
     */
    public List<Usuario> getUsuariosDestinatario() {
        return usuariosDestinatario;
    }

    /**
     * @param usuariosDestinatario
     *            the usuariosDestinatario to set
     */
    public void setUsuariosDestinatario(List<Usuario> usuariosDestinatario) {
        this.usuariosDestinatario = usuariosDestinatario;
    }

    /**
     * @return the usuarioDestinatarioSeleccionado
     */
    public Usuario getUsuarioDestinatarioSeleccionado() {
        return usuarioDestinatarioSeleccionado;
    }

    /**
     * @param usuarioDestinatarioSeleccionado
     *            the usuarioDestinatarioSeleccionado to set
     */
    public void setUsuarioDestinatarioSeleccionado(Usuario usuarioDestinatarioSeleccionado) {
        this.usuarioDestinatarioSeleccionado = usuarioDestinatarioSeleccionado;
    }

    /**
     * @return the tercerosDestinatario
     */
    public List<CorTercero> getTercerosDestinatario() {
        return tercerosDestinatario;
    }

    /**
     * @param tercerosDestinatario
     *            the tercerosDestinatario to set
     */
    public void setTercerosDestinatario(List<CorTercero> tercerosDestinatario) {
        this.tercerosDestinatario = tercerosDestinatario;
    }

    /**
     * @return the terceroDestinatarioSeleccionado
     */
    public CorTercero getTerceroDestinatarioSeleccionado() {
        return terceroDestinatarioSeleccionado;
    }

    /**
     * @param terceroDestinatarioSeleccionado
     *            the terceroDestinatarioSeleccionado to set
     */
    public void setTerceroDestinatarioSeleccionado(CorTercero terceroDestinatarioSeleccionado) {
        this.terceroDestinatarioSeleccionado = terceroDestinatarioSeleccionado;
    }

    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario
     *            the idUsuario to set
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the idEmpresa
     */
    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa
     *            the idEmpresa to set
     */
    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the administrarTerceros
     */
    public boolean isAdministrarTerceros() {
        return administrarTerceros;
    }

    /**
     * @param administrarTerceros the administrarTerceros to set
     */
    public void setAdministrarTerceros(boolean administrarTerceros) {
        this.administrarTerceros = administrarTerceros;
    }
}