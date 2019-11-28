/*
* Archivo: CorSecuenciaService.java
* Fecha creacion = 03/04/2017
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
package co.com.grupoasd.documental.presentacion.service.correspondencia.iface;

import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorSecuencia;

/**
 * Servicios del recurso CorSecuencia.
 * @author JuanMojica
 *
 */
public interface CorSecuenciaService {
    
    /**
     * Buscar una CorSecuencia por su id.
     * @param id Identificador de la secuencia.
     * @return CorSecuencia asociada.
     */
    CorSecuencia obtenerPorId(final Integer id);
    
    /**
     * Buscar una CorSecuencia por su empresa.
     * @param id Identificador de la empresa.
     * @return CorSecuencia asociada.
     */
    CorSecuencia obtenerPorEmpresa(final Integer id);
    
    /**
     * Crea una nueva secuencia.
     * @param token Token con identificacion del usuario.
     * @param corSecuencia Objeto CorSecuencia.
     * @return Objeto CorSecuencia creado con identificador asignado.
     */
    CorSecuencia crear(final Token token, final CorSecuencia corSecuencia);    
    
}
