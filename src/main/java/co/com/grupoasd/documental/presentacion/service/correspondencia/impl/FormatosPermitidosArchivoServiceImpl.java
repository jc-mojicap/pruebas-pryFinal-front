/*
* Archivo: FormatosPermitidosArchivoServiceImpl.java
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
package co.com.grupoasd.documental.presentacion.service.correspondencia.impl;

import java.util.List;

import co.com.grupoasd.documental.cliente.correspondencia.FormatosPermitidosArchivoProxyService;
import co.com.grupoasd.documental.cliente.correspondencia.model.FormatosPermitidosArchivo;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.FormatosPermitidosArchivoService;

public class FormatosPermitidosArchivoServiceImpl implements FormatosPermitidosArchivoService {

    @Override
    public FormatosPermitidosArchivo obtenerPorId(Integer id) {
        return FormatosPermitidosArchivoProxyService.obtenerPorId(id);
    }

    @Override
    public List<FormatosPermitidosArchivo> listarTodos() {
        return FormatosPermitidosArchivoProxyService.listarTodos();
    }

}
