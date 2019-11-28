package co.com.grupoasd.documental.presentacion.controller.trd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.trd.model.TrdTablaRetencion;
import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.comun.vo.ConstantAttributes;
import co.com.grupoasd.documental.presentacion.comun.vo.EstadoTrd;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.controller.util.Base64;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;
import co.com.grupoasd.documental.presentacion.service.trd.TrdServiceFactory;
import co.com.grupoasd.documental.presentacion.service.trd.dto.ResultadoGeneracionTablaRetencionExcel;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdTablaRetencionService;

/**
 * Controlador de la vista Configuración Tabla Retención Documental
 * 
 * @author gmora
 * @since Junio 21 de 2017
 */
public class AdminTablaRetController {

	@Wire
	private Window windowsAdminTablaRet;
	@Wire
	private Textbox txtBusqueda;
	@Wire
	private Button btnNuevaTrd;
	@Wire
	private Button btnCopiarTrd;
	@Wire
	private Button btnNuevaTrd2;
	@Wire
	private Button btnCopiarTrd2;
	@Wire
	private Button btnBuscar;
	@Wire
	private Groupbox grbBusqueda;
	@Wire
	private Groupbox grbBotones;
	@Wire
	private Groupbox grbTable;
	@Wire
	private Groupbox grbTableFiltro;
	@Wire
	private Listbox lstTrds;
	@Wire
	private Listbox lstTrdsFiltro;

	private Integer idEmpresaSesion;
	private List<TrdTablaRetencion> listadoTrds = new ArrayList<>();
	private List<TrdTablaRetencion> listadoTrdsFiltro = new ArrayList<>();
	private TrdTablaRetencion trdSelected = new TrdTablaRetencion();

	private final static int ESTADO_ABIERTO = 1;
	private final static int EN_EDICION = 3;	

	private static final Logger LOG = Logger.getLogger(AdminTablaRetController.class.getName());

	@Wire
	private Groupbox grbTableNoResults;

	/**
	 * Servicios
	 */
	private TrdTablaRetencionService opcionTrdService;	

	@Init
	public void init(Event event) {
		try {			
			idEmpresaSesion = ((SesionVo) Sessions.getCurrent().getAttribute("usrAuth")).getEmpresaId();		

			/**
			 * Inicializa servicios
			 */

		} catch (Exception e) {
			listadoTrds = null;
			grbTable.setVisible(true);
			grbTableFiltro.setVisible(false);
			Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
		}

	}

	/**
	 * Método que se ejecuta después de que ZK crea los componentes de la vista.
	 * 
	 * @param view
	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		try {
			idEmpresaSesion = ((SesionVo) Sessions.getCurrent().getAttribute("usrAuth")).getEmpresaId();

			/**
			 * Inicializa servicios
			 */
			opcionTrdService = TrdServiceFactory.getTrdTablaRetencionService();
			cargarDatosIniciales();
			grbTable.setVisible(true);
			grbTableFiltro.setVisible(false);
			btnCopiarTrd.setDisabled(true);
			btnCopiarTrd2.setDisabled(true);

		} catch (ServerErrorException ex) {
			Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
		} catch (Exception ex) {
			Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
		}
	}

	/**
	 * Metodo que realiza la carga inicial de datos con las validaciones
	 * necesarias
	 * 
	 * 
	 */
	@NotifyChange({ "listadoTrds", "trdSeleccionado" })
	public void cargarDatosIniciales() {
		listadoTrds.clear();
		try {
			listadoTrds = opcionTrdService.listarPorEmpresaIdAndEstadoDelExpediente(idEmpresaSesion, ESTADO_ABIERTO);
		} catch (NotFoundException e) {
			listadoTrds = new ArrayList<>();
			Messagebox.show("No hay datos para mostrar",
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
		}
		
		if (hasTrdEnEdicion()) {
			btnNuevaTrd.setDisabled(true);
		}
	}

	/**
	 * Metodo que valida si la empresa tiene TRD en estado de Edicion
	 * 
	 * 
	 */
	public boolean hasTrdEnEdicion() {
		if (listadoTrds.size() == 0) {
			btnCopiarTrd.setDisabled(true);
			btnCopiarTrd2.setDisabled(true);
			btnBuscar.setDisabled(true);
		} else {
			for (TrdTablaRetencion trd : listadoTrds) {
				if (EstadoTrd.getEstadoTrd(trd.getEstado().getEstadoId()).getNombre().equalsIgnoreCase("E")) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Action crear una nueva TRD
	 * 
	 * @param event
	 */
	@Command
	public void nuevaTrd() {
		Messagebox.show("Mensaje", Labels.getLabel("retencion.admin.confirmacion.mensaje.crear"),
				Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(Event evnt) throws Exception {
						if (Messagebox.ON_YES.equals(evnt.getName())) {
							try {
								Map<String, Object> args = new HashMap<>();
								args.put("trdId", null);
								UiUtils.setModalWindow("/trd/configuracion/config_tabla_retencion.zul",
										windowsAdminTablaRet, args, false, true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				});

	}

	/**
	 * Action copiar la trd
	 * 
	 * @param event
	 */
	@Command
	public void copiarTrd(@BindingParam("arg1") TrdTablaRetencion trd) {
		Messagebox.show(Labels.getLabel("retencion.admin.confirmacion.mensaje.copiar"), "Mensaje",
				Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(Event evnt) throws Exception {
						if (Messagebox.ON_YES.equals(evnt.getName())) {
							try {
								 System.out.println("Prueba:............... " + trdSelected.getNombre());
								 Map<String, Object> args = new HashMap<>();
								 args.put("trdId", trd.getTrdId());								
								 @SuppressWarnings("unused")
								 Window win =  UiUtils.setModalWindow("/trd/configuracion/copy_tabla_retencion.zul",
								 windowsAdminTablaRet,  args, false, true);
							
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				});

	}

	/**
	 * Action Ver la información de la TRD
	 * 
	 * @param event
	 * @throws Exception
	 */
	@Command
	public void ver(@BindingParam("arg1") TrdTablaRetencion trd) throws Exception {
		try {
			if (trd != null) {
				Map<String, Object> args = new HashMap<>();
				args.put("trdId", trd.getTrdId());
				@SuppressWarnings("unused")
				Window win = UiUtils.setModalWindow("/trd/administracion/ver_tabla_retencion.zul", windowsAdminTablaRet,
						args, false, true);
			}
		} catch (Exception ex) {
			Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
		}
		
	}


	/**
	 * Action Exportar la información de la TRD
	 * 
	 * @param event
	 */
	@Command
	public void exportar(@BindingParam("arg1") TrdTablaRetencion trd) {
		try {			
		 ResultadoGeneracionTablaRetencionExcel resultado= opcionTrdService.exportarToXSL(trd.getTrdId());
           if(resultado != null && resultado.getFile() != null){
        	   try {
                   resultado.getFile()
                           .setBytesArchivo(Base64.decodeFast(resultado.getFile().getArchivo()));
                   mostrarBytesArchivo(resultado.getFile());
                   BindUtils.postNotifyChange(null, null, AdminTablaRetController.this, "*");
               } catch (Exception ex) {
                   LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                   UiUtils.mostrarInformacion(Labels.getLabel("retencion.admin.label.generacion.archivo.error " + ex));
               }
        	  
           }
           else{
        	   UiUtils.mostrarError(Labels.getLabel("retencion.admin.label.generacion.archivo.error"));
           }           
		} catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(Labels.getLabel("excepcion.no.identificado") + " "
                    + new String[] { ex.getLocalizedMessage() });
        }
	}

	/**
	 * Action Eliminar la información de la TRD
	 * 
	 * @param event
	 */
	@Command
	public void eliminar(@BindingParam("arg1") TrdTablaRetencion trd) {
		// Solo se habilita para la TRD en edicion
		Messagebox.show(Labels.getLabel("retencion.admin.confirmacion.mensaje.borrar"), "Advertencia",
				Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(Event evnt) throws Exception {
						if (Messagebox.ON_YES.equals(evnt.getName())) {
							if (trd != null && trd.getEstado().getEstadoId().equals(EN_EDICION)) {
								try {
									if (opcionTrdService.eliminarTrd(SessionUtil.getAuthToken(Sessions.getCurrent()),
											trd.getTrdId())) {
										BindUtils.postNotifyChange(null, null, AdminTablaRetController.this, "*");
										cargarDatosIniciales();
									}
								} catch (BadRequestException ex) {
									Messagebox.show(Labels.getLabel("retencion.admin.label.eliminar.error"),
											Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
								}
								catch (Exception ex) {
									Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
											Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
								}
							}
						}
					}
				});
	}

	/**	 
	 * Action buscar la información de la TRD digitada en el textbox
	 * mediante la accion clic del boton buscar 	
	 * 
	 * @param event
	 */
	@Command
	public void buscarTrdXButton() {
		if (txtBusqueda != null && !txtBusqueda.getText().isEmpty()) {
			search(txtBusqueda.getText());
		} else {
			grbTable.setVisible(true);
			grbTableFiltro.setVisible(false);
		}
		BindUtils.postNotifyChange(null, null, AdminTablaRetController.this, "*");
	}


	public void search(String textobusqueda) {
		CharSequence s = textobusqueda.toLowerCase();
		listadoTrdsFiltro.clear();
		listadoTrdsFiltro = opcionTrdService.filtrar(listadoTrds, s.toString());

		if (listadoTrdsFiltro.size() > 0) {
			if (listadoTrdsFiltro.size() == listadoTrds.size()) {
				grbTable.setVisible(true);
				grbTableFiltro.setVisible(false);
				return;
			}
			grbTable.setVisible(false);
			grbTableFiltro.setVisible(true);
		}
	}
	

	@Command
	@NotifyChange({ "trdSelected" })
	public void habilitarBtn() {
		btnCopiarTrd.setDisabled(false);
		btnCopiarTrd2.setDisabled(false);
		System.out.println("Prueba:............... " + trdSelected.getNombre());
	}
	
	/**
	 * Action copiar la trd
	 * 
	 * @param event
	 */
	@Command
	public void editar(@BindingParam("arg1") TrdTablaRetencion trd) {
		Messagebox.show(Labels.getLabel("retencion.admin.confirmacion.mensaje.editar"), "Mensaje",
				Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(Event evnt) throws Exception {
						if (Messagebox.ON_YES.equals(evnt.getName())) {
							try {
								 System.out.println("Prueba:............... " + trdSelected.getNombre());
								 Map<String, Object> args = new HashMap<>();
								 args.put("trdId", trd.getTrdId());	
								 @SuppressWarnings("unused")
								 Window win =  UiUtils.setModalWindow("/trd/configuracion/config_tabla_retencion.zul",
								 windowsAdminTablaRet,  args, false, true);
							
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				});

	}
	
	
	protected void mostrarBytesArchivo(InfoMedia infoMedia) {
        Media media = new AMedia(infoMedia.getNombreArchivo(), infoMedia.getTipoArchivo(), null, infoMedia.getBytesArchivo());
        if (!(media.getFormat() != null && media.getFormat().contains("tif"))
                && (media.getContentType() == null
                        || (!media.getContentType().contains("image")
                                && !media.getContentType().contains("pdf")))) {
            Sessions.getCurrent().setAttribute(ConstantAttributes.DOWNLOAD, media);
            Executions.getCurrent().sendRedirect(ConstantAttributes.DOWNLOAD_PAGE, "other");
        } else {
            Window window = new Window("Visor", "normal", true);
            window.setHeight("90%");
            window.setWidth("90%");
            window.setStyle("max-height:100%:max-width:100%;margin: auto;text-align: center;");
            window.setParent(windowsAdminTablaRet);
            window.doModal();
            window.setSizable(true);
            
            Iframe iframe = new Iframe();
            iframe.setHflex("true");
            iframe.setVflex("true");
            iframe.setStyle("max-height:100%:max-width:100%;margin: auto;text-align: center;");
            iframe.setParent(window);
            iframe.setContent(media);
        }
    }

	public List<TrdTablaRetencion> getListadoTrds() {
		return listadoTrds;
	}

	public void setListadoTrds(List<TrdTablaRetencion> listadoTrds) {
		this.listadoTrds = listadoTrds;
	}

	public List<TrdTablaRetencion> getListadoTrdsFiltro() {
		return listadoTrdsFiltro;
	}

	public void setListadoTrdsFiltro(List<TrdTablaRetencion> listadoTrds) {
		this.listadoTrdsFiltro = listadoTrds;
	}

	/**
	 * @return the trdSelected
	 */
	public TrdTablaRetencion getTrdSelected() {
		return trdSelected;
	}

	/**
	 * @param trdSelected
	 *            the trdSelected to set
	 */
	public void setTrdSelected(TrdTablaRetencion trdSelected) {
		this.trdSelected = trdSelected;
	}

}
