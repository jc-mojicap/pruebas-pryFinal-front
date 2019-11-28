/*
* Archivo: TrdMetadatoSubserieService.java
* Fecha creacion = 20/06/2017
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
import co.com.grupoasd.documental.cliente.trd.model.TrdMetadatoSubserie;

/**
 * Servicios del recurso TrdMetadatoSubserie.
 * @author JuanMojica
 */
public interface TrdMetadatoSubserieService {

    /**
     * obtenerPorMetadatoIdYSubserieId.
     * @param metadatoId Identificador del metadato.
     * @param subserieId Identificador de la subserie.
     * @return TrdMetadatoSubserie asociado.
     */
    TrdMetadatoSubserie obtenerPorMetadatoIdYSubserieId(Integer metadatoId, Integer subserieId);
    
    /**
     * listarPorMetadato.
     * @param metadatoId Identificador del metadato.
     * @return TrdMetadatoSubserie asociado.
     */
    List<TrdMetadatoSubserie> listarPorMetadato(Integer metadatoId);
    
    /**
     * listarPorSubserie.
     * @param subserieId Identificador de la subserie.
     * @return TrdMetadatoSubserie asociado.
     */
    List<TrdMetadatoSubserie> listarPorSubserie(Integer subserieId);
    
    /**
     * guardarTrdMetadatosSubserie.
     * @param token Token con identificación del usuario.
     * @param trdMetadatosSubserie Lista de trdMetadatoSubserie a guardar.
     * @return lista de trdMetadatoSubserie guardadas.
     */
    List<TrdMetadatoSubserie> guardarTrdMetadatosSubserie(Token token, List<TrdMetadatoSubserie> trdMetadatosSubserie);
    
}
