/*
 * Archivo: ActualizarRadicadoController.java
 * Fecha creacion: 13/03/2017 10:04:34 a. m.
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

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.ws.rs.ServerErrorException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.cliente.catalogo.model.Serie;
import co.com.grupoasd.documental.cliente.catalogo.model.Subserie;
import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorAdjunto;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorCanal;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorComentario;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTercero;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTipoEmbalaje;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTransportadora;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorrespondenciaBitacoraRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.EstadoRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.FormatosPermitidosArchivo;
import co.com.grupoasd.documental.cliente.correspondencia.model.RotuloInfo;
import co.com.grupoasd.documental.presentacion.comun.PropertiesMessagesUtil;
import co.com.grupoasd.documental.presentacion.comun.vo.TipoRadicado;
import co.com.grupoasd.documental.presentacion.controller.util.GenericController;
import co.com.grupoasd.documental.presentacion.controller.util.Reportes;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;
import co.com.grupoasd.documental.presentacion.controller.util.Utils;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.AreaService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SerieService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SubserieService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.UsuarioService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.CorrespondenciaServiceFactory;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorAdjuntoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorCanalService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTipoEmbalajeService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTransportadoraService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorrespondenciaBitacoraRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.EstadoRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.FormatosPermitidosArchivoService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Controlador que implementa la logica que maneja la vista
 * actualizar_correspondencia
 * 
 * @author cestrada
 */
public class DetalleRadicadoController extends GenericController<Window> {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -3825384294748943496L;
    /**
     * Controles
     */

    private Spinner spinCantFolios;
    private Spinner spinCantEmbalaje;
    private Popup popCorreoCert;
    private Image imgRotulo;
    private Datebox dtxFechaCorreoCertif;

    /**
     * Textbox
     */
    private Textbox txtAsunto;
    private Textbox txtRadicadoAsociado;
    private Textbox txtNombreFuncionario;
    private Textbox txtIdFuncionario;
    private Textbox txtRadicadoRemitente;
    private Textbox txtComentario;
    private Textbox txtGuia;

    /**
     * Combobox
     */
    private Combobox cmbCanalRecepcion;
    private Combobox cmbEstado;
    private Combobox cmbNombreTercero;
    private Combobox cmbNroIndentificacion;
    private Combobox cmbNombreUsuarioSalida;
    private Combobox cmbNombreUsuarioInterna;
    private Combobox cmbTipoRadicacion;
    private Combobox cmbAreaProceso;
    private Combobox cmbSerie;
    private Combobox cmbSubSerie;
    private Combobox cmbTransportadora;
    private Combobox cmbTipoEmbalaje;

    /**
     * Botones
     */
    private Button btnCorreroCertificado;

    /**
     * Listbox
     */
    private Listbox lstDatosDestinatarioEntrada;
    private Listbox lstComentarios;
    private Listbox lstDatosDestinatarioSalida;
    private Listbox lstDatosDestinatarioInterna;
    private Listbox lstTrazabilidad;
    private Listbox lstArchAdjuntos;
    private Listbox lstFormatos;

    /**
     * Label
     */
    private Label lblDireccionRemitente;
    private Label lblCiudadRemitente;
    private Label lblResponsableRemitente;
    private Label lblCargoRemitente;
    private Label lblDependenciaRemitente;
    private Label lblSecuencia;
    private Label lblRadicado;
    private Label lblFechaDelSistema;
    private Label lblCargoRemInterna;
    private Label lblAreaProcesoRemInterna;
    private Label lblCargoRemSalida;
    private Label lblAreaProcesoRemSalida;

    /**
     * Groupbox
     */
    private Groupbox grbRemitenteEntrada;
    private Groupbox grbDatosDestinatarioEntrada;
    private Groupbox grbRemitenteSalida;
    private Groupbox grbDatosDestinatarioSalida;
    private Groupbox grbRemitenteInterna;
    private Groupbox grbDatosDestinatarioInterna;

    /**
     * Services
     */
    private AreaService areaService;
    private SubserieService subSerieService;
    private SerieService serieService;
    private CorCanalService corCanalService;
    private EstadoRadicadoService corEstadoRadicadoService;
    private CorRadicadoService corRadicadoService;
    private UsuarioService usuarioService;
    private CorrespondenciaBitacoraRadicadoService bitacoraRadicadoService;
    private FormatosPermitidosArchivoService permitidosArchivoService;
    private CorTransportadoraService corTransportadoraService;
    private CorTipoEmbalajeService corTipoEmbalajeService;
    private CorAdjuntoService adjuntoService;

    /**
     * Objetos generales
     */
    private CorRadicado radicado;
    private List<Usuario> usuariosDestInterna;
    private List<Usuario> usuariosDestEntrada;
    private List<CorTercero> tercerosDestSalida;
    private Usuario usuarioDestPrincipalEntrada;
    private CorTercero terceroDestPrincipalSalida;
    private Usuario usuarioDestPrincipalInterna;
    private List<FormatosPermitidosArchivo> permitidosArchivos;
    private List<EstadoRadicado> estadosRadicado;

    private Long radicadoId = (Long) args.get("radicadoId");

    @Override
    public void onCreate(Event event) {
        try {
            /**
             * Instancia de los servicios
             */
            radicadoId = (Long) args.get("radicadoId");
            if (radicadoId == null) {
                Clients.showNotification(
                        PropertiesMessagesUtil.instance().get("correspondencia.commons.label.sinRadicado"), "error",
                        self.getParent(), "middle_center", 3000, true);
                self.onClose();
                return;
            }
            areaService = CatalogoServiceFactory.getAreaService();
            subSerieService = CatalogoServiceFactory.getSubserieService();
            serieService = CatalogoServiceFactory.getSerieService();
            corCanalService = CorrespondenciaServiceFactory.getCorCanalService();
            corEstadoRadicadoService = CorrespondenciaServiceFactory.getEstadoRadicadoService();
            corRadicadoService = CorrespondenciaServiceFactory.getCorRadicadoService();
            usuarioService = CatalogoServiceFactory.getUsuarioService();
            bitacoraRadicadoService = CorrespondenciaServiceFactory.getCorrespondenciaBitacoraRadicadoService();
            permitidosArchivoService = CorrespondenciaServiceFactory.getFormatosPermitidosArchivoService();
            corTransportadoraService = CorrespondenciaServiceFactory.getCorTransportadoraService();
            corTipoEmbalajeService = CorrespondenciaServiceFactory.getCorTipoEmbalajeService();
            adjuntoService = CorrespondenciaServiceFactory.getCorAdjuntoService();
            
            usuariosDestInterna = new ArrayList<>();
            usuariosDestEntrada = new ArrayList<>();
            tercerosDestSalida = new ArrayList<>();

            /**
             * Asignacion de datos iniciales
             */
            Utils.llenarComboboxCorCanal(corCanalService.listar(), cmbCanalRecepcion);
            Utils.llenarComboboxEstadoRadicado(corEstadoRadicadoService.listar(), cmbEstado);
            Utils.llenarComboboxTipoEmbalaje(corTipoEmbalajeService.listar(), cmbTipoEmbalaje);
            Utils.llenarComboboxCorTransportadora(corTransportadoraService.listar(), cmbTransportadora);

            /**
             * Lista archivos permitidos
             */
            permitidosArchivos = permitidosArchivoService.listarTodos();
            listarFormatosPermitidos();

            /**
             * Listado de estadosRadicados
             */
            estadosRadicado = corEstadoRadicadoService.listar();

            /**
             * Traemos la informacion del radicado por id
             */

            traerInformacionRadicado();
        } finally {
            deshabilitarComponentes(grbDatosDestinatarioEntrada, true);
            deshabilitarComponentes(grbDatosDestinatarioSalida, true);
            deshabilitarComponentes(grbDatosDestinatarioInterna, true);
        }
    }

    /**
     * Agrega a un listbox los formatos de archivo permitidos.
     */
    private void listarFormatosPermitidos() {
        if (permitidosArchivos != null && permitidosArchivos.size() > 0) {
            Listitem item = new Listitem();
            item.setParent(lstFormatos);
            for (int i = 0; i < permitidosArchivos.size(); i++) {
                if (i % 3 == 0) {
                    item = new Listitem();
                    item.setParent(lstFormatos);
                }
                item.appendChild(
                        new Listcell("." + permitidosArchivos.get(i).getExtension(), "/recursos/icons/dot.png"));
            }
        }
    }

    /**
     * Limpia las listbox
     */
    private void limpiarListbox() {
        if (lstArchAdjuntos.getItems() != null) {
            lstArchAdjuntos.getItems().clear();
        }

        if (lstComentarios.getItems() != null) {
            lstComentarios.getItems().clear();
        }

        if (lstDatosDestinatarioEntrada.getItems() != null) {
            lstDatosDestinatarioEntrada.getItems().clear();
        }

        if (lstDatosDestinatarioInterna.getItems() != null) {
            lstDatosDestinatarioInterna.getItems().clear();
        }

        if (lstDatosDestinatarioSalida.getItems() != null) {
            lstDatosDestinatarioSalida.getItems().clear();
        }

        if (lstTrazabilidad.getItems() != null) {
            lstTrazabilidad.getItems().clear();
        }

    }

    /**
     * Obtiene la información asociada al radicado
     */
    private void traerInformacionRadicado() {

        /**
         * Inicializamos los componentes
         */
        grbRemitenteEntrada.setVisible(true);
        grbDatosDestinatarioEntrada.setVisible(true);
        grbRemitenteSalida.setVisible(false);
        grbDatosDestinatarioSalida.setVisible(false);
        grbRemitenteInterna.setVisible(false);
        grbDatosDestinatarioInterna.setVisible(false);

        try {
            radicado = corRadicadoService.obtenerPorId(radicadoId);
        } catch (Exception e) {
            if (e instanceof ServerErrorException) {
                ServerErrorException ex = (ServerErrorException) e;
                if (ex.getResponse() != null && ex.getResponse().getHeaders() != null) {
                    List<Object> myResponses = ex.getResponse().getHeaders().get(Utils.LLAVE_CODIGOS_ERROR_SERVER);
                    if (myResponses != null && myResponses.size() > 0) {
                        if (myResponses.contains(Utils.CODIGO_ERROR_IO_EXCEPTION)) {
                            Messagebox.show(
                                    Labels.getLabel(
                                            "correspondencia.asignarCorrespondencia.mensaje.errorAdjuntosObtener"),
                                    Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
                        }
                    }
                }
            }
            return;
        }

        Comboitem comboitemTipoRadicado;

        /**
         * Limpiamos los controles
         */
        limpiarListbox();

        /**
         * Se identifica el tipo de radicado que viene por parametro
         */
        TipoRadicado tipo = TipoRadicado.getTipoCorrespondencia(radicado.getTipoRadicacion());

        switch (tipo) {
        case ENTRADA:
            comboitemTipoRadicado = new Comboitem();
            comboitemTipoRadicado.setValue(TipoRadicado.ENTRADA);
            comboitemTipoRadicado.setLabel(TipoRadicado.ENTRADA.getNombre());
            cmbTipoRadicacion.appendChild(comboitemTipoRadicado);
            cmbTipoRadicacion.setSelectedItem(comboitemTipoRadicado);
            grbRemitenteEntrada.setVisible(true);
            grbDatosDestinatarioEntrada.setVisible(true);
            grbRemitenteSalida.setVisible(false);
            grbDatosDestinatarioSalida.setVisible(false);
            grbRemitenteInterna.setVisible(false);
            grbDatosDestinatarioInterna.setVisible(false);

            /**
             * Obtengo los dados del remitente <Tercero>
             */
            CorTercero corTercero = radicado.getTerceroRemitente();
            if (corTercero != null) {
                Comboitem itemTercero = new Comboitem();
                itemTercero.setValue(corTercero);
                itemTercero.setLabel(corTercero.getNombre());
                cmbNombreTercero.appendChild(itemTercero);
                cmbNombreTercero.setSelectedItem(itemTercero);

                Comboitem comboNroIden = new Comboitem();
                comboNroIden.setValue(corTercero);
                comboNroIden.setLabel(corTercero.getNumero());
                cmbNroIndentificacion.appendChild(comboNroIden);
                cmbNroIndentificacion.setSelectedItem(comboNroIden);
                lblDireccionRemitente.setValue(corTercero.getDireccion());
                lblCiudadRemitente.setValue(corTercero.getMunicipio());
                lblResponsableRemitente.setValue(corTercero.getResponsable());
                lblCargoRemitente.setValue(corTercero.getCargo());
                lblDependenciaRemitente.setValue(corTercero.getDependencia());
            }

            usuarioDestPrincipalEntrada = radicado.getUsuarioPrincipal();

            /**
             * Funcionario
             */
            txtNombreFuncionario.setValue(radicado.getNombreFuncionario());
            txtIdFuncionario.setValue(radicado.getNroIdentificacionFunc());

            txtRadicadoRemitente.setValue(radicado.getRadicadoExterno());

            /**
             * Obtengo los datos del destinatario <Usuario>
             */
            usuariosDestEntrada = radicado.getUsuariosDestinatarios();

            if (usuariosDestEntrada == null) {
                usuariosDestEntrada = new ArrayList<>();
            }

            if (usuarioDestPrincipalEntrada != null) {
                usuariosDestEntrada.add(usuarioDestPrincipalEntrada);
            }

            if (usuariosDestEntrada != null && usuariosDestEntrada.size() > 0) {
                for (Usuario usuario : usuariosDestEntrada) {
                    llenarDatosDestUsuarioEntrada(usuario);
                }
            }

            /**
             * Estado
             */
            definirEstadoRadicadoEntrada();

            /**
             * Radicado asociado
             */
            txtRadicadoAsociado.setDisabled(true);

            break;

        case SALIDA:
            comboitemTipoRadicado = new Comboitem();
            comboitemTipoRadicado.setValue(TipoRadicado.SALIDA);
            comboitemTipoRadicado.setLabel(TipoRadicado.SALIDA.getNombre());
            cmbTipoRadicacion.appendChild(comboitemTipoRadicado);
            cmbTipoRadicacion.setSelectedItem(comboitemTipoRadicado);
            grbRemitenteSalida.setVisible(true);
            grbDatosDestinatarioSalida.setVisible(true);
            grbRemitenteEntrada.setVisible(false);
            grbDatosDestinatarioEntrada.setVisible(false);
            grbRemitenteInterna.setVisible(false);
            grbDatosDestinatarioInterna.setVisible(false);

            /**
             * Información usuario remitente
             */
            Usuario usuarioRemitente = radicado.getUsuarioRemitente();
            if (usuarioRemitente != null) {
                Comboitem comboitem = new Comboitem();
                comboitem.setValue(usuarioRemitente);
                comboitem.setLabel(usuarioRemitente.getNombreCompleto());
                cmbNombreUsuarioSalida.appendChild(comboitem);
                cmbNombreUsuarioSalida.setSelectedItem(comboitem);
                lblCargoRemSalida.setValue(usuarioRemitente.getNombreRol());
                lblAreaProcesoRemSalida.setValue(usuarioRemitente.getNombreArea());
            }

            terceroDestPrincipalSalida = radicado.getTerceroPrincipal();

            /**
             * Terceros distinatarios
             */
            tercerosDestSalida = radicado.getTercerosDestinatarios();

            if (tercerosDestSalida == null) {
                tercerosDestSalida = new ArrayList<>();
            }

            if (terceroDestPrincipalSalida != null) {
                tercerosDestSalida.add(terceroDestPrincipalSalida);
            }

            if (tercerosDestSalida != null && tercerosDestSalida.size() > 0) {
                for (CorTercero tercero : tercerosDestSalida) {
                    llenarDatosDestTerceroSalida(tercero);
                }
            }

            /**
             * Estado radicado: <br>
             * - Cuando el Radicado Requiere Respuesta o no Requiere Respuesta y
             * el tipo de radicación es 'Salida', <br>
             * el sistema presenta el campo estado con las opciones: 'Recibido'
             * y 'Gestionado'. Cuando el estado es <br>
             * 'Gestionado' el sistema nuestra el campo como solo lectura.
             */
            if (estadosRadicado != null && estadosRadicado.size() > 0) {
                if (radicado.getEstadoId() == Utils.ID_ESTADO_RADICADO_GESTIONADO) {
                    EstadoRadicado estadoRadicado = estadosRadicado.stream()
                            .filter(x -> x.getEstadoRadId() == radicado.getEstadoId()).findFirst().get();
                    Comboitem comboitem = new Comboitem();
                    comboitem.setValue(estadoRadicado);
                    comboitem.setLabel(estadoRadicado.getNombre());
                    cmbEstado.appendChild(comboitem);
                    cmbEstado.setSelectedItem(comboitem);
                } else {
                    estadosRadicado = estadosRadicado.stream()
                            .filter(x -> x.getEstadoRadId() == Utils.ID_ESTADO_RADICADO_RECIBIDO
                                    || x.getEstadoRadId() == Utils.ID_ESTADO_RADICADO_GESTIONADO)
                            .collect(Collectors.toList());
                    Utils.llenarComboboxEstadoRadicado(estadosRadicado, cmbEstado);

                    if (Utils.isValidoCombobox(cmbEstado)) {
                        for (Comboitem row : cmbEstado.getItems()) {
                            EstadoRadicado corEstadoRadicado = (EstadoRadicado) row.getValue();
                            if (corEstadoRadicado != null
                                    && corEstadoRadicado.getEstadoRadId() == radicado.getEstadoId()) {
                                cmbEstado.setSelectedItem(row);
                                break;
                            }
                        }
                    }
                }
            }

            txtRadicadoAsociado.setValue(radicado.getRadicadoAsociado());

            break;

        case INTERNA:
            comboitemTipoRadicado = new Comboitem();
            comboitemTipoRadicado.setValue(TipoRadicado.INTERNA);
            comboitemTipoRadicado.setLabel(TipoRadicado.INTERNA.getNombre());
            cmbTipoRadicacion.appendChild(comboitemTipoRadicado);
            cmbTipoRadicacion.setSelectedItem(comboitemTipoRadicado);
            grbRemitenteInterna.setVisible(true);
            grbDatosDestinatarioInterna.setVisible(true);
            grbRemitenteEntrada.setVisible(false);
            grbDatosDestinatarioEntrada.setVisible(false);
            grbRemitenteSalida.setVisible(false);
            grbDatosDestinatarioSalida.setVisible(false);

            /**
             * Usuario remitente
             */
            Usuario usuarioRemitenteInterna = radicado.getUsuarioRemitente();
            if (usuarioRemitenteInterna != null) {
                Comboitem comboitem = new Comboitem();
                comboitem.setValue(usuarioRemitenteInterna);
                comboitem.setLabel(usuarioRemitenteInterna.getNombreCompleto());
                cmbNombreUsuarioInterna.appendChild(comboitem);
                cmbNombreUsuarioInterna.setSelectedItem(comboitem);
                lblCargoRemInterna.setValue(usuarioRemitenteInterna.getNombreRol());
                lblAreaProcesoRemInterna.setValue(usuarioRemitenteInterna.getNombreArea());
            }

            usuarioDestPrincipalInterna = radicado.getUsuarioPrincipal();

            /**
             * Listamos los usuarios destinatarios
             */
            usuariosDestInterna = radicado.getUsuariosDestinatarios();

            if (usuariosDestInterna == null) {
                usuariosDestInterna = new ArrayList<>();
            }

            if (usuarioDestPrincipalInterna != null) {
                usuariosDestInterna.add(usuarioDestPrincipalInterna);
            }

            if (usuariosDestInterna != null && usuariosDestInterna.size() > 0) {
                for (Usuario usuario : usuariosDestInterna) {
                    llenarDatosDestUsuarioInterna(usuario);
                }
            }

            /**
             * Estado Radicado
             */
            definirEstadoRadicadoInterno();

            txtRadicadoAsociado.setValue(radicado.getRadicadoAsociado());

            break;
        default:
            return;
        }

        lblSecuencia.setValue(radicado.getSecuencia() + "");
        lblRadicado.setValue(radicado.getRadicado());
        spinCantFolios.setValue(radicado.getCantidadFolios());
        lblFechaDelSistema.setValue(dateTimeFormat(radicado.getFechaRadicacion()));

        /**
         * Canal recepcion
         */
        if (Utils.isValidoCombobox(cmbCanalRecepcion)) {
            for (Comboitem row : cmbCanalRecepcion.getItems()) {
                CorCanal canal = (CorCanal) row.getValue();
                if (canal != null && canal.getCanalId() == radicado.getCanalId()) {
                    cmbCanalRecepcion.setSelectedItem(row);
                    break;
                }
            }
        }

        /**
         * Definimos si el tipo de canal es correo certificado para cargar los
         * datos en la ventana popup
         */
        if (radicado.getCanalId() == Utils.ID_CANAL_CORREO_CERTIFICADO) {
            btnCorreroCertificado.setVisible(true);
            /**
             * Tipo Embalaje
             */
            if (Utils.isValidoCombobox(cmbTipoEmbalaje)) {
                for (Comboitem row : cmbTipoEmbalaje.getItems()) {
                    CorTipoEmbalaje corTipoEmbalaje = (CorTipoEmbalaje) row.getValue();
                    if (corTipoEmbalaje != null
                            && corTipoEmbalaje.getTipoEmbalajeId() == radicado.getTipoEmbalajeId()) {
                        cmbTipoEmbalaje.setSelectedItem(row);
                        break;
                    }
                }
            }

            /**
             * Transportadora
             */
            if (Utils.isValidoCombobox(cmbTransportadora)) {
                for (Comboitem row : cmbTransportadora.getItems()) {
                    CorTransportadora corTransportadora = (CorTransportadora) row.getValue();
                    if (corTransportadora != null
                            && corTransportadora.getTransportadoraId() == radicado.getTransportadoraId()) {
                        cmbTransportadora.setSelectedItem(row);
                        break;
                    }
                }
            }

            /**
             * Guia
             */
            txtGuia.setValue(radicado.getNumeroGuia());
            dtxFechaCorreoCertif.setValue(radicado.getFechaOperacion());

        }

        /**
         * Areas
         */
        Utils.llenarComboboxArea(
                areaService.listar(SessionUtil.getCurrentSession().getEmpresaId(), Utils.ESTADO_EMPRESA_INACTIVA),
                cmbAreaProceso);
        if (Utils.isValidoCombobox(cmbAreaProceso)) {
            for (Comboitem row : cmbAreaProceso.getItems()) {
                Area area = (Area) row.getValue();
                if (area != null && area.getAreaId() == radicado.getArea().getAreaId()) {
                    cmbAreaProceso.setSelectedItem(row);
                    break;
                }
            }
        }

        /**
         * Series
         */
        Utils.llenarComboboxSerie(
                serieService.listarPorArea(radicado.getArea().getAreaId(), Utils.ESTADO_SERIE_INACTIVA), cmbSerie);
        if (Utils.isValidoCombobox(cmbSerie)) {
            for (Comboitem row : cmbSerie.getItems()) {
                Serie serie = (Serie) row.getValue();
                if (serie != null && serie.getSerieId() == radicado.getSerie().getSerieId()) {
                    cmbSerie.setSelectedItem(row);
                    break;
                }
            }
        }

        /**
         * Subserie
         */
        Utils.llenarComboboxSubSerie(
                subSerieService.listarPorSerie(radicado.getSerie().getSerieId(), Utils.ESTADO_SUBSERIE_INACTIVA),
                cmbSubSerie);
        if (Utils.isValidoCombobox(cmbSubSerie)) {
            for (Comboitem row : cmbSubSerie.getItems()) {
                Subserie subserie = (Subserie) row.getValue();
                if (subserie != null && subserie.getSubserieId() == radicado.getSubserie().getSubserieId()) {
                    cmbSubSerie.setSelectedItem(row);
                    break;
                }
            }
        }

        txtAsunto.setValue(radicado.getAsunto());

        /**
         * Listamos los comentarios asociados al radicado
         */
        List<CorComentario> listComentarios = radicado.getComentarios();
        if (listComentarios != null && listComentarios.size() > 0)
            for (CorComentario comentario : listComentarios) {
                Listitem item = new Listitem();
                item.setValue(comentario);
                item.setParent(lstComentarios);
                item.appendChild(new Listcell(dateTimeFormat(comentario.getFecha())));
                Usuario usuario = usuarioService.obtenerPorId(comentario.getUsuarioId());
                item.appendChild(new Listcell(usuario.getNombreCompleto()));
                item.appendChild(new Listcell(comentario.getComentario()));
            }

        /**
         * Obtenemos los adjuntos relacionados al radicado
         */
        List<CorAdjunto> corAdjuntos = radicado.getCorAdjuntos();
        if (corAdjuntos != null && corAdjuntos.size() > 0)
            for (CorAdjunto corAdjunto : corAdjuntos) {
                Listitem item = new Listitem();
                item.setValue(corAdjunto);
                item.setParent(lstArchAdjuntos);

                String nombreArchivo = corAdjunto.getRuta().contains("\\")
                        ? corAdjunto.getRuta().substring(1, corAdjunto.getRuta().length()) : corAdjunto.getRuta();
                corAdjunto.setRuta(nombreArchivo);

                item.appendChild(new Listcell(corAdjunto.getRuta()));
                item.appendChild(new Listcell(dateTimeFormat(corAdjunto.getFecha())));

                Usuario usuario = usuarioService.obtenerPorId(corAdjunto.getUsuarioId());
                item.appendChild(new Listcell(usuario.getNombreCompleto()));

                item.appendChild(new Listcell(corAdjunto.getNombreTipoDocumental()));

                Listcell listcell = new Listcell();
                listcell.setParent(item);

                Toolbarbutton toolbarVer = new Toolbarbutton("", "/recursos/icons/search_file.png");
                toolbarVer.setParent(listcell);
                toolbarVer.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
                    @Override
                    public void onEvent(Event arg0) throws Exception {
                        mostrarAdjuntos(corAdjunto);
                    }
                });
            }

        /**
         * Listamos la trazabilidad
         */
        List<CorrespondenciaBitacoraRadicado> bitacoraRadicados = bitacoraRadicadoService
                .listarPorRadicadoId(radicado.getRadicadoId());
        if (bitacoraRadicados != null && bitacoraRadicados.size() > 0)
            for (CorrespondenciaBitacoraRadicado bitacoraRadicado : bitacoraRadicados) {
                Listitem item = new Listitem();
                item.setValue(bitacoraRadicado);
                item.setParent(lstTrazabilidad);
                item.appendChild(new Listcell(dateTimeFormat(bitacoraRadicado.getFecha())));
                Usuario usuario = usuarioService.obtenerPorId(bitacoraRadicado.getIdUsuario());
                item.appendChild(new Listcell(usuario.getNombreCompleto()));
                item.appendChild(new Listcell(bitacoraRadicado.getCampo()));
                String valorAnterior = bitacoraRadicado.getValorAnterior() != null ? bitacoraRadicado.getValorAnterior()
                        : "";
                String valorNuevo = bitacoraRadicado.getValorNuevo() != null ? bitacoraRadicado.getValorNuevo() : "";
                item.appendChild(new Listcell(valorAnterior.concat(" => ").concat(valorNuevo)));
            }
    }

    /**
     * Action para abrir la ventana del correo certificado
     * 
     * @param event
     */
    public void onClick$btnCorreroCertificado(Event event) {
        txtGuia.setSclass("");
        txtGuia.invalidate();

        cmbTransportadora.setSclass("");
        cmbTransportadora.invalidate();

        dtxFechaCorreoCertif.setSclass("");
        dtxFechaCorreoCertif.invalidate();

        cmbTipoEmbalaje.setSclass("");
        cmbTipoEmbalaje.invalidate();

        spinCantEmbalaje.setSclass("");
        spinCantEmbalaje.invalidate();

        popCorreoCert.open(btnCorreroCertificado, "after_start");
    }

    /**
     * Button action aceptar correo certificado
     * 
     * @param event
     */
    public void onClick$btnAceptarCorreoCertf(Event event) {
        popCorreoCert.close();
    }

    /**
     * Definir estado radicado Entrada
     */
    private void definirEstadoRadicadoEntrada() {

        if (estadosRadicado == null || estadosRadicado.size() == 0) {
            return;
        }

        /**
         * Cuando el Radicado Requiere Respuesta y el tipo de radicación es
         * 'Entrada', el sistema presenta el campo estado con las opciones:
         * 'Recibido' y 'Radicado'. Cuando el estado es 'Asignado' y
         * 'Gestionado' el sistema nuestra el campo como solo lectura
         */
        if (radicado.isRequiereRespuesta()) {
            if (radicado.getEstadoId() == Utils.ID_ESTADO_RADICADO_ASIGNADO
                    || radicado.getEstadoId() == Utils.ID_ESTADO_RADICADO_GESTIONADO) {
                EstadoRadicado eatadoRadicado = estadosRadicado.stream()
                        .filter(x -> x.getEstadoRadId() == radicado.getEstadoId()).findFirst().get();
                Comboitem comboitem = new Comboitem();
                comboitem.setValue(eatadoRadicado);
                comboitem.setLabel(eatadoRadicado.getNombre());
                cmbEstado.appendChild(comboitem);
                cmbEstado.setSelectedItem(comboitem);
            } else {
                estadosRadicado = estadosRadicado.stream()
                        .filter(x -> x.getEstadoRadId() == Utils.ID_ESTADO_RADICADO_RECIBIDO
                                || x.getEstadoRadId() == Utils.ID_ESTADO_RADICADO_RADICADO)
                        .collect(Collectors.toList());
                Utils.llenarComboboxEstadoRadicado(estadosRadicado, cmbEstado);

                if (Utils.isValidoCombobox(cmbEstado)) {
                    for (Comboitem row : cmbEstado.getItems()) {
                        EstadoRadicado corEstadoRadicado = (EstadoRadicado) row.getValue();
                        if (corEstadoRadicado != null && corEstadoRadicado.getEstadoRadId() == radicado.getEstadoId()) {
                            cmbEstado.setSelectedItem(row);
                            break;
                        }
                    }
                }
            }
        } else {
            if (radicado.getEstadoId() == Utils.ID_ESTADO_RADICADO_INFORMADO
                    || radicado.getEstadoId() == Utils.ID_ESTADO_RADICADO_GESTIONADO) {
                EstadoRadicado eatadoRadicado = estadosRadicado.stream()
                        .filter(x -> x.getEstadoRadId() == radicado.getEstadoId()).findFirst().get();
                Comboitem comboitem = new Comboitem();
                comboitem.setValue(eatadoRadicado);
                comboitem.setLabel(eatadoRadicado.getNombre());
                cmbEstado.appendChild(comboitem);
                cmbEstado.setSelectedItem(comboitem);
            } else {
                estadosRadicado = estadosRadicado.stream()
                        .filter(x -> x.getEstadoRadId() == Utils.ID_ESTADO_RADICADO_RECIBIDO
                                || x.getEstadoRadId() == Utils.ID_ESTADO_RADICADO_RADICADO)
                        .collect(Collectors.toList());
                Utils.llenarComboboxEstadoRadicado(estadosRadicado, cmbEstado);

                if (Utils.isValidoCombobox(cmbEstado)) {
                    for (Comboitem row : cmbEstado.getItems()) {
                        EstadoRadicado corEstadoRadicado = (EstadoRadicado) row.getValue();
                        if (corEstadoRadicado != null && corEstadoRadicado.getEstadoRadId() == radicado.getEstadoId()) {
                            cmbEstado.setSelectedItem(row);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Definir estado radicado Interno
     */
    private void definirEstadoRadicadoInterno() {
        cmbEstado.setDisabled(true);

        if (estadosRadicado == null || estadosRadicado.size() == 0) {
            return;
        }

        /**
         * Cuando el Radicado Requiere Respuesta y el tipo de radicación es
         * 'Interna', el sistema presenta el campo estado con las opciones:
         * 'Recibido' y 'Radicado'. Cuando el estado es 'Asignado' y
         * 'Gestionado' el sistema nuestra el campo como solo lectura
         */
        if (radicado.isRequiereRespuesta()) {
            if (radicado.getEstadoId() == Utils.ID_ESTADO_RADICADO_ASIGNADO
                    || radicado.getEstadoId() == Utils.ID_ESTADO_RADICADO_GESTIONADO) {
                EstadoRadicado estadoRadicado = estadosRadicado.stream()
                        .filter(x -> x.getEstadoRadId() == radicado.getEstadoId()).findFirst().get();
                Comboitem comboitem = new Comboitem();
                comboitem.setValue(estadoRadicado);
                comboitem.setLabel(estadoRadicado.getNombre());
                cmbEstado.appendChild(comboitem);
                cmbEstado.setSelectedItem(comboitem);
            } else {
                estadosRadicado = estadosRadicado.stream()
                        .filter(x -> x.getEstadoRadId() == Utils.ID_ESTADO_RADICADO_RECIBIDO
                                || x.getEstadoRadId() == Utils.ID_ESTADO_RADICADO_RADICADO)
                        .collect(Collectors.toList());
                Utils.llenarComboboxEstadoRadicado(estadosRadicado, cmbEstado);

                if (Utils.isValidoCombobox(cmbEstado)) {
                    for (Comboitem row : cmbEstado.getItems()) {
                        EstadoRadicado corEstadoRadicado = (EstadoRadicado) row.getValue();
                        if (corEstadoRadicado != null && corEstadoRadicado.getEstadoRadId() == radicado.getEstadoId()) {
                            cmbEstado.setSelectedItem(row);
                            break;
                        }
                    }
                }
                cmbEstado.setDisabled(false);
            }
        } else {
            if (radicado.getEstadoId() == Utils.ID_ESTADO_RADICADO_INFORMADO
                    || radicado.getEstadoId() == Utils.ID_ESTADO_RADICADO_GESTIONADO) {
                EstadoRadicado estadoRadicado = estadosRadicado.stream()
                        .filter(x -> x.getEstadoRadId() == radicado.getEstadoId()).findFirst().get();
                Comboitem comboitem = new Comboitem();
                comboitem.setValue(estadoRadicado);
                comboitem.setLabel(estadoRadicado.getNombre());
                cmbEstado.appendChild(comboitem);
                cmbEstado.setSelectedItem(comboitem);
            } else {
                estadosRadicado = estadosRadicado.stream()
                        .filter(x -> x.getEstadoRadId() == Utils.ID_ESTADO_RADICADO_RECIBIDO
                                || x.getEstadoRadId() == Utils.ID_ESTADO_RADICADO_RADICADO)
                        .collect(Collectors.toList());
                Utils.llenarComboboxEstadoRadicado(estadosRadicado, cmbEstado);

                if (Utils.isValidoCombobox(cmbEstado)) {
                    for (Comboitem row : cmbEstado.getItems()) {
                        EstadoRadicado corEstadoRadicado = (EstadoRadicado) row.getValue();
                        if (corEstadoRadicado != null && corEstadoRadicado.getEstadoRadId() == radicado.getEstadoId()) {
                            cmbEstado.setSelectedItem(row);
                            break;
                        }
                    }
                }
                cmbEstado.setDisabled(false);
            }
        }
    }

    /**
     * Llena la tabla datos destinatario cuando el tipo de radicación es INTERNA
     * 
     * @param usuario
     *            Objeto Usuario
     */
    private void llenarDatosDestUsuarioInterna(Usuario usuario) {
        Listitem item = new Listitem();
        item.setValue(usuario);
        item.setParent(lstDatosDestinatarioInterna);

        item.appendChild(new Listcell(""));
        item.appendChild(new Listcell(usuario.getNombreCompleto()));
        item.appendChild(new Listcell(usuario.getNombreRol()));
        item.appendChild(new Listcell(usuario.getNombreArea()));

        Listcell listcell = new Listcell();
        listcell.setParent(item);

        // TODO: Verificar si se filtra por empresa
        if (usuarioDestPrincipalInterna != null
                && usuario.getUsuarioId() == usuarioDestPrincipalInterna.getUsuarioId()) {
            item.setSelected(true);
        }
    }

    /**
     * Llena la tabla destinatarios tercero cuando el tipo de radicación es
     * SALIDA
     * 
     * @param corTercero
     *            Objero CorTercero
     */
    private void llenarDatosDestTerceroSalida(CorTercero corTercero) {
        Listitem item = new Listitem();
        item.setValue(corTercero);
        item.setParent(lstDatosDestinatarioSalida);

        item.appendChild(new Listcell(""));
        item.appendChild(new Listcell(corTercero.getNombre()));
        item.appendChild(new Listcell(corTercero.getResponsable()));
        item.appendChild(new Listcell(corTercero.getDireccion()));
        item.appendChild(new Listcell(corTercero.getMunicipio()));

        Listcell listcell = new Listcell();
        listcell.setParent(item);

        // TODO: Verificar si se filtra por empresa
        if (terceroDestPrincipalSalida != null
                && corTercero.getTerceroId() == terceroDestPrincipalSalida.getTerceroId()) {
            item.setSelected(true);
        }
    }

    /**
     * Llena la tabla datos destinatario cuando el tipo de radicación es ENTRADA
     * 
     * @param usuario
     *            Objeto Usuario
     */
    private void llenarDatosDestUsuarioEntrada(Usuario usuario) {
        Listitem item = new Listitem();
        item.setValue(usuario);
        item.setParent(lstDatosDestinatarioEntrada);

        item.appendChild(new Listcell(""));
        item.appendChild(new Listcell(usuario.getNombreCompleto()));
        item.appendChild(new Listcell(usuario.getNombreRol()));
        item.appendChild(new Listcell(usuario.getNombreArea()));

        Listcell listcell = new Listcell();
        listcell.setParent(item);

        // TODO: Verificar si se filtra por empresa
        if (usuarioDestPrincipalEntrada != null
                && usuario.getUsuarioId() == usuarioDestPrincipalEntrada.getUsuarioId()) {
            item.setSelected(true);
        }
    }

    /**
     * 
     * ************************************************** ROTULO
     * **************************************************
     */

    /**
     * Evento vista preliminar del rotulo
     * 
     * @param event
     */
    public void onClick$btnVistaPreviaRotulo(Event event) {
        if (radicado.getRadicado() == null || radicado.getRadicado().isEmpty()) {
            return;
        }

        if (radicado.getTipoRadicacion() == null || radicado.getTipoRadicacion().toString().isEmpty()) {
            return;
        }

        if (radicado.getFechaRadicacion() == null) {
            return;
        }

        RotuloInfo rotuloInfo = new RotuloInfo();
        rotuloInfo.setRadicado(radicado.getRadicado());
        rotuloInfo.setCodigoBarras(radicado.getRadicado());
        rotuloInfo.setFecha(dateTimeFormat(radicado.getFechaRadicacion()));

        TipoRadicado tipo = TipoRadicado.getTipoCorrespondencia(radicado.getTipoRadicacion());
        Combobox combobox = null;

        switch (tipo) {
        case ENTRADA:
            combobox = cmbNombreTercero;
            break;
        case SALIDA:
            combobox = cmbNombreUsuarioSalida;
            break;
        case INTERNA:
            combobox = cmbNombreUsuarioInterna;
            break;
        }

        if (!Utils.isValidoComboboxItem(combobox)) {
            return;
        }

        rotuloInfo.setRemitente(combobox.getValue());

        JasperPrint jasperPrint;
        Map<String, Object> params = new HashMap<>();
        List<RotuloInfo> rotulos = new ArrayList<>();
        rotulos.add(rotuloInfo);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            params.put("logo", ImageIO.read(getClass().getResource("/images/logo.png")));

            InputStream report = getClass().getResourceAsStream("/reports/rotulo.jasper");

            jasperPrint = Reportes.llenarReporte(report, params, new JRBeanCollectionDataSource(rotulos));

            Reportes.generarReportePdf(jasperPrint, out);

            ByteArrayInputStream is = new ByteArrayInputStream(out.toByteArray());
            BufferedImage img = Reportes.convertirPdfAImagen(is);

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
     * 
     * ************************************************** SALIR PANTALLA
     * **************************************************
     */

    /**
     * Action cancelar edicion
     */
    public void onClick$btnCancelar(Event event) {
        self.onClose();
    }

    public void onClick$lstComentarios(Event event) throws Exception {
        Listitem itemSeleccinado = lstComentarios.getSelectedItem();
        txtComentario.setValue(((CorComentario) itemSeleccinado.getValue()).getComentario());
    }
    
    /**
     * Utilidad para mostrar el archivo adjunto
     * @param adjunto
     */
    private void mostrarAdjuntos(CorAdjunto adjunto) {
        try {
            byte[] bytes = adjuntoService.obtenerBytesAdjunto(radicado.getRadicadoId(), adjunto.getAdjuntoId());
            Utils.mostrarArchivoAdjunto(adjunto.getRuta(), bytes, self);
        } catch (ServerErrorException ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            if (ex.getResponse() != null && ex.getResponse().getHeaders() != null) {
                List<Object> myResponses = ex.getResponse().getHeaders().get(Utils.LLAVE_CODIGOS_ERROR_SERVER);

                if (myResponses != null && myResponses.size() > 0) {
                    if (myResponses.contains(Utils.CODIGO_ERROR_FILE_NOT_FOUND_EXCEPTION)) {
                        Messagebox.show("El documento solicitado no existe, Por favor Verifique que el documento exista.", 
                                Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
                    } else {
                        Messagebox.show("No se puede visualizar el documento, Por favor intente de nuevo.", 
                                Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
                    }
                }
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            Messagebox.show("No se puede visualizar el documento, Por favor intente de nuevo.", 
                    Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
        }
    }

    private void deshabilitarComponentes(Component component, boolean incluirItems) {
        if (component.getChildren() == null) {
            return;
        } else {
            for (Component children : component.getChildren()) {
                if (children.getChildren() != null) {
                    try {
                        if (!(children instanceof Listitem) || incluirItems) {
                            if (children instanceof Textbox && !(children instanceof Combobox)) {
                                UiUtils.setMethodBooleanAtComponents(true, "setReadonly", children);
                            } else {
                                UiUtils.setMethodBooleanAtComponents(true, "setDisabled", children);
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                deshabilitarComponentes(children, incluirItems);
            }
        }
    }
}