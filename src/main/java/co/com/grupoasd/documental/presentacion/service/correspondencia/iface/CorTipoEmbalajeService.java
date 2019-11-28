/*
* Archivo: CorTipoEmbalajeService.java
* Fecha creacion = 23/03/2017
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

import co.com.grupoasd.documental.cliente.correspondencia.model.CorTipoEmbalaje;

/**
 * Servicios del recurso CorTipoEmbalaje.
 * @author JuanMojica
 *
 */
public interface CorTipoEmbalajeService {

	/**
	 * Buscar un CorTipoEmbalaje por su id.
	 * @param id identificador del CorTipoEmbalaje.
	 * @return CorTipoEmbalaje asociado.
	 */
	CorTipoEmbalaje buscarPorId(Integer id);
	
	/**
	 * Lista los CorTipoEmbalaje.
	 * @return Lista de CorTipoEmbalaje.
	 */
	List<CorTipoEmbalaje> listar();
}
