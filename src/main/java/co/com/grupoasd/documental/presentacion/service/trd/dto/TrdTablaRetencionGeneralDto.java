/**
 * 
 */
package co.com.grupoasd.documental.presentacion.service.trd.dto;

import java.util.List;

import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.cliente.trd.model.TrdTablaRetencion;

/**
 * Dto general para el envio de los parametros necesarios para la creación de una TRD
 * @author alopez
 * @since Junio 29 de 2017
 */
public class TrdTablaRetencionGeneralDto {
	
	/**
	 * Objeto Trd
	 */
	private TrdTablaRetencion trdTablaRetencion;
	
	/**
	 * Lista de areas
	 */
	private List<Area> areas;
	
	/**
	 * Constructor
	 */
	public TrdTablaRetencionGeneralDto() {
		
	}

	/**
	 * @return the trdTablaRetencion
	 */
	public TrdTablaRetencion getTrdTablaRetencion() {
		return trdTablaRetencion;
	}

	/**
	 * @param trdTablaRetencion the trdTablaRetencion to set
	 */
	public void setTrdTablaRetencion(TrdTablaRetencion trdTablaRetencion) {
		this.trdTablaRetencion = trdTablaRetencion;
	}

	/**
	 * @return the trdNodoDtos
	 */
	public List<Area> getAreas() {
		return areas;
	}

	/**
	 * @param trdNodoDtos the trdNodoDtos to set
	 */
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

}
