/*
* Archivo: CorrespondenciaBitacoraRadicadoService.java
* Fecha creacion = 10/05/2017
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

import co.com.grupoasd.documental.cliente.correspondencia.model.CorrespondenciaBitacoraRadicado;

/**
 * Servicios del recurso CorrespondenciaBitacoraRadicado.
 * @author JuanMojica
 *
 */
public interface CorrespondenciaBitacoraRadicadoService {

    /**
     * Obtiene un CorrespondenciaBitacoraRadicado por su id.
     * @param id Identificador del CorrespondenciaBitacoraRadicado.
     * @return CorrespondenciaBitacoraRadicado asociado.
     */
    public CorrespondenciaBitacoraRadicado obtenerPorId(Long id);
    
    /**
     * Lista CorrespondenciaBitacoraRadicado por el radicado al que pertenecen.
     * @param radicadoId Identificador del radicado.
     * @return Lista de CorrespondenciaBitacoraRadicado asociados.
     */
    public List<CorrespondenciaBitacoraRadicado> listarPorRadicadoId(Long radicadoId);
}
