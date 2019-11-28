/*
* Archivo: TrdServiceFactory.java
* Fecha creacion = 06/06/2017
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
package co.com.grupoasd.documental.presentacion.service.trd;

import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdAreaService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdCodificacionService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdDocumentoService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdEstructuraService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdExpedienteService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdOpcionDisposicionFinalService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdSerieService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdSubserieOpcionAdcDispService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdSubserieService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdTablaRetencionService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdMetadatoService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdMetadatoSubserieService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdMetadatoTipoDocmService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdTipoDatoService;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdTipoDocumentalService;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdAreaServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdCodificacionServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdDocumentoServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdEstructuraServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdExpedienteServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdMetadatoServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdOpcionDisposicionFinalServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdSerieServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdSubserieOpcionAdcDispServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdSubserieServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdTablaRetencionServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdMetadatoSubserieServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdMetadatoTipoDocmServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdTipoDatoServiceImpl;
import co.com.grupoasd.documental.presentacion.service.trd.impl.TrdTipoDocumentalServiceImpl;

/**
 * Factory que instancia las implementaciones de los servicios del modulo de
 * trd.
 * 
 * @author JuanMojica
 *
 */
public final class TrdServiceFactory {

	/**
	 * Constructor privado. Esta clase no debe instancearse.
	 */
	private TrdServiceFactory() {

	}

	/**
	 * Obtiene la implementación del servicio de TrdTipoDato.
	 * 
	 * @return TrdTipoDatoService.
	 */
	public static TrdTipoDatoService getTrdTipoDatoService() {
		return new TrdTipoDatoServiceImpl();
	}

	/**
	 * Obtiene la implementación del servicio de TrdEstructura.
	 * 
	 * @return TrdEstructuraService.
	 */
	public static TrdEstructuraService getTrdEstructuraService() {
		return new TrdEstructuraServiceImpl();
	}

	/**
	 * Obtiene la implementación del servicio de TrdCodificacionService.
	 * 
	 * @return TrdCodificacionService.
	 */
	public static TrdCodificacionService getTrdCodificacionService() {
		return new TrdCodificacionServiceImpl();
	}

	/**
	 * Obtiene la implementación de TrdOpcionDisposicionFinalService
	 * 
	 * @return TrdOpcionDisposicionFinalService
	 */
	public static TrdOpcionDisposicionFinalService getTrdOpcionDisposicionFinalService() {
		return new TrdOpcionDisposicionFinalServiceImpl();
	}

	/**
	 * Obtiene la implementación del servicio de TrdMetadatoService.
	 * 
	 * @return TrdMetadatoService.
	 */
	public static TrdMetadatoService getTrdMetadatoService() {
		return new TrdMetadatoServiceImpl();
	}

	/**
	 * 
	 * @return
	 */
	public static TrdDocumentoService getTrdDocumentoService() {
		return new TrdDocumentoServiceImpl();
	}

	/**
	 * 
	 * @return
	 */
	public static TrdExpedienteService getTrdExpedienteService() {
		return new TrdExpedienteServiceImpl();
	}

	/**
	 * Obtiene la implementación del servicio de TrdMetadatoSubserieService.
	 * 
	 * @return TrdMetadatoSubserieService.
	 */
	public static TrdMetadatoSubserieService getTrdMetadatoSubserieService() {
		return new TrdMetadatoSubserieServiceImpl();
	}

	/**
	 * Obtiene la implementación del servicio de TrdMetadatoTipoDocmService.
	 * 
	 * @return TrdMetadatoTipoDocmService.
	 */
	public static TrdMetadatoTipoDocmService getTrdMetadatoTipoDocmService() {
		return new TrdMetadatoTipoDocmServiceImpl();
	}

	/**
	 * Implementacion TrdTablaRetencionService
	 * 
	 * @return TrdTablaRetencionService
	 */
	public static TrdTablaRetencionService getTrdTablaRetencionService() {
		return new TrdTablaRetencionServiceImpl();
	}

	/**
	 * Implementacion TrdAreaService
	 * 
	 * @return TrdAreaService
	 */
	public static TrdAreaService getTrdAreaService() {
		return new TrdAreaServiceImpl();
	}

	/**
	 * Implementacion TrdSerieService
	 * 
	 * @return TrdSerieService
	 */
	public static TrdSerieService getTrdSerieService() {
		return new TrdSerieServiceImpl();
	}

	/**
	 * Implementacion TrdSubserieService
	 * 
	 * @return TrdSubserieService
	 */
	public static TrdSubserieService getTrdSubserieService() {
		return new TrdSubserieServiceImpl();
	}

	/**
	 * Implementacion TrdTipoDocumentalService
	 * 
	 * @return TrdTipoDocumentalService
	 */
	public static TrdTipoDocumentalService getTrdTipoDocumentalService() {
		return new TrdTipoDocumentalServiceImpl();
	}

	/**
	 * 
	 * @return
	 */
	public static TrdSubserieOpcionAdcDispService getTrdSubserieOpcionAdcDispService(){
    	return new TrdSubserieOpcionAdcDispServiceImpl();
    }
    
}
