/*
* Archivo: TrdCodificacionService.java
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
package co.com.grupoasd.documental.presentacion.service.trd.iface;

import java.util.List;

import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.trd.model.TrdCodificacion;

/**
 * Servicios del recurso TrdCodificacion.
 * @author JuanMojica
 *
 */
public interface TrdCodificacionService {

    /**
     * Listar todas las Codificaciones.
     * @return Lista con todas las Codificaciones.
     */
    List<TrdCodificacion> listarTodos();
    
    /**
     * Listar las Codificaciones por empresa.
     * @param empresaId identificador de la empresa.
     * @return Lista de las Codificaciones asociadas.
     */
    List<TrdCodificacion> listarPorEmpresaId(Integer empresaId);
    
    /**
     * Listar las Codificaciones por estructura.
     * @param estructuraId identificador de la estructura.
     * @return Lista de las Codificaciones asociadas.
     */
    List<TrdCodificacion> listarPorEstructuraId(Integer estructuraId);
    
    /**
     * Listar las Codificaciones por empresa y estructura.
     * @param empresaId identificador de la empresa.
     * @param estructuraId identificador de la estructura.
     * @return Lista de las Codificaciones asociadas.
     */
    List<TrdCodificacion> listarPorEmpresaIdYEstructuraId(Integer empresaId, Integer estructuraId);
    
    /**
     * Guarda las Codificaciones.
     * @param token Token con identificacion del usuario.
     * @param trdCodificaciones Lista de codificaciones a guardar.
     * @return Lista de las Codificaciones actualizadas.
     */
    List<TrdCodificacion> guardarCodificaciones(Token token, List<TrdCodificacion> trdCodificaciones);
}
