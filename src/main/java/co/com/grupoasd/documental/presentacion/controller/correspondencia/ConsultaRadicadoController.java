/*
 * Archivo: ConsultaRadicadoController.java
 * Fecha creacion: 13/03/2017 04:30:33 p. m.
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
package co.com.grupoasd.documental.presentacion.controller.correspondencia;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.InternalServerErrorException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.autorizacion.AccionApp;
import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.cliente.catalogo.model.Serie;
import co.com.grupoasd.documental.cliente.catalogo.model.Subserie;
import co.com.grupoasd.documental.cliente.catalogo.model.TipoDocumental;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorCanal;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTerceroXRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorUsuarioXRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.EstadoRadicado;
import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.comun.vo.EstadosRadicado;
import co.com.grupoasd.documental.presentacion.comun.vo.TipoArchivo;
import co.com.grupoasd.documental.presentacion.comun.vo.TipoRadicado;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.controller.util.DateUtils;
import co.com.grupoasd.documental.presentacion.controller.util.GenericPagingController;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;
import co.com.grupoasd.documental.presentacion.controller.util.Utils;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.AreaService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SerieService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SubserieService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.TipoDocumentalService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.CorrespondenciaServiceFactory;
import co.com.grupoasd.documental.presentacion.service.correspondencia.dto.RadicadoDto;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorRadicadoService;

/**
 * Controller de consulta de radicados.
 * 
 * @author cestrada
 *
 */
public final class ConsultaRadicadoController extends GenericPagingController<Window> {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -7909712927419492208L;

    /**
     * Componentes UI.
     */

    @Wire
    private Textbox txtRadicado;
    @Wire
    private Combobox cmbTipoRadicado;
    @Wire
    private Combobox cmbSerie;
    @Wire
    private Combobox cmbSubserie;
    @Wire
    private Combobox cmbTipoDoc;
    @Wire
    private Combobox cmbAsignado;
    @Wire
    private Combobox cmbEntidadRemi;
    @Wire
    private Checkbox chkAnulados;
    @Wire
    private Checkbox chkRequiereRta;
    @Wire
    private Textbox txtRadicadoAsoc;
    @Wire
    private Textbox txtRadicadoExt;
    @Wire
    private Datebox dtxFechaInicial;
    @Wire
    private Datebox dtxFechaFinal;
    @Wire
    private Datebox dtxFechaVencimiento;
    @Wire
    private Grid grdRadicados;
    @Wire
    private Paging pageResult;

    /**
     * Servicios.
     */
    private CorRadicadoService correspondenciaRadicadoService;
    private AreaService areaService;
    private SerieService serieService;
    private SubserieService subserieService;
    /**
     * Abributos Controlador.
     */
    // private ListModelList<TipoRadicado> modelTipoRadicado;
    private TipoRadicado tipoRadicadoSeleccionado;
    private TipoDocumentalService tipoDocumentalService;
    private List<EstadoRadicado> estadosRadicados;
    private EstadoRadicado estadoRadicadoSeleccionado;
    private List<TipoDocumental> tiposDocumentales;
    private TipoDocumental tipoDocumentalSeleccionado;
    private List<Area> areas;
    private Area areaSeleccionada;
    private List<Serie> series;
    private Serie serieSeleccionada;
    private List<Subserie> subseries;
    private Subserie subserieSeleccionada;
    private List<CorCanal> canales;
    private CorCanal canalieSeleccionado;
    private List<RadicadoDto> radicados;
    private List<CorTerceroXRadicado> tercerosRadicados;
    private CorTerceroXRadicado terceroRadicadoSeleccionado;
    private List<CorUsuarioXRadicado> usuariosRadicados;
    private CorUsuarioXRadicado usuarioRadicadoSeleccionado;

    private static final Logger LOG = Logger.getLogger(ConsultaRadicadoController.class.getName());

    @Override
    public void onCreate(Event event) {

    }

    /**
     * Inicializacion del controller.
     */
    @Init
    private void init() {
        try {
            correspondenciaRadicadoService = CorrespondenciaServiceFactory.getCorRadicadoService();
            areaService = CatalogoServiceFactory.getAreaService();
            serieService = CatalogoServiceFactory.getSerieService();
            subserieService = CatalogoServiceFactory.getSubserieService();
            tipoDocumentalService = CatalogoServiceFactory.getTipoDocumentalService();
            areas = new ArrayList<>();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(Labels.getLabel("excepcion.no.identificado") + " " + ex.getLocalizedMessage());
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
            Utils.llenarComboboxTipoRadicado(cmbTipoRadicado);
            estadosRadicados = Utils
                    .adicionarSeleccioneEstadosRadicado(correspondenciaRadicadoService.listarEstadosRadicado());
            areas = Utils.adicionarSeleccioneAreas(
                    areaService.listar(SessionUtil.getCurrentSession().getEmpresaId(), false));
            canales = Utils.adicionarSeleccioneCanales(correspondenciaRadicadoService.listarCanales());
            usuariosRadicados = Utils
                    .adicionarSeleccioneUsuariosRadicado(correspondenciaRadicadoService.listarUsuariosConRadicados());
            tercerosRadicados = Utils
                    .adicionarSeleccioneTercerosRadicado(correspondenciaRadicadoService.listarTercerosConRadicados());
            if (!SessionUtil.accionPermitida(AccionApp.CONSULTAR_CORRESPONDENCIA_DE_DIFERENTES_USUARIOS.getId())) {
                cmbAsignado.setDisabled(true);
            }
            establecerUsuarioAsignado(SessionUtil.getCurrentSession());
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(Labels.getLabel("excepcion.no.identificado") + " " + ex.getLocalizedMessage());
        }
    }

    /**
     * cargarInformacionSerie.
     */
    @Command
    @NotifyChange({ "series", "serieSeleccionada" })
    public void cargarInformacionSerie() {
        try {
            if (series != null) {
                limpiarCombos("serie");
            }
            ListModelList<Serie> seriesTmp = new ListModelList<>(
                    Utils.adicionarSeleccioneSeries(serieService.listarPorArea(areaSeleccionada.getAreaId(), false)));
            setSeries(seriesTmp);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(Labels.getLabel("excepcion.no.identificado" + " " + ex.getLocalizedMessage()));
        }
    }

    /**
     * cargarInformacionSubserie.
     */
    @Command
    @NotifyChange({ "subseries", "subserieSeleccionada" })
    public void cargarInformacionSubserie() {
        try {
            if (subseries != null) {
                limpiarCombos("subserie");
            }
            ListModelList<Subserie> subserieTmp = new ListModelList<>(Utils.adicionarSeleccioneSubseries(
                    subserieService.listarPorSerie(serieSeleccionada.getSerieId(), false)));
            setSubseries(subserieTmp);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(Labels.getLabel("excepcion.no.identificado") + " " + ex.getLocalizedMessage());
        }
    }

    /**
     * cargarInformacionTipoDocumental.
     */
    @Command
    @NotifyChange({ "tiposDocumentales", "tipoDocumentalSeleccionado" })
    public void cargarInformacionTipoDocumental() {
        try {
            if (tiposDocumentales != null) {
                limpiarCombos("tipo documental");
            }
            ListModelList<TipoDocumental> tipoDocTmp = new ListModelList<>(Utils.adicionarSeleccioneTiposDocumentales(
                    tipoDocumentalService.listar(subserieSeleccionada.getSubserieId(), false)));
            setTiposDocumentales(tipoDocTmp);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(Labels.getLabel("excepcion.no.identificado") + " " + ex.getLocalizedMessage());
        }
    }

    /**
     * buscarRadicados.
     */
    @Command
    @NotifyChange({ "radicados" })
    public void buscarRadicados() {
        try {
            validarFechaRadicacion();
            mostrarPaginaResultado();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(Labels.getLabel("excepcion.no.identificado") + " " + ex.getLocalizedMessage());
        }
    }

    /**
     * exportarCsv.
     */
    @Command
    public void exportarCsv() {
        try {
            if (radicados != null && !radicados.isEmpty()) {
                generarArchivoCSV();
            } else {
                UiUtils.mostrarInformacion(Labels.getLabel("commons.label.consultaSinResultados"));
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(Labels.getLabel("excepcion.no.identificado") + " " + ex.getLocalizedMessage());
        }
    }

    /**
     * renderizar.
     */
    @Command
    @NotifyChange({ "grdRadicados" })
    public void renderizar() {
    }

    /**
     * paginar.
     */
    @Command
    @NotifyChange({ "pageResult", "radicados" })
    public void paginar() {
        mostrarPaginaResultado();
    }

    /**
     * mostrarPaginaResultado.
     */
    @Override
    protected void mostrarPaginaResultado() {
        try {
            int resultCount = correspondenciaRadicadoService
                    .contarRadicadosComunicacionPorFiltros(cargarDatosConsulta(null, null));
            pageResult.setTotalSize(resultCount);
            if (resultCount < 1) {
                UiUtils.mostrarInformacion(Labels.getLabel("commons.mensaje.busqueda.sinResultados"));
            }
            int pgn = pageResult.getActivePage();
            int size = pageResult.getPageSize();
            radicados = correspondenciaRadicadoService
                    .listarRadicadosComunicacionPorFiltros(cargarDatosConsulta(pgn, size));
            grdRadicados.invalidate();
        } catch (InternalServerErrorException ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(String.format(Labels.getLabel("excepcion.servicio"), ex.getLocalizedMessage()));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(Labels.getLabel("excepcion.no.identificado") + " " + ex.getLocalizedMessage());
        }
    }

    @Command
    public void cambiarEstado() {
        if (estadoRadicadoSeleccionado.getEstadoRadId() != EstadosRadicado.ASIGNADO.getId()) {
            dtxFechaVencimiento.setValue(null);
        }
    }

    @Command
    @NotifyChange({ "estadoRadicadoSeleccionado" })
    public void cambiarFechaVencimiento() {
        for (EstadoRadicado estado : estadosRadicados) {
            if (estado.getEstadoRadId() == EstadosRadicado.ASIGNADO.getId()) {
                estadoRadicadoSeleccionado = estado;
                break;
            }
        }
    }

    @Command
    @NotifyChange({ "dtxFechaFinalInicial", "dtxFechaFinal" })
    public void validarFechaRadicacion() {
        if (dtxFechaInicial.getValue() != null && dtxFechaFinal.getRawValue() != null) {
            if (DateUtils.obtenerDiferenciaDiasSinHora(dtxFechaInicial.getValue(), dtxFechaFinal.getValue()) < 0) {
                throw new WrongValueException(dtxFechaInicial,
                        Labels.getLabel("commons.mensaje.fechaInicialMayorFechaFinal"));
            }
        }
    }

    /********************************
     * Private methods
     ********************************/
    /**
     * Permite borrar la información de los combos que estan enlazados.
     * 
     * @param nivel
     *            <code>String</code> para ejecutar el borrado:<br>
     *            <code>"serie"</code> para borrar los combos de serie, subserie
     *            y tipo documental<br>
     *            <code>"subserie"</code> para borrar los combos de subserie y
     *            tipo documental<br>
     *            y <code>"tipo documental"</code> para borrar unicamente el
     *            combo de tipo documental
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
        case "tipo documental":
            if (tiposDocumentales != null) {
                tiposDocumentales.clear();
            }
            setTipoDocumentalSeleccionado(null);
            break;
        }
        if (cmbSerie != null) {
            cmbSerie.clearErrorMessage();
        }
        if (cmbSubserie != null) {
            cmbSubserie.clearErrorMessage();
        }
        if (cmbTipoDoc != null) {
            cmbTipoDoc.clearErrorMessage();
        }
    }

    /**
     * cargarDatosConsulta.
     * 
     * @param pgn
     * @param size
     * @return RadicadoDto Filtros para la consulta de radicados.
     * @throws Exception
     */
    private RadicadoDto cargarDatosConsulta(Integer pgn, Integer size) throws Exception {
        RadicadoDto filtrosConsulta = new RadicadoDto();
        filtrosConsulta.setCanalId(canalieSeleccionado != null && canalieSeleccionado.getCanalId() > -1
                ? canalieSeleccionado.getCanalId() : null);
        filtrosConsulta.setTipoDocumentalId(
                tipoDocumentalSeleccionado != null && tipoDocumentalSeleccionado.getTipoDocumentalId() > -1
                        ? tipoDocumentalSeleccionado.getTipoDocumentalId() : null);
        filtrosConsulta.setRadicado(txtRadicado != null ? txtRadicado.getText() : null);
        if (cmbTipoRadicado.getSelectedItem() != null && cmbTipoRadicado.getSelectedItem().getValue() != null) {
            filtrosConsulta.setTipoRadicacion(((TipoRadicado) cmbTipoRadicado.getSelectedItem().getValue()).getId());
        }
        filtrosConsulta
                .setEstadoId(estadoRadicadoSeleccionado != null && estadoRadicadoSeleccionado.getEstadoRadId() > -1
                        ? estadoRadicadoSeleccionado.getEstadoRadId() : null);
        filtrosConsulta.setAreaId(
                areaSeleccionada != null && areaSeleccionada.getAreaId() > -1 ? areaSeleccionada.getAreaId() : null);
        filtrosConsulta.setSerieId(serieSeleccionada != null && serieSeleccionada.getSerieId() > -1
                ? serieSeleccionada.getSerieId() : null);
        filtrosConsulta.setSubserieId(subserieSeleccionada != null && subserieSeleccionada.getSubserieId() > -1
                ? subserieSeleccionada.getSubserieId() : null);
        filtrosConsulta.setRadicadoAsociado(txtRadicadoAsoc != null ? txtRadicadoAsoc.getText() : null);
        filtrosConsulta.setRadicadoExterno(txtRadicadoExt != null ? txtRadicadoExt.getText() : null);
        filtrosConsulta
                .setAsignado(usuarioRadicadoSeleccionado != null && usuarioRadicadoSeleccionado.getUsuarioId() > -1
                        ? usuarioRadicadoSeleccionado.getUsuarioId() : null);
        filtrosConsulta.setFechaRadicado(dtxFechaInicial != null ? dtxFechaInicial.getValue() : null);
        filtrosConsulta.setFechaFinal(dtxFechaFinal != null ? dtxFechaFinal.getValue() : null);
        filtrosConsulta.setFechaVencimiento(dtxFechaVencimiento != null ? dtxFechaVencimiento.getValue() : null);
        filtrosConsulta.setRequiereRespuesta(chkRequiereRta != null ? chkRequiereRta.isChecked() : false);
        filtrosConsulta.setAnulado(chkAnulados != null ? chkAnulados.isChecked() : false);
        filtrosConsulta
                .setRemitente(terceroRadicadoSeleccionado != null && terceroRadicadoSeleccionado.getTerceroId() > -1
                        ? terceroRadicadoSeleccionado.getTerceroId() : null);
        filtrosConsulta.setOrderBy(2);
        filtrosConsulta.setOrderDesc(true);
        if (pgn != null && size != null) {
            filtrosConsulta.setFirstResult(pgn * size);
            filtrosConsulta.setMaxResults(size);
        }
        return filtrosConsulta;
    }

    /**
     * establecerUsuarioAsignado.
     * 
     * @param currentUser
     */
    private void establecerUsuarioAsignado(SesionVo currentUser) {
        if (cmbAsignado.isDisabled()) {
            boolean asignado = false;
            for (CorUsuarioXRadicado usuRad : usuariosRadicados) {
                if (usuRad.getUsuarioId() == currentUser.getUsuarioId()) {
                    usuarioRadicadoSeleccionado = usuRad;
                    asignado = true;
                    break;
                }
            }
            if (!asignado) {
                if (!cmbAsignado.isDisabled()) {
                    return;
                }
                CorUsuarioXRadicado usuarioAsig = new CorUsuarioXRadicado();
                usuarioAsig.setUsuarioId(currentUser.getUsuarioId());
                usuarioAsig.setNombreUsuario(currentUser.getNombreCompletoUsuario());
                usuarioRadicadoSeleccionado = usuarioAsig;
            }
        }
    }

    /**
     * generarArchivoCSV.
     * 
     * @throws Exception
     */
    private void generarArchivoCSV() throws Exception {
        try {
            InfoMedia infoMedia = correspondenciaRadicadoService
                    .obtenerArchivoRadicadosComunicacionPorFiltros(cargarDatosConsulta(null, null), TipoArchivo.CSV);
            mostrarBytesArchivo(infoMedia);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(
                    String.format(Labels.getLabel("commons.mensaje.falloExportacionCsv"), ex.getLocalizedMessage()));
        }
    }

    /********************************
     * Getters and setters
     ********************************/

    /**
     * getEstadosRadicados
     * 
     * @return lista de estados radicados
     */
    public List<EstadoRadicado> getEstadosRadicados() {
        return estadosRadicados;
    }

    /**
     * setEstadosRadicados
     * 
     * @param estadosRadicados
     *            estados radicados
     */
    public void setEstadosRadicados(List<EstadoRadicado> estadosRadicados) {
        this.estadosRadicados = estadosRadicados;
    }

    /**
     * getEstadoRadicadoSeleccionado
     * 
     * @return EstadoRadicado estado radicado seleccionado en el combobox
     */
    public EstadoRadicado getEstadoRadicadoSeleccionado() {
        return estadoRadicadoSeleccionado;
    }

    /**
     * setEstadoRadicadoSeleccionado
     * 
     * @param estadoRadicadoSeleccionado
     *            estado radicado seleccionado en el combobox
     */
    public void setEstadoRadicadoSeleccionado(EstadoRadicado estadoRadicadoSeleccionado) {
        this.estadoRadicadoSeleccionado = estadoRadicadoSeleccionado;
    }

    /**
     * getTipoRadicadoSeleccionado.
     * 
     * @return TipoRadicado
     */
    public TipoRadicado getTipoRadicadoSeleccionado() {
        return tipoRadicadoSeleccionado;
    }

    /**
     * setTipoRadicadoSeleccionado.
     * 
     * @param tipoRadicadoSeleccionado
     */
    public void setTipoRadicadoSeleccionado(TipoRadicado tipoRadicadoSeleccionado) {
        this.tipoRadicadoSeleccionado = tipoRadicadoSeleccionado;
    }

    // /**
    // * getModelTipoRadicado.
    // *
    // * @return Lista Modelo para tipo radicado.
    // */
    // public ListModelList<TipoRadicado> getModelTipoRadicado() {
    // return modelTipoRadicado;
    // }
    //
    // /**
    // * setModelTipoRadicado.
    // *
    // * @param modelTipoRadicado
    // */
    // public void setModelTipoRadicado(ListModelList<TipoRadicado>
    // modelTipoRadicado) {
    // this.modelTipoRadicado = modelTipoRadicado;
    // }

    /**
     * getAreas.
     * 
     * @return Lista de áreas.
     */
    public List<Area> getAreas() {
        return areas;
    }

    /**
     * setAreas.
     * 
     * @param areas
     */
    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    /**
     * getAreaSeleccionada.
     * 
     * @return Area
     */
    public Area getAreaSeleccionada() {
        return areaSeleccionada;
    }

    /**
     * setAreaSeleccionada.
     * 
     * @param areaSeleccionada
     */
    public void setAreaSeleccionada(Area areaSeleccionada) {
        this.areaSeleccionada = areaSeleccionada;
    }

    /**
     * getTiposDocumentales.
     * 
     * @return Lista de tipos documentales.
     */
    public List<TipoDocumental> getTiposDocumentales() {
        return tiposDocumentales;
    }

    /**
     * setTiposDocumentales.
     * 
     * @param tiposDocumentales
     */
    public void setTiposDocumentales(List<TipoDocumental> tiposDocumentales) {
        this.tiposDocumentales = tiposDocumentales;
    }

    /**
     * getTipoDocumentalSeleccionado.
     * 
     * @return TipoDocumental
     */
    public TipoDocumental getTipoDocumentalSeleccionado() {
        return tipoDocumentalSeleccionado;
    }

    /**
     * setTipoDocumentalSeleccionado.
     * 
     * @param tipoDocumentalSeleccionado
     */
    public void setTipoDocumentalSeleccionado(TipoDocumental tipoDocumentalSeleccionado) {
        this.tipoDocumentalSeleccionado = tipoDocumentalSeleccionado;
    }

    /**
     * getSeries.
     * 
     * @return Lista de series.
     */
    public List<Serie> getSeries() {
        return series;
    }

    /**
     * setSeries.
     * 
     * @param series
     */
    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    /**
     * getSerieSeleccionada.
     * 
     * @return Serie
     */
    public Serie getSerieSeleccionada() {
        return serieSeleccionada;
    }

    /**
     * setSerieSeleccionada.
     * 
     * @param serieSeleccionada
     */
    public void setSerieSeleccionada(Serie serieSeleccionada) {
        this.serieSeleccionada = serieSeleccionada;
    }

    /**
     * getSubseries.
     * 
     * @return Lista de subseries.
     */
    public List<Subserie> getSubseries() {
        return subseries;
    }

    /**
     * setSubseries.
     * 
     * @param subseries
     */
    public void setSubseries(List<Subserie> subseries) {
        this.subseries = subseries;
    }

    /**
     * getSubserieSeleccionada.
     * 
     * @return Subserie
     */
    public Subserie getSubserieSeleccionada() {
        return subserieSeleccionada;
    }

    /**
     * setSubserieSeleccionada.
     * 
     * @param subserieSeleccionada
     */
    public void setSubserieSeleccionada(Subserie subserieSeleccionada) {
        this.subserieSeleccionada = subserieSeleccionada;
    }

    /**
     * getCanales.
     * 
     * @return Lista de canales.
     */
    public List<CorCanal> getCanales() {
        return canales;
    }

    /**
     * setCanales.
     * 
     * @param canales
     */
    public void setCanales(List<CorCanal> canales) {
        this.canales = canales;
    }

    /**
     * getCanalieSeleccionado.
     * 
     * @return
     */
    public CorCanal getCanalieSeleccionado() {
        return canalieSeleccionado;
    }

    /**
     * setCanalieSeleccionado.
     * 
     * @param canalieSeleccionado
     */
    public void setCanalieSeleccionado(CorCanal canalieSeleccionado) {
        this.canalieSeleccionado = canalieSeleccionado;
    }

    /**
     * getRadicados
     * 
     * @return Lista de radicados DTO
     */
    public List<RadicadoDto> getRadicados() {
        return radicados;
    }

    /**
     * setRadicados.
     * 
     * @param radicados
     */
    public void setRadicados(List<RadicadoDto> radicados) {
        this.radicados = radicados;
    }

    /**
     * getTercerosRadicados.
     * 
     * @return Lista de terceros con radicados.
     */
    public List<CorTerceroXRadicado> getTercerosRadicados() {
        return tercerosRadicados;
    }

    /**
     * tercerosRadicados.
     * 
     * @param tercerosRadicados
     */
    public void setTercerosRadicados(List<CorTerceroXRadicado> tercerosRadicados) {
        this.tercerosRadicados = tercerosRadicados;
    }

    /**
     * getTerceroRadicadoSeleccionado.
     * 
     * @return CorTerceroXRadicado
     */
    public CorTerceroXRadicado getTerceroRadicadoSeleccionado() {
        return terceroRadicadoSeleccionado;
    }

    /**
     * setTerceroRadicadoSeleccionado.
     * 
     * @param terceroRadicadoSeleccionado
     */
    public void setTerceroRadicadoSeleccionado(CorTerceroXRadicado terceroRadicadoSeleccionado) {
        this.terceroRadicadoSeleccionado = terceroRadicadoSeleccionado;
    }

    /**
     * getUsuariosRadicados.
     * 
     * @return Lista de usuarios con radicados.
     */
    public List<CorUsuarioXRadicado> getUsuariosRadicados() {
        return usuariosRadicados;
    }

    /**
     * setUsuariosRadicados.
     * 
     * @param usuariosRadicados
     */
    public void setUsuariosRadicados(List<CorUsuarioXRadicado> usuariosRadicados) {
        this.usuariosRadicados = usuariosRadicados;
    }

    /**
     * getUsuarioRadicadoSeleccionado.
     * 
     * @return CorUsuarioXRadicado
     */
    public CorUsuarioXRadicado getUsuarioRadicadoSeleccionado() {
        return usuarioRadicadoSeleccionado;
    }

    /**
     * setUsuarioRadicadoSeleccionado.
     * 
     * @param usuarioRadicadoSeleccionado
     */
    public void setUsuarioRadicadoSeleccionado(CorUsuarioXRadicado usuarioRadicadoSeleccionado) {
        this.usuarioRadicadoSeleccionado = usuarioRadicadoSeleccionado;
    }

    @Override
    protected EventListener<Event> ordenarLista() {
        // TODO Auto-generated method stub
        return null;
    }

}
