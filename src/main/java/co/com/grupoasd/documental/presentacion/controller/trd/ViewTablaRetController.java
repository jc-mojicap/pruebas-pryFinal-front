package co.com.grupoasd.documental.presentacion.controller.trd;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.cliente.catalogo.model.Serie;
import co.com.grupoasd.documental.cliente.catalogo.model.Subserie;
import co.com.grupoasd.documental.cliente.trd.model.TrdArea;
import co.com.grupoasd.documental.cliente.trd.model.TrdOpcionDisposicionFinal;
import co.com.grupoasd.documental.cliente.trd.model.TrdSerie;
import co.com.grupoasd.documental.cliente.trd.model.TrdSubserie;
import co.com.grupoasd.documental.cliente.trd.model.TrdSubserieOpcionAdcDisp;
import co.com.grupoasd.documental.cliente.trd.model.TrdTablaRetencion;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.controller.util.GenericController;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.AreaService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SerieService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SubserieService;
import co.com.grupoasd.documental.presentacion.service.trd.TrdServiceFactory;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdOpcionDisposicionFinalService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdSubserieOpcionAdcDispService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdTablaRetencionService;

/**
 * Controlador de la vista Ver Tabla Retención Documental
 * 
 * @author gmora
 * @since Junio 27 de 2017
 */
public class ViewTablaRetController extends GenericController<Window> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7620270330255977795L;
	private static Integer trdId;

	/**
	 * Tree
	 */
	private Tree treeTrd;
	private Treeitem treeitemEmpresa = new Treeitem();
	private Treechildren treechildrenEmpresa = new Treechildren();

	/**
	 * Objetos generales
	 */
	private Integer idEmpresaSesion;
	private String nombreTrd;
	private List<Area> areas;
	private List<Serie> series;
	private List<Subserie> subseries;
	private List<TrdOpcionDisposicionFinal> opcionesDispFinal;

	/**
	 * Servicios
	 */
	private TrdOpcionDisposicionFinalService opcionDisposicionFinalService;
	private TrdSubserieOpcionAdcDispService opcionSubserieOpcionService;
	private TrdTablaRetencionService opcionTrdService;
	private AreaService areaService;
	private SerieService serieService;
	private SubserieService subserieService;

	private Treecols treecols;
	private Auxheader auxheaderOpcDispFinal;

	private TrdTablaRetencion trd;
	private List<TrdArea> trdAreasList;
	private List<TrdSerie> trdSeriesList;
	private List<TrdSubserie> trdSubseriesList;

	private final static int DISPOSICION_FINAL_CT = 1;
	private final static int DISPOSICION_FINAL_E = 2;
	private final static int DISPOSICION_FINAL_S = 3;

	@Override
	public void onCreate(Event event) {
		try {
			/**
			 * Valores enviados desde la ventana anterior
			 */
			trdId = (Integer) args.get("trdId");

			/**
			 * Valores por sesión
			 */

			idEmpresaSesion = ((SesionVo) Sessions.getCurrent().getAttribute("usrAuth")).getEmpresaId();
			/**
			 * Inicializa servicios
			 */
			opcionDisposicionFinalService = TrdServiceFactory.getTrdOpcionDisposicionFinalService();
			opcionTrdService = TrdServiceFactory.getTrdTablaRetencionService();
			opcionSubserieOpcionService = TrdServiceFactory.getTrdSubserieOpcionAdcDispService();

			areaService = CatalogoServiceFactory.getAreaService();
			serieService = CatalogoServiceFactory.getSerieService();
			subserieService = CatalogoServiceFactory.getSubserieService();

			opcionesDispFinal = opcionDisposicionFinalService.listarPorEmpresaAndEstado(idEmpresaSesion, false);
			areas = new ArrayList<>();
			trd = opcionTrdService.getTrdById(trdId);
			nombreTrd = trd.getNombre();
			inicializar();

			/**
			 * Inicializamos el componente padre de los nodos
			 */

		} catch (Exception e) {
			Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
			self.detach();
		}

	}

	public void inicializar() {
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
		 * Observaciones
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
		 * Seteamos a partir de los datos obtenidos por el servicio para la trd
		 * - Area, Serie y Subserie
		 */
		Treerow treerow = new Treerow();
		Treecell treecell = new Treecell();
		Treechildren treechildren = new Treechildren();

		Label labelPadre = new Label();
		labelPadre.setStyle("margin:5px;");
		labelPadre.setValue(nombreTrd);

		Image imagePadre = new Image("/recursos/icons/folder.png");

		treecell.appendChild(imagePadre);
		treecell.appendChild(labelPadre);

		treerow.appendChild(treecell);
		treeitemEmpresa.appendChild(treerow);
		treechildren.appendChild(treeitemEmpresa);

		treeitemEmpresa.appendChild(treechildrenEmpresa);
		treeTrd.appendChild(treecols);
		treeTrd.appendChild(treechildren);

		onCargarAreas();

	}

	public void onCargarAreas() {
		areas = areaService.listar(idEmpresaSesion, false);
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
			} else {
				trdAreasList = trd.getAreaList();
				if (trdAreasList != null) {
					for (Area area : areas) {
						agregarArea(area);
					}
				}
			}
			areas.clear();
		} else {
			if (treechildrenEmpresa.getItems() != null) {

			}
		}
	}

	/**
	 * Agrega una Area al tree comparandola con la que existe en la TRD
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

		if (!trdAreasList.isEmpty()) {
			for (TrdArea a : trdAreasList) {
				if (a.getAreaId().equals(area.getAreaId())) {
					labelArea.setValue(area.getNombre());
					Image imagePadre = new Image("/recursos/icons/folder.png");
					treecell.appendChild(imagePadre);
					treecell.appendChild(labelArea);

					treerow.appendChild(treecell);
					treeitem.appendChild(treerow);
					treeitem.appendChild(treechildrenArea);
					treechildrenEmpresa.appendChild(treeitem);

					trdSeriesList = a.getSerieList();
					if (trdSeriesList != null && trdSeriesList.size() > 0) {
						series = serieService.listarPorArea(a.getAreaId(), false);
						if (series != null) {
							for (TrdSerie se : trdSeriesList) {
								onCargarSeries(se.getTrdSerieId(), treechildrenArea);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Evento cargar las series en los nodos areas
	 * 
	 * @param event
	 */
	public void onCargarSeries(Integer trdSerieId, Treechildren treechildrenArea) {

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
			} else {
				for (Serie serie : series) {
					agregarSerie(serie, treechildrenArea);
				}
			}
			series.clear();
		} else {
			return;
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

		if (!trdSeriesList.isEmpty()) {
			for (TrdSerie ts : trdSeriesList) {
				if (ts.getSerieId().equals(serie.getSerieId())) {
					labelSerie.setValue(serie.getNombre());
					Image imagePadre = new Image("/recursos/icons/folder.png");
					treecell.appendChild(imagePadre);
					treecell.appendChild(labelSerie);

					treerow.appendChild(treecell);
					treeitem.appendChild(treerow);
					treeitem.appendChild(treechildrenSerie);
					treechildrenArea.appendChild(treeitem);

					trdSubseriesList = ts.getSubseriesList();
					if (trdSubseriesList != null && trdSubseriesList.size() > 0) {
						subseries = subserieService.listarPorSerie(ts.getSerieId(), false);
						if (subseries != null) {
							for (TrdSubserie ss : trdSubseriesList) {
								onCargarSubseries(ss.getSubserieId(), treechildrenSerie);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Evento cargar subseries por serie
	 * 
	 * @param event
	 */
	public void onCargarSubseries(Integer subserieId, Treechildren treechildrenSerie) {

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
						// subseries.remove(subserie);
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

			} else {
				for (Subserie subserie : subseries) {
					agregarSubserie(subserie, treechildrenSerie);
				}
			}
			subseries.clear();
		} else {
			return;
		}
	}

	/**
	 * Agrega un item subserie al nodo serie
	 * 
	 * @param serie
	 * @param treechildrenSerie
	 */
	private void agregarSubserie(Subserie subserie, final Treechildren treechildrenSerie) {
		Treecell treecell = new Treecell();
		Treerow treerow = new Treerow();
		Treeitem treeitem = new Treeitem();
		Treechildren treechildrenSubserie = new Treechildren();

		treeitem.setValue(subserie);

		Label labelSubSerie = new Label();
		labelSubSerie.setStyle("margin:5px;");

		if (!trdSubseriesList.isEmpty()) {
			for (TrdSubserie ss : trdSubseriesList) {
				if (ss.getSubserieId().equals(subserie.getSubserieId())) {
					labelSubSerie.setValue(subserie.getNombre());
					Image imagePadre = new Image("/recursos/icons/folder.png");
					/**
					 * Eventos del nodo
					 */
					treecell.appendChild(imagePadre);
					treecell.appendChild(labelSubSerie);

					/**
					 * * * * * * * * * * * * * * * RETENCION * * * * * * * * * *
					 * * * * *
					 */

					/**
					 * Celda AG
					 */
					Treecell treecellAg = new Treecell();

					Label labelTxtAg = new Label();
					labelTxtAg.setStyle("width: 40px; height:20px; padding:5px;");
					treecellAg.appendChild(labelTxtAg);

					Label labelCmbAg = new Label();
					labelCmbAg.setStyle("width: 40px; height:20px; padding:5px;");
					treecellAg.appendChild(labelCmbAg);

					/**
					 * Celda AC
					 */
					Treecell treecellAc = new Treecell();

					Label labelTxtAc = new Label();
					labelTxtAc.setStyle("width: 40px; height:20px; padding:5px;");
					treecellAc.appendChild(labelTxtAc);

					Label labelCmbAc = new Label();
					labelCmbAc.setStyle("width: 40px; height:20px; padding:5px;");
					treecellAc.appendChild(labelCmbAc);

					/**
					 * DISPOSICION FINAL * * * * * *
					 * 
					 */

					/**
					 * Celda CT
					 */
					Treecell treecellCt = new Treecell();

					Label labelCt = new Label();
					labelCt.setStyle("padding:5px;");
					if (ss.getTipoDispId() == DISPOSICION_FINAL_CT) {
						labelCt.setValue("X");
						labelCt.setVisible(true);
					} 
					treecellCt.appendChild(labelCt);

					/**
					 * Celda E
					 */
					Treecell treecellE = new Treecell();

					Label labelE = new Label();
					labelE.setStyle("padding:5px;");

					if (ss.getTipoDispId() == DISPOSICION_FINAL_E) {
						labelE.setValue("X");
						labelE.setVisible(true);
					} 
					treecellE.appendChild(labelE);

					/**
					 * Celda S
					 */
					Treecell treecellS = new Treecell();

					Label labelS = new Label();
					labelS.setStyle("padding:5px;");
					if (ss.getTipoDispId() == DISPOSICION_FINAL_S) {
						labelS.setValue("X");
						labelS.setVisible(true);
					} 
					treecellS.appendChild(labelS);

					/**
					 * Antes de agregar las opciones de disposición final
					 * agregamos las celdas hasta dispocion final
					 */
					treerow.appendChild(treecell);
					treerow.appendChild(treecellAg);
					treerow.appendChild(treecellAc);
					treerow.appendChild(treecellCt);
					treerow.appendChild(treecellE);
					treerow.appendChild(treecellS);

					/**
					 * OPCIONES ADC DE DISPOSICION FINAL
					 */
					if (opcionesDispFinal != null && opcionesDispFinal.size() > 0) {
						for (TrdOpcionDisposicionFinal opc : opcionesDispFinal) {
							Treecell treecellOpc = new Treecell();

							Label label = new Label();
							label.setStyle("padding:5px;");							

							List<TrdSubserieOpcionAdcDisp> listDisposiciones = opcionSubserieOpcionService
									.getOpcionesDisposicionFinalBySubserieId(ss.getTrdSubserieId());
						
							if(listDisposiciones != null && listDisposiciones.size() > 0){								
								for(TrdSubserieOpcionAdcDisp obj : listDisposiciones){
									if(obj.getTrdOpcionDisposicionFinal().equals(opc.getOpcDispId()) && obj.isActivo()){										
										label.setValue("X");
										label.setVisible(true);
									}									
								}
							}							
							treecellOpc.appendChild(label);
							treerow.appendChild(treecellOpc);
						}
					}

					/**
					 * * * * * * * * * * * * * * * OBSERVACIONES * * * * * * * *
					 * * * * * * *
					 */

					Treecell treecellObs = new Treecell();
					Label labelObs = new Label();
					labelObs.setStyle("padding:5px;");
					treecellObs.appendChild(labelObs);

					labelTxtAg.setValue(ss.getAgValor().toString());
					labelTxtAg.setVisible(true);

					labelCmbAg.setValue(ss.getAgUnidad().toString());
					labelCmbAg.setVisible(true);

					labelTxtAc.setVisible(true);
					labelTxtAc.setValue(ss.getAcValor().toString());

					labelCmbAc.setVisible(true);
					labelCmbAc.setValue(ss.getAcUnidad().toString());

					labelCt.setVisible(true);
					labelE.setVisible(true);
					labelS.setVisible(true);

					/**
					 * * * * * * * * * * * * * * * OPCIONES * * * * * * * * * *
					 * * * * *
					 */
					Treecell treecellOpc = new Treecell();

					/**
					 * AGREGAMOS LAS CELDAS A LA FILA DE PROCEDIMIENTOS Y
					 * OPCIONES * * * * * * * * * * * * * *
					 */
					treerow.appendChild(treecellObs);
					treerow.appendChild(treecellOpc);

					treeitem.appendChild(treerow);
					treeitem.appendChild(treechildrenSubserie);
					treechildrenSerie.appendChild(treeitem);
				}
			}
		}
	}

	/**
	 * Action Aceptar
	 */
	public void onClick$btnAceptar(Event event) {
		event.stopPropagation();
		onClose(event);
	}

	/**
	 * Action Cerrar Ventana
	 */
	@Override
	public void onClose(Event event) {
		self.detach();
	}

}
