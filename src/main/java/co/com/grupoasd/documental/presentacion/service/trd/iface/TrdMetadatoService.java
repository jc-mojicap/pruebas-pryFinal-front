/*
* Archivo: TrdMetadatoService.java
* Fecha creacion = 12/06/2017
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
import co.com.grupoasd.documental.cliente.trd.model.TrdMetadato;

public interface TrdMetadatoService {
    
    /**
     * ListarTodos.
     * @return Lista de todos los metadatos.
     */
    public List<TrdMetadato> listarTodos();

    /**
     * listarPorIdTipoDatoNombre.
     * @param valor Valor para realizar la búsqueda.
     * @return Lista de todos los metadatos.
     */
    public List<TrdMetadato> listarPorIdTipoDatoNombre(String valor);
    
    /**
     * obtenerPorId.
     * @param metadatoId Identificador del metadato.
     * @return Metadato asociado.
     */
    public TrdMetadato obtenerPorId(Integer metadatoId);
    
    /**
     * guardarMetadatos.
     * @param token Token con identificación del usuario.
     * @param metadato Metadato a guardar.
     * @return Metadato guardado.
     */
    public TrdMetadato guardarMetadato(Token token, TrdMetadato metadato);
    
}
