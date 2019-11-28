/*
 * Archivo: CatalogoServiceFactory.java
 * Fecha creacion: 28/02/2017
 * Todos los derechos de propiedad intelectual e industrial sobre esta
 * aplicacion son de propiedad exclusiva del GRUPO ASESORIA EN
 * SISTEMATIZACION DE DATOS SOCIEDAD POR ACCIONES SIMPLIFICADA – GRUPO ASD S.A.S.
 * Su uso, alteracion, reproduccion o modificacion sin la debida
 * consentimiento por escrito de GRUPO ASD S.A.S.
 * autorizacion por parte de su autor quedan totalmente prohibidos.
 * 
 * Este programa se encuentra protegido por las disposiciones de la
 * Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
 * propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
 * previstas en la Ley.
 */
package co.com.grupoasd.documental.presentacion.service.catalogo;

import co.com.grupoasd.documental.presentacion.service.catalogo.iface.AccionService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.AreaService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.EmpresaService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.MdtMunicipioService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.ParametrosKeycloackService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SerieService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.SubserieService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.TipoDocumentalService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.UsuarioService;
import co.com.grupoasd.documental.presentacion.service.catalogo.impl.AccionServiceImpl;
import co.com.grupoasd.documental.presentacion.service.catalogo.impl.AreaServiceImpl;
import co.com.grupoasd.documental.presentacion.service.catalogo.impl.EmpresaServiceImpl;
import co.com.grupoasd.documental.presentacion.service.catalogo.impl.MdtMunicipioServiceImpl;
import co.com.grupoasd.documental.presentacion.service.catalogo.impl.ParametrosKeycloackServiceImpl;
import co.com.grupoasd.documental.presentacion.service.catalogo.impl.SerieServiceImpl;
import co.com.grupoasd.documental.presentacion.service.catalogo.impl.SubserieServiceImpl;
import co.com.grupoasd.documental.presentacion.service.catalogo.impl.TipoDocumentalServiceImpl;
import co.com.grupoasd.documental.presentacion.service.catalogo.impl.UsuarioServiceImpl;

/**
 * Factory que instancia las implementaciones de los servicois del modulo de
 * catalogos.
 * 
 * @author Juan Carlos Castellanos
 */
public final class CatalogoServiceFactory {

    /**
     * Constructor privado. Esta clase no puede instancearse.
     */
    private CatalogoServiceFactory() {

    }

    /**
     * Obtiene la implementacion del servicio de Empresa.
     * 
     * @return EmpresaService
     */
    public static EmpresaService getEmpresaService() {
        return new EmpresaServiceImpl();
    }

    /**
     * Obtiene la implementacion del servicio de Area.
     * 
     * @return AreaService
     */
    public static AreaService getAreaService() {
        return new AreaServiceImpl();
    }

    /**
     * Obtiene la implementacion del servicio de TipoDocumental.
     * 
     * @return TipoDocumentalService
     */
    public static TipoDocumentalService getTipoDocumentalService() {
        return new TipoDocumentalServiceImpl();
    }

    /**
     * Obtiene la implementación del servicio de Serie.
     * 
     * @return SerieService.
     */
    public static SerieService getSerieService() {
        return new SerieServiceImpl();
    }

    /**
     * Obtiene la implementación del servicio de Subserie.
     * 
     * @return SubserieService.
     */
    public static SubserieService getSubserieService() {
        return new SubserieServiceImpl();
    }

    /**
     * Obtiene la implementación del servicio de Accion.
     * 
     * @return AccionService.
     */
    public static AccionService getAccionService() {
        return new AccionServiceImpl();
    }

    /**
     * Obtienen la implementación del servicio de Usuario.
     * 
     * @return UsuarioService.
     */
    public static UsuarioService getUsuarioService() {
        return new UsuarioServiceImpl();
    }

    /**
     * Obtiene la implementacion del servicio de ParametrosKeycloack.
     * 
     * @return ParametrosKeycloackService.
     */
    public static ParametrosKeycloackService getParametrosKeycloackService() {
        return new ParametrosKeycloackServiceImpl();
    }
    
    /**
     * Obtiene la implementacion del servicio de MdtMunicipio.
     * 
     * @return MdtMunicipioService.
     */
    public static MdtMunicipioService getMdtMunicipioService() {
        return new MdtMunicipioServiceImpl();
    }

}
