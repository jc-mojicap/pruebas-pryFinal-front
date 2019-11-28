/*
* Archivo: CorrespondenciaServiceFactory.java
* Fecha creacion = 10/03/2017
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
package co.com.grupoasd.documental.presentacion.service.correspondencia;

import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorAdjuntoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorCanalService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorComentarioService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTerceroService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTipoEmbalajeService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTransportadoraService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorrespondenciaBitacoraRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.FormatosPermitidosArchivoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.EstadoRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.impl.CorAdjuntoServiceImpl;
import co.com.grupoasd.documental.presentacion.service.correspondencia.impl.CorCanalServiceImpl;
import co.com.grupoasd.documental.presentacion.service.correspondencia.impl.CorComentarioServiceImpl;
import co.com.grupoasd.documental.presentacion.service.correspondencia.impl.CorTerceroServiceImpl;
import co.com.grupoasd.documental.presentacion.service.correspondencia.impl.CorTipoEmbalajeServiceImpl;
import co.com.grupoasd.documental.presentacion.service.correspondencia.impl.CorTransportadoraServiceImpl;
import co.com.grupoasd.documental.presentacion.service.correspondencia.impl.CorrespondenciaBitacoraRadicadoServiceImpl;
import co.com.grupoasd.documental.presentacion.service.correspondencia.impl.FormatosPermitidosArchivoServiceImpl;
import co.com.grupoasd.documental.presentacion.service.correspondencia.impl.EstadoRadicadoServiceImpl;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorSecuenciaService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.impl.CorRadicadoServiceImpl;
import co.com.grupoasd.documental.presentacion.service.correspondencia.impl.CorSecuenciaServiceImpl;

/**
 * Factory que instancia las implementaciones de los servicios del modulo de
 * correspondencia.
 * 
 * @author JuanMojica
 *
 */
public final class CorrespondenciaServiceFactory {

    /**
     * Constructor privado. Esta clase no debe instancearse.
     */
    private CorrespondenciaServiceFactory() {

    }

    /**
     * Obtiene la implementación del servicio de CorCanal.
     * 
     * @return SubserieService.
     */
    public static CorCanalService getCorCanalService() {
        return new CorCanalServiceImpl();
    }

    /**
     * Obtiene la implementación del servicio de CorTransportadora.
     * 
     * @return
     */
    public static CorTransportadoraService getCorTransportadoraService() {
        return new CorTransportadoraServiceImpl();
    }

    /**
     * Obtiene la implementación del servicio de CorTipoEmbalaje.
     * 
     * @return
     */
    public static CorTipoEmbalajeService getCorTipoEmbalajeService() {
        return new CorTipoEmbalajeServiceImpl();
    }

    /**
     * Obtiene la implementación del servicio de CorTercero.
     * 
     * @return
     */
    public static CorTerceroService getCorTerceroService() {
        return new CorTerceroServiceImpl();
    }

    /**
     * Obtiene la implementacion del servicio de CorrespondenciaRadicado.
     * 
     * @return CorrespondenciaRadicadoService
     * @author cestrada
     */
    public static CorRadicadoService getCorRadicadoService() {
        return new CorRadicadoServiceImpl();
    }

    /**
     * Obtiene la implementación del servicio de CorComentario.
     * 
     * @return
     */
    public static CorComentarioService getCorComentarioService() {
        return new CorComentarioServiceImpl();
    }

    /**
     * Obtiene la implementación del servicio de CorSecuencia.
     * 
     * @return
     */
    public static CorSecuenciaService getCorSecuenciaService() {
        return new CorSecuenciaServiceImpl();
    }
    
    /**
     * Obtiene la implementación del servicio de CorrespondenciaBitacoraRadicado.
     * 
     * @return
     */
    public static CorrespondenciaBitacoraRadicadoService getCorrespondenciaBitacoraRadicadoService() {
        return new CorrespondenciaBitacoraRadicadoServiceImpl();
    }
    
    /**
     * Obtiene la implementación del servicio EstadoRadicadoService
     * @return
     */
    public static EstadoRadicadoService getEstadoRadicadoService() {
    	return new EstadoRadicadoServiceImpl();
    }
    
    /**
     * Obtiene la implementación del servicio de FormatosPermitidosArchivo.
     * 
     * @return
     */
    public static FormatosPermitidosArchivoService getFormatosPermitidosArchivoService(){
        return new FormatosPermitidosArchivoServiceImpl();
    }
    
    /**
     * Obtiene la implementacion de CorAdjuntoService
     * @return
     */
    public static CorAdjuntoService getCorAdjuntoService() {
    	return new CorAdjuntoServiceImpl();
    }

}
