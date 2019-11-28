/*
* Archivo: SerieService.java
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
package co.com.grupoasd.documental.presentacion.service.catalogo.iface;

import java.util.List;

import co.com.grupoasd.documental.cliente.catalogo.model.Serie;

/**
 * Servicios del recurso Serie.
 */
public interface SerieService {

    /**
     * Buscar una serie por su id.
     * 
     * @param id
     *            identificador de la serie.
     * @param isInactivo Define si la serie se encuentra inactiva, true si está inactiva, false activa            
     * @return Serie asociada.
     */
    Serie buscarPorId(Integer id, boolean isInactivo);

    /**
     * Lista series por su área.
     * 
     * @param id identificador del área.
     * @param isInactivo Define si la serie se encuentra inactiva, true si está inactiva, false activa
     * @return Lista de series asociadas.
     */
    List<Serie> listarPorArea(Integer id, boolean isInactivo);
}
