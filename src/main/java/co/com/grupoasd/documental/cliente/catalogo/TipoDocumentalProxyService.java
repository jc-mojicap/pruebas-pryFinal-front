/*
 * Archivo: TipoDocumentalProxyService.java
 * Fecha creacion: 21/03/2017
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
package co.com.grupoasd.documental.cliente.catalogo;

import java.util.List;
import co.com.grupoasd.documental.cliente.catalogo.iface.TipoDocumentalIface;
import co.com.grupoasd.documental.cliente.catalogo.model.TipoDocumental;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;

/**
 * Proxy que permite la invocacion remota de los servicios del recurso tipo
 * documental.
 * 
 * @author cestrada
 *
 */
public final class TipoDocumentalProxyService {

    private TipoDocumentalProxyService() {

    }

    /**
     * Se obtiene un tipoDocumental por id.
     * 
     * @param id
     *            Filtro Identificador del tipo documental.
     * @return TipoDocumental objeto tipo documental.
     */
    public static TipoDocumental obtenerPorId(Integer id) {
        return ServiciosEndpoint.get().proxy(TipoDocumentalIface.class).obtenerPorId(id);
    }

    /**
     * Lista todos los tipos documentales, o por id de la subserie y/o activas e
     * inactivas.
     * 
     * @param subserieId
     *            Filtro Identificador de la subserie se puede enviar null.
     * @param inactivo
     *            Filtro Inactivo .
     * @return List<TipoDocumental> listado de los tipos documentales, si los
     *         filtros son null, se consultan todas los tipos documentales.
     */
    public static List<TipoDocumental> listar(Integer subserieId, Boolean inactivo) {
        return ServiciosEndpoint.get().proxy(TipoDocumentalIface.class).listar(subserieId, inactivo);
    }

}
