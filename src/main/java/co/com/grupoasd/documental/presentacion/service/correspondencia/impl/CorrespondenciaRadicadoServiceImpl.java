/*
 * Archivo: CorrespondenciaRadicadoServiceImpl.java
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
package co.com.grupoasd.documental.presentacion.service.correspondencia.impl;

import java.util.List;

import co.com.grupoasd.documental.cliente.correspondencia.CorAdjuntoProxyService;
import co.com.grupoasd.documental.cliente.correspondencia.CorCanalProxyService;
import co.com.grupoasd.documental.cliente.correspondencia.CorRadicadoProxyService;
import co.com.grupoasd.documental.cliente.correspondencia.CorTerceroXRadicadoProxyService;
import co.com.grupoasd.documental.cliente.correspondencia.CorUsuarioXRadicadoProxyService;
import co.com.grupoasd.documental.cliente.correspondencia.EstadoRadicadoProxyService;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorAdjunto;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorCanal;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTerceroXRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorUsuarioXRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.EstadoRadicado;
import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.controller.util.Base64;
import co.com.grupoasd.documental.presentacion.service.correspondencia.dto.RadicadoDto;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorrespondenciaRadicadoService;

/**
 * Implementacion de CorrespondenciaRadicadoService.
 * @author cestrada
 *
 */
public class CorrespondenciaRadicadoServiceImpl implements CorrespondenciaRadicadoService{

   
    /*******************************
     * EstadoRadicado
     ******************************/
    @Override
    public List<EstadoRadicado> listarEstadosRadicado() {
        return EstadoRadicadoProxyService.listar();
    }

    
    /*******************************
     * CorCanal
     ******************************/
    @Override
    public CorCanal buscarCanalPorId(Integer id) {
        return CorCanalProxyService.obtenerPorId(id);
    }

    @Override
    public List<CorCanal> listarCanales() {
        return CorCanalProxyService.listar();
    }

    
    /*******************************
     * CorRadicado
     ******************************/
    
    @Override
    public List<RadicadoDto> listarRadicadosComunicacionPorFiltros(RadicadoDto filtros) {
        return CorRadicadoProxyService.listar(filtros);
    }
    
    @Override
    public Integer contarRadicadosComunicacionPorFiltros(RadicadoDto filtros) {
        return CorRadicadoProxyService.contar(filtros);
    }
    
    @Override
    public InfoMedia obtenerArchivoRadicadosComunicacionPorFiltros(RadicadoDto filtros, String tipoArchivo) {
        InfoMedia infoMedia = CorRadicadoProxyService.obtenerArchivo(filtros, tipoArchivo);
        infoMedia.setBytesArchivo(Base64.decodeFast(infoMedia.getArchivo()));
        return infoMedia;
    }

    /*******************************
     * CorAdjunto
     ******************************/
    
    @Override
    public List<CorAdjunto> listarAdjuntosPorRadicadoIdEliminado(Long radicadoId, Boolean eliminado) {
        return CorAdjuntoProxyService.listarPorRadicadoIdEliminado(radicadoId, eliminado);
    }


    @Override
    public List<CorAdjunto> listarAdjuntosPorRadicadoId(Long radicadoId) {
        return CorAdjuntoProxyService.listarPorRadicadoId(radicadoId);
    }


    @Override
    public Integer contarAdjuntosPorRadicadoIdEliminado(Long radicadoId, Boolean eliminado) {
        return CorAdjuntoProxyService.contarPorRadicadoIdEliminado(radicadoId, eliminado);
    }


    /*******************************
     * CorTerceroXRadicado
     ******************************/
    
    @Override
    public List<CorTerceroXRadicado> listarRadicadosPorTerceroId(Integer terceroId) {
        return CorTerceroXRadicadoProxyService.listarPorTerceroId(terceroId);
    }

    @Override
    public List<CorTerceroXRadicado> listarTercerosPorRadicadoId(Long radicadoId) {
        return CorTerceroXRadicadoProxyService.listarPorRadicadoId(radicadoId);
    }
    
    @Override
    public List<CorTerceroXRadicado> listarTercerosConRadicados() {
        return CorTerceroXRadicadoProxyService.listarTercerosConRadicados();
    }

    
    /*******************************
     * CorUsuarioXRadicado
     ******************************/
    
    @Override
    public List<CorUsuarioXRadicado> listarRadicadosPorUsuarioId(Integer usuarioId) {
        return CorUsuarioXRadicadoProxyService.listarPorUsuarioId(usuarioId);
    }


    @Override
    public List<CorUsuarioXRadicado> listarUsuariosPorRadicadoId(Long radicadoId) {
        return CorUsuarioXRadicadoProxyService.listarPorRadicadoId(radicadoId);
    }

    @Override
    public List<CorUsuarioXRadicado> listarUsuariosConRadicados() {
        return CorUsuarioXRadicadoProxyService.listarUsuariosConRadicados();
    }
    
}
