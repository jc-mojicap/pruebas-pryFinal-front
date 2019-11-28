/*
* Archivo: AccionService.java
* Fecha creacion = 28/04/2017
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

import co.com.grupoasd.documental.presentacion.service.catalogo.dto.AccionDto;

/**
 * Servicios del recurso accion.
 * 
 */
public interface AccionService {

    /**
     * Busca una lista de acciones por el usuario y rol al que están asociadas.
     * 
     * @param rolId
     *            id del rol.
     * @param usuarioId
     *            id del usuario.
     * @return Lista de acciones asociadas.
     */
    public List<AccionDto> listarPorUsuarioYRol(Integer rolId, Integer usuarioId);

    /**
     * listarPorUsuarioIdEInactivo.
     * 
     * @param usuarioId
     * @param inactivo
     * @return Lista de Acciones por el Id Usuario y si están inactivas o no las
     *         acciones.
     */
    public List<AccionDto> listarPorUsuarioIdEInactivo(Integer usuarioId, Boolean inactivo);

    /**
     * listarPorRolIdEInactivo.
     * 
     * @param rolId
     * @param inactivo
     * @return Lista de Acciones por el Id Usuario y si están inactivas o no las
     *         acciones.
     */
    public List<AccionDto> listarPorRolIdEInactivo(Integer rolId, Boolean inactivo);

}
