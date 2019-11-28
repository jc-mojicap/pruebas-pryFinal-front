/*
* Archivo: ParametroAplicacionServiceImpl.java
* Fecha creacion = 07/06/2017
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
package co.com.grupoasd.documental.presentacion.service.parametrizacion.impl;

import java.util.List;

import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.parametrizacion.ParametroAplicacionProxyService;
import co.com.grupoasd.documental.cliente.parametrizacion.model.ParametroAplicacion;
import co.com.grupoasd.documental.presentacion.service.parametrizacion.iface.ParametroAplicacionService;

public class ParametroAplicacionServiceImpl implements ParametroAplicacionService {

    @Override
    public List<ParametroAplicacion> listarTodos() {
        return ParametroAplicacionProxyService.listarTodos();
    }

    @Override
    public ParametroAplicacion obtenerPorIdYEmpresa(String parametroId, int empresaId) {
        return ParametroAplicacionProxyService.obtenerPorIdYEmpresa(parametroId, empresaId);
    }

    @Override
    public List<ParametroAplicacion> guardarParametros(Token token, List<ParametroAplicacion> parametrosAplicacion) {
        return ParametroAplicacionProxyService.guardarParametros(token, parametrosAplicacion);
    }


}
