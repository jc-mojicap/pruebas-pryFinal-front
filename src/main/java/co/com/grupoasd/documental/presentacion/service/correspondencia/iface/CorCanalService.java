/*
* Archivo: CorCanalService.java
* Fecha creacion = 22/03/2017
* Todos los derechos de propiedad intelectual e industrial sobre esta
* aplicacion son de propiedad exclusiva del GRUPO ASESORIA EN
* SISTEMATIZACION DE DATOS SOCIEDAD POR ACCIONES SIMPLIFICADA – GRUPO ASD S.A.S.
* Su uso, alteracion, reproduccion o modificacion sin el debido
* consentimiento por escrito del GRUPO ASD S.A.S.
* autorizacion por parte de su autor quedan totalmente prohibidos.
* 
* Este programa se encuentra protegido por las disposiciones de la
* Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
* propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
* previstas en la Ley.
*/
package co.com.grupoasd.documental.presentacion.service.correspondencia.iface;

import java.util.List;

import co.com.grupoasd.documental.cliente.correspondencia.model.CorCanal;

/**
 * Servicios del recurso CorCanal.
 * @author JuanMojica
 *
 */
public interface CorCanalService {
	
	/**
	 * Buscar un corCanal por su id.
	 * @param id identificador del corCanal.
	 * @return CorCanal asociado.
	 */
	CorCanal buscarPorId(Integer id);
	
	/**
	 * Lista los corCanales.
	 * @return Lista de corCanales.
	 */
	List<CorCanal> listar();

}
