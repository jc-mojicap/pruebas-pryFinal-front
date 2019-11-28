/*
 * Archivo: EstadoRadicadoService.java
 * Fecha creacion: 14/03/2017
 * Todos los derechos de propiedad intelectual e industrial sobre esta
 * aplicacion son de propiedad exclusiva del GRUPO ASESORIA EN
 * SISTEMATIZACION DE DATOS SOCIEDAD POR ACCIONES SIMPLIFICADA – GRUPO ASD S.A.S.
 * Su uso, alteracion, reproduccion o modificacion sin la debida
 * consentimiento por escrito de GRUPO ASD S.A.S.
 * autorizacion por parte de su autor quedan totalmente prohibidos.
 * 
 * Este programa se encuentra protegido por las disposiciones de la
 * Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
 * propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
 * previstas en la Ley.
 */
package co.com.grupoasd.documental.presentacion.service.correspondencia.iface;

import java.util.List;

import co.com.grupoasd.documental.cliente.correspondencia.model.CorAdjunto;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorCanal;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTerceroXRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorUsuarioXRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.EstadoRadicado;
import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.service.correspondencia.dto.RadicadoDto;

/**
 * Servicios del recurso estado radicado. 
 * 
 * @author cestrada
 *
 */
public interface CorrespondenciaRadicadoService {
    
    /*******************************
     * EstadoRadicado
     ******************************/
    
    /**
     * Lista los estados radicado activas e inactivas.
     * @return Listado con estados radicado. Si no existen retorna vacio.
     * @author cestrada
     */
    public List<EstadoRadicado> listarEstadosRadicado();
    
    
    /*******************************
     * CorCanal
     ******************************/
    
    /**
     * Buscar un corCanal por su id.
     * @param id identificador del corCanal.
     * @return CorCanal asociado.
     */
    CorCanal buscarCanalPorId(Integer id);
    
    /**
     * Lista los corCanales.
     * @return Lista de corCanales.
     */
    List<CorCanal> listarCanales();
    
    
    /*******************************
     * CorRadicado
     ******************************/
    
    /**
     * Lista los radicados de comunicación por filtros.
     * @param filtros Filtros para la consulta.
     * @return Listado con estados radicado. Si no existen retorna vacio.
     * @author cestrada
     */
    public List<RadicadoDto> listarRadicadosComunicacionPorFiltros(RadicadoDto filtros);
    
    /**
     * Cantidad de radicados filtrados.
     * @param filtros Filtros para la consulta.
     * @return Integer
     * @author cestrada
     */
    public Integer contarRadicadosComunicacionPorFiltros(RadicadoDto filtros);
    
    /**
     * Archivo de radicados filtrados enviando el tipo de archivo.
     * @return InfoMedia Archivo de radicados.
     * @author cestrada
     */
    public InfoMedia obtenerArchivoRadicadosComunicacionPorFiltros(RadicadoDto filtros, String tipoArchivo);
    
    
    /*******************************
     * CorAdjunto
     ******************************/

    /**
     * Lista los adjuntos de radicados.
     * @param radicadoId Identificador del radicado.
     * @param eliminado Adjunto eliminado.
     * @return Listado con adjuntos de radicados. Si no existen retorna vacio.
     * @author cestrada
     */
    public List<CorAdjunto> listarAdjuntosPorRadicadoIdEliminado(Long radicadoId, Boolean eliminado);    
    
    /**
     * Lista los adjuntos de radicados.
     * @param radicadoId Identificador del radicado.
     * @return Listado con adjuntos de radicados. Si no existen retorna vacio.
     * @author cestrada
     */
    public List<CorAdjunto> listarAdjuntosPorRadicadoId(Long radicadoId);
    
    /**
     * Cantidad de adjuntos por radicado.
     * @param radicadoId Identificador del radicado.
     * @param eliminado Adjunto eliminado.
     * @return Integer Cantidad de adjuntos.
     * @author cestrada
     */
    public Integer contarAdjuntosPorRadicadoIdEliminado(Long radicadoId, Boolean eliminado); 
    
    
    /*******************************
     * CorTerceroXRadicado
     ******************************/
    
    /**
     * listarRadicadosPorTerceroId.
     * @param terceroId Identificador del tercero.
     * @return Lista de radicados por tercero.
     * @author cestrada
     */
    public List<CorTerceroXRadicado> listarRadicadosPorTerceroId(Integer terceroId);
    
    /**
     * listarTercerosPorRadicadoId.
     * @param radicadoId Identificador del radicado.
     * @return Lista de terceros por radicado.
     * @author cestrada
     */
    public List<CorTerceroXRadicado> listarTercerosPorRadicadoId(Long radicadoId);
    
    
    /**
     * listarTercerosConRadicados.
     * @return Lista de terceros con radicados.
     * @author cestrada
     */
    public List<CorTerceroXRadicado> listarTercerosConRadicados();
    
    /*******************************
     * CorUsuarioXRadicado
     ******************************/
    
    /**
     * listarRadicadosPorUsuarioId.
     * @param terceroId Identificador del tercero.
     * @return Lista de radicados por usuario.
     * @author cestrada
     */
    public List<CorUsuarioXRadicado> listarRadicadosPorUsuarioId(Integer usuarioId);
    
    /**
     * listarUsuariosPorRadicadoId.
     * @param radicadoId Identificador del radicado.
     * @return Lista de usuarios por radicado.
     * @author cestrada
     */
    public List<CorUsuarioXRadicado> listarUsuariosPorRadicadoId(Long radicadoId);
    
    /**
     * listarUsuariosConRadicados.
     * @return Lista de usuarios con radicados.
     * @author cestrada
     */
    public List<CorUsuarioXRadicado> listarUsuariosConRadicados();
    
}
