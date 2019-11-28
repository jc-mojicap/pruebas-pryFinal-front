/**
 * 
 */
package co.com.grupoasd.documental.presentacion.controller.trd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.cliente.catalogo.model.Empresa;
import co.com.grupoasd.documental.cliente.catalogo.model.Serie;
import co.com.grupoasd.documental.cliente.catalogo.model.Subserie;
import co.com.grupoasd.documental.cliente.catalogo.model.TipoDocumental;
import co.com.grupoasd.documental.cliente.trd.model.TrdOpcionDisposicionFinal;
import co.com.grupoasd.documental.cliente.trd.model.TrdTablaRetencion;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.controller.util.GenericController;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;
import co.com.grupoasd.documental.presentacion.controller.util.Utils;
import co.com.grupoasd.documental.presentacion.service.trd.TrdServiceFactory;
import co.com.grupoasd.documental.presentacion.service.trd.dto.TrdTablaRetencionGeneralDto;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdOpcionDisposicionFinalService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdTablaRetencionService;

/**
 * Controllador de la vista Configuración Tabla Retención Documental
 * 
 * @author alopez
 * @since Junio 12 de 2017
 */
public class ConfigTablaRetController extends GenericController<Window> {

	private static final long serialVersionUID = 3221172202607053635L;

	/**
	 * Tree
	 */
	private Tree treeTrd;
	private Treeitem treeitemEmpresa = new Treeitem();
	private Treechildren treechildrenEmpresa = new Treechildren();

	/**
	 * Objetos generales
	 */
	private Integer idUsuarioSesion;
	private Integer idEmpresaSesion;
	private String nombreEmpresaSesion;
	private String nombreTrd;
	private List<Area> areas;
	private List<Serie> series;
	private List<Subserie> subseries;
	private List<TipoDocumental> tiposDocumentales;
	private List<TrdOpcionDisposicionFinal> opcionesDispFinal;

	/**
	 * Servicios
	 */
	private TrdOpcionDisposicionFinalService opcionDisposicionFinalService;
	private TrdTablaRetencionService tablaRetencionService;

	/**
	 * Tree
	 */
	private Treecols treecols;
	private Auxheader auxheaderOpcDispFinal;
	private Auxhead auxHeadPrincipal;

	/**
	 * Staticas
	 */
	private final static int DISPOSICION_FINAL_CONSERV_TOTAL = 1;
	private final static int DISPOSICION_FINAL_ELIMINACION = 2;
	private final static int DISPOSICION_FINAL_SELECCION = 3;
	private final static int NUMERO_CELDA_AG = 1;
	private final static int NUMERO_CELDA_AC = 2;
	private final static int NUMERO_CELDA_CT = 3;
	private final static int NUMERO_CELDA_E = 4;
	private final static int NUMERO_CELDA_S = 5;
	private final static int NUMERO_CELDA_PROCEDIMIENTO = 6;

	/**
	 * Constructor
	 */
	@Override
	public void onCreate(Event event) {
		try {
			/**
			 * Valores por sesión
			 */
			idUsuarioSesion = ((SesionVo) Sessions.getCurrent().getAttribute("usrAuth")).getUsuarioId();
			idEmpresaSesion = ((SesionVo) Sessions.getCurrent().getAttribute("usrAuth")).getEmpresaId();
			nombreEmpresaSesion = ((SesionVo) Sessions.getCurrent().getAttribute("usrAuth")).getEmpresa();
			areas = new ArrayList<>();

			/**
			 * Inicializa servicios
			 */
			opcionDisposicionFinalService = TrdServiceFactory.getTrdOpcionDisposicionFinalService();
			tablaRetencionService = TrdServiceFactory.getTrdTablaRetencionService();

			/**
			 * Obtenemos las opciones de disposición final parametrizadas en el
			 * sistema
			 */
			try {
				opcionesDispFinal = opcionDisposicionFinalService.listarPorEmpresaAndEstado(idEmpresaSesion, false);
			} catch (NotFoundException ex) {
				opcionesDispFinal = null;
				/**
				 * Eliminamos el header de Opciones de disposición final
				 */
				auxHeadPrincipal.removeChild(auxheaderOpcDispFinal);
			}

			/**
			 * Inicializamos el componente padre de los nodos
			 */
			inicializarNodos();

		} catch (Exception e) {
			Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
			self.detach();
		}
	}

	/**
	 * Action inicializar nodos
	 */
	private void inicializarNodos() {
		nombreTrd = tablaRetencionService.obtenerNombreTrd(idEmpresaSesion, nombreEmpresaSesion);
		
		/**
		 * Areas
		 */
		Treecol treecolAreaSeSubTipDoc = new Treecol();
		treecolAreaSeSubTipDoc.setLabel("");
		treecolAreaSeSubTipDoc.setHflex("5");

		/**
		 * Retencion
		 */
		Treecol treecolAg = new Treecol();
		treecolAg.setLabel("AG");
		treecolAg.setHflex("2");

		Treecol treecolAc = new Treecol();
		treecolAc.setLabel("AC");
		treecolAc.setHflex("2");

		/**
		 * Dispocision final
		 */
		Treecol treecolCt = new Treecol();
		treecolCt.setLabel("CT");
		treecolCt.setHflex("1");

		Treecol treecolE = new Treecol();
		treecolE.setLabel("E");
		treecolE.setHflex("1");

		Treecol treecolS = new Treecol();
		treecolS.setLabel("S");
		treecolS.setHflex("1");

		treecols.appendChild(treecolAreaSeSubTipDoc);
		treecols.appendChild(treecolAg);
		treecols.appendChild(treecolAc);
		treecols.appendChild(treecolCt);
		treecols.appendChild(treecolE);
		treecols.appendChild(treecolS);

		/**
		 * Opciones de disposición final
		 */
		if (opcionesDispFinal != null && opcionesDispFinal.size() > 0) {
			/**
			 * Le decimos al header de opciones de disposición final cuantas
			 * columnas tiene
			 */
			auxheaderOpcDispFinal.setColspan(opcionesDispFinal.size());

			for (TrdOpcionDisposicionFinal opc : opcionesDispFinal) {
				Treecol treecol = new Treecol();
				treecol.setLabel(opc.getNombre());
				treecol.setHflex("1");
				treecols.appendChild(treecol);
			}
		}

		/**
		 * Procedimientos
		 */
		Treecol treecolProc = new Treecol();
		treecolProc.setLabel("");
		treecolProc.setHflex("2");
		treecols.appendChild(treecolProc);

		/**
		 * Opciones
		 */
		Treecol treecolOpc = new Treecol();
		treecolOpc.setLabel("");
		treecolOpc.setHflex("2");
		treecols.appendChild(treecolOpc);

		/**
		 * Creamos el item padre Nombre empresa Version
		 */
		Treerow treerow = new Treerow();
		Treecell treecell = new Treecell();
		Treechildren treechildren = new Treechildren();

		Label labelPadre = new Label();
		labelPadre.setStyle("margin:5px;");
		labelPadre.setValue(nombreTrd);

		Image imagePadre = new Image("/recursos/icons/folder.png");

		/**
		 * Boton agregar area
		 */
		Toolbarbutton agregarArea = new Toolbarbutton("", "/recursos/icons/add.png");
		agregarArea.setStyle("margin:5px");
		agregarArea.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				if (treechildrenEmpresa.getItems() != null && treechildrenEmpresa.getItems().size() > 0) {
					for (Treeitem treeitem : treechildrenEmpresa.getItems()) {
						if (treeitem.getValue() instanceof Area) {
							Area area = (Area) treeitem.getValue();
							areas.add(area);
						}
					}
				}
				Map<String, Object> params = new HashMap<>();
				params.put("callback", "onCargarAreas");
				params.put("nombreTrd", nombreTrd);
				params.put("areas", areas);
				UiUtils.setModalWindow("/trd/configuracion/agregar_area.zul", self, params, false, true);
			}
		});

		treecell.appendChild(imagePadre);
		treecell.appendChild(labelPadre);
		treecell.appendChild(agregarArea);

		treerow.appendChild(treecell);
		treeitemEmpresa.appendChild(treerow);
		treechildren.appendChild(treeitemEmpresa);

		treeitemEmpresa.appendChild(treechildrenEmpresa);

		treeTrd.appendChild(treecols);
		treeTrd.appendChild(treechildren);
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * * * * * * * * EVENTOS AREAS * * * * * * * * * * * * * * * * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 */

	/**
	 * Evento que carga las areas en el tree
	 * 
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	public void onCargarAreas(Event event) {
		areas = (List<Area>) event.getData();
		if (areas != null && areas.size() > 0) {
			if (treechildrenEmpresa.getItems() != null && treechildrenEmpresa.getItems().size() > 0) {
				List<Treeitem> listEliminar = new ArrayList<>();

				for (Treeitem treeitem : treechildrenEmpresa.getItems()) {
					if (!(treeitem.getValue() instanceof Area)) {
						continue;
					}

					Area area = (Area) treeitem.getValue();

					/**
					 * Existe item del tree en la lista ?
					 */
					if (areas.contains(area)) {
						areas.remove(area);
					} else {
						listEliminar.add(treeitem);
					}
				}

				/**
				 * Agregamos al tree las areas que no se encontraron en el tree
				 */
				if (areas.size() > 0) {
					areas.forEach(x -> {
						agregarArea(x);
					});
				}

				/**
				 * Eliminamos las areas que no se encuentran seleccionadas
				 */
				if (listEliminar.size() > 0) {
					listEliminar.forEach(x -> {
						treechildrenEmpresa.removeChild(x);
					});
				}

			} else {
				for (Area area : areas) {
					agregarArea(area);
				}
			}

			areas.clear();

		} else {
			if (treechildrenEmpresa.getItems() != null) {

			}
		}
	}

	/**
	 * Agrega una Area al tree
	 * 
	 * @param area
	 */
	private void agregarArea(Area area) {
		Treecell treecell = new Treecell();
		Treerow treerow = new Treerow();
		Treeitem treeitem = new Treeitem();
		Treechildren treechildrenArea = new Treechildren();

		treeitem.setValue(area);

		Label labelArea = new Label();
		labelArea.setStyle("margin:5px;");
		labelArea.setValue(area.getNombre());

		Image imagePadre = new Image("/recursos/icons/folder.png");

		/**
		 * Boton eliminar area
		 */
		Toolbarbutton eliminarArea = new Toolbarbutton("", "/recursos/icons/delete.png");
		eliminarArea.setStyle("margin:5px");
		eliminarArea.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				Messagebox.show("¿ Está seguro de eliminar esta área ?", "Advertencia", Messagebox.YES | Messagebox.NO,
						Messagebox.QUESTION, new EventListener<Event>() {
							@Override
							public void onEvent(Event evnt) throws Exception {
								if (Messagebox.ON_YES.equals(evnt.getName())) {
									treechildrenEmpresa.removeChild(treeitem);
								}
							}
						});
			}
		});

		/**
		 * Boton agregar serie
		 */
		Toolbarbutton agregarSerie = new Toolbarbutton("", "/recursos/icons/add.png");
		agregarSerie.setStyle("margin:5px");
		agregarSerie.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				/**
				 * Llenamos la lista con las series en el nodo
				 */
				if (treechildrenArea.getItems() != null && treechildrenArea.getItems().size() > 0) {
					treechildrenArea.getItems().forEach(x -> {
						if (x.getValue() instanceof Serie) {
							Serie serie = (Serie) x.getValue();
							series.add(serie);
						}
					});
				}

				Map<String, Object> params = new HashMap<>();
				params.put("callback", "onCargarSeries");
				params.put("nombreTrd", nombreTrd);
				params.put("series", series);
				params.put("areaId", area.getAreaId());
				params.put("treechildrenArea", treechildrenArea);
				UiUtils.setModalWindow("/trd/configuracion/agregar_serie.zul", self, params, false, true);
			}
		});

		treecell.appendChild(imagePadre);
		treecell.appendChild(labelArea);
		treecell.appendChild(eliminarArea);
		treecell.appendChild(agregarSerie);

		treerow.appendChild(treecell);
		treeitem.appendChild(treerow);
		treeitem.appendChild(treechildrenArea);
		treechildrenEmpresa.appendChild(treeitem);
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * * * * * * * * EVENTOS SERIE * * * * * * * * *  * * * * * * * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 */

	/**
	 * Evento cargar las series en los nodos areas
	 * 
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	public void onCargarSeries(Event event) {
		Map<String, Object> params = (Map<String, Object>) event.getData();

		series = (List<Serie>) params.get("seriesSeleccionadas");
		Treechildren treechildrenArea = (Treechildren) params.get("treechildrenArea");

		if (series != null && series.size() > 0) {
			List<Treeitem> listEliminar = new ArrayList<>();
			if (treechildrenArea.getItems() != null && treechildrenArea.getItems().size() > 0) {

				for (Treeitem treeitem : treechildrenArea.getItems()) {
					if (!(treeitem.getValue() instanceof Serie)) {
						continue;
					}

					Serie serie = (Serie) treeitem.getValue();

					/**
					 * Existe item del tree en la lista ?
					 */
					if (series.contains(serie)) {
						series.remove(serie);
					} else {
						listEliminar.add(treeitem);
					}
				}

				/**
				 * Agregamos al tree las series que no se encontraron en el tree
				 */
				if (series.size() > 0) {
					series.forEach(x -> {
						agregarSerie(x, treechildrenArea);
					});
				}

				/**
				 * Eliminamos las series que no se encuentran seleccionadas
				 */
				if (listEliminar.size() > 0) {
					listEliminar.forEach(x -> {
						treechildrenArea.removeChild(x);
					});
				}

			} else {
				for (Serie serie : series) {
					agregarSerie(serie, treechildrenArea);
				}
			}

			series.clear();
		} else {
			if (treechildrenArea.getItems() != null) {

			}
		}
	}

	/**
	 * Agrega un item al nodo area
	 * 
	 * @param serie
	 * @param treechildrenArea
	 */
	private void agregarSerie(Serie serie, final Treechildren treechildrenArea) {
		Treecell treecell = new Treecell();
		Treerow treerow = new Treerow();
		Treeitem treeitem = new Treeitem();
		Treechildren treechildrenSerie = new Treechildren();

		treeitem.setValue(serie);

		Label labelSerie = new Label();
		labelSerie.setStyle("margin:5px;");
		labelSerie.setValue(serie.getNombre());

		Image imagePadre = new Image("/recursos/icons/folder.png");

		/**
		 * Boton eliminar serie
		 */
		Toolbarbutton eliminarSerie = new Toolbarbutton("", "/recursos/icons/delete.png");
		eliminarSerie.setStyle("margin:5px");
		eliminarSerie.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				Messagebox.show("¿ Está seguro de eliminar esta serie ?", "Advertencia", Messagebox.YES | Messagebox.NO,
						Messagebox.QUESTION, new EventListener<Event>() {
							@Override
							public void onEvent(Event evnt) throws Exception {
								if (Messagebox.ON_YES.equals(evnt.getName())) {
									treechildrenArea.removeChild(treeitem);
								}
							}
						});
			}
		});

		/**
		 * Boton agregar sub serie
		 */
		Toolbarbutton agregarSubserie = new Toolbarbutton("", "/recursos/icons/add.png");
		agregarSubserie.setStyle("margin:5px");
		agregarSubserie.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				if (treechildrenSerie.getItems() != null && treechildrenSerie.getItems().size() > 0) {
					for (Treeitem x : treechildrenSerie.getItems()) {
						if (x.getValue() instanceof Subserie) {
							Subserie subserie = (Subserie) x.getValue();
							subseries.add(subserie);
						}
					}
				}

				Map<String, Object> params = new HashMap<>();
				params.put("callback", "onCargarSubseries");
				params.put("nombreTrd", nombreTrd);
				params.put("subseries", subseries);
				params.put("serieId", serie.getSerieId());
				params.put("treechildrenSerie", treechildrenSerie);
				UiUtils.setModalWindow("/trd/configuracion/agregar_subserie.zul", self, params, false, true);
			}
		});

		treecell.appendChild(imagePadre);
		treecell.appendChild(labelSerie);
		treecell.appendChild(eliminarSerie);
		treecell.appendChild(agregarSubserie);

		treerow.appendChild(treecell);
		treeitem.appendChild(treerow);
		treeitem.appendChild(treechildrenSerie);
		treechildrenArea.appendChild(treeitem);
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * * * * * * * * EVENTOS SUBSERIES * * * * * * *  
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 */

	/**
	 * Evento cargar subseries por serie
	 * 
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	public void onCargarSubseries(Event event) {
		Map<String, Object> params = (Map<String, Object>) event.getData();

		subseries = (List<Subserie>) params.get("subseriesSeleccionadas");
		Treechildren treechildrenSerie = (Treechildren) params.get("treechildrenSerie");

		if (subseries != null && subseries.size() > 0) {
			List<Treeitem> listEliminar = new ArrayList<>();
			if (treechildrenSerie.getItems() != null && treechildrenSerie.getItems().size() > 0) {

				for (Treeitem treeitem : treechildrenSerie.getItems()) {
					if (!(treeitem.getValue() instanceof Subserie)) {
						continue;
					}

					Subserie subserie = (Subserie) treeitem.getValue();

					/**
					 * Existe item del tree en la lista ?
					 */
					if (subseries.contains(subserie)) {
						subseries.remove(subserie);
					} else {
						listEliminar.add(treeitem);
					}
				}

				/**
				 * Agregamos al tree las series que no se encontraron en el tree
				 */
				if (subseries.size() > 0) {
					subseries.forEach(x -> {
						agregarSubserie(x, treechildrenSerie);
					});
				}

				/**
				 * Eliminamos las series que no se encuentran seleccionadas
				 */
				if (listEliminar.size() > 0) {
					listEliminar.forEach(x -> {
						treechildrenSerie.removeChild(x);
					});
				}

			} else {
				for (Subserie subserie : subseries) {
					agregarSubserie(subserie, treechildrenSerie);
				}
			}

			subseries.clear();
		} else {
			if (treechildrenSerie.getItems() != null) {

			}
		}
	}

	/**
	 * Agrega un item subserie al nodo serie
	 * 
	 * @param serie
	 * @param treechildrenSerie
	 */
	private void agregarSubserie(Subserie serie, final Treechildren treechildrenSerie) {
		Treecell treecell = new Treecell();
		Treerow treerow = new Treerow();
		Treeitem treeitem = new Treeitem();
		Treechildren treechildrenSubserie = new Treechildren();

		treeitem.setValue(serie);

		Label labelSerie = new Label();
		labelSerie.setStyle("margin:5px;");
		labelSerie.setValue(serie.getNombre());

		Image imagePadre = new Image("/recursos/icons/folder.png");

		/**
		 * Boton eliminar subserie
		 */
		Toolbarbutton eliminarSubserie = new Toolbarbutton("", "/recursos/icons/delete.png");
		eliminarSubserie.setStyle("margin:5px");
		eliminarSubserie.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				Messagebox.show("¿ Está seguro de eliminar esta subserie ?", "Advertencia",
						Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
							@Override
							public void onEvent(Event evnt) throws Exception {
								if (Messagebox.ON_YES.equals(evnt.getName())) {
									treechildrenSerie.removeChild(treeitem);
								}
							}
						});
			}
		});

		/**
		 * Boton agregar tipo documental
		 */
		Toolbarbutton agregarTipoDoc = new Toolbarbutton("", "/recursos/icons/add.png");
		agregarTipoDoc.setStyle("margin:5px");
		agregarTipoDoc.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				if (treechildrenSubserie.getItems() != null && treechildrenSubserie.getItems().size() > 0) {
					for (Treeitem x : treechildrenSubserie.getItems()) {
						if (x.getValue() instanceof TipoDocumental) {
							TipoDocumental tipoDocumental = (TipoDocumental) x.getValue();
							tiposDocumentales.add(tipoDocumental);
						}
					}
				}

				Map<String, Object> params = new HashMap<>();
				params.put("callback", "onCargarTiposDocumentales");
				params.put("nombreTrd", nombreTrd);
				params.put("tiposDocumentales", tiposDocumentales);
				params.put("subSerieId", serie.getSubserieId());
				params.put("treechildrenSubSerie", treechildrenSubserie);
				UiUtils.setModalWindow("/trd/configuracion/agregar_tipo_documentales.zul", self, params, false, true);
			}
		});

		/**
		 * Eventos del nodo
		 */
		treecell.appendChild(imagePadre);
		treecell.appendChild(labelSerie);
		treecell.appendChild(eliminarSubserie);
		treecell.appendChild(agregarTipoDoc);

		/**
		 * * * * * * * * * * * * * * * RETENCION * * * * * * * * * * * * * *
		 */

		/**
		 * Celda AG
		 */
		Treecell treecellAg = new Treecell();

		Textbox textboxAg = new Textbox();
		textboxAg.setStyle("width: 40px; height:20px; padding:5px;");
		treecellAg.appendChild(textboxAg);

		Label labelTxtAg = new Label();
		labelTxtAg.setStyle("width: 40px; height:20px; padding:5px;");
		treecellAg.appendChild(labelTxtAg);

		Combobox comboboxAg = new Combobox();
		comboboxAg.setReadonly(true);
		comboboxAg.setStyle("width: 60px; height:20px; padding:5px;");
		Utils.llenarComboboxTrd(comboboxAg);
		treecellAg.appendChild(comboboxAg);

		Label labelCmbAg = new Label();
		labelCmbAg.setStyle("width: 40px; height:20px; padding:5px;");
		treecellAg.appendChild(labelCmbAg);

		/**
		 * Celda AC
		 */
		Treecell treecellAc = new Treecell();

		Textbox textboxAc = new Textbox();
		textboxAc.setStyle("width: 40px; height:20px; padding:5px;");
		treecellAc.appendChild(textboxAc);

		Label labelTxtAc = new Label();
		labelTxtAc.setStyle("width: 40px; height:20px; padding:5px;");
		treecellAc.appendChild(labelTxtAc);

		Combobox comboboxAc = new Combobox();
		comboboxAc.setReadonly(true);
		comboboxAc.setStyle("width: 60px; height:20px; padding:5px;");
		Utils.llenarComboboxTrd(comboboxAc);
		treecellAc.appendChild(comboboxAc);

		Label labelCmbAc = new Label();
		labelCmbAc.setStyle("width: 40px; height:20px; padding:5px;");
		treecellAc.appendChild(labelCmbAc);

		/**
		 * * * * * * * * * * * * * * * DISPOSICION FINAL * * * * * * * * * * * *
		 * * *
		 */

		Radio radioCt = new Radio();
		radioCt.setChecked(true);
		Radio radioE = new Radio();
		Radio radioS = new Radio();

		/**
		 * Celda CT
		 */
		Treecell treecellCt = new Treecell();

		radioCt.setStyle("padding:5px;");
		treecellCt.appendChild(radioCt);
		radioCt.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				if (radioCt.isChecked()) {
					radioE.setChecked(false);
					radioS.setChecked(false);
				}
			};
		});

		Label labelCt = new Label();
		labelCt.setValue("X");
		labelCt.setStyle("padding:5px;");
		treecellCt.appendChild(labelCt);

		/**
		 * Celda E
		 */
		Treecell treecellE = new Treecell();

		radioE.setStyle("padding:5px;");
		treecellE.appendChild(radioE);
		radioE.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				if (radioE.isChecked()) {
					radioCt.setChecked(false);
					radioS.setChecked(false);
				}
			};
		});

		Label labelE = new Label();
		labelE.setStyle("padding:5px;");
		treecellE.appendChild(labelE);

		/**
		 * Celda S
		 */
		Treecell treecellS = new Treecell();

		radioS.setStyle("padding:5px;");
		treecellS.appendChild(radioS);
		radioS.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				if (radioS.isChecked()) {
					radioE.setChecked(false);
					radioCt.setChecked(false);
				}
			};
		});

		Label labelS = new Label();
		labelS.setStyle("padding:5px;");
		treecellS.appendChild(labelS);

		/**
		 * Antes de agregar las opciones de disposición final agregamos las
		 * celdas hasta dispocion final
		 */
		treerow.appendChild(treecell);
		treerow.appendChild(treecellAg);
		treerow.appendChild(treecellAc);
		treerow.appendChild(treecellCt);
		treerow.appendChild(treecellE);
		treerow.appendChild(treecellS);

		/**
		 * * * * * * * * * * * * * * * * * * * * * OPCIONES ADC DE DISPOSICION
		 * FINAL * * * * * * * * * * * * * * * * * * * *
		 */
		if (opcionesDispFinal != null && opcionesDispFinal.size() > 0) {
			for (TrdOpcionDisposicionFinal opc : opcionesDispFinal) {
				Treecell treecellOpc = new Treecell();

				Checkbox checkbox = new Checkbox();
				checkbox.setVisible(false);
				checkbox.setValue(opc);
				checkbox.setStyle("padding:5px;");
				treecellOpc.appendChild(checkbox);

				Label label = new Label();
				label.setStyle("padding:5px;");
				label.setVisible(true);
				treecellOpc.appendChild(label);

				treerow.appendChild(treecellOpc);
			}
		}

		/**
		 * * * * * * * * * * * * * * * PROCEDIMIENTO * * * * * * * * * * * * * *
		 */

		Treecell treecellProc = new Treecell();
		Textbox textboxProc = new Textbox();
		textboxProc.setStyle("width: 100px; height: 30px; padding:5px;");
		textboxProc.setMaxlength(3000);
		textboxProc.setRows(3);
		treecellProc.appendChild(textboxProc);
		Label labelProc = new Label();
		treecellProc.appendChild(labelProc);

		/**
		 * Inicializar componentes fila
		 */
		textboxAg.setVisible(false);
		textboxAg.setText("0");
		comboboxAg.setVisible(false);
		labelTxtAg.setValue("0");
		labelTxtAg.setVisible(true);
		// labelTxtAg.setId(ID_LABEL_AG);
		labelCmbAg.setValue("A");
		labelCmbAg.setVisible(true);
		// labelCmbAg.setId(ID_COMBOBOX_AG);

		textboxAc.setVisible(false);
		textboxAc.setText("0");
		comboboxAc.setVisible(false);
		labelTxtAc.setVisible(true);
		labelTxtAc.setValue("0");
		// labelTxtAc.setId(ID_LABEL_AC);
		labelCmbAc.setVisible(true);
		labelCmbAc.setValue("A");
		// labelCmbAc.setId(ID_COMBOBOX_AC);

		radioCt.setVisible(false);
		radioE.setVisible(false);
		radioS.setVisible(false);
		labelCt.setVisible(true);
		// labelCt.setId(ID_LABEL_CT);
		labelE.setVisible(true);
		// labelE.setId(ID_LABEL_E);
		labelS.setVisible(true);
		// labelS.setId(ID_LABEL_S);

		textboxProc.setVisible(false);
		labelProc.setVisible(true);
		// textboxProc.setId(ID_TEXTBOXT_PROCEDIMIENTO);

		/**
		 * * * * * * * * * * * * * * * OPCIONES * * * * * * * * * * * * * *
		 */
		Treecell treecellOpc = new Treecell();
		Toolbarbutton buttonCancelar = new Toolbarbutton("", "/recursos/icons/cancel.png");
		Toolbarbutton buttonEditar = new Toolbarbutton("", "/recursos/icons/pencil.png");
		Toolbarbutton buttonAceptar = new Toolbarbutton("", "/recursos/icons/accept.png");

		buttonAceptar.setVisible(false);
		buttonCancelar.setVisible(false);

		treecellOpc.appendChild(buttonEditar);
		treecellOpc.appendChild(buttonAceptar);
		treecellOpc.appendChild(buttonCancelar);

		/**
		 * Evento editar
		 */
		buttonEditar.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				buttonEditar.setVisible(false);
				buttonAceptar.setVisible(true);
				buttonCancelar.setVisible(true);

				textboxAg.setVisible(true);
				textboxAg.setValue(labelTxtAg.getValue());
				labelTxtAg.setVisible(false);

				comboboxAg.setVisible(true);
				labelCmbAg.setVisible(false);

				textboxAc.setVisible(true);
				textboxAc.setValue(labelTxtAc.getValue());
				labelTxtAc.setVisible(false);

				comboboxAc.setVisible(true);
				labelCmbAc.setVisible(false);

				radioCt.setVisible(true);
				labelCt.setVisible(false);

				radioE.setVisible(true);
				labelE.setVisible(false);

				radioS.setVisible(true);
				labelS.setVisible(false);

				textboxProc.setVisible(true);
				labelProc.setVisible(false);

				treerow.getChildren().forEach(x -> {
					if (x instanceof Treecell) {
						Treecell treecell = (Treecell) x;
						treecell.getChildren().forEach(y -> {
							if (y instanceof Checkbox
									&& ((Checkbox) y).getValue() instanceof TrdOpcionDisposicionFinal) {
								Checkbox checkbox = (Checkbox) y;
								checkbox.setVisible(true);

								if (treecell.getLastChild() instanceof Label) {
									Label label = (Label) treecell.getLastChild();
									label.setVisible(false);
								}
							}
						});
					}
				});
			}
		});

		/**
		 * Evento aceptar
		 */
		buttonAceptar.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				buttonEditar.setVisible(true);
				buttonAceptar.setVisible(false);
				buttonCancelar.setVisible(false);

				textboxAg.setVisible(false);
				labelTxtAg.setValue(textboxAg.getValue());
				labelTxtAg.setVisible(true);

				comboboxAg.setVisible(false);
				labelCmbAg.setVisible(true);
				labelCmbAg.setValue(comboboxAg.getValue());

				textboxAc.setVisible(false);
				labelTxtAc.setValue(textboxAc.getValue());
				labelTxtAc.setVisible(true);

				comboboxAc.setVisible(false);
				labelCmbAc.setVisible(true);
				labelCmbAc.setValue(comboboxAc.getValue());

				radioCt.setVisible(false);
				labelCt.setVisible(true);
				if (radioCt.isChecked()) {
					labelCt.setValue("X");
				} else {
					labelCt.setValue("");
				}

				radioE.setVisible(false);
				labelE.setVisible(true);
				if (radioE.isChecked()) {
					labelE.setValue("X");
				} else {
					labelE.setValue("");
				}

				radioS.setVisible(false);
				labelS.setVisible(true);
				if (radioS.isChecked()) {
					labelS.setValue("X");
				} else {
					labelS.setValue("");
				}

				textboxProc.setVisible(false);
				labelProc.setVisible(true);
				labelProc.setValue(textboxProc.getValue());
				labelProc.setTooltip(textboxProc.getValue());

				treerow.getChildren().forEach(x -> {
					if (x instanceof Treecell) {
						Treecell treecell = (Treecell) x;
						treecell.getChildren().forEach(y -> {
							if (y instanceof Checkbox
									&& ((Checkbox) y).getValue() instanceof TrdOpcionDisposicionFinal) {
								Checkbox checkbox = (Checkbox) y;
								checkbox.setVisible(false);

								if (treecell.getLastChild() instanceof Label) {
									Label label = (Label) treecell.getLastChild();
									label.setVisible(true);

									if (checkbox.isChecked()) {
										label.setValue("X");
									} else {
										label.setValue("");
									}
								}
							}
						});
					}
				});
			}
		});

		/**
		 * Evento cancelar
		 */
		buttonCancelar.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				buttonEditar.setVisible(true);
				buttonAceptar.setVisible(false);
				buttonCancelar.setVisible(false);

				textboxAg.setVisible(false);
				labelTxtAg.setVisible(true);

				comboboxAg.setVisible(false);
				labelCmbAg.setVisible(true);
				labelCmbAg.setValue(labelCmbAg.getValue());

				textboxAc.setVisible(false);
				labelTxtAc.setVisible(true);

				comboboxAc.setVisible(false);
				labelCmbAc.setVisible(true);
				labelCmbAc.setValue(comboboxAc.getValue());

				radioCt.setVisible(false);
				radioE.setVisible(false);
				radioS.setVisible(false);

				labelCt.setVisible(true);
				labelE.setVisible(true);
				labelS.setVisible(true);

				textboxProc.setVisible(false);
				labelProc.setVisible(true);

				treerow.getChildren().forEach(x -> {
					if (x instanceof Treecell) {
						Treecell treecell = (Treecell) x;
						treecell.getChildren().forEach(y -> {
							if (y instanceof Checkbox
									&& ((Checkbox) y).getValue() instanceof TrdOpcionDisposicionFinal) {
								Checkbox checkbox = (Checkbox) y;
								checkbox.setVisible(false);

								if (treecell.getLastChild() instanceof Label) {
									Label label = (Label) treecell.getLastChild();
									label.setVisible(true);
								}
							}
						});
					}
				});
			}
		});

		/**
		 * * * * * * * * * * * * * * * AGREGAMOS LAS CELDAS A LA FILA DE
		 * PROCEDIMIENTOS Y OPCIONES * * * * * * * * * * * * * *
		 */
		treerow.appendChild(treecellProc);
		treerow.appendChild(treecellOpc);

		treeitem.appendChild(treerow);
		treeitem.appendChild(treechildrenSubserie);
		treechildrenSerie.appendChild(treeitem);
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * * * * * * EVENTOS TIPOS DOCUMENTALES * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 */

	/**
	 * Carga los tipos documentales en el nodo de sub serie
	 * 
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	public void onCargarTiposDocumentales(Event event) {
		Map<String, Object> params = (Map<String, Object>) event.getData();

		tiposDocumentales = (List<TipoDocumental>) params.get("tiposDocSeleccionados");
		Treechildren treechildrenSubserie = (Treechildren) params.get("treechildrenSubSerie");

		if (tiposDocumentales != null && tiposDocumentales.size() > 0) {
			List<Treeitem> listEliminar = new ArrayList<>();
			if (treechildrenSubserie.getItems() != null && treechildrenSubserie.getItems().size() > 0) {

				for (Treeitem treeitem : treechildrenSubserie.getItems()) {
					if (!(treeitem.getValue() instanceof TipoDocumental)) {
						continue;
					}

					TipoDocumental tipoDocumental = (TipoDocumental) treeitem.getValue();

					/**
					 * Existe item del tree en la lista ?
					 */
					if (tiposDocumentales.contains(tipoDocumental)) {
						tiposDocumentales.remove(tipoDocumental);
					} else {
						listEliminar.add(treeitem);
					}
				}

				/**
				 * Agregamos al tree las series que no se encontraron en el tree
				 */
				if (tiposDocumentales.size() > 0) {
					tiposDocumentales.forEach(x -> {
						agregarTipoDocumental(x, treechildrenSubserie);
					});
				}

				/**
				 * Eliminamos las series que no se encuentran seleccionadas
				 */
				if (listEliminar.size() > 0) {
					listEliminar.forEach(x -> {
						treechildrenSubserie.removeChild(x);
					});
				}

			} else {
				for (TipoDocumental tipoDocumental : tiposDocumentales) {
					agregarTipoDocumental(tipoDocumental, treechildrenSubserie);
				}
			}

			tiposDocumentales.clear();
		} else {
			if (treechildrenSubserie.getItems() != null) {

			}
		}
	}

	/**
	 * Agrega un tipo documental a un item de sub serie
	 * 
	 * @param tipoDocumental
	 * @param treechildrenSubserie
	 */
	private void agregarTipoDocumental(TipoDocumental tipoDocumental, final Treechildren treechildrenSubserie) {
		Treecell treecell = new Treecell();
		Treerow treerow = new Treerow();
		Treeitem treeitem = new Treeitem();
		Treechildren treechildrenTipoDocumental = new Treechildren();

		treeitem.setValue(tipoDocumental);

		Label labelSerie = new Label();
		labelSerie.setStyle("margin:5px;");
		labelSerie.setValue(tipoDocumental.getNombre());

		Image imagePadre = new Image("/recursos/icons/folder.png");

		/**
		 * Boton eliminar tipoDocumental
		 */
		Toolbarbutton eliminarTipoDocumental = new Toolbarbutton("", "/recursos/icons/delete.png");
		eliminarTipoDocumental.setStyle("margin:5px");
		eliminarTipoDocumental.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				Messagebox.show("¿ Está seguro de eliminar este Tipo Documental ?", "Advertencia",
						Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
							@Override
							public void onEvent(Event evnt) throws Exception {
								if (Messagebox.ON_YES.equals(evnt.getName())) {
									treechildrenSubserie.removeChild(treeitem);
								}
							}
						});
			}
		});

		/**
		 * Boton agregar
		 */
		Toolbarbutton toolbarbutton = new Toolbarbutton("", "/recursos/icons/add.png");
		toolbarbutton.setStyle("margin:5px");
		toolbarbutton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {

			}
		});

		treecell.appendChild(imagePadre);
		treecell.appendChild(labelSerie);
		treecell.appendChild(eliminarTipoDocumental);
		treecell.appendChild(toolbarbutton);

		treerow.appendChild(treecell);
		treeitem.appendChild(treerow);
		treeitem.appendChild(treechildrenTipoDocumental);
		treechildrenSubserie.appendChild(treeitem);
	}

	
	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * * * * * * * GENERAR TRD * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 */
	
	/**
	 * Evento guardar parcial
	 * @param event
	 */
	public void onClick$btnGuardarParcial(Event event) {
		if (treechildrenEmpresa != null && treechildrenEmpresa.getItems() != null && treechildrenEmpresa.getItems().size() > 0) {
			List<Area> areas = new ArrayList<>();

			for (Treeitem treeitem : treechildrenEmpresa.getItems()) {
				/**
				 * Tree item es area
				 */
				if (!(treeitem.getValue() instanceof Area)) {
					continue;
				}
				
				Area area = (Area) treeitem.getValue();
				System.out.println(area.getNombre());
				
				List<Serie> series = new ArrayList<>();

				/**
				 * Recorremos los items del area para buscar las series
				 */
				for (Component componentArea : treeitem.getChildren()) {

					if (!(componentArea instanceof Treechildren)) {
						continue;
					}

					Treechildren childrenArea = (Treechildren) componentArea;

					/**
					 * Recorremos los treechildren del area
					 */
					for (Component componentChilArea : childrenArea.getChildren()) {

						if (!(componentChilArea instanceof Treeitem)) {
							continue;
						}

						Treeitem treeitemSerie = (Treeitem) componentChilArea;

						if (!(treeitemSerie.getValue() instanceof Serie)) {
							continue;
						}

						/**
						 * Obtenemos la serie
						 */
						Serie serie = (Serie) treeitemSerie.getValue();
						System.out.println(serie.getNombre());
						
						List<Subserie> subseries = new ArrayList<>();

						/**
						 * Recorremos los items de la serie para buscar las
						 * subseries
						 */
						for (Component componentSerie : treeitemSerie.getChildren()) {

							if (!(componentSerie instanceof Treechildren)) {
								continue;
							}

							Treechildren treechildrenSerie = (Treechildren) componentSerie;

							/**
							 * Recorremos los children de la serie para
							 * obtener la subserie
							 */
							for (Component componentChilSerie : treechildrenSerie.getChildren()) {

								if (!(componentChilSerie instanceof Treeitem)) {
									continue;
								}

								Treeitem treeitemSubserie = (Treeitem) componentChilSerie;

								if (!(treeitemSubserie.getValue() instanceof Subserie)) {
									continue;
								}

								/**
								 * Obtenemos la subserie
								 */
								Subserie subserie = obtenerSubserie(treeitemSubserie);
								
								/**
								 * Validamos los tiempos
								 */
								if (subserie.getAgValor() == 0 && subserie.getAcValor() == 0) {
									Messagebox.show(Labels.getLabel("retencion.config.mensaje.campoObligatorio"),
											Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.INFORMATION);
									treeTrd.clearSelection();
									treeTrd.setSelectedItem(treeitemSubserie);
									treeitemSerie.setOpen(true);
									return;
								}
								
								/**
								 * Validamos Procedimiento
								 */
								if (subserie.getProcedimiento() == null || subserie.getProcedimiento().trim().length() == 0) {
									Messagebox.show("Campo procedimiento es obligatorio. Por favor revisar.",
											Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.INFORMATION);
									treeTrd.clearSelection();
									treeTrd.setSelectedItem(treeitemSubserie);
									treeitemSerie.setOpen(true);
									return;
								}
								
								System.out.println(subserie.getNombre());

								List<TipoDocumental> tipoDocumentals = new ArrayList<>();

								/**
								 * Recorremos los items de la subserie para
								 * obtener los tipos documental
								 */
								for (Component componentSubserie : treeitemSubserie.getChildren()) {

									if (!(componentSubserie instanceof Treechildren)) {
										continue;
									}

									Treechildren treechildrenSubserie = (Treechildren) componentSubserie;

									for (Component componentChilSubserie : treechildrenSubserie.getChildren()) {

										if (!(componentChilSubserie instanceof Treeitem)) {
											continue;
										}

										Treeitem treeitemTipoDocumental = (Treeitem) componentChilSubserie;

										if (!(treeitemTipoDocumental.getValue() instanceof TipoDocumental)) {
											continue;
										}

										TipoDocumental tipoDocumental = (TipoDocumental) treeitemTipoDocumental.getValue();
										tipoDocumentals.add(tipoDocumental);
										System.out.println(tipoDocumental.getNombre());
									}

								}
								subserie.setTipoDocumentalList(tipoDocumentals);
								subseries.add(subserie);
							}
						}
						serie.setSubseries(subseries);
						series.add(serie);
					}
				}
				
				area.setSeries(series);
				areas.add(area);
			}

			TrdTablaRetencion trdTablaRetencion = new TrdTablaRetencion();
			trdTablaRetencion.setCodigo("");
			trdTablaRetencion.setNombre(nombreTrd);
			trdTablaRetencion.setVigente(true);
			Empresa empresa = new Empresa();
			empresa.setEmpresaId(idEmpresaSesion);
			trdTablaRetencion.setEmpresaId(empresa);
			trdTablaRetencion.setUsuarioModifica(idUsuarioSesion);

			TrdTablaRetencionGeneralDto generalDto = new TrdTablaRetencionGeneralDto();
			generalDto.setTrdTablaRetencion(trdTablaRetencion);
			generalDto.setAreas(areas);
			
			String preguntaConfirmacion = Labels.getLabel("retencion.config.mensaje.confirmarGenerarTrd");
	        Messagebox.show(String.format(preguntaConfirmacion, nombreTrd), Labels.getLabel("commons.titulo.confirmacion"), 
	        		Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
	            @Override
	            public void onEvent(Event evnt) throws Exception {
	                if (Messagebox.ON_YES.equals(evnt.getName())) {
						try {
							
							TrdTablaRetencion trdTablaRetencion = tablaRetencionService.guardarParcial(SessionUtil.getAuthToken(Sessions.getCurrent()), generalDto);
							//TODO: pintal el arbol
							
						} catch (BadRequestException e) {
							Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"), 
									Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
							// self.detach();
						} catch (ServerErrorException ex) {
							Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
									Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
						} catch (Exception e) {
							Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
									Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
							// self.detach();
						}
	                }
	            }
	        });
		} else {
			Messagebox.show(Labels.getLabel("retencion.config.mensaje.trdSinDatos"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.INFORMATION);
		}
	}

	/**
	 * Evento generar TRD
	 * 
	 * @param event
	 */
	public void onClick$btnGenerarTrd(Event event) {
		if (treechildrenEmpresa != null && treechildrenEmpresa.getItems() != null && treechildrenEmpresa.getItems().size() > 0) {
			List<Area> areas = new ArrayList<>();

			for (Treeitem treeitem : treechildrenEmpresa.getItems()) {
				/**
				 * Tree item es area
				 */
				if (!(treeitem.getValue() instanceof Area)) {
					continue;
				}
				
				Area area = (Area) treeitem.getValue();
				System.out.println(area.getNombre());
				
				List<Serie> series = new ArrayList<>();

				/**
				 * Recorremos los items del area para buscar las series
				 */
				for (Component componentArea : treeitem.getChildren()) {

					if (!(componentArea instanceof Treechildren)) {
						continue;
					}

					Treechildren childrenArea = (Treechildren) componentArea;

					/**
					 * Recorremos los treechildren del area
					 */
					for (Component componentChilArea : childrenArea.getChildren()) {

						if (!(componentChilArea instanceof Treeitem)) {
							continue;
						}

						Treeitem treeitemSerie = (Treeitem) componentChilArea;

						if (!(treeitemSerie.getValue() instanceof Serie)) {
							continue;
						}

						/**
						 * Obtenemos la serie
						 */
						Serie serie = (Serie) treeitemSerie.getValue();
						System.out.println(serie.getNombre());
						
						List<Subserie> subseries = new ArrayList<>();

						/**
						 * Recorremos los items de la serie para buscar las
						 * subseries
						 */
						for (Component componentSerie : treeitemSerie.getChildren()) {

							if (!(componentSerie instanceof Treechildren)) {
								continue;
							}

							Treechildren treechildrenSerie = (Treechildren) componentSerie;

							/**
							 * Recorremos los children de la serie para
							 * obtener la subserie
							 */
							for (Component componentChilSerie : treechildrenSerie.getChildren()) {

								if (!(componentChilSerie instanceof Treeitem)) {
									continue;
								}

								Treeitem treeitemSubserie = (Treeitem) componentChilSerie;

								if (!(treeitemSubserie.getValue() instanceof Subserie)) {
									continue;
								}

								/**
								 * Obtenemos la subserie
								 */
								Subserie subserie = obtenerSubserie(treeitemSubserie);
								
								/**
								 * Validamos los tiempos
								 */
								if (subserie.getAgValor() == 0 && subserie.getAcValor() == 0) {
									Messagebox.show(Labels.getLabel("retencion.config.mensaje.campoObligatorio"),
											Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.INFORMATION);
									treeTrd.clearSelection();
									treeTrd.setSelectedItem(treeitemSubserie);
									treeitemSerie.setOpen(true);
									return;
								}
								
								/**
								 * Validamos Procedimiento
								 */
								if (subserie.getProcedimiento() == null || subserie.getProcedimiento().trim().length() == 0) {
									Messagebox.show("Campo procedimiento es obligatorio. Por favor revisar.",
											Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.INFORMATION);
									treeTrd.clearSelection();
									treeTrd.setSelectedItem(treeitemSubserie);
									treeitemSerie.setOpen(true);
									return;
								}
								
								System.out.println(subserie.getNombre());

								List<TipoDocumental> tipoDocumentals = new ArrayList<>();

								/**
								 * Recorremos los items de la subserie para
								 * obtener los tipos documental
								 */
								for (Component componentSubserie : treeitemSubserie.getChildren()) {

									if (!(componentSubserie instanceof Treechildren)) {
										continue;
									}

									Treechildren treechildrenSubserie = (Treechildren) componentSubserie;

									for (Component componentChilSubserie : treechildrenSubserie.getChildren()) {

										if (!(componentChilSubserie instanceof Treeitem)) {
											continue;
										}

										Treeitem treeitemTipoDocumental = (Treeitem) componentChilSubserie;

										if (!(treeitemTipoDocumental.getValue() instanceof TipoDocumental)) {
											continue;
										}

										TipoDocumental tipoDocumental = (TipoDocumental) treeitemTipoDocumental.getValue();
										tipoDocumentals.add(tipoDocumental);
										System.out.println(tipoDocumental.getNombre());
									}

								}
								subserie.setTipoDocumentalList(tipoDocumentals);
								subseries.add(subserie);
							}
						}
						serie.setSubseries(subseries);
						series.add(serie);
					}
				}
				
				area.setSeries(series);
				areas.add(area);
			}

			TrdTablaRetencion trdTablaRetencion = new TrdTablaRetencion();
			trdTablaRetencion.setCodigo("");
			trdTablaRetencion.setNombre(nombreTrd);
			trdTablaRetencion.setVigente(true);
			Empresa empresa = new Empresa();
			empresa.setEmpresaId(idEmpresaSesion);
			trdTablaRetencion.setEmpresaId(empresa);
			trdTablaRetencion.setUsuarioModifica(idUsuarioSesion);

			TrdTablaRetencionGeneralDto generalDto = new TrdTablaRetencionGeneralDto();
			generalDto.setTrdTablaRetencion(trdTablaRetencion);
			generalDto.setAreas(areas);
			
		
				String preguntaConfirmacion = Labels.getLabel("retencion.config.mensaje.confirmarGenerarTrd");
		        Messagebox.show(String.format(preguntaConfirmacion, nombreTrd), Labels.getLabel("commons.titulo.confirmacion"), 
		        		Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
		            @Override
		            public void onEvent(Event evnt) throws Exception {
		                if (Messagebox.ON_YES.equals(evnt.getName())) {
							try {
								
								tablaRetencionService.generarTrd(SessionUtil.getAuthToken(Sessions.getCurrent()), generalDto);
								self.detach();
								
							} catch (BadRequestException e) {
								Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"), Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
								// self.detach();
							} catch (ServerErrorException ex) {
								if (ex.getResponse() != null && ex.getResponse().getHeaders() != null) {
									List<Object> myResponses = ex.getResponse().getHeaders()
											.get(Utils.LLAVE_CODIGOS_ERROR_SERVER);
									if (myResponses != null && myResponses.size() > 0) {
										if (myResponses.contains(Utils.CODIGO_ERROR_ILLEGAL_ARGUMENT_EXCEPTION)) {
											Messagebox.show(Labels.getLabel("retencion.config.mensaje.trdIgual"),
													Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
										} else {
											Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
													Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
										}
									}
								}
							} catch (Exception e) {
								Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
										Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
								// self.detach();
							}
		                }
		            }
		        });

		} else {
			Messagebox.show(Labels.getLabel("retencion.config.mensaje.trdSinDatos"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.INFORMATION);
		}
	}
	
	/**
	 * Utilidad para obtener el objeto subserie en el recorrido del arbol TRD
	 * @param treeitemSubserie Treeitem de la subserie
	 * @return Objeto subserie
	 */
	private Subserie obtenerSubserie(Treeitem treeitemSubserie) {
		Subserie subserie = (Subserie) treeitemSubserie.getValue();
		
		/**
		 * Recorremos los las celdas para obtener
		 * los valores de retencion, dispisición
		 * final, procedmiento...
		 */
		for (int contCelda = 0; contCelda < treeitemSubserie.getTreerow().getChildren().size(); contCelda++) {
			Component children = treeitemSubserie.getTreerow().getChildren().get(contCelda);

			if (!(children instanceof Treecell)) {
				continue;
			}

			Treecell treecell = (Treecell) children;

			if (contCelda == NUMERO_CELDA_AG) {
				int temp = 1;
				for (Component childrenTreeCell : treecell.getChildren()) {
					System.out.println(childrenTreeCell);

					if (childrenTreeCell instanceof Label) {
						if (temp == 1) {
							Label label = (Label) childrenTreeCell;
							int valor = label == null || label.getValue() == null || label.getValue().trim().length() == 0 ? 0 : Integer.parseInt(label.getValue());
							subserie.setAgValor(valor);
						} else {
							subserie.setAgUnidad(((Label) childrenTreeCell).getValue().charAt(0));
						}
						temp++;
					}
				}
				continue;
			}

			if (contCelda == NUMERO_CELDA_AC) {
				int temp = 1;
				for (Component childrenTreeCell : treecell.getChildren()) {
					System.out.println(childrenTreeCell);

					if (childrenTreeCell instanceof Label) {
						if (temp == 1) {
							Label label = (Label) childrenTreeCell;
							int valor = label == null || label.getValue() == null || label.getValue().trim().length() == 0 ? 0 : Integer.parseInt(label.getValue());
							subserie.setAcValor(valor);
						} else {
							subserie.setAcUnidad(((Label) childrenTreeCell).getValue().charAt(0));
						}
						temp++;
					}
				}
				continue;
			}

			if (contCelda == NUMERO_CELDA_CT) {
				for (Component childrenTreeCell : treecell.getChildren()) {
					System.out.println(childrenTreeCell);

					if (!(childrenTreeCell instanceof Radio)) {
						continue;
					}

					if (!((Radio) childrenTreeCell).isChecked()) {
						break;
					}

					subserie.setTipoDispFinal(DISPOSICION_FINAL_CONSERV_TOTAL);
					break;
				}
				continue;
			}

			if (contCelda == NUMERO_CELDA_E) {
				for (Component childrenTreeCell : treecell.getChildren()) {
					System.out.println(childrenTreeCell);

					if (!(childrenTreeCell instanceof Radio)) {
						continue;
					}

					if (!((Radio) childrenTreeCell).isChecked()) {
						break;
					}

					subserie.setTipoDispFinal(DISPOSICION_FINAL_ELIMINACION);
					break;
				}
				continue;
			}

			if (contCelda == NUMERO_CELDA_S) {
				for (Component childrenTreeCell : treecell.getChildren()) {
					System.out.println(childrenTreeCell);

					if (!(childrenTreeCell instanceof Radio)) {
						continue;
					}

					if (!((Radio) childrenTreeCell).isChecked()) {
						break;
					}

					subserie.setTipoDispFinal(DISPOSICION_FINAL_SELECCION);
					break;
				}
				continue;
			}

			if (contCelda <= NUMERO_CELDA_S) {
				continue;
			}

			/**
			 * Buscamos los valores de disposición
			 * final si existen
			 */
			if (opcionesDispFinal != null && opcionesDispFinal.size() > 0) {

				/**
				 * La celda de procedimientos es la
				 * la suma de la celda selección más
				 * número de opciones de disposición
				 * final
				 */
				if (contCelda == (NUMERO_CELDA_S + opcionesDispFinal.size() + 1)) {
					for (Component childrenTreeCell : treecell.getChildren()) {
						System.out.println(childrenTreeCell);

						if (!(childrenTreeCell instanceof Label)) {
							continue;
						}

						subserie.setProcedimiento(((Label) childrenTreeCell).getValue());
						break;
					}
					continue;
				}

				for (int contList = 0; contList < opcionesDispFinal.size(); contList++) {
					int contTemp = NUMERO_CELDA_S + contList + 1;

					if (contCelda != contTemp) {
						continue;
					}

					for (Component childrenTreeCell : treecell.getChildren()) {
						System.out.println(childrenTreeCell);

						if (!(childrenTreeCell instanceof Checkbox)) {
							continue;
						}

						opcionesDispFinal.get(contList).setActivo(((Checkbox) childrenTreeCell).isChecked());
						break;
					}
				}

			} else {
				if (contCelda == NUMERO_CELDA_PROCEDIMIENTO) {
					for (Component childrenTreeCell : treecell.getChildren()) {
						System.out.println(childrenTreeCell);

						if (!(childrenTreeCell instanceof Label)) {
							continue;
						}

						subserie.setProcedimiento(((Label) childrenTreeCell).getValue());
						break;
					}
				}
			}
		}

		if (opcionesDispFinal != null && opcionesDispFinal.size() > 0) {
			subserie.setOpcionDisposicionFinals(opcionesDispFinal);
		}
		
		return subserie;
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
                	self.detach();
                }
            }
        });
	}
	
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

}
