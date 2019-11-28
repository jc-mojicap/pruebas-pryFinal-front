/*
* Archivo: FormatosPermitidosArchivoService.java
* Fecha creacion = 22/05/2017
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

import java.util.List;

import co.com.grupoasd.documental.cliente.correspondencia.model.FormatosPermitidosArchivo;

/**
 * Servicios del recurso FormatosPermitidosArchivo.
 * @author JuanMojica
 *
 */
public interface FormatosPermitidosArchivoService {
    
    /**
     * Obtiene un FormatosPermitidosArchivo por su id.
     * @param id Identificador del FormatosPermitidosArchivo.
     * @return FormatosPermitidosArchivo asociado.
     */
    FormatosPermitidosArchivo obtenerPorId(Integer id);
    
    /**
     * Lista todos los FormatosPermitidosArchivo existentes en el sistema.
     * @return Lista con los FormatosPermitidosArchivo.
     */
    List<FormatosPermitidosArchivo> listarTodos();

}
