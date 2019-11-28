/*
* Archivo: SubserieProxyService.java
* Fecha creacion = 21/03/2017
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

import java.util.List;

import co.com.grupoasd.documental.cliente.catalogo.iface.SubserieRestIface;
import co.com.grupoasd.documental.cliente.catalogo.model.Subserie;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;

/**
 * Proxy que permite la invocacion remota de los servicios del recurso Subserie.
 * 
 * @author JuanMojica
 *
 */
public final class SubserieProxyService {

    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private SubserieProxyService() {

    }

    /**
     * Busca una serie por su id.
     * 
     * @param id Identificador de la serie.
     * @param isInactivo define si la subserie se encuentra inactiva, true si está inactiva, false activa.
     * @return Serie asociada.
     */
    public static Subserie obtenerPorId(final Integer id, final boolean isInactivo) {
        return ServiciosEndpoint.get().proxy(SubserieRestIface.class).obtenerPorId(id, isInactivo);
    }

    /**
     * Busca una lista de series por su área.
     * 
     * @param id Identificador del área.
     * @param isInactivo define si la subserie se encuentra inactiva, true si está inactiva, false activa.
     * @return Lista de series asociadas.
     */
    public static List<Subserie> listarPorArea(final Integer id, final boolean isInactivo) {
        return ServiciosEndpoint.get().proxy(SubserieRestIface.class).listarPorSerie(id, isInactivo);
    }
}