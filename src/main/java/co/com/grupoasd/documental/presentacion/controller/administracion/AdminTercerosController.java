/*
 * Archivo: AdminTercerosController.java
 * Fecha creacion: 8/05/2017
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
package co.com.grupoasd.documental.presentacion.controller.administracion;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.WrongValuesException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.MessageboxDlg;

import co.com.grupoasd.documental.cliente.catalogo.model.MdtMunicipio;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTercero;
import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.comun.dto.Paginacion;
import co.com.grupoasd.documental.presentacion.comun.dto.RespuestaServicio;
import co.com.grupoasd.documental.presentacion.comun.vo.FiltroTercero;
import co.com.grupoasd.documental.presentacion.controller.util.Base64;
import co.com.grupoasd.documental.presentacion.controller.util.GenericPagingController;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.MdtMunicipioService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.CorrespondenciaServiceFactory;
import co.com.grupoasd.documental.presentacion.service.correspondencia.dto.ResultadoImpoTerceros;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTerceroService;

import org.apache.commons.lang3.StringUtils;

/**
 * Controller de adminstrción de terceros.
 * 
 * @author cestrada
 *
 */
public class AdminTercerosController extends GenericPagingController<Window> {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -8050600942066102538L;
    /**
     * strOrderBy.
     */
    private static String[] strOrderBy = { "nombre" };

    /**
     * Componentes UI.
     */
    @Wire
    Radiogroup rdgFiltroTercero;
    @Wire
    private Textbox txtFiltroTercero;
    @Wire
    Textbox txtNumeroIdentificacion;
    @Wire
    private Textbox txtNombre;
    @Wire
    private Spinner intDiasCaida;
    @Wire
    private Textbox txtCorreo;
    @Wire
    private Textbox txtDireccion;
    @Wire
    private Combobox cmbMunicipio;
    @Wire
    private Textbox txtTelefonos;
    @Wire
    private Textbox txtResponsable;
    @Wire
    private Textbox txtDependencia;
    @Wire
    private Textbox txtCargo;
    @Wire
    private Button btnCrear;
    @Wire
    private Button btnActualizar;
    @Wire
    private Button btnEliminar;
    @Wire
    private Listbox lstTerceros;
    @Wire
    private Paging pageResult;

    /**
     * Servicios.
     */
    private CorTerceroService corTerceroService;
    private MdtMunicipioService municipioService;

    /**
     * Atributos Controlador.
     */
    private List<CorTercero> terceros;
    private List<CorTercero> tercerosWork;
    private CorTercero terceroSeleccionado;
    private List<MdtMunicipio> municipios;
    private MdtMunicipio municipioSeleccionado;
    private ListModelList<FiltroTercero> modelFiltroTercero;
    private FiltroTercero filtroTerceroSeleccionado;
    private String callback;
    private String valorBusqueda;
    private String tipoEvento;

    private static final Logger LOG = Logger.getLogger(AdminTercerosController.class.getName());

    /**
     * Inicializacion del controller.
     */
    @Init
    private void init() {
        try {
            callback = args.containsKey("callback") ? (String) args.get("callback") : "";
            corTerceroService = CorrespondenciaServiceFactory.getCorTerceroService();
            municipioService = CatalogoServiceFactory.getMdtMunicipioService();
            municipios = municipioService.listar();
            modelFiltroTercero = new ListModelList<>(FiltroTercero.values());
            modelFiltroTercero.addToSelection(FiltroTercero.IDENTIFICACION);
            filtroTerceroSeleccionado = FiltroTercero.IDENTIFICACION;
            tipoEvento = "";
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(
                    Labels.getLabel("excepcion.no.identificado") + " " + new String[] { ex.getLocalizedMessage() });
        }
    }

    /**
     * Metodo que se ejecuta despues de que ZK crea los componentes de la vista.
     * 
     * @param view
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        try {
            Selectors.wireComponents(view, this, false);
            mostrarPaginaResultado();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(
                    Labels.getLabel("excepcion.no.identificado") + " " + new String[] { ex.getLocalizedMessage() });
        }
    }

    @Command
    @NotifyChange({ "rdgFiltroTercero", "lstTerceros", "terceros" })
    public void cambiarFiltro(Event event) {
        filtroTerceroSeleccionado = (FiltroTercero) rdgFiltroTercero.getSelectedItem().getValue();
        try {
            if (filtroTerceroSeleccionado == FiltroTercero.IDENTIFICACION) {
                txtFiltroTercero.setTooltiptext(FiltroTercero.IDENTIFICACION.getValor());
            } else {
                txtFiltroTercero.setTooltiptext(FiltroTercero.NOMBRE.getValor());
            }
            if (txtFiltroTercero != null && txtFiltroTercero.getValue() != null
                    && !txtFiltroTercero.getValue().isEmpty()) {
                mostrarPaginaResultado();
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(
                    Labels.getLabel("excepcion.no.identificado") + " " + new String[] { ex.getLocalizedMessage() });
        }
    }

    @Command
    @NotifyChange({ "lstTerceros", "terceros" })
    public void filtrarTercero(@ContextParam(ContextType.TRIGGER_EVENT) InputEvent event) {
        try {
            tipoEvento = "onChanging";
            valorBusqueda = event.getValue();
            mostrarPaginaResultado();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(
                    Labels.getLabel("excepcion.no.identificado") + " " + new String[] { ex.getLocalizedMessage() });
        }
    }

    @Command
    @NotifyChange({ "lstTerceros", "terceros" })
    public void buscarNumero(Event event) {
        try {
            tipoEvento = "onOk";
            valorBusqueda = txtNumeroIdentificacion.getText();
            mostrarPaginaResultado();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(
                    Labels.getLabel("excepcion.no.identificado") + " " + new String[] { ex.getLocalizedMessage() });
        }
    }

    @Command
    @NotifyChange({ "terceroSeleccionado" })
    public void cargarTercero() {
        cargarDatosTerecero(false);
    }

    /**
     * paginar.
     */
    @Command
    @NotifyChange({ "pageResult", "lstTerceros", "terceros" })
    public void paginar() {
        mostrarPaginaResultado();
    }

    @Command
    @NotifyChange({ "pageResult", "lstTerceros", "terceros" })
    public void guardar(@BindingParam("metodo") String valor) throws Exception {
        validarFormulario();
        try {
            asignarDatosFormulario();
            terceroSeleccionado = corTerceroService.guardar(terceroSeleccionado);
            if (!terceroSeleccionado.isError()) {
                tipoEvento = "";
                mostrarPaginaResultado();
                cargarDatosTerecero(true);
                if (valor.equalsIgnoreCase("crear")) {
                    UiUtils.mostrarInformacion(Labels.getLabel("commons.mensaje.registroCreado"));
                } else if (valor.equalsIgnoreCase("actualizar")) {
                    UiUtils.mostrarInformacion(Labels.getLabel("commons.mensaje.registroActualizado"));
                }
            } else {
                UiUtils.mostrarError(terceroSeleccionado.getRespuesta());
            }

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(
                    Labels.getLabel("excepcion.no.identificado") + " " + new String[] { ex.getLocalizedMessage() });
        }
    }

    @Command
    public void eliminar() {
        UiUtils.mostrarAlerta(String.format(Labels.getLabel("administracion.terceros.mensaje.confirmacionEliminar"),
                terceroSeleccionado.getNombre()), new EventListener<Event>() {
                    @Override
                    public void onEvent(Event event) throws Exception {
                        MessageboxDlg dlg = (MessageboxDlg) event.getTarget();
                        if (dlg.getResult() == Messagebox.Button.YES) {
                            try {
                                terceroSeleccionado = corTerceroService.inactivar(terceroSeleccionado);
                                BindUtils.postNotifyChange(null, null, AdminTercerosController.this, "*");
                                tipoEvento = "";
                                mostrarPaginaResultado();
                                cargarDatosTerecero(true);
                                // UiUtils.mostrarInformacion(Labels.getLabel("commons.mensaje.registroInactivado"));
                            } catch (Exception ex) {
                                LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                                UiUtils.mostrarError(Labels.getLabel("excepcion.no.identificado") + " "
                                        + new String[] { ex.getLocalizedMessage() });
                            }
                        }
                    }
                });
    }

    @Command
    @NotifyChange({ "pageResult", "lstTerceros", "terceros" })
    public void importar(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event) {
        final InfoMedia media = new InfoMedia();
        try {
            media.setNombreArchivo(event.getMedia().getName());
            media.setTipoArchivo(event.getMedia().getFormat());
            RespuestaServicio respuestaServicio = corTerceroService.validar(media.getNombreArchivo());
            if (respuestaServicio.isError()) {
                UiUtils.mostrarInformacion(respuestaServicio.getRespuesta());
            } else {
                media.setArchivo(Base64.encodeToString(event.getMedia().getByteData(), false));
                UiUtils.mostrarAlerta(
                        String.format(Labels.getLabel("administracion.terceros.mensaje.confirmacionCargueArchivo"),
                                media.getNombreArchivo()),
                        new EventListener<Event>() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                MessageboxDlg dlg = (MessageboxDlg) event.getTarget();
                                if (dlg.getResult() == Messagebox.Button.YES) {
                                    try {
                                        ResultadoImpoTerceros resultadoImpoTerceros = corTerceroService.importar(media,
                                                SessionUtil.getCurrentSession().getEmpresaId(),
                                                SessionUtil.getCurrentSession().getUsuarioId());
                                        if (!respuestaServicio.isError()) {
                                            mostrarResumenImpoTerceros(resultadoImpoTerceros);
                                        } else {
                                            UiUtils.mostrarError(resultadoImpoTerceros.getMensaje());
                                        }
                                    } catch (Exception ex) {
                                        LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                                        UiUtils.mostrarError(Labels.getLabel("excepcion.no.identificado") + " "
                                                + new String[] { ex.getLocalizedMessage() });
                                    }
                                }
                            }
                        });
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(
                    Labels.getLabel("excepcion.no.identificado") + " " + new String[] { ex.getLocalizedMessage() });
        }
    }

    @Command
    @NotifyChange({ "pageResult", "lstTerceros", "terceros" })
    public void limpiar() {
        valorBusqueda = "";
        mostrarPaginaResultado();
        cargarDatosTerecero(true);
    }

    @Override
    protected void mostrarPaginaResultado() {
        try {
            int resultCount = obtenerMaxResult();
            pageResult.setTotalSize(resultCount);
            if (resultCount < 1) {
                UiUtils.mostrarInformacion(Labels.getLabel("commons.mensaje.busqueda.sinResultados"));
            }
            int pgn = pageResult.getActivePage();
            int size = pageResult.getPageSize();
            obtenerTerceros(pgn, size);
            lstTerceros.invalidate();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(
                    Labels.getLabel("excepcion.no.identificado") + " " + new String[] { ex.getLocalizedMessage() });
        }
    }

    @Override
    public void onCreate(Event event) {

    }

    @Override
    protected EventListener<Event> ordenarLista() {
        return null;
    }

    /**
     * onClose.
     */
    public void onClose(Event event) {
        if (StringUtils.isNotBlank(callback)) {
            Events.sendEvent(callback, self.getParent(), event);
        }
    }

    /********************************
     * Private methods
     ********************************/
    private void cargarDatosTerecero(boolean limpiar) {
        try {
            if (limpiar) {
                terceroSeleccionado = new CorTercero();
                btnActualizar.setDisabled(true);
                btnEliminar.setDisabled(true);
                btnCrear.setDisabled(false);
                municipioSeleccionado = null;
                UiUtils.seleccionarComboitemPorLabel(cmbMunicipio, null);
                if (tipoEvento.equalsIgnoreCase("onOk")) {
                    terceroSeleccionado.setNumero(valorBusqueda);
                } else if (!tipoEvento.isEmpty()) {
                    txtFiltroTercero.setText(valorBusqueda);
                }
            } else {
                if (terceroSeleccionado == null) {
                    return;
                }
                if (tipoEvento.equalsIgnoreCase("onOk")) {
                    txtFiltroTercero.setText("");
                }
                btnActualizar.setDisabled(false);
                btnEliminar.setDisabled(false);
                btnCrear.setDisabled(true);
                UiUtils.seleccionarComboitemPorLabel(cmbMunicipio, terceroSeleccionado.getNombreMunicipio());
                municipioSeleccionado = cmbMunicipio.getSelectedItem().getValue();
            }
            txtNumeroIdentificacion.setText(terceroSeleccionado.getNumero());
            txtNombre.setText(terceroSeleccionado.getNombre());
            Constraint c = intDiasCaida.getConstraint();
            intDiasCaida.setConstraint((Constraint) null);
            intDiasCaida.setValue(1);
            intDiasCaida.setConstraint(c);
            txtCorreo.setText(terceroSeleccionado.getCorreo());
            txtDireccion.setText(terceroSeleccionado.getDireccion());
            txtTelefonos.setText(terceroSeleccionado.getTelefonos());
            txtResponsable.setText(terceroSeleccionado.getResponsable());
            txtDependencia.setText(terceroSeleccionado.getDependencia());
            txtCargo.setText(terceroSeleccionado.getCargo());
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(
                    Labels.getLabel("excepcion.no.identificado") + " " + new String[] { ex.getLocalizedMessage() });
        }
    }

    private void asignarDatosFormulario() {
        if (terceroSeleccionado == null) {
            terceroSeleccionado = new CorTercero();
        }
        terceroSeleccionado.setEmpresaId(SessionUtil.getCurrentSession().getEmpresaId());
        terceroSeleccionado.setNumero(txtNumeroIdentificacion.getText());
        terceroSeleccionado.setNombre(txtNombre.getText());
        terceroSeleccionado.setDiasCaida(intDiasCaida.getValue());
        terceroSeleccionado.setCorreo(txtCorreo.getText());
        terceroSeleccionado.setDireccion(txtDireccion.getText());
        terceroSeleccionado.setMunicipio(municipioSeleccionado.getCodigoDane());
        terceroSeleccionado.setTelefonos(txtTelefonos.getText());
        terceroSeleccionado.setResponsable(txtResponsable.getText());
        terceroSeleccionado.setDependencia(txtDependencia.getText());
        terceroSeleccionado.setCargo(txtCargo.getText());
        terceroSeleccionado.setUsuarioModifica(SessionUtil.getCurrentSession().getUsuarioId());
    }

    private int obtenerMaxResult() {
        if (valorBusqueda != null && !valorBusqueda.isEmpty()) {
            if (tipoEvento.equalsIgnoreCase("onOk") || (tipoEvento.equalsIgnoreCase("onChanging")
                    && filtroTerceroSeleccionado == FiltroTercero.IDENTIFICACION)) {
                return corTerceroService.contarPorNumero(SessionUtil.getCurrentSession().getEmpresaId(), valorBusqueda);
            } else if (tipoEvento.equalsIgnoreCase("onChanging") && filtroTerceroSeleccionado == FiltroTercero.NOMBRE) {
                return corTerceroService.contarPorNombre(SessionUtil.getCurrentSession().getEmpresaId(), valorBusqueda);
            }
        }
        return corTerceroService.contarTodos(SessionUtil.getCurrentSession().getEmpresaId());
    }

    private void obtenerTerceros(Integer pgn, Integer size) {
        Paginacion paginacion = new Paginacion(pgn, size, strOrderBy, false);
        if (valorBusqueda != null && !valorBusqueda.isEmpty()) {
            if (tipoEvento.equalsIgnoreCase("onOk") || (tipoEvento.equalsIgnoreCase("onChanging")
                    && filtroTerceroSeleccionado == FiltroTercero.IDENTIFICACION)) {
                tercerosWork = corTerceroService.listarPorNumeroConPaginacion(
                        SessionUtil.getCurrentSession().getEmpresaId(), valorBusqueda, paginacion);
                asignarDatosDespuesConsulta();
                return;
            } else if (tipoEvento.equalsIgnoreCase("onChanging") && filtroTerceroSeleccionado == FiltroTercero.NOMBRE) {
                tercerosWork = corTerceroService.listarPorNombreConPaginacion(
                        SessionUtil.getCurrentSession().getEmpresaId(), valorBusqueda, paginacion);
                asignarDatosDespuesConsulta();
                return;
            }
        }
        tercerosWork = corTerceroService.listarTodos(SessionUtil.getCurrentSession().getEmpresaId(), paginacion);
        asignarDatosDespuesConsulta();
    }

    private void asignarDatosDespuesConsulta() {
        for (CorTercero tercero : tercerosWork) {
            tercero.setNombreMunicipio(obtenerNombreMunicipio(tercero.getMunicipio()));
        }
        if (terceros == null) {
            terceros = new ArrayList<CorTercero>();
        } else {
            terceros.clear();
        }
        terceros.addAll(tercerosWork);
        if (terceros.size() == 1 && !tipoEvento.isEmpty()) {
            terceroSeleccionado = terceros.get(0);
            cargarDatosTerecero(false);

        } else {
            cargarDatosTerecero(true);
        }
    }

    private String obtenerNombreMunicipio(String codigoDane) {
        for (MdtMunicipio municipio : municipios) {
            if (codigoDane.equalsIgnoreCase(municipio.getCodigoDane())) {
                return municipio.getMunicipioDepartamento();
            }
        }
        return codigoDane;
    }

    private void validarFormulario() throws WrongValuesException {
        List<WrongValueException> excepciones = new ArrayList<WrongValueException>();
        if (StringUtils.isBlank(txtNumeroIdentificacion.getValue())) {
            excepciones.add(new WrongValueException(txtNumeroIdentificacion,
                    Labels.getLabel("commons.tooltip.campoRequerido")));
        }
        if (StringUtils.isBlank(txtNombre.getValue())) {
            excepciones.add(new WrongValueException(txtNombre, Labels.getLabel("commons.tooltip.campoRequerido")));
        }
        if (!UiUtils.validNumber(intDiasCaida)) {
            excepciones.add(new WrongValueException(intDiasCaida, Labels.getLabel("commons.tooltip.campoRequerido")));
        }
        if (StringUtils.isBlank(txtCorreo.getValue())) {
            excepciones.add(new WrongValueException(txtCorreo, Labels.getLabel("commons.tooltip.campoRequerido")));
        }
        if (StringUtils.isBlank(txtDireccion.getValue())) {
            excepciones.add(new WrongValueException(txtDireccion, Labels.getLabel("commons.tooltip.campoRequerido")));
        }
        if (!UiUtils.itemSelected(cmbMunicipio)) {
            excepciones.add(new WrongValueException(cmbMunicipio, Labels.getLabel("commons.tooltip.campoRequerido")));
        }
        if (StringUtils.isBlank(txtTelefonos.getValue())) {
            excepciones.add(new WrongValueException(txtTelefonos, Labels.getLabel("commons.tooltip.campoRequerido")));
        }
        if (excepciones.size() > 0) {
            throw new WrongValuesException(excepciones.toArray(new WrongValueException[excepciones.size()]));
        }
    }

    private void mostrarResumenImpoTerceros(final ResultadoImpoTerceros resultado) {
        String msg = "";
        try {
            if (resultado.getFile() == null && resultado.getMensaje() == null) {
                msg += Labels.getLabel("commons.mensaje.archivoCargado") + "\n\n";
                msg += Labels.getLabel("commons.mensaje.registrosError") + ": " + resultado.getRegisError() + "\n";
                msg += Labels.getLabel("commons.mensaje.registrosCargados") + ": " + resultado.getRegisInser() + "\n";
                msg += Labels.getLabel("commons.mensaje.registrosActualizados") + ": " + resultado.getRegisActua()
                        + "\n";
                UiUtils.mostrarInformacion(msg);
            } else {
                if (resultado.getFile() == null && resultado.getMensaje() != null
                        && !resultado.getMensaje().equalsIgnoreCase("")) {
                    msg += "No se puede cargar el archivo, por favor verifique. " + resultado.getMensaje() + "\n\n";
                    UiUtils.mostrarError(msg);
                } else {
                    if (resultado.getFile() != null) {
                        msg += Labels.getLabel("commons.mensaje.archivoCargado") + "\n\n";
                        msg += Labels.getLabel("commons.mensaje.registrosError") + ": " + resultado.getRegisError()
                                + "\n";
                        msg += Labels.getLabel("commons.mensaje.registrosCargados") + ": " + resultado.getRegisInser()
                                + "\n";
                        msg += Labels.getLabel("commons.mensaje.registrosActualizados") + ": "
                                + resultado.getRegisActua() + "\n";
                        UiUtils.mostrarError(msg, new EventListener<Event>() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                try {
                                    resultado.getFile()
                                            .setBytesArchivo(Base64.decodeFast(resultado.getFile().getArchivo()));
                                    mostrarBytesArchivo(resultado.getFile());
                                    BindUtils.postNotifyChange(null, null, AdminTercerosController.this, "*");
                                    mostrarPaginaResultado();
                                    cargarDatosTerecero(true);
                                } catch (Exception ex) {
                                    LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                                    UiUtils.mostrarError(Labels.getLabel("excepcion.no.identificado") + " "
                                            + new String[] { ex.getLocalizedMessage() });
                                }
                            }
                        });
                    }
                }
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(Labels.getLabel("excepcion.no.identificado") + " " + ex.getLocalizedMessage());
        }
    }

    /********************************
     * Getters and setters
     ********************************/

    /**
     * getTerceros.
     * 
     * @return Lista de terceros.
     */
    public List<CorTercero> getTerceros() {
        return terceros;
    }

    /**
     * setTerceros.
     * 
     * @param terceros.
     */
    public void setTerceros(List<CorTercero> terceros) {
        this.terceros = terceros;
    }

    /**
     * getTerceroSeleccionado.
     * 
     * @return terceroSeleccionado.
     */
    public CorTercero getTerceroSeleccionado() {
        return terceroSeleccionado;
    }

    /**
     * setTerceroSeleccionado.
     * 
     * @param terceroSeleccionado.
     */
    public void setTerceroSeleccionado(CorTercero terceroSeleccionado) {
        this.terceroSeleccionado = terceroSeleccionado;
    }

    /**
     * getMunicipios.
     * 
     * @return Lista de municipios.
     */
    public List<MdtMunicipio> getMunicipios() {
        return municipios;
    }

    /**
     * setMunicipios.
     * 
     * @param municipios
     */
    public void setMunicipios(List<MdtMunicipio> municipios) {
        this.municipios = municipios;
    }

    /**
     * getMunicipioSeleccionado.
     * 
     * @return MdtMunicipioDto.
     */
    public MdtMunicipio getMunicipioSeleccionado() {
        return municipioSeleccionado;
    }

    /**
     * setMunicipioSeleccionado.
     * 
     * @param municipioSeleccionado
     */
    public void setMunicipioSeleccionado(MdtMunicipio municipioSeleccionado) {
        this.municipioSeleccionado = municipioSeleccionado;
    }

    /**
     * getModelFiltroTercero.
     * 
     * @return Model filtro tercero.
     */
    public ListModelList<FiltroTercero> getModelFiltroTercero() {
        return modelFiltroTercero;
    }

    /**
     * setModelFiltroTercero.
     * 
     * @param modelFiltroTercero.
     */
    public void setModelFiltroTercero(ListModelList<FiltroTercero> modelFiltroTercero) {
        this.modelFiltroTercero = modelFiltroTercero;
    }

    /**
     * getFiltroTerceroSeleccionado.
     * 
     * @return FiltroTercero.
     */
    public FiltroTercero getFiltroTerceroSeleccionado() {
        return filtroTerceroSeleccionado;
    }

    /**
     * setFiltroTerceroSeleccionado.
     * 
     * @param filtroTerceroSeleccionado.
     */
    public void setFiltroTerceroSeleccionado(FiltroTercero filtroTerceroSeleccionado) {
        this.filtroTerceroSeleccionado = filtroTerceroSeleccionado;
    }

}
