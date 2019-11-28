/*
* Archivo: ParametroskeycloackProxyService.java
* Fecha creacion = 13/03/2017
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
package co.com.grupoasd.documental.cliente.catalogo;

import co.com.grupoasd.documental.cliente.catalogo.iface.ParametrosKeycloackRestIface;
import co.com.grupoasd.documental.cliente.catalogo.model.ParametrosKeycloack;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;

/**
 * Proxy que permite la invocacion remota de los servicios del recurso Parametroskeycloack.
 * @author JuanMojica
 *
 */
public final class ParametroskeycloackProxyService {

    /**
	 * Constructor privado. Esta clase no debe instanciarse.
	 */
	private ParametroskeycloackProxyService(){
		
	}
	
	/**
	 * Busca los ParametrosKeycloack por su id.
	 * @param id identificador para buscar los ParametrosKeycloack.
	 * @return ParametrosKeycloack.
	 */
	public static ParametrosKeycloack obtenerPorId(final Integer id){
		return ServiciosEndpoint.get().proxy(ParametrosKeycloackRestIface.class).obtenerPorId(id);
	}
	
}
