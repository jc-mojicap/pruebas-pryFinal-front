/*
* Archivo: CorTerceroServiceImpl.java
* Fecha creacion = 23/03/2017
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
package co.com.grupoasd.documental.presentacion.service.correspondencia.impl;

import java.util.List;

import co.com.grupoasd.documental.cliente.correspondencia.CorTerceroProxyService;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorTercero;
import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.comun.dto.Paginacion;
import co.com.grupoasd.documental.presentacion.comun.dto.RespuestaServicio;
import co.com.grupoasd.documental.presentacion.service.correspondencia.dto.ResultadoImpoTerceros;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorTerceroService;

public class CorTerceroServiceImpl implements CorTerceroService {

	@Override
	public List<CorTercero> listarPorEmpresaYNombre(Integer empresaId, String nombre, boolean inactivo) {
		return CorTerceroProxyService.listarPorEmpresaYNombre(empresaId, nombre, inactivo);
	}
	
	@Override
	public List<CorTercero> listarPorEmpresaYNumero(Integer empresaId, String numero, boolean inactivo) {
		return CorTerceroProxyService.listarPorEmpresaYNumero(empresaId, numero, inactivo);
	}

    @Override
    public List<CorTercero> listarPorNumeroNombreResponsableDireccionMunicipio(Integer empresaId, String valor) {
        return CorTerceroProxyService.listarPorNumeroNombreResponsableDireccionMunicipio(empresaId, valor);
    }

    @Override
    public List<CorTercero> listarTodos(Integer empresaId, Paginacion paginacion) {
        return CorTerceroProxyService.listarTodos(empresaId, paginacion);
    }

    @Override
    public Integer contarTodos(Integer empresaId) {
        return CorTerceroProxyService.contarTodos(empresaId);
    }

    
    @Override
    public List<CorTercero> listarPorNumeroConPaginacion(Integer empresaId, String numero, Paginacion paginacion) {
        return CorTerceroProxyService.listarPorNumeroConPaginacion(empresaId, numero, paginacion);
    }

    @Override
    public Integer contarPorNumero(Integer empresaId, String numero) {
        return CorTerceroProxyService.contarPorNumero(empresaId, numero);
    }

    @Override
    public List<CorTercero> listarPorNombreConPaginacion(Integer empresaId, String nombre,  Paginacion paginacion) {
        return CorTerceroProxyService.listarPorNombreConPaginacion(empresaId, nombre, paginacion);
    }
    
    @Override
    public Integer contarPorNombre(Integer empresaId, String nombre) {
         return CorTerceroProxyService.contarPorNombre(empresaId, nombre);
    }

    @Override
    public List<CorTercero> listarPorNumeroNombreConPaginacion(Integer empresaId, String numero, String nombre,  Paginacion paginacion) {
        return CorTerceroProxyService.listarPorNumeroNombreConPaginacion(empresaId, numero, nombre, paginacion);

    }

    @Override
    public Integer contarPorNumeroNombre(Integer empresaId, String numero, String nombre) {
        return CorTerceroProxyService.contarPorNumeroNombre(empresaId, numero, nombre);
    }

    @Override
    public CorTercero guardar(CorTercero tercero) {
        return CorTerceroProxyService.guardar(tercero);
    }

    @Override
    public CorTercero inactivar(CorTercero tercero) {
        return CorTerceroProxyService.inactivar(tercero);
    }

    @Override
    public RespuestaServicio validar(String nombreArchivo) {
        return CorTerceroProxyService.validar(nombreArchivo);
    }

    @Override
    public ResultadoImpoTerceros importar(InfoMedia archivoImporte, Integer empresaId, Integer usuarioId) {
        return CorTerceroProxyService.importar(archivoImporte, empresaId, usuarioId);
    }
}

