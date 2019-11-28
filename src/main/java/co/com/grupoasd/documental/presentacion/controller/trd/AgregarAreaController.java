package co.com.grupoasd.documental.presentacion.controller.trd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.AreaService;

public class AgregarAreaController {
	
	@Wire
	private Window windowAgregarArea;
	
	private AreaService areaService;
	private Integer idEmpresaSesion;
	private String nombreTrd;
	private String callback;
	private List<Area> areas;
	private List<Area> areasPersistencia;

	private List<Area> areasSeleccionadas;

	@Wire
	private Textbox txtBusqueda;
	
	@Init
	public void init(@ExecutionArgParam("nombreTrd") String nombreTrd, @ExecutionArgParam("callback") String callback, 
			@ExecutionArgParam("areas") List<Area> areasSeleccionadas) {
		this.nombreTrd = nombreTrd;
		this.callback = callback;
		this.areasSeleccionadas = areasSeleccionadas;
		
		if (this.areasSeleccionadas == null) {
			this.areasSeleccionadas = new ArrayList<>();
		}
		
		areaService = CatalogoServiceFactory.getAreaService();
		idEmpresaSesion = ((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getEmpresaId();
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		areas = areaService.listar(idEmpresaSesion, false);
		areasPersistencia = areas;
		
		if (this.nombreTrd == null || this.callback == null) {
			windowAgregarArea.detach();
			return;
		}
		
		windowAgregarArea.setTitle("Agregar Área - ".concat(this.nombreTrd));
	}
	
	/**
	 * @return the areas
	 */
	public List<Area> getAreas() {
		return areas;
	}

	/**
	 * @param areas the areas to set
	 */
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	
	/**
	 * @return the areasSeleccionadas
	 */
	public List<Area> getAreasSeleccionadas() {
		return areasSeleccionadas;
	}

	/**
	 * @param areasSeleccionadas the areasSeleccionadas to set
	 */
	public void setAreasSeleccionadas(List<Area> areasSeleccionadas) {
		this.areasSeleccionadas = areasSeleccionadas;
	}
	
	/**
	 * Action boton de busqueda
	 * @param event
	 */
	@NotifyChange({ "areas", "areasSeleccionadas" })
	@Command
	public void buscarAreaXButton(Event event) {
		buscarArea();
	}
	
	/**
	 * Action busqueda por Textbox
	 * @param event
	 */
	@NotifyChange({ "areas", "areasSeleccionadas" })
	@Command
	public void buscarAreaXTextbox(Event event) {
		buscarArea();
	}
	
	/**
	 * Busca area por codigo o nombre
	 */
	private void buscarArea() {
		if (areasPersistencia != null && areasPersistencia.size() > 0) {
			if (txtBusqueda.getValue() != null && !txtBusqueda.getValue().toString().isEmpty()) {
				if (StringUtils.isNumeric(txtBusqueda.getValue().toString())) {
					areas = areasPersistencia.stream().filter(x -> x.getAreaId() == Integer.parseInt(txtBusqueda.getValue().toString())).collect(Collectors.toList());
				} else {
					areas = areasPersistencia.stream().filter(x -> StringUtils.containsIgnoreCase(x.getNombre(), txtBusqueda.getValue().toString())).collect(Collectors.toList());
				}
			} else {
				areas = areasPersistencia;
			}
		}
	}
	
	/**
	 * Evento seleccion de la tabla
	 * @param event
	 */
	@Command
	public void seleccionarAreas() {
		System.out.println(areasSeleccionadas);
	}
	
	/**
	 * Action aceptar selección
	 * @param event
	 */
	@Command
	public void aceptarSeleccion() {
        Events.sendEvent(callback, windowAgregarArea.getParent() , areasSeleccionadas);
        windowAgregarArea.detach();
    }
	
	/**
	 * Action aceptar selección
	 * @param event
	 */
	@Command
	public void cancelarSeleccion() {
		windowAgregarArea.detach();
    }

}
