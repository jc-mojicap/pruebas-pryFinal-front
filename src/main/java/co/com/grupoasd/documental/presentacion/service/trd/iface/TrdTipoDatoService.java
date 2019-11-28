/*
* Archivo: TrdTipoDatoService.java
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

import co.com.grupoasd.documental.cliente.trd.model.TrdTipoDato;

/**
 * Servicios del recurso TrdTipoDato.
 * @author JuanMojica
 *
 */
public interface TrdTipoDatoService {

    /**
     * Listar todos los tipos de dato.
     * @return Lista con todos los tipos de dato.
     */
    List<TrdTipoDato> listarTodos();
    
    /**
     * Obtener un tipo de dato por id.
     * @param id Identificador del tipo de dato.
     * @return tipo de dato asociado.
     */
    TrdTipoDato obtenerPorId(Integer id);
    
}
