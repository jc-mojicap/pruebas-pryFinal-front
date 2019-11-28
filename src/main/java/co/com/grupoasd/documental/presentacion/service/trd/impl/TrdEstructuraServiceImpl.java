/*
* Archivo: TrdEstructuraServiceImpl.java
* Fecha creacion = 06/06/2017
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
package co.com.grupoasd.documental.presentacion.service.trd.impl;

import java.util.List;

import co.com.grupoasd.documental.cliente.trd.TrdEstructuraProxyService;
import co.com.grupoasd.documental.cliente.trd.model.TrdEstructura;
import co.com.grupoasd.documental.presentacion.service.trd.iface.TrdEstructuraService;

public class TrdEstructuraServiceImpl implements TrdEstructuraService {

    @Override
    public List<TrdEstructura> listarTodos() {
        return TrdEstructuraProxyService.listarTodos();
    }

    @Override
    public TrdEstructura obtenerPorId(Integer id) {
        return TrdEstructuraProxyService.obtenerPorId(id);
    }

}
