/**
 * 
 */
package co.com.grupoasd.documental.presentacion.controller.correspondencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;

import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.cliente.catalogo.model.Serie;
import co.com.grupoasd.documental.cliente.catalogo.model.Subserie;
import co.com.grupoasd.documental.cliente.catalogo.model.TipoDocumental;
import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorAdjunto;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorComentario;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTercero;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorrespondenciaBitacoraRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.FormatosPermitidosArchivo;
import co.com.grupoasd.documental.presentacion.comun.vo.TipoRadicado;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.controller.util.GenericController;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.Utils;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.AreaService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SerieService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SubserieService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.TipoDocumentalService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.UsuarioService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.CorrespondenciaServiceFactory;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorAdjuntoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorrespondenciaBitacoraRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.FormatosPermitidosArchivoService;

/**
 * Controlador ventana Asignar Radicado
 * @author adrian.lopez
 * @since Mayo 24 2017
 */
public class AsignarRadicadoController extends GenericController<Window> {

private static final long serialVersionUID = 3221172202607053635L;
	
	/**
	 * Controles
	 */
	private Datebox dtxFechaLimiteRpta;
	private Window windowsAsignarRadicado;
	
	/**
	 * Textbox
	 */
	
	/**
	 * Combobox
	 */
	private Combobox cmbAreaProceso;
	private Combobox cmbSerie;
	private Combobox cmbSubserie;
	private Combobox cmbRequiereRespuesta;
	private Combobox cmbTiposDocumental;
	
	/**
	 * Listbox
	 */
	private Listbox lstResponsables;
	private Listbox lstArchAdjuntos;
	private Listbox lstComentarios;
	private Listbox lstTrazabilidad;
	private Listbox lstFormatos;
	
	/**
	 * Label
	 */
	private Label lblRadicado;
	private Label lblFechaRadicado;
	private Label lblUsuarioRadicacion;
	private Label lblTipoRadicado;
	private Label lblCantFolios;
	private Label lblCanal;
	private Label lblRemitente;
	private Label lblRadicadoRemitente;
	private Label lblAsunto;
	
	/**
	 * Buttons
	 */
	private Button btnAgregarArchivo;
	
	/**
	 * Services
	 */
	private AreaService areaService;
	private SubserieService subSerieService;
	private SerieService serieService;
	private CorRadicadoService corRadicadoService;
	private TipoDocumentalService tipoDocumentalService;
	private UsuarioService usuarioService;	
	private CorrespondenciaBitacoraRadicadoService bitacoraRadicadoService;
	private FormatosPermitidosArchivoService permitidosArchivoService;
	private CorAdjuntoService adjuntoService;
	
	/**
	 * Objetos generales
	 */
	private CorRadicado corRadicadoEditar;
	private Usuario usuarioSistema;
	private List<Usuario> usuariosResponsables;
	private Usuario usuarioRespPrincipal;
	private List<FormatosPermitidosArchivo> permitidosArchivos;
	private static Integer idUsuarioSesion;
	private static Integer idEmpresaSesion;
	private static Long radicadoId;
	
	/**
	 * Init de la pagina
	 */
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
			corRadicadoService = CorrespondenciaServiceFactory.getCorRadicadoService();
			tipoDocumentalService = CatalogoServiceFactory.getTipoDocumentalService();
			usuarioService = CatalogoServiceFactory.getUsuarioService();
			bitacoraRadicadoService = CorrespondenciaServiceFactory.getCorrespondenciaBitacoraRadicadoService();
			permitidosArchivoService = CorrespondenciaServiceFactory.getFormatosPermitidosArchivoService();
			adjuntoService = CorrespondenciaServiceFactory.getCorAdjuntoService();
			
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
			
			obtenerInformacionRadicado();
		} catch (NotFoundException e) {
			Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"), 
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
			windowsAsignarRadicado.detach();
		} catch (Exception e) {
			Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"), 
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
			windowsAsignarRadicado.detach();
		}
	}
	
	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * *
	 * EVENTOS INICIALES
	 * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * *
	 */
	
	/**
	 * Obtiene la información del radicado
	 */
	private void obtenerInformacionRadicado() {
		corRadicadoEditar = corRadicadoService.obtenerPorId(radicadoId);
		
		lblRadicado.setValue(corRadicadoEditar.getRadicado());
		lblFechaRadicado.setValue(dateTimeFormat(corRadicadoEditar.getFechaRadicacion()));
		lblUsuarioRadicacion.setValue(corRadicadoEditar.getNombreUsuarioRadicacion());
		
		TipoRadicado tipo = TipoRadicado.getTipoCorrespondencia(corRadicadoEditar.getTipoRadicacion());
		lblTipoRadicado.setValue(tipo.getNombre());
		
		lblCantFolios.setValue(corRadicadoEditar.getCantidadFolios() + "");
		lblCanal.setValue(corRadicadoEditar.getNombreCanal());
		
		switch (tipo) {
		case ENTRADA:
			CorTercero terceroRemitente = corRadicadoEditar.getTerceroRemitente();
			if (terceroRemitente != null) {
				lblRemitente.setValue(terceroRemitente.getNombre());
			}
			break;
		case INTERNA:
			Usuario usuarioRemitente = corRadicadoEditar.getUsuarioRemitente();
			if (usuarioRemitente != null) {
				lblRemitente.setValue(usuarioRemitente.getNombreCompleto());
			}
			break;
		default:
			break;
		}
		
		lblRadicadoRemitente.setValue(corRadicadoEditar.getRadicadoAsociado());
		lblAsunto.setValue(corRadicadoEditar.getAsunto());
		
		/**
		 * Requiere respuesta
		 */
		Utils.llenarComboboxResp(cmbRequiereRespuesta);
		String requiereRpta = "NO";
		if (corRadicadoEditar.isRequiereRespuesta()) {
			requiereRpta = "SI";
		}
		
		if (Utils.isValidoCombobox(cmbRequiereRespuesta)) {
			for (Comboitem row : cmbRequiereRespuesta.getItems()) {;
				if (row.getValue() == requiereRpta) {
					cmbRequiereRespuesta.setSelectedItem(row);
					break;
				}
			}
		}
		
		/**
		 * Destinatarios
		 */
		usuariosResponsables = corRadicadoEditar.getUsuariosDestinatarios();
		usuarioRespPrincipal = corRadicadoEditar.getUsuarioPrincipal();
		if (usuariosResponsables == null) {
			usuariosResponsables = new ArrayList<>();
		}
		
		usuariosResponsables.add(usuarioRespPrincipal);
		usuariosResponsables.forEach(x -> llenarTablaResponsables(x));
		
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
		Utils.llenarComboboxSubSerie(subSerieService.listarPorSerie(corRadicadoEditar.getSerie().getSerieId(), Utils.ESTADO_SUBSERIE_INACTIVA), cmbSubserie);	
		if (Utils.isValidoCombobox(cmbSubserie)) {
			for (Comboitem row : cmbSubserie.getItems()) {
				Subserie subserie = (Subserie) row.getValue();
				if (subserie != null && subserie.getSubserieId() == corRadicadoEditar.getSubserie().getSubserieId()) {
					cmbSubserie.setSelectedItem(row);
					break;
				}
			}
		}
		
		/**
		 * Tipo Documental
		 */
		Utils.llenarComboboxTipoDocumental(corRadicadoEditar.getTipoDocumentals(), cmbTiposDocumental);
		
		/**
		 * Listamos los comentarios asociados al radicado
		 */
		List<CorComentario> listComentarios = corRadicadoEditar.getComentarios();
		if (listComentarios != null && listComentarios.size() > 0) {
			for (CorComentario comentario : listComentarios) {
				Listitem item = new Listitem();
				item.setValue(comentario);
				item.setParent(lstComentarios);
				item.appendChild(new Listcell(dateTimeFormat(comentario.getFecha())));
				Usuario usuario = usuarioService.obtenerPorId(comentario.getUsuarioId());
				item.appendChild(new Listcell(usuario.getNombreCompleto()));
				item.appendChild(new Listcell(comentario.getComentario()));
			}
		}
		
		/**
		 * Trazabilidad
		 */
		List<CorrespondenciaBitacoraRadicado> bitacoraRadicados = bitacoraRadicadoService.listarPorRadicadoId(corRadicadoEditar.getRadicadoId());
		if (bitacoraRadicados != null && bitacoraRadicados.size() > 0) {
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
		}
		
		if (corRadicadoEditar.getFechaVencimientoRta() != null) {
			dtxFechaLimiteRpta.setValue(corRadicadoEditar.getFechaVencimientoRta());
		} else {
			dtxFechaLimiteRpta.setValue(new Date());
		}
		
		/**
		 * Adjuntos
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
						Messagebox.show("El documento solicitado no existe, Por favor Verifique que el documento exista.", 
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
	 * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * *
	 * EVENTOS COMBOBOX
	 * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * *
	 */
	
	/**
	 * Evento seleccion de area para traer las series asociadas
	 * @param event
	 */
	public void onSelect$cmbAreaProceso(Event event){
		Constraint constraint1 = cmbSerie.getConstraint();
		Constraint constraint2 = cmbSubserie.getConstraint();
		Constraint constraint3 = cmbTiposDocumental.getConstraint();
		
		cmbSerie.setConstraint((String) null);
		cmbSubserie.setConstraint((String) null);
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
		
		if (cmbSubserie != null && cmbSubserie.getItems() != null && cmbSubserie.getItems().size() > 0) {
			cmbSubserie.getItems().clear();
			cmbSubserie.setText("");
		}

		if (cmbSerie != null && cmbSerie.getSelectedItem() != null) {
			Serie serie = (Serie) cmbSerie.getSelectedItem().getValue();
			if (serie != null) {
				Utils.llenarComboboxSubSerie(this.subSerieService.listarPorSerie(serie.getSerieId(), Utils.ESTADO_SUBSERIE_INACTIVA), cmbSubserie);
			}
		}
		
		if (cmbTiposDocumental != null && cmbTiposDocumental.getItems() != null && cmbTiposDocumental.getItems().size() > 0) {
			cmbTiposDocumental.getItems().clear();
			cmbTiposDocumental.setText("");
		}
		
		if (cmbSubserie != null && cmbSubserie.getItems() != null && cmbSubserie.getItems().size() > 0) {
			Subserie subserie = (Subserie) cmbSubserie.getSelectedItem().getValue();
			if (subserie != null) {
				Utils.llenarComboboxTipoDocumental(tipoDocumentalService.listar(subserie.getSubserieId(), Utils.ESTADO_TIPO_DOCUMENTAL_INACTIVO), cmbTiposDocumental);
			}
		}
		
		cmbSerie.setConstraint(constraint1);
		cmbSubserie.setConstraint(constraint2);
		cmbTiposDocumental.setConstraint(constraint3);
	}
    
	/**
	 * Evento seleccion de serie para traer las sub series asociadas
	 * @param event
	 */
	public void onSelect$cmbSerie(Event event) {
		Constraint constraint = cmbSubserie.getConstraint();
		cmbSubserie.setConstraint((String) null);
		
		if (cmbSubserie != null && cmbSubserie.getItems() != null) {
			cmbSubserie.getItems().clear();
			cmbSubserie.setText("");
		}

		if (cmbSerie != null && cmbSerie.getSelectedItem() != null) {
			Serie serie = (Serie) cmbSerie.getSelectedItem().getValue();
			if (serie != null) {
				Utils.llenarComboboxSubSerie(this.subSerieService.listarPorSerie(serie.getSerieId(), Utils.ESTADO_SUBSERIE_INACTIVA), cmbSubserie);
			}
		}
		
		if (cmbTiposDocumental != null && cmbTiposDocumental.getItems() != null) {
			cmbTiposDocumental.getItems().clear();
			cmbTiposDocumental.setText("");
		}

		if (cmbSubserie != null && cmbSubserie.getSelectedItem() != null) {
			Subserie subSerie = (Subserie) cmbSubserie.getSelectedItem().getValue();
			if (subSerie != null) {
				Utils.llenarComboboxTipoDocumental(tipoDocumentalService.listar(subSerie.getSubserieId(), Utils.ESTADO_TIPO_DOCUMENTAL_INACTIVO), cmbTiposDocumental);
			}
		}
		
		cmbSubserie.setConstraint(constraint);
	}
	
	/**
	 * Evento seleccion de sub serie para traer los tipos documentales 
	 * @param event
	 */
	public void onSelect$cmbSubserie(Event event) {
		//Constraint constraint = cmbTiposDocumental.getConstraint();
		//cmbTiposDocumental.setConstraint((String) null);

		if (cmbTiposDocumental != null && cmbTiposDocumental.getItems() != null) {
			cmbTiposDocumental.getItems().clear();
			cmbTiposDocumental.setText("");
		}

		if (Utils.isValidoComboboxItem(cmbSubserie)) {
			Subserie subSerie = (Subserie) cmbSubserie.getSelectedItem().getValue();
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
	 * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * *
	 * EVENTO AGREGAR RESPONSABLE
	 * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * *
	 */
	
	/**
	 * Evento selección destinatario tipo radicado ENTRADA
	 * @param event
	 */
	public void onSelect$lstResponsables(Event event) {
		lstResponsables.setSclass("");
		lstResponsables.invalidate();
	}
	
	/**
	 * Evento abrir ventana para seleccionar usuarios destinatarios ENTRADA
	 * @param event
	 */
	public void onClick$btnAgregarResponsales(Event event) {
		/**
		 * Obtenemos el item seleccionado del Listbox y lo asignamos como usuario principal
		 */
		usuarioRespPrincipal = lstResponsables != null && lstResponsables.getSelectedItem() != null ? lstResponsables.getSelectedItem().getValue() : null;
		
		Map<String, Object> params = new HashMap<>();
        params.put("callback", "onCargarResponsables");
        params.put("empresaId", idEmpresaSesion);
        params.put("usuarios", usuariosResponsables);
        params.put("isRespuestaMvc", true);
        Utils.setModalWindow("/correspondencia/radicar/agregar_responsable.zul", self, params, false, true);
	}
	
	/**
	 * Funcion que llama la ventana agregar destinatario usuario ENTRADA
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	public void onCargarResponsables(Event event) {
		lstResponsables.getItems().clear();
		usuariosResponsables = (List<Usuario>) event.getData();
		
		if (usuariosResponsables != null && usuariosResponsables.size() > 0) {
			/**
			 * Si el usuario principal no se encuentra en la lista de seleccion, se asigna como null
			 */
			if (!usuariosResponsables.contains(usuarioRespPrincipal)) {
				usuarioRespPrincipal = null;
			}
			
			for (Usuario usuario : usuariosResponsables) {
				llenarTablaResponsables(usuario);
			}
		}
	}
	
	/**
	 * Llena la tabla de los responsables
	 * @param usuario Objeto Usuario
	 */
	private void llenarTablaResponsables(Usuario usuario) {
		Listitem item = new Listitem();
		item.setValue(usuario);
		item.setParent(lstResponsables);

		item.appendChild(new Listcell(""));
		item.appendChild(new Listcell(usuario.getNumeroIdentificacion()));
		item.appendChild(new Listcell(usuario.getNombreCompleto()));
		item.appendChild(new Listcell(usuario.getNombreRol()));
		item.appendChild(new Listcell(usuario.getNombreArea()));
		item.appendChild(new Listcell(usuario.getCorreo()));

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
									lstResponsables.removeItemAt(item.getIndex());
									usuariosResponsables.remove(usuario);
								}
							}
						});
			}
		});

		//TODO: Verificar si se filtra por empresa
		if (usuarioRespPrincipal != null && usuario.getUsuarioId() == usuarioRespPrincipal.getUsuarioId()) {
			item.setSelected(true);
		}
	}
	
	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * *
	 * ADJUNTOS
	 * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * *
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
							 TipoDocumental documental = (TipoDocumental) cmbTiposDocumental.getSelectedItem().getValue();
							 if (documental != null) {
								 corAdjunto.setNombreTipoDocumental(documental.getNombre());
								 corAdjunto.setTipoDocumentalId(documental.getTipoDocumentalId());
							 }
						 }
						 
						 isExisteAdjunto = false;
						 
						 if (Utils.isValidoListbox(lstArchAdjuntos))
							 for (Listitem listitem : lstArchAdjuntos.getItems()) {
								 CorAdjunto adjunto = (CorAdjunto) listitem.getValue();
								 if (adjunto.getRuta().equalsIgnoreCase(corAdjunto.getRuta()) && adjunto.getTipoDocumentalId() == corAdjunto.getTipoDocumentalId()) {
									 Clients.showNotification(String.format(Labels.getLabel("correspondencia.actualizarRadicado.mensajeExisteAdjunto"), 
											 adjunto.getRuta(), adjunto.getNombreTipoDocumental()), "warning", lstArchAdjuntos, "middle_center", 5000, true);
									 
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
									Utils.mostrarArchivoAdjunto(corAdjunto.getRuta(), null, corAdjunto.getBytesAdjuntos(), self);	
								}
							});
							
							
							Toolbarbutton toolbarbutton = new Toolbarbutton("", "/recursos/icons/delete.png");
							toolbarbutton.setParent(listcell);
							toolbarbutton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
								@Override
								public void onEvent(Event arg0) throws Exception {
									Messagebox.show(String.format(Labels.getLabel("correspondencia.actualizarRadicado.mensajeEliminarAdjunto"), ((CorAdjunto) item.getValue()).getRuta()), "Advertencia", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
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
					 }else {
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
                	windowsAsignarRadicado.detach();
                }
            }
        });
	}

	/**
	 * 
	 * **************************************************
	 * ASIGNAR RADICADO
	 * **************************************************
	 */
	
	/**
	 * Action asignar radicado
	 * @param event
	 */
	public void onClick$btnAsignar(Event event) {
		if (!isPermiteAsignar()){
			return;
		}
		
		String preguntaConfirmacion = Labels.getLabel("correspondencia.actualizarRadicado.mensajeActualizarRadicado");
        String titulo = Labels.getLabel("commons.titulo.confirmacion");
        Messagebox.show(preguntaConfirmacion, titulo, Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
            @Override
            public void onEvent(Event evnt) throws Exception {
                if (Messagebox.ON_YES.equals(evnt.getName())) {
                	asignarRadicado();
                }
            }
        });
	}
	
	/**
	 * Action asignar radicado
	 */
	private void asignarRadicado() {
		corRadicadoEditar.setAreaId(((Area) cmbAreaProceso.getSelectedItem().getValue()).getAreaId());
		corRadicadoEditar.setSerieId(((Serie) cmbSerie.getSelectedItem().getValue()).getSerieId());
		corRadicadoEditar.setSubserieId(((Subserie) cmbSubserie.getSelectedItem().getValue()).getSubserieId());
		
		/**
		 * Radicados que requieren respuesta: Pasan a estado 'Asignado' 
		 * Radicados que No requieren respuesta: Pasa a estado 'Informado' 
		 */
		if (cmbRequiereRespuesta.getValue().equalsIgnoreCase("SI")) {
			corRadicadoEditar.setRequiereRespuesta(true);
			corRadicadoEditar.setEstadoId(Utils.ID_ESTADO_RADICADO_ASIGNADO);
		} else {
			corRadicadoEditar.setRequiereRespuesta(false);
			corRadicadoEditar.setEstadoId(Utils.ID_ESTADO_RADICADO_INFORMADO);
		}
		
		/**
		 * Usuarios responsables
		 */
		List<Usuario> usuariosResponsables = new ArrayList<>();
		for (Listitem listitem : lstResponsables.getItems()) {
			if (listitem.isSelected()) {
				corRadicadoEditar.setUsuarioPrincipal((Usuario) listitem.getValue());
			} else {
				usuariosResponsables.add((Usuario) listitem.getValue());
			}
		}
		
		corRadicadoEditar.setUsuariosDestinatarios(usuariosResponsables);
		
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
		 * Usuario modifica
		 */
		corRadicadoEditar.setUsuarioModifica(idUsuarioSesion);
		
		corRadicadoEditar.setFechaVencimientoRta(dtxFechaLimiteRpta.getValue());
		
		try {
			corRadicadoEditar = corRadicadoService.asignarResponsables(SessionUtil.getAuthToken(Sessions.getCurrent()), corRadicadoEditar);
		} catch (BadRequestException e) {
			Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"), 
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
		} catch (Exception e) {
			if (e instanceof ServerErrorException) {
				ServerErrorException ex = (ServerErrorException) e;
				
				if (ex.getResponse() != null && ex.getResponse().getHeaders() != null) {
					List<Object> myResponses = ex.getResponse().getHeaders().get(Utils.LLAVE_CODIGOS_ERROR_SERVER);
					
					if (myResponses != null && myResponses.size() > 0) {
						if (myResponses.contains(Utils.CODIGO_ERROR_MESSAGING_EXCEPTION)) {
							Messagebox.show(
									Labels.getLabel("correspondencia.asignarCorrespondencia.mensaje.errorEnvioEmail"),
									Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
						} else if (myResponses.contains(Utils.CODIGO_ERROR_IO_EXCEPTION)) {
							Messagebox.show(
									Labels.getLabel("correspondencia.asignarCorrespondencia.mensaje.errorAdjuntosSubir"),
									Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
						} else {
							Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"), 
									Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
						}
					}
				}
			} else {
				Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"), 
						Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
			}
		}
		
		/**
		 * Cierra ventana
		 */
		windowsAsignarRadicado.detach();
	}
	
	/**
	 * Valida que se pueda realizar la asignación
	 * @return
	 */
	private boolean isPermiteAsignar() {
		if (!Utils.isValidoComboboxItem(cmbAreaProceso)) {
			cmbAreaProceso.setSclass("obligatorio");
			throw new WrongValueException(cmbAreaProceso, Labels.getLabel("commons.tooltip.campoRequerido"));
		}

		if (!Utils.isValidoComboboxItem(cmbSerie)) {
			cmbSerie.setSclass("obligatorio");
			throw new WrongValueException(cmbSerie, Labels.getLabel("commons.tooltip.campoRequerido"));
		}

		if (!Utils.isValidoComboboxItem(cmbSubserie)) {
			cmbSubserie.setSclass("obligatorio");
			throw new WrongValueException(cmbSubserie, Labels.getLabel("commons.tooltip.campoRequerido"));
		}
		
		if (!Utils.isValidoComboboxItem(cmbRequiereRespuesta)) {
			cmbRequiereRespuesta.setSclass("obligatorio");
			throw new WrongValueException(cmbRequiereRespuesta, Labels.getLabel("commons.tooltip.campoRequerido"));
		}
		
		/**
		 * Si el radicado requiere respuesta, se valida que la fecha limite no sea igual a null
		 */
		if (cmbRequiereRespuesta.getValue().equalsIgnoreCase("SI")) {
			if (dtxFechaLimiteRpta.getValue() == null) {
				dtxFechaLimiteRpta.setSclass("obligatorio");
				throw new WrongValueException(dtxFechaLimiteRpta, Labels.getLabel("commons.tooltip.campoRequerido"));
			}
		} 
		
		if (lstResponsables == null || lstResponsables.getItems() == null || lstResponsables.getItems().size() == 0) {
			lstResponsables.setSclass("obligatorio");
			throw new WrongValueException(lstResponsables, Labels.getLabel("commons.tooltip.campoRequerido"));
		}

		if (lstResponsables.getSelectedItem() == null) {
			lstResponsables.setSclass("obligatorio");
			throw new WrongValueException(lstResponsables, Labels.getLabel("correspondencia.asignarCorrespondencia.mensaje.noSeleccionResponsable"));
		}	
		
		return true;
	}
}
