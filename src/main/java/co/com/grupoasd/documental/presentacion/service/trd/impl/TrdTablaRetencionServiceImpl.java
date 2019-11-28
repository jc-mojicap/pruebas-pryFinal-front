/**
 * 
 */
package co.com.grupoasd.documental.presentacion.service.trd.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.trd.TrdTablaRetencionProxyService;
import co.com.grupoasd.documental.cliente.trd.model.TrdTablaRetencion;
import co.com.grupoasd.documental.presentacion.controller.util.DateUtils;
import co.com.grupoasd.documental.presentacion.service.trd.dto.ResultadoGeneracionTablaRetencionExcel;
import co.com.grupoasd.documental.presentacion.service.trd.dto.TrdTablaRetencionGeneralDto;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdTablaRetencionService;

/**
 * Implementación TrdTablaRetencionService
 * @author gmora
 * @since Junio 22 de 2017
 */
public class TrdTablaRetencionServiceImpl implements TrdTablaRetencionService {

	private final static String SI = "si";
	private final static String NO = "no";
	
	@Override
	public List<TrdTablaRetencion> listarPorEmpresaId(Integer empresaId) {
		return TrdTablaRetencionProxyService.listarPorEmpresaId(empresaId);
	}

	@Override
	public List<TrdTablaRetencion> listarPorEmpresaIdAndEstado(Integer empresaId, Integer estado) {		
		return TrdTablaRetencionProxyService.listarPorEmpresaIdAndEstado(empresaId, estado);
	}
	
	@Override
	public List<TrdTablaRetencion> listarPorEmpresaIdAndEstadoDelExpediente(Integer empresaId, Integer estado){
		return TrdTablaRetencionProxyService.listarPorEmpresaIdAndEstadoDelExpediente(empresaId, estado);
	}

	@Override
	public boolean eliminarTrd(Token token, Integer trdId) {
		return TrdTablaRetencionProxyService.eliminar(token, trdId);		
	}
	
	public TrdTablaRetencion generarTrd(final Token token, final TrdTablaRetencionGeneralDto generalDto) {
		return TrdTablaRetencionProxyService.generarTrd(token, generalDto);
	}

	@Override
	public TrdTablaRetencion getTrdById(Integer trdId) {
		return TrdTablaRetencionProxyService.getTrdById(trdId);
	}

	@Override
	public List<TrdTablaRetencion> filtrar(List<TrdTablaRetencion> list, String search) {
		List<TrdTablaRetencion> listadoTrdsFiltro = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		Calendar fecha = Calendar.getInstance();		
		DateFormat sdf1 = new SimpleDateFormat("d-MMM-yyyy");
		
		for (TrdTablaRetencion trd : list) {
			if (trd.getNombre().toLowerCase().contains(search)) {
				listadoTrdsFiltro.add(trd);
			}
			if (trd.getFechaCreacion() != null) {
				try {
					cal.setTime(sdf1.parse(search));	
					fecha.setTime(trd.getFechaCreacion());
					if(DateUtils.obtenerDiferenciaDiasSinHora(fecha.getTime(), cal.getTime()) == 0){
						listadoTrdsFiltro.add(trd);
					}	
				} catch (ParseException e) {					
					System.out.println("El filtro de busqueda no es una fecha .... " + e);
				}				
			}
			if (trd.getFechaGeneracion() != null) {
				try {
					cal.setTime(sdf1.parse(search));					
					fecha.setTime(trd.getFechaGeneracion());
					if(DateUtils.obtenerDiferenciaDiasSinHora(fecha.getTime(), cal.getTime()) == 0){
						listadoTrdsFiltro.add(trd);
					}					
				} catch (ParseException e) {					
					System.out.println("El filtro de busqueda no es una fecha .... " + e);
				}				
			}
			if (trd.getEstado() != null && trd.getEstado().getNombre().toString().toLowerCase().contains(search)) {
				listadoTrdsFiltro.add(trd);
			}
			try {
				if (trd.getTotalExpendientes() == Integer.parseInt(search)) {
					listadoTrdsFiltro.add(trd);
				}
			} catch (Exception e) {
				System.out.println("El filtro de busqueda no es un número .... " + e);
			}
			if (search.toString().toLowerCase().contains(SI)) {
				if (trd.getVigente() == true) {
					listadoTrdsFiltro.add(trd);
				}
			}
			if (search.toString().toLowerCase().contains(NO)) {
				if (trd.getVigente() == false) {
					listadoTrdsFiltro.add(trd);
				}
			}
		}
		return listadoTrdsFiltro;
	}

	@Override
	public ResultadoGeneracionTablaRetencionExcel exportarToXSL(Integer trdId) {		
		return TrdTablaRetencionProxyService.exportarToXSL(trdId);
	}

	public TrdTablaRetencion guardarParcial(Token token, final TrdTablaRetencionGeneralDto generalDto) {
		return TrdTablaRetencionProxyService.guardarParcial(token, generalDto);
	}

	@Override
	public String obtenerNombreTrd(Integer empresaId, String nombreEmpresa) {
		return TrdTablaRetencionProxyService.obtenerNombreTrd(empresaId, nombreEmpresa);
	}
}
