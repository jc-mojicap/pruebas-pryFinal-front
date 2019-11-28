/*
* Archivo: CorComentarioServiceImpl.java
* Fecha creacion = 31/03/2017
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

import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.correspondencia.CorComentarioProxyService;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorComentario;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorComentarioService;

public class CorComentarioServiceImpl implements CorComentarioService {

    @Override
    public CorComentario obtenerPorId(Long id) {
        return CorComentarioProxyService.obtenerPorId(id);
    }

    @Override
    public List<CorComentario> obtenerPorRadicado(Long id) {
        return CorComentarioProxyService.obtenerPorRadicado(id);
    }

    @Override
    public CorComentario crear(Token token, CorComentario corComentario) {
        return CorComentarioProxyService.crear(token, corComentario);
    }

}
