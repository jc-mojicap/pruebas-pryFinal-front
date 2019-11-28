/*
 * Archivo: ActualizarRadicadoController.java
 * Fecha creacion: 13/03/2017 10:04:34 a. m.
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
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Constraint;
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

import co.com.grupoasd.documental.cliente.autorizacion.AccionApp;
import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.cliente.catalogo.model.Serie;
import co.com.grupoasd.documental.cliente.catalogo.model.Subserie;
import co.com.grupoasd.documental.cliente.catalogo.model.TipoDocumental;
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
import co.com.grupoasd.documental.cliente.parametrizacion.model.ParametroAplicacion;
import co.com.grupoasd.documental.presentacion.comun.vo.TipoRadicado;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.controller.util.GenericController;
import co.com.grupoasd.documental.presentacion.controller.util.Reportes;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;
import co.com.grupoasd.documental.presentacion.controller.util.Utils;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.AreaService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SerieService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SubserieService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.TipoDocumentalService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.UsuarioService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.CorrespondenciaServiceFactory;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorAdjuntoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorCanalService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTerceroService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTipoEmbalajeService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTransportadoraService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorrespondenciaBitacoraRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.EstadoRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.FormatosPermitidosArchivoService;
import co.com.grupoasd.documental.presentacion.service.parametrizacion.ParametroAplicacionServiceFactory;
import co.com.grupoasd.documental.presentacion.service.parametrizacion.iface.ParametroAplicacionService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Controlador que implementa la logica que maneja la vista
 * actualizar_correspondencia
 * 
 * @author adrian.lopez
 * @since 8 de Mayo 2017
 */
public class ActualizarRadicadoController extends GenericController<Window> {

	private static final long serialVersionUID = 3221172202607053635L;
	
	/**
	 * Controles
	 */
	
	private Spinner spinCantFolios;
	private Popup popCorreoCert;
	private Datebox dtxFechaCorreoCertif;
	private Spinner spinCantEmbalaje;
	private Image imgRotulo;
	private Window windowsActualizarRadicado;
	
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
	private Combobox cmbTiposDocumental;
	private Combobox cmbTransportadora;
	private Combobox cmbTipoEmbalaje;
	
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
	 * Buttons
	 */
	private Button btnAgregarArchivo;
	private Button btnCorreroCertificado;
	private Button btnAdminTerceros;
	private Button btnAdmTercerosRadSalida;
	private Button btnAnular;
	
	/**
	 * Groupbox
	 */
	private Groupbox grbRemitenteEntrada;
	private Groupbox grbDatosDestinatarioEntrada;
	private Groupbox grbRemitenteSalida;
	private Groupbox grbDatosDestinatarioSalida;
	private Groupbox grbRemitenteInterna;
	private Groupbox grbDatosDestinatarioInterna;
	private Groupbox grbBotonesAdmTerceros;
	
	/**
	 * Services
	 */
	private AreaService areaService;
	private SubserieService subSerieService;
	private SerieService serieService;
	private CorCanalService corCanalService;
	private EstadoRadicadoService corEstadoRadicadoService;
	private CorTerceroService corTerceroService;
	private CorRadicadoService corRadicadoService;
	private TipoDocumentalService tipoDocumentalService;
	private UsuarioService usuarioService;	
	private CorrespondenciaBitacoraRadicadoService bitacoraRadicadoService;
	private CorTransportadoraService corTransportadoraService;
	private CorTipoEmbalajeService corTipoEmbalajeService;
	private FormatosPermitidosArchivoService permitidosArchivoService;
	private CorAdjuntoService adjuntoService;
	private ParametroAplicacionService parametroAplicacionService;
	
	/**
	 * Objetos generales
	 */
	private CorRadicado corRadicadoEditar;
	private Usuario usuarioSistema;
	private List<Usuario> usuariosDestInterna;
	private List<Usuario> usuariosDestEntrada;
	private List<CorTercero> tercerosDestSalida;
	private Usuario usuarioDestPrincipalEntrada;
	private CorTercero terceroDestPrincipalSalida;
	private Usuario usuarioDestPrincipalInterna;
	private List<FormatosPermitidosArchivo> permitidosArchivos;
	private static Integer idUsuarioSesion;
	private static Integer idEmpresaSesion;
	private static Long radicadoId;
	private CorRadicado radicadoAsociado;
	private List<EstadoRadicado> estadosRadicado;
	private static final String ID_LOGO_EMPRESA = "logoEmpresa";
    private ParametroAplicacion logoEmpresa;

	@Override
	public void onCreate(Event event) {
		try {
			
			/**
			 * Obtenemos el id del radicado que viene por parametro
			 */
			radicadoId = (Long) args.get("radicadoId");
			
			if (radicadoId == null) {
	            Clients.showNotification(Labels.getLabel("correspondencia.asignarCorrespondencia.mensaje.errorIdRadicado"), "error", self.getParent(), "middle_center", 3000, true);
	            self.onClose();
	            return;
	        }
			
			/**
			 * Instancia de los servicios
			 */
			areaService = CatalogoServiceFactory.getAreaService();
			subSerieService = CatalogoServiceFactory.getSubserieService();
			serieService = CatalogoServiceFactory.getSerieService();
			corCanalService = CorrespondenciaServiceFactory.getCorCanalService();
			corEstadoRadicadoService = CorrespondenciaServiceFactory.getEstadoRadicadoService();
			corTerceroService = CorrespondenciaServiceFactory.getCorTerceroService();
			corRadicadoService = CorrespondenciaServiceFactory.getCorRadicadoService();
			tipoDocumentalService = CatalogoServiceFactory.getTipoDocumentalService();
			usuarioService = CatalogoServiceFactory.getUsuarioService();
			bitacoraRadicadoService = CorrespondenciaServiceFactory.getCorrespondenciaBitacoraRadicadoService();
			corTransportadoraService = CorrespondenciaServiceFactory.getCorTransportadoraService();
			corTipoEmbalajeService = CorrespondenciaServiceFactory.getCorTipoEmbalajeService();
			permitidosArchivoService = CorrespondenciaServiceFactory.getFormatosPermitidosArchivoService();
			adjuntoService = CorrespondenciaServiceFactory.getCorAdjuntoService();
			parametroAplicacionService = ParametroAplicacionServiceFactory.getParametroAplicacionService();
			
			usuariosDestInterna = new ArrayList<>();
			usuariosDestEntrada = new ArrayList<>();
			tercerosDestSalida = new ArrayList<>();
	
			/**
			 * Asignacion de datos iniciales
			 */
			Utils.llenarComboboxCorCanal(corCanalService.listar(), cmbCanalRecepcion);
			Utils.llenarComboboxTipoEmbalaje(corTipoEmbalajeService.listar(), cmbTipoEmbalaje);
			Utils.llenarComboboxCorTransportadora(corTransportadoraService.listar(), cmbTransportadora);
			
			/**
			 * Valores por sesión
			 */
			idUsuarioSesion = ((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getUsuarioId();
			idEmpresaSesion = ((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getEmpresaId();
			
			//TODO: obtener el usuario logueado en el sistema y la empresa
			usuarioSistema = usuarioService.obtenerPorId(idUsuarioSesion);
			
			/**
			 * Lista archivos permitidos
			 */
			permitidosArchivos = permitidosArchivoService.listarTodos();
			listarFormatosPermitidos();
			
			/**
			 * Listado de radicados
			 */
			estadosRadicado = corEstadoRadicadoService.listar();
			
			/**
			 * Boton administrar tercero
			 */
			if (!SessionUtil.accionPermitida(AccionApp.ADMINISTRAR_TERCEROS.getId())) {
				btnAdminTerceros.setDisabled(true);
				btnAdmTercerosRadSalida.setDisabled(true);
			}
			
			/**
			 * Traemos la informacion del radicado por id
			 */
			traerInformacionRadicado();	
		} catch (NotFoundException e) {
			Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
			windowsActualizarRadicado.detach();
		} catch (Exception e) {
			Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
			windowsActualizarRadicado.detach();
		}
	}
	
	/**
	 * Obtiene la información asociada al radicado
	 */
	private void traerInformacionRadicado(){
		/**
		 * Inicializamos los componentes
		 */
		grbRemitenteEntrada.setVisible(true);
		grbDatosDestinatarioEntrada.setVisible(true);
		grbRemitenteSalida.setVisible(false);
		grbDatosDestinatarioSalida.setVisible(false);
		grbBotonesAdmTerceros.setVisible(true);
		grbRemitenteInterna.setVisible(false);
		grbDatosDestinatarioInterna.setVisible(false);
		
		corRadicadoEditar = corRadicadoService.obtenerPorId(radicadoId);
		
		/**
		 * Boton anular
		 */
		if (corRadicadoEditar.getEstadoId() != Utils.ID_ESTADO_RADICADO_RECIBIDO) {
			btnAnular.setDisabled(true);
		}
		
		Comboitem comboitemTipoRadicado;

		/**
		 * Limpiamos los controles
		 */
		limpiarListbox();
		
		/**
		 * Se identifica el tipo de radicado que viene por parametro
		 */
		TipoRadicado tipo = TipoRadicado.getTipoCorrespondencia(corRadicadoEditar.getTipoRadicacion());

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
			grbBotonesAdmTerceros.setVisible(true);
			grbRemitenteInterna.setVisible(false);
			grbDatosDestinatarioInterna.setVisible(false);

			/**
			 * Obtengo los dados del remitente <Tercero>
			 */
			CorTercero corTercero = corRadicadoEditar.getTerceroRemitente();
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
				lblCiudadRemitente.setValue(corTercero.getNombreMunicipio());
				lblResponsableRemitente.setValue(corTercero.getResponsable());
				lblCargoRemitente.setValue(corTercero.getCargo());
				lblDependenciaRemitente.setValue(corTercero.getDependencia());
			}

			usuarioDestPrincipalEntrada = corRadicadoEditar.getUsuarioPrincipal();

			/**
			 * Funcionario
			 */
			txtNombreFuncionario.setValue(corRadicadoEditar.getNombreFuncionario());
			txtIdFuncionario.setValue(corRadicadoEditar.getNroIdentificacionFunc());

			txtRadicadoRemitente.setValue(corRadicadoEditar.getRadicadoExterno());

			/**
			 * Obtengo los datos del destinatario <Usuario>
			 */
			usuariosDestEntrada = corRadicadoEditar.getUsuariosDestinatarios();
			
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
			grbBotonesAdmTerceros.setVisible(false);
			grbRemitenteEntrada.setVisible(false);
			grbDatosDestinatarioEntrada.setVisible(false);
			grbRemitenteInterna.setVisible(false);
			grbDatosDestinatarioInterna.setVisible(false);

			/**
			 * Información usuario remitente
			 */
			Usuario usuarioRemitente = corRadicadoEditar.getUsuarioRemitente();
			if (usuarioRemitente != null) {
				Comboitem comboitem = new Comboitem();
				comboitem.setValue(usuarioRemitente);
				comboitem.setLabel(usuarioRemitente.getNombreCompleto());
				cmbNombreUsuarioSalida.appendChild(comboitem);
				cmbNombreUsuarioSalida.setSelectedItem(comboitem);
				lblCargoRemSalida.setValue(usuarioRemitente.getNombreRol());
				lblAreaProcesoRemSalida.setValue(usuarioRemitente.getNombreArea());
			}

			terceroDestPrincipalSalida = corRadicadoEditar.getTerceroPrincipal();

			/**
			 * Terceros distinatarios
			 */
			tercerosDestSalida= corRadicadoEditar.getTercerosDestinatarios();
			
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
			 * - Cuando el Radicado Requiere Respuesta o no Requiere Respuesta y el tipo de radicación es 'Salida', <br>
			 *   el sistema presenta el campo estado con las opciones: 'Recibido' y 'Gestionado'. Cuando el estado es <br>
			 *   'Gestionado' el sistema nuestra el campo como solo lectura.
			 */
			cmbEstado.setDisabled(true);
			if (estadosRadicado != null && estadosRadicado.size() > 0) {
				if (corRadicadoEditar.getEstadoId() == Utils.ID_ESTADO_RADICADO_GESTIONADO) {
					EstadoRadicado radicado = estadosRadicado.stream()
							.filter(x -> x.getEstadoRadId() == corRadicadoEditar.getEstadoId())
							.findFirst().get();
					Comboitem comboitem = new Comboitem();
					comboitem.setValue(radicado);
					comboitem.setLabel(radicado.getNombre());
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
							if (corEstadoRadicado != null && corEstadoRadicado.getEstadoRadId() == corRadicadoEditar.getEstadoId()) {
								cmbEstado.setSelectedItem(row);
								break;
							}
						}
					}
					cmbEstado.setDisabled(false);
				}
			}

			txtRadicadoAsociado.setValue(corRadicadoEditar.getRadicadoAsociado());
			
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
			grbBotonesAdmTerceros.setVisible(false);

			/**
			 * Usuario remitente
			 */
			Usuario usuarioRemitenteInterna = corRadicadoEditar.getUsuarioRemitente();
			if (usuarioRemitenteInterna != null) {
				Comboitem comboitem = new Comboitem();
				comboitem.setValue(usuarioRemitenteInterna);
				comboitem.setLabel(usuarioRemitenteInterna.getNombreCompleto());
				cmbNombreUsuarioInterna.appendChild(comboitem);
				cmbNombreUsuarioInterna.setSelectedItem(comboitem);
				lblCargoRemInterna.setValue(usuarioRemitenteInterna.getNombreRol());
				lblAreaProcesoRemInterna.setValue(usuarioRemitenteInterna.getNombreArea());
			}

			usuarioDestPrincipalInterna = corRadicadoEditar.getUsuarioPrincipal();

			/**
			 * Listamos los usuarios destinatarios
			 */
			usuariosDestInterna = corRadicadoEditar.getUsuariosDestinatarios();
			
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
			 * Estado
			 */
			definirEstadoRadicadoInterno();
			
			txtRadicadoAsociado.setValue(corRadicadoEditar.getRadicadoAsociado());
			
			break;
		default:
			return;
		}

		lblSecuencia.setValue(corRadicadoEditar.getSecuencia() + "");
		lblRadicado.setValue(corRadicadoEditar.getRadicado());
		spinCantFolios.setValue(corRadicadoEditar.getCantidadFolios());
		lblFechaDelSistema.setValue(dateTimeFormat(corRadicadoEditar.getFechaRadicacion()));

		/**
		 * Canal recepcion
		 */
		if (Utils.isValidoCombobox(cmbCanalRecepcion)) {
			for (Comboitem row : cmbCanalRecepcion.getItems()) {
				CorCanal canal = (CorCanal) row.getValue();
				if (canal != null && canal.getCanalId() == corRadicadoEditar.getCanalId()) {
					cmbCanalRecepcion.setSelectedItem(row);
					break;
				}
			}
		}
		
		/**
		 * Definimos si el tipo de canal es correo certificado para cargar los datos en la ventana popup
		 */
		if(corRadicadoEditar.getCanalId() == Utils.ID_CANAL_CORREO_CERTIFICADO) {
			btnCorreroCertificado.setDisabled(false);
			/**
			 * Tipo Embalaje
			 */
			if (Utils.isValidoCombobox(cmbTipoEmbalaje)) {
				for (Comboitem row : cmbTipoEmbalaje.getItems()) {
					CorTipoEmbalaje corTipoEmbalaje = (CorTipoEmbalaje) row.getValue();
					if (corTipoEmbalaje != null && corTipoEmbalaje.getTipoEmbalajeId() == corRadicadoEditar.getTipoEmbalajeId()) {
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
					if (corTransportadora != null && corTransportadora.getTransportadoraId() == corRadicadoEditar.getTransportadoraId()) {
						cmbTransportadora.setSelectedItem(row);
						break;
					}
				}
			}
			
			/**
			 * Guia
			 */
			txtGuia.setValue(corRadicadoEditar.getNumeroGuia());
			dtxFechaCorreoCertif.setValue(corRadicadoEditar.getFechaOperacion());
			
			/**
			 * Cantidad de embalaje
			 */
			spinCantEmbalaje.setValue(corRadicadoEditar.getCantidadEmbalaje());
		}

		/**
		 * Areas
		 */
		Utils.llenarComboboxArea(areaService.listar(idEmpresaSesion, Utils.ESTADO_EMPRESA_INACTIVA), cmbAreaProceso);
		if (Utils.isValidoCombobox(cmbAreaProceso)) {
			for (Comboitem row : cmbAreaProceso.getItems()) {
				Area area = (Area) row.getValue();
				if (area != null && area.getAreaId() == corRadicadoEditar.getArea().getAreaId()) {
					cmbAreaProceso.setSelectedItem(row);
					break;
				}
			}
		}
		
		/**
		 * Series
		 */
		Utils.llenarComboboxSerie(serieService.listarPorArea(corRadicadoEditar.getArea().getAreaId(), Utils.ESTADO_SERIE_INACTIVA), cmbSerie);
		if (Utils.isValidoCombobox(cmbSerie)) {
			for (Comboitem row : cmbSerie.getItems()) {
				Serie serie = (Serie) row.getValue();
				if (serie != null && serie.getSerieId() == corRadicadoEditar.getSerie().getSerieId()) {
					cmbSerie.setSelectedItem(row);
					break;
				}
			}
		}
		
		/**
		 * Subserie
		 */
		Utils.llenarComboboxSubSerie(subSerieService.listarPorSerie(corRadicadoEditar.getSerie().getSerieId(), Utils.ESTADO_SUBSERIE_INACTIVA), cmbSubSerie);	
		if (Utils.isValidoCombobox(cmbSubSerie)) {
			for (Comboitem row : cmbSubSerie.getItems()) {
				Subserie subserie = (Subserie) row.getValue();
				if (subserie != null && subserie.getSubserieId() == corRadicadoEditar.getSubserie().getSubserieId()) {
					cmbSubSerie.setSelectedItem(row);
					break;
				}
			}
		}
		
		/**
		 * Tipo Documental
		 */
		Utils.llenarComboboxTipoDocumental(corRadicadoEditar.getTipoDocumentals(), cmbTiposDocumental);

		txtAsunto.setValue(corRadicadoEditar.getAsunto());

		/**
		 * Listamos los comentarios asociados al radicado
		 */
		List<CorComentario> listComentarios = corRadicadoEditar.getComentarios();
		if (listComentarios != null && listComentarios.size() > 0) {
			try {
				for (CorComentario comentario : listComentarios) {
					Listitem item = new Listitem();
					item.setValue(comentario);
					item.setParent(lstComentarios);
					item.appendChild(new Listcell(dateTimeFormat(comentario.getFecha())));
					Usuario usuario = usuarioService.obtenerPorId(comentario.getUsuarioId());
					item.appendChild(new Listcell(usuario.getNombreCompleto()));
					item.appendChild(new Listcell(comentario.getComentario()));
				}
			} catch (NotFoundException e) {
				System.err.println(e);
			}
		}

		/**
		 * Obtenemos los adjuntos relacionados al radicado
		 */
		List<CorAdjunto> corAdjuntos = corRadicadoEditar.getCorAdjuntos();
		if (corAdjuntos != null && corAdjuntos.size() > 0) {
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
				
				Toolbarbutton toolbarbutton = new Toolbarbutton("", "/recursos/icons/delete.png");
				toolbarbutton.setParent(listcell);
				toolbarbutton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						Messagebox.show(
								String.format(Labels.getLabel("correspondencia.actualizarRadicado.mensajeEliminarAdjunto"), ((CorAdjunto) item.getValue()).getRuta()),
									"Advertencia", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
								new EventListener<Event>() {
									@Override
									public void onEvent(Event evnt) throws Exception {
										if (Messagebox.ON_YES.equals(evnt.getName())) {
											lstArchAdjuntos.removeItemAt(item.getIndex());
										}
									}
								});
					}
				});
			}
		}

		/**
		 * Listamos la trazabilidad
		 */
		List<CorrespondenciaBitacoraRadicado> bitacoraRadicados = bitacoraRadicadoService.listarPorRadicadoId(corRadicadoEditar.getRadicadoId());
		if (bitacoraRadicados != null && bitacoraRadicados.size() > 0) {
			try {
				for (CorrespondenciaBitacoraRadicado bitacoraRadicado : bitacoraRadicados) {
					Listitem item = new Listitem();
					item.setValue(bitacoraRadicado);
					item.setParent(lstTrazabilidad);
					item.appendChild(new Listcell(dateTimeFormat(bitacoraRadicado.getFecha())));
					Usuario usuario = usuarioService.obtenerPorId(bitacoraRadicado.getIdUsuario());
					item.appendChild(new Listcell(usuario.getNombreCompleto()));
					item.appendChild(new Listcell(bitacoraRadicado.getCampo()));
					String valorAnterior = bitacoraRadicado.getValorAnterior() != null ? bitacoraRadicado.getValorAnterior() : "";
					String valorNuevo = bitacoraRadicado.getValorNuevo() != null ? bitacoraRadicado.getValorNuevo() : "";
					item.appendChild(new Listcell(valorAnterior.concat(" => ").concat(valorNuevo)));
				}
			} catch (NotFoundException e) {
				System.err.println(e);
			}
		}
	}
	
	/**
	 * Utilidad para mostrar el archivo adjunto
	 * @param adjunto
	 */
	private void mostrarAdjuntos(CorAdjunto adjunto) {
		try {
			byte[] bytes = adjuntoService.obtenerBytesAdjunto(corRadicadoEditar.getRadicadoId(), adjunto.getAdjuntoId());
			Utils.mostrarArchivoAdjunto(adjunto.getRuta(), bytes, self);
		} catch (ServerErrorException ex) {
			if (ex.getResponse() != null && ex.getResponse().getHeaders() != null) {
				List<Object> myResponses = ex.getResponse().getHeaders().get(Utils.LLAVE_CODIGOS_ERROR_SERVER);

				if (myResponses != null && myResponses.size() > 0) {
					if (myResponses.contains(Utils.CODIGO_ERROR_FILE_NOT_FOUND_EXCEPTION)) {
						Messagebox.show("El documento solicitado no existe", 
								Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
					} else {
						Messagebox.show("No se puede visualizar el documento, Por favor intente de nuevo.", 
								Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
					}
				}
			}
		} catch (Exception ex) {
			Messagebox.show("No se puede visualizar el documento, Por favor intente de nuevo.", 
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
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
		 * Cuando el Radicado Requiere Respuesta y el tipo de radicación es 'Interna', 
		 * el sistema presenta el campo estado con las opciones: 'Recibido' y 'Radicado'.  Cuando 
		 * el estado es 'Asignado' y 'Gestionado' el sistema nuestra el campo como solo lectura
		 */
		if (corRadicadoEditar.isRequiereRespuesta()) {
			if (corRadicadoEditar.getEstadoId() == Utils.ID_ESTADO_RADICADO_ASIGNADO || corRadicadoEditar.getEstadoId() == Utils.ID_ESTADO_RADICADO_GESTIONADO) {
				EstadoRadicado radicado = estadosRadicado.stream()
						.filter(x -> x.getEstadoRadId() == corRadicadoEditar.getEstadoId()).findFirst().get();
				Comboitem comboitem = new Comboitem();
				comboitem.setValue(radicado);
				comboitem.setLabel(radicado.getNombre());
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
						if (corEstadoRadicado != null
								&& corEstadoRadicado.getEstadoRadId() == corRadicadoEditar.getEstadoId()) {
							cmbEstado.setSelectedItem(row);
							break;
						}
					}
				}
				cmbEstado.setDisabled(false);
			}
		} else {
			if (corRadicadoEditar.getEstadoId() == Utils.ID_ESTADO_RADICADO_INFORMADO || corRadicadoEditar.getEstadoId() == Utils.ID_ESTADO_RADICADO_GESTIONADO) {
				EstadoRadicado radicado = estadosRadicado.stream()
						.filter(x -> x.getEstadoRadId() == corRadicadoEditar.getEstadoId()).findFirst().get();
				Comboitem comboitem = new Comboitem();
				comboitem.setValue(radicado);
				comboitem.setLabel(radicado.getNombre());
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
						if (corEstadoRadicado != null
								&& corEstadoRadicado.getEstadoRadId() == corRadicadoEditar.getEstadoId()) {
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
	 * Definir estado radicado Entrada
	 */
	private void definirEstadoRadicadoEntrada() {
		cmbEstado.setDisabled(true);
		
		if (estadosRadicado == null || estadosRadicado.size() == 0) {
			return;
		}
		
		/**
		 * Cuando el Radicado Requiere Respuesta y el tipo de radicación es 'Entrada', 
		 * el sistema presenta el campo estado con las opciones: 'Recibido' y 'Radicado'.  Cuando 
		 * el estado es 'Asignado' y 'Gestionado' el sistema nuestra el campo como solo lectura
		 */
		if (corRadicadoEditar.isRequiereRespuesta()) {
			if (corRadicadoEditar.getEstadoId() == Utils.ID_ESTADO_RADICADO_ASIGNADO || corRadicadoEditar.getEstadoId() == Utils.ID_ESTADO_RADICADO_GESTIONADO) {
				EstadoRadicado radicado = estadosRadicado.stream()
						.filter(x -> x.getEstadoRadId() == corRadicadoEditar.getEstadoId()).findFirst().get();
				Comboitem comboitem = new Comboitem();
				comboitem.setValue(radicado);
				comboitem.setLabel(radicado.getNombre());
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
						if (corEstadoRadicado != null
								&& corEstadoRadicado.getEstadoRadId() == corRadicadoEditar.getEstadoId()) {
							cmbEstado.setSelectedItem(row);
							break;
						}
					}
				}
				cmbEstado.setDisabled(false);
			}
		} else {
			if (corRadicadoEditar.getEstadoId() == Utils.ID_ESTADO_RADICADO_INFORMADO || corRadicadoEditar.getEstadoId() == Utils.ID_ESTADO_RADICADO_GESTIONADO) {
				EstadoRadicado radicado = estadosRadicado.stream()
						.filter(x -> x.getEstadoRadId() == corRadicadoEditar.getEstadoId()).findFirst().get();
				Comboitem comboitem = new Comboitem();
				comboitem.setValue(radicado);
				comboitem.setLabel(radicado.getNombre());
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
						if (corEstadoRadicado != null
								&& corEstadoRadicado.getEstadoRadId() == corRadicadoEditar.getEstadoId()) {
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
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * ADJUNTOS
	 * * * * * * * * * * *  * * * * * * *  * * *  * * * *  * *
	 */
	
	/**
     * Verifica que el tipo de archivo (extensión) corresponda a los permitidos.
     * @param formato formato (extensión) del archivo.
     * @return<code>true</code> si el archivo es válido, <code>false</code> en caso contrario.
     */
    private boolean isValidoTipoArchivo(String formato) {
    	if (permitidosArchivos != null && permitidosArchivos.size() > 0) {
	        for (FormatosPermitidosArchivo FormatosPermitidosArchivo : permitidosArchivos) {
	            if (FormatosPermitidosArchivo.getExtension().equalsIgnoreCase(formato)){
	                return true;
	            }
	        }
    	}
        return false;
    }
	
	/**
	 * Evento adjuntar archivo
	 * @param UploadEvent
	 */
	public void onUpload$btnAgregarArchivo(UploadEvent event) {
		Media[] medias = event.getMedias();
		boolean isExisteAdjunto;

		if (medias != null && medias.length > 0) {
			for (Media media : medias) {

				if (media.getName().indexOf(".") > 0) {
					String formato = media.getName().substring(media.getName().lastIndexOf(".") + 1);

					if (isValidoTipoArchivo(formato)) {
						CorAdjunto corAdjunto = new CorAdjunto();
						corAdjunto.setRuta(media.getName());
						corAdjunto.setNombreUsuario(usuarioSistema.getNombreCompleto());
						corAdjunto.setUsuarioId(usuarioSistema.getUsuarioId());
						corAdjunto.setBytesAdjuntos(media.getByteData());
						corAdjunto.setFecha(new Date());
						if (Utils.isValidoCombobox(cmbTiposDocumental)) {
							TipoDocumental documental = (TipoDocumental) cmbTiposDocumental.getSelectedItem()
									.getValue();
							if (documental != null) {
								corAdjunto.setNombreTipoDocumental(documental.getNombre());
								corAdjunto.setTipoDocumentalId(documental.getTipoDocumentalId());
							}
						}

						isExisteAdjunto = false;

						if (Utils.isValidoListbox(lstArchAdjuntos))
							for (Listitem listitem : lstArchAdjuntos.getItems()) {
								CorAdjunto adjunto = (CorAdjunto) listitem.getValue();
								if (adjunto.getRuta().equalsIgnoreCase(corAdjunto.getRuta())
										&& adjunto.getTipoDocumentalId() == corAdjunto.getTipoDocumentalId()) {
									Clients.showNotification(
											String.format(
													Labels.getLabel(
															"correspondencia.actualizarRadicado.mensajeExisteAdjunto"),
													adjunto.getRuta(), adjunto.getNombreTipoDocumental()),
											"warning", lstArchAdjuntos, "middle_center", 5000, true);

									isExisteAdjunto = true;
									break;
								}
							}

						if (!isExisteAdjunto) {
							Listitem item = new Listitem();
							item.setValue(corAdjunto);
							item.setParent(lstArchAdjuntos);

							item.appendChild(new Listcell(corAdjunto.getRuta()));
							item.appendChild(new Listcell(dateTimeFormat(corAdjunto.getFecha())));
							item.appendChild(new Listcell(corAdjunto.getNombreUsuario()));
							item.appendChild(new Listcell(corAdjunto.getNombreTipoDocumental()));

							Listcell listcell = new Listcell();
							listcell.setParent(item);

							Toolbarbutton toolbarVer = new Toolbarbutton("", "/recursos/icons/search_file.png");
							toolbarVer.setParent(listcell);
							toolbarVer.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
								@Override
								public void onEvent(Event arg0) throws Exception {
									Utils.mostrarArchivoAdjunto(corAdjunto.getRuta(), corAdjunto.getBytesAdjuntos(),
											self);
								}
							});

							Toolbarbutton toolbarbutton = new Toolbarbutton("", "/recursos/icons/delete.png");
							toolbarbutton.setParent(listcell);
							toolbarbutton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
								@Override
								public void onEvent(Event arg0) throws Exception {
									Messagebox.show(
											String.format(
													Labels.getLabel(
															"correspondencia.actualizarRadicado.mensajeEliminarAdjunto"),
													((CorAdjunto) item.getValue()).getRuta()),
											"Advertencia", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
											new EventListener<Event>() {
												@Override
												public void onEvent(Event evnt) throws Exception {
													if (Messagebox.ON_YES.equals(evnt.getName())) {
														lstArchAdjuntos.removeItemAt(item.getIndex());
													}
												}
											});
								}
							});
						}
					} else {
						String mensaje = Labels.getLabel("correspondencia.actualizarRadicado.mensajeArchivoNoValido");
						String tipo = "error";
						Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
					}
				} else {
					String mensaje = Labels.getLabel("correspondencia.actualizarRadicado.mensajeArchivoNoValido");
					String tipo = "error";
					Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
				}
			}
		}
	}
	
	/**
	 * 
	 * *********************************************************************
	 * EVENTOS AGREGAR DISTINATARIOS
	 * *********************************************************************
	 * 
	 */
	
	/**
	 * Evento abrir ventana para seleccionar usuarios destinatarios ENTRADA
	 * @param event
	 */
	public void onClick$btnAgregarDestUsuarioEntrada(Event event) {
		/**
		 * Obtenemos el item seleccionado del Listbox y lo asignamos como usuario principal
		 */
		usuarioDestPrincipalEntrada = lstDatosDestinatarioEntrada != null && lstDatosDestinatarioEntrada.getSelectedItem() != null ? lstDatosDestinatarioEntrada.getSelectedItem().getValue() : null;
		
		Map<String, Object> params = new HashMap<>();
        params.put("callback", "onCargarTercerosDestEntrada");
        params.put("empresaId", idEmpresaSesion);
        params.put("usuarios", usuariosDestEntrada);
        params.put("isRespuestaMvc", true);
        Utils.setModalWindow("/correspondencia/radicar/agregar_responsable.zul", self, params, false, true);
	}
	
	/**
	 * Funcion que llama la ventana agregar destinatario usuario ENTRADA
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	public void onCargarTercerosDestEntrada(Event event) {
		lstDatosDestinatarioEntrada.getItems().clear();
		usuariosDestEntrada = (List<Usuario>) event.getData();
		
		if (usuariosDestEntrada != null && usuariosDestEntrada.size() > 0) {
			/**
			 * Si el usuario principal no se encuentra en la lista de seleccion, se asigna como null
			 */
			if (!usuariosDestEntrada.contains(usuarioDestPrincipalEntrada)) {
				usuarioDestPrincipalEntrada = null;
			}
			
			for (Usuario usuario : usuariosDestEntrada) {
				llenarDatosDestUsuarioEntrada(usuario);
			}
		}
	}
	
	/**
	 * Evento abrir ventana para seleccionar usuarios destinatarios SALIDA
	 * @param event
	 */
	public void onClick$btnAgregarDatosDestTerceroSalida(Event event) {
		/**
		 * Obtenemos el item seleccionado de la Listbox y lo asignamos como tercero principal
		 */
		terceroDestPrincipalSalida  = lstDatosDestinatarioSalida != null && lstDatosDestinatarioSalida.getSelectedItem() != null ? lstDatosDestinatarioSalida.getSelectedItem().getValue() : null;
		
		Map<String, Object> params = new HashMap<>();
        params.put("callback", "onCargarTercerosDestSalida");
        params.put("empresaId", idEmpresaSesion);
        params.put("terceros", tercerosDestSalida);
        params.put("isRespuestaMvc", true);
        Utils.setModalWindow("/correspondencia/radicar/agregar_tercero.zul", self, params, false, true);
	}
	
	/**
	 * Funcion que llama la ventana agregar destinatario usuario SALIDA
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	public void onCargarTercerosDestSalida(Event event) {
		lstDatosDestinatarioSalida.getItems().clear();
		tercerosDestSalida = (List<CorTercero>) event.getData();
		
		if (tercerosDestSalida != null && tercerosDestSalida.size() > 0) {
			/**
			 * Si el tercero principal no viene en la lista de seleccion, se asgina como null
			 */
			if (!tercerosDestSalida.contains(terceroDestPrincipalSalida)) {
				terceroDestPrincipalSalida = null;
			}
			
			for (CorTercero tercero : tercerosDestSalida) {
				llenarDatosDestTerceroSalida(tercero);
			}
		}
	}
	
	/**
	 * Evento abrir ventana para seleccionar usuarios destinatarios INTERNA
	 * @param event
	 */
	public void onClick$btnAgregarDatosDestUsuarioInterna(Event event) {
		/**
		 * Obtenemos el item seleccionado de la Listbox y lo asignamos como usuario principal
		 */
		usuarioDestPrincipalInterna = lstDatosDestinatarioInterna != null && lstDatosDestinatarioInterna.getSelectedItem() != null ? lstDatosDestinatarioInterna.getSelectedItem().getValue() : null;
		
		Map<String, Object> params = new HashMap<>();
        params.put("callback", "onCargarUsuariosDestInterna");
        params.put("empresaId", idEmpresaSesion);
        params.put("usuarios", usuariosDestInterna);
        params.put("isRespuestaMvc", true);
        Utils.setModalWindow("/correspondencia/radicar/agregar_responsable.zul", self, params, false, true);
	}
	
	/**
	 * Funcion que llama la ventana agregar destinatario usuario INTERNA
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	public void onCargarUsuariosDestInterna(Event event) {
		lstDatosDestinatarioInterna.getItems().clear();
		usuariosDestInterna = (List<Usuario>) event.getData();
		
		if (usuariosDestInterna != null && usuariosDestInterna.size() > 0) {
			/**
			 * Si en la lista de seleccion no se encuentra el usuario principal, lo seteamos como null
			 */
			if (!usuariosDestInterna.contains(usuarioDestPrincipalInterna)) {
				usuarioDestPrincipalInterna = null;
			}
			
			for (Usuario usuario : usuariosDestInterna) {
				llenarDatosDestUsuarioInterna(usuario);
			}
		}
	}
	
	/**
	 * Llena la tabla datos destinatario cuando el tipo de radicación es INTERNA
	 * @param usuario Objeto Usuario
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
		Toolbarbutton toolbarbutton = new Toolbarbutton("", "/recursos/icons/delete.png");
		toolbarbutton.setParent(listcell);
		toolbarbutton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				Messagebox.show(String.format(Labels.getLabel("correspondencia.actualizarRadicado.mensajeEliminarUsuario"), ((Usuario) item.getValue()).getNombreCompleto()), "Advertencia", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
						new EventListener<Event>() {
							@Override
							public void onEvent(Event evnt) throws Exception {
								if (Messagebox.ON_YES.equals(evnt.getName())) {
									lstDatosDestinatarioInterna.removeItemAt(item.getIndex());
									usuariosDestInterna.remove(usuario);
								}
							}
						});
			}
		});
		
		//TODO: Verificar si se filtra por empresa
        if (usuarioDestPrincipalInterna != null && usuario.getUsuarioId() == usuarioDestPrincipalInterna.getUsuarioId()) {	
			item.setSelected(true);
        }
	}
	
	/**
	 * Llena la tabla destinatarios tercero cuando el tipo de radicación es SALIDA
	 * @param corTercero Objero CorTercero
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
		Toolbarbutton toolbarbutton = new Toolbarbutton("", "/recursos/icons/delete.png");
		toolbarbutton.setParent(listcell);
		toolbarbutton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				Messagebox.show(String.format(Labels.getLabel("correspondencia.actualizarRadicado.mensajeEliminarTercero"), ((CorTercero) item.getValue()).getNombre()),
						"Advertencia", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
						new EventListener<Event>() {
							@Override
							public void onEvent(Event evnt) throws Exception {
								if (Messagebox.ON_YES.equals(evnt.getName())) {
									lstDatosDestinatarioSalida.removeItemAt(item.getIndex());
									tercerosDestSalida.remove(corTercero);
								}
							}
						});
			}
		});

		//TODO: Verificar si se filtra por empresa
		if (terceroDestPrincipalSalida != null && corTercero.getTerceroId() == terceroDestPrincipalSalida.getTerceroId()) {
			item.setSelected(true);
		}
	}
	
	/**
	 * Llena la tabla datos destinatario cuando el tipo de radicación es ENTRADA
	 * @param usuario Objeto Usuario
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
		Toolbarbutton toolbarbutton = new Toolbarbutton("", "/recursos/icons/delete.png");
		toolbarbutton.setParent(listcell);
		toolbarbutton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				Messagebox.show(
						String.format(Labels.getLabel("correspondencia.actualizarRadicado.mensajeEliminarUsuario"), ((Usuario) item.getValue()).getNombreCompleto()), 
							"Advertencia", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
						new EventListener<Event>() {
							@Override
							public void onEvent(Event evnt) throws Exception {
								if (Messagebox.ON_YES.equals(evnt.getName())) {
									lstDatosDestinatarioEntrada.removeItemAt(item.getIndex());
									usuariosDestEntrada.remove(usuario);
								}
							}
						});
			}
		});

		//TODO: Verificar si se filtra por empresa
		if (usuarioDestPrincipalEntrada != null && usuario.getUsuarioId() == usuarioDestPrincipalEntrada.getUsuarioId()) {
			item.setSelected(true);
		}
	}
	
	/**
	 * 
	 * *********************************************************************
	 * EVENTOS COMBOBOX
	 * *********************************************************************
	 * 
	 */
	
	/**
	 * Evento seleccion de area para traer las series asociadas
	 * @param event
	 */
	public void onSelect$cmbAreaProceso(Event event){
		Constraint constraint1 = cmbSerie.getConstraint();
		Constraint constraint2 = cmbSubSerie.getConstraint();
		Constraint constraint3 = cmbTiposDocumental.getConstraint();
		
		cmbSerie.setConstraint((String) null);
		cmbSubSerie.setConstraint((String) null);
		cmbTiposDocumental.setConstraint((String) null);
		
		if (cmbSerie != null && cmbSerie.getItems() != null && cmbSerie.getItems().size() > 0) {
			cmbSerie.getItems().clear();
			cmbSerie.setText("");
		}

		if (cmbAreaProceso != null && cmbAreaProceso.getSelectedItem() != null) {
			Area area = (Area) cmbAreaProceso.getSelectedItem().getValue();
			if (area != null) {
				Utils.llenarComboboxSerie(this.serieService.listarPorArea(area.getAreaId(), Utils.ESTADO_SERIE_INACTIVA), cmbSerie);
			}
		}
		
		if (cmbSubSerie != null && cmbSubSerie.getItems() != null && cmbSubSerie.getItems().size() > 0) {
			cmbSubSerie.getItems().clear();
			cmbSubSerie.setText("");
		}

		if (cmbSerie != null && cmbSerie.getSelectedItem() != null) {
			Serie serie = (Serie) cmbSerie.getSelectedItem().getValue();
			if (serie != null) {
				Utils.llenarComboboxSubSerie(this.subSerieService.listarPorSerie(serie.getSerieId(), Utils.ESTADO_SUBSERIE_INACTIVA), cmbSubSerie);
			}
		}
		
		if (cmbTiposDocumental != null && cmbTiposDocumental.getItems() != null && cmbTiposDocumental.getItems().size() > 0) {
			cmbTiposDocumental.getItems().clear();
			cmbTiposDocumental.setText("");
		}
		
		if (cmbSubSerie != null && cmbSubSerie.getItems() != null && cmbSubSerie.getItems().size() > 0) {
			Subserie subserie = (Subserie) cmbSubSerie.getSelectedItem().getValue();
			if (subserie != null) {
				Utils.llenarComboboxTipoDocumental(tipoDocumentalService.listar(subserie.getSubserieId(), Utils.ESTADO_TIPO_DOCUMENTAL_INACTIVO), cmbTiposDocumental);
			}
		}
		
		cmbSerie.setConstraint(constraint1);
		cmbSubSerie.setConstraint(constraint2);
		cmbTiposDocumental.setConstraint(constraint3);
	}
	
	/**
	 * Evento seleccion de serie para traer las sub series asociadas
	 * @param event
	 */
	public void onSelect$cmbSerie(Event event) {
		Constraint constraint = cmbSubSerie.getConstraint();
		cmbSubSerie.setConstraint((String) null);
		
		if (cmbSubSerie != null && cmbSubSerie.getItems() != null) {
			cmbSubSerie.getItems().clear();
			cmbSubSerie.setText("");
		}

		if (cmbSerie != null && cmbSerie.getSelectedItem() != null) {
			Serie serie = (Serie) cmbSerie.getSelectedItem().getValue();
			if (serie != null) {
				Utils.llenarComboboxSubSerie(this.subSerieService.listarPorSerie(serie.getSerieId(), Utils.ESTADO_SUBSERIE_INACTIVA), cmbSubSerie);
			}
		}
		
		if (cmbTiposDocumental != null && cmbTiposDocumental.getItems() != null) {
			cmbTiposDocumental.getItems().clear();
			cmbTiposDocumental.setText("");
		}

		if (cmbSubSerie != null && cmbSubSerie.getSelectedItem() != null) {
			Subserie subSerie = (Subserie) cmbSubSerie.getSelectedItem().getValue();
			if (subSerie != null) {
				Utils.llenarComboboxTipoDocumental(tipoDocumentalService.listar(subSerie.getSubserieId(), Utils.ESTADO_TIPO_DOCUMENTAL_INACTIVO), cmbTiposDocumental);
			}
		}
		
		cmbSubSerie.setConstraint(constraint);
	}
	
	/**
	 * Evento seleccion de sub serie para traer los tipos documentales 
	 * @param event
	 */
	public void onSelect$cmbSubSerie(Event event) {
		//Constraint constraint = cmbTiposDocumental.getConstraint();
		//cmbTiposDocumental.setConstraint((String) null);

		if (cmbTiposDocumental != null && cmbTiposDocumental.getItems() != null) {
			cmbTiposDocumental.getItems().clear();
			cmbTiposDocumental.setText("");
		}

		if (Utils.isValidoComboboxItem(cmbSubSerie)) {
			Subserie subSerie = (Subserie) cmbSubSerie.getSelectedItem().getValue();
			if (subSerie != null) {
				Utils.llenarComboboxTipoDocumental(tipoDocumentalService.listar(subSerie.getSubserieId(), Utils.ESTADO_TIPO_DOCUMENTAL_INACTIVO), cmbTiposDocumental);
			}
		}
		
		//cmbTiposDocumental.setConstraint(constraint);
		btnAgregarArchivo.setDisabled(true);
	}
	
	/**
	 * Evento seleccion de tipo documental
	 * @param event
	 */
	public void onSelect$cmbTiposDocumental(Event event){
		btnAgregarArchivo.setDisabled(false);
	}

	/**
	 * 
	 * **************************************************
	 * EVENTOS AUTOCOMPLETAR COMBOBOX DATOS REMITENTE
	 * **************************************************
	 */
	
    /**
	 * Evento auto completar del combo usuario remitente tipo radicacion SALIDA
	 * @param event
	 */
    public void onChanging$cmbNombreUsuarioSalida(@BindingParam("arg1") InputEvent event) {
		Utils.llenarComboboxUsuario(usuarioService.listarPorNombresYApellidos(idEmpresaSesion, event.getValue()), cmbNombreUsuarioSalida);
		cmbNombreUsuarioSalida.open();
    }
    
    /**
     * Evento seleccion item combobo usuario remitente tipo radicacion SALIDA
     * @param event
     */
    public void onSelect$cmbNombreUsuarioSalida(Event event){
    	lblCargoRemSalida.setValue(null);
		lblAreaProcesoRemSalida.setValue(null);
		
    	if (Utils.isValidoComboboxItem(cmbNombreUsuarioSalida)) {
    		Usuario usuario = (Usuario) cmbNombreUsuarioSalida.getSelectedItem().getValue();
    		if (usuario != null) {
    			lblCargoRemSalida.setValue(usuario.getNombreRol());
    			lblAreaProcesoRemSalida.setValue(usuario.getNombreArea());
    		}
    	}
    }
    
    /**
	 * Evento auto completar del combo usuario remitente tipo radicacion INTERNA
	 * @param event
	 */
    public void onChanging$cmbNombreUsuarioInterna(@BindingParam("arg1") InputEvent event) {
		Utils.llenarComboboxUsuario(usuarioService.listarPorNombresYApellidos(idEmpresaSesion, event.getValue()), cmbNombreUsuarioInterna);
		cmbNombreUsuarioInterna.open();
    }
    
    /**
     * Evento seleccion item combobo usuario remitente tipo radicacion INTERNA
     * @param event
     */
    public void onSelect$cmbNombreUsuarioInterna(Event event){
    	lblCargoRemInterna.setValue(null);
		lblAreaProcesoRemInterna.setValue(null);
		
    	if (Utils.isValidoComboboxItem(cmbNombreUsuarioInterna)) {
    		Usuario usuario = (Usuario) cmbNombreUsuarioInterna.getSelectedItem().getValue();
    		if (usuario != null) {
    			lblCargoRemInterna.setValue(usuario.getNombreRol());
    			lblAreaProcesoRemInterna.setValue(usuario.getNombreArea());
    		}
    	}
    }
    
    /**
	 * Evento auto completar del combobox nombre tercero remitente tipo radicacion ENTRADA
	 * @param event
	 */
    public void onChanging$cmbNombreTercero(@BindingParam("arg1") InputEvent event) {
		Utils.llenarComboboxCorTercero(corTerceroService.listarPorEmpresaYNombre(idEmpresaSesion, event.getValue(), Utils.ESTADO_TERCERO_INACTIVO), cmbNombreTercero, false);
		cmbNombreTercero.open();
    }
    
    /**
     * Action selección del item del combobox nombre tercero remitente tipo radicacion ENTRADA
     * @param event
     */
	public void onSelect$cmbNombreTercero(Event event) {
		Constraint constraint = cmbNroIndentificacion.getConstraint();
		cmbNroIndentificacion.setConstraint((String) null);
		
		cmbNroIndentificacion.setText(null);
		if (cmbNroIndentificacion.getItems() != null) {
			cmbNroIndentificacion.getItems().clear();
		}
		lblDireccionRemitente.setValue(null);
		lblCiudadRemitente.setValue(null);
		lblResponsableRemitente.setValue(null);
		lblCargoRemitente.setValue(null);
		lblDependenciaRemitente.setValue(null);
		
		if (Utils.isValidoComboboxItem(cmbNombreTercero)) {
			CorTercero corTercero = (CorTercero) cmbNombreTercero.getSelectedItem().getValue();
			if (corTercero != null) {
				Comboitem comboitem = new Comboitem();
				comboitem.setValue(corTercero);
				comboitem.setLabel(corTercero.getNumero());
				cmbNroIndentificacion.appendChild(comboitem);
				cmbNroIndentificacion.setSelectedItem(comboitem);
				lblDireccionRemitente.setValue(corTercero.getDireccion());
				lblCiudadRemitente.setValue(corTercero.getNombreMunicipio());
				lblResponsableRemitente.setValue(corTercero.getResponsable());
				lblCargoRemitente.setValue(corTercero.getCargo());
				lblDependenciaRemitente.setValue(corTercero.getDependencia());
			}
		}
		cmbNroIndentificacion.setConstraint(constraint);
	}
    
    /**
     * Evento auto completar del combo nro identificacion tercero remitente tipo radicacion ENTRADA
     * @param event
     */
    public void onChanging$cmbNroIndentificacion(@BindingParam("arg1") InputEvent event) {
		Utils.llenarComboboxCorTercero(this.corTerceroService.listarPorEmpresaYNumero(idEmpresaSesion, event.getValue(), Utils.ESTADO_TERCERO_INACTIVO), cmbNroIndentificacion, true);
		cmbNroIndentificacion.open();
    }

    /**
     * Action selección del item del combobox nro identificación tercero remitente tipo radicacion ENTRADA
     * @param event
     */
	public void onSelect$cmbNroIndentificacion(Event event) {
		Constraint constraint = cmbNombreTercero.getConstraint();
		cmbNombreTercero.setConstraint((String) null);
		
		cmbNombreTercero.setText(null);
		if (cmbNombreTercero.getItems() != null) {
			cmbNombreTercero.getItems().clear();
		}
		lblDireccionRemitente.setValue(null);
		lblCiudadRemitente.setValue(null);
		lblResponsableRemitente.setValue(null);
		lblCargoRemitente.setValue(null);
		lblDependenciaRemitente.setValue(null);
		
		if (Utils.isValidoComboboxItem(cmbNroIndentificacion)) {
			CorTercero corTercero = (CorTercero) cmbNroIndentificacion.getSelectedItem().getValue();
			if (corTercero != null) {
				Comboitem comboitem = new Comboitem();
				comboitem.setValue(corTercero);
				comboitem.setLabel(corTercero.getNombre());
				cmbNombreTercero.appendChild(comboitem);
				cmbNombreTercero.setSelectedItem(comboitem);
				lblDireccionRemitente.setValue(corTercero.getDireccion());
				lblCiudadRemitente.setValue(corTercero.getNombreMunicipio());
				lblResponsableRemitente.setValue(corTercero.getResponsable());
				lblCargoRemitente.setValue(corTercero.getCargo());
				lblDependenciaRemitente.setValue(corTercero.getDependencia());
			}
		}
		cmbNombreTercero.setConstraint(constraint);
	}
    
	/**
	 * 
	 * **************************************************
	 * COMENTARIOS
	 * **************************************************
	 */
	
	/**
	 * Evento añadir comentario
	 * @param event
	 */
	public void onClick$btnAñadirComentario(Event event) {
		txtComentario.setClass("");
		txtComentario.invalidate();
		cmbEstado.setClass("");
		cmbEstado.invalidate();
		cmbEstado.clearErrorMessage();
		txtComentario.clearErrorMessage();

		if (!Utils.isValidoComboboxItem(cmbEstado)) {
			cmbEstado.setSclass("obligatorio");
			throw new WrongValueException(cmbEstado, Labels.getLabel("commons.tooltip.campoRequerido"));
		}
		
		if (Utils.isValidoTextbox(txtComentario)) {
			Listitem item = new Listitem();
			CorComentario comentario = new CorComentario();
			
			comentario.setRadicadoId(corRadicadoEditar.getRadicadoId());
			comentario.setUsuarioId(usuarioSistema.getUsuarioId());
			comentario.setFecha(new Date());
			comentario.setComentario(txtComentario.getText());
			comentario.setEstadoRadicadoId(((EstadoRadicado) cmbEstado.getSelectedItem().getValue()).getEstadoRadId());
			
	        item.setValue(comentario);
	        item.setParent(lstComentarios);
	        item.appendChild(new Listcell(dateTimeFormat(comentario.getFecha())));
	        item.appendChild(new Listcell(usuarioSistema.getNombreCompleto()));
	        item.appendChild(new Listcell(comentario.getComentario()));
	        Utils.clearSelectedValue(txtComentario);
		} else {
			txtComentario.setSclass("obligatorio");
			throw new WrongValueException(txtComentario, Labels.getLabel("commons.tooltip.campoRequerido"));
		}
	}
	
	/**
	 * 
	 * **************************************************
	 * CANAL RECEPCION CORREO CERTIFICADO
	 * **************************************************
	 */
	
	/**
	 * Evento seleccion del combobox Canal Recepcion
	 * @param event
	 */
	public void onSelect$cmbCanalRecepcion(Event event) {
		if (cmbCanalRecepcion != null && cmbCanalRecepcion.getSelectedItem() != null) {
			CorCanal canal = (CorCanal) cmbCanalRecepcion.getSelectedItem().getValue();
			if (canal != null && canal.getCanalId() == Utils.ID_CANAL_CORREO_CERTIFICADO) {
				btnCorreroCertificado.setDisabled(false);
			} else { 
				btnCorreroCertificado.setDisabled(true);
			}
		}
	}
	
	/**
	 * Action para abrir la ventana del correo certificado
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
	 * @param event
	 */
	public void onClick$btnAceptarCorreoCertf(Event event) {
		popCorreoCert.close();
	}
	
	/**
	 * 
	 * **************************************************
	 * ROTULO
	 * **************************************************
	 */
	
	/**
	 * Evento vista preliminar del rotulo
	 * @param event
	 */
	public void onClick$btnVistaPreviaRotulo(Event event) {
		if (corRadicadoEditar.getRadicado() == null || corRadicadoEditar.getRadicado().isEmpty()) {
			lblRadicado.setSclass("obligatorio");
			throw new WrongValueException(lblRadicado, Labels.getLabel("commons.tooltip.campoRequerido"));
		}

		if (corRadicadoEditar.getTipoRadicacion() == null || corRadicadoEditar.getTipoRadicacion().toString().isEmpty()) {
			cmbTipoRadicacion.setSclass("obligatorio");
			throw new WrongValueException(cmbTipoRadicacion, Labels.getLabel("commons.tooltip.campoRequerido"));
		}
		
		if (corRadicadoEditar.getFechaRadicacion() == null) {
			lblFechaDelSistema.setSclass("obligatorio");
			throw new WrongValueException(lblFechaDelSistema, Labels.getLabel("commons.tooltip.campoRequerido"));
		}

		RotuloInfo rotuloInfo = new RotuloInfo();
		rotuloInfo.setRadicado(corRadicadoEditar.getRadicado());
		rotuloInfo.setCodigoBarras(corRadicadoEditar.getRadicado());
		rotuloInfo.setFecha(dateTimeFormat(corRadicadoEditar.getFechaRadicacion()));

		TipoRadicado tipo = TipoRadicado.getTipoCorrespondencia(corRadicadoEditar.getTipoRadicacion());
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

		if(!Utils.isValidoComboboxItem(combobox)) {
			combobox.setSclass("obligatorio");
			throw new WrongValueException(combobox, Labels.getLabel("commons.tooltip.campoRequerido"));
		}
		
		rotuloInfo.setRemitente(combobox.getValue());
		
		JasperPrint jasperPrint;
		Map<String, Object> params = new HashMap<>();
		List<RotuloInfo> rotulos = new ArrayList<>();
		rotulos.add(rotuloInfo);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

		    logoEmpresa = parametroAplicacionService.obtenerPorIdYEmpresa(ID_LOGO_EMPRESA, idEmpresaSesion);
            
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
	 * Evento imprimir rotulo
	 * @param event
	 */
	public void onClick$btnImprimir(Event event) {
		Map<String, Object> params = new HashMap<>();
        params.put("radicado", corRadicadoEditar);
        params.put("isCreacion", false);
        
        TipoRadicado tipo = TipoRadicado.getTipoCorrespondencia(corRadicadoEditar.getTipoRadicacion());
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
		params.put("remitente", combobox.getValue());
        Window window = (Window) Executions.createComponents("/correspondencia/radicar/imprimir_rotulos.zul", self, params);
        window.doModal();
	}
	
	/**
	 * Anula rotulo
	 * @param event
	 */
	public void onClick$btnAnular(Event event) {
		if (corRadicadoEditar.getEstadoId() == Utils.ID_ESTADO_RADICADO_RECIBIDO) {
			String preguntaConfirmacion = String.format(Labels.getLabel(
					"correspondencia.actualizarRadicado.mensaje.confirmar.anularRotulo"), corRadicadoEditar.getRadicado());
	        String titulo = Labels.getLabel("commons.titulo.confirmacion");
	        Messagebox.show(preguntaConfirmacion, titulo, Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
	            @Override
	            public void onEvent(Event evnt) throws Exception {
	                if (Messagebox.ON_YES.equals(evnt.getName())) {
	                	try {
	                		corRadicadoEditar = corRadicadoService.anularRadicado(SessionUtil.getAuthToken(Sessions.getCurrent()), corRadicadoEditar);
	                	} catch (BadRequestException e) {
	                		Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"), 
									Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
	                	} catch (Exception e) {
	                		Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
	            					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
						}
	                	self.onClose();
	                }
	            }
	        });
		} else {
			String mensaje = Labels.getLabel("correspondencia.actualizarRadicado.mensajeAnularRotuloEstadoNoValido");
            String tipo = "info";
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
		}
	}
	/**
	 * 
	 * **************************************************
	 * ACTUALIZAR RADICADO
	 * **************************************************
	 */
	
	/**
	 * Evento selección destinatario tipo radicado ENTRADA
	 * @param event
	 */
	public void onSelect$lstDatosDestinatarioEntrada(Event event) {
		lstDatosDestinatarioEntrada.setSclass("");
		lstDatosDestinatarioEntrada.invalidate();
	}

	/**
	 * Evento selección destinatario tipo radicado SALIDA
	 * @param event
	 */
	public void onSelect$lstDatosDestinatarioSalida(Event event) {
		lstDatosDestinatarioSalida.setSclass("");
		lstDatosDestinatarioSalida.invalidate();
	}

	/**
	 * Evento selección destinatario tipo radicado INTERNA
	 * @param event
	 */
	public void onSelect$lstDatosDestinatarioInterna(Event event) {
		lstDatosDestinatarioInterna.setSclass("");
		lstDatosDestinatarioInterna.invalidate();
	}
	
	/**	 
	 * **************************************************
	 * ADMINISTRAR TERCEROS
	 * **************************************************
	 */
	
	/**
	 * Action administrar terceros
	 * @param event
	 * @throws Exception 
	 */
	public void onClick$btnAdminTerceros(Event event) throws Exception {
		Window win = UiUtils.setModalWindow("/administracion/terceros/administrar_terceros.zul", self,
                args, false, true);
	}
	
	/**
	 * Action administrar terceros
	 * @param event
	 * @throws Exception 
	 */
	public void onClick$btnAdmTercerosRadSalida(Event event) throws Exception {
		Window win = UiUtils.setModalWindow("/administracion/terceros/administrar_terceros.zul", self,
                args, false, true);
	}
	
	/**
	 * 
	 * **************************************************
	 * ACTUALIZAR RADICADO
	 * **************************************************
	 */

	/**
	 * Validaciones para actualizar radicado
	 * @return
	 */
	private boolean isPermiteActualizar() {
		if (!Utils.isValidoComboboxItem(cmbAreaProceso)) {
			cmbAreaProceso.setSclass("obligatorio");
			throw new WrongValueException(cmbAreaProceso, Labels.getLabel("commons.tooltip.campoRequerido"));
		}

		if (!Utils.isValidoComboboxItem(cmbSerie)) {
			cmbSerie.setSclass("obligatorio");
			throw new WrongValueException(cmbSerie, Labels.getLabel("commons.tooltip.campoRequerido"));
		}

		if (!Utils.isValidoComboboxItem(cmbSubSerie)) {
			cmbSubSerie.setSclass("obligatorio");
			throw new WrongValueException(cmbSubSerie, Labels.getLabel("commons.tooltip.campoRequerido"));
		}

		if (spinCantFolios.getValue() < 1) {
			spinCantFolios.setSclass("obligatorio");
			throw new WrongValueException(spinCantFolios, Labels.getLabel("commons.tooltip.campoRequerido"));
		}

		if (!Utils.isValidoComboboxItem(cmbCanalRecepcion)) {
			cmbCanalRecepcion.setSclass("obligatorio");
			throw new WrongValueException(cmbCanalRecepcion, Labels.getLabel("commons.tooltip.campoRequerido"));
		}

		if (((CorCanal) cmbCanalRecepcion.getSelectedItem().getValue()).getCanalId() == Utils.ID_CANAL_CORREO_CERTIFICADO) {
			if (!Utils.isValidoTextbox(txtGuia)) {
				popCorreoCert.open(btnCorreroCertificado, "after_start");
				txtGuia.setSclass("obligatorio");
				throw new WrongValueException(txtGuia, Labels.getLabel("correspondencia.actualizarRadicado.validacionGuiaCF"));
			}

			if (!Utils.isValidoComboboxItem(cmbTransportadora)) {
				popCorreoCert.open(btnCorreroCertificado, "after_start");
				cmbTransportadora.setSclass("obligatorio");
				throw new WrongValueException(cmbTransportadora, Labels.getLabel("correspondencia.actualizarRadicado.validacionTransportadoraCF"));
			}

			if (dtxFechaCorreoCertif.getValue() == null) {
				popCorreoCert.open(btnCorreroCertificado, "after_start");
				dtxFechaCorreoCertif.setSclass("obligatorio");
				throw new WrongValueException(dtxFechaCorreoCertif, Labels.getLabel("correspondencia.actualizarRadicado.validacionFechaCF"));
			}

			if (!Utils.isValidoComboboxItem(cmbTipoEmbalaje)) {
				popCorreoCert.open(btnCorreroCertificado, "after_start");
				cmbTipoEmbalaje.setSclass("obligatorio");
				throw new WrongValueException(cmbTipoEmbalaje, Labels.getLabel("correspondencia.actualizarRadicado.validacionTipoEmbalajeCF"));
			}

			if (spinCantEmbalaje.getValue() == null || spinCantEmbalaje.getValue() <= 0) {
				popCorreoCert.open(btnCorreroCertificado, "after_start");
				spinCantEmbalaje.setSclass("obligatorio");
				throw new WrongValueException(spinCantEmbalaje, Labels.getLabel("correspondencia.actualizarRadicado.validacionCantEmabalajeCF"));
			}
		}

		if (!Utils.isValidoTextbox(txtAsunto)) {
			txtAsunto.setSclass("obligatorio");
			throw new WrongValueException(txtAsunto, Labels.getLabel("commons.tooltip.campoRequerido"));
		}

		TipoRadicado tipo = TipoRadicado.getTipoCorrespondencia(corRadicadoEditar.getTipoRadicacion());
		Combobox combobox = null;
		Listbox listbox = null;
		boolean isValidaRemitenteXDest = false;

		switch (tipo) {
		case ENTRADA:
			combobox = cmbNombreTercero;
			listbox = lstDatosDestinatarioEntrada;
			break;
		case SALIDA:
			combobox = cmbNombreUsuarioSalida;
			listbox = lstDatosDestinatarioSalida;
			break;
		case INTERNA:
			combobox = cmbNombreUsuarioInterna;
			listbox = lstDatosDestinatarioInterna;
			isValidaRemitenteXDest = true;
			break;
		}

		if (!Utils.isValidoComboboxItem(combobox)) {
			combobox.setSclass("obligatorio");
			throw new WrongValueException(combobox, Labels.getLabel("commons.tooltip.campoRequerido"));
		}

		if (listbox == null || listbox.getItems() == null || listbox.getItems().size() == 0) {
			listbox.setSclass("obligatorio");
			throw new WrongValueException(listbox, Labels.getLabel("commons.tooltip.campoRequerido"));
		}

		if (listbox.getSelectedItem() == null) {
			listbox.setSclass("obligatorio");
			throw new WrongValueException(listbox, Labels.getLabel("correspondencia.commons.label.destinatarioSeleccionar"));
		}
		
		/**
		 * Usuario Remitente no es Destinatario
		 */
		if (isValidaRemitenteXDest) {
			Usuario remitente = (Usuario) cmbNombreUsuarioInterna.getSelectedItem().getValue();
			for (Listitem listitem : lstDatosDestinatarioInterna.getItems()) {
				Usuario destinatario = (Usuario) listitem.getValue();
				if (destinatario.getUsuarioId() == remitente.getUsuarioId()) {
					lstDatosDestinatarioInterna.setSclass("obligatorio");
					throw new WrongValueException(lstDatosDestinatarioInterna,
							Labels.getLabel("correspondencia.asignarCorrespondencia.mensaje.errorRemitenteEsDest"));
				}	
			}
		}
		
		/**
		 * Valida radicado asociado
		 */
		radicadoAsociado = null;
		txtRadicadoAsociado.clearErrorMessage();
		
		if (Utils.isValidoTextbox(txtRadicadoAsociado)) {
			try {
				radicadoAsociado = corRadicadoService.buscarPorEmpresaYRadicado(idEmpresaSesion, txtRadicadoAsociado.getText());
				if (radicadoAsociado == null) {
					txtRadicadoAsociado.setSclass("obligatorio");
					throw new WrongValueException(txtRadicadoAsociado,
							String.format(Labels.getLabel("correspondencia.actualizarRadicado.mensaje.radicadoAsociadoNoExiste"), txtRadicadoAsociado.getText()));
				}
			} catch (Exception e) {
				txtRadicadoAsociado.setSclass("obligatorio");
				throw new WrongValueException(txtRadicadoAsociado,
						String.format(Labels.getLabel("correspondencia.actualizarRadicado.mensaje.radicadoAsociadoNoExiste"), txtRadicadoAsociado.getText().trim()));
			}
			
			if (tipo == TipoRadicado.SALIDA) {
				TipoRadicado tipoRadAsoc = TipoRadicado.getTipoCorrespondencia(radicadoAsociado.getTipoRadicacion());

				if (radicadoAsociado.isRequiereRespuesta()) {
					/**
					 * A un Radicado de salida solo se le puede asociar (radicado asociado) un Radicado de Entrada
					 */
					if (tipoRadAsoc != TipoRadicado.ENTRADA) {
						txtRadicadoAsociado.setSclass("obligatorio");
						throw new WrongValueException(txtRadicadoAsociado, Labels
								.getLabel("correspondencia.actualizarRadicado.mensaje.radSalida.reqRpta.radicadoAsocNoEsEntrada"));
					}
					
					/**
					 * A un Radicado de salida solo se le puede asociar (radicado asociado) un Radicado de Entrada que se encuentre 
					 * en estado 'Asignado' (Requiere respuesta)
					 */
					if (radicadoAsociado.getEstadoId() != Utils.ID_ESTADO_RADICADO_GESTIONADO && radicadoAsociado.getEstadoId() != Utils.ID_ESTADO_RADICADO_ASIGNADO) {
						txtRadicadoAsociado.setSclass("obligatorio");
						throw new WrongValueException(txtRadicadoAsociado, Labels.getLabel(
								"correspondencia.actualizarRadicado.mensaje.radSalida.reqRpta.radicadoAsocNoEstadoAsignado"));
					}
				} else {
					/**
					 * A un Radicado de salida solo se le puede asociar (radicado asociado) un Radicado de Entrada
					 */
					if (tipoRadAsoc != TipoRadicado.ENTRADA) {
						txtRadicadoAsociado.setSclass("obligatorio");
						throw new WrongValueException(txtRadicadoAsociado, Labels
								.getLabel("correspondencia.actualizarRadicado.mensaje.radSalida.noReqRpta.radicadoAsocNoEsEntrada"));
					}
					
					/**
					 * A un Radicado de salida solo se le puede asociar (radicado asociado) un Radicado de Entrada que se encuentre en estado 
					 * 'Informado' o 'Asignado' (No Requiere respuesta)
					 */
					if (radicadoAsociado.getEstadoId() != Utils.ID_ESTADO_RADICADO_INFORMADO && radicadoAsociado.getEstadoId() != Utils.ID_ESTADO_RADICADO_GESTIONADO) {
						txtRadicadoAsociado.setSclass("obligatorio");
						throw new WrongValueException(txtRadicadoAsociado, Labels.getLabel(
								"correspondencia.actualizarRadicado.mensaje.radSalida.noReqRpta.radicadoAsocNoEstadoAsignado"));
					}
				}
			} else if (tipo == TipoRadicado.INTERNA) {
				TipoRadicado tipoRadAsocInt = TipoRadicado.getTipoCorrespondencia(radicadoAsociado.getTipoRadicacion());
				
				if (radicadoAsociado.isRequiereRespuesta()) {
					/**
					 * A un Radicado Interno solo se le puede asociar (radicado
					 * asociado) un Radicado Interno
					 */
					if (tipoRadAsocInt != TipoRadicado.INTERNA) {
						txtRadicadoAsociado.setSclass("obligatorio");
						throw new WrongValueException(txtRadicadoAsociado, Labels.getLabel(
								"correspondencia.actualizarRadicado.mensaje.radInterna.reqRpta.radicadoAsocNoEsInterna"));
					}

					/**
					 * A un Radicado Interno solo se le puede asociar (radicado
					 * asociado) un Radicado Interno que se encuentre en estado
					 * 'Asignado' (Requiere respuesta)
					 */
					if (radicadoAsociado.getEstadoId() != Utils.ID_ESTADO_RADICADO_GESTIONADO && radicadoAsociado.getEstadoId() != Utils.ID_ESTADO_RADICADO_ASIGNADO) {
						txtRadicadoAsociado.setSclass("obligatorio");
						throw new WrongValueException(txtRadicadoAsociado, Labels.getLabel(
								"correspondencia.actualizarRadicado.mensaje.radInterna.reqRpta.radicadoAsocNoEstadoAsignado"));
					}
				} else {
					/**
					 * A un Radicado Interno solo se le puede asociar (radicado asociado) un Radicado Interno 
					 */
					if (tipoRadAsocInt != TipoRadicado.INTERNA) {
						txtRadicadoAsociado.setSclass("obligatorio");
						throw new WrongValueException(txtRadicadoAsociado, Labels
								.getLabel("correspondencia.actualizarRadicado.mensaje.radInterna.noReqRpta.radicadoAsocNoEsInterna"));
					}
					
					/**
					 * A un Radicado Interno solo se le puede asociar (radicado asociado) un Radicado Interno que se encuentre 
					 * en estado 'Informado' o 'Asignado' (No Requiere respuesta).
					 */
					if (radicadoAsociado.getEstadoId() != Utils.ID_ESTADO_RADICADO_INFORMADO && radicadoAsociado.getEstadoId() != Utils.ID_ESTADO_RADICADO_GESTIONADO) {
						txtRadicadoAsociado.setSclass("obligatorio");
						throw new WrongValueException(txtRadicadoAsociado, Labels.getLabel(
								"correspondencia.actualizarRadicado.mensaje.radInterna.noReqRpta.radicadoAsocNoEstadoAsignado"));
					}
				}
			}
		}
		
		/**
		 * Validamos comentarios
		 */
		if (corRadicadoEditar.getComentarios() != null) {
			if (corRadicadoEditar.getComentarios().size() == lstComentarios.getItemCount()) {
				txtComentario.setSclass("obligatorio");
				throw new WrongValueException(txtComentario,
						Labels.getLabel("correspondencia.actualizarRadicado.mensaje.agregarComentario"));
			}
		} else if (lstComentarios.getItems() == null || lstComentarios.getItems().size() == 0) {
			txtComentario.setSclass("obligatorio");
			throw new WrongValueException(txtComentario,
					Labels.getLabel("correspondencia.actualizarRadicado.mensaje.agregarComentario"));
		}
		
		return true;
	}
	
	/**
	 * Evento actualiza radicado
	 * @param event
	 */
	public void onClick$btnActualizar(Event event) {
		if(!isPermiteActualizar()){
			return;
		}
		
		String preguntaConfirmacion = Labels.getLabel("correspondencia.actualizarRadicado.mensajeActualizarRadicado");
        String titulo = Labels.getLabel("commons.titulo.confirmacion");
        Messagebox.show(preguntaConfirmacion, titulo, Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
            @Override
            public void onEvent(Event evnt) throws Exception {
                if (Messagebox.ON_YES.equals(evnt.getName())) {
                	actualizarRadicado();
                }
            }
        });
	}
	
	/**
	 * Actualizar Radicado
	 */
	private void actualizarRadicado() {
		boolean isShowMnjRadAsociadoSal = false;
		boolean isShowMnjRadAsociadoInt = false;
		
		corRadicadoEditar.setAreaId(((Area) cmbAreaProceso.getSelectedItem().getValue()).getAreaId());
		corRadicadoEditar.setSerieId(((Serie) cmbSerie.getSelectedItem().getValue()).getSerieId());
		corRadicadoEditar.setSubserieId(((Subserie) cmbSubSerie.getSelectedItem().getValue()).getSubserieId());
		corRadicadoEditar.setCantidadFolios(spinCantFolios.getValue());
		corRadicadoEditar.setAsunto(txtAsunto.getText());
		
		if (corRadicadoEditar.getNombreFuncionario() != null && corRadicadoEditar.getNombreFuncionario().trim().length() > 0) {
			corRadicadoEditar.setNombreFuncionario(txtNombreFuncionario.getValue());
		} else if (txtNombreFuncionario.getValue() != null && txtNombreFuncionario.getValue().trim().length() > 0) {
			corRadicadoEditar.setNombreFuncionario(txtNombreFuncionario.getValue());
		}
		
		if (corRadicadoEditar.getNroIdentificacionFunc() != null && corRadicadoEditar.getNroIdentificacionFunc().trim().length() > 0) {
			corRadicadoEditar.setNroIdentificacionFunc(txtIdFuncionario.getValue());
		} else if (txtIdFuncionario.getValue() != null && txtIdFuncionario.getValue().trim().length() > 0) {
			corRadicadoEditar.setNroIdentificacionFunc(txtIdFuncionario.getValue());
		}
		
		if (corRadicadoEditar.getRadicadoExterno() != null && corRadicadoEditar.getRadicadoExterno().trim().length() > 0) {
			corRadicadoEditar.setRadicadoExterno(txtRadicadoRemitente.getValue());
		} else if (txtRadicadoRemitente.getValue() != null && txtRadicadoRemitente.getValue().trim().length() > 0) {
			corRadicadoEditar.setRadicadoExterno(txtRadicadoRemitente.getValue());
		}
		
		corRadicadoEditar.setUsuarioModifica(usuarioSistema.getUsuarioId());
		
		corRadicadoEditar.setCanalId(((CorCanal) cmbCanalRecepcion.getSelectedItem().getValue()).getCanalId());
		if (corRadicadoEditar.getCanalId() == Utils.ID_CANAL_CORREO_CERTIFICADO) {
			corRadicadoEditar.setNumeroGuia(txtGuia.getText());
			corRadicadoEditar.setTransportadoraId(((CorTransportadora) cmbTransportadora.getSelectedItem().getValue()).getTransportadoraId());
			corRadicadoEditar.setFechaOperacion(dtxFechaCorreoCertif.getValue());
			corRadicadoEditar.setTipoEmbalajeId(((CorTipoEmbalaje) cmbTipoEmbalaje.getSelectedItem().getValue()).getTipoEmbalajeId());
			corRadicadoEditar.setCantidadEmbalaje(spinCantEmbalaje.getValue());
		}
		
		if (lstComentarios != null && lstComentarios.getItems() != null && lstComentarios.getItems().size() > 0) {
			List<CorComentario> corComentarios = new ArrayList<>();
			for (Listitem listitem : lstComentarios.getItems()) {
				CorComentario comentario = listitem.getValue();
				corComentarios.add(comentario);
			}
			corRadicadoEditar.setComentarios(corComentarios);
		}
		
		corRadicadoEditar.setRadicadoAsociado(txtRadicadoAsociado.getText() != null && txtRadicadoAsociado.getText().trim().length() > 0 ? txtRadicadoAsociado.getText() : "");
		
		/**
		 * Radicado asociado lo asignamos en nulo
		 */
		corRadicadoEditar.setRadicadoAsociadoId(null);
		
		TipoRadicado tipo = TipoRadicado.getTipoCorrespondencia(corRadicadoEditar.getTipoRadicacion());
		
		if (tipo == TipoRadicado.ENTRADA) {
			/**
			 * Tercero remitente
			 */
			corRadicadoEditar.setTerceroRemitente((CorTercero) cmbNombreTercero.getSelectedItem().getValue());
			
			/**
			 * Usuarios destinatarios
			 */
			List<Usuario> usuariosDestinatarios = new ArrayList<>();
			for (Listitem listitem : lstDatosDestinatarioEntrada.getItems()) {
				if (listitem.isSelected()) {
					corRadicadoEditar.setUsuarioPrincipal((Usuario) listitem.getValue());
				} else {
					usuariosDestinatarios.add((Usuario) listitem.getValue());
				}
			}
			corRadicadoEditar.setUsuariosDestinatarios(usuariosDestinatarios);
			
		} else if (tipo == TipoRadicado.SALIDA) {
			/**
			 * Usuario remitente
			 */
			corRadicadoEditar.setUsuarioRemitente((Usuario) cmbNombreUsuarioSalida.getSelectedItem().getValue());
			
			/**
			 * Terceros destinatarios
			 */
			List<CorTercero> tercerosDestinatarios = new ArrayList<>();
			for (Listitem listitem : lstDatosDestinatarioSalida.getItems()) {
				if (listitem.isSelected()) {
					corRadicadoEditar.setTerceroPrincipal((CorTercero) listitem.getValue());
				} else {
					tercerosDestinatarios.add((CorTercero) listitem.getValue());
				}
			}
			corRadicadoEditar.setTercerosDestinatarios(tercerosDestinatarios);
			
			/**
			 * Radicado asociado
			 */
			if (radicadoAsociado != null) {
				corRadicadoEditar.setRadicadoAsociadoId(radicadoAsociado.getRadicadoId());
				
				/**
				 * Solo se muestra el mensaje si el radicado pasa a gestionado y requiere respuesta
				 */
				if (radicadoAsociado.getEstadoId() != Utils.ID_ESTADO_RADICADO_GESTIONADO && radicadoAsociado.isRequiereRespuesta()) {
					isShowMnjRadAsociadoSal = true;
				}
				
			}
			
		} else if (tipo == TipoRadicado.INTERNA) {
			/**
			 * Usuario remitente
			 */
			corRadicadoEditar.setUsuarioRemitente((Usuario) cmbNombreUsuarioInterna.getSelectedItem().getValue());

			/**
			 * Usuario destinatario
			 */
			List<Usuario> usuariosDestinatarios = new ArrayList<>();
			for (Listitem listitem : lstDatosDestinatarioInterna.getItems()) {
				if (listitem.isSelected()) {
					corRadicadoEditar.setUsuarioPrincipal((Usuario) listitem.getValue());
				} else {
					usuariosDestinatarios.add((Usuario) listitem.getValue());
				}
			}
			corRadicadoEditar.setUsuariosDestinatarios(usuariosDestinatarios);
			
			/**
			 * Radicado asociado
			 */
			if (radicadoAsociado != null) {
				corRadicadoEditar.setRadicadoAsociadoId(radicadoAsociado.getRadicadoId());
				
				/**
				 * Solo se muestra el mensaje si el radicado pasa a gestionado y requiere respuesta
				 */
				if (radicadoAsociado.getEstadoId() != Utils.ID_ESTADO_RADICADO_GESTIONADO && radicadoAsociado.isRequiereRespuesta()) {
					isShowMnjRadAsociadoInt = true;
				}
				
			}
		}
		
		/**
		 * Adjuntos
		 */
		List<CorAdjunto> corAdjuntos = new ArrayList<>();
		for (Listitem listitem : lstArchAdjuntos.getItems()) {
			CorAdjunto adjunto = (CorAdjunto) listitem.getValue();
			corAdjuntos.add(adjunto);
		}
		corRadicadoEditar.setCorAdjuntos(corAdjuntos);
		
		/**
		 * Estado Radicado
		 */
		if (!cmbEstado.isDisabled() && Utils.isValidoComboboxItem(cmbEstado)) {
			corRadicadoEditar.setEstadoId(((EstadoRadicado) cmbEstado.getSelectedItem().getValue()).getEstadoRadId());
		}
		
		try {
			corRadicadoEditar = corRadicadoService.actualizarRadicado(SessionUtil.getAuthToken(Sessions.getCurrent()), corRadicadoEditar);
			
			/**
			 * Mensaje cambio estado radicado asociado
			 */
			if (isShowMnjRadAsociadoSal) {
				Messagebox.show(
						String.format(Labels.getLabel(
								"correspondencia.actualizarRadicado.mensaje.radSalida.cambioEstadoRadiAsociado"), radicadoAsociado.getRadicado()),
						Labels.getLabel("commons.titulo.informacion"), Messagebox.OK, Messagebox.INFORMATION);
			} else if (isShowMnjRadAsociadoInt) {
				Messagebox.show(
						String.format(Labels.getLabel(
								"correspondencia.actualizarRadicado.mensaje.radInterna.cambioEstadoRadiAsociado"), radicadoAsociado.getRadicado()),
						Labels.getLabel("commons.titulo.informacion"), Messagebox.OK, Messagebox.INFORMATION);
			}
		} catch (BadRequestException e) {
			Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"), 
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
		} catch (Exception e) {
			if (e instanceof ServerErrorException) {
				ServerErrorException ex = (ServerErrorException) e;

				if (ex.getResponse() != null && ex.getResponse().getHeaders() != null) {
					List<Object> myResponses = ex.getResponse().getHeaders().get(Utils.LLAVE_CODIGOS_ERROR_SERVER);

					if (myResponses != null && myResponses.size() > 0) {
						if (myResponses.contains(Utils.CODIGO_ERROR_IO_EXCEPTION)) {
							Messagebox.show(Labels.getLabel("correspondencia.asignarCorrespondencia.mensaje.errorAdjuntosSubir"), 
									Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
						}
					} else {
						Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"), 
								Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
					}
				}
			} else {
				Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"), 
						Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
			}
		}
		
		windowsActualizarRadicado.detach();
	}
	
	/**
	 * 
	 * **************************************************
	 * SALIR PANTALLA
	 * **************************************************
	 */
	
	/**
	 * Action cancelar edicion
	 */
	public void onClick$btnCancelar(Event event) {
		event.stopPropagation();
		salir();
	}
	
	/**
	 * Action cancelar edicion
	 */
	@Override
	public void onClose(Event event) {
		event.stopPropagation();
		salir();
	}
	
	/**
	 * salir pantalla
	 */
	private void salir() {
		String preguntaConfirmacion = Labels.getLabel("commons.mensaje.salirSinGuardar");
        String titulo = Labels.getLabel("commons.titulo.confirmacion");
        Messagebox.show(preguntaConfirmacion, titulo, Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
            @Override
            public void onEvent(Event evnt) throws Exception {
                if (Messagebox.ON_YES.equals(evnt.getName())) {
                	windowsActualizarRadicado.detach();
                }
            }
        });
	}
}
