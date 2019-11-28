/*
* Archivo: TrdMetadatoTipoDocmService.java
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
import co.com.grupoasd.documental.cliente.trd.model.TrdMetadatoTipoDocm;

/**
 * Servicios del recurso TrdMetadatoTipoDocm.
 * @author JuanMojica
 */
public interface TrdMetadatoTipoDocmService {

    /**
     * obtenerPorMetadatoIdYTipoDocumentalId.
     * @param metadatoId Identificador del metadato.
     * @param tipoDocumentalId Identificador del tipo Documental.
     * @return TrdMetadatoTipoDocm asociado.
     */
    TrdMetadatoTipoDocm obtenerPorMetadatoIdYTipoDocumentalId(Integer metadatoId, Integer tipoDocumentalId);
    
    /**
     * listarPorMetadato.
     * @param metadatoId Identificador del metadato.
     * @return TrdMetadatoTipoDocm asociado.
     */
    List<TrdMetadatoTipoDocm> listarPorMetadato(Integer metadatoId);
    
    /**
     * listarPorTipoDocumental.
     * @param tipoDocumentalId Identificador del tipo documental.
     * @return TrdMetadatoSubserie asociado.
     */
    List<TrdMetadatoTipoDocm> listarPorTipoDocumental(Integer tipoDocumentalId);
    
    /**
     * guardarTrdMetadatosTipoDocm.
     * @param token Token con identificación del usuario.
     * @param trdMetadatosTipoDocm Lista de TrdMetadatoTipoDocm a guardar.
     * @return lista de TrdMetadatoTipoDocm guardadas.
     */
    List<TrdMetadatoTipoDocm> guardarTrdMetadatosTipoDocm(Token token, List<TrdMetadatoTipoDocm> trdMetadatosTipoDocm);
}
